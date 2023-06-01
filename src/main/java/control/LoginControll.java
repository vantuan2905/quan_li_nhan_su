package control;
import java.net.http.HttpResponse;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.resource.HttpResource;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
@SessionAttributes({"ds_nhanvien","CV"})
public class LoginControll {
	@Autowired
	repoService rps;
	@ModelAttribute
	public void init(Model model) {
		model.addAttribute("ds_nhanvien",rps.findAllNhanvien());
	}
	@ModelAttribute("CV")
	public List<String> sessionCV() {
		List<chucvu> dscv=rps.findAllChucvu();
		List<String> dscv2=new ArrayList<>();
		for(chucvu i:dscv) dscv2.add(i.macv);
		return dscv2;
	}
	@GetMapping
	public String login() {
		
		return "login";
	}
	@GetMapping("/logout")
	public String logout(Model model) {
		model.addAttribute("message_logout", "Dang xuat thanh cong. Moi ban dang nhap ");
		return "login";
	}
	@PostMapping("/authen")
	public String authen(HttpSession session,@RequestParam("username") String username,@RequestParam("password") String pass,@RequestParam("role") String role,Model model) {
		List<accounts> ds=rps.findAllAccount();
		for(accounts i:ds) {
			if(i.username.equals(username)&&i.password.equals(pass)&&i.role.equals(role)&&role.equals("admin")) {
				session.setAttribute("ds_nhanvien", rps.findAllNhanvien());
				return "adminPage";}
			if(i.username.equals(username)&&i.password.equals(pass)&&i.role.equals(role)&&role.equals("nhanvien")) {
				model.addAttribute("nhanvien", rps.findNhanvienById(i.manv));
				session.setAttribute("nhanvienSession", rps.findNhanvienById(i.manv));
				return "nhanvienPages";
			}
		}		
		model.addAttribute("message", "nhap sai tai khoan hoac mat khau");
		return "login";
	}
	@PostMapping("/themNhanvien")
	public String themNhanvien(@RequestParam("manv") String manv,@RequestParam("tennv") String tennv,@RequestParam("dc") String dc,
			@RequestParam("sdt") String sdt,@RequestParam("chucvu") String chucvu,Model model) {
		nhanvien nv=new nhanvien(manv, tennv, dc, sdt, chucvu);
		System.out.println(chucvu+"  "+nv);
		String role="nhanvien";
		if(chucvu.equals("TP")) role="admin";
		accounts ac=new accounts(manv, manv, role);ac.setManv(manv);
		luong lg=new luong(manv, 0);
		rps.saveLuong(lg);
		rps.saveAccount(ac);
		model.addAttribute("ds_nhanvien",rps.findAllNhanvien());
		rps.saveNhanvien(nv);
		return "adminPage";
	}
	@GetMapping("/XacNhanXoaNhanvien")
	public String xacNhanXoaNhanVien(@RequestParam("manv") String manv,Model model) {
		System.out.println("mnv "+manv);
		model.addAttribute("NhanVienCanXoa",rps.findNhanvienById(manv));
		return "XacNhanXoaNV";
	}
	@GetMapping("/xoaNhanvien")
	public String XoaNhanVien(@RequestParam("manv") String manv,@RequestParam("xoa") String xoa,HttpSession session) {
		if(xoa.equals("No")) return "adminPage";
		rps.deleteNhanvienById(manv);
		rps.deleteLuongById(manv);
		rps.deleteAccountById(manv);
		session.setAttribute("ds_nhanvien", rps.findAllNhanvien());
		return "adminPage";
	}
	@GetMapping("/ChamCong")
	public String ChamCong(Model model) {
		List<nhanvien> dsNV=rps.findAllNhanvien();
		model.addAttribute("ds_nhanvien" , rps.findAllNhanvien());
		Date ngayHienTai=new Date();
		model.addAttribute("ngayHienTai", ngayHienTai);
		Calendar calendar=new GregorianCalendar();
		calendar.setTime(ngayHienTai);
		int day=calendar.get(Calendar.DAY_OF_MONTH);
		int month=calendar.get(Calendar.MONTH);
		int year=calendar.get(Calendar.YEAR);
		java.sql.Date a=new java.sql.Date(year, month, day);
		model.addAttribute("ngayHienTai", day);model.addAttribute("thangHienTai", month+1);model.addAttribute("namHienTai",year);
		return "ChamCongPage";
	}
	@GetMapping("/errors")
	public String error() {
		System.out.println("hello");
		return "adminPage";
	}
	@GetMapping("/errors2")
	public String error2(Model model,@RequestParam("manv") String manv) {
		
		model.addAttribute("nhanvien", rps.findNhanvienById(manv));
		return "nhanvienPages";
	}
	@GetMapping("/XuLiChamCong")
	public String XuLiChamCong(Model model,@RequestParam("trangthai") String[] trangthai,@RequestParam("ngay") int ngay
			,@RequestParam("thang") int thang,@RequestParam("nam") int nam) throws ParseException {
		
			DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            df.setLenient(false);
            df.parse(ngay+"-"+thang+"-"+nam);
           thang-=1;
		List<nhanvien> dsNV=rps.findAllNhanvien();List<String> NhanVienCoMat = Arrays.asList(trangthai); 
		//ArrayList<String> NhanVienCoMat=(ArrayList<String>) Arrays.asList(trangthai);
		for(nhanvien i:dsNV) {
			if(NhanVienCoMat.contains(i.getManv()))
			rps.saveChamcong(new chamcong(ngay, thang,nam, i.getManv(), "1"));
			else rps.saveChamcong(new chamcong(ngay, thang,nam, i.getManv(), "0"));
		}
		return "adminPage";
	}
	@GetMapping("/search_nv")
	public String locNv(@RequestParam("kieuloc") String kieuloc, @RequestParam("manv") String thongtin,Model model) {
		if(kieuloc.equals("manv")) {
			
			model.addAttribute("dsnhanvien",rps.findNhanvienById(thongtin) );
			return "search_nv";

		}else if(kieuloc.equals("tennv")){
			model.addAttribute("dsnhanvien",rps.findByTennv(thongtin));
			return "search_nv";
		}else {
			model.addAttribute("dsnhanvien", rps.findByChucvu(thongtin));
			return "search_nv";
		}
	}
	@GetMapping("/TinhLuong")
	public String tinhluong() {
		return "tinhluong";
	}
	@GetMapping("/ThucHienTinhLuong")
	public String ThucHienTinhluong(@RequestParam String thang,Model model) {
		int thag=Integer.parseInt(thang);
		List<nhanvien> dsNV=rps.findAllNhanvien();
		List<TienCongNhanVien> ds=new ArrayList<>();
		for(nhanvien i:dsNV) {
			String manv=i.getManv();
			chucvu cv=rps.findChucvuById(i.getChucvu());
			long soNgayLam=rps.demNgayCong(manv, thag);
			
			TienCongNhanVien a=new TienCongNhanVien(manv, i.getTennv(), cv.luongcoban, cv.getPhucap(), soNgayLam);
			System.out.println(manv+"  "+thag+" "+soNgayLam+" "+a);
			ds.add(a);
		}
		System.out.println(ds.size());
		model.addAttribute("tienCong", ds);
		model.addAttribute("thag", thag);
		return "KQtinhluong";
	}
	@ExceptionHandler({ParseException.class})
	public String xuli() {
		return "DayInvalid";
	}
	@GetMapping("/XemChamCong")
	public String XemChamCong(Model model,@RequestParam("manv") String manv) {
			model.addAttribute("nhanvien", rps.findNhanvienById(manv));
			model.addAttribute("manv",manv);
			return "XemChamCong";
	}
	@GetMapping("/XuLiXemChamCong")
	public String XuLiXemChamCong(Model model,@RequestParam("manv") String manv,@RequestParam("thang") int thang) {
			model.addAttribute("nhanvien", rps.findNhanvienById(manv));
			model.addAttribute("thag", thang);
			model.addAttribute("manv",manv);
			List<chamcong> ds=rps.findChamCong(manv, thang);
			model.addAttribute("ds", ds);
			return "KQXemChamCong";
	}
	@GetMapping("/doiMatKhau")
	public String matkhau(Model model,@RequestParam String manv) {
		model.addAttribute("nhanvien", rps.findNhanvienById(manv));
		return "doimk";
	}
	@PostMapping("/XuLiDoiMatKhau")
	public String doimk(@RequestParam String manv,@RequestParam("password") String pass,@RequestParam("password_new") String
			
			pass1,@RequestParam("password_new2") String pass2,Model model) {
		
		accounts ac=rps.findAccountById(manv);
		nhanvien x=rps.findNhanvienById(manv);
		System.out.println("ok ko"+ac.password+"  "+pass);
		if(pass.equals(ac.password)==false) {
			model.addAttribute("nhanvien", rps.findNhanvienById(manv));
			model.addAttribute("message_wrongpass", "bạn nhập sai mật khẩu");
			return "doimk";
		}
		if(pass1.equals(pass2)==false) {
			model.addAttribute("nhanvien", rps.findNhanvienById(manv));
			model.addAttribute("message_differentpass", "mật khẩu không khớp");
			return "doimk";
		}
		model.addAttribute("nhanvien", rps.findNhanvienById(manv));
		rps.updateAccount(pass1, x.manv);
		return "nhanvienPages";
	}
	//sua thong tin nhan vien trang nhan vien
		@PostMapping("/capnhap")
		public String capnhap(@RequestParam("manv") String manv,@RequestParam("tennv") String ten,@RequestParam("dc") String dc,
				@RequestParam("sdt") String sdt,@RequestParam("chucvu") String chucvu,Model model) {
			rps.updateNhanvien(ten, dc, sdt, manv);
			System.out.println(manv+" "+ten);
			nhanvien a=rps.findNhanvienById(manv);
			model.addAttribute("nhanvien", rps.findNhanvienById(manv));
			System.out.println(a.tennv);
			return "nhanvienPages";
		}
		//.
}
