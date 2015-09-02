package com.dxsfw.pub.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

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
				GlobalValue.USER_TOKEN_MAP.put(token, user.getUserid());
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
			GlobalValue.USER_TOKEN_MAP.put(token, user.getUserid());
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
				GlobalValue.USER_TOKEN_MAP.put(token, user.getUserid());
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
	
	/**
	 * 上传或更新个人头像
	 * @param file
	 * @param userid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "uploadUserPicture")
	public Res uploadUserPicture(@RequestParam(value = "file") MultipartFile file,
			@RequestParam(value = "userid") int userid, @RequestParam(value = "type") String type,
			@RequestParam(value = "token") String token) {
		Res responseJson = new Res();
		try {
			//文件名
			String fileName = userid + "." + type;
			//文件相对路径
			String picture = userid + File.separator + fileName;
			//文件夹绝对路径
			String picturePath = GlobalValue.PATH_USER_PICTURE + userid + File.separator;
			User user = new User();
			user.setUserid(userid);
			user.setPicture(picture);
			user = userService.updateUser(user);
			File targetFile = new File(picturePath);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			// 保存
			targetFile = new File(picturePath + fileName);
			file.transferTo(targetFile);
			responseJson.setMsg(Constant.UPDATE + Constant.USER_PICTURE + Constant.OK);
		} catch (Exception e) {
			log.error("/uploadUserPicture", e);
			responseJson.setMsg(Constant.UPDATE + Constant.USER_PICTURE + Constant.ERROR);
			responseJson.setStatus(Constant.STATUS_ERROR_500);
		}
		return responseJson;
	}

	/**
	 * 下载个人头像
	 * @param file
	 * @param userid
	 * @param type
	 * @param token
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "downloadUserPicture")
	public ModelAndView downloadUserPicture(@RequestParam(value = "userid") int userid, HttpServletResponse response) throws Exception{

		User user = userService.getUser(userid);
		// 解析出文件名 
		// {userid}\{userid}.type  
		// eg： 1\1.ico
		// 1\1.ico -> 1.ico
		String fileName = user.getPicture().substring(
				user.getPicture().indexOf(user.getUserid() + File.separator)
						+ (user.getUserid() + File.separator).length());
		// 文件夹绝对路径
		String downLoadPath = GlobalValue.PATH_USER_PICTURE + user.getPicture();

		this.downloadFile(response, fileName, downLoadPath);
		return null;
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
	
	/**
	 * 删除简历
	 * @return
	 */
	@ResponseBody
	@RequestMapping("deleteJianLi")
	public Res deleteJianLi(@RequestParam(value = "jianliid") int jianliid) {
		Res responseJson = new Res();
		try {
			jianLiService.deleteJianLi(jianliid);
			responseJson.setMsg(Constant.DELETE + Constant.JIANLI + Constant.OK);
		} catch (Exception e) {
			log.error("/getJianLiByUser", e);
			responseJson.setMsg(Constant.DELETE + Constant.JIANLI + Constant.ERROR);
			responseJson.setStatus(Constant.STATUS_ERROR_500);
		}
		return responseJson;
	}
	
	/**
	 * 更新简历
	 * @return
	 */
	@ResponseBody
	@RequestMapping("updateJianLi")
	public Res updateJianLi(@RequestBody JianLi jianli) {
		Res responseJson = new Res();
		try {
			jianli = jianLiService.updateJianLi(jianli);
			if (jianli != null) {
				responseJson.setMsg(Constant.UPDATE + Constant.JIANLI + Constant.OK);
			}
		} catch (Exception e) {
			log.error("/addJianLi", e);
			jianli = null;
		}
		if (jianli == null) {
			responseJson.setMsg(Constant.UPDATE + Constant.JIANLI + Constant.ERROR);
			responseJson.setStatus(Constant.STATUS_ERROR_500);
		}
		responseJson.setJianli(jianli);
		return responseJson;
	}
	
	/**
	 * 上传或更新简历头像
	 * @param file
	 * @param jianliid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "uploadJianLiPicture")
	public Res uploadJianLiPicture(@RequestParam(value = "file") MultipartFile file,
			@RequestParam(value = "jianliid") int jianliid, @RequestParam(value = "type") String type,
			@RequestParam(value = "token") String token) {
		Res responseJson = new Res();
		try {
			//文件名
			String fileName = jianliid + "." + type;
			//文件相对路径
			String picture = jianliid + File.separator + fileName;
			//文件夹绝对路径
			String picturePath = GlobalValue.PATH_JIANLI_PICTURE + jianliid + File.separator;
			JianLi jl = new JianLi();
			jl.setJianliid(jianliid);
			jl.setPicture(picture);
			jianLiService.updateJianLi(jl);
			File targetFile = new File(picturePath);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			// 保存
			targetFile = new File(picturePath + fileName);
			file.transferTo(targetFile);
			responseJson.setMsg(Constant.UPDATE + Constant.JIANLI_PICTURE + Constant.OK);
		} catch (Exception e) {
			log.error("/uploadJianLiPicture", e);
			responseJson.setMsg(Constant.UPDATE + Constant.JIANLI_PICTURE + Constant.ERROR);
			responseJson.setStatus(Constant.STATUS_ERROR_500);
		}
		return responseJson;
	}
	

	/**
	 * 上传或更新简历附件
	 * @param file
	 * @param jianliid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "uploadJianLiFujian")
	public Res uploadJianLiFujian(@RequestParam(value = "file") MultipartFile file,
			@RequestParam(value = "jianliid") int jianliid, @RequestParam(value = "type") String type,
			@RequestParam(value = "token") String token) {
		Res responseJson = new Res();
		try {
			//文件名
			String fileName = jianliid + "." + type;
			//文件相对路径
			String fujian = jianliid + File.separator + fileName;
			//文件夹绝对路径
			String picturePath = GlobalValue.PATH_JIANLI_FUJIAN + jianliid + File.separator;
			JianLi jl = new JianLi();
			jl.setJianliid(jianliid);
			jl.setFujian(fujian);
			jianLiService.updateJianLi(jl);
			File targetFile = new File(picturePath);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			// 保存
			targetFile = new File(picturePath + fileName);
			file.transferTo(targetFile);
			responseJson.setMsg(Constant.UPDATE + Constant.JIANLI_FUJIAN + Constant.OK);
		} catch (Exception e) {
			log.error("/uploadJianLiFujian", e);
			responseJson.setMsg(Constant.UPDATE + Constant.JIANLI_FUJIAN + Constant.ERROR);
			responseJson.setStatus(Constant.STATUS_ERROR_500);
		}
		return responseJson;
	}
	
	/**
	 * 下载简历个人头像
	 * @param jianliid
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "downloadJianLiPicture")
	public ModelAndView downloadJianLiPicture(@RequestParam(value = "jianliid") int jianliid, HttpServletResponse response) throws Exception{
		JianLi jianli = jianLiService.getJianLi(jianliid);
		// 文件名
		String fileName = jianli.getPicture().substring(
				jianli.getPicture().indexOf(jianli.getJianliid() + File.separator)
						+ (jianli.getJianliid() + File.separator).length());
		// 文件夹绝对路径
		String downLoadPath = GlobalValue.PATH_JIANLI_PICTURE + jianli.getPicture();

		this.downloadFile(response, fileName, downLoadPath);
		return null;
	}
	
	/**
	 * 下载简历附件
	 * @param jianliid
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "downloadJianLiFujian")
	public ModelAndView downloadJianLiFujian(@RequestParam(value = "jianliid") int jianliid, HttpServletResponse response) throws Exception{
		JianLi jianli = jianLiService.getJianLi(jianliid);
		// 文件名
		String fileName = jianli.getFujian().substring(
				jianli.getFujian().indexOf(jianli.getJianliid() + File.separator)
				+ (jianli.getJianliid() + File.separator).length());
		// 文件夹绝对路径
		String downLoadPath = GlobalValue.PATH_JIANLI_FUJIAN + jianli.getFujian();
		
		this.downloadFile(response, fileName, downLoadPath);
		return null;
	}
	// ---------------------------简历---------------------------end
	
	
	// ---------------------------辅助方法---------------------------end
	/**
	 * 下载文件
	 * @param response
	 * @param fileName
	 * @param downLoadPath
	 * @throws IOException
	 */
	private void downloadFile(HttpServletResponse response, String fileName,
			String downLoadPath) throws IOException {
		response.setContentType("multipart/form-data");
		java.io.BufferedInputStream bis = null;
		java.io.BufferedOutputStream bos = null;
		try {
			
			long fileLength = new File(downLoadPath).length();
			response.setContentType("multipart/form-data");
			response.setHeader("Content-disposition", "attachment; filename=" + fileName);
			response.setHeader("Content-Length", String.valueOf(fileLength));
			bis = new BufferedInputStream(new FileInputStream(downLoadPath));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[1024];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			log.error("downloadFile", e);
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
	}
}
