package com.dxsfw.pub.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dxsfw.pub.dao.UserDao;
import com.dxsfw.pub.model.User;
import com.dxsfw.pub.model.UserExample;
import com.dxsfw.pub.service.UserService;

@Repository("userService")
public class UserServiceImp implements UserService {
	private static Logger log = LoggerFactory.getLogger(UserServiceImp.class);
	
	@Autowired
    private UserDao userDao;
	
	@Override
	public User reg(String mobile, String password) {
		UserExample example = new UserExample();
		//建立条件
		example.createCriteria().andMobileEqualTo(mobile).andPasswordEqualTo(password);
		List<User> list = userDao.selectByExample(example);
		if (list.size() == 0) {
			User record = new User();
			record.setMobile(mobile);
			record.setPassword(password);
			int r = userDao.insertSelective(record);
			if (r > 0) {
				//插入成功
				list = userDao.selectByExample(example);
				return list.get(0);
			}
		} else {
			log.info("用户已经注册:" + mobile);
		}
		return null;
	}

	@Override
	public User login(String mobile, String password) {
		UserExample example = new UserExample();
		//建立条件
		example.createCriteria().andMobileEqualTo(mobile).andPasswordEqualTo(password);
		List<User> list = userDao.selectByExampleWithBLOBs(example);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public User getUser(int userid) {
		return userDao.selectByPrimaryKey(userid);
	}

	@Override
	public User updateUser(User user) {
		int userid = userDao.updateByPrimaryKeySelective(user);
		return this.getUser(userid);
	}

	@Override
	public User uploadUserPicture(int userid, byte[] picture) {
		User user = this.getUser(userid);
		user.setPicture(picture);
		userDao.updateByPrimaryKeyWithBLOBs(user);
		return user;
	}

}
