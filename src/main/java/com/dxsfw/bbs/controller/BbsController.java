package com.dxsfw.bbs.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dxsfw.bbs.model.Bbs;
import com.dxsfw.bbs.model.BbsShengqing;
import com.dxsfw.bbs.service.BbsService;
import com.dxsfw.common.base.BaseController;
import com.dxsfw.common.base.Res;
import com.dxsfw.common.constants.Constant;
import com.dxsfw.common.page.Pagination;
import com.dxsfw.pub.service.ReplyService;

@Controller
@RequestMapping("/bbs")
public class BbsController extends BaseController {
	private static Logger log = LoggerFactory.getLogger(BbsController.class);

	@Autowired
	private BbsService bbsService;

	@Autowired
	private ReplyService replyService;
	
	/**
	 * 新增交流/授课
	 * @return
	 */
	@ResponseBody
	@RequestMapping("add")
	public Res add(@RequestBody Bbs bbs) {
		Res responseJson = new Res();
		try {
			Date now = new Date();
			bbs.setCreatetime(now);
			bbs.setUpdatetime(now);
			bbs = bbsService.addBbs(bbs);
			if (bbs != null) {
				responseJson.setMsg(Constant.ADD + Constant.BBS + Constant.OK);
			}
		} catch (Exception e) {
			log.error("/add", e);
			bbs = null;
		}
		if (bbs == null) {
			responseJson.setMsg(Constant.ADD + Constant.BBS + Constant.ERROR);
			responseJson.setStatus(Constant.STATUS_ERROR_500);
		}
		responseJson.setBbs(bbs);
		return responseJson;
	}
	
	/**
	 * 删除交流/授课
	 * @return
	 */
	@ResponseBody
	@RequestMapping("delete/{id:\\d+}")
	public Res delete(@PathVariable("id") int id) {
		Res responseJson = new Res();
		try {
			bbsService.deleteById(id);
			replyService.deleteReplys("t_" + Constant.BBS, id);
			//数据库自动删除级联关系
			
			responseJson.setMsg(Constant.DELETE + Constant.BBS + Constant.OK);
		} catch (Exception e) {
			log.error("/delete", e);
			responseJson.setMsg(Constant.DELETE + Constant.BBS + Constant.ERROR);
			responseJson.setStatus(Constant.STATUS_ERROR_500);
		}
		return responseJson;
	}
	
	/**
	 * 更新交流/授课
	 * @return
	 */
	@ResponseBody
	@RequestMapping("update")
	public Res update(@RequestBody Bbs bbs) {
		Res responseJson = new Res();
		try {
			Date now = new Date();
			bbs.setUpdatetime(now);
			int r = bbsService.updateByIdSelective(bbs);
			if (r > 0) {
				bbs = bbsService.findById(bbs.getBbsid());
				responseJson.setMsg(Constant.UPDATE + Constant.BBS + Constant.OK);
			}
		} catch (Exception e) {
			log.error("/update", e);
			bbs = null;
		}
		if (bbs == null) {
			responseJson.setMsg(Constant.UPDATE + Constant.BBS + Constant.ERROR);
			responseJson.setStatus(Constant.STATUS_ERROR_500);
		}
		responseJson.setBbs(bbs);
		return responseJson;
	}
	
	/**
	 * 获取交流/授课
	 * @return
	 */
	@ResponseBody
	@RequestMapping("get/{id:\\d+}")
	public Res get(@PathVariable("id") int id) {
		Res responseJson = new Res();
		Bbs bbs = null;
		try {
			bbs = bbsService.findById(id);
			// 更新点击量
			Bbs entity = new Bbs();
			entity.setBbsid(id);
			if (bbs.getClicknumber() == null) {
				entity.setClicknumber(1);
			} else {
				entity.setClicknumber(bbs.getClicknumber() + 1);
			}
			bbsService.updateByIdSelective(entity);
			responseJson.setMsg(Constant.SELECT + Constant.BBS + Constant.OK);
		} catch (Exception e) {
			log.error("/get", e);
			bbs = null;
		}
		if (bbs == null) {
			responseJson.setMsg(Constant.SELECT + Constant.BBS + Constant.ERROR);
			responseJson.setStatus(Constant.STATUS_ERROR_500);
		}
		responseJson.setBbs(bbs);
		return responseJson;
	}
	
