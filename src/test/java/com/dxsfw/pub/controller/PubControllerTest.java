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
	
	//---------------------------登录、注册---------------------------start
    @Test
    //启动应用 不能直接登录
    public void testopenApp() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/pub/openApp?token=1"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
    
    @Test
    public void testregMobile() throws Exception {
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
    	MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/pub/login?mobile=15207109571&password=pwd1"))
//                .andExpect(MockMvcResultMatchers.view().name("user/view"))
//                .andExpect(MockMvcResultMatchers.model().attributeExists("user"))
    			.andDo(MockMvcResultHandlers.print())
    			.andReturn();
    	
//        Assert.assertNotNull(result.getModelAndView().getModel().get("user"));
    }
  //---------------------------登录、注册---------------------------end
    
  //---------------------------简历---------------------------start
    @Test
    public void testgetJianLiByUser() throws Exception {
    	MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/pub/getJianLiByUser?userid=2"))
    			.andDo(MockMvcResultHandlers.print())
    			.andReturn();
    }
    
    @Test
    public void testgetJianLiByJianliid() throws Exception {
    	MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/pub/getJianLiByJianliid?jianliid=2"))
    			.andDo(MockMvcResultHandlers.print())
    			.andReturn();
    }
    
    @Test
    public void testaddJianLi() throws Exception {
        String requestBody = "{\"jianliid\":3,\"userid\":1,\"title\":\"简历标题\",\"name\":\"name\",\"sex\":null,\"birthdate\":1438617600000,\"mobile\":null,\"email\":null,\"card\":null,\"school\":null,\"education\":null,\"experience\":null,\"evaluation\":null,\"picture\":null,\"fujian\":null}"; 
        mockMvc.perform(MockMvcRequestBuilders.post("/pub/addJianLi")
                    .contentType(MediaType.APPLICATION_JSON).content(requestBody)
                    .accept(MediaType.APPLICATION_JSON)) //执行请求
                    .andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON)) //验证响应contentType
//                .andExpect(MockMvcResultMatchers.jsonPath("$.jianliid").value(2)) //使用Json path验证JSON 请参考http://goessner.net/articles/JsonPath/
        		.andReturn();

//        String errorBody = "{id:1, name:zhang}";
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/pub/addJianLi")
//                .contentType(MediaType.APPLICATION_JSON).content(errorBody)
//                .accept(MediaType.APPLICATION_JSON)) //执行请求
//                .andExpect(MockMvcResultMatchers.status().isBadRequest()) //400错误请求
//                .andReturn();
//
//        Assert.assertTrue(HttpMessageNotReadableException.class.isAssignableFrom(result.getResolvedException().getClass()));//错误的请求内容体

    }
    
	// ---------------------------简历---------------------------end
    
	// ---------------------------个人---------------------------start
    @Test
    public void tesupdateUser() throws Exception {
//        String requestBody = "{\"useid\":1,\"type\":3,\"name\":\"开发人员\",\"password\":\"pwd\",\"email\":null,\"mobile\":\"15207109571\",\"nickname\":null,\"organization\":null,\"introduction\":null,\"card\":null,\"sex\":null,\"weixin\":null,\"zhifubao\":null}"; 
    	//没属性和null，将不更新。值""可以更新。
        String requestBody = "{\"useid\":1,\"type\":null,\"name\":\"开发人员\",\"email\":\"email2\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/pub/updateUser")
                    .contentType(MediaType.APPLICATION_JSON).content(requestBody)
                    .accept(MediaType.APPLICATION_JSON)) //执行请求
                    .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON)) //验证响应contentType
//                .andExpect(MockMvcResultMatchers.jsonPath("$.jianliid").value(2)) //使用Json path验证JSON 请参考http://goessner.net/articles/JsonPath/
        		.andReturn();
    }
	// ---------------------------个人---------------------------end
    
    
}
