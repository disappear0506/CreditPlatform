package code.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@ParentPackage("CreditPlatform")
@Controller
@Scope("prototype")
public class TestAction {
	private String background;

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	} 
	
	@Action(value="test",results = { @Result(location = "/NewFile.jsp") })
	public String test(){
		return "success";
	}
}
