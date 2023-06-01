package control;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;

@Entity
@IdClass(chamcongkey.class)
public class chamcong {
	@Id
	private Date ngay;
	@Id
	private String manv;
	private String trangthai;
	public chamcong() {
		
	}
	public chamcong(Date ngay, String manv, String trangthai) {
		super();
		this.ngay = ngay;
		this.manv = manv;
		this.trangthai = trangthai;
	}
	public chamcong(int ngay,int thang,int nam, String manv, String trangthai) {
		super();
		this.ngay = new Date(nam, thang, ngay);
		this.manv = manv;
		this.trangthai = trangthai;
	}
	public String getNgay() {
		return ngay.getDate()+"-"+(ngay.getMonth()+1);
	}
	public void setNgay(Date ngay) {
		this.ngay = ngay;
	}
	public String getManv() {
		return manv;
	}
	public void setManv(String manv) {
		this.manv = manv;
	}
	public String getTrangthai() {
		return trangthai;
	}
	public void setTrangthai(String trangthai) {
		this.trangthai = trangthai;
	}
	
}
