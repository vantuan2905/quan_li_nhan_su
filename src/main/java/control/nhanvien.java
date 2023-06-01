package control;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class nhanvien {
	public nhanvien() {
		
	}
	public nhanvien(String manv, String tennv, String dc, String sdt, String chucvu) {
		super();
		this.manv = manv;
		this.tennv = tennv;
		this.dc = dc;
		this.sdt = sdt;
		this.chucvu = chucvu;
	}
	@Id
	String manv;
	String tennv,dc,sdt,chucvu;
	public String getManv() {
		return manv;
	}
	public void setManv(String manv) {
		this.manv = manv;
	}
	public String getTennv() {
		return tennv;
	}
	public void setTennv(String tennv) {
		this.tennv = tennv;
	}
	public String getDc() {
		return dc;
	}
	public void setDc(String dc) {
		this.dc = dc;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public String getChucvu() {
		return chucvu;
	}
	public void setChucvu(String chucvu) {
		this.chucvu = chucvu;
	}
	public String toString() {
		return manv+"  "+chucvu;
	}
}
