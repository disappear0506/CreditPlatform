package code.action;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;

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
public class LoadAction implements ModelDriven<Activity>{
	@Resource
	private ActivityServiceI activityService;
	public String tempname;
	public int currPage;//获取从页面传递过来的当前页面数
	private Activity activity=new Activity();
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

	
	
	public String getTempname() {
		return tempname;
	}
	public void setTempname(String tempname) {
		this.tempname = tempname;
	}
	public int getCurrPage() {
		return currPage;
	}
	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}
	
	@Override
	public Activity getModel() {
		
		return activity;
	}
	
	@Action(value = "loadPublish1", results = { @Result(location = "/WEB-INF/publish1.jsp") })
	public String appointPublish1() {
		try {
			tempname = new String(tempname.getBytes("iso-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}
	
	@Action(value="loadFindPageBean",
			results={@Result(location="/WEB-INF/load.jsp")})
	public String appointFindPageBean(){
		province=schoolService.getProvince();
		schoolname=schoolService.getSchoolByProvince("北京");//默认展示北京的大学
		PageBean<Activity> pageBean=activityService.findByPage(currPage, 5);
		//将pageBean存放到值栈中
		ActionContext.getContext().getValueStack().push(pageBean);
		return "success";
	}
}
