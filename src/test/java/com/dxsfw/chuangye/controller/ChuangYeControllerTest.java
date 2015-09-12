package com.dxsfw.chuangye.controller;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.dxsfw.BaseTest;

public class ChuangYeControllerTest extends BaseTest {
	public static String action = "/chuangye";
	
	@Test
	public void add() throws Exception {
		 String requestBody = "{\"userid\":4,\"title\":\"创业标题4\",\"tag\":\"黄焖鸡 饮食4\",\"type\":\"4餐饮业\",\"title\":\"黄焖鸡4加盟\",\"jieshao\":\"介绍内容，所有人可显示\",\"content\":\"具体内容(加密的,只对支付人和自己显示)\",\"price\":12.5}"; 
	        mockMvc.perform(MockMvcRequestBuilders.post("/chuangye/add")
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
		String requestBody = "{\"chuangyeid\":3,\"userid\":5,\"title\":\"创业标题3\",\"tag\":\"汽车\",\"type\":\"汽车\",\"title\":\"二手车买卖创业\",\"jieshao\":\"车介绍内容，所有人可显示\",\"content\":\"车具体内容(加密的,只对支付人和自己显示)\",\"price\":1000.12}";
	        mockMvc.perform(MockMvcRequestBuilders.post("/chuangye/update")
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
	public void pay() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(action + "/pay/3")
				.param("userid", "1"))
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
