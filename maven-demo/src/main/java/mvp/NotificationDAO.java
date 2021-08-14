package mvp;

import java.util.List;

public interface NotificationDAO {
	public void saveNotification(Notification value);
	public List<Notification> getRequestNotifications(String senderUsername);
	public List<Notification> getNotifications(String receiverUsername);
}
