package code.service;

import code.domain.User;
import net.sf.json.JSONArray;

public interface UserServiceI {
	User login(User user);
	User register(User user);
	void revise(User user);
	User findByPho(String phone);
	void saveEdit(User user);
	JSONArray publishedActivity(User user,int begin,int pageSize);
	JSONArray getJoAcByPage(User user,int begin,int pageSize);
	int getJoAcCount(int userId);
	int getPublishedCount(int userId);
	String sendVerficationCode(String email);
	User findByEmail(String email);
	String enroll(int activityId,String publishername);
	void changeScore(int userId,int score);
	User findById(int userId);
}