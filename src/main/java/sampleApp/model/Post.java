package sampleApp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="posts")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	//@Column(name = "user_id", nullable = false)
	//private int userId;


	@ManyToOne
	private Account user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Account getUser() {
		return user;
	}

	public void setUser(Account user) {
		this.user = user;
	}

	@Column(name = "text", nullable = false, length = 50)
	private String text;

	//public int getUserId() {
		//return getUserId();
	//}

	//public void setUserId(int userId) {
		//this.userId = userId;
	//}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
