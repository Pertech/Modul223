package ch.gibm.facade;

import java.io.Serializable;
import java.util.List;

import ch.gibm.dao.EntityManagerHelper;
import ch.gibm.dao.UserDAO;
import ch.gibm.dao.User_RoleDAO;
import ch.gibm.entity.User;
import ch.gibm.entity.User_Role;

public class UserFacade implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private UserDAO userDAO = new UserDAO();

	public void createUser(User user) {
		EntityManagerHelper.beginTransaction();
		userDAO.save(user);
		EntityManagerHelper.commitAndCloseTransaction();
	}
	
	public void deleteUser(User user){
		EntityManagerHelper.beginTransaction();
		User persistedUser = userDAO.getUserByName(user.getUserName());
		userDAO.delete(persistedUser);
		EntityManagerHelper.commitAndCloseTransaction();
		
	}
	
	public void updateUser(User user) {
		EntityManagerHelper.beginTransaction();
		User persistedUser = userDAO.find(user.getUserName());
		persistedUser.setDark(user.isDark());
		EntityManagerHelper.commitAndCloseTransaction();
	}
	
	public List<User> listAll() {
		EntityManagerHelper.beginTransaction();
		List<User> result = userDAO.findAll();
		EntityManagerHelper.commitAndCloseTransaction();

		return result;
	}
	
	public User findUser(String userName) {
		EntityManagerHelper.beginTransaction();
		User user = userDAO.find(userName);
		EntityManagerHelper.commitAndCloseTransaction();
		return user;
	}
}