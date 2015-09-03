package com.dxsfw.jianzhi.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dxsfw.common.base.BaseController;
import com.dxsfw.common.base.Res;
import com.dxsfw.common.constants.Constant;
import com.dxsfw.jianzhi.model.Jianzhi;
import com.dxsfw.jianzhi.service.JianzhiService;
import com.dxsfw.pub.service.JianLiService;
import com.dxsfw.pub.service.UserService;

@Controller
@RequestMapping("/jianzhi")
public class JianzhiController extends BaseController {
	private static Logger log = LoggerFactory.getLogger(JianzhiController.class);

//	@Autowired
//	private UserService userService;
//	@Autowired
//	private JianLiService jianLiService;
	@Autowired
	private JianzhiService jianzhiService;

	/**
	 * 新增兼职
	 * @return
	 */
	@ResponseBody
	@RequestMapping("add")
	public Res add(@RequestBody Jianzhi jianzhi) {
		Res responseJson = new Res();
		try {
			Date now = new Date();
			jianzhi.setCreatetime(now);
			jianzhi.setUpdatetime(now);
			jianzhi = jianzhiService.addJianzhi(jianzhi);
			if (jianzhi != null) {
				responseJson.setMsg(Constant.ADD + Constant.JIANZHI + Constant.OK);
			}
		} catch (Exception e) {
			log.error("/addJianLi", e);
			jianzhi = null;
		}
		if (jianzhi == null) {
			responseJson.setMsg(Constant.ADD + Constant.JIANZHI + Constant.ERROR);
			responseJson.setStatus(Constant.STATUS_ERROR_500);
		}
		responseJson.setJianzhi(jianzhi);
		return responseJson;
	}
	
	/**
	 * 删除兼职
	 * @return
	 */
	@ResponseBody
	@RequestMapping("delete/{id:\\d+}")
	public Res delete(@PathVariable("id") int id) {
		Res responseJson = new Res();
		try {
			jianzhiService.deleteById(id);
			responseJson.setMsg(Constant.DELETE + Constant.JIANZHI + Constant.OK);
		} catch (Exception e) {
			log.error("/delete", e);
			responseJson.setMsg(Constant.DELETE + Constant.JIANZHI + Constant.ERROR);
			responseJson.setStatus(Constant.STATUS_ERROR_500);
		}
		return responseJson;
	}
	
	/**
	 * 更新兼职
	 * @return
	 */
	@ResponseBody
	@RequestMapping("update")
	public Res update(@RequestBody Jianzhi jianzhi) {
		Res responseJson = new Res();
		try {
			Date now = new Date();
			jianzhi.setUpdatetime(now);
			int r = jianzhiService.updateByIdSelective(jianzhi);
			if (r > 0) {
				jianzhi = jianzhiService.findById(jianzhi.getJianzhiid());
				responseJson.setMsg(Constant.UPDATE + Constant.JIANZHI + Constant.OK);
			}
		} catch (Exception e) {
			log.error("/update", e);
			jianzhi = null;
		}
		if (jianzhi == null) {
			responseJson.setMsg(Constant.UPDATE + Constant.JIANZHI + Constant.ERROR);
			responseJson.setStatus(Constant.STATUS_ERROR_500);
		}
		responseJson.setJianzhi(jianzhi);
		return responseJson;
	}
	
	/**
	 * 获取兼职
	 * @return
	 */
	@ResponseBody
	@RequestMapping("get/{id:\\d+}")
	public Res get(@PathVariable("id") int id) {
		Res responseJson = new Res();
		Jianzhi jianzhi = null;
		try {
			jianzhi = jianzhiService.findById(id);
			responseJson.setMsg(Constant.SELECT + Constant.JIANZHI + Constant.OK);
		} catch (Exception e) {
			log.error("/get", e);
			jianzhi = null;
		}
		if (jianzhi == null) {
			responseJson.setMsg(Constant.SELECT + Constant.JIANZHI + Constant.ERROR);
			responseJson.setStatus(Constant.STATUS_ERROR_500);
		}
		responseJson.setJianzhi(jianzhi);
		return responseJson;
	}
}
