package code.service;

import java.util.List;

public interface SchoolServiceI {
	//查询所有的省份
	public List<String> getProvince();
	
	//查询省份查询所有学校
	public List<String> getSchoolByProvince(String province);
	
}
