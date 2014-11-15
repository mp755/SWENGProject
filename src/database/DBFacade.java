package database;

import java.util.ArrayList;
import java.util.List;

import client.File;
import client.Permission;
import client.User;

/* Database Facade class
 * 		This class wraps all functionality in the database package in one class.
 * 		Any class outside of the database package should associate only with this class.
 * 		This class is a singleton.
 * 
 * Methods:
 * 		This class contains many of the same methods as other classes in this package. Check those respective classes for info on those methods.
 * 		
 * 		+ boolean registerUser(User user)					: Will register a user and log them on.
 * 		+ List<File> getCollaborations(String userName)		: Will get all files that a user can access from other users. (Files in which the user is granted read/write access). 
 */

public class DBFacade {
	private FileDAO fid;
	private FriendDAO frd;
	private PermissionDAO pd;
	private UserDAO ud;
	
	private static DBFacade instance = null;
	
	private DBFacade() {
		fid = new FileDAO();
		frd = new FriendDAO();
		pd = new PermissionDAO();
		ud = new UserDAO();
	}
	
	public static DBFacade getInstance() {
		if(instance == null) {
			instance = new DBFacade();
		}
		
		return instance;
	}
	
	/* USER DAO */
	public boolean registerUser(User user) {
		boolean isRegistered = ud.create(user);
		
		if(isRegistered) {
			ud.authenticate(user);
		}
		
		return isRegistered;
	}
	
	public boolean authenticate(User user) {
		boolean authenticated = ud.authenticate(user);
		
		return authenticated;
	}
	
	public boolean deauthenticate(User user) {
		boolean deauthenticated = ud.deauthenticate(user);
		
		return deauthenticated;
	}
	
	public boolean isOnline(String userName) {
		boolean isOnline = ud.isOnline(userName);
		
		return isOnline;
	}
	
	public User getUser(String userName) {
		User user = ud.getUser(userName);
		
		return user;
	}
	
	public List<User> getAllUsers() {
		List<User> userList = ud.getAllUsers();
		
		return userList;
	}
	
	public boolean changePassword(User user) {
		boolean passChanged = ud.update(user);
		
		return passChanged;
	}
	
	public boolean removeUser(User user) {
		boolean isRemoved = ud.delete(user.getUserName());
		
		return isRemoved;
	}
	
	/* FRIEND DAO */
	public List<User> getFriends(User user) {
		List<User> friendList = frd.getAllFriends(user);
		
		return friendList;
	}
	
	public boolean addFriend(User user, User friend) {
		boolean areFriends = frd.create(user, friend);
		
		return areFriends;
	}
	
	public boolean deleteFriend(User user, User friend) {
		boolean deleted = frd.delete(user, friend);
		
		return deleted;
	}
	
	public boolean checkFriends(User user, User friend) {
		boolean areFriends = frd.isFriendsWith(user, friend);
		
		return areFriends;
	}
	
	/* FILE DAO */
	public boolean addFile(File file) {
		boolean created = fid.create(file);
		
		return created;
	}
	
	public boolean deleteFile(File file) {
		boolean deleted = fid.delete(file.getOwner(), file.getFileName());
		
		return deleted;
	}
	
	public List<File> getFiles(String userName) {
		List<File> fileList = fid.getFileList(userName);
		
		return fileList;
	}
	
	public File getFile(int fileID) {
		File file = fid.getFile(fileID);
		
		return file;
	}
	
	public File getFile(String userName, String fileName) {
		File file = fid.getFile(userName, fileName);
		
		return file;
	}
	
	public boolean updateTimestamp(String userName, String fileName) {
		boolean isUpdated = fid.updateTimestamp(userName, fileName);
		
		return isUpdated;
	}
	
	public boolean updateFileName(String userName, String oldName, String newName) {
		boolean isChanged = fid.updateFileName(userName, oldName, newName);
		
		return isChanged;
	}
	
	/* PERMISSION DAO */
	public boolean addPermission(Permission permission) {
		boolean created = pd.create(permission);
		
		return created;
	}
	
	public boolean removePermission(Permission permission) {
		boolean removed = pd.delete(permission);
		
		return removed;
	}
	
	public boolean canAccess(String userName, File file) {
		boolean access = pd.canAccess(userName, file.getFileID());
		
		return access;		
	}
	
	public boolean canAccess(String userName, String fileName) {
		File tempFile = fid.getFile(userName, fileName);
		
		boolean access = pd.canAccess(userName, tempFile.getFileID());
		return access;
	}
	
	public boolean canWrite(String userName, File file) {
		boolean write = pd.canWrite(userName, file.getFileID());
		
		return write;
	}
	
	public boolean canWrite(String userName, String fileName) {
		File tempFile = fid.getFile(userName, fileName);
		
		boolean write = pd.canWrite(userName, tempFile.getFileID());
		return write;
	}
	
	public boolean updateAccess(Permission permission) {
		boolean updated = pd.updateAccess(permission);
		
		return updated;
	}
	
	public List<Permission> getAccessList(String userName) {
		List<Permission> accessList = pd.getAccessList(userName);
		
		return accessList;
	}
	
	public List<File> getCollaborations(String userName) {
		List<Permission> permList = getAccessList(userName);
		List<File> fileList = new ArrayList<File>();
		
		for(Permission p : permList) {
			File f = fid.getFile(p.getFileID());
			fileList.add(f);
		}
		
		return fileList;
	}
	
}