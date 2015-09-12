package com.dxsfw.pub.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.dxsfw.bbs.model.Bbs;
import com.dxsfw.bbs.service.BbsService;
import com.dxsfw.chuangye.model.ChuangYe;
import com.dxsfw.chuangye.service.ChuangYeService;
import com.dxsfw.common.base.Res;
import com.dxsfw.common.base.jianli.ResJianLi;
import com.dxsfw.common.constants.Constant;
import com.dxsfw.common.constants.GlobalValue;
import com.dxsfw.common.page.Pagination;
import com.dxsfw.common.util.JsonUtil;
import com.dxsfw.common.util.RandomUtil;
import com.dxsfw.idea.model.Idea;
import com.dxsfw.idea.model.Zhengji;
import com.dxsfw.idea.service.IdeaService;
import com.dxsfw.idea.service.ZhengjiService;
import com.dxsfw.jianzhi.model.Jianzhi;
import com.dxsfw.jianzhi.service.JianzhiService;
import com.dxsfw.party.model.Party;
import com.dxsfw.party.service.PartyService;
import com.dxsfw.pub.model.Fujian;
import com.dxsfw.pub.model.JianLi;
import com.dxsfw.pub.model.Picture;
import com.dxsfw.pub.model.Reply;
import com.dxsfw.pub.model.ReplyExample;
import com.dxsfw.pub.model.ReplyExample.Criteria;
import com.dxsfw.pub.model.User;
import com.dxsfw.pub.service.JianLiService;
import com.dxsfw.pub.service.PubService;
import com.dxsfw.pub.service.ReplyService;
import com.dxsfw.pub.service.UserService;

@Controller
@RequestMapping("/pub")
public class PubController {
	private static Logger log = LoggerFactory.getLogger(PubController.class);

