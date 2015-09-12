package com.dxsfw.idea.controller;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.dxsfw.BaseTest;

public class ZhengjiControllerTest extends BaseTest {
	public static String action = "/zhengji";
	
	@Test
	public void add() throws Exception {
		 String requestBody = "{\"userid\":6,\"title\":\"征集标题4\",\"tag\":\"黄焖鸡 饮食\",\"content\":\"征集创意的具体内容\"}"; 
	        mockMvc.perform(MockMvcRequestBuilders.post("/zhengji/add")
	                    .contentType(MediaType.APPLICATION_JSON).content(requestBody)
	                    .accept(MediaType.APPLICATION_JSON))
	                    .andDo(MockMvcResultHandlers.print())
	        		.andReturn();
	}
	
	@Test
	public void update() throws Exception {
		String requestBody = "{\"zhengjiid\":2,\"userid\":7,\"title\":\"征集标题1\",\"tag\":\"1黄焖鸡 饮食\",\"content\":\"1征集创意的具体内容\"}";
	        mockMvc.perform(MockMvcRequestBuilders.post("/zhengji/update")
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
	public void get() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(action + "/get/3"))
    			.andDo(MockMvcResultHandlers.print())
    			.andReturn();
	}
	
	@Test
	public void search() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(action + "/search")
				.param("keyword", "").param("pageNo", "3").param("pageSize", "3"))
//				.param("pageNo", "1"))
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
	}
	
	@Test
	public void myList() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(action + "/myList")
				.param("userid", "6").param("pageNo", "3").param("pageSize", "3"))
//				.param("pageNo", "1"))
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
	}
	
}
