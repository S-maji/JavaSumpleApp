package sampleApp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fvusers")
public class FvUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private int id;

	@Column(name = "user_id", nullable = false)
	private int user_id;

	@Column(name = "fvuser_id", nullable = false)
	private int fvuser_id;

	public int getUserId() {
		return this.user_id;
	}

	public void setUserId(int id) {
		this.user_id = id;
	}

	public int getFvUserId() {
		return this.fvuser_id;
	}

	public void setFvUserId(int id) {
		this.fvuser_id = id;
	}
}
