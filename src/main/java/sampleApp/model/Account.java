package sampleApp.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name = "name", nullable = false, length = 20)
	private String name;

	@Column(name = "password", nullable = false, length = 60)
	private String password;

	@OneToMany(mappedBy = "user")
	private List<Post> posts;

	@ManyToMany(mappedBy = "fvusers")
	private List<Account> follower;

	@ManyToMany
	@JoinTable(name = "fvusers", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "fvuser_id"))
	private List<Account> fvusers;

	public List<Account> getFollower() {
		return follower;
	}

	public List<Account> getFvusers() {
		return fvusers;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


}