	/**
	 * 报名申请交流/授课
	 * @param bbsid
	 * @param publishuserid
	 * @param shengqinguserid
	 * @param jianliid
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("applyClass")
	public Res applyClass(@RequestParam(value = "bbsid") Integer bbsid, 
			@RequestParam(value = "publishuserid") Integer publishuserid,
			@RequestParam(value = "shengqinguserid") Integer shengqinguserid,
			@RequestParam(value = "message", required = false) String message
			) {
		Res responseJson = new Res();
		BbsShengqing record = new BbsShengqing();
		record.setBbsid(bbsid);
		record.setPublishuserid(publishuserid);
		record.setShengqinguserid(shengqinguserid);
		record.setTime(new Date());
		record.setMessage(message);
		boolean flag = bbsService.applyClass(record);
		if (flag) {
			// 申请成功
			responseJson.setMsg(Constant.BBSSHENGQING + Constant.OK);
		} else {
			responseJson.setMsg("您已经报名申请过此授课或授课名额已经报满");
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
			@RequestParam(value = "type", required = false) String type,
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
			p = bbsService.search(keyword, type, p);
			responseJson.setList(p.getList());
			responseJson.setMsg(Constant.SEARCH + Constant.BBS + Constant.OK);
		} catch (Exception e) {
			log.error("/search", e);
			responseJson.setMsg(Constant.SEARCH + Constant.BBS + Constant.ERROR);
			responseJson.setStatus(Constant.STATUS_ERROR_500);
		}
		return responseJson;
	}
	
	/**
	 * 查看我发布的交流/授课列表List
	 * @param userid
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping("myList")
	public Res myList(@RequestParam(value = "userid") Integer userid, 
			@RequestParam(value = "type", required = false) String type,
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
			p = bbsService.myList(userid, type, p);
			responseJson.setList(p.getList());
			responseJson.setMsg(Constant.SEARCH + Constant.BBS + Constant.OK);
		} catch (Exception e) {
			log.error("/myList", e);
			responseJson.setMsg(Constant.SEARCH + Constant.BBS + Constant.ERROR);
			responseJson.setStatus(Constant.STATUS_ERROR_500);
		}
		return responseJson;
	}
	
	/**
	 * 查看我申请的授课列表List
	 * @param userid
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping("myApplyList")
	public Res myApplyList(@RequestParam(value = "userid") Integer userid, 
			@RequestParam(value = "type", required = false) String type,
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
			p = bbsService.myApplyList(userid, type, p);
			responseJson.setList(p.getList());
			responseJson.setMsg(Constant.SEARCH + Constant.BBS + Constant.OK);
		} catch (Exception e) {
			log.error("/myApplyList", e);
			responseJson.setMsg(Constant.SEARCH + Constant.BBS + Constant.ERROR);
			responseJson.setStatus(Constant.STATUS_ERROR_500);
		}
		return responseJson;
	}
	
	/**
	 * 查看某个授课的报名人列表
	 * @param bbsid
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping("applyUserList")
	public Res applyUserList(@RequestParam(value = "bbsid") Integer bbsid, 
			@RequestParam(value = "pageNo") Integer pageNo,
			@RequestParam(value = "pageSize", required = false) Integer pageSize,
			@RequestParam(value = "includePublishUser", required = false) Boolean includePublishUser
			) {
		Res responseJson = new Res();
		try {
			Pagination p = new Pagination();
			p.setPageNo(pageNo);
			if (pageSize != null) {
				p.setPageSize(pageSize);
			}
			//
			p = bbsService.applyUserList(bbsid, includePublishUser, p);
			responseJson.setList(p.getList());
			responseJson.setMsg(Constant.SEARCH + Constant.BBS + Constant.OK);
		} catch (Exception e) {
			log.error("/applyUserList", e);
			responseJson.setMsg(Constant.SEARCH + Constant.BBS + Constant.ERROR);
			responseJson.setStatus(Constant.STATUS_ERROR_500);
		}
		return responseJson;
	}
	
	/**
	 * 查看我回复的交流List
	 * @param userid
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping("myReplyList")
	public Res myReplyList(@RequestParam(value = "userid") Integer userid, 
			@RequestParam(value = "type", required = false) String type,
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
			p = bbsService.myReplyList(userid, type, p);
			responseJson.setList(p.getList());
			responseJson.setMsg(Constant.SEARCH + Constant.BBS + Constant.OK);
		} catch (Exception e) {
			log.error("/myApplyList", e);
			responseJson.setMsg(Constant.SEARCH + Constant.BBS + Constant.ERROR);
			responseJson.setStatus(Constant.STATUS_ERROR_500);
		}
		return responseJson;
	}
}
