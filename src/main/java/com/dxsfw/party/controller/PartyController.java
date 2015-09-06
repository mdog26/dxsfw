package com.dxsfw.party.controller;

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

import com.dxsfw.common.base.BaseController;
import com.dxsfw.common.base.Res;
import com.dxsfw.common.constants.Constant;
import com.dxsfw.common.page.Pagination;
import com.dxsfw.party.model.Party;
import com.dxsfw.party.model.PartyShengqing;
import com.dxsfw.party.service.PartyService;

@Controller
@RequestMapping("/party")
public class PartyController extends BaseController {
	private static Logger log = LoggerFactory.getLogger(PartyController.class);

	@Autowired
	private PartyService partyService;

	/**
	 * 新增活动
	 * @return
	 */
	@ResponseBody
	@RequestMapping("add")
	public Res add(@RequestBody Party party) {
		Res responseJson = new Res();
		try {
			Date now = new Date();
			party.setCreatetime(now);
			party.setUpdatetime(now);
			party = partyService.addParty(party);
			if (party != null) {
				responseJson.setMsg(Constant.ADD + Constant.PARTY + Constant.OK);
			}
		} catch (Exception e) {
			log.error("/add", e);
			party = null;
		}
		if (party == null) {
			responseJson.setMsg(Constant.ADD + Constant.PARTY + Constant.ERROR);
			responseJson.setStatus(Constant.STATUS_ERROR_500);
		}
		responseJson.setParty(party);
		return responseJson;
	}
	
	/**
	 * 删除活动
	 * @return
	 */
	@ResponseBody
	@RequestMapping("delete/{id:\\d+}")
	public Res delete(@PathVariable("id") int id) {
		Res responseJson = new Res();
		try {
			partyService.deleteById(id);
			//数据库自动删除级联关系
			
			responseJson.setMsg(Constant.DELETE + Constant.PARTY + Constant.OK);
		} catch (Exception e) {
			log.error("/delete", e);
			responseJson.setMsg(Constant.DELETE + Constant.PARTY + Constant.ERROR);
			responseJson.setStatus(Constant.STATUS_ERROR_500);
		}
		return responseJson;
	}
	
	/**
	 * 更新活动
	 * @return
	 */
	@ResponseBody
	@RequestMapping("update")
	public Res update(@RequestBody Party party) {
		Res responseJson = new Res();
		try {
			Date now = new Date();
			party.setUpdatetime(now);
			int r = partyService.updateByIdSelective(party);
			if (r > 0) {
				party = partyService.findById(party.getPartyid());
				responseJson.setMsg(Constant.UPDATE + Constant.PARTY + Constant.OK);
			}
		} catch (Exception e) {
			log.error("/update", e);
			party = null;
		}
		if (party == null) {
			responseJson.setMsg(Constant.UPDATE + Constant.PARTY + Constant.ERROR);
			responseJson.setStatus(Constant.STATUS_ERROR_500);
		}
		responseJson.setParty(party);
		return responseJson;
	}
	
	/**
	 * 获取活动
	 * @return
	 */
	@ResponseBody
	@RequestMapping("get/{id:\\d+}")
	public Res get(@PathVariable("id") int id) {
		Res responseJson = new Res();
		Party party = null;
		try {
			party = partyService.findById(id);
			responseJson.setMsg(Constant.SELECT + Constant.PARTY + Constant.OK);
		} catch (Exception e) {
			log.error("/get", e);
			party = null;
		}
		if (party == null) {
			responseJson.setMsg(Constant.SELECT + Constant.PARTY + Constant.ERROR);
			responseJson.setStatus(Constant.STATUS_ERROR_500);
		}
		responseJson.setParty(party);
		return responseJson;
	}
	
	/**
	 * 报名申请活动
	 * @param partyid
	 * @param publishuserid
	 * @param shengqinguserid
	 * @param jianliid
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("applyParty")
	public Res applyParty(@RequestParam(value = "partyid") Integer partyid, 
			@RequestParam(value = "publishuserid") Integer publishuserid,
			@RequestParam(value = "shengqinguserid") Integer shengqinguserid
			) {
		Res responseJson = new Res();
		PartyShengqing record = new PartyShengqing();
		record.setPartyid(partyid);
		record.setPublishuserid(publishuserid);
		record.setShengqinguserid(shengqinguserid);
		record.setTime(new Date());
		boolean flag = partyService.applyParty(record);
		if (flag) {
			// 申请成功
			responseJson.setMsg(Constant.PARTYSHENGQING + Constant.OK);
		} else {
			responseJson.setMsg("您已经报名申请过此活动/活动已经报满");
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
			p = partyService.search(keyword, p);
			responseJson.setList(p.getList());
			responseJson.setMsg(Constant.SEARCH + Constant.PARTY + Constant.OK);
		} catch (Exception e) {
			log.error("/search", e);
			responseJson.setMsg(Constant.SEARCH + Constant.PARTY + Constant.ERROR);
			responseJson.setStatus(Constant.STATUS_ERROR_500);
		}
		return responseJson;
	}
	
	/**
	 * 查看我发布的活动列表List
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
			p = partyService.myList(userid, p);
			responseJson.setList(p.getList());
			responseJson.setMsg(Constant.SEARCH + Constant.PARTY + Constant.OK);
		} catch (Exception e) {
			log.error("/myList", e);
			responseJson.setMsg(Constant.SEARCH + Constant.PARTY + Constant.ERROR);
			responseJson.setStatus(Constant.STATUS_ERROR_500);
		}
		return responseJson;
	}
	
	/**
	 * 查看我申请的活动列表List
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
			p = partyService.myApplyList(userid, p);
			responseJson.setList(p.getList());
			responseJson.setMsg(Constant.SEARCH + Constant.PARTY + Constant.OK);
		} catch (Exception e) {
			log.error("/myApplyList", e);
			responseJson.setMsg(Constant.SEARCH + Constant.PARTY + Constant.ERROR);
			responseJson.setStatus(Constant.STATUS_ERROR_500);
		}
		return responseJson;
	}
	
	/**
	 * 查看某个活动的报名人列表
	 * @param partyid
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping("applyUserList")
	public Res applyUserList(@RequestParam(value = "partyid") Integer partyid, 
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
			p = partyService.applyUserList(partyid, includePublishUser, p);
			responseJson.setList(p.getList());
			responseJson.setMsg(Constant.SEARCH + Constant.PARTY + Constant.OK);
		} catch (Exception e) {
			log.error("/applyUserList", e);
			responseJson.setMsg(Constant.SEARCH + Constant.PARTY + Constant.ERROR);
			responseJson.setStatus(Constant.STATUS_ERROR_500);
		}
		return responseJson;
	}
}
