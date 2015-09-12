package com.dxsfw.idea.controller;

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
import com.dxsfw.idea.model.Idea;
import com.dxsfw.idea.model.Zhengji;
import com.dxsfw.idea.service.IdeaService;
import com.dxsfw.idea.service.ZhengjiService;

@Controller
@RequestMapping("/idea")
public class IdeaController extends BaseController {
	private static Logger log = LoggerFactory.getLogger(IdeaController.class);

	@Autowired
	private IdeaService ideaService;
	
	@Autowired
	private ZhengjiService zhengjiService;
	
	/**
	 * 新增创意
	 * @return
	 */
	@ResponseBody
	@RequestMapping("add")
	public Res add(@RequestBody Idea idea) {
		Res responseJson = new Res();
		try {
			Date now = new Date();
			idea.setCreatetime(now);
			idea.setUpdatetime(now);
			idea = ideaService.addIdea(idea);
			if (idea != null) {
				responseJson.setMsg(Constant.ADD + Constant.IDEA + Constant.OK);
			}
		} catch (Exception e) {
			log.error("/add", e);
			idea = null;
		}
		if (idea == null) {
			responseJson.setMsg(Constant.ADD + Constant.IDEA + Constant.ERROR);
			responseJson.setStatus(Constant.STATUS_ERROR_500);
		}
		responseJson.setIdea(idea);
		return responseJson;
	}
	
	/**
	 * 删除创意
	 * @return
	 */
	@ResponseBody
	@RequestMapping("delete/{id:\\d+}")
	public Res delete(@PathVariable("id") int id) {
		Res responseJson = new Res();
		try {
			ideaService.deleteById(id);
			//数据库自动删除级联关系
			
			responseJson.setMsg(Constant.DELETE + Constant.IDEA + Constant.OK);
		} catch (Exception e) {
			log.error("/delete", e);
			responseJson.setMsg(Constant.DELETE + Constant.IDEA + Constant.ERROR);
			responseJson.setStatus(Constant.STATUS_ERROR_500);
		}
		return responseJson;
	}
	
	/**
	 * 更新创意
	 * @return
	 */
	@ResponseBody
	@RequestMapping("update")
	public Res update(@RequestBody Idea idea) {
		Res responseJson = new Res();
		try {
			Date now = new Date();
			idea.setUpdatetime(now);
			int r = ideaService.updateByIdSelective(idea);
			if (r > 0) {
				idea = ideaService.findById(idea.getIdeaid());
				responseJson.setMsg(Constant.UPDATE + Constant.IDEA + Constant.OK);
			}
		} catch (Exception e) {
			log.error("/update", e);
			idea = null;
		}
		if (idea == null) {
			responseJson.setMsg(Constant.UPDATE + Constant.IDEA + Constant.ERROR);
			responseJson.setStatus(Constant.STATUS_ERROR_500);
		}
		responseJson.setIdea(idea);
		return responseJson;
	}
	
	/**
	 * 获取创意
	 * @return
	 */
	@ResponseBody
	@RequestMapping("get/{id:\\d+}")
	public Res get(@PathVariable("id") int id) {
		Res responseJson = new Res();
		Idea idea = null;
		try {
			idea = ideaService.findById(id);
			responseJson.setMsg(Constant.SELECT + Constant.IDEA + Constant.OK);
		} catch (Exception e) {
			log.error("/get", e);
			idea = null;
		}
		if (idea == null) {
			responseJson.setMsg(Constant.SELECT + Constant.IDEA + Constant.ERROR);
			responseJson.setStatus(Constant.STATUS_ERROR_500);
		}
		responseJson.setIdea(idea);
		return responseJson;
	}
	
	/**
	 * @param keyword
	 * @param pageNo
	 * @param pageNumShown
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
			p = ideaService.search(keyword, p);
			responseJson.setList(p.getList());
			responseJson.setMsg(Constant.SEARCH + Constant.IDEA + Constant.OK);
		} catch (Exception e) {
			log.error("/search", e);
			responseJson.setMsg(Constant.SEARCH + Constant.IDEA + Constant.ERROR);
			responseJson.setStatus(Constant.STATUS_ERROR_500);
		}
		return responseJson;
	}
	
	/**
	 * 查看我发布的创意列表List
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
			p = ideaService.myList(userid, p);
			responseJson.setList(p.getList());
			responseJson.setMsg(Constant.SEARCH + Constant.IDEA + Constant.OK);
		} catch (Exception e) {
			log.error("/myList", e);
			responseJson.setMsg(Constant.SEARCH + Constant.IDEA + Constant.ERROR);
			responseJson.setStatus(Constant.STATUS_ERROR_500);
		}
		return responseJson;
	}
	
	/**
	 * 模拟支付成功
	 * @return
	 */
	@ResponseBody
	@RequestMapping("pay/{id:\\d+}")
	public Res pay(@PathVariable("id") int id, @RequestParam(value = "userid") Integer userid) {
		Res responseJson = new Res();
		try {
			Idea entity = ideaService.findById(id);
			entity.setPayuserid(userid);
			entity.setSellstatus("Y");
			ideaService.updateByIdSelective(entity);
			
			//如果有征集，同步更新征集表
			if (entity.getZhengjiid() != null && entity.getZhengjiid() > 0) {
				Zhengji zhengji = zhengjiService.findById(entity.getZhengjiid());
				zhengji.setSelluserid(entity.getUserid());
				zhengji.setSellstatus("Y");
				zhengjiService.updateByIdSelective(zhengji);
			}
			responseJson.setMsg("支付成功");
		} catch (Exception e) {
			log.error("/pay", e);
			responseJson.setMsg("支付失败");
			responseJson.setStatus(Constant.STATUS_ERROR_500);
		}
		return responseJson;
	}
	
	/**
	 * 评价
	 * @return
	 */
	@ResponseBody
	@RequestMapping("pingjia/{id:\\d+}")
	public Res pingjia(@PathVariable("id") int id, @RequestParam(value = "pingjia") String pingjia,
			@RequestParam(value = "pingjiafenshu") Integer pingjiafenshu) {
		Res responseJson = new Res();
		try {
			Idea entity = ideaService.findById(id);
			entity.setPingjia(pingjia);
			entity.setPingjiafenshu(pingjiafenshu);
			ideaService.updateByIdSelective(entity);
			responseJson.setMsg("评价成功");
		} catch (Exception e) {
			log.error("/pay", e);
			responseJson.setMsg("评价失败");
			responseJson.setStatus(Constant.STATUS_ERROR_500);
		}
		return responseJson;
	}
}
