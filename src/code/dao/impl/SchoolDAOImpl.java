package code.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import code.dao.ActivityDAOI;
import code.dao.SchoolDAOI;
import code.domain.Activity;
import code.domain.School;
@Repository("schoolDAO")
public class SchoolDAOImpl extends GenericDAOImpl<School> implements SchoolDAOI{


	@Override
	public List<String> getProvince() {
		String hql="select school.province from School as school group by school.province";
		List<String> list=this.getHibernateTemplate().find(hql);
		return list;
	}

	@Override
	public List<String> getSchoolByProvince(String province) {
		String hql="select school.name from School as school where school.province=?";
		List<String> list=this.getHibernateTemplate().find(hql,province);
		
		return list;
	}

}
