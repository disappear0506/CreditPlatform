package code.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="activity")
//活动实体
public class Activity {
	private int activityId; //活动id
	private String name; //活动标题
	private String status; //活动状态--0 待审核 1 报名中 2 进行中 3结束
	private String imgUrl; //活动图片的url
	private String introduce; //活动介绍
	private String place; //活动地点
	private String time; //活动时间
	private User publisher; //活动发布者 --外键 1-n
	private School school; //活动学校  外键 1-n
	private ActivityType activityType; //活动类型 外键1-n
	private List<User> joinerList;
	private Set<Score> scoreSet; //活动获得的信用分
	private List<User> unwillingOutList; //被动删除的报名者  m-n
	private Set<User> willingOutSet; //主动删除的报名者 m-n
	private Set<Comment> commentSet; //
	private List<User> trueJoinerList;
	
	public Activity() {
		super();
		joinerList = new ArrayList<User>();
		scoreSet = new HashSet<Score>();
		willingOutSet = new HashSet<User>();
        unwillingOutList = new ArrayList<User>();
        commentSet = new HashSet<Comment>();
        trueJoinerList = new ArrayList<User>();
	}
	@Id
	@GeneratedValue
	public int getActivityId() {
		return activityId;
	}
	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.REFRESH},fetch=FetchType.EAGER)
	@JoinColumn(name="publisher")
	public User getPublisher() {
		return publisher;
	}
	public void setPublisher(User publisher) {
		this.publisher = publisher;
	}
	
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.REFRESH},fetch=FetchType.LAZY)
	@JoinColumn(name="school")
	public School getSchool() {
		return school;
	}
	public void setSchool(School school) {
		this.school = school;
	}
	
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.REFRESH},fetch=FetchType.LAZY)
	@JoinColumn(name="activityType")
	public ActivityType getActivityType() {
		return activityType;
	}
	public void setActivityType(ActivityType activityType) {
		this.activityType = activityType;
	}
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "joinActivityList")
	public List<User> getJoinerList() {
		return joinerList;
	}
	public void setJoinerList(List<User> joinerList) {
		this.joinerList = joinerList;
	}
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="activity",fetch=FetchType.LAZY)
	public Set<Score> getScoreSet() {
		return scoreSet;
	}
	
	public void setScoreSet(Set<Score> scoreSet) {
		this.scoreSet = scoreSet;
	}
	
	@ManyToMany(cascade={CascadeType.PERSIST,CascadeType.PERSIST},fetch=FetchType.LAZY)
	@JoinTable(name="willingOuter_activities",
		joinColumns={@JoinColumn(name="activity")},
		inverseJoinColumns={@JoinColumn(name="willingOuter")})
	public Set<User> getWillingOutSet() {
		return willingOutSet;
	}

	public void setWillingOutSet(Set<User> willingOutSet) {
		this.willingOutSet = willingOutSet;
	}

	@OneToMany(cascade=CascadeType.ALL,mappedBy="activity",fetch=FetchType.EAGER)
	public Set<Comment> getCommentSet() {
		return commentSet;
	}
	
	@ManyToMany(cascade={CascadeType.PERSIST,CascadeType.PERSIST},fetch=FetchType.LAZY)
	@JoinTable(name="unwillingOuter_activities",
		joinColumns={@JoinColumn(name="activity")},
		inverseJoinColumns={@JoinColumn(name="unwillingOuter")})
	public List<User> getUnwillingOutList() {
		return unwillingOutList;
	}
	public void setUnwillingOutList(List<User> unwillingOutList) {
		this.unwillingOutList = unwillingOutList;
	}
	public void setCommentSet(Set<Comment> commentSet) {
		this.commentSet = commentSet;
	}
	
	@ManyToMany(fetch=FetchType.EAGER,cascade = { CascadeType.REFRESH, CascadeType.MERGE})
	@JoinTable(name = "trueJoiner_activities", joinColumns = { @JoinColumn(name = "activity") }, inverseJoinColumns = {@JoinColumn(name = "trueJoiner") })
	public List<User> getTrueJoinerList() {
		return trueJoinerList;
	}
	public void setTrueJoinerList(List<User> trueJoinerList) {
		this.trueJoinerList = trueJoinerList;
	}
}
