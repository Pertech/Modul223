package ch.gibm.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.gibm.entity.Person;
import ch.gibm.entity.User;
import ch.gibm.entity.User_Role;

public class User_RoleDAO extends GenericDAO<User_Role> {

	private static final long serialVersionUID = 1L;

	public User_RoleDAO() {
		super(User_Role.class);
	}

	public List<User_Role> findUserRoles(User userName) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("userName", userName);

		return super.findResults(User_Role.FIND_USER_ROLES, parameters);
	}
}
