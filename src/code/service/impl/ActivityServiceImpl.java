package code.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.Resource;
import javax.transaction.Synchronization;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import code.dao.ActivityDAOI;
import code.dao.UserDAOI;
import code.domain.Activity;
import code.domain.ActivityBO;
import code.domain.ActivityType;
import code.domain.Comment;
import code.domain.PageBean;
import code.domain.User;
import code.service.ActivityServiceI;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Transactional
@Repository("activityService")
public class ActivityServiceImpl implements ActivityServiceI{
	
	@Resource 
    private ActivityDAOI activityDAO;
	
	@Resource
	private UserDAOI userDAO;
	@Override
	public void publish(Activity activity) {
		activityDAO.save(activity);
	}
	@Override
	public ActivityType getActivityType(String name) {
		return activityDAO.getActivityType(name);
	}
	@Override
	//根据活动类型分页查询所属活动
	public PageBean<Activity> findByPage(int currPage, int activityType) {
		PageBean<Activity> pageBean=new PageBean<Activity>();
		
		//封装page
		pageBean.setCurrPage(currPage);//封装当前页数
		//封装每页记录数
		int pageSize=8;//每页显示的条数
		pageBean.setPageSize(pageSize);
		//封装总记录数
		int totalCount=activityDAO.findCount(activityType);//获取总记录数
		pageBean.setTotalCount(totalCount);
		//封装总页数
		double tc=totalCount;
		double num=Math.ceil(tc/pageSize);
		pageBean.setTotalPage((int)num);
		//封装每页显示的数据
		int begin=(currPage-1)*pageSize;
		List<Activity> list=activityDAO.findByPage(begin, pageSize,activityType);
		List<ActivityBO> returnlist = new ArrayList<ActivityBO>();
		for(Activity tempAc:list){
			int joinerCount = activityDAO.getAcPerNum(tempAc.getActivityId());
			ActivityBO acBo= new ActivityBO();
			acBo.po2bo(tempAc, joinerCount);
			returnlist.add(acBo);
		}
		pageBean.setReturnlist(returnlist);
//		pageBean.setList(list);
		return pageBean;
	}
	
	//根据活动类型与其他条件动态查询活动
	@Override
	public PageBean<Activity> findByCondition(int currPage, int activityType, Map<String, String> refers) {
		System.out.println("SERVICE");
		PageBean<Activity> pageBean=new PageBean<Activity>();
		
		//封装page
		pageBean.setCurrPage(currPage);//封装当前页数
		//封装每页记录数
		int pageSize=8;//每页显示的条数
		pageBean.setPageSize(pageSize);
		//封装总记录数
		int totalCount=activityDAO.findCountByCondition(activityType, refers);//获取总记录数
		pageBean.setTotalCount(totalCount);
		System.out.println("totalCount"+totalCount);
		//封装总页数
		double tc=totalCount;
		double num=Math.ceil(tc/pageSize);
		pageBean.setTotalPage((int)num);
		//封装每页显示的数据
		int begin=(currPage-1)*pageSize;
		List<Activity> list=activityDAO.findByCondition(begin, pageSize, activityType, refers);
		/*List<ActivityBO> returnlist = new ArrayList<ActivityBO>();
		for(Activity tempAc:list){
			int joinerCount = activityDAO.getAcPerNum(tempAc.getActivityId());
			ActivityBO acBo= new ActivityBO();
			acBo.po2bo(tempAc, joinerCount);
			returnlist.add(acBo);
		}
		pageBean.setReturnlist(returnlist);*/
		pageBean.setList(list);
		return pageBean;
	}
	@Override
	public List<Activity> getIndexActivity() {
		List<Activity> result=new ArrayList<Activity>();
		List<Activity> temp=null;
		int pageSize=6;
		//遍历取
		for(int i=1; i<=4; i++)
		{
			temp=new ArrayList<Activity>();
			temp=activityDAO.findByPage(0, pageSize,i);
			for(Activity a : temp)
			{
				result.add(a);
			}
//			System.out.println("这是我第"+i+"次遍历取");
		}
//		System.out.println("我取完了jjjjjjjjjjjjjjjjjjjjjjjjjjj");
		return result;
	}
	
