package code.dao;

import java.util.List;
import java.util.Set;

import code.domain.Activity;
import code.domain.User;

public interface UserDAOI extends GenericDAOI<User>{
	User findByPhoAndPsw(String phone,String password);
	User findByPho(String phone);
	User findByEmail(String email);
	User findByEmaAndPsw(String email,String password);
	List<Activity> getJoinActivityList(int id);
	List<Activity> getPublishedAcList(int id,int begin,int pageSize);
	List<Activity> getJoAcListByPage(int id,int begin,int pageSize);
	int getJoAcCount(int userId);
	int getPublishedCount(int userId);
	User findById(int userId);
	void addEnroll(String username,String activityname,String time,String publishername);
}
