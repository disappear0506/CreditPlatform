package code.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

import code.domain.Activity;
import code.domain.PageBean;
import code.service.ActivityServiceI;
import code.service.SchoolServiceI;

@ParentPackage("CreditPlatform")
@Controller
@Scope("prototype")
public class AppointAction implements ModelDriven<Activity>{
	HttpServletRequest request = ServletActionContext.getRequest();
	@Resource
	private ActivityServiceI activityService;
	
	@Resource 
	private SchoolServiceI schoolService;
	
	private List<String> province;//用于存储页面展示的省份
	private List<String> schoolname;//用于存储页面展示的学校
	
	public List<String> getProvince() {
		return province;
	}
	public void setProvince(List<String> province) {
		this.province = province;
	}
	public List<String> getSchoolname() {
		return schoolname;
	}
	public void setSchoolname(List<String> schoolname) {
		this.schoolname = schoolname;
	}

	public String tempname;
	public int currPage;//获取从页面传递过来的当前页面数
	private Activity activity=new Activity();
	
	
	public int getCurrPage() {
		return currPage;
	}
	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	
	public String getTempname() {
		return tempname;
	}
	public void setTempname(String tempname) {
		this.tempname = tempname;
	}
	@Override
	public Activity getModel() {
		
		return activity;
	}
	
	@Action(value="appointPublish1",
			results={@Result(location="/WEB-INF/publish1.jsp")})
	public String appointPublish1(){
		try {
			tempname = new String(tempname.getBytes("iso-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}
	
	@Action(value="appointFindPageBean",
			results={@Result(location="/WEB-INF/appoint.jsp")})
	public String appointFindPageBean(){
		
		PageBean<Activity> pageBean=activityService.findByPage(currPage, 1);
		province=schoolService.getProvince();
		schoolname=schoolService.getSchoolByProvince("北京");//默认展示北京的大学
		//将pageBean存放到值栈中
		ActionContext.getContext().getValueStack().push(pageBean);
		return "success";
	}
	
	//通过条件分页加载活动信息
	private String referKey;//参照的条件
	private String referValue;//参照的值
	
	public String getReferKey() {
		return referKey;
	}
	public void setReferKey(String referKey) {
		this.referKey = referKey;
	}
	public String getReferValue() {
		return referValue;
	}
	public void setReferValue(String referValue) {
		this.referValue = referValue;
	}
	@Action(value="appointFindCondition")
	public void appointFindCondition(){
		System.out.println("referKey:"+referKey+" referValue:"+referValue);
		//条件
		Map<String,String> refers=(Map<String, String>) request.getSession().getAttribute("appointRefers");
		if(refers==null)
		{
			refers=new HashMap<String,String>();
		}
		if(referKey!=null&& referValue!=null)
		{
			refers.put(referKey, referValue);//将条件放入
		}
		PageBean<Activity> pageBean=activityService.findByCondition(currPage, 1, refers);
		
		
		//将pageBean存放到值栈中
		ActionContext.getContext().getValueStack().push(pageBean);
	}
	
	
	
}
