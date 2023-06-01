package model;
import lombok.Data;
@Data
public class Admin {
	
	private String username;
	private String password;
	private int role;
    public Admin(String username, String password, int role) {
		this.username=username;
		this.password=password;
		this.role=role;
	}

}
