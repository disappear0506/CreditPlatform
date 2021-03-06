package code.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import code.Patttern.Observer;
import code.domain.Activity;
import code.domain.ActivityType;
import code.domain.Comment;
import code.domain.Score;
import code.domain.User;
import code.pattern.impl.ActStartSendMessage;
import code.pattern.impl.ComputerStrategyimpl;
import code.pattern.impl.CreditPointsFactoryimpl;
import code.pattern.impl.MessageSender;
import code.service.ActivityServiceI;
import code.service.CommentServiceI;
import code.service.MessageServiceI;
import code.service.ScoreServiceI;
import code.service.UserServiceI;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@ParentPackage("CreditPlatform")
@Controller
@Scope("prototype")
public class ActivityAction {

	HttpServletResponse response = ServletActionContext.getResponse();
	HttpServletRequest request = ServletActionContext.getRequest();
	
	@Resource
	private ActivityServiceI activityService;
	@Resource
	private UserServiceI userService;
	@Resource
	private CommentServiceI commentService;
	@Resource
	private ScoreServiceI scoreService;
	@Resource
	private MessageServiceI messageService;
	@Resource
	MessageSender sendMessage ;
	public Activity activity;
	
	private String background; 

	public String name;

	private int activityId;// 用于接收从页面传过来的activityId

	private int acPerNum;// 用来表示每个活动的报名人数

	private List<User> joinerlist;// 参与活动的人数
	
	private List<User> trueJoinerlist;
	private List<User> Outerlist;//被拒绝的人员
	private List<User> isdoList;//等待审核的报名人员

	public List<User> getOuterlist() {
		return Outerlist;
	}

	public void setOuterlist(List<User> outerlist) {
		Outerlist = outerlist;
	}

	public List<User> getIsdoList() {
		return isdoList;
	}

	public void setIsdoList(List<User> isdoList) {
		this.isdoList = isdoList;
	}

	public List<User> getTrueJoinerlist() {
		return trueJoinerlist;
	}

	public void setTrueJoinerlist(List<User> trueJoinerlist) {
		this.trueJoinerlist = trueJoinerlist;
	}

	private int userId;// 用于接收页面传来的userId;

	private List<File> file;

	private List<String> fileFileName;

	private List<String> fileContentType;

	private List<String> dataUrl = new ArrayList<String>();
	
	private int begin;
	
	private int joinCount;
	private int trueJoinCount;
	
	private String email;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getJoinCount() {
		return joinCount;
	}

	public void setJoinCount(int joinCount) {
		this.joinCount = joinCount;
	}

	public int getTrueJoinCount() {
		return trueJoinCount;
	}

	public void setTrueJoinCount(int trueJoinCount) {
		this.trueJoinCount = trueJoinCount;
	}

	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public int getAcPerNum() {
		return acPerNum;
	}

	public void setAcPerNum(int acPerNum) {
		this.acPerNum = acPerNum;
	}

	public List<User> getJoinerlist() {
		return joinerlist;
	}

