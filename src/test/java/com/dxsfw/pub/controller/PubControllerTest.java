package com.dxsfw.pub.controller;

import java.io.File;
import java.util.Date;

import org.aspectj.util.FileUtil;
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
    	MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/pub/regMobile").param("mobile", "15010842975").param("password", "pwd").param("check_msg", "xxxx"))
  			.andDo(MockMvcResultHandlers.print())
  			.andReturn();
    }
    
    @Test
    public void testgetMobileCheckMsg() throws Exception {
    	MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/pub/getMobileCheckMsg?mobile=15010842975"))
    			.andDo(MockMvcResultHandlers.print())
    			.andReturn();
    }
    
    @Test
    public void testLogin() throws Exception {
    	MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/pub/login?mobile=15207109571&password=pwd"))
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
    	MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("http://120.25.58.3:8080/dxsfw/pub/getJianLiByUser?userid=5"))
    			.andDo(MockMvcResultHandlers.print())
    			.andReturn();
    }
    
    @Test
    public void testgetJianLiByJianliid() throws Exception {
    	MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/pub/getJianLiByJianliid?jianliid=8"))
    			.andDo(MockMvcResultHandlers.print())
    			.andReturn();
    }
    
    @Test
    public void testaddJianLi() throws Exception {
//        String requestBody = "{\"userid\":1,\"title\":\"简历标题\",\"name\":\"测试中文\",\"sex\":null,\"birthdate\":1438617600000,\"mobile\":null,\"email\":null,\"card\":null,\"school\":null,\"education\":null,\"experience\":null,\"evaluation\":null,\"picture\":null,\"fujian\":null}"; 
    	String requestBody = "{\"userid\":4,\"title\":\"我的简历标题2\",\"name\":\"姓名\",\"sex\":\"女 \",\"birthdate\":1441036800000,\"mobile\":\"13607447461\",\"email\":\"sfa@163.com\",\"card\":\"420202199012120000\",\"address\":\"长沙市望城坡1-1301\",\"height\":\"身高\",\"evaluation\":\"自我评价啊,随便填\",\"picture\":\"5/1.ico\",\"fujian\":\"2/2.xls\",\"createtime\":1441448327000,\"updatetime\":1441448595000,\"status\":\"N\",\"_education\":[{\"time\":\"2003/9-2006/6\",\"school\":\"湖南大学\",\"zhuanye\":\"会计学\",\"xueli\":\"本科\",\"miaoshu\":\"预留字段\"},{\"time\":\"2007/9-2010/6\",\"school\":\"北京大学\",\"zhuanye\":\"会计学\",\"xueli\":\"研究生\"},{\"time\":\"2010/9-2013/6\",\"school\":\"哈弗大学\",\"zhuanye\":\"财经管理\",\"xueli\":\"博士生\"}],\"_train\":[{\"time\":\"2013/9-2013/12\",\"company\":\"新东方厨师学院\",\"kecheng\":\"厨师高级班\",\"address\":\"长沙\",\"zhengshu\":\"国家级厨师专业三级\",\"miaoshu\":\"预留字段\"},{\"time\":\"2013/9-2013/12\",\"company\":\"某某飞行学校\",\"kecheng\":\"飞行驾驶员课程\",\"address\":\"上海\",\"zhengshu\":\"飞行员资格证书\"}],\"_language\":[{\"zhonglei\":\"英语\",\"dengji\":\"专业八级\",\"chengdu\":\"精通\",\"duxie\":\"一般\",\"tingshuo\":\"良好\"},{\"zhonglei\":\"日语\",\"dengji\":\"国家一级\",\"chengdu\":\"熟练\",\"duxie\":\"一般\",\"tingshuo\":\"良好\"},{\"zhonglei\":\"法语\",\"dengji\":\"一级\",\"chengdu\":\"熟练\",\"duxie\":\"一般\",\"tingshuo\":\"良好\"}],\"_zhengshu\":[{\"time\":\"2013/9\",\"name\":\"校级辩论赛一等奖\",\"dengji\":\"高级0\"},{\"time\":\"2003/9\",\"name\":\"1一等奖\",\"dengji\":\"高级1\"},{\"time\":\"2004/9\",\"name\":\"2一等奖\",\"dengji\":\"高级2\"},{\"time\":\"2014/9\",\"name\":\"3一等奖\",\"dengji\":\"高级3\"},{\"time\":\"2015/9\",\"name\":\"4一等奖\",\"dengji\":\"高级4\"},{\"time\":\"2013/10\",\"name\":\"5一等奖\",\"dengji\":\"高级5\"}],\"_experience\":[{\"time\":\"2013/9-2015/12\",\"company\":\"新东方英语学校\",\"zhiwei\":\"英语口语高级讲师\",\"address\":\"北京\",\"zhengshu\":\"预留字段\",\"miaoshu\":\"预留字段\"}]}";
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
    
    @Test
    public void testdeleteJianLi() throws Exception {
    	MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/pub/deleteJianLi?jianliid=3"))
    			.andDo(MockMvcResultHandlers.print())
    			.andReturn();
    }
    
    @Test
    public void testupdateJianLi() throws Exception {
    	Date date = new Date();
    	String requestBody = "{\"jianliid\":5,\"userid\":4,\"title\":\"1我的简历标题2\",\"name\":\"姓名\",\"sex\":\"女\",\"birthdate\":1441036800000,\"mobile\":\"13607447461\",\"email\":\"sfa@163.com\",\"card\":\"420202199012120000\",\"address\":\"长沙市望城坡1-1301\",\"height\":\"身高\",\"evaluation\":\"自我评价啊,随便填\",\"picture\":\"5/1.ico\",\"fujian\":\"2/2.xls\",\"createtime\":1441448327000,\"updatetime\":"+date.getTime()+",\"status\":\"N\",\"_education\":[{\"time\":\"2003/9-2006/6\",\"school\":\"湖南大学\",\"zhuanye\":\"会计学\",\"xueli\":\"本科\",\"miaoshu\":\"预留字段\"},{\"time\":\"2007/9-2010/6\",\"school\":\"北京大学\",\"zhuanye\":\"会计学\",\"xueli\":\"研究生\"},{\"time\":\"2010/9-2013/6\",\"school\":\"哈弗大学\",\"zhuanye\":\"财经管理\",\"xueli\":\"博士生\"}],\"_train\":[{\"time\":\"2013/9-2013/12\",\"company\":\"新东方厨师学院\",\"kecheng\":\"厨师高级班\",\"address\":\"长沙\",\"zhengshu\":\"国家级厨师专业三级\",\"miaoshu\":\"预留字段\"},{\"time\":\"2013/9-2013/12\",\"company\":\"某某飞行学校\",\"kecheng\":\"飞行驾驶员课程\",\"address\":\"上海\",\"zhengshu\":\"飞行员资格证书\"}],\"_language\":[{\"zhonglei\":\"英语\",\"dengji\":\"专业八级\",\"chengdu\":\"精通\",\"duxie\":\"一般\",\"tingshuo\":\"良好\"},{\"zhonglei\":\"日语\",\"dengji\":\"国家一级\",\"chengdu\":\"熟练\",\"duxie\":\"一般\",\"tingshuo\":\"良好\"},{\"zhonglei\":\"法语\",\"dengji\":\"一级\",\"chengdu\":\"熟练\",\"duxie\":\"一般\",\"tingshuo\":\"良好\"}],\"_zhengshu\":[{\"time\":\"2013/9\",\"name\":\"校级辩论赛一等奖\",\"dengji\":\"高级0\"},{\"time\":\"2003/9\",\"name\":\"1一等奖\",\"dengji\":\"高级1\"},{\"time\":\"2004/9\",\"name\":\"2一等奖\",\"dengji\":\"高级2\"},{\"time\":\"2014/9\",\"name\":\"3一等奖\",\"dengji\":\"高级3\"},{\"time\":\"2015/9\",\"name\":\"4一等奖\",\"dengji\":\"高级4\"},{\"time\":\"2013/10\",\"name\":\"5一等奖\",\"dengji\":\"高级5\"}],\"_experience\":[{\"time\":\"2013/9-2015/12\",\"company\":\"新东方英语学校\",\"zhiwei\":\"英语口语高级讲师\",\"address\":\"北京\",\"zhengshu\":\"预留字段\",\"miaoshu\":\"预留字段1\"}]}";
//    	String requestBody = "{\"jianliid\":8,\"userid\":4,\"title\":\"1我的简历标题2\",\"name\":\"姓名\",\"sex\":\"女\",\"birthdate\":"+new Date().getTime()+",\"mobile\":\"13607447461\",\"email\":\"sfa@163.com\",\"card\":\"420202199012120000\",\"address\":\"长沙市望城坡1-1301\",\"height\":\"身高\",\"evaluation\":\"自我评价啊,随便填\",\"picture\":\"5/1.ico\",\"fujian\":\"2/2.xls\",\"createtime\":1441448327000,\"updatetime\":1441448595000,\"status\":\"N\",\"_education\":[{\"time\":\"2003/9-2006/6\",\"school\":\"湖南大学\",\"zhuanye\":\"会计学\",\"xueli\":\"本科\",\"miaoshu\":\"预留字段\"},{\"time\":\"2007/9-2010/6\",\"school\":\"北京大学\",\"zhuanye\":\"会计学\",\"xueli\":\"研究生\"},{\"time\":\"2010/9-2013/6\",\"school\":\"哈弗大学\",\"zhuanye\":\"财经管理\",\"xueli\":\"博士生\"}],\"_train\":[{\"time\":\"2013/9-2013/12\",\"company\":\"新东方厨师学院\",\"kecheng\":\"厨师高级班\",\"address\":\"长沙\",\"zhengshu\":\"国家级厨师专业三级\",\"miaoshu\":\"预留字段\"},{\"time\":\"2013/9-2013/12\",\"company\":\"某某飞行学校\",\"kecheng\":\"飞行驾驶员课程\",\"address\":\"上海\",\"zhengshu\":\"飞行员资格证书\"}],\"_language\":[{\"zhonglei\":\"英语\",\"dengji\":\"专业八级\",\"chengdu\":\"精通\",\"duxie\":\"一般\",\"tingshuo\":\"良好\"},{\"zhonglei\":\"日语\",\"dengji\":\"国家一级\",\"chengdu\":\"熟练\",\"duxie\":\"一般\",\"tingshuo\":\"良好\"},{\"zhonglei\":\"法语\",\"dengji\":\"一级\",\"chengdu\":\"熟练\",\"duxie\":\"一般\",\"tingshuo\":\"良好\"}],\"_zhengshu\":[{\"time\":\"2013/9\",\"name\":\"校级辩论赛一等奖\",\"dengji\":\"高级0\"},{\"time\":\"2003/9\",\"name\":\"1一等奖\",\"dengji\":\"高级1\"},{\"time\":\"2004/9\",\"name\":\"2一等奖\",\"dengji\":\"高级2\"},{\"time\":\"2014/9\",\"name\":\"3一等奖\",\"dengji\":\"高级3\"},{\"time\":\"2015/9\",\"name\":\"4一等奖\",\"dengji\":\"高级4\"},{\"time\":\"2013/10\",\"name\":\"5一等奖\",\"dengji\":\"高级5\"}],\"_experience\":[{\"time\":\"2013/9-2015/12\",\"company\":\"新东方英语学校\",\"zhiwei\":\"英语口语高级讲师\",\"address\":\"北京\",\"zhengshu\":\"预留字段\",\"miaoshu\":\"预留字段1\"}]}";
        System.out.println(requestBody);
        mockMvc.perform(MockMvcRequestBuilders.post("/pub/updateJianLi")
                    .contentType(MediaType.APPLICATION_JSON).content(requestBody)
                    .accept(MediaType.APPLICATION_JSON)) //执行请求
                    .andDo(MockMvcResultHandlers.print())
        		.andReturn();
    }
    

    @Test
    public void uploadJianLiPicture() throws Exception {
    	//文件上传  
    	File file = new File("D:/workspace/xzl/picture/1.ico");
    	byte[] bytes = FileUtil.readAsByteArray(file);
    	mockMvc.perform(MockMvcRequestBuilders.fileUpload("/pub/uploadJianLiPicture?jianliid=1").file("file", bytes).param("type", "ico").param("token", "配合jsp页面测试spring")) //执行文件上传  
//    	        .andExpect(MockMvcResultMatchers.model().attribute("qq", bytes)) //验证属性相等性  
    	.andDo(MockMvcResultHandlers.print())
    	.andReturn();
    }
    
    @Test
    public void uploadJianLiFujian() throws Exception {
    	//文件上传  
    	File file = new File("D:/workspace/xzl/API接口说明.xls");
    	byte[] bytes = FileUtil.readAsByteArray(file);
    	mockMvc.perform(MockMvcRequestBuilders.fileUpload("/pub/uploadJianLiFujian?jianliid=2").file("file", bytes).param("type", "xls").param("token", "配合jsp页面测试spring")) //执行文件上传  
//    	        .andExpect(MockMvcResultMatchers.model().attribute("qq", bytes)) //验证属性相等性  
    	.andDo(MockMvcResultHandlers.print())
    	.andReturn();
    }
    
    @Test
    public void downloadJianLiPicture() throws Exception {
    	mockMvc.perform(MockMvcRequestBuilders.get("/pub/downloadJianLiPicture?jianliid=1").contentType(MediaType.MULTIPART_FORM_DATA))
		.andDo(MockMvcResultHandlers.print())
    	.andReturn();
    }
    
    @Test
    public void downloadJianLiFujian() throws Exception {
    	mockMvc.perform(MockMvcRequestBuilders.get("/pub/downloadJianLiFujian?jianliid=2").contentType(MediaType.MULTIPART_FORM_DATA))
    	.andDo(MockMvcResultHandlers.print())
    	.andReturn();
    }
    
    @Test
    public void getJianLiPublicInfo() throws Exception {
    	MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/pub/getJianLiPublicInfo?userid=2"))
    			.andDo(MockMvcResultHandlers.print())
    			.andReturn();
    }
	// ---------------------------简历---------------------------end
    
	// ---------------------------个人---------------------------start
    @Test
    public void tesupdateUser() throws Exception {
//        String requestBody = "{\"userid\":1,\"type\":3,\"name\":\"开发人员\",\"password\":\"pwd\",\"email\":null,\"mobile\":\"15207109571\",\"nickname\":null,\"organization\":null,\"introduction\":null,\"card\":null,\"sex\":null,\"weixin\":null,\"zhifubao\":null}"; 
    	//没属性和null，将不更新。值""可以更新。
        String requestBody = "{\"userid\":1,\"type\":null,\"name\":\"开发人员\",\"email\":\"email2\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/pub/updateUser")
                    .contentType(MediaType.APPLICATION_JSON).content(requestBody)
                    .accept(MediaType.APPLICATION_JSON)) //执行请求
                    .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON)) //验证响应contentType
