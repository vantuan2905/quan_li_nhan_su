package tacos.dao;

import model.Admin;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO extends DBContext {
	public Admin check(String username, String password) {
		String sql = "SELECT[Username]\n" + "  ,[Password]\n" + ",[role]\n" + "FROM [dbo].[Admin]\n"
				+ "where username=? and Password=?";
		try {
			PreparedStatement st = connection.prepareStatement(sql);
			st.setString(1, username);
			st.setString(2, password);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				Admin a = new Admin(rs.getString("Username"), rs.getString("Password"), rs.getInt("rele"));
				return a;
			}

		} catch (SQLException e) {

		}
		return null;
	}
}
