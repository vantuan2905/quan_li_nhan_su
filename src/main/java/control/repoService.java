package control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
@Service
public class repoService {
	@Autowired
	repository_luong repoLuong;
	@Autowired
	private repository_chucvu repoCv;
	@Autowired
	repository_phongban repoPb;
	@Autowired
	repository_nhanvien repoNv;
	@Autowired
	repository_account repoAc;
	@Autowired
	repository_chamcong repoChamcong;
	public List<accounts> findAllAccount(){
		return repoAc.findAll();
	}
	public List<nhanvien> findAllNhanvien(){
		return repoNv.findAll();
	}
	
	public nhanvien findNhanvienById(String id) {
		return repoNv.findById(id).get();
	}
	
	
	public List<nhanvien> findByTennv (String name) {
		return repoNv.findByTennv(name);
	}
	public chucvu findChucvuById(String macv) {
		return repoCv.findById(macv).get();
	}
	public List<nhanvien> findByChucvu (String cvu){
		return repoNv.findByChucvu(cvu);
	}
	public List<chucvu> findAllChucvu(){
		return repoCv.findAll();
	}
	public void saveLuong(luong a) {
		repoLuong.save(a);
	}
	public void saveNhanvien(nhanvien a) {
		repoNv.save(a);
	}
	public void saveAccount(accounts a) {
		repoAc.save(a);
	}
	public void deleteNhanvienById(String id) {
		repoNv.deleteById(id);
	}
	public void deleteLuongById(String id) {
		repoLuong.deleteById(id);
	}
	public void deleteAccountById(String id) {
		repoAc.deleteById(id);
	}
	public void saveChamcong(chamcong a) {
		repoChamcong.save(a);
	}
	public long demNgayCong(String manv,int thang) {
		return repoChamcong.countNgaycong(manv, thang,"1");
	}
	public int updateNhanvien(String ten,String dc,String sdt,String manv) {
		repoNv.update(ten,dc,sdt, manv);return 1;
	}
	public List<chamcong> findChamCong(String manv,int thag) {
		return repoChamcong.findChamCong(manv, thag);
	}
	public void updateAccount(String pass,String manv) {
		repoAc.updateAccount( pass, manv);
	}
	public accounts findAccountById(String id) {
		return repoAc.findById(id).get();
	}
}