//                .andExpect(MockMvcResultMatchers.jsonPath("$.jianliid").value(2)) //使用Json path验证JSON 请参考http://goessner.net/articles/JsonPath/
        		.andReturn();
    }
    
    @Test
    public void uploadUserPicture() throws Exception {
    	//文件上传  
    	File file = new File("D:/workspace/xzl/picture/1.ico");
    	byte[] bytes = FileUtil.readAsByteArray(file);
    	mockMvc.perform(MockMvcRequestBuilders.fileUpload("/pub/uploadUserPicture?userid=1").file("file", bytes).param("type", "ico").param("token", "配合jsp页面测试spring")) //执行文件上传  
//    	        .andExpect(MockMvcResultMatchers.model().attribute("qq", bytes)) //验证属性相等性  
    	        .andDo(MockMvcResultHandlers.print())
        		.andReturn();
    }
    
    @Test
    public void downloadUserPicture() throws Exception {
    	mockMvc.perform(MockMvcRequestBuilders.get("/pub/downloadUserPicture?userid=1").contentType(MediaType.MULTIPART_FORM_DATA))
		.andDo(MockMvcResultHandlers.print())
    	.andReturn();
    }
    
	// ---------------------------个人---------------------------end
    
    // ---------------------------公共---------------------------start
    /**
     * 业务模块单一图片测试
     * @throws Exception
     */
    @Test
    public void uploadPicture() throws Exception {
    	//文件上传  
    	File file = new File("D:/workspace/xzl/picture/1.ico");
    	byte[] bytes = FileUtil.readAsByteArray(file);
    	//单一个图片业务模块新增或更新测试
//    	mockMvc.perform(MockMvcRequestBuilders.fileUpload("/pub/upload/jianzhi/4").file("file", bytes).param("type", "jpg").param("token", "配合jsp页面测试spring")) //执行文件上传  
    	//多图片业务模块更新测试
    	mockMvc.perform(MockMvcRequestBuilders.fileUpload("/pub/upload/party/5").file("file", bytes).param("pictureid", "5").param("type", "bpm").param("token", "配合jsp页面测试spring")) //执行文件上传  
    	.andDo(MockMvcResultHandlers.print())
    	.andReturn();
    }
    
    /**
     * 业务模块多图片测试
     * @throws Exception
     */
    @Test
    public void uploadMorePicture() throws Exception {
    	//文件上传  
    	File file = new File("D:/workspace/xzl/picture/1.ico");
    	byte[] bytes = FileUtil.readAsByteArray(file);
    	mockMvc.perform(MockMvcRequestBuilders.fileUpload("/pub/uploadMore/party/5").file("file", bytes).param("type", "jpg").param("token", "配合jsp页面测试spring")) //执行文件上传  
    	.andDo(MockMvcResultHandlers.print())
    	.andReturn();
    }
    
    @Test
    public void downloadPicture() throws Exception {
    	//单一个图片业务模块新增或更新测试
    	mockMvc.perform(MockMvcRequestBuilders.get("/pub/download/jianzhi/5").contentType(MediaType.MULTIPART_FORM_DATA))
    	//多图片业务模块更新测试
//    	mockMvc.perform(MockMvcRequestBuilders.get("/pub/download/party/1").param("pictureid", "5").contentType(MediaType.MULTIPART_FORM_DATA))
		.andDo(MockMvcResultHandlers.print())
    	.andReturn();
    }
    // ---------------------------公共---------------------------end
    
}
