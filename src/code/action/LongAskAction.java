package code.action;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import code.domain.User;
import code.service.ActivityServiceI;
import code.service.UserServiceI;

/*
 *  长轮询是否有新的通过的活动
 */
@ParentPackage("CreditPlatform")
@Controller
@Scope("prototype")
public class LongAskAction{

	@Resource
	private ActivityServiceI activityService;
	
	@Resource
	private UserServiceI userService;

	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	
	@Action(value="askAppoint")
	public void askAppoint(){
		
//		System.out.println("进入约起来的长轮询");
		List<Long> countlist = (List<Long>)request.getSession().getAttribute("countlist");
		while(true){
			try {
				Thread.sleep(100000000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			List<Long> relist = (List<Long>)request.getSession().getAttribute("countlist");
			if(countlist.get(0).intValue()!=relist.get(0).intValue()){
//				System.out.println("约起来的长度变了");
				return ;
			}
//			System.out.println("约起来进入休眠");
		}
	}
	
	@Action(value="askGrowUp")
	public void askGroupUp(){
//		System.out.println("进入助成长的长轮询");
		List<Long> countlist = (List<Long>)request.getSession().getAttribute("countlist");
		while(true){
			try {
				Thread.sleep(100000000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			List<Long> relist = (List<Long>)request.getSession().getAttribute("countlist");
			if(countlist.get(2).intValue()!=relist.get(2).intValue()){
//				System.out.println("助成长的长度变了");
				return ;
			}
//			System.out.println("助成长进入休眠");
		}
	}
	
	@Action(value="askLeaveMarket")
	public void leaveMarket(){
//		System.out.println("进入闲置市场的长轮询");
		List<Long> countlist = (List<Long>)request.getSession().getAttribute("countlist");
		while(true){
			try {
				Thread.sleep(100000000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			List<Long> relist = (List<Long>)request.getSession().getAttribute("countlist");
			if(relist.get(3).intValue()!=countlist.get(3).intValue()){
//				System.out.println("闲置市场的长度变了");
				return;
			}
//			System.out.println("闲置市场进入休眠");
		}
	}
	
	@Action(value="askLendBook")
	public void lendBook(){
//		System.out.println("进入互借书的长轮询");
		List<Long> countlist = (List<Long>)request.getSession().getAttribute("countlist");
		while(true){
			try {
				Thread.sleep(100000000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			List<Long> relist = (List<Long>)request.getSession().getAttribute("countlist");
			if(relist.get(1).intValue()!=countlist.get(1).intValue()){
//				System.out.println("互借书的长度变了");
				return;
			}
//			System.out.println("互借书进入休眠");
		}
	}
	
	@Action(value="askLoad")
	public void load(){
//		System.out.println("进入小额借款的长轮询");
		List<Long> countlist = (List<Long>)request.getSession().getAttribute("countlist");
		while(true){
			try {
//				System.out.println("小额借款进入休眠");
				Thread.sleep(100000000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			List<Long> relist = (List<Long>)request.getSession().getAttribute("countlist");
			if(relist.get(4).intValue()!=countlist.get(4).intValue()){
//				System.out.println("小额借款的长度变了");
				return;
			}
		}
	}

	@Action(value = "askPassActivities")
	public void newActivity() {
		List<Long> countlist = (List<Long>)request.getSession().getAttribute("countlist");
		while (true) {
			try {
				Thread.sleep(100000000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
//			System.out.println("轮询");
			List<Long> relist = activityService.groupBy();
			if(relist.size()!=countlist.size()){
				return ;
			}
			for(int i=0;i<relist.size();i++){
				if(relist.get(i).intValue()!=countlist.get(i).intValue()){
					return ;
				}
			}
//			System.out.println("进入睡眠");
		}
	}
	
	@Action(value = "askEnroll")
	public void askEnroll() {
		long sessionEnrollCount = 0;
		long enrollCount = 0;
		User user = (User)request.getSession().getAttribute("user");
		Map application = ActionContext.getContext().getApplication();
		if(null!=application.get("enrollCount")){
			sessionEnrollCount = (long) application.get("enrollCount");
		}
		while (true) {
			try {
				Thread.sleep(100000000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			enrollCount = activityService.askEnrollCount(user.getName());
			System.out.println("轮询EnrollCount");
			System.out.println(sessionEnrollCount+" "+enrollCount);
			if(enrollCount!=sessionEnrollCount){
//				application.put("enrollCount",enrollCount);
				try {
					response.setCharacterEncoding("UTF-8");
					PrintWriter out = response.getWriter();
					out.print(enrollCount-sessionEnrollCount);
					out.flush();
					out.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				return ;
			}
			System.out.println("进入睡眠EnrollCount");
		}
	}
}
