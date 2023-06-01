package control;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class accounts {
	@Id
	String manv;
	String username,password;
	String role;
	public accounts() {
		
	}
	public accounts( String username, String password, String role) {
		super();
		this.manv = "";
		this.username = username;
		this.password = password;
		this.role = role;
	}
	public String getManv() {
		return manv;
	}
	public void setManv(String manv) {
		this.manv = manv;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public boolean equals(accounts x) {
		if(x.username.equals(username)&&x.password.equals(password)&&x.role.equals(role)) return true;
		return false;
	}
	public String toString() {
		return username+" "+password+" "+role;
	}
}
