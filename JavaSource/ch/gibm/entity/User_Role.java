package ch.gibm.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "User_Role.findUserRoles", query = "select u from User_Role u left join fetch u.user_name where u.user_name = :userName")
public class User_Role implements Serializable{
	private static final long serialVersionUID = 1L;

	public static final String FIND_USER_ROLES = "User_Role.findUserRoles";
	
	@Id
	@ManyToOne
	@JoinColumn(name="user_name")
	private User user_name;
	@Id
	private String role_name;
	
	public User getUser() {
		return user_name;
	}
	public void setUser(User user_name) {
		this.user_name = user_name;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	
	
}
