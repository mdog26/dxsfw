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
		 String requestBody = "{\"userid\":5,\"title\":\"兼职标题4\",\"tag\":\"cto ceo\",\"workplace\":\"anywhere\"}"; 
	        mockMvc.perform(MockMvcRequestBuilders.post("/jianzhi/add")
	                    .contentType(MediaType.APPLICATION_JSON).content(requestBody)
	                    .accept(MediaType.APPLICATION_JSON))
	                    .andDo(MockMvcResultHandlers.print())
	        		.andReturn();
	}
	
	@Test
	public void delete() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(action + "/delete/3"))
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
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(action + "/get/4"))
    			.andDo(MockMvcResultHandlers.print())
    			.andReturn();
	}
	
	@Test
	public void applyJianzhi() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(action + "/applyJianzhi")
				.param("jianzhiid", "4").param("publishuserid", "4").param("shengqinguserid", "1").param("jianliid", "5"))
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
	}
	
	@Test
	public void search() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(action + "/search")
				.param("keyword", "java 武汉").param("pageNo", "3").param("pageSize", "3"))
//				.param("pageNo", "1"))
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
	}
	
	@Test
	public void myList() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(action + "/myList")
				.param("userid", "1").param("pageNo", "3").param("pageSize", "3"))
//				.param("pageNo", "1"))
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
	}
	
	@Test
	public void myApplyList() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(action + "/myApplyList")
				.param("userid", "2").param("pageNo", "3").param("pageSize", "3"))
//				.param("pageNo", "1"))
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
	}
	
	@Test
	public void applyJianLiList() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(action + "/applyJianLiList")
				.param("jianzhiid", "4").param("pageNo", "3").param("pageSize", "3"))
//				.param("pageNo", "1"))
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
	}
}
