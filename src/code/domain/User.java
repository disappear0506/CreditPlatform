package code.domain;

import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "user")
// 用户
public class User {
	private int userId; // 用户id
	private String name; // 用户名
	private String phone; // 电话--登陆账号
	private String password;// 密码
	private String department;// 院系
	private String grade;// 年级
	private String sex;// 性别
	private int burn;// 出生年月
	private String imgUrl;// 头像url
	private int score;// 信用分数
	private String school;// 学校名
	private String email;
	private Set<Activity> publishActivitySet; // 发布的活动 1->n
	private List<Activity> joinActivityList;// 报名的活动 n-m
	private Set<Score> scoreSet; // 所获得信用分数的集合
	private Set<Comment> commentSet;// 发表评价的集合
	private Set<Message> sendMessageSet; // 发送的消息 1--n
	private Set<Message> acceptMessageSet; // 收到的消息 1-n
	private Set<Activity> willingOutActivitySet; // 自动删除的活动 n-m
	private Set<Activity> unwillingOutActivitySet; // 被动删除的活动 n-m
	private List<Activity> trueJoinAcList;//报名且获得同意的活动

	public User() {
		super();
		publishActivitySet = new HashSet<Activity>();
		scoreSet = new HashSet<Score>();
		commentSet = new HashSet<Comment>();
		joinActivityList = new ArrayList<Activity>();
		sendMessageSet = new HashSet<Message>();
		acceptMessageSet = new HashSet<Message>();
		willingOutActivitySet = new HashSet<Activity>();
		unwillingOutActivitySet = new HashSet<Activity>();
		trueJoinAcList = new ArrayList();
	}

	@Id
	@GeneratedValue
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getBurn() {
		return burn;
	}

	public void setBurn(int burn) {
		this.burn = burn;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	@OneToMany(cascade = {CascadeType.MERGE,CascadeType.REFRESH}, mappedBy = "publisher",fetch=FetchType.LAZY)
	public Set<Activity> getPublishActivitySet() {
		return publishActivitySet;
	}

	public void setPublishActivitySet(Set<Activity> publishActivitySet) {
		this.publishActivitySet = publishActivitySet;
	}

	@ManyToMany(cascade = { CascadeType.REFRESH, CascadeType.MERGE}, fetch = FetchType.LAZY)
	@JoinTable(name = "joiner_activities", joinColumns = { @JoinColumn(name = "joiner") }, inverseJoinColumns = {@JoinColumn(name = "activity") })
	public List<Activity> getJoinActivityList() {
		return joinActivityList;
	}

	public void setJoinActivityList(List<Activity> joinActivityList) {
		this.joinActivityList = joinActivityList;
	}

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.PERSIST },fetch=FetchType.LAZY)
	@JoinTable(name = "willingOuter_activities", joinColumns = {
			@JoinColumn(name = "willingOuter") }, inverseJoinColumns = { @JoinColumn(name = "activity") })
	public Set<Activity> getWillingOutActivitySet() {
		return willingOutActivitySet;
	}

	public void setWillingOutActivitySet(Set<Activity> willingOutActivitySet) {
		this.willingOutActivitySet = willingOutActivitySet;
	}

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.PERSIST },fetch=FetchType.LAZY)
	@JoinTable(name = "unwillingOuter_activities", joinColumns = {
			@JoinColumn(name = "unwillingOuter") }, inverseJoinColumns = { @JoinColumn(name = "activity") })
	public Set<Activity> getUnwillingOutActivitySet() {
		return unwillingOutActivitySet;
	}

	public void setUnwillingOutActivitySet(Set<Activity> unwillingOutActivitySet) {
		this.unwillingOutActivitySet = unwillingOutActivitySet;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "trueJoiner",fetch=FetchType.LAZY)
	public Set<Score> getScoreSet() {
		return scoreSet;
	}

	public void setScoreSet(Set<Score> scoreSet) {
		this.scoreSet = scoreSet;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "commenter",fetch=FetchType.LAZY)
	public Set<Comment> getCommentSet() {
		return commentSet;
	}

	public void setCommentSet(Set<Comment> commentSet) {
		this.commentSet = commentSet;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "sender",fetch=FetchType.LAZY)
	public Set<Message> getSendMessageSet() {
		return sendMessageSet;
	}

	public void setSendMessageSet(Set<Message> sendMessageSet) {
		this.sendMessageSet = sendMessageSet;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "accepter",fetch=FetchType.LAZY)
	public Set<Message> getAcceptMessageSet() {
		return acceptMessageSet;
	}

	public void setAcceptMessageSet(Set<Message> acceptMessageSet) {
		this.acceptMessageSet = acceptMessageSet;
	}
	
	@ManyToMany(mappedBy = "trueJoinerList", fetch = FetchType.LAZY)
	public List<Activity> getTrueJoinAcList() {
		return trueJoinAcList;
	}

	public void setTrueJoinAcList(List<Activity> trueJoinAcList) {
		this.trueJoinAcList = trueJoinAcList;
	}

	public void update(User user) {
		this.burn = user.burn;
		this.grade = user.grade;
		if (user.imgUrl != null && user.imgUrl != "") {
			this.imgUrl = user.imgUrl;
		}
		this.name = user.name;
		this.phone = user.phone;
		this.school = user.school;
		this.department = user.department;
		this.score = user.score;
		this.sex = user.sex;
		this.email = user.email;
	}
	
	
}
