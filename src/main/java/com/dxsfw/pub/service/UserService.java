package com.dxsfw.pub.service;

import com.dxsfw.pub.model.User;

public interface UserService {
	public User login(String mobile, String password);
	
	public User reg(String mobile, String password);
	
	public User getUser(int userid);
	
	public User updateUser(User user);
	
	public User uploadUserPicture(int userid, byte[] picture);
}
