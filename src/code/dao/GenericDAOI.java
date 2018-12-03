package code.dao;

import java.util.List;

public interface GenericDAOI<T> {
	List<T> findByPage(int begin, int pageSize);
	
	List<Object> findByPage(String hql,int begin, int pageSize);

	int findCount(String tableName);

	void save(T obj);

	T findById(Integer id);

	void update(T obj);

	void delete(T obj);
}
