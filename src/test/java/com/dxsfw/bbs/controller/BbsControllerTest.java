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
		 String requestBody = "{\"userid\":5,\"title\":\"海鲜自助聚餐\",\"tag\":\"吃\",\"address\":\"自助餐厅\", \"cost\":\"120元\",\"bbstime\":\"一起定日子\"}"; 
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
		 String requestBody = "{\"bbsid\":2,\"title\":\"十一欧洲7日游\",\"tag\":\"旅游 十一 欧洲\"}"; 
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
	public void applyBbs() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(action + "/applyBbs")
				.param("bbsid", "5").param("publishuserid", "1").param("shengqinguserid", "7"))
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
	}
	
	@Test
	public void search() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(action + "/search")
				.param("keyword", "会").param("pageNo", "2").param("pageSize", "2"))
//				.param("pageNo", "1"))
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
	}
	
	@Test
	public void myList() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(action + "/myList")
				.param("userid", "7").param("pageNo", "2").param("pageSize", "1"))
//				.param("pageNo", "1"))
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
	}
	
	@Test
	public void myApplyList() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(action + "/myApplyList")
				.param("userid", "6").param("pageNo", "3").param("pageSize", "3"))
//				.param("pageNo", "1"))
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
	}
	
	@Test
	public void applyUserList() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(action + "/applyUserList")
				.param("bbsid", "5").param("pageNo", "1").param("pageSize", "2")
//				.param("includePublishUser", "false")
				)
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
	}
}
