package code.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.annotation.Resource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.ActionContext;

import code.dao.ActivityDAOI;
import code.dao.MessageDAOI;
import code.dao.UserDAOI;
import code.domain.Activity;
import code.domain.Comment;
import code.domain.User;
import code.service.UserServiceI;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import code.domain.MessageT;
@Transactional
@Repository("userService")
public class UserServiceImpl implements UserServiceI{

	@Resource
	private UserDAOI userDAO;
	
	@Resource
	private ActivityDAOI activityDAO;
	@Resource
	private MessageDAOI messageDAO;
	
	@Override
	public User login(User user) {
       return userDAO.findByEmaAndPsw(user.getEmail(), user.getPassword());
	}

	@Override
	public User register(User user) {
		 userDAO.save(user);
		 return user;
	}

	@Override
	public void revise(User user) {
	    userDAO.update(user);
	}

	@Override
	public User findByPho(String phone) {
		return userDAO.findByPho(phone);
	}

	@Override
	public void saveEdit(User user) {
		User oldUser = userDAO.findByEmail(user.getEmail());
		oldUser.update(user);
		userDAO.update(oldUser);
		ActionContext.getContext().getSession().put("user",oldUser);
	}

	@Override
	public JSONArray publishedActivity(User user, int begin, int pageSize) {
		Integer logCount = this.getPublishedCount(user.getUserId());
		List<Activity> list = userDAO.getPublishedAcList(user.getUserId(),begin,pageSize);
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(logCount);
		int pernum = 0;
		for(Activity a:list){
			pernum = activityDAO.getAcPerNum(a.getActivityId());
			JSONObject ac = new JSONObject();
			
			Set<Comment> commentSet=new HashSet<Comment>();
			commentSet =a.getCommentSet();
			int ispingjia =0;
			for(Comment comment:commentSet)
			{
				if((comment.getCommenter().getUserId())==user.getUserId())
				{
					ispingjia =1;
				}
			}
			
			ac.put("activityId", a.getActivityId());
			ac.put("name", a.getName());
			ac.put("status", a.getStatus());
			ac.put("imgUrl", a.getImgUrl());
			ac.put("time", a.getTime());
			ac.put("perNum", pernum);
			ac.put("ispingjia", ispingjia);
			jsonArray.add(ac);
		}
		return jsonArray;
	}
	
	
	@Override
	public String sendVerficationCode(String email) {
		//向邮箱发送验证码，如果成功，则返回验证码，失败返回"error"
		
		String sender="380119841@qq.com";//发件人
		String pass="bghvcjrrllccbjaj";//登陆邮箱用的注册码
		String code=this.createCode();// 验证码
		Properties props=new  Properties();
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.transport.protocol", "smtp");
		//使用SSL
		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.port", "465");
		
		Session session=Session.getInstance(props);
		session.setDebug(true);
		
		Message msg=new MimeMessage(session);//发送的信息
		
		try {
			msg.setSubject("大学生信用培养平台注册验证码");// 设置邮件主题   
			msg.setSentDate(new Date());// 设置邮件发送时期
			msg.setText("验证码为："+code);//邮件内容
			msg.setFrom(new InternetAddress(sender));//发件人
			
			Transport transport=session.getTransport();
			transport.connect("smtp.qq.com",465,sender,pass);//登陆上邮箱 登陆密码用注册码
			transport.sendMessage(msg, new Address[]{new InternetAddress(email)});//发送邮件
			
			transport.close();
		} catch (Exception e) {
			return "error";
		}
		return code;
		
	}
	// 生成验证码
	public String createCode()
	{
		StringBuilder sb=new StringBuilder();
		for(int i=0; i<6; i++)
		{
			String s=String.valueOf((int)(Math.random()*10));
			sb.append(s);
		}
		return sb.toString();
	}

	@Override
	public User findByEmail(String email) {
		//查找是否有指定邮箱的用户
		return userDAO.findByEmail(email);
	}

	@Override
	public String enroll(int activityId,String publishername) {
		User sessionUser = (User) ActionContext.getContext().getSession().get("user");
		Activity activity = activityDAO.findById(activityId);
		List<Activity> list = userDAO.getJoinActivityList(sessionUser.getUserId());
		for(Activity a:list){
			if(a.getActivityId()==activityId)
				return "该活动您已经报名过啦~";
		}
		list.add(activity);
		sessionUser.setJoinActivityList(list);
		userDAO.update(sessionUser);
		String tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		userDAO.addEnroll(sessionUser.getName(), activity.getName(),tempDate,publishername);
		ActionContext.getContext().getSession().put("user",sessionUser);
		MessageT message = new MessageT();
		message.setContent(sessionUser.getName()+"报名了您发布的活动‘"+activity.getName()+"’");
		message.setAccepter(activity.getPublisher());
		message.setIsRead(0);
		message.setSender(sessionUser);
		message.setSendTime(new Date());
		messageDAO.save(message);
		//messageDAO
		
		return "报名成功！";
	}

	@Override
	public JSONArray getJoAcByPage(User user, int begin, int pageSize) {
		Integer logCount = this.getJoAcCount(user.getUserId());
		List<Activity> list = userDAO.getJoAcListByPage(user.getUserId(), begin, pageSize);
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(logCount);
		int pernum = 0;
		List<User> trueJoinerlist =new ArrayList<User>();
		List<User> unwillingOutSet =new ArrayList<User>();
		
		for(Activity a:list){
			pernum = activityDAO.getAcPerNum(a.getActivityId());
			trueJoinerlist = activityDAO.findTrueJoiner(a.getActivityId()); // 同意报名人员
			unwillingOutSet = activityDAO.findUnwillingOuter(a.getActivityId()); //拒绝报名人员
			
			String trueJoinerId ="";
			for(User trueJoiner:trueJoinerlist){
				trueJoinerId+=trueJoiner.getUserId()+",";
			}
			String unwillingOutID ="";
			for(User unwillingOut:unwillingOutSet){
				unwillingOutID+=unwillingOut.getUserId()+",";
			}
			
			Set<Comment> commentSet=new HashSet<Comment>();
			commentSet =a.getCommentSet();
			int ispingjia =0;
			for(Comment comment:commentSet)
			{
				if((comment.getCommenter().getUserId())==user.getUserId())
				{
					ispingjia =1;
				}
			}
			
			JSONObject ac = new JSONObject();
			ac.put("activityId", a.getActivityId());
			ac.put("name", a.getName());
			ac.put("status", a.getStatus());
			ac.put("imgUrl", a.getImgUrl());
			ac.put("time", a.getTime());
			ac.put("perNum", pernum);
			ac.put("trueJoinerId", trueJoinerId);
			ac.put("unwillingOutID", unwillingOutID);
			ac.put("ispingjia", ispingjia);
			jsonArray.add(ac);
		}
		return jsonArray;
	}

	@Override
	public int getJoAcCount(int userId) {
		return userDAO.getJoAcCount(userId);
	}

	@Override
	public int getPublishedCount(int userId) {
		return userDAO.getPublishedCount(userId);
	}

	//修改信用积分
	@Override
	public void changeScore(int userId, int score) {
		User user=userDAO.findById(userId);
		user.setScore(user.getScore()+score);
		System.out.println("service"+user.getScore());
		userDAO.update(user);
	}

	@Override
	public User findById(int userId) {
		return userDAO.findById(userId);
	}

}
