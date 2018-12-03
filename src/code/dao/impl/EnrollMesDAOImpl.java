package code.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import code.dao.EnrollMesDAOI;
import code.domain.Enroll;

@Repository("enrollMesDAO")
public class EnrollMesDAOImpl extends GenericDAOImpl<Enroll> implements EnrollMesDAOI{

	@Override
	public List<Enroll> readEnroll(String publishername,int begin) {
		String hql = "from Enroll where publishername = '"+publishername+"' order by id desc";
		Session session = this.getSession();
		Query query =  session.createQuery(hql);
		query.setFirstResult(begin);
		query.setMaxResults(7);
		return (List<Enroll>)query.list();
	}

	@Override
	public long enrollLogCount(String publishername) {
		String hql = "select count(*) from Enroll where publishername='"+publishername+"'";
		List list = this.getHibernateTemplate().find(hql);
		return (long)list.get(0);
	}

}
