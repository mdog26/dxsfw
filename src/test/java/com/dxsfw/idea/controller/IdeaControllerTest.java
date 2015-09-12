package com.dxsfw.idea.controller;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.dxsfw.BaseTest;

public class IdeaControllerTest extends BaseTest {
	public static String action = "/idea";
	
	@Test
	public void add() throws Exception {
//		 String requestBody = "{\"userid\":4,\"title\":\"创意标题4\",\"tag\":\"黄焖鸡 饮食4\",\"type\":\"4餐饮业\",\"title\":\"黄焖鸡4加盟\",\"jieshao\":\"介绍内容，所有人可显示\",\"content\":\"具体内容(加密的,只对支付人和自己显示)\",\"price\":12.5}"; 
		 String requestBody = "{\"zhengjiid\":3,\"userid\":7,\"title\":\"投标啦\",\"tag\":\"投标啦\",\"type\":\"4餐饮业\",\"title\":\"投标啦4加盟\",\"jieshao\":\"介绍内容，所有人可显示投标啦\",\"content\":\"投标啦具体内容(加密的,只对支付人和自己显示)\",\"price\":12.5}"; 
	        mockMvc.perform(MockMvcRequestBuilders.post("/idea/add")
	                    .contentType(MediaType.APPLICATION_JSON).content(requestBody)
	                    .accept(MediaType.APPLICATION_JSON))
	                    .andDo(MockMvcResultHandlers.print())
	        		.andReturn();
	}
	
	@Test
	public void update() throws Exception {
		String requestBody = "{\"ideaid\":1,\"userid\":5,\"title\":\"创意标题3\",\"tag\":\"汽车\",\"type\":\"汽车\",\"title\":\"二手车买卖创意\",\"jieshao\":\"车介绍内容，所有人可显示\",\"content\":\"车具体内容(加密的,只对支付人和自己显示)\",\"price\":1000.12}";
	        mockMvc.perform(MockMvcRequestBuilders.post("/idea/update")
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
	public void get() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(action + "/get/3"))
    			.andDo(MockMvcResultHandlers.print())
    			.andReturn();
	}
	
	@Test
	public void search() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(action + "/search")
				.param("keyword", "车 黄").param("pageNo", "3").param("pageSize", "3"))
//				.param("pageNo", "1"))
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
	}
	
	@Test
	public void myList() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(action + "/myList")
				.param("userid", "5").param("pageNo", "3").param("pageSize", "3"))
//				.param("pageNo", "1"))
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
	}
	
	@Test
	public void ideaList() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/zhengji/ideaList")
				.param("zhengjiid", "3").param("pageNo", "3").param("pageSize", "3"))
//				.param("pageNo", "1"))
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
	}
	
	@Test
	public void pay() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(action + "/pay/4")
				.param("userid", "6"))
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
	}
	
	@Test
	public void pingjia() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(action + "/pingjia/3")
				.param("pingjia", "好评，给五星").param("pingjiafenshu", "5"))
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
	}
	
}
