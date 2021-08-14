package mvp;

import java.util.List;

public interface UserDAO {
	public User getUserByEmail(String email);
	public User getUserByUsername(String username);
	public void saveUser(User user);
	public void updateUser(User user);
	public List<User >getAllUsers();
}
