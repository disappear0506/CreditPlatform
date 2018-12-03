package code.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="activityType")
//活动类型实体
public class ActivityType {
	private int activityTypeId; //活动类型id
	private String name; //活动名
	private Set<Activity> activitySet; //该类型活动的集合 1-n
	
	public ActivityType() {
		super();
		// TODO Auto-generated constructor stub
		activitySet = new HashSet<Activity>();
	}
	@Id
	@GeneratedValue
	public int getActivityTypeId() {
		return activityTypeId;
	}
	public void setActivityTypeId(int activityTypeId) {
		this.activityTypeId = activityTypeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="activityType")
	public Set<Activity> getActivitySet() {
		return activitySet;
	}
	public void setActivitySet(Set<Activity> activitySet) {
		this.activitySet = activitySet;
	}
	
}