	@Override
	public List<Long> groupBy(){
		return activityDAO.groupBy();
	}
	
	//根据活动id查询活动实体
	@Override
	public Activity findById(int activityId) {
		return activityDAO.findById(activityId);
	}
	@Override
	public int getAcPerNum(int activityId) {
		return activityDAO.getAcPerNum(activityId);
	}

	@Override
	public User findPublisherByAcId(int activityId) {
		return activityDAO.findPublisherByAcId(activityId);
	}
	@Override
	public ActivityType findTypeByAcId(int activityId) {
		return activityDAO.findTypeByAcId(activityId);
	}
	@Override
	public List<User> getJoiner(int activityId,int begin) {
		List<User> templist = activityDAO.findJoiner(activityId);
		List<User> returnlist = new ArrayList<User>();
		int size = templist.size();
		if(size>=3){
			if(begin<=size-4){
				for(int i=begin;i<begin+3;i++){
					returnlist.add(templist.get(size-1-i));
				}
			}
			else{
				for(int i=begin;i<=size-1;i++){
					returnlist.add(templist.get(size-1-i));
				}
			}
		}else{
			for(int i=0;i<=size-1;i++){
				returnlist.add(templist.get(size-1-i));
			}
		}
		return returnlist;
	}
	//接受报名者
		@Override
		public JSONArray addJoiner(int activityId,int userId) {
			List<User> list = activityDAO.findTrueJoiner(activityId);
			List<User> unWillingerlist = activityDAO.findUnwillingOuter(activityId);
			if(null == list){
				list = new ArrayList<User>();
			}
			if(null==unWillingerlist){                              
				unWillingerlist = new ArrayList<User>();
			}
			Activity activity = activityDAO.findById(activityId);
			JSONArray jsonArray = new JSONArray();
			boolean flag = false;
			if(!"已通过".equals(activity.getStatus())){
				flag=true;
				JSONObject obj = new JSONObject();              
				obj.put("info", "未处于报名期");
				jsonArray.add(obj);
			}else{
				for(User a:list){
					if(a.getUserId()==userId){
						flag=true;
						JSONObject obj = new JSONObject();              
						obj.put("info", "已接受过");
						jsonArray.add(obj);
						break;
					}
				}
				for(User a:unWillingerlist){                                    
					if(a.getUserId()==userId){
						flag=true;
						JSONObject obj = new JSONObject();
						obj.put("info", "已拒绝，不能接受");
						jsonArray.add(obj);
						break;
					}
				}
			}
			User user = userDAO.findById(userId);
			if(flag==false){
				list.add(user);
				activity.setTrueJoinerList(list);
				activityDAO.update(activity);
				JSONObject obj = new JSONObject();                  
				obj.put("info", "接受成功");
				jsonArray.add(obj);
			}
			if(list.size()>=3){
				int size = list.size();
				for(int i=0;i<3;i++){
					JSONObject obj = new JSONObject();
					obj.put("imgUrl", list.get(size-1-i).getImgUrl());
					obj.put("name", list.get(size-1-i).getName());
					obj.put("userId", list.get(size-1-i).getUserId());
					jsonArray.add(obj);
				}
			}else{
				int size = list.size();
				for(int i=0;i<=size-1;i++){
					JSONObject obj = new JSONObject();
					obj.put("imgUrl", list.get(size-1-i).getImgUrl());
					obj.put("name", list.get(size-1-i).getName());
					obj.put("userId", list.get(size-1-i).getUserId());
					jsonArray.add(obj);
				}
			}
			return jsonArray;
		}
	//拒绝报名者
	@Override
	public String refuseJoiner(int activityId, int userId) {
		
		List<User> list = activityDAO.findUnwillingOuter(activityId);
		List<User> truerlist = activityDAO.findTrueJoiner(activityId);
		if(null == list){
			list = new ArrayList<User>();
		}
		if(null==truerlist){
			list = new ArrayList<User>(); 
		}
		Activity activity = activityDAO.findById(activityId);
		JSONArray jsonArray = new JSONArray();
		boolean flag = false;
		if(!"已通过".equals(activity.getStatus())){
			flag=true;
			return "未处于报名期";
		}else{
			for(User a:list){
				if(a.getUserId()==userId){
					flag=true;
					return "已拒绝过";
				}
			}
			for(User a:truerlist){
				if(a.getUserId()==userId){
					flag=true;
					return "已接受，不能拒绝";
				}
			}
		}
		User user = userDAO.findById(userId);
		if(flag==false){
			list.add(user);
			activity.setUnwillingOutList(list);
			activityDAO.update(activity);
			return "拒绝成功";
		}
		return null;
	}
	//查看已加入的
	@Override
	public List<User> acceptJoiner(int activityId,int begin) {
		List<User> templist = activityDAO.findTrueJoiner(activityId);
		List<User> returnlist = new ArrayList<User>();
		int size = templist.size();
		if(size>=3){
			if(begin<=size-4){
				for(int i=begin;i<begin+3;i++){
					returnlist.add(templist.get(size-1-i));
				}
			}else{
				for(int i=begin;i<=size-1;i++){
					returnlist.add(templist.get(size-1-i));
				}
			}
		}else{
			for(int i=0;i<=size-1;i++){
				returnlist.add(templist.get(size-1-i));
			}
		}
		return returnlist;
	}
	@Override
	public int getAcTruePerNum(int activityId) {
		return activityDAO.getAcTruePerNum(activityId);
	}
	@Override
	public void stopActivity(int activityId) {
		Activity ac = this.findById(activityId);
		if(!ac.getStatus().equals("已结束")){
			ac.setStatus("已结束");
			activityDAO.update(ac);
		}
	}
	@Override
	public JSONArray searchJoinedAc(int userId, int begin,String content) {
		Integer logCount = activityDAO.getSearchJoCount(userId,content);
		List<Activity> list = activityDAO.geSearchtJoAcByPage(userId, begin,content);
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(logCount);
		int pernum = 0;
		for(Activity a:list){
			pernum = activityDAO.getAcPerNum(a.getActivityId());
			JSONObject ac = new JSONObject();
			ac.put("activityId", a.getActivityId());
			ac.put("name", a.getName());
			ac.put("status", a.getStatus());
			ac.put("imgUrl", a.getImgUrl());
			ac.put("time", a.getTime());
			ac.put("perNum", pernum);
			jsonArray.add(ac);
		}
		return jsonArray;
	}
	@Override
	public JSONArray searchPublishedAc(int userId, int begin,String content) {
		Integer logCount = activityDAO.getSearchPubCount(userId,content);
		List<Activity> list = activityDAO.geSearchtPubAcByPage(userId, begin,content);
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(logCount);
		int pernum = 0;
		for(Activity a:list){
			pernum = activityDAO.getAcPerNum(a.getActivityId());
			JSONObject ac = new JSONObject();
			ac.put("activityId", a.getActivityId());
			ac.put("name", a.getName());
			ac.put("status", a.getStatus());
			ac.put("imgUrl", a.getImgUrl());
			ac.put("time", a.getTime());
			ac.put("perNum", pernum);
			jsonArray.add(ac);
		}
		return jsonArray;
	}
	@Override
	public long askEnrollCount(String publishername) {
		return activityDAO.askEnrollCount(publishername);
	}
	//修改活动状态
	@Override
	public void changeStatus(int activityId, String status) {
		Activity activity=activityDAO.findById(activityId);
		activity.setStatus(status);
		activityDAO.update(activity);
	}
	//查询当前要评价的用户
	@Override
	public User findTrueJoin(int activityId, int index) {
		// TODO Auto-generated method stub
		List<User> list=activityDAO.findTrueJoiner(activityId);
		System.out.println(list.size());
		if(list!=null && list.size()-1>index)
		{
			return list.get(index-1);
		}
		return null;
	}
	//增加一条评论
	@Override
	public void addCommet(int activityId, String content, User commenter) {
		Activity activity=activityDAO.findById(activityId);
		Set<Comment> commentSet=activity.getCommentSet();
		Comment comment=new Comment();
		comment.setContent(content);
		comment.setCommenter(commenter);
		comment.setTime(new Date());
		commentSet.add(comment);
		activity.setCommentSet(commentSet);
		activityDAO.update(activity);
	}
}
