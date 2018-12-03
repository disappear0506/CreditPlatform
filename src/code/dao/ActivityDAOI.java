package code.dao;

import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import code.domain.Activity;
import code.domain.ActivityType;
import code.domain.User;

public interface ActivityDAOI extends GenericDAOI<Activity>{
	public ActivityType getActivityType(String name);
	//根据活动类型查询该活动所有条数
	public int findCount(int activityType);
	//根据活动类型分页查询活动
	public List<Activity> findByPage(int begin, int pageSize,int activityType);
	//根据activityId获取活动信息
	public Activity findById(int activityId);
	//根据activityId获取活动的发布者
	public User findPublisherByAcId(int activityId);
	//根据activityId获取活动的type
	public ActivityType findTypeByAcId(int activityId);
	//获取每个活动的报名人数
	public int getAcPerNum(int activityId);
	//获取每个活动的实际参与人数
	public int getAcTruePerNum(int activityId);
	public List<Long> groupBy();
	//查找活动的参与者
	public List<User> findJoiner(int activityId);
	//获取活动被同意的报名者
	public List<User> findTrueJoiner(int activityId);
	//查找活动被拒绝的报名者
	public List<User> findUnwillingOuter(int activityId);
	//获取按条件查找的报名的活动的数量
	public Integer getSearchJoCount(int userId, String content);
	//获取按条件查找报名的活动
	public List<Activity> geSearchtJoAcByPage(int userId, int begin,String content);
	//获取按条件查找发布的活动的数量
	public Integer getSearchPubCount(int userId, String content);
	//获取按条件查找的发布的活动
	public List<Activity> geSearchtPubAcByPage(int userId, int begin,String content);
	
	public long askEnrollCount(String publishername);
	//根据活动类型条件分页查询活动
	public List<Activity> findByCondition(int begin, int pageSize,int activityType,Map<String,String> refers);
	//根据活动类型和条件查询该活动所有条数
	public int findCountByCondition(int activityType,Map<String,String> refers);

}
