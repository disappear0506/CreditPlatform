package code.domain;

public class ActivityBO {
	private int activityId;
	private String name; //活动标题
	private String status; //活动状态--0 待审核 1 报名中 2 进行中 3结束
	private String imgUrl; //活动图片的url
	private int joinerCount;
	
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
	public int getJoinerCount() {
		return joinerCount;
	}
	public void setJoinerCount(int joinerCount) {
		this.joinerCount = joinerCount;
	}
	public void po2bo(Activity ac,int joinerCount){
		this.activityId = ac.getActivityId();
		this.imgUrl = ac.getImgUrl();
		this.joinerCount = joinerCount;
		this.name = ac.getName();
		this.status = ac.getStatus();
	}
}
