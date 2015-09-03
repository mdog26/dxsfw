/**
 * 
 */
package com.dxsfw.jianzhi.service.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dxsfw.common.base.BaseServiceImpl;
import com.dxsfw.common.page.Pagination;
import com.dxsfw.jianzhi.dao.JianzhiDao;
import com.dxsfw.jianzhi.dao.JianzhiShengqingDao;
import com.dxsfw.jianzhi.model.Jianzhi;
import com.dxsfw.jianzhi.model.JianzhiExample;
import com.dxsfw.jianzhi.model.JianzhiShengqing;
import com.dxsfw.jianzhi.model.JianzhiShengqingExample;
import com.dxsfw.jianzhi.service.JianzhiService;
import com.dxsfw.pub.dao.JianLiDao;
import com.dxsfw.pub.model.JianLiExample;
import com.dxsfw.pub.service.JianLiService;

/**
 * @author riven
 *
 */
@Repository("jianzhiService")
public class JianzhiServiceImp extends BaseServiceImpl<Jianzhi, Integer> implements JianzhiService {
	private static Logger log = LoggerFactory.getLogger(JianzhiServiceImp.class);
	
	@Autowired
    private JianzhiDao jianzhiDao;
	
	@Autowired
	private JianzhiShengqingDao jianzhiShengqingDao;
	
	@Autowired
	private JianLiDao jianLiDao;
	
	@Autowired
	private JianLiService jianLiService;
	
	@Override
	public JianzhiDao getDao() {
		return jianzhiDao;
	}
	
	/* 添加兼职基本信息(不包括图片和附件)
	 */
	@Override
	public Jianzhi addJianzhi(Jianzhi record) {
		int r = jianzhiDao.insertSelective(record);
		if (r > 0) {
			JianzhiExample example = new JianzhiExample();
			example.createCriteria().andUseridEqualTo(record.getUserid());
			example.setOrderByClause("jianzhiid desc");
			List<Jianzhi> list = jianzhiDao.selectByExample(example);
			if (list.size() > 0) {
				return list.get(0);
			}
		}
		return null;
	}

	/* 报名申请兼职
	 */
	@Override
	public boolean applyJianzhi(JianzhiShengqing record) {
		// 检查是否已经申请过
		JianzhiShengqingExample example1 = new JianzhiShengqingExample();
		example1.createCriteria().andShengqinguseridEqualTo(record.getShengqinguserid())
				.andJianzhiidEqualTo(record.getJianzhiid());
		List<JianzhiShengqing> idList = jianzhiShengqingDao.selectByExample(example1);
		//兼职id and 申请人di 同时重复，表示已经投过简历了
		if (idList.size() == 0) {
			int r = jianzhiShengqingDao.insertSelective(record);
			if (r > 0) {
				// 更新兼职 updatetime
				Jianzhi jianzhi = new Jianzhi();
				jianzhi.setJianzhiid(record.getJianzhiid());
				jianzhi.setUpdatetime(new Date());
				jianzhiDao.updateByPrimaryKeySelective(jianzhi);
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	/* 获取兼职List或者search兼职List
	 */
	@Override
	public Pagination search(String keyword, Pagination p) {
		JianzhiExample example = new JianzhiExample();
		if (keyword != null && !StringUtils.isEmpty(keyword)) {
			String[] keys = keyword.split(" ");
			for (int i = 0; i < keys.length; i++) {
				String key = keys[i];
				if (i == 0) {
					example.createCriteria().andTagLike("%" + key + "%");
					example.or(example.createCriteria().andWorkplaceLike("%" + key + "%"));
				} else {
					example.or(example.createCriteria().andTagLike("%" + key + "%"));
					example.or(example.createCriteria().andWorkplaceLike("%" + key + "%"));
				}
			}
		}
		example.setOrderByClause("updatetime desc");
		return this.queryByExample(example, p);
	}
	
	/**
	 * 我发布的兼职列表
	 */
	@Override
	public Pagination myList(Integer userid, Pagination p) {
		JianzhiExample example = new JianzhiExample();
		example.createCriteria().andUseridEqualTo(userid);
		example.setOrderByClause("updatetime desc");
		return this.queryByExample(example, p);
	}
	
	/**
	 * 我申请的兼职列表
	 */
	@Override
	public Pagination myApplyList(Integer userid, Pagination p) {
		JianzhiShengqingExample example1 = new JianzhiShengqingExample();
		example1.createCriteria().andShengqinguseridEqualTo(userid);
		List<JianzhiShengqing> idList = jianzhiShengqingDao.selectByExample(example1);
		if (idList.size() > 0) {
			// 子表查询出所有的兼职id
			List<Integer> ids = new ArrayList<Integer>();
			for (JianzhiShengqing jianzhiShengqing : idList) {
				ids.add(jianzhiShengqing.getJianzhiid());
			}
			// 再查主表
			JianzhiExample example = new JianzhiExample();
			example.createCriteria().andJianzhiidIn(ids);
			example.setOrderByClause("updatetime desc");
			return this.queryByExample(example, p);
		} else {
			return p;
		}
	}
	
	/**
	 * 兼职的报名简历列表
	 */
	@Override
	public Pagination applyJianLiList(Integer jianzhiid, Pagination p) {
		JianzhiShengqingExample example1 = new JianzhiShengqingExample();
		example1.createCriteria().andJianzhiidEqualTo(jianzhiid);
		example1.setOrderByClause("time desc");
		List<JianzhiShengqing> idList = jianzhiShengqingDao.selectByExample(example1);
		if (idList.size() > 0) {
			// 子表查询出所有的兼职申请 的 简历ids
			List<Integer> ids = new ArrayList<Integer>();
			for (JianzhiShengqing jianzhiShengqing : idList) {
				ids.add(jianzhiShengqing.getJianliid());
			}
			// 再查主表 简历表
			JianLiExample example = new JianLiExample();
			example.createCriteria().andJianliidIn(ids);
			example.setOrderByClause("jianliid desc");
			return jianLiService.searchJianLiList(example, p);
		} else {
			return p;
		}
	}

}
