package com.dxsfw.jianzhi.controller;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.dxsfw.BaseTest;

public class JianzhiControllerTest extends BaseTest {
	public static String action = "/jianzhi";
	
	@Test
	public void add() throws Exception {
		 String requestBody = "{\"userid\":1,\"title\":\"兼职标题\",\"tag\":\"传单 java 服务员\",\"workplace\":\"武汉\"}"; 
	        mockMvc.perform(MockMvcRequestBuilders.post("/jianzhi/add")
	                    .contentType(MediaType.APPLICATION_JSON).content(requestBody)
	                    .accept(MediaType.APPLICATION_JSON))
	                    .andDo(MockMvcResultHandlers.print())
	        		.andReturn();
	}
	
	@Test
	public void delete() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(action + "/delete/2"))
    			.andDo(MockMvcResultHandlers.print())
    			.andReturn();
	}
	
	@Test
	public void update() throws Exception {
		 String requestBody = "{\"jianzhiid\":3,\"title\":\"兼职标题2\",\"tag\":\"传单 java 服务员 快递\",\"workplace\":\"北京\"}"; 
	        mockMvc.perform(MockMvcRequestBuilders.post("/jianzhi/update")
	                    .contentType(MediaType.APPLICATION_JSON).content(requestBody)
	                    .accept(MediaType.APPLICATION_JSON))
	                    .andDo(MockMvcResultHandlers.print())
	        		.andReturn();
	}

	@Test
	public void get() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(action + "/get/3"))
    			.andDo(MockMvcResultHandlers.print())
    			.andReturn();
	}
}
