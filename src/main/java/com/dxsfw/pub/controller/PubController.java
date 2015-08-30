package com.dxsfw.pub.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dxsfw.common.constants.GlobalValue;
import com.dxsfw.common.util.RandomUtil;
import com.dxsfw.pub.model.JianLi;
import com.dxsfw.pub.model.User;
import com.dxsfw.pub.service.JianLiService;
import com.dxsfw.pub.service.UserService;

@Controller
@RequestMapping("/pub")
public class PubController {
	private static Logger log = LoggerFactory.getLogger(PubController.class);

	@Autowired
	private UserService userService;
	@Autowired
	private JianLiService jianLiService;

	// ---------------------------登录注册---------------------------start
	/**
	 * token登录
	 * 
	 * @param token
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("openApp")
	public Map openApp(@RequestParam(value = "token") String token, HttpServletRequest request,
			HttpServletResponse response) {
		User user = null;
		Integer userid = GlobalValue.USER_TOKEN_MAP.get(token);
		if (userid != null) {
			// 服务器未重启
			try {
				user = userService.getUser(userid);
				token = request.getSession().getId();
				GlobalValue.USER_TOKEN_MAP.put(token, user.getUseid());
			} catch (Exception e) {
				log.error("/openApp", e);
			}
		} else {
			token = "";
		}
		return this.responseJsonObject(token, user);
	}

	/**
	 * 手机号登录
	 * 
	 * @param mobile
	 * @param password
	 * @return
	 */
	@ResponseBody
	@RequestMapping("login")
	public Map login(@RequestParam(value = "mobile") String mobile, @RequestParam(value = "password") String password,
			HttpServletRequest request) {
		User user = userService.login(mobile, password);
		if (user != null) {
			String token = request.getSession().getId();
			GlobalValue.USER_TOKEN_MAP.put(token, user.getUseid());
			return this.responseJsonObject(token, user);
		} else {
			return this.responseJsonObject("", null);
		}
	}

	/**
	 * 注册
	 * 
	 * @param check_msg
	 * @param mobile
	 * @param password
	 * @return
	 */
	@ResponseBody
	@RequestMapping("regMobile")
	public Map regMobile(@RequestParam(value = "check_msg") String check_msg,
			@RequestParam(value = "mobile") String mobile, @RequestParam(value = "password") String password,
			HttpServletRequest request) {
		String token = "";
		User user = null;
		//验证短信码
		String key = mobile + "-" + check_msg;
		Long value = GlobalValue.MOBILE_CHECK_MAP.get(key) == null ? 0 : GlobalValue.MOBILE_CHECK_MAP.get(key);
		long currentTime = System.currentTimeMillis();

		if (currentTime - value < 60 * 1000) {
			// 未超时可注册
			user = userService.reg(mobile, password);
			if (user != null) {
				token = request.getSession().getId();
				GlobalValue.USER_TOKEN_MAP.put(token, user.getUseid());
			}
		}
		//不管是否注册成功 都需要清理map
		GlobalValue.MOBILE_CHECK_MAP.remove(key);
		return this.responseJsonObject(token, user);
	}
	
	/**
	 * 获取短信验证码
	 * @param mobile
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getMobileCheckMsg")
	public String getMobileCheckMsg(@RequestParam(value = "mobile") String mobile, HttpServletRequest request) {
		String checkMsg = RandomUtil.general4Number();
		// TODO 短信猫发送短信
		
		//手机号和验证码绑定60秒
		long value = System.currentTimeMillis();
		String key = mobile + "-" + checkMsg;
		GlobalValue.MOBILE_CHECK_MAP.put(key, value);
		
		return checkMsg;
	}
	// ---------------------------登录注册---------------------------end
	
	// ---------------------------个人---------------------------start
	
	/**
	 * 更新个人信息
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping("updateUser")
	public User updateUser(@RequestBody User user) {
		return userService.updateUser(user);
	}
	
	@RequestMapping(value = "uploadUserPicture")
	public String upload(@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest request, ModelMap model) {
		
//		byte[] picture = file.getBytes();
//		userService.uploadUserPicture(userid, picture)
//		file.getBytes()
//		String path = request.getSession().getServletContext().getRealPath("upload");
//		String fileName = file.getOriginalFilename();
//		File targetFile = new File(path, fileName);
//		if (!targetFile.exists()) {
//			targetFile.mkdirs();
//		}
//
//		// 保存
//		try {
//			file.transferTo(targetFile);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		model.addAttribute("fileUrl", request.getContextPath() + "/upload/" + fileName);

		return "result";
	}

	// ---------------------------个人---------------------------end
	
	// ---------------------------简历---------------------------start
	/**
	 * 新增简历
	 * @return
	 */
	@ResponseBody
	@RequestMapping("addJianLi")
	public JianLi addJianLi(@RequestBody JianLi jianli) {
		jianli = jianLiService.addJianLi(jianli);
		return jianli;
	}
	
	/**
	 * 获取用户的所有简历
	 * @param userid
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getJianLiByUser")
	public List<JianLi> getJianLiByUser(@RequestParam(value = "userid") int userid) {
		return jianLiService.getJianLiByUser(userid);
	}
	
	// ---------------------------简历---------------------------end
	
	/**
	 * User通用返回json对象 ｛token，user｝
	 * 
	 * @return
	 */
	private LinkedHashMap responseJsonObject(String token, User user) {
		LinkedHashMap map = new LinkedHashMap();
		map.put("token", token);
		if (user != null) {
			map.put("user", user);
		}
		return map;
	}
}