	public void setJoinerlist(List<User> joinerlist) {
		this.joinerlist = joinerlist;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public int getActivityId() {
		return activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}
	
	private String content;//搜索条件
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	//我发布的活动条件搜索
	@Action(value="searchPublishedAc")
	public void searchPublishedAc(){
		String myContent = null;
		try {
			myContent = new String(content.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		User user = null;
		if(null==email)
			user = (User)request.getSession().getAttribute("user");
		else
			user = userService.findByEmail(email);
	    JSONArray jsonArray = activityService.searchPublishedAc(user.getUserId(), begin,myContent);
		PrintWriter out;
		response.setCharacterEncoding("UTF-8");
		try {
			out = response.getWriter();
			out.print(jsonArray);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//我参与的活动条件搜索
	@Action(value="searchJoinedAc")
	public void searchJoinedAc(){
		String myContent = null;
		try {
			myContent = new String(content.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		User user = null;
		if(null==email)
			user = (User)request.getSession().getAttribute("user");
		else
			user = userService.findByEmail(email);
	    JSONArray jsonArray = activityService.searchJoinedAc(user.getUserId(), begin,myContent);
		PrintWriter out;
		response.setCharacterEncoding("UTF-8");
		try {
			out = response.getWriter();
			out.print(jsonArray);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//结束活动
	@Action(value="stopActivity")
	public void stopActivity(){
		activityService.stopActivity(activityId);
	}

	// 接受报名者
	@Action(value = "joinerHandler")
	public void joinerHander() {
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		JSONArray jsonArray = activityService.addJoiner(activityId,userId);
		//发送信息
		
		try {
			PrintWriter out = response.getWriter();
			out.print(jsonArray);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//拒绝报名者
	@Action (value="refuseJoiner")
	public void refuseJoiner(){
		String result = activityService.refuseJoiner(activityId,userId);
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			PrintWriter out = response.getWriter();
			out.print(result);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 展示我的活动
	@Action(value = "myActivity", results = { @Result(location = "/WEB-INF/myActivity.jsp") })
	public String myActivity() {
		activity = activityService.findById(activityId);
		User publisher = activityService.findPublisherByAcId(activityId);
		ActivityType type = activityService.findTypeByAcId(activityId);
		activity.setPublisher(publisher);
		activity.setActivityType(type);
		acPerNum = activityService.getAcPerNum(activityId);
		joinerlist = activityService.getJoiner(activityId,0);
		trueJoinerlist = activityService.acceptJoiner(activityId,0); // 同意报名人员
		joinCount = activityService.getAcPerNum(activityId);
		trueJoinCount = activityService.getAcTruePerNum(activityId);
		Outerlist = activityService.findUnwillingOuter(activityId); //拒绝报名人员
		isdoList =activityService.getJoiner(activityId,0);//正在等待审核的报名人员
		
		Iterator<User> it = isdoList.iterator();  
		while(it.hasNext()) {  
		    User isdo = it.next();
		    for(User trueJoiner:trueJoinerlist)
			{
				if((isdo.getUserId())==trueJoiner.getUserId())
				{
					it.remove();
				}
			}
	       
		 }  
		it = isdoList.iterator();  
		while(it.hasNext()) {  
		    User isdo = it.next();
		    for(User Outer:Outerlist)
			{
				if((isdo.getUserId())==Outer.getUserId())
				{
					it.remove();
				}
			}
	       
		 }  
		System.out.println("11111111111111111+"+isdoList.size()+"+"+Outerlist.size()+"+"+trueJoinerlist.size());
		
		//System.out.println("11111111111111111+"+list.size()+list.get(0).getName());
		return "success";
	}
	
	//显示报名的人
	/*
    @Action(value="getJoinerByPage")
    public void getJoinerByPage(){
    	Integer logCount = activityService.getAcPerNum(activityId);
    	joinerlist = activityService.getJoiner(activityId,begin);
    	JSONArray jsonArray = new JSONArray();
    	jsonArray.add(logCount);
    	for(User user:joinerlist){
    		JSONObject jsonObject = new JSONObject();
    		jsonObject.put("userId",user.getUserId());
    		jsonObject.put("name", user.getName());
    		jsonObject.put("imgUrl", user.getImgUrl());
    		jsonObject.put("email",user.getEmail());
    		jsonArray.add(jsonObject);
    	}
    	try {
    		response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jsonArray);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }*/
    //显示报名通过的人
	/*
    @Action(value="getTrueJoinerByPage")
    public void getTrueJoinerByPage(){
    	Integer logCount = activityService.getAcTruePerNum(activityId);
		trueJoinerlist = activityService.acceptJoiner(activityId,begin);
    	JSONArray jsonArray = new JSONArray();
    	jsonArray.add(logCount);
    	for(User user:trueJoinerlist){
    		JSONObject jsonObject = new JSONObject();
    		jsonObject.put("userId",user.getUserId());
    		jsonObject.put("name", user.getName());
    		jsonObject.put("imgUrl", user.getImgUrl());
    		jsonObject.put("email", user.getEmail());
    		jsonArray.add(jsonObject);
    	}
    	try {
    		response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jsonArray);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }*/
    //活动整个流程控制
    //1.点击开始活动按钮
    @Action(value="startactivity",results = { @Result(location = "/WEB-INF/changeStatusSuccess.jsp") })
    public String startActivity()
    {
    	//修改活动状态为“进行中”
    	activityService.changeStatus(activityId, "进行中");
    	Activity activity=activityService.findById(activityId);
    	request.setAttribute("infomsg", "活动开启成功");
    	
    	List<User> joinUser = activity.getTrueJoinerList();
    	System.out.println("活动参与者人数："+joinUser.size());
    	for(User user:joinUser)
    	{
    		Observer observer=new ActStartSendMessage();
    		observer.setUser(user);
    		sendMessage.AddObserver(observer);
    	}
    	sendMessage.sendMessage(activity);
    	
    	return "success";
    }
    //2.结束活动按钮
    @Action(value="endactivity",results={ @Result(location="/WEB-INF/changeStatusSuccess.jsp")})
    public String endActivity()
    {
    	//修改活动状态为“待评价”
    	activityService.changeStatus(activityId, "已结束");
    	request.setAttribute("activityId", activityId);
    	request.setAttribute("infomsg", "活动结束成功");
    	return "success";
    }
    //3.评价自己发布的活动
    private int currPage;//评价的用户是第几个的标志
    public int getCurrPage() {
		return currPage;
	}
	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	@Action(value="evaluate",
			results={@Result(name = "success", location = "/WEB-INF/evaluate.jsp")
})
	public String evaluate(){
    	//获取要评价的活动信息
    	Activity activity=activityService.findById(activityId);
    	request.setAttribute("activity", activity);
    	//设置当前页
    	 //request.setAttribute("currPage", currPage);
    	//System.out.println("currpage:"+currPage);
    	//查询当前要评论的用户
    	trueJoinerlist=activityService.acceptJoiner2(activityId);
    	
		return "success";
	}
	public String commentContent;
	
	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	List<String> canyuscore = new ArrayList<String>();
	
	public List<String> getCanyuscore() {
		return canyuscore;
	}

	public void setCanyuscore(List<String> canyuscore) {
		this.canyuscore = canyuscore;
	}
	public String faqiscore;
	
	public String getFaqiscore() {
		return faqiscore;
	}

	public void setFaqiscore(String faqiscore) {
		this.faqiscore = faqiscore;
	}
	
	//评价活动
	@Action(value = "setEvaluate", results = { @Result(location = "/WEB-INF/changeStatusSuccess.jsp") })
	public String setEvaluate(){
		//保存活动评价
		Activity activity=activityService.findById(activityId);
		User user = (User)request.getSession().getAttribute("user");
		
		Comment comment = new Comment();
		comment.setActivity(activity);
		
		comment.setCommenter(user);
		comment.setContent(commentContent);
		comment.setTime(new Date());
		commentService.save(comment);
		
		//保存评分
		//根据活动类别设置总分
		int sum=0;
		int activityTypeId = activity.getActivityType().getActivityTypeId();
		sum = new CreditPointsFactoryimpl().getCreditPoint(activityTypeId);
		/*if(activityTypeId==1)
		{
			sum=10;
		}else if(activityTypeId==2)
		{
			sum=5;
		}else if(activityTypeId==3)
		{
			sum=15;
		}else if(activityTypeId==4){
			sum=8;
		}else{
			sum=20;
		}*/
		//1.发起者
		if(faqiscore!=null)
		{
			double score = Double.valueOf(faqiscore);
			//信用分计算
			ComputerStrategyimpl computerstrategyimpl=new ComputerStrategyimpl();
			if(score<5.0) { computerstrategyimpl.select(1);}
			else if(score==5.0) { computerstrategyimpl.select(2);}
			else  computerstrategyimpl.select(3);
			computerstrategyimpl.algorithm(score, sum);			
			//score = score/10.0*sum;
			
			Score scoreEntity=new Score();
			scoreEntity.setActivity(activity);
			scoreEntity.setPingjiaer(user);
			scoreEntity.setScore(score);
			scoreEntity.setTrueJoiner(activity.getPublisher());
			scoreService.save(scoreEntity);
		}
		//2.对参与者进行评价
		//先将自己从实际参与者中移除
		List<User> list=new ArrayList<User>();
		list = activity.getTrueJoinerList();
		Iterator<User> it = list.iterator();  
		while(it.hasNext()) {  
		    User canyuzhe = it.next();
		    if(canyuzhe.getUserId()==user.getUserId())
					it.remove();
		 }  
		for(int i=0; i<canyuscore.size();i++)
		{
			User canyuzhe = list.get(i);
			double score = Double.valueOf(canyuscore.get(i));
			score = score/10.0*sum;
			Score scoreEntity=new Score();
			scoreEntity.setActivity(activity);
			scoreEntity.setPingjiaer(user);
			scoreEntity.setScore(score);
			scoreEntity.setTrueJoiner(canyuzhe);
			scoreService.save(scoreEntity);
		}
		request.setAttribute("infomsg", "评价成功");
		return "success";
	}
	
	
	
    //4.评价自己参与的活动
    
    @Action(value="joinerevaluate", results={@Result(location="/WEB-INF/joinerevaluate.jsp"),})
    public String joinerevaluate(){
    	//获取要评价的活动信息
    	Activity activity=activityService.findById(activityId);
    	request.setAttribute("activity", activity);
    	return "success";
    }
    public int score;
    public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	//评价自己参加的活动的操作
	@Action(value="joinerevaluateChange",results={@Result(name = "success", location = "/WEB-INF/changeStatusSuccess.jsp")})
    public String joinerevaluateChange()
    {
    	//1.积分更改 
    	userService.changeScore(userId, score);
    	//2.添加活动评论
    	User user=userService.findById(userId);
    	activityService.addCommet(activityId, content, user);
    	request.setAttribute("activityId", activityId);
    	request.setAttribute("infomsg", "评论成功");
    	return "success";
    }
    
	// 展示活动详情
	@Action(value = "showAcdetails", results = { @Result(location = "/WEB-INF/acdetails.jsp") })
	public String showAcdetails() {
		activity = activityService.findById(activityId);
		User publisher = activityService.findPublisherByAcId(activityId);
		ActivityType type = activityService.findTypeByAcId(activityId);
		activity.setPublisher(publisher);
		activity.setActivityType(type);
		acPerNum = activityService.getAcPerNum(activityId);
		return "success";
	}

	@Action(value = "publish2", results = { @Result(location = "/WEB-INF/publish2.jsp") })
	public String publish() {
		return "success";
	}

	@Action(value = "publish3", results = { @Result(location = "/WEB-INF/publish3.jsp") })
	public String publish3() {
		String imgpath = "upload/";
		String path = ServletActionContext.getServletContext().getRealPath("/");
		InputStream is = null;
		OutputStream os = null;
		try {
			for (int i = 0; i < file.size(); i++) {
				String reg = "[\u4e00-\u9fa5]";
				Pattern pat = Pattern.compile(reg);  
			    Matcher mat=pat.matcher(this.getFileFileName().get(i)); 
			    String filename = mat.replaceAll("");
		        long time = System.currentTimeMillis();
				is = new FileInputStream(file.get(i));
				dataUrl.add(imgpath + time + filename);
				File destFile = new File(path + imgpath, time + filename);
				os = new FileOutputStream(destFile);

				byte[] buffer = new byte[400];

				int length = 0;

				while ((length = is.read(buffer)) > 0) {
					os.write(buffer, 0, length);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				is.close();
				os.flush();
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		activity.setImgUrl(dataUrl.get(0));
		activity.setActivityType(activityService.getActivityType(name));
		activity.setStatus("已发布");
		activity.setPublisher((User) ActionContext.getContext().getSession().get("user"));
		activityService.publish(activity);
		return "success";
	}

	public List<File> getFile() {
		return file;
	}

	public void setFile(List<File> file) {
		this.file = file;
	}

	public List<String> getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(List<String> fileFileName) {
		this.fileFileName = fileFileName;
	}

	public List<String> getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(List<String> fileContentType) {
		this.fileContentType = fileContentType;
	}

	public List<String> getDataUrl() {
		return dataUrl;
	}

	public void setDataUrl(List<String> dataUrl) {
		this.dataUrl = dataUrl;
	}

}
