package com.dxsfw.pub.service.impl;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dxsfw.pub.model.User;
import com.dxsfw.pub.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class) // 整合 
@ContextConfiguration(locations="classpath:applicationContext.xml") // 加载配置
public class UserServiceImpTest {
	@Autowired // 注入
	private UserService userService;

	@Test
	public void testReg() {
		fail("Not yet implemented");
	}

	@Test
	public void testLogin() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetUser() {
		int userid = 1;
		User user = userService.getUser(userid);
		if(user == null){
			fail("Not found user");
		}
	}

	@Test
	public void testUpdateUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testUploadUserPicture() {
		fail("Not yet implemented");
	}

}
