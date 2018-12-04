package code.action;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import code.dao.ActivityDAOI;
import code.domain.Activity;
import code.domain.PageBean;
import code.domain.User;
import code.service.ActivityServiceI;
import code.service.UserServiceI;
import net.sf.json.JSONArray;

@ParentPackage("CreditPlatform")
@Controller
@Scope("prototype")
public class UserAction {
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	
	private String email; //注册时传过来的邮箱
	private int activityId;//我要报名时传过来的activityId
	private Activity activity;//将activity传回页面
	int acPerNum;//活动的报名人数
	public int getAcPerNum() {
		return acPerNum;
	}
	public void setAcPerNum(int acPerNum) {
		this.acPerNum = acPerNum;
	}
	public Activity getActivity() {
		return activity;
	}
	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	public int getActivityId() {
		return activityId;
	}
	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Resource
	private UserServiceI userService;
	@Resource
	private ActivityServiceI activityService;
	List<Activity> list;//首页展示的活动集合
	
	
	public List<Activity> getList() {
		return list;
	}

	public void setList(List<Activity> list) {
		this.list = list;
	}
	/*
	 * 个人中心获取“我参加的活动”
	 */
	@Action(value="getJoAcByPage")
	public void getJoAcByPage(){
		User user = null;
		if(null==email)
			user = (User)request.getSession().getAttribute("user");
		else
			user = userService.findByEmail(email);
	    JSONArray jsonArray = userService.getJoAcByPage(user, begin, pageSize);
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
	
	/*
	 * 个人中心获取“我发布的活动”
	 */
	@Action(value="getpublishedActivity")
	public void getpublishedActivity(){
		User curUser = null;
		if(null==email)
			curUser = (User)request.getSession().getAttribute("user");
		else
			curUser = userService.findByEmail(email);
	    JSONArray jsonArray = userService.publishedActivity(curUser,begin,pageSize);
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
	
	/*
	 * 获取活动数据
	 */
	@Action(value="getPassActivities",results={@Result(name="success",location="/WEB-INF/index.jsp")})
    public String getPassActivities(){
		list = activityService.getIndexActivity();
		request.getSession().setAttribute("countlist", activityService.groupBy());
		return "success";
	}
	/**
	 * 我要报名 
	 */
	@Action(value="enroll",results={@Result(name="success",location="/WEB-INF/acdetails.jsp")})
	public String enroll(){
		User publisher = activityService.findPublisherByAcId(activityId);
		User sessionUser = (User)request.getSession().getAttribute("user");
		activity = activityService.findById(activityId);
		activity.setPublisher(publisher);
		activity.setActivityType(activityService.findTypeByAcId(activityId));
		if(publisher.getUserId()== sessionUser.getUserId())
			enrollMes="活动发起者不需要报名";
		else{
			enrollMes = userService.enroll(activityId,publisher.getName());
		}
		acPerNum = activityService.getAcPerNum(activityId);
		return "success";
	}
	
	/**
	 * 登陆
	 */
	@Action(value = "login", results = {
			@Result(name = "success", location = "/WEB-INF/index.jsp"),
			@Result(name = "error", location = "/login.jsp") })
	public String login() {
		
		List<Activity> relist;
		if (userService.login(user) != null) {
			//2016.12.4增加登陆成功后的页面展示功能
			list=activityService.getIndexActivity();
			request.getSession().setAttribute("countlist", activityService.groupBy());
			return "success";
		} else {
			loginMes = "账号或密码错误";
			return "error";
		}
	}
	
	/**
	 * 注册账号
	 */
	@Action(value="sendCode")
	public void sendCode()
	{
		//3.13 注册：向指定的邮箱发送验证码
		String result=userService.sendVerficationCode(email);
		//对返回结果进行处理
		if(result.equals("error"))//发送邮件失败
		{
			registerMes = "发送邮件失败";
		}else
		{
			//将code存储到session中
			request.getSession().setAttribute("code", result);
		}
		
	}
	
	private String checkCode;//验证码
	
	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	@Action(value = "register", results = {
			@Result(name = "success", location = "/login.jsp"),
			@Result(name = "error", location = "/register.jsp") })

	public String register() {
		
		//判断验证码是否正确
		String sessionCode=(String) request.getSession().getAttribute("code");//获取session中的code
		if(sessionCode!=null && !sessionCode.equals(""))
		{
			//验证码正确
			if(sessionCode.equals(checkCode))
			{
				//判断邮箱是否被注册
				if (userService.findByEmail(user.getEmail()) == null) {
					this.user = userService.register(user);
					registerMes = "注册成功,请登录";
					return "success";
				} else {
					registerMes = "当前邮箱已注册";
					return "error";
				}
				
			}else
			{
				registerMes = "验证码错误";
				return "error";
			}
		}else
		{
			registerMes = "请发送验证码到邮箱进行验证";
			return "error";
		}
	}
	
	/**
	 * 修改个人信息
	 */
	@Action(value = "edit", results = { @Result(name = "success", location = "/WEB-INF/changeInfo.jsp") })
	public String edit() {
		return "success";
	}

	@Action(value = "saveEdit", results = { @Result(name = "success", location = "/WEB-INF/personal.jsp") })
	public String saveEdit() {
		if (file != null) {
			String imgpath = "upload/";
			String path = ServletActionContext.getServletContext().getRealPath(
					"/");
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
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					is.close();
					os.flush();
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			user.setImgUrl(dataUrl.get(0));
		}
		userService.saveEdit(user);
		return "success";
	}
	
	/**
	 * 找回密码
	 */
	
	@Action(value = "getCode")
	public void getImageCode() {
		//1.创建bufferedImage图像数据缓冲区 存储生成的图片 参数(图片宽度，图片高度，图片类型)
        BufferedImage bi=new BufferedImage(80,35,BufferedImage.TYPE_INT_RGB);
        
        //2.画图片
        Graphics g=bi.getGraphics();
        //绘制图片背景
        Color c=new Color(200,150,255);//设置颜色 三个值是RGB
        g.setColor(c);//设置颜色
        g.fillRect(0, 0,80, 35);//绘制一个矩形 从坐标（0，0）开始  宽，高  颜色是c
        
        //3.绘制验证码
        char[] ch="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();//设置验证码生成的字符集
        Random r=new Random();
        int len=ch.length;//获取字符集的长度
        int index;//存储随机取到的字符的下标
        StringBuffer sb=new StringBuffer();//存储生成的验证码
        
        for(int i=0; i<4; i++)
        {
            index=r.nextInt(len);//从[0,len)中随机取一个整形
            //将验证码画在图片上
            g.setColor(new Color(r.nextInt(88),r.nextInt(188),r.nextInt(255)));//随机设置字体的颜色
            g.drawString(ch[index]+"", (i*15)+7, 20);//将生成的字符画在图片上 (字符,x,y)
            sb.append(ch[index]);//存储验证码信息
        }
        request.getSession().setAttribute("piccode", sb.toString());//将验证码信息保存到session中
        
        //输出图片(图片对象，格式类型，输出的位置)
        try {
			ImageIO.write(bi,"JPG",response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}


	}
	private String findCode;//找回密码第一阶段输入的验证码
	private String reviseOneMes="";//找回密码第一阶段提示信息
	public String getReviseOneMes() {
		return reviseOneMes;
	}

	public void setReviseOneMes(String reviseOneMes) {
		this.reviseOneMes = reviseOneMes;
	}

	public String getFindCode() {
		return findCode;
	}

	public void setFindCode(String findCode) {
		this.findCode = findCode;
	}
	
	@Action(value = "reviseOne", results = { @Result(name = "success", location = "/WEB-INF/reviseTwo.jsp"),
											@Result(name="error",location="/reviseOne.jsp")})
	public String reviseOne()
	{
		String piccode=(String) request.getSession().getAttribute("piccode");//获取session中的验证码信息
		if(!piccode.equals("") && piccode!=null)
		{
			//1.验证验证码是否输入正确
			if(!piccode.toUpperCase().equals(findCode.toUpperCase()))
			{
				reviseOneMes="验证码输入错误，请重新输入";
				return "error";
			}else
			{
				//2.验证邮箱是否存在
				if(userService.findByEmail(user.getEmail())==null)
				{
					reviseOneMes="用户不存在，请重新输入";
					return "error";
				}
				//3.跳转至第二阶段页面
				request.getSession().setAttribute("findemail", user.getEmail());
				return "success";
			}
		}
		return "success";
		
	}
	private String reviseTwoMes="";//找回密码第二阶段提示信息
	
	public String getReviseTwoMes() {
		return reviseTwoMes;
	}

	public void setReviseTwoMes(String reviseTwoMes) {
		this.reviseTwoMes = reviseTwoMes;
	}

	@Action(value = "reviseTwo", results = { @Result(name = "success", location = "/WEB-INF/reviseThree.jsp"),
			@Result(name="error",location="/WEB-INF/reviseTwo.jsp")})
	public String reviseTwo()
	{
		//1.查看验证码是否正确
		String sessionCode=(String) request.getSession().getAttribute("code");//获取session中的code
		if(!sessionCode.equals("")&& sessionCode!=null)
		{
			if(sessionCode.equals(checkCode))
			{
				return "success";//验证码正确
			}else
			{
				reviseTwoMes="验证码错误，请重新输入";
				return "error";
			}
		}else
		{
			reviseTwoMes="验证码未发送";
		}
		
		return "error";
	}
	
	
	private String confirmPassword;//找回密码第三阶段输入的第二次密码
	private String reviseThreeMes="";//找回密码第三阶段的提示信息
	
	@Action(value = "reviseThree", results = { @Result(name = "success", location = "/login.jsp"),
			@Result(name="error",location="/WEB-INF/reviseThree.jsp")})
	public String reviseThree()
	{
		//1.验证两次输入的密码信息是否一致
		if(confirmPassword.equals(user.getPassword()))
		{
			//将新密码更新至数据库
			//1.根据邮箱获取到user
			String findEmail=(String)request.getSession().getAttribute("findemail");
			
			if(findEmail!=null && !findEmail.equals(""))
			{
				User updateUser=userService.findByEmail(findEmail);
				if(updateUser!=null)
				{
					updateUser.setPassword(user.getPassword());
					userService.revise(updateUser);
					reviseThreeMes="密码修改成功!请登录";
				    return "success";
				}
			}else
			{
				reviseThreeMes="出现错误";
				return "error";
			}
			
		}else
		{
			reviseThreeMes="前后两次密码不一致，请重新输入";
			return "error";
		}
		return "error";
		
	}
	private int score;
	private int userId;
	
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	//修改信用积分
	@Action(value = "changeScore")
	public void changeScore()
	{
		System.out.println("score:"+score+"  "+userId);
		userService.changeScore(userId, score);
	}
	
	//查看他人信息
	@Action(value="lookOtherInfo",results = { @Result(name = "success", location = "/WEB-INF/otherInfo.jsp")} )
	public 	String lookOtherInfo()
	{
		User user=userService.findByEmail(email);
		request.setAttribute("other", user);
		return "success";
	}
	
	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getReviseThreeMes() {
		return reviseThreeMes;
	}

	public void setReviseThreeMes(String reviseThreeMes) {
		this.reviseThreeMes = reviseThreeMes;
	}
	
	private String enrollMes = "";

	public String getEnrollMes() {
		return enrollMes;
	}

	public User user;

	public String loginMes = "";

	public String getLoginMes() {
		return loginMes;
	}

	public String registerMes = "";

	public String getRegisterMes() {
		return registerMes;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int age;

	public void setAge(int age) {
		this.age = age;
	}

	public int getAge() {
		return age;
	}

	private List<File> file;

	private List<String> fileFileName;

	private List<String> fileContentType;

	private List<String> dataUrl = new ArrayList<String>();

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

	public int begin;
	public int pageSize;
	public int logCount;

	public int getLogCount() {
		return logCount;
	}
	public void setLogCount(int logCount) {
		this.logCount = logCount;
	}
	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
