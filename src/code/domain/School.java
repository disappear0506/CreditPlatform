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
@Table(name="school")
//学校
public class School {
	private int schoolId;//学校id
	private String name; //学校名
	private String province;//所在省份
	private Set<Activity> activitySet; //某学校开展的活动 1-n
	
	
	public School() {
		super();
		activitySet = new HashSet<Activity>();
		
	}
	
	@Id
	@GeneratedValue
	public int getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}

	@OneToMany(cascade=CascadeType.ALL,mappedBy="school")
	public Set<Activity> getActivitySet() {
		return activitySet;
	}

	public void setActivitySet(Set<Activity> activitySet) {
		this.activitySet = activitySet;
	}

	
	
}
