package control;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class luong {
	@Id
	String manv;
	int songaylam;
	public luong() {
		
	}
	public String getManv() {
		return manv;
	}
	public void setManv(String manv) {
		this.manv = manv;
	}
	public int getSongaylam() {
		return songaylam;
	}
	public void setSongaylam(int songaylam) {
		this.songaylam = songaylam;
	}
	public luong(String manv, int songaylam) {
		super();
		this.manv = manv;
		this.songaylam = songaylam;}
	
}
