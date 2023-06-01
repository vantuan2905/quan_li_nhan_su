package control;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class phongban {
	@Id
	String mapb;
	String tenpb;
	public String getMapb() {
		return mapb;
	}
	public void setMapb(String mapb) {
		this.mapb = mapb;
	}
	public String getTenpb() {
		return tenpb;
	}
	public void setTenpb(String tenpb) {
		this.tenpb = tenpb;
	}
	public phongban(String mapb, String tenpb) {
		super();
		this.mapb = mapb;
		this.tenpb = tenpb;
	}
	
}
