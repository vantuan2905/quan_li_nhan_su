package control;
public class TienCongNhanVien {
	private String manv,tennv;private long luongcoban,phucap,songaylam,tienuong;

	public TienCongNhanVien(String manv, String tennv, long luongcoban, long phucap, long songaylam) {
		super();
		this.manv = manv;
		this.tennv = tennv;
		this.luongcoban = luongcoban;
		this.phucap = phucap;
		this.songaylam = songaylam;
		this.tienuong = (luongcoban-(28-songaylam)*100+phucap)*1000;
		if(this.tienuong<0) this.tienuong=0;
	}

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

	public long getLuongcoban() {
		return luongcoban;
	}

	public void setLuongcoban(long luongcoban) {
		this.luongcoban = luongcoban;
	}

	public long getPhucap() {
		return phucap;
	}

	public void setPhucap(long phucap) {
		this.phucap = phucap;
	}

	public long getSongaylam() {
		return songaylam;
	}

	public void setSongaylam(long songaylam) {
		this.songaylam = songaylam;
	}

	public long getTienuong() {
		return tienuong;
	}

	public void setTienuong(long tienuong) {
		this.tienuong = tienuong;
	}
	public String toString() {
		return manv+" "+tennv+"  "+songaylam+" "+luongcoban+" "+phucap+"  "+tienuong;
	}
}
