package code.dao.impl;

import org.springframework.stereotype.Repository;

import code.dao.ScoreDAOI;
import code.domain.Score;

@Repository("scoreDAO")
public class ScoreDAOImpl extends GenericDAOImpl<Score> implements ScoreDAOI{
	@Override
	public void save(Score obj) {
		this.getHibernateTemplate().save(obj);
	}
}
