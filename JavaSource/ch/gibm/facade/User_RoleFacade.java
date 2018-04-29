package ch.gibm.facade;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import ch.gibm.dao.CityDAO;
import ch.gibm.dao.EntityManagerHelper;
import ch.gibm.dao.LanguageDAO;
import ch.gibm.dao.PersonDAO;
import ch.gibm.dao.UserDAO;
import ch.gibm.dao.User_RoleDAO;
import ch.gibm.entity.City;
import ch.gibm.entity.Language;
import ch.gibm.entity.Person;
import ch.gibm.entity.User;
import ch.gibm.entity.User_Role;

public class User_RoleFacade implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private User_RoleDAO user_roleDAO = new User_RoleDAO();

	public void createUserRole(User_Role user_role) {
		EntityManagerHelper.beginTransaction();
		user_roleDAO.save(user_role);
		EntityManagerHelper.commitAndCloseTransaction();
	}
	
	public List<User_Role> findUserRoles(User userName) {
		EntityManagerHelper.beginTransaction();
		List<User_Role> user_roles = user_roleDAO.findUserRoles(userName);
		EntityManagerHelper.commitAndCloseTransaction();
		return user_roles;
	}
}