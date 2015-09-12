package com.dxsfw.idea.service.imp;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dxsfw.common.base.BaseServiceImpl;
import com.dxsfw.common.page.Pagination;
import com.dxsfw.idea.dao.IdeaDao;
import com.dxsfw.idea.dao.ZhengjiDao;
import com.dxsfw.idea.model.IdeaExample;
import com.dxsfw.idea.model.Zhengji;
import com.dxsfw.idea.model.ZhengjiExample;
import com.dxsfw.idea.model.ZhengjiExample.Criteria;
import com.dxsfw.idea.service.ZhengjiService;

/**
 * @author riven
 *
 */
@Repository("zhengjiService")
public class ZhengjiServiceImp extends BaseServiceImpl<Zhengji, Integer> implements ZhengjiService {
	private static Logger log = LoggerFactory.getLogger(ZhengjiServiceImp.class);
	
	@Autowired
    private ZhengjiDao zhengjiDao;
	
	@Autowired
	private IdeaDao ideaDao;
	
	@Override
	public ZhengjiDao getDao() {
		return zhengjiDao;
	}
	
	/* 添加征集基本信息(不包括图片和附件)
	 */
	@Override
	public Zhengji addZhengji(Zhengji record) {
		int r = zhengjiDao.insertSelective(record);
		if (r > 0) {
			ZhengjiExample example = new ZhengjiExample();
			example.createCriteria().andUseridEqualTo(record.getUserid());
			example.setOrderByClause("zhengjiid desc");
			List<Zhengji> list = zhengjiDao.selectByExample(example);
			if (list.size() > 0) {
				return list.get(0);
			}
		}
		return null;
	}
	
	/* 获取征集List或者search征集List
	 */
	@Override
	public Pagination search(String keyword, Pagination p) {
		ZhengjiExample example = new ZhengjiExample();
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
	 * 我发布的征集列表
	 */
	@Override
	public Pagination myList(Integer userid, Pagination p) {
		ZhengjiExample example = new ZhengjiExample();
		Criteria c = example.createCriteria().andUseridEqualTo(userid);
		example.setOrderByClause("updatetime desc");
		return this.queryByExample(example, p);
	}

}
