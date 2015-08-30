package com.dxsfw.pub.dao;

import org.springframework.stereotype.Repository;

import com.dxsfw.common.base.BaseDao;
import com.dxsfw.pub.model.User;

@Repository("userDao")
public interface UserDao extends BaseDao<User, Integer> {

}