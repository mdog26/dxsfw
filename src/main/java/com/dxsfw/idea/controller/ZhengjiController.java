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
import com.dxsfw.idea.model.Zhengji;
import com.dxsfw.idea.service.IdeaService;
import com.dxsfw.idea.service.ZhengjiService;

@Controller
@RequestMapping("/zhengji")
public class ZhengjiController extends BaseController {
	private static Logger log = LoggerFactory.getLogger(ZhengjiController.class);

	@Autowired
	private ZhengjiService zhengjiService;
	
	@Autowired
	private IdeaService ideaService;
	
	/**
	 * 新增征集
	 * @return
	 */
	@ResponseBody
	@RequestMapping("add")
	public Res add(@RequestBody Zhengji zhengji) {
		Res responseJson = new Res();
		try {
			Date now = new Date();
			zhengji.setCreatetime(now);
			zhengji.setUpdatetime(now);
			zhengji = zhengjiService.addZhengji(zhengji);
			if (zhengji != null) {
				responseJson.setMsg(Constant.ADD + Constant.ZHENGJI + Constant.OK);
			}
		} catch (Exception e) {
			log.error("/add", e);
			zhengji = null;
		}
		if (zhengji == null) {
			responseJson.setMsg(Constant.ADD + Constant.ZHENGJI + Constant.ERROR);
			responseJson.setStatus(Constant.STATUS_ERROR_500);
		}
		responseJson.setZhengji(zhengji);
		return responseJson;
	}
	
	/**
	 * 删除征集
	 * @return
	 */
	@ResponseBody
	@RequestMapping("delete/{id:\\d+}")
	public Res delete(@PathVariable("id") int id) {
		Res responseJson = new Res();
		try {
			zhengjiService.deleteById(id);
			//数据库自动删除级联关系
//			ideaService.deleteByZhengji(id);
			responseJson.setMsg(Constant.DELETE + Constant.ZHENGJI + Constant.OK);
		} catch (Exception e) {
			log.error("/delete", e);
			responseJson.setMsg(Constant.DELETE + Constant.ZHENGJI + Constant.ERROR);
			responseJson.setStatus(Constant.STATUS_ERROR_500);
		}
		return responseJson;
	}
	
	/**
	 * 更新征集
	 * @return
	 */
	@ResponseBody
	@RequestMapping("update")
	public Res update(@RequestBody Zhengji zhengji) {
		Res responseJson = new Res();
		try {
			Date now = new Date();
			zhengji.setUpdatetime(now);
			int r = zhengjiService.updateByIdSelective(zhengji);
			if (r > 0) {
				zhengji = zhengjiService.findById(zhengji.getZhengjiid());
				responseJson.setMsg(Constant.UPDATE + Constant.ZHENGJI + Constant.OK);
			}
		} catch (Exception e) {
			log.error("/update", e);
			zhengji = null;
		}
		if (zhengji == null) {
			responseJson.setMsg(Constant.UPDATE + Constant.ZHENGJI + Constant.ERROR);
			responseJson.setStatus(Constant.STATUS_ERROR_500);
		}
		responseJson.setZhengji(zhengji);
		return responseJson;
	}
	
	/**
	 * 获取征集
	 * @return
	 */
	@ResponseBody
	@RequestMapping("get/{id:\\d+}")
	public Res get(@PathVariable("id") int id) {
		Res responseJson = new Res();
		Zhengji zhengji = null;
		try {
			zhengji = zhengjiService.findById(id);
			responseJson.setMsg(Constant.SELECT + Constant.ZHENGJI + Constant.OK);
		} catch (Exception e) {
			log.error("/get", e);
			zhengji = null;
		}
		if (zhengji == null) {
			responseJson.setMsg(Constant.SELECT + Constant.ZHENGJI + Constant.ERROR);
			responseJson.setStatus(Constant.STATUS_ERROR_500);
		}
		responseJson.setZhengji(zhengji);
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
			p = zhengjiService.search(keyword, p);
			responseJson.setList(p.getList());
			responseJson.setMsg(Constant.SEARCH + Constant.ZHENGJI + Constant.OK);
		} catch (Exception e) {
			log.error("/search", e);
			responseJson.setMsg(Constant.SEARCH + Constant.ZHENGJI + Constant.ERROR);
			responseJson.setStatus(Constant.STATUS_ERROR_500);
		}
		return responseJson;
	}
	
	/**
	 * 查看我发布的征集列表List
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
			p = zhengjiService.myList(userid, p);
			responseJson.setList(p.getList());
			responseJson.setMsg(Constant.SEARCH + Constant.ZHENGJI + Constant.OK);
		} catch (Exception e) {
			log.error("/myList", e);
			responseJson.setMsg(Constant.SEARCH + Constant.ZHENGJI + Constant.ERROR);
			responseJson.setStatus(Constant.STATUS_ERROR_500);
		}
		return responseJson;
	}
	
	/**
	 * 获取某个征集对应的所有创意点子List
	 * @param userid
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping("ideaList")
	public Res ideaList(@RequestParam(value = "zhengjiid") Integer zhengjiid, 
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
			p = ideaService.ideaList(zhengjiid, p);
			responseJson.setList(p.getList());
			responseJson.setMsg(Constant.SEARCH + Constant.ZHENGJI + Constant.OK);
		} catch (Exception e) {
			log.error("/ideaList", e);
			responseJson.setMsg(Constant.SEARCH + Constant.ZHENGJI + Constant.ERROR);
			responseJson.setStatus(Constant.STATUS_ERROR_500);
		}
		return responseJson;
	}
	
}
