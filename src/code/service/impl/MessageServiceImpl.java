package code.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import code.dao.MessageDAOI;
import code.domain.MessageT;
import code.service.MessageServiceI;

@Transactional
@Repository("messageService")
public class MessageServiceImpl implements MessageServiceI{

	@Resource
	private MessageDAOI messageDAO;
	@Override
	public void save(MessageT Message) {
		messageDAO.save(Message);
	}
	@Override
	public void update(MessageT message) {
		messageDAO.update(message);
		
	}

}
