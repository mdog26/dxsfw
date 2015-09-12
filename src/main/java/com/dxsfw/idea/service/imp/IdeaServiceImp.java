/**
 * 
 */
package com.dxsfw.idea.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dxsfw.common.base.BaseDao;
import com.dxsfw.common.base.BaseServiceImpl;
import com.dxsfw.common.page.Pagination;
import com.dxsfw.idea.dao.IdeaDao;
import com.dxsfw.idea.model.Idea;
import com.dxsfw.idea.model.IdeaExample;
import com.dxsfw.idea.model.IdeaExample.Criteria;
import com.dxsfw.idea.service.IdeaService;

/**
 * @author riven
 *
 */
@Repository("ideaService")
public class IdeaServiceImp extends BaseServiceImpl<Idea, Integer> implements IdeaService {
	private static Logger log = LoggerFactory.getLogger(IdeaServiceImp.class);
	
	@Autowired
    private IdeaDao ideaDao;
	
	@Override
	public IdeaDao getDao() {
		return ideaDao;
	}
	
	/* 添加创意基本信息(不包括图片和附件)
	 */
	@Override
	public Idea addIdea(Idea record) {
		int r = ideaDao.insertSelective(record);
		if (r > 0) {
			IdeaExample example = new IdeaExample();
			example.createCriteria().andUseridEqualTo(record.getUserid());
			example.setOrderByClause("ideaid desc");
			List<Idea> list = ideaDao.selectByExample(example);
			if (list.size() > 0) {
				return list.get(0);
			}
		}
		return null;
	}
	
	/* 获取创意List或者search创意List
	 */
	@Override
	public Pagination search(String keyword, Pagination p) {
		IdeaExample example = new IdeaExample();
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
	 * 我发布的创意列表
	 */
	@Override
	public Pagination myList(Integer userid, Pagination p) {
		IdeaExample example = new IdeaExample();
		Criteria c = example.createCriteria().andUseridEqualTo(userid);
		example.setOrderByClause("updatetime desc");
		return this.queryByExample(example, p);
	}

	/**
	 * 删除征集对应的点子
	 */
	@Override
	public void deleteByZhengji(Integer zhengjiid) {
		IdeaExample example = new IdeaExample();
		Criteria c = example.createCriteria().andZhengjiidEqualTo(zhengjiid);
		ideaDao.deleteByExample(example);
	}

	/**
	 * 获取某个征集对应的所有创意点子List
	 */
	@Override
	public Pagination ideaList(Integer zhengjiid, Pagination p) {
		IdeaExample example = new IdeaExample();
		example.createCriteria().andZhengjiidEqualTo(zhengjiid);
		example.setOrderByClause("updatetime desc");
		return this.queryByExample(example, p);
	}
}
