package code.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import code.dao.ActivityDAOI;
import code.domain.Activity;
import code.domain.ActivityType;
import code.domain.User;

@Repository("activityDAO")
public class ActivityDAOImpl extends GenericDAOImpl<Activity> implements ActivityDAOI{

	
	@Override
	public ActivityType getActivityType(String name) {
		String hql = "from ActivityType where name = '"+name+"'";
		List<ActivityType> list = this.getHibernateTemplate().find(hql);
        if(list.size()>0){
        	return list.get(0);
        }
		return null;
	}

	@Override
	public int findCount(int activityType) {
		String hql="select count(*) from Activity as activity where activity.activityType.activityTypeId="+activityType;
		List<Long> list=this.getHibernateTemplate().find(hql);
		if(list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}
	@Override
	public List<Activity> findByPage(int begin, int pageSize,int activityType)
	{
		String hql="from Activity as activity where activity.activityType.activityTypeId="+activityType+" and activity.status = '已通过' order by activity.activityId desc";
		Session session=this.getSession();
		
		Query query=session.createQuery(hql);
		query.setFirstResult(begin);
	    query.setMaxResults(pageSize);
	    List<Activity> list=query.list();
	    
	    session.close();
	    
	    return list;
		
	}
	@Override
	public List<Activity> findByCondition(int begin, int pageSize,int activityType,Map<String,String> refers)
	{
		String hql="from Activity as activity where activity.activityType.activityTypeId="+activityType;
		//遍历循环条件 拼接语句
		//不存在status的值
		if(!refers.containsKey("status"))
		{
			hql+=" and activity.status = '已通过'";
		}
		for(Entry<String, String> entry : refers.entrySet())
		{
			hql+=" and activity."+entry.getKey()+"='"+entry.getValue()+"'";
		}
		hql+=" order by activity.activityId desc";
		
		System.out.println("hql"+hql);
		Session session=this.getSession();
		
		Query query=session.createQuery(hql);
		query.setFirstResult(begin);
	    query.setMaxResults(pageSize);
	    List<Activity> list=query.list();
	  
	    session.close();
	    
	    return list;
		
	}
	
	//根据activityId查询activity实体
	@Override
	public Activity findById(int activityId) {
		String hql="from  Activity as activity where activity.activityId=?";
		Activity activity=null;
		
		List<Activity> activityList=this.getHibernateTemplate().find(hql,activityId);
		if(activityList.size()!=0 && activityList!=null)
		{
			activity=new Activity();
			activity=activityList.get(0);
		}
		
		return activity;
	}

	@Override
	public int getAcPerNum(int activityId) {
		String hql = "select a.joinerList from Activity a where a.activityId = "+activityId;
		List<User> list = this.getHibernateTemplate().find(hql);
		return list.size();
	}

	@Override
	public User findPublisherByAcId(int activityId) {
		String hql = "select a.publisher from Activity a where a.activityId = "+activityId;
		List<User> list = this.getHibernateTemplate().find(hql);
		if(list.size()>0)
			return list.get(0);
		return null;
	}

	@Override
	public ActivityType findTypeByAcId(int activityId) {
		String hql = "select a.activityType from Activity a where a.activityId = "+activityId;
		List<ActivityType> list = this.getHibernateTemplate().find(hql);
		if(list.size()>0)
			return list.get(0);
		return null;
	}
	
	@Override
	public List<Long> groupBy(){
//		System.out.println("我在groupby");
		String hql = "select count(*) from Activity a where a.status = '已通过' group by a.activityType.activityTypeId";
		List<Long> list = this.getHibernateTemplate().find(hql);
//		System.out.println("groupby 结束啦啦啦啦啦啦啦啦啦");
		return list;
	}

	@Override
	public List<User> findJoiner(int activityId) {
		String hql = "select a.joinerList from Activity a where a.activityId = "+activityId;
		List<User> list = this.getHibernateTemplate().find(hql);
		return list;
	}

	@Override
	public List<User> findTrueJoiner(int activityId) {
		String hql = "select a.trueJoinerList from Activity a where a.activityId = "+activityId;
		List<User> list = this.getHibernateTemplate().find(hql);
		return list;
	}
	
	@Override
	public List<User> findUnwillingOuter(int activityId) {
		String hql = "select a.unwillingOutList from Activity a where a.activityId = "+activityId;
		List<User> list = this.getHibernateTemplate().find(hql);
		return list;
	}

	@Override
	public int getAcTruePerNum(int activityId) {
		String hql = "select a.trueJoinerList from Activity a where a.activityId = "+activityId;
		List<User> list = this.getHibernateTemplate().find(hql);
		return list.size();
	}

	@Override
	public Integer getSearchJoCount(int userId, String content) {
		String hql =  "select a.joinActivityList from User a where a.userId = "+userId;
		List<Activity> templist = this.getHibernateTemplate().find(hql);
		int returnnum = 0;
		for(Activity ac:templist){
			if(ac.getStatus().equals(content))
				returnnum++;
		}
		return returnnum;
	}

	@Override
	public List<Activity> geSearchtJoAcByPage(int userId, int begin,String content) {
		String hql =  "select a.joinActivityList from User a where a.userId = "+userId;
		Session session = this.getSession();
		Query query = session.createQuery(hql);
		query.setFirstResult(begin);
		query.setMaxResults(3);
		List<Activity> templist = query.list();
		session.close();
		int size = templist.size();
		List<Activity> returnlist = new ArrayList<Activity>();
		for(int i=0;i<templist.size();i++){
			if(templist.get(size-1-i).getStatus().equals(content))
				returnlist.add(templist.get(size-1-i));
		}
		return returnlist;
	}

	@Override
	public Integer getSearchPubCount(int userId, String content) {
		String hql = "from Activity where publisher = "+userId;
		List<Activity> list = this.getHibernateTemplate().find(hql);
		int returnnum = 0;
		for(Activity ac:list){
			if(ac.getStatus().equals(content))
				returnnum++;
		}
		return returnnum;
	}

	@Override
	public List<Activity> geSearchtPubAcByPage(int userId, int begin,String content) {
		String hql = "from Activity where publisher = "+userId+" and status = '"+content+"' order by activityId desc";
		Session session = this.getSession();
		Query query = session.createQuery(hql);
		query.setFirstResult(begin);
		query.setMaxResults(3);
		List<Activity> list = query.list();
		session.close();
		return list;
	}

	@Override
	public long askEnrollCount(String publishername) {
		String hql = "select count(*) from Enroll where publishername = '"+publishername+"'";
		List list = this.getHibernateTemplate().find(hql);
		return (long)list.get(0);
	}

	@Override
	public void save(Activity obj) {
		this.getHibernateTemplate().save(obj);
		
	}

	@Override
	public void update(Activity obj) {
		this.getHibernateTemplate().update(obj);
		
	}

	@Override
	public void delete(Activity obj) {
		this.getHibernateTemplate().delete(obj);
		
	}

	@Override
	public int findCountByCondition(int activityType, Map<String, String> refers) {
		String hql="select count(*) from Activity as activity where activity.activityType.activityTypeId="+activityType;
		//遍历循环条件 拼接语句
		//不存在status的值
		if(!refers.containsKey("status"))
		{
			hql+=" and activity.status = '已通过'";
		}
		for(Entry<String, String> entry : refers.entrySet())
		{
			hql+=" and activity."+entry.getKey()+"='"+entry.getValue()+"'";
		}
		
		List<Long> list=this.getHibernateTemplate().find(hql);
		if(list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}

}
