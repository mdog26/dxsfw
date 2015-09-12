/**
 * 
 */
package com.dxsfw.chuangye.service.imp;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dxsfw.chuangye.dao.ChuangYeDao;
import com.dxsfw.chuangye.model.ChuangYe;
import com.dxsfw.chuangye.model.ChuangYeExample;
import com.dxsfw.chuangye.model.ChuangYeExample.Criteria;
import com.dxsfw.chuangye.service.ChuangYeService;
import com.dxsfw.common.base.BaseServiceImpl;
import com.dxsfw.common.page.Pagination;

/**
 * @author riven
 *
 */
@Repository("chuangyeService")
public class ChuangYeServiceImp extends BaseServiceImpl<ChuangYe, Integer> implements ChuangYeService {
	private static Logger log = LoggerFactory.getLogger(ChuangYeServiceImp.class);
	
	@Autowired
    private ChuangYeDao chuangyeDao;
	
	@Override
	public ChuangYeDao getDao() {
		return chuangyeDao;
	}
	
	/* 添加创业基本信息(不包括图片和附件)
	 */
	@Override
	public ChuangYe addChuangYe(ChuangYe record) {
		int r = chuangyeDao.insertSelective(record);
		if (r > 0) {
			ChuangYeExample example = new ChuangYeExample();
			example.createCriteria().andUseridEqualTo(record.getUserid());
			example.setOrderByClause("chuangyeid desc");
			List<ChuangYe> list = chuangyeDao.selectByExample(example);
			if (list.size() > 0) {
				return list.get(0);
			}
		}
		return null;
	}
	
	/* 获取创业List或者search创业List
	 */
	@Override
	public Pagination search(String keyword, Pagination p) {
		ChuangYeExample example = new ChuangYeExample();
		if (keyword != null && !StringUtils.isEmpty(keyword)) {
			String[] keys = keyword.split(" ");
			for (int i = 0; i < keys.length; i++) {
				String key = keys[i];
				if (i == 0) {
					example.createCriteria().andTagLike("%" + key + "%");
					example.or(example.createCriteria().andTitleLike("%" + key + "%"));
				} else {
					example.or(example.createCriteria().andTagLike("%" + key + "%"));
					example.or(example.createCriteria().andTitleLike("%" + key + "%"));
				}
			}
		}
		
		example.setOrderByClause("updatetime desc");
		return this.queryByExample(example, p);
	}
	
	/**
	 * 我发布的创业列表
	 */
	@Override
	public Pagination myList(Integer userid, Pagination p) {
		ChuangYeExample example = new ChuangYeExample();
		Criteria c = example.createCriteria().andUseridEqualTo(userid);
		example.setOrderByClause("updatetime desc");
		return this.queryByExample(example, p);
	}

}
