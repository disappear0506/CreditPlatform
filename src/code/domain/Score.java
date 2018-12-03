package code.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="score")
//信用分数
public class Score {
	private int scoreId; //信用分的id
	private User trueJoiner; //获得者 外键 1-n
	private Activity activity; //获得的活动来源 1-n
	private Double score;//获得的分数
	private User pingjiaer;//评价者
	public Score() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="pingjiaer")
	public User getPingjiaer() {
		return pingjiaer;
	}

	public void setPingjiaer(User pingjiaer) {
		this.pingjiaer = pingjiaer;
	}

	@Id
	@GeneratedValue
	public int getScoreId() {
		return scoreId;
	}
	public void setScoreId(int scoreId) {
		this.scoreId = scoreId;
	}
	
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="trueJoiner")
	public User getTrueJoiner() {
		return trueJoiner;
	}
	public void setTrueJoiner(User trueJoiner) {
		this.trueJoiner = trueJoiner;
	}
	
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="activity")
	public Activity getActivity() {
		return activity;
	}
	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	
}
