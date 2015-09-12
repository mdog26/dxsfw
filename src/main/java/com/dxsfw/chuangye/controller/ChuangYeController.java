package com.dxsfw.chuangye.controller;

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

import com.dxsfw.chuangye.model.ChuangYe;
import com.dxsfw.chuangye.service.ChuangYeService;
import com.dxsfw.common.base.BaseController;
import com.dxsfw.common.base.Res;
import com.dxsfw.common.constants.Constant;
import com.dxsfw.common.page.Pagination;

@Controller
@RequestMapping("/chuangye")
public class ChuangYeController extends BaseController {
	private static Logger log = LoggerFactory.getLogger(ChuangYeController.class);

	@Autowired
	private ChuangYeService chuangyeService;
	
	/**
	 * 新增创业
	 * @return
	 */
	@ResponseBody
	@RequestMapping("add")
	public Res add(@RequestBody ChuangYe chuangye) {
		Res responseJson = new Res();
		try {
			Date now = new Date();
			chuangye.setCreatetime(now);
			chuangye.setUpdatetime(now);
			chuangye = chuangyeService.addChuangYe(chuangye);
			if (chuangye != null) {
				responseJson.setMsg(Constant.ADD + Constant.CHUANGYE + Constant.OK);
			}
		} catch (Exception e) {
			log.error("/add", e);
			chuangye = null;
		}
		if (chuangye == null) {
			responseJson.setMsg(Constant.ADD + Constant.CHUANGYE + Constant.ERROR);
			responseJson.setStatus(Constant.STATUS_ERROR_500);
		}
		responseJson.setChuangye(chuangye);
		return responseJson;
	}
	
	/**
	 * 删除创业
	 * @return
	 */
	@ResponseBody
	@RequestMapping("delete/{id:\\d+}")
	public Res delete(@PathVariable("id") int id) {
		Res responseJson = new Res();
		try {
			chuangyeService.deleteById(id);
			//数据库自动删除级联关系
			
			responseJson.setMsg(Constant.DELETE + Constant.CHUANGYE + Constant.OK);
		} catch (Exception e) {
			log.error("/delete", e);
			responseJson.setMsg(Constant.DELETE + Constant.CHUANGYE + Constant.ERROR);
			responseJson.setStatus(Constant.STATUS_ERROR_500);
		}
		return responseJson;
	}
	
	/**
	 * 更新创业
	 * @return
	 */
	@ResponseBody
	@RequestMapping("update")
	public Res update(@RequestBody ChuangYe chuangye) {
		Res responseJson = new Res();
		try {
			Date now = new Date();
			chuangye.setUpdatetime(now);
			int r = chuangyeService.updateByIdSelective(chuangye);
			if (r > 0) {
				chuangye = chuangyeService.findById(chuangye.getChuangyeid());
				responseJson.setMsg(Constant.UPDATE + Constant.CHUANGYE + Constant.OK);
			}
		} catch (Exception e) {
			log.error("/update", e);
			chuangye = null;
		}
		if (chuangye == null) {
			responseJson.setMsg(Constant.UPDATE + Constant.CHUANGYE + Constant.ERROR);
			responseJson.setStatus(Constant.STATUS_ERROR_500);
		}
		responseJson.setChuangye(chuangye);
		return responseJson;
	}
	
	/**
	 * 获取创业
	 * @return
	 */
	@ResponseBody
	@RequestMapping("get/{id:\\d+}")
	public Res get(@PathVariable("id") int id) {
		Res responseJson = new Res();
		ChuangYe chuangye = null;
		try {
			chuangye = chuangyeService.findById(id);
			responseJson.setMsg(Constant.SELECT + Constant.CHUANGYE + Constant.OK);
		} catch (Exception e) {
			log.error("/get", e);
			chuangye = null;
		}
		if (chuangye == null) {
			responseJson.setMsg(Constant.SELECT + Constant.CHUANGYE + Constant.ERROR);
			responseJson.setStatus(Constant.STATUS_ERROR_500);
		}
		responseJson.setChuangye(chuangye);
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
			p = chuangyeService.search(keyword, p);
			responseJson.setList(p.getList());
			responseJson.setMsg(Constant.SEARCH + Constant.CHUANGYE + Constant.OK);
		} catch (Exception e) {
			log.error("/search", e);
			responseJson.setMsg(Constant.SEARCH + Constant.CHUANGYE + Constant.ERROR);
			responseJson.setStatus(Constant.STATUS_ERROR_500);
		}
		return responseJson;
	}
	
	/**
	 * 查看我发布的创业列表List
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
			p = chuangyeService.myList(userid, p);
			responseJson.setList(p.getList());
			responseJson.setMsg(Constant.SEARCH + Constant.CHUANGYE + Constant.OK);
		} catch (Exception e) {
			log.error("/myList", e);
			responseJson.setMsg(Constant.SEARCH + Constant.CHUANGYE + Constant.ERROR);
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
			ChuangYe entity = chuangyeService.findById(id);
			entity.setPayuserid(userid);
			entity.setSellstatus("Y");
			chuangyeService.updateByIdSelective(entity);
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
			ChuangYe entity = chuangyeService.findById(id);
			entity.setPingjia(pingjia);
			entity.setPingjiafenshu(pingjiafenshu);
			chuangyeService.updateByIdSelective(entity);
			responseJson.setMsg("评价成功");
		} catch (Exception e) {
			log.error("/pay", e);
			responseJson.setMsg("评价失败");
			responseJson.setStatus(Constant.STATUS_ERROR_500);
		}
		return responseJson;
	}
}
