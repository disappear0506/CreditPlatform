package code.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

import code.dao.ActivityDAOI;
import code.domain.Activity;
import code.domain.PageBean;
import code.domain.User;
import code.service.ActivityServiceI;
import code.service.SchoolServiceI;
import code.service.UserServiceI;
import code.service.impl.UserServiceImpl;

@ParentPackage("CreditPlatform")
@Controller
@Scope("prototype")
public class IndexAction  implements ModelDriven<Activity> {
	@Resource
	private UserServiceI userService;
	
	@Resource 
    private ActivityServiceI activityService;
	
	@Resource 
	private SchoolServiceI schoolService;
	
	private List<String> province;//用于存储页面展示的省份
	private List<String> schoolname;//用于存储页面展示的学校
	List<Activity> list;//首页展示的活动集合

	public List<Activity> getList() {
		return list;
	}

	public void setList(List<Activity> list) {
		this.list = list;
	}

	public List<String> getSchoolname() {
		return schoolname;
	}

	public void setSchoolname(List<String> schoolname) {
		this.schoolname = schoolname;
	}

	public List<String> getProvince() {
		return province;
	}

	public void setProvince(List<String> province) {
		this.province = province;
	}

	@Action(value="appoint",
			results={@Result(location="/WEB-INF/appoint.jsp"),})
	public String appoint(){
		
		PageBean<Activity> pageBean=activityService.findByPage(1, 1);
		province=schoolService.getProvince();
		schoolname=schoolService.getSchoolByProvince("北京");//默认展示北京的大学
		//将pageBean存放到值栈中
		ActionContext.getContext().getValueStack().push(pageBean);
		return "success";
	}
	
	@Action(value="lendBook",
			results={@Result(location="/WEB-INF/lendBook.jsp"),})
	public String lendBook(){
		PageBean<Activity> pageBean=activityService.findByPage(1, 2);
		province=schoolService.getProvince();
		schoolname=schoolService.getSchoolByProvince("北京");//默认展示北京的大学
		ActionContext.getContext().getValueStack().push(pageBean);
		return "success";
	}
	
	@Action(value="myIndex",
			results={@Result(location="/WEB-INF/index.jsp"),})
	public String index(){
		list=activityService.getIndexActivity();
		return "success";
	}
	
	@Action(value="growUp",
			results={@Result(location="/WEB-INF/growUp.jsp"),})
	public String growUp(){
		PageBean<Activity> pageBean=activityService.findByPage(1, 3);
		province=schoolService.getProvince();
		schoolname=schoolService.getSchoolByProvince("北京");//默认展示北京的大学
		ActionContext.getContext().getValueStack().push(pageBean);
		return "success";
	}
	
	@Action(value="leaveMarket",
			results={@Result(location="/WEB-INF/leaveMarket.jsp"),})
	public String leaveMarket(){
		PageBean<Activity> pageBean=activityService.findByPage(1, 4);
		province=schoolService.getProvince();
		schoolname=schoolService.getSchoolByProvince("北京");//默认展示北京的大学
		ActionContext.getContext().getValueStack().push(pageBean);
		return "success";
	}
	
	@Action(value="load",
			results={@Result(location="/WEB-INF/load.jsp"),})
	public String load(){
		PageBean<Activity> pageBean=activityService.findByPage(1, 5);
		province=schoolService.getProvince();
		schoolname=schoolService.getSchoolByProvince("北京");//默认展示北京的大学
		ActionContext.getContext().getValueStack().push(pageBean);
		return "success";
	}
	
	@Action(value="personal",
			results={@Result(location="/WEB-INF/personal.jsp"),})
	public String personal(){
		return "success";
	}
	
	
	
	@Override
	public Activity getModel() {
		// TODO Auto-generated method stub
		return null;
	}

}
