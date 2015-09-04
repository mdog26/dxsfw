package com.dxsfw.pub.service;

import com.dxsfw.common.page.Pagination;
import com.dxsfw.pub.model.User;
import com.dxsfw.pub.model.UserExample;

public interface UserService {
	public User login(String mobile, String password);
	
	public User reg(String mobile, String password);
	
	public User getUser(int userid);
	
	public User updateUser(User user);
	
	Pagination searchUserList(UserExample example, Pagination p);
}
