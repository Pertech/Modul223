package ch.gibm.bean;

import java.io.Serializable;
import java.security.Principal;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import ch.gibm.entity.Language;
import ch.gibm.entity.Person;
import ch.gibm.entity.User;
import ch.gibm.entity.User_Role;
import ch.gibm.facade.PersonFacade;
import ch.gibm.facade.UserFacade;
import ch.gibm.facade.User_RoleFacade;

@SessionScoped
@ManagedBean(name = "userBean")
public class UserBean extends AbstractBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private User user;
	private User selectedUser;
	private UserFacade userFacade;
	private User_RoleFacade user_roleFacade;
	
	private List<User> users;
	
	public boolean isAdmin() {
		return this.getUser() != null ? user.isAdmin() : false;
	}
	
	public boolean isDefaultUser() {
		return this.getUser() != null ? user.isUser() : false;
	}
	
	public boolean isDark() {
		return this.getUser() != null ? user.isDark() : false;
	}
	
	public boolean isNotDark() {
		return this.getUser() != null ? !user.isDark() : false;
	}

	public void updateUser() {
		try {
			getUserFacade().updateUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/pages/protected/index?faces-redirect=true";
	}
	
	public User getUser() {
		if (user == null) {
			Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
			
			if (principal != null) {
				UserFacade userFacade = new UserFacade();
				user = userFacade.findUser(principal.getName());
			}
		}
		return user;
	}
	
	public User getSelectedUser() {
		if (selectedUser == null) {
			Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
			
			if (principal != null) {
				UserFacade userFacade = new UserFacade();
				selectedUser = userFacade.findUser(principal.getName());
			}
		}
		return selectedUser;
	}

	public List<User> getAllUsers() {
		if (users == null) {
			loadUsers();
		}

		return users;
	}

	private void loadUsers() {
		users = getUserFacade().listAll();
	}

	public UserFacade getUserFacade() {
		if (userFacade == null) {
			userFacade = new UserFacade();
		}

		return userFacade;
	}

	public User_RoleFacade getUserRoleFacade() {
		if (user_roleFacade == null) {
			user_roleFacade = new User_RoleFacade();
		}

		return user_roleFacade;
	}

	public void createUser() {
		try {
			getUserFacade().createUser(selectedUser);
			User_Role ur = new User_Role();
			ur.setUser(selectedUser);
			ur.setRole_name("USER");
			getUserRoleFacade().createUserRole(ur);
			closeDialog();
			displayInfoMessageToUser("Created with success");
			loadUsers();
			resetUser();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("A problem occurred while saving. Try again later");
			e.printStackTrace();
		}
	}
	
	public void updateSelectedUser() {
		try {
			getUserFacade().updateUser(selectedUser);
			closeDialog();
			displayInfoMessageToUser("Updated with success");
			loadUsers();
			resetUser();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("A problem occurred while updating. Try again later");
			e.printStackTrace();
		}
	}
	
	public void deleteUser() {
		try {
			getUserFacade().deleteUser(selectedUser);
			closeDialog();
			displayInfoMessageToUser("Deleted with success");
			loadUsers();
			resetUser();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("A problem occurred while removing. Try again later");
			e.printStackTrace();
		}
	}

	public void resetUser() {
		selectedUser = new User();
	}
	
	public void setSelectedUser(User user) {
		this.selectedUser = user;
	}
}
