package com.dxsfw.pub.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//XML风格
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "web")
@ContextHierarchy({
      @ContextConfiguration(name = "parent", locations = "classpath:applicationContext.xml"),
      @ContextConfiguration(name = "child", locations = "classpath:spring-servlet.xml")
})
//http://jinnianshilongnian.iteye.com/blog/2004660
public class PubControllerTest {

	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
    @Test
    //启动应用 不能直接登录
    public void testopenApp() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/pub/openApp?token=1"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
    
    @Test
    public void testregMobile() throws Exception {
//        String requestBody = "{\"id\":1, \"name\":\"zhang\"}";
//        mockMvc.perform(MockMvcRequestBuilders.post("/pub/regMobile").param("mobile", "18000000").param("password", "pwd").param("check_msg", "xxxx")
//                    .contentType(MediaType.APPLICATION_JSON).content(requestBody)
//                    .accept(MediaType.APPLICATION_JSON)) //执行请求
//                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON)) //验证响应contentType
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1)); //使用Json path验证JSON 请参考http://goessner.net/articles/JsonPath/

    	MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/pub/regMobile").param("mobile", "18000000").param("password", "pwd").param("check_msg", "xxxx"))
  			.andDo(MockMvcResultHandlers.print())
  			.andReturn();

    }
    
    @Test
    public void testgetMobileCheckMsg() throws Exception {
    	MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/pub/getMobileCheckMsg?mobile=18000000"))
    			.andDo(MockMvcResultHandlers.print())
    			.andReturn();
    }
    
    @Test
    public void testLogin() throws Exception {
    	MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/pub/login?mobile=1&password=2"))
//                .andExpect(MockMvcResultMatchers.view().name("user/view"))
//                .andExpect(MockMvcResultMatchers.model().attributeExists("user"))
    			.andDo(MockMvcResultHandlers.print())
    			.andReturn();
    	
//        Assert.assertNotNull(result.getModelAndView().getModel().get("user"));
    }
}
