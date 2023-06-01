package tacos.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {
	protected Connection connection;
	public DBContext() {
		try {
			String url="jdbc:sqlserver://localhost:1433:databaseName=Trading2022";
			String username="sa";
			String passwordString="admin";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		}catch (ClassNotFoundException ex) {
			System.out.println(ex);
		}
	}
}
