package code.dao.impl;

import java.util.List;




import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.opensymphony.xwork2.ActionContext;

import code.dao.AdminDAOI;
import code.dao.UserDAOI;
import code.domain.Activity;
import code.domain.Admin;
import code.domain.Enroll;
import code.domain.User;

@Repository("adminDAO")
public class AdminDAOImpl extends GenericDAOImpl<Admin> implements AdminDAOI{
	
	@Override
	public Admin find(String name, String password) {
		String hql = "from Admin where name = '"+name+"' and password = '"+password+"'";
		List<Admin> list = this.getHibernateTemplate().find(hql);
        if(list.size()>0){
        	ActionContext.getContext().getSession().put("admin",list.get(0));
        	return list.get(0);
        }
		return null;
	}

	
}
