package code.pattern.impl;

import java.util.Date;




import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import code.Patttern.Observer;
import code.domain.Activity;
import code.domain.MessageT;
import code.domain.User;

public class ActStartSendMessage implements Observer{

	public User user;

	@Override
	public MessageT sendMessage(Activity activity) {
		MessageT message = new MessageT();
		message.setContent("您报名的活动‘"+activity.getName()+"’开始啦~");
		message.setAccepter(user);
		message.setIsRead(0);
		message.setSender(activity.getPublisher());
		message.setSendTime(new Date());
		System.out.println("message:"+message);
		return message;
		//messageDAO.save(message);
		
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	

}
