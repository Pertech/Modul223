package ch.gibm.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import ch.gibm.facade.UserFacade;
import ch.gibm.facade.User_RoleFacade;

@Entity
public class User implements Serializable{
	private static final long serialVersionUID = 1L;

	private static boolean admin = false;
	private static boolean user = false;
	
	@Id
	private String user_name;
	private String password;

	public User() {
	}
	
	public String getUserName() {
		return user_name;
	}

	public void setUserName(String name) {
		this.user_name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAdmin() {
		initRoles();
		return admin;
	}
	
	public boolean isUser() {
		initRoles();
		return user;
	}
	
	public void initRoles() {
		admin = false;
		user = false;
		User_RoleFacade uf = new User_RoleFacade();
		List<User_Role> user_roles = uf.findUserRoles(this);
		for(User_Role ur : user_roles) {
			if(ur.getRole_name().equalsIgnoreCase("ADMIN")) {
				admin = true;
			} else if(ur.getRole_name().equalsIgnoreCase("USER")) {
				user = true;
			}
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof User) {
			User user = (User) obj;
			return user.getUserName() == user_name;
		}

		return false;
	}
	
	@Override
	public String toString() {
		return user_name;
	}
}
