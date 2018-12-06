package code.Patttern;

import code.domain.Activity;
import code.domain.MessageT;
import code.domain.User;

public interface Observer {
	public MessageT sendMessage(Activity activity);
	public void setUser(User user);

}
