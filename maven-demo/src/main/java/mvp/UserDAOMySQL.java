package mvp;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOMySQL implements UserDAO{
	
    ConnectionClass conn = new ConnectionClass();
    Connection con = conn.getConnection();
    ResultSet rs;
    PreparedStatement stmt;
    String usern;
    String mail;
    String password;
    String username;
    String name;
    String surname;
    String gender;
    Date dateBirth;
    String country;
    boolean listVisibility;
    boolean receptionStatus;
    
	
	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		User user = new User();

        try {
            stmt = con.prepareStatement("SELECT * FROM User WHERE Email = ?;");
            stmt.setString(1, email);
            rs = stmt.executeQuery();

            while(rs.next()){
               mail =  rs.getString(1);
               password = rs.getString(2);
               username = rs.getString(3);
               name = rs.getString(4);
               surname = rs.getString(5);
               gender = rs.getString(6);
               dateBirth = rs.getDate(7);
               country = rs.getString(8);
               listVisibility = rs.getBoolean(9);
               receptionStatus = rs.getBoolean(10);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        user.setEmail(mail);
        user.setPassword(password);
        user.setUsername(username);
        user.setName(name);
        user.setSurname(surname);
        user.setGender(gender);
        user.setDateBirth(dateBirth);
        user.setCountry(country);
        user.setListVisibility(listVisibility);
        user.setReceptionStatus(receptionStatus);

        return user;
	}

	
	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		User user = new User();
		try {
            stmt = con.prepareStatement("SELECT * FROM User WHERE Username = ?;");
            stmt.setString(1, username);
            rs = stmt.executeQuery();

            while(rs.next()){
               mail =  rs.getString(1);
               password = rs.getString(2);
               usern = rs.getString(3);
               name = rs.getString(4);
               surname = rs.getString(5);
               gender = rs.getString(6);
               dateBirth = rs.getDate(7);
               country = rs.getString(8);
               listVisibility = rs.getBoolean(9);
               receptionStatus = rs.getBoolean(10);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        user.setEmail(mail);
        user.setPassword(password);
        user.setUsername(usern);
        user.setName(name);
        user.setSurname(surname);
        user.setGender(gender);
        user.setDateBirth(dateBirth);
        user.setCountry(country);
        user.setListVisibility(listVisibility);
        user.setReceptionStatus(receptionStatus);

        return user;

	}

	
	public void saveUser(User user) {
		// TODO Auto-generated method stub
		

        try {
            stmt = con.prepareStatement("INSERT INTO User VALUES ( 'null','" +
                    "" + user.getEmail() +"', " +
                    "'" + user.getPassword() +"', " +
                    "'"+ user.getUsername() +"', " +
                    "'" + user.getName() +"', " +
                    "'" + user.getSurname() +"', " +
                    "'"+ user.getGender() +"', " +
                    "'"+ user.getDateBirth() +"', " +
                    "'"+ user.getCountry() +"', " +
                    "'"+ user.isListVisibility() +"', " +
                    "'"+ user.isReceptionStatus() +"')");
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
        try{

            stmt.executeUpdate("UPDATE User set " +
                    "Email='"+ user.getEmail() +"' " +
                    "Password='"+ user.getPassword() +"', " +
                    "Name='"+ user.getName() +"', " +
                    "Surname='"+ user.getSurname() +"', " +
                    "Gender='"+ user.getGender() +"', " +
                    "DateBirth='"+ user.getDateBirth() +"', " +
                    "Country='"+ user.getCountry() +"', " +
                    "ListVisibility='"+ user.isListVisibility() +"', " +
                    "ReceptionStatus='"+ user.isReceptionStatus() +"'  " +
                    "WHERE username ='"+ user.getUsername() +"'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		List<User> listUsers = new ArrayList<User> ();
		
		try {
            stmt = con.prepareStatement("SELECT * FROM User;");
            rs = stmt.executeQuery();

            while(rs.next()){
               mail =  rs.getString(1);
               password = rs.getString(2);
               username = rs.getString(3);
               name = rs.getString(4);
               surname = rs.getString(5);
               gender = rs.getString(6);
               dateBirth = rs.getDate(7);
               country = rs.getString(8);
               listVisibility = rs.getBoolean(9);
               receptionStatus = rs.getBoolean(10);
               
               User user = new User();
               user.setEmail(mail);
               user.setPassword(password);
               user.setUsername(username);
               user.setName(name);
               user.setSurname(surname);
               user.setGender(gender);
               user.setDateBirth(dateBirth);
               user.setCountry(country);
               user.setListVisibility(listVisibility);
               user.setReceptionStatus(receptionStatus);
               
               listUsers.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listUsers;
	}
	
}
