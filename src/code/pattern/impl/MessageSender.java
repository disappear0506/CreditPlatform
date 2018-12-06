package code.pattern.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import code.Patttern.Observer;
import code.dao.MessageDAOI;
import code.domain.Activity;
import code.domain.MessageT;
@Transactional
@Repository("MessageSender")
public class MessageSender {
	public List<Observer> observers=new ArrayList<Observer>();
	@Resource
	private MessageDAOI messageDAO;
	
	public void AddObserver(Observer observer)
	{
		observers.add(observer);
	}
	
	public void sendMessage(Activity activity){
		for(Observer observer:observers)
		{
			MessageT message = observer.sendMessage(activity);
			messageDAO.save(message);
		}
	}
	
}
