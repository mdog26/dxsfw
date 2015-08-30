package com.dxsfw.pub.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.dxsfw.common.base.BaseDao;
import com.dxsfw.pub.model.User;
import com.dxsfw.pub.model.UserExample;

@Repository("userDao")
public interface UserDao extends BaseDao<User, Integer> {

	List<User> selectByExampleWithBLOBs(UserExample example);

	int updateByExampleWithBLOBs(@Param("record") User record, @Param("example") UserExample example);

	int updateByPrimaryKeyWithBLOBs(User record);

}