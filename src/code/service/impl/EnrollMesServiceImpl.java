package code.service.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.ActionContext;

import code.dao.EnrollMesDAOI;
import code.domain.Enroll;
import code.domain.User;
import code.service.EnrollMesServiceI;
import net.sf.json.JSONArray;

@Transactional
@Repository("enrollMesService")
public class EnrollMesServiceImpl implements EnrollMesServiceI{

	@Resource
	private EnrollMesDAOI enrollMesDAO;
	
	@Override
	public JSONArray readMes(int begin) {
		Map session = ActionContext.getContext().getSession();
		
		User sessionUser = (User) session.get("user");
		List<Enroll> templist = enrollMesDAO.readEnroll(sessionUser.getName(), begin);
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(enrollMesDAO.enrollLogCount(sessionUser.getName()));
		for(Enroll enroll:templist){
			String str = enroll.getUsername()+"报名了您发布的活动‘"+enroll.getActivityname()+"’,报名日期为："+enroll.getTime();
			jsonArray.add(str);
		}
		return jsonArray;
	}

}
