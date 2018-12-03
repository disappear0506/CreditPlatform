package code.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import code.dao.GenericDAOI;

public abstract class GenericDAOImpl<T> extends HibernateDaoSupport implements GenericDAOI<T>{
	
	private Class<T> clzz;
	
	public GenericDAOImpl()
	{
		clzz = (Class<T>)((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	@Resource(name = "sessionFactory")
    public void setSuperSessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
    }

	@Override
	public List findByPage(int begin, int pageSize) {
		DetachedCriteria criteria = DetachedCriteria.forClass(clzz);
        List<T> list = this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> findByPage(String hql,int begin, int pageSize) {
		List<Object> list  = new ArrayList<Object>();
		final String str = hql;
		  final int offset = begin;
		  final int length = pageSize ;
		  list = this.getHibernateTemplate().executeFind(new HibernateCallback(){
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
               Query query = session.createQuery(str);
               query.setFirstResult(offset);
               query.setMaxResults(length);
               return  query.list();
			}
			});
		  return list ;
	}

	@Override
	public int findCount(String tableName) {
		String hql = "select count(*) from "+tableName;
        List<Long> list = this.getHibernateTemplate().find(hql);
        if(list.size()>0)
        	return list.get(0).intValue();
		return 0;
	}

	@Override
	public void save(Object obj) {
		this.getHibernateTemplate().save(obj);
	}

	@Override
	public T findById(Integer id) {
		return this.getHibernateTemplate().get(clzz, id);
	}

	@Override
	public void update(Object obj) {
		this.getHibernateTemplate().update(obj);
	}

	@Override
	public void delete(Object obj) {
		this.getHibernateTemplate().delete(obj);
	}
}
