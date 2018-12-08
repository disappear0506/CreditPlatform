package code.dao;

import code.domain.Admin;



public interface AdminDAOI extends GenericDAOI<Admin>{

	Admin find(String name, String password);
	
}
