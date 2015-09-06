package com.dxsfw.jianzhi.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dxsfw.common.base.BaseController;
import com.dxsfw.common.base.Res;
import com.dxsfw.common.constants.Constant;
import com.dxsfw.common.constants.GlobalValue;
import com.dxsfw.common.page.Pagination;
import com.dxsfw.jianzhi.model.Jianzhi;
import com.dxsfw.jianzhi.model.JianzhiShengqing;
import com.dxsfw.jianzhi.model.JianzhiShengqingExample;
import com.dxsfw.jianzhi.service.JianzhiService;
import com.dxsfw.pub.model.JianLiExample;
import com.dxsfw.pub.model.User;
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
			log.error("/add", e);
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
			//删除级联关系
			
			
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
	
	/**
	 * 报名申请兼职
	 * @param jianzhiid
	 * @param publishuserid
	 * @param shengqinguserid
	 * @param jianliid
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("applyJianzhi")
	public Res applyJianzhi(@RequestParam(value = "jianzhiid") Integer jianzhiid, 
			@RequestParam(value = "publishuserid") Integer publishuserid,
			@RequestParam(value = "shengqinguserid") Integer shengqinguserid,
			@RequestParam(value = "jianliid") Integer jianliid
			) {
		Res responseJson = new Res();
		JianzhiShengqing record = new JianzhiShengqing();
		record.setJianzhiid(jianzhiid);
		record.setPublishuserid(publishuserid);
		record.setShengqinguserid(shengqinguserid);
		record.setJianliid(jianliid);
		record.setTime(new Date());
		boolean flag = jianzhiService.applyJianzhi(record);
		if (flag) {
			// 申请成功
			responseJson.setMsg(Constant.JIANZHISHENGQING + Constant.OK);
		} else {
			responseJson.setMsg("您已经报名申请过此兼职");
			responseJson.setStatus(Constant.STATUS_ERROR_500);
		}
		return responseJson;
	}
	
	/**
	 * @param keyword
	 * @param pageNo
	 * @param pageNumShown
	 * @param jianliid
	 * @return
	 */
	@ResponseBody
	@RequestMapping("search")
	public Res search(@RequestParam(value = "keyword", required = false) String keyword, 
			@RequestParam(value = "pageNo") Integer pageNo,
			@RequestParam(value = "pageSize", required = false) Integer pageSize
			) {
		Res responseJson = new Res();
		try {
			Pagination p = new Pagination();
			p.setPageNo(pageNo);
			if (pageSize != null) {
				p.setPageSize(pageSize);
			}
			p = jianzhiService.search(keyword, p);
			responseJson.setList(p.getList());
			responseJson.setMsg(Constant.SEARCH + Constant.JIANZHI + Constant.OK);
		} catch (Exception e) {
			log.error("/search", e);
			responseJson.setMsg(Constant.SEARCH + Constant.JIANZHI + Constant.ERROR);
			responseJson.setStatus(Constant.STATUS_ERROR_500);
		}
		return responseJson;
	}
	
	/**
	 * 查看我发布的兼职列表List
	 * @param userid
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping("myList")
	public Res myList(@RequestParam(value = "userid") Integer userid, 
			@RequestParam(value = "pageNo") Integer pageNo,
			@RequestParam(value = "pageSize", required = false) Integer pageSize
			) {
		Res responseJson = new Res();
		try {
			Pagination p = new Pagination();
			p.setPageNo(pageNo);
			if (pageSize != null) {
				p.setPageSize(pageSize);
			}
			p = jianzhiService.myList(userid, p);
			responseJson.setList(p.getList());
			responseJson.setMsg(Constant.SEARCH + Constant.JIANZHI + Constant.OK);
		} catch (Exception e) {
			log.error("/myList", e);
			responseJson.setMsg(Constant.SEARCH + Constant.JIANZHI + Constant.ERROR);
			responseJson.setStatus(Constant.STATUS_ERROR_500);
		}
		return responseJson;
	}
	
	/**
	 * 查看我申请的兼职列表List
	 * @param userid
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping("myApplyList")
	public Res myApplyList(@RequestParam(value = "userid") Integer userid, 
			@RequestParam(value = "pageNo") Integer pageNo,
			@RequestParam(value = "pageSize", required = false) Integer pageSize
			) {
		Res responseJson = new Res();
		try {
			Pagination p = new Pagination();
			p.setPageNo(pageNo);
			if (pageSize != null) {
				p.setPageSize(pageSize);
			}
			p = jianzhiService.myApplyList(userid, p);
			responseJson.setList(p.getList());
			responseJson.setMsg(Constant.SEARCH + Constant.JIANZHI + Constant.OK);
		} catch (Exception e) {
			log.error("/myApplyList", e);
			responseJson.setMsg(Constant.SEARCH + Constant.JIANZHI + Constant.ERROR);
			responseJson.setStatus(Constant.STATUS_ERROR_500);
		}
		return responseJson;
	}
	
	/**
	 * 查看某个兼职的报名简历列表
	 * @param userid
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping("applyJianLiList")
	public Res applyJianLiList(@RequestParam(value = "jianzhiid") Integer jianzhiid, 
			@RequestParam(value = "pageNo") Integer pageNo,
			@RequestParam(value = "pageSize", required = false) Integer pageSize
			) {
		Res responseJson = new Res();
		try {
			Pagination p = new Pagination();
			p.setPageNo(pageNo);
			if (pageSize != null) {
				p.setPageSize(pageSize);
			}
			//
//			JianzhiShengqingExample example1 = new JianzhiShengqingExample();
//			example1.createCriteria().andJianzhiidEqualTo(jianzhiid);
//			example1.setOrderByClause("time desc");
//			List<JianzhiShengqing> idList = null;//jianzhiShengqingDao.selectByExample(example1);
//			if (idList.size() > 0) {
//				// 子表查询出所有的兼职申请 的 简历ids
//				List<Integer> ids = new ArrayList<Integer>();
//				for (JianzhiShengqing jianzhiShengqing : idList) {
//					ids.add(jianzhiShengqing.getJianliid());
//				}
//				// 再查主表 简历表
//				JianLiExample example = new JianLiExample();
//				example.createCriteria().andJianliidIn(ids);
//				p =  jianLiService.queryByExample(example, p);
//			}
			//
			p = jianzhiService.applyJianLiList(jianzhiid, p);
			responseJson.setList(p.getList());
			responseJson.setMsg(Constant.SEARCH + Constant.JIANZHI + Constant.OK);
		} catch (Exception e) {
			log.error("/applyJianLiList", e);
			responseJson.setMsg(Constant.SEARCH + Constant.JIANZHI + Constant.ERROR);
			responseJson.setStatus(Constant.STATUS_ERROR_500);
		}
		return responseJson;
	}
}
