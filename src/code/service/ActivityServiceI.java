package code.service;

import java.util.List;
import java.util.Map;

import code.domain.Activity;
import code.domain.ActivityType;
import code.domain.PageBean;
import code.domain.User;
import net.sf.json.JSONArray;

public interface ActivityServiceI {
	public void publish(Activity activity);
	public ActivityType getActivityType(String name);
	//根据活动类型分页查询所属活动
	public PageBean<Activity> findByPage(int currPage,int activityType);
	//整合首页所需要的活动集合
	public List<Activity> getIndexActivity();
	//根据activityId查询活动实体
	public Activity findById(int activityId);
	//根据activityId查询活动对应的发布者
	public User findPublisherByAcId(int activityId);
	//根据activityId查询活动对应的类型
	public ActivityType findTypeByAcId(int activityId);
	//根据activityId统计该活动的报名人数
	public int getAcPerNum(int activityId);
	//根据activityId统计该活动实际参与的人数
	public int getAcTruePerNum(int activityId);
	public List<Long> groupBy();
	//查找活动的参与者
	public List<User> getJoiner(int activityId,int begin);
	//同意报名者加入
	public JSONArray addJoiner(int activityId,int userId);
	//拒绝报名者加入
	public String refuseJoiner(int activityId, int userId);
	//查看已加入的
	public List<User> acceptJoiner(int activityId,int begin);
	//结束活动
	public void stopActivity(int activityId);
	//根据条件搜索我发布的活动
	public JSONArray searchJoinedAc(int userId, int begin,String content);
	//根据条件搜索我参与的活动
	public JSONArray searchPublishedAc(int userId, int begin,String content);
	
	public long askEnrollCount(String publishername);
	
	//根据id修改活动状态
	public void changeStatus(int activityId,String status);
	//查询当前要评价的参与者
	public User findTrueJoin(int activityId,int index);
	//根据活动类型与其他条件查询pageBean
	public PageBean<Activity> findByCondition(int currPage,int activityType,Map<String,String> refers);
	//添加活动评论
	public void addCommet(int activityId,String content,User commenter);
	
}
