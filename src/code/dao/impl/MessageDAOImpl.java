package code.dao.impl;

import org.springframework.stereotype.Repository;

import code.dao.MessageDAOI;
import code.domain.MessageT;

@Repository("messageDAO")
public class MessageDAOImpl extends GenericDAOImpl<MessageT> implements MessageDAOI{
	@Override
	public void save(MessageT obj) {
		this.getHibernateTemplate().save(obj);
	}
	@Override
	public void update(MessageT obj) {
		this.getHibernateTemplate().update(obj);
		
	}
}
