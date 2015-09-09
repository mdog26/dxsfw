package com.dxsfw.bbs.controller;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.dxsfw.BaseTest;

public class BbsControllerTest extends BaseTest {
	public static String action = "/bbs";
	
	@Test
	public void add() throws Exception {
		 String requestBody = "{\"userid\":4,\"type\":\"2\",\"title\":\"授课1\",\"tag\":\"数学\",\"content\":\"高数补课\",\"teachtype\" : \"1对1面授\", \"teachtime\" : \"周六下午4点-6点\", \"people\" : 1}"; 
//		 String requestBody = "{\"userid\":5,\"type\":\"1\",\"title\":\"交流1\",\"tag\":\"吃\",\"content\":\"交流内容，学生学术交流\"}"; 
	        mockMvc.perform(MockMvcRequestBuilders.post(action + "/add")
	                    .contentType(MediaType.APPLICATION_JSON).content(requestBody)
	                    .accept(MediaType.APPLICATION_JSON))
	                    .andDo(MockMvcResultHandlers.print())
	        		.andReturn();
	}
	
	@Test
	public void delete() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(action + "/delete/1"))
    			.andDo(MockMvcResultHandlers.print())
    			.andReturn();
	}
	
	@Test
	public void update() throws Exception {
//		 String requestBody = "{\"bbsid\":5,\"userid\":4,\"createtime\":1441802644000,\"updatetime\":1441802644000,\"type\":\"2\",\"title\":\"授课1\",\"teachtype\":\"1对1面授\",\"content\":\"高数补课\",\"teachtime\":\"周六下午4点-6点\",\"people\":1,\"tag\":\"数学\"}"; 
		 String requestBody = "{\"bbsid\":4,\"userid\":5,\"createtime\":1441802263000,\"updatetime\":1441802263000,\"type\":\"1\",\"title\":\"交流1update\",\"content\":\"交流内容，学生学术交流\",\"tag\":\"学术 交流\"}"; 
	        mockMvc.perform(MockMvcRequestBuilders.post(action + "/update")
	                    .contentType(MediaType.APPLICATION_JSON).content(requestBody)
	                    .accept(MediaType.APPLICATION_JSON))
	                    .andDo(MockMvcResultHandlers.print())
	        		.andReturn();
	}

	@Test
	public void get() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(action + "/get/2"))
    			.andDo(MockMvcResultHandlers.print())
    			.andReturn();
	}
	
	@Test
	public void applyClass() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(action + "/applyClass")
				.param("bbsid", "5").param("publishuserid", "1").param("shengqinguserid", "7"))
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
	}
	
	@Test
	public void search() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(action + "/search")
				.param("keyword", "1").param("type", "2").param("pageNo", "1").param("pageSize", "2"))
//				.param("pageNo", "1"))
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
	}
	
	@Test
	public void myList() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(action + "/myList")
				.param("userid", "5").param("type", "2").param("pageNo", "1").param("pageSize", "2"))
//				.param("pageNo", "1"))
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
	}
	
	@Test
	public void myApplyList() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(action + "/myApplyList")
				.param("userid", "7").param("type", "1").param("pageNo", "3").param("pageSize", "3"))
//				.param("pageNo", "1"))
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
	}
	
	@Test
	public void applyUserList() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(action + "/applyUserList")
				.param("bbsid", "5").param("pageNo", "1").param("pageSize", "2")
				.param("includePublishUser", "true")
				)
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
	}

	@Test
	public void myReplyList() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(action + "/myReplyList")
				.param("userid", "1").param("pageNo", "3").param("pageSize", "3")
//				.param("type", "2")
				)
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
	}
}
