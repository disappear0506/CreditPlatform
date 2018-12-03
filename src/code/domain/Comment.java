package code.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="comment")
//活动评价
public class Comment {
	private int commentId; //评价id
	private String content; //评价内容
	private Date time;//评价时间
	private Activity activity; //评价的活动  1-n 外键
	private User commenter; //评价的用户 1-n 外键
	private Comment reply; //评价的回复 1-1 
	
	
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Id
	@GeneratedValue
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}

	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="activity")
	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="commenter")
	public User getCommenter() {
		return commenter;
	}

	public void setCommenter(User commenter) {
		this.commenter = commenter;
	}

	@OneToOne(cascade={CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="reply")
	public Comment getReply() {
		return reply;
	}

	public void setReply(Comment reply) {
		this.reply = reply;
	}
}
