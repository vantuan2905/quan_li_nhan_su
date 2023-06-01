package control;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class chucvu {
	@Id
	String macv;
	String tencv,phongban;
	long luongcoban;
	int phucap;
	public chucvu() {
		
	}
	public chucvu(String macv, String tencv, String phongban, long luongcoban,int phucap) {
		super();
		this.macv = macv;
		this.tencv = tencv;
		this.phongban = phongban;
		this.luongcoban = luongcoban;
		this.phucap=phucap;
	}
	
	public int getPhucap() {
		return phucap;
	}
	public void setPhucap(int phucap) {
		this.phucap = phucap;
	}
	public void setMacv(String macv) {
		this.macv = macv;
	}
	public void setTencv(String tencv) {
		this.tencv = tencv;
	}
	public void setPhongban(String phongban) {
		this.phongban = phongban;
	}
	public void setLuongcoban(long luongcoban) {
		this.luongcoban = luongcoban;
	}
	public String toString() {
		return String.format("%s %s %s %d", macv,tencv,phongban,luongcoban);
	}
}
