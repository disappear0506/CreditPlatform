package code.dao;

import java.util.List;

import code.domain.Enroll;

public interface EnrollMesDAOI {
	public List<Enroll> readEnroll(String publishername,int begin);
	public long enrollLogCount(String publishername);
}
