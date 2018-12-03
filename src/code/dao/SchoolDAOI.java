package code.dao;

import java.util.List;

public interface SchoolDAOI {
	//查询所有省份
	public List<String> getProvince();
	//查询省份查询所有学校
	public List<String> getSchoolByProvince(String province);
}
