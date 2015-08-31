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

import com.dxsfw.common.base.Res;
import com.dxsfw.common.constants.Constant;
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
	public Res openApp(@RequestParam(value = "token") String token, HttpServletRequest request,
			HttpServletResponse response) {
		User user = null;
		int status = Constant.STATUS_ERROR_500;
		String msg = Constant.MSG_ERROR_TOKEN;
		Integer userid = GlobalValue.USER_TOKEN_MAP.get(token);
		if (userid != null) {
			// 服务器未重启
			try {
				user = userService.getUser(userid);
				token = request.getSession().getId();
				GlobalValue.USER_TOKEN_MAP.put(token, user.getUseid());
				status = Constant.STATUS_OK_200;
				msg = Constant.MSG_OK_TOKEN;
			} catch (Exception e) {
				log.error("/openApp", e);
			}
		} else {
			token = "";
		}
		Res responseJson = new Res(status, msg);
		responseJson.setToken(token);
		responseJson.setUser(user);
		return responseJson;
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
	public Res login(@RequestParam(value = "mobile") String mobile, @RequestParam(value = "password") String password,
			HttpServletRequest request) {
		int status = Constant.STATUS_ERROR_500;
		String msg = Constant.MSG_ERROR_LOGIN;
		String token = null;
		User user = userService.login(mobile, password);
		if (user != null) {
			token = request.getSession().getId();
			GlobalValue.USER_TOKEN_MAP.put(token, user.getUseid());
			status = Constant.STATUS_OK_200;
			msg = Constant.MSG_OK_TOKEN;
		}
		Res responseJson = new Res(status, msg);
		responseJson.setToken(token);
		responseJson.setUser(user);
		return responseJson;
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
	public Res regMobile(@RequestParam(value = "check_msg") String check_msg,
			@RequestParam(value = "mobile") String mobile, @RequestParam(value = "password") String password,
			HttpServletRequest request) {
		int status = Constant.STATUS_ERROR_500;
		String msg = Constant.MSG_ERROR_LOGIN;
		String token = null;
		User user = null;
		//验证短信码
		String key = mobile + "-" + check_msg;
		Long value = GlobalValue.MOBILE_CHECK_MAP.get(key) == null ? 0 : GlobalValue.MOBILE_CHECK_MAP.get(key);
		long currentTime = System.currentTimeMillis();
//		value = currentTime;//test code

		if (currentTime - value < 60 * 1000) {
			// 未超时可注册
			user = userService.reg(mobile, password);
			if (user != null) {
				token = request.getSession().getId();
				GlobalValue.USER_TOKEN_MAP.put(token, user.getUseid());
				status = Constant.STATUS_OK_200;
				msg = Constant.MSG_OK_USER_REG;
			} else {
				msg = Constant.MSG_ERROR_USER_REG;
			}
		} else{
			msg = Constant.MSG_ERROR_CHECK_MSG;
		}
		//不管是否注册成功 都需要清理map
		GlobalValue.MOBILE_CHECK_MAP.remove(key);
		Res responseJson = new Res(status, msg);
		responseJson.setToken(token);
		responseJson.setUser(user);
		return responseJson;
	}
	
	/**
	 * 获取短信验证码
	 * @param mobile
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getMobileCheckMsg")
	public Map getMobileCheckMsg(@RequestParam(value = "mobile") String mobile, HttpServletRequest request) {
		String checkMsg = RandomUtil.general4Number();
		// TODO 短信猫发送短信
		
		//手机号和验证码绑定60秒
		long value = System.currentTimeMillis();
		String key = mobile + "-" + checkMsg;
		GlobalValue.MOBILE_CHECK_MAP.put(key, value);
		
		LinkedHashMap map = new LinkedHashMap();
		map.put("status", Constant.STATUS_OK_200);
		map.put("msg", Constant.MSG_OK_CHECK_MSG);
		map.put("check_msg", checkMsg);
		return map;
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
	public Res updateUser(@RequestBody User user) {
		Res responseJson = new Res();
		try {
			user = userService.updateUser(user);
			responseJson.setMsg(Constant.MSG_OK_USER_UPDATE);
		} catch (Exception e) {
			log.error("/updateUser", e);
			responseJson.setMsg(Constant.MSG_ERROR_USER_UPDATE);
			responseJson.setStatus(Constant.STATUS_ERROR_500);
			user = null;
		}
		responseJson.setUser(user);
		return responseJson;
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
	public Res addJianLi(@RequestBody JianLi jianli) {
		Res responseJson = new Res();
		try {
			jianli = jianLiService.addJianLi(jianli);
			if (jianli != null) {
				responseJson.setMsg(Constant.MSG_OK_JIANLI_ADD);
			}
		} catch (Exception e) {
			log.error("/addJianLi", e);
			jianli = null;
		}
		if (jianli == null) {
			responseJson.setMsg(Constant.MSG_ERROR_JIANLI_ADD);
			responseJson.setStatus(Constant.STATUS_ERROR_500);
		}
		responseJson.setJianli(jianli);
		return responseJson;
	}
	
	/**
	 * 获取用户的所有简历
	 * @param userid
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getJianLiByUser")
	public Res getJianLiByUser(@RequestParam(value = "userid") int userid) {
		Res responseJson = new Res();
		try {
			List<JianLi> jianliList = jianLiService.getJianLiByUser(userid);
			responseJson.setJianliList(jianliList);
			responseJson.setMsg(Constant.MSG_OK_JIANLI_LIST);
		} catch (Exception e) {
			log.error("/getJianLiByUser", e);
			responseJson.setMsg(Constant.MSG_ERROR_JIANLI_LIST);
			responseJson.setStatus(Constant.STATUS_ERROR_500);
		}
		return responseJson;
	}
	
	/**
	 * 获取用户某个简历
	 * @param userid
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getJianLiByJianliid")
	public Res getJianLiByJianliid(@RequestParam(value = "jianliid") int jianliid) {
		Res responseJson = new Res();
		try {
			JianLi jianli = jianLiService.getJianLi(jianliid);
			responseJson.setJianli(jianli);
			responseJson.setMsg(Constant.MSG_OK_JIANLI_GET);
		} catch (Exception e) {
			log.error("/getJianLiByUser", e);
			responseJson.setMsg(Constant.MSG_ERROR_JIANLI_GET);
			responseJson.setStatus(Constant.STATUS_ERROR_500);
		}
		return responseJson;
	}
	
	// ---------------------------简历---------------------------end
	
}
