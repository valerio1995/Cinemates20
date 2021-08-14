package mvp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdministratorDAOMySQL implements AdministratorDAO{

		ConnectionClass conn = new ConnectionClass();
	    Connection con = conn.getConnection();
	    ResultSet rs;
	    PreparedStatement stmt;
	    Administrator administrator = new Administrator();
	    String mail;
	    String password;
	    
	
	public Administrator getAdministratorByEmail(String email) {
		// TODO Auto-generated method stub
		
		try {
            stmt = con.prepareStatement("SELECT * FROM Amministratore WHERE email = ?;");
            stmt.setString(1, email);
            rs = stmt.executeQuery();

            while(rs.next()){
               mail =  rs.getString(1);
               password = rs.getString(2);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        administrator.setEmail(mail);
        administrator.setPassword(password);
        
        return administrator;
	}
	
}