	@Autowired
	private PubService pubService;
	@Autowired
	private UserService userService;
	@Autowired
	private JianLiService jianLiService;
	@Autowired
	private JianzhiService jianzhiService;
	@Autowired
	private PartyService partyService;
	@Autowired
	private BbsService bbsService;
	@Autowired
	private ReplyService replyService;
	@Autowired
	private ChuangYeService chuangyeService;
	@Autowired
	private IdeaService ideaService;
	@Autowired
	private ZhengjiService zhengjiService;

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
	 * 获取个人信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getUser")
	public Res getUser(@RequestParam(value = "userid") Integer userid) {
		Res responseJson = new Res();
		User user = null;
		try {
			user = userService.getUser(userid);
			responseJson.setMsg(Constant.MSG_OK_USER_GET);
		} catch (Exception e) {
			log.error("/getUser", e);
			responseJson.setMsg(Constant.MSG_ERROR_USER_GET);
			responseJson.setStatus(Constant.STATUS_ERROR_500);
			user = null;
		}
		responseJson.setUser(user);
		return responseJson;
	}
	
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
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("addJianLi")
	public Res addJianLi(@RequestBody ResJianLi res) throws Exception {
		Res responseJson = new Res();
		JianLi jianli = null;
		try {
			jianli = JsonUtil.res2Jianli(res);
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
		responseJson.setJianli(JsonUtil.jianli2Res(jianli));
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
			responseJson.setJianliList(JsonUtil.jianli2Res(jianliList));
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
			responseJson.setJianli(JsonUtil.jianli2Res(jianli));
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
	public Res updateJianLi(@RequestBody ResJianLi res) throws Exception {
		Res responseJson = new Res();
		JianLi jianli = null;
		try {
			jianli = JsonUtil.res2Jianli(res);
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
		responseJson.setJianli(JsonUtil.jianli2Res(jianli));
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
	
	/**
	 * 获取用户之前简历的基本信息
	 * @param userid
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getJianLiPublicInfo")
	public Res getJianLiPublicInfo(@RequestParam(value = "userid") int userid) {
		Res responseJson = new Res();
		try {
			List<JianLi> list = jianLiService.getJianLiByUser(userid);
			if (list.size() > 0) {
				JianLi jianli = list.get(0);
				ResJianLi res = new ResJianLi();
				//公共信息
				res.setName(jianli.getName());
				res.setSex(jianli.getSex());
				res.setBirthdate(jianli.getBirthdate());
				res.setMobile(jianli.getMobile());
				res.setEmail(jianli.getEmail());
				res.setCard(jianli.getCard());
				res.setAddress(jianli.getAddress());
				res.setHeight(jianli.getHeight());
				
				responseJson.setJianli(res);
				responseJson.setMsg(Constant.MSG_OK_JIANLI_GET);
			} else {
				responseJson.setMsg(Constant.MSG_ERROR_JIANLI_LIST);
			}
		} catch (Exception e) {
			log.error("/getJianLiByUser", e);
			responseJson.setMsg(Constant.MSG_ERROR_JIANLI_GET);
			responseJson.setStatus(Constant.STATUS_ERROR_500);
		}
		return responseJson;
	}
	// ---------------------------简历---------------------------end
	
	
	// ---------------------------共用接口---------------------------start
	/**
	 * 上传业务模块单一图片
	 * 更新图片指定图片
	 * @return
	 */
	@ResponseBody
	@RequestMapping("upload/{modlename}/{pk:\\d+}")
	public Res uploadPubPicture(@RequestParam(value = "file") MultipartFile file,
			@PathVariable("modlename") String modlename, 
			@PathVariable("pk") int pk,
			@RequestParam(value = "type") String type,
			@RequestParam(value = "pictureid", required = false) Integer pictureid,
			@RequestParam(value = "token") String token) {
		Res responseJson = new Res();
		// 验证模块是否支持
//		if (GlobalValue.TABLE_SET.contains(modlename)) {
			try {
				String tablename = "t_" + modlename;
				// 文件名
				String fileName = null;
				if (pictureid == null) {
					// 业务条目唯一图片
					fileName = pk + "." + type;
				} else {
					// 业务条目多图片
					fileName = pk + "_" + pictureid + "." + type;
				}
				// 文件夹绝对路径
				String picturePath = GlobalValue.PATH_PICTURE + modlename + File.separator;
				File targetFile = new File(picturePath);
				if (!targetFile.exists()) {
					targetFile.mkdirs();
				}
				// 保存
				targetFile = new File(picturePath + fileName);
				file.transferTo(targetFile);
				
				Picture p = new Picture();
				p.setPictureid(pictureid);
				p.setTablename(tablename);
				p.setPk(pk);
				p.setPath(fileName);
				pubService.addorUpdatePicture(p);
				responseJson.setMsg(Constant.ADD + "/" + Constant.UPDATE + Constant.PICTURE + Constant.OK);
				
				//属于其他业务连带动作
				if (GlobalValue.MODLE_JIANZHI.equals(modlename)) {
					Jianzhi jianzhi = new Jianzhi();
					jianzhi.setJianzhiid(pk);
					jianzhi.setUpdatetime(new Date());
					jianzhiService.updateByIdSelective(jianzhi);
				}
			} catch (Exception e) {
				log.error("/picture", e);
				responseJson.setMsg(Constant.ADD + "/" + Constant.UPDATE + Constant.PICTURE + Constant.ERROR);
				responseJson.setStatus(Constant.STATUS_ERROR_500);
			}
//		} else {
//			responseJson.setMsg(modlename + "模块不支持单图片上传,请用uploadMore方式" );
//			responseJson.setStatus(Constant.STATUS_ERROR_500);
//		}
		return responseJson;
	}
	
	/**
	 * 更新附件
	 * @return
	 */
	@ResponseBody
	@RequestMapping("uploadFujian/{modlename}/{pk:\\d+}")
	public Res uploadPubFujian(@RequestParam(value = "file") MultipartFile file,
			@PathVariable("modlename") String modlename, 
			@PathVariable("pk") int pk,
			@RequestParam(value = "type") String type,
			@RequestParam(value = "fujianid", required = false) Integer fujianid,
			@RequestParam(value = "token") String token) {
		Res responseJson = new Res();
		// 验证模块是否支持
		if (GlobalValue.MODLE_CHUANGYE.contains(modlename)) {
			try {
				String tablename = "t_" + modlename;
				// 文件名
				String fileName = null;
				if (fujianid == null) {
					// 业务条目唯一图片
					fileName = pk + "." + type;
				} else {
					// 业务条目多图片
					fileName = pk + "_" + fujianid + "." + type;
				}
				// 文件夹绝对路径
				String fujianPath = GlobalValue.PATH_FUJIAN + modlename + File.separator;
				File targetFile = new File(fujianPath);
				if (!targetFile.exists()) {
					targetFile.mkdirs();
				}
				// 保存
				targetFile = new File(fujianPath + fileName);
				file.transferTo(targetFile);
				
				Fujian p = new Fujian();
				p.setFujianid(fujianid);
				p.setTablename(tablename);
				p.setPk(pk);
				p.setPath(fileName);
				pubService.addorUpdateFujian(p);
				responseJson.setMsg(Constant.ADD + "/" + Constant.UPDATE + Constant.FUJIAN + Constant.OK);
			} catch (Exception e) {
				log.error("/fujian", e);
				responseJson.setMsg(Constant.ADD + "/" + Constant.UPDATE + Constant.FUJIAN + Constant.ERROR);
				responseJson.setStatus(Constant.STATUS_ERROR_500);
			}
		} else {
			responseJson.setMsg(modlename + "模块不支持上传更新" );
			responseJson.setStatus(Constant.STATUS_ERROR_500);
		}
		return responseJson;
	}
	
	/**
	 * 上传1对多的图片接口，可添加返回该1对多pictureid list,然后一个一个下载
	 * 增加添加图片接口
	 * @return
	 */
	@ResponseBody
	@RequestMapping("uploadMore/{modlename}/{pk:\\d+}")
	public Res uploadMorePubPicture(@RequestParam(value = "file") MultipartFile file,
			@PathVariable("modlename") String modlename, 
			@PathVariable("pk") int pk,
			@RequestParam(value = "type") String type,
			@RequestParam(value = "token") String token) {
		Res responseJson = new Res();
		// 验证模块是否支持
		if (GlobalValue.MODLE_PARTY.equals(modlename)) {
			Integer pictureid = null;
			try {
				//属于其他业务连带动作
				Party party = new Party();
				party.setPartyid(pk);
				party = partyService.findById(pk);
				if (party != null) {
					// #先保存表数据获取picture id
					Picture p = new Picture();
					p.setTablename("t_" + modlename);
					p.setPk(pk);
					p = pubService.addPicture(p);
					pictureid = p.getPictureid();
							
					//设置 path
					String fileName =  pk + "_" + pictureid + "." + type;;
					//#更新picture表
					p.setPath(fileName);
					pubService.addorUpdatePicture(p);
					//#保存图片到硬盘
					// 文件夹绝对路径
					String picturePath = GlobalValue.PATH_PICTURE + modlename + File.separator;
					File targetFile = new File(picturePath);
					if (!targetFile.exists()) {
						targetFile.mkdirs();
					}
					// 保存
					targetFile = new File(picturePath + fileName);
					file.transferTo(targetFile);
					
					//#更新相应业务
					String pcitures = party.getPictures();
					if (StringUtils.isEmpty(pcitures)) {
						pcitures = String.valueOf(pictureid);
					} else {
						pcitures += "," + pictureid;
					}
					party.setUpdatetime(new Date());
					party.setPictures(pcitures);
					partyService.updateByIdSelective(party);
				}
				
				responseJson.setMsg(Constant.ADD + Constant.PICTURE + Constant.OK);
			} catch (Exception e) {
				log.error("/uploadMore", e);
				if (pictureid != null) {
					Picture p = new Picture();
					p.setPictureid(pictureid);
					pubService.deletePicture(p);
				}
				responseJson.setMsg(Constant.ADD + Constant.PICTURE + Constant.ERROR);
				responseJson.setStatus(Constant.STATUS_ERROR_500);
			}
		} else if (GlobalValue.MODLE_BBS.equals(modlename)) {
				Integer pictureid = null;
				try {
					//属于其他业务连带动作
					Bbs bbs = new Bbs();
					bbs.setBbsid(pk);
					bbs = bbsService.findById(pk);
					if (bbs != null) {
						// #先保存表数据获取picture id
						Picture p = new Picture();
						p.setTablename("t_" + modlename);
						p.setPk(pk);
						p = pubService.addPicture(p);
						pictureid = p.getPictureid();
						
						//设置 path
						String fileName =  pk + "_" + pictureid + "." + type;;
						//#更新picture表
						p.setPath(fileName);
						pubService.addorUpdatePicture(p);
						//#保存图片到硬盘
						// 文件夹绝对路径
						String picturePath = GlobalValue.PATH_PICTURE + modlename + File.separator;
						File targetFile = new File(picturePath);
						if (!targetFile.exists()) {
							targetFile.mkdirs();
						}
						// 保存
						targetFile = new File(picturePath + fileName);
						file.transferTo(targetFile);
						
						//#更新相应业务
						String pcitures = bbs.getPictures();
						if (StringUtils.isEmpty(pcitures)) {
							pcitures = String.valueOf(pictureid);
						} else {
							pcitures += "," + pictureid;
						}
						bbs.setUpdatetime(new Date());
						bbs.setPictures(pcitures);
						bbsService.updateByIdSelective(bbs);
					}
					
					responseJson.setMsg(Constant.ADD + Constant.PICTURE + Constant.OK);
				} catch (Exception e) {
					log.error("/uploadMore", e);
					if (pictureid != null) {
						Picture p = new Picture();
						p.setPictureid(pictureid);
						pubService.deletePicture(p);
					}
					responseJson.setMsg(Constant.ADD + Constant.PICTURE + Constant.ERROR);
					responseJson.setStatus(Constant.STATUS_ERROR_500);
				}
		} else if (GlobalValue.MODLE_IDEA.equals(modlename)) {
			Integer pictureid = null;
			try {
				//属于其他业务连带动作
				Idea idea = new Idea();
				idea.setIdeaid(pk);
				idea = ideaService.findById(pk);
				if (idea != null) {
					// #先保存表数据获取picture id
					Picture p = new Picture();
					p.setTablename("t_" + modlename);
					p.setPk(pk);
					p = pubService.addPicture(p);
					pictureid = p.getPictureid();
					
					//设置 path
					String fileName =  pk + "_" + pictureid + "." + type;;
					//#更新picture表
					p.setPath(fileName);
					pubService.addorUpdatePicture(p);
					//#保存图片到硬盘
					// 文件夹绝对路径
					String picturePath = GlobalValue.PATH_PICTURE + modlename + File.separator;
					File targetFile = new File(picturePath);
					if (!targetFile.exists()) {
						targetFile.mkdirs();
					}
					// 保存
					targetFile = new File(picturePath + fileName);
					file.transferTo(targetFile);
					
					//#更新相应业务
					String pcitures = idea.getPictures();
					if (StringUtils.isEmpty(pcitures)) {
						pcitures = String.valueOf(pictureid);
					} else {
						pcitures += "," + pictureid;
					}
					idea.setUpdatetime(new Date());
					idea.setPictures(pcitures);
					ideaService.updateByIdSelective(idea);
				}
				
				responseJson.setMsg(Constant.ADD + Constant.PICTURE + Constant.OK);
			} catch (Exception e) {
				log.error("/uploadMore", e);
				if (pictureid != null) {
					Picture p = new Picture();
					p.setPictureid(pictureid);
					pubService.deletePicture(p);
				}
				responseJson.setMsg(Constant.ADD + Constant.PICTURE + Constant.ERROR);
				responseJson.setStatus(Constant.STATUS_ERROR_500);
			}
		} else if (GlobalValue.MODLE_ZHENGJI.equals(modlename)) {
			Integer pictureid = null;
			try {
				//属于其他业务连带动作
				Zhengji zhengji = new Zhengji();
				zhengji.setZhengjiid(pk);
				zhengji = zhengjiService.findById(pk);
				if (zhengji != null) {
					// #先保存表数据获取picture id
					Picture p = new Picture();
					p.setTablename("t_" + modlename);
					p.setPk(pk);
					p = pubService.addPicture(p);
					pictureid = p.getPictureid();
					
					//设置 path
					String fileName =  pk + "_" + pictureid + "." + type;;
					//#更新picture表
					p.setPath(fileName);
					pubService.addorUpdatePicture(p);
					//#保存图片到硬盘
					// 文件夹绝对路径
					String picturePath = GlobalValue.PATH_PICTURE + modlename + File.separator;
					File targetFile = new File(picturePath);
					if (!targetFile.exists()) {
						targetFile.mkdirs();
					}
					// 保存
					targetFile = new File(picturePath + fileName);
					file.transferTo(targetFile);
					
					//#更新相应业务
					String pcitures = zhengji.getPictures();
					if (StringUtils.isEmpty(pcitures)) {
						pcitures = String.valueOf(pictureid);
					} else {
						pcitures += "," + pictureid;
					}
					zhengji.setUpdatetime(new Date());
					zhengji.setPictures(pcitures);
					zhengjiService.updateByIdSelective(zhengji);
				}
				
				responseJson.setMsg(Constant.ADD + Constant.PICTURE + Constant.OK);
			} catch (Exception e) {
				log.error("/uploadMore", e);
				if (pictureid != null) {
					Picture p = new Picture();
					p.setPictureid(pictureid);
					pubService.deletePicture(p);
				}
				responseJson.setMsg(Constant.ADD + Constant.PICTURE + Constant.ERROR);
				responseJson.setStatus(Constant.STATUS_ERROR_500);
			}
		} else if (GlobalValue.MODLE_CHUANGYE.equals(modlename)) {
			Integer pictureid = null;
			try {
				//属于其他业务连带动作
				ChuangYe chuangye = new ChuangYe();
				chuangye.setChuangyeid(pk);
				chuangye = chuangyeService.findById(pk);
				if (chuangye != null) {
					// #先保存表数据获取picture id
					Picture p = new Picture();
					p.setTablename("t_" + modlename);
					p.setPk(pk);
					p = pubService.addPicture(p);
					pictureid = p.getPictureid();
					
					//设置 path
					String fileName =  pk + "_" + pictureid + "." + type;;
					//#更新picture表
					p.setPath(fileName);
					pubService.addorUpdatePicture(p);
					//#保存图片到硬盘
					// 文件夹绝对路径
					String picturePath = GlobalValue.PATH_PICTURE + modlename + File.separator;
					File targetFile = new File(picturePath);
					if (!targetFile.exists()) {
						targetFile.mkdirs();
					}
					// 保存
					targetFile = new File(picturePath + fileName);
					file.transferTo(targetFile);
					
					//#更新相应业务
					String pcitures = chuangye.getPictures();
					if (StringUtils.isEmpty(pcitures)) {
						pcitures = String.valueOf(pictureid);
					} else {
						pcitures += "," + pictureid;
					}
					chuangye.setUpdatetime(new Date());
					chuangye.setPictures(pcitures);
					chuangyeService.updateByIdSelective(chuangye);
				}
				
				responseJson.setMsg(Constant.ADD + Constant.PICTURE + Constant.OK);
			} catch (Exception e) {
				log.error("/uploadMore", e);
				if (pictureid != null) {
					Picture p = new Picture();
					p.setPictureid(pictureid);
					pubService.deletePicture(p);
				}
				responseJson.setMsg(Constant.ADD + Constant.PICTURE + Constant.ERROR);
				responseJson.setStatus(Constant.STATUS_ERROR_500);
			}
		} else {
			responseJson.setMsg(modlename + "模块不支持图片上传" );
			responseJson.setStatus(Constant.STATUS_ERROR_500);
		}
		return responseJson;
	}
	
	/**
	 * 上传1对多的附件接口，可添加返回该1对多fujianid list,然后一个一个下载
	 * 增加添加图片接口
	 * @return
	 */
	@ResponseBody
	@RequestMapping("uploadMoreFujian/{modlename}/{pk:\\d+}")
	public Res uploadMorePubFujian(@RequestParam(value = "file") MultipartFile file,
			@PathVariable("modlename") String modlename, 
			@PathVariable("pk") int pk,
			@RequestParam(value = "type") String type,
			@RequestParam(value = "token") String token) {
		Res responseJson = new Res();
		// 验证模块是否支持
		if (GlobalValue.MODLE_CHUANGYE.equals(modlename)) {
			Integer fujianid = null;
			try {
				//属于其他业务连带动作
				ChuangYe chuangye = new ChuangYe();
				chuangye.setChuangyeid(pk);
				chuangye = chuangyeService.findById(pk);
				if (chuangye != null) {
					// #先保存表数据获取fujian id
					Fujian p = new Fujian();
					p.setTablename("t_" + modlename);
					p.setPk(pk);
					p = pubService.addFujian(p);
					fujianid = p.getFujianid();
					
					//设置 path
					String fileName =  pk + "_" + fujianid + "." + type;;
					//#更新Fujian表
					p.setPath(fileName);
					pubService.addorUpdateFujian(p);
					//#保存图片到硬盘
					// 文件夹绝对路径
					String fujianPath = GlobalValue.PATH_FUJIAN + modlename + File.separator;
					File targetFile = new File(fujianPath);
					if (!targetFile.exists()) {
						targetFile.mkdirs();
					}
					// 保存
					targetFile = new File(fujianPath + fileName);
					file.transferTo(targetFile);
					
					//#更新相应业务
					String fujian = chuangye.getFujian();
					if (StringUtils.isEmpty(fujian)) {
						fujian = String.valueOf(fujianid);
					} else {
						fujian += "," + fujianid;
					}
					chuangye.setUpdatetime(new Date());
					chuangye.setFujian(fujian);
					chuangyeService.updateByIdSelective(chuangye);
				}
				
				responseJson.setMsg(Constant.ADD + Constant.FUJIAN + Constant.OK);
			} catch (Exception e) {
				log.error("/uploadMore", e);
				if (fujianid != null) {
					Fujian p = new Fujian();
					p.setFujianid(fujianid);
					pubService.deleteFujian(p);
				}
				responseJson.setMsg(Constant.ADD + Constant.FUJIAN + Constant.ERROR);
				responseJson.setStatus(Constant.STATUS_ERROR_500);
			}
		} else {
			responseJson.setMsg(modlename + "模块不支持附件上传" );
			responseJson.setStatus(Constant.STATUS_ERROR_500);
		}
		return responseJson;
	}
	
	/**
	 * 下载公共图片
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "download/{modlename}/{pk:\\d+}")
	public ModelAndView downloadPubPicture(@PathVariable("modlename") String modlename, 
			@PathVariable("pk") int pk,
			@RequestParam(value = "pictureid", required = false) Integer pictureid, 
			HttpServletResponse response)
			throws Exception {
		// 验证模块是否支持
		Picture p = null;
		if (pictureid == null) {
			// 单一模块图片逻辑
			if (GlobalValue.TABLE_SET.contains(modlename)) {
				String tablename = "t_" + modlename;
				p = new Picture();
				p.setTablename(tablename);
				p.setPk(pk);
				List<Picture> list = pubService.getPicture(p);
				if (list.size() > 0) {
					p = list.get(0);
				}
			} else {
				log.error(modlename + "模块不支持单图片下载");
				throw new Exception(modlename + "模块不支持单图片下载");
			}
		} else {
			p = pubService.getPicture(pictureid);
		}
		// 文件名
		String fileName = p.getPath();
		// 文件夹绝对路径
		String downLoadPath = GlobalValue.PATH_PICTURE + modlename + File.separator + fileName;
		//System.out.println(downLoadPath);
		this.downloadFile(response, fileName, downLoadPath);
		return null;
	}
	
	/**
	 * 删除公共图片
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "deletePicture/{modlename}/{pk:\\d+}")
	public Res deletePicture(@PathVariable("modlename") String modlename, 
			@PathVariable("pk") int pk,
			@RequestParam(value = "pictureid", required = false) Integer pictureid, 
			HttpServletResponse response)
					throws Exception {
		Res responseJson = new Res();
		//更新附带表
		if (GlobalValue.MODLE_USER.equals(modlename)) {
			//简历是图片字段 存的path
			User entity = userService.getUser(pk);
			entity.setPicture("");
			userService.updateUser(entity);
			responseJson.setMsg("删除图片成功" );
			return responseJson;
		} else if (GlobalValue.MODLE_JIANLI.equals(modlename)) {
			//简历是图片字段 存的path
			JianLi entity = jianLiService.getJianLi(pk);
			entity.setPicture("");
			jianLiService.updateJianLi(entity);
			responseJson.setMsg("删除图片成功" );
			return responseJson;
		} else if (GlobalValue.MODLE_JIANZHI.equals(modlename)) {
			//兼职是单图片模块
			Picture p = p = new Picture();;
			p.setTablename("t_" + GlobalValue.MODLE_JIANZHI);
			p.setPk(pk);
			pubService.deletePicture(p);
			responseJson.setMsg("删除图片成功");
			return responseJson;
		} else if (GlobalValue.MODLE_PARTY.equals(modlename)) {
			Party entity = partyService.findById(pk);
			String pictures = entity.getPictures();
			pictures = this.removePictureid(pictureid, pictures);
			entity.setPictures(pictures);
			partyService.updateById(entity);
		} else if (GlobalValue.MODLE_BBS.equals(modlename)) {
			Bbs entity = bbsService.findById(pk);
			String pictures = entity.getPictures();
			pictures = this.removePictureid(pictureid, pictures);
			entity.setPictures(pictures);
			bbsService.updateById(entity);
		} else if (GlobalValue.MODLE_REPLY.equals(modlename)) {
			Reply entity = replyService.findById(pk);
			String pictures = entity.getPicture();
			pictures = this.removePictureid(pictureid, pictures);
			entity.setPicture(pictures);
			replyService.updateById(entity);
		} else if (GlobalValue.MODLE_CHUANGYE.equals(modlename)) {
			ChuangYe entity = chuangyeService.findById(pk);
			String pictures = entity.getPictures();
			pictures = this.removePictureid(pictureid, pictures);
			entity.setPictures(pictures);
			chuangyeService.updateById(entity);
		} else if (GlobalValue.MODLE_IDEA.equals(modlename)) {
			Idea entity = ideaService.findById(pk);
			String pictures = entity.getPictures();
			pictures = this.removePictureid(pictureid, pictures);
			entity.setPictures(pictures);
			ideaService.updateById(entity);
		} else if (GlobalValue.MODLE_ZHENGJI.equals(modlename)) {
			Zhengji entity = zhengjiService.findById(pk);
			String pictures = entity.getPictures();
			pictures = this.removePictureid(pictureid, pictures);
			entity.setPictures(pictures);
			zhengjiService.updateById(entity);
		} else {
			responseJson.setMsg(modlename + "不支持图片删除" );
			responseJson.setStatus(Constant.STATUS_ERROR_500);
			return responseJson;
		}
		// 删除
		Picture p = p = new Picture();
		p.setPictureid(pictureid);
		pubService.deletePicture(p);
		responseJson.setMsg("删除图片成功");
		return responseJson;
	}

	/**
	 * 下载公共附件
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "downloadFujian/{modlename}/{pk:\\d+}")
	public ModelAndView downloadPubFujian(@PathVariable("modlename") String modlename, 
			@PathVariable("pk") int pk,
			@RequestParam(value = "fujianid", required = false) Integer fujianid, 
			HttpServletResponse response)
			throws Exception {
		// 验证模块是否支持
		Fujian p = null;
		if (fujianid == null) {
			// 单一模块图片逻辑
			if (GlobalValue.MODLE_CHUANGYE.contains(modlename)) {
				String tablename = "t_" + modlename;
				p = new Fujian();
				p.setTablename(tablename);
				p.setPk(pk);
				List<Fujian> list = pubService.getFujian(p);
				if (list.size() > 0) {
					p = list.get(0);
				}
			} else {
				log.error(modlename + "模块不支持附件下载");
				throw new Exception(modlename + "模块不支持附件下载");
			}
		} else {
			p = pubService.getFujian(fujianid);
		}
		// 文件名
		String fileName = p.getPath();
		// 文件夹绝对路径
		String downLoadPath = GlobalValue.PATH_FUJIAN + modlename + File.separator + fileName;
		//System.out.println(downLoadPath);
		this.downloadFile(response, fileName, downLoadPath);
		return null;
	}
	
	/**
	 * 删除公共附件
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "deleteFujian/{modlename}/{pk:\\d+}")
	public Res deleteFujian(@PathVariable("modlename") String modlename, 
			@PathVariable("pk") int pk,
			@RequestParam(value = "fujianid", required = false) Integer fujianid, 
			HttpServletResponse response)
					throws Exception {
		Res responseJson = new Res();
		//更新附带表
		if (GlobalValue.MODLE_CHUANGYE.equals(modlename)) {
			ChuangYe entity = chuangyeService.findById(pk);
			String fujians = entity.getFujian();
			fujians = this.removePictureid(fujianid, fujians);
			entity.setFujian(fujians);
			chuangyeService.updateById(entity);
		} else if (GlobalValue.MODLE_JIANLI.equals(modlename)) {
			JianLi entity = jianLiService.getJianLi(pk);
			entity.setFujian("");;
			jianLiService.updateJianLi(entity);
			responseJson.setMsg("删除附件成功" );
			return responseJson;
		} else {
			responseJson.setMsg(modlename + "不支持附件删除" );
			responseJson.setStatus(Constant.STATUS_ERROR_500);
			return responseJson;
		}
		// 删除
		Fujian p = p = new Fujian();;
		p.setFujianid(fujianid);
		pubService.deleteFujian(p);
		responseJson.setMsg("删除附件成功" );
		return responseJson;
	}
	
	/**
	 * 公共回复
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("addReply/{modlename}")
	public Res addReply(@PathVariable("modlename") String modlename, @RequestBody Reply reply) throws Exception {
		Res responseJson = new Res();
		reply.setTablename("t_" + modlename);
		try {
			reply = replyService.addReply(reply);
			if (reply != null) {
				if (GlobalValue.MODLE_BBS.contains(modlename)) {
					Bbs bbs = bbsService.findById(reply.getPk());
					Integer replyNum = bbs.getReplynumber();
					if(replyNum == null){
						replyNum = 1;
					} else {
						replyNum += 1;
					}
					bbs.setReplynumber(replyNum);
					bbsService.updateByIdSelective(bbs);
				}
				responseJson.setMsg(Constant.ADD + Constant.REPLY + Constant.OK);
			}
		} catch (Exception e) {
			log.error("/addReply", e);
			reply = null;
		}
		if (reply == null) {
			responseJson.setMsg(Constant.ADD + Constant.REPLY + Constant.ERROR);
			responseJson.setStatus(Constant.STATUS_ERROR_500);
		}
		responseJson.setReply(reply);
		return responseJson;
	}
	
	/**
	 * 获取公共的回复List
	 * @param bbsid
	 * @return
	 */
	@ResponseBody
	@RequestMapping("{modlename}/ReplyList")
	public Res replyList(@PathVariable("modlename") String modlename,
			@RequestParam(value = "pk") Integer pk, 
			@RequestParam(value = "leftJoinUser", required = false) Boolean leftJoinUser, 
			@RequestParam(value = "pageNo") Integer pageNo,
			@RequestParam(value = "pageSize", required = false) Integer pageSize
			) {
		Res responseJson = new Res();
		try {
			String tablename = "t_" + modlename;
			if (GlobalValue.MODLE_BBS.equals(modlename)) {
				ReplyExample example = new ReplyExample();
				Criteria c = example.createCriteria().andTablenameEqualTo(tablename);
				if (leftJoinUser != null && leftJoinUser) {
					example.setLeftJoinUser(leftJoinUser);
				}
				if (pk != null) {
					c.andPkEqualTo(pk);
				}
				Pagination p = new Pagination();
				p.setPageNo(pageNo);
				if (pageSize != null) {
					p.setPageSize(pageSize);
				}
				example.setOrderByClause("time asc");
				p = replyService.getReplyList(example, p);
				responseJson.setList(p.getList());
				responseJson.setMsg(Constant.SEARCH + Constant.REPLY + Constant.OK);
			} else {
				log.error(modlename + "模块不支持回复");
				throw new Exception(modlename + "模块不支持回复");
			}
		} catch (Exception e) {
			log.error("/myApplyList", e);
			responseJson.setMsg(Constant.SEARCH + Constant.REPLY + Constant.ERROR);
			responseJson.setStatus(Constant.STATUS_ERROR_500);
		}
		return responseJson;
	}
	
	// ---------------------------共用接口---------------------------end
	
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
	
	private static String removePictureid(Integer pictureid, String pictures) {
		if (pictures != null) {
			ArrayList<String> list = new ArrayList<String>();
			String[] arr = pictures.split(",");
			for (String string : arr) {
				if (!string.equals(String.valueOf(pictureid))) {
					list.add(string);
				}
			}
			pictures = "";
			for (int i = 0; i < list.size(); i++) {
				if (i == list.size() - 1) {
					pictures += list.get(i);
				} else {
					pictures += list.get(i) + ",";

				}
			}
			if (StringUtils.isEmpty(pictures)) {
				pictures = null;
			}
		}
		return pictures;
	}
}
