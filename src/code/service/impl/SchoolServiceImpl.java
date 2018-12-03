package code.service.impl;

import java.util.List;


import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import code.dao.SchoolDAOI;
import code.service.SchoolServiceI;
@Transactional
@Repository("schoolService")
public class SchoolServiceImpl implements SchoolServiceI {
	
	@Resource 
    private SchoolDAOI schoolDAO;
	
	@Override
	public List<String> getProvince() {
		List<String> list=schoolDAO.getProvince();
		return list;
	}

	@Override
	public List<String> getSchoolByProvince(String province) {
		List<String> list=schoolDAO.getSchoolByProvince(province);
		return list;
	}

}
