package code.dao.impl;

import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.opensymphony.xwork2.ActionContext;

import code.dao.UserDAOI;
import code.domain.Activity;
import code.domain.Enroll;
import code.domain.User;

@Repository("userDAO")
public class UserDAOImpl extends GenericDAOImpl<User> implements UserDAOI{
	
	@Override
	public User findByPhoAndPsw(String phone, String password) {
		String hql = "from User where phone = '"+phone+"' and password = '"+password+"'";
		List<User> list = this.getHibernateTemplate().find(hql);
        if(list.size()>0){
        	ActionContext.getContext().getSession().put("user",list.get(0));
        	return list.get(0);
        }
		return null;
	}

	@Override
	public User findByPho(String phone) {
		String hql = "from User where phone = '"+phone+"'";
		List<User> list = this.getHibernateTemplate().find(hql);
        if(list.size()>0)
        	return list.get(0);
		return null;
	}

	@Override
	public User findByEmail(String email) {
		String hql = "from User where email = '"+email+"'";
		List<User> list = this.getHibernateTemplate().find(hql);
        if(list.size()>0){
        	return list.get(0);
        }
		return null;
	}

	@Override
	public User findByEmaAndPsw(String email, String password) {
		String hql = "from User where email = '"+email+"' and password = '"+password+"'";
		List<User> list = this.getHibernateTemplate().find(hql);
        if(list.size()>0){
        	ActionContext.getContext().getSession().put("user",list.get(0));
        	return list.get(0);
        }
		return null;
	}
    
	@Override
	public List<Activity> getJoinActivityList(int id){
		String hql = "select a.joinActivityList from User a where a.userId = "+id;
		List<Activity> list = this.getHibernateTemplate().find(hql);
		return list;
	}

	@Override
	public void save(User obj) {

		this.getHibernateTemplate().save(obj);
	}

	@Override
	public void update(User obj) {
		this.getHibernateTemplate().update(obj);
		
	}

	@Override
	public void delete(User obj) {
		this.getHibernateTemplate().delete(obj);
		
	}

	@Override
	public List<Activity> getPublishedAcList(int id,int begin,int pageSize) {
		String hql = "select a from Activity a where a.publisher = "+id+" order by a.activityId desc";
		Session session = this.getSession();
		Query query = session.createQuery(hql);
		query.setFirstResult(begin);
		query.setMaxResults(pageSize);
		List<Activity> list = query.list();
		session.close();
		return list;
	}

	@Override
	public List<Activity> getJoAcListByPage(int id, int begin, int pageSize) {
		String hql="select a.joinActivityList from User a where a.userId = "+id;
		Session session = this.getSession();
		Query query = session.createQuery(hql);
		query.setFirstResult(begin);
		query.setMaxResults(pageSize);
		List<Activity> list = query.list();
		session.close();
		return list;
	}

	@Override
	public int getJoAcCount(int userId) {
		String hql =  "select a.joinActivityList from User a where a.userId = "+userId;
		List<Activity> list = this.getHibernateTemplate().find(hql);
		return list.size();
	}

	@Override
	public int getPublishedCount(int userId) {
		String hql = "from Activity where publisher = "+userId;
		List<Activity> list = this.getHibernateTemplate().find(hql);
		return list.size();
	}

	@Override
	public void addEnroll(String username, String activityname, String time,String publishername) {
		Enroll enroll = new Enroll(username,activityname,time,publishername);
		Session session = this.getSession();
		session.save(enroll);
		session.close();
	}

	@Override
	public User findById(int userId) {
		String hql = "from User where userId = '"+userId+"'";
		List<User> list = this.getHibernateTemplate().find(hql);
        if(list.size()>0)
        	return list.get(0);
		return null;
	}
}
