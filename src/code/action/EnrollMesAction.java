package code.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

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

import code.service.EnrollMesServiceI;
import net.sf.json.JSONArray;

@ParentPackage("CreditPlatform")
@Controller
@Scope("prototype")
public class EnrollMesAction {
	
	HttpServletRequest request = ServletActionContext.getRequest();
	
	private int begin;
	private long mesCount;

	public long getMesCount() {
		return mesCount;
	}

	public void setMesCount(long mesCount) {
		this.mesCount = mesCount;
	}

	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	@Resource
	private EnrollMesServiceI enrollMesService;
	
	@Action(value="readMes")
	public void readMes(){
		JSONArray jsonArray = enrollMesService.readMes(begin);
		HttpServletResponse response = ServletActionContext.getResponse();	
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
	}
	
	@Action(value="tojsp",results={ @Result (location="/WEB-INF/enrollMessage.jsp")})
	public String Tojsp(){
		Map application = ActionContext.getContext().getApplication();
		long tempEnrollCount = 0;
	    if(null!=application.get("enrollCount")){
	    	tempEnrollCount = (long)application.get("enrollCount");
	    	application.put("enrollCount",tempEnrollCount+mesCount);
	    }else{
	    	application.put("enrollCount",mesCount);
	    }
		System.out.println(mesCount+" "+tempEnrollCount+" ");
		return "success";
	}
	
	@Action(value="toPerson",results={ @Result (location="/WEB-INF/personal.jsp")})
	public String ToPerson(){
		return "success";
	}
}
