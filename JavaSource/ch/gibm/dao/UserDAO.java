package ch.gibm.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.gibm.entity.Person;
import ch.gibm.entity.User;
import ch.gibm.entity.User_Role;

public class UserDAO extends GenericDAO<User> {

	private static final long serialVersionUID = 1L;

	public UserDAO() {
		super(User.class);
	}

	public void delete(User user) {
        	super.delete(user.getUserName(), User.class);
	}

	public User getUserByName(String userName) {
		return EntityManagerHelper.getEntityManager().getReference(User.class, userName);
	}

	public User find(String userName) {
		return EntityManagerHelper.getEntityManager().find(User.class, userName);
	}
}
