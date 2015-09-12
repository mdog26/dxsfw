/**
 * 
 */
package com.dxsfw.bbs.service.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dxsfw.bbs.dao.BbsDao;
import com.dxsfw.bbs.dao.BbsShengqingDao;
import com.dxsfw.bbs.model.Bbs;
import com.dxsfw.bbs.model.BbsExample;
import com.dxsfw.bbs.model.BbsExample.Criteria;
import com.dxsfw.bbs.model.BbsShengqing;
import com.dxsfw.bbs.model.BbsShengqingExample;
import com.dxsfw.bbs.service.BbsService;
import com.dxsfw.common.base.BaseServiceImpl;
import com.dxsfw.common.page.Pagination;
import com.dxsfw.pub.dao.ReplyDao;
import com.dxsfw.pub.dao.UserDao;
import com.dxsfw.pub.model.Reply;
import com.dxsfw.pub.model.ReplyExample;
import com.dxsfw.pub.model.UserExample;
import com.dxsfw.pub.service.UserService;

/**
 * @author riven
 *
 */
@Repository("bbsService")
public class BbsServiceImp extends BaseServiceImpl<Bbs, Integer> implements BbsService {
	private static Logger log = LoggerFactory.getLogger(BbsServiceImp.class);
	
	@Autowired
    private BbsDao bbsDao;
	
	@Autowired
	private BbsShengqingDao bbsShengqingDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ReplyDao replyDao;
	
	@Autowired
	private UserService userService;
	
	@Override
	public BbsDao getDao() {
		return bbsDao;
	}
	
	/* 添加交流/授课基本信息(不包括图片和附件)
	 */
	@Override
	public Bbs addBbs(Bbs record) {
		int r = bbsDao.insertSelective(record);
		if (r > 0) {
			BbsExample example = new BbsExample();
			example.createCriteria().andUseridEqualTo(record.getUserid());
			example.setOrderByClause("bbsid desc");
			List<Bbs> list = bbsDao.selectByExample(example);
			if (list.size() > 0) {
				return list.get(0);
			}
		}
		return null;
	}
	
	/* 添加交流/授课基本信息(不包括图片和附件)
	 */
	@Override
	public Bbs addClass(Bbs record) {
		int r = bbsDao.insertSelective(record);
		if (r > 0) {
			BbsExample example = new BbsExample();
			example.createCriteria().andUseridEqualTo(record.getUserid());
			example.setOrderByClause("bbsid desc");
			List<Bbs> list = bbsDao.selectByExample(example);
			if (list.size() > 0) {
				return list.get(0);
			}
		}
		return null;
	}

	/* 报名申请交流/授课
	 */
	@Override
	public boolean applyClass(BbsShengqing record) {
		//检查报名是否已经满员
		BbsShengqingExample example = new BbsShengqingExample();
		example.createCriteria().andBbsidEqualTo(record.getBbsid());
		int count = bbsShengqingDao.countByExample(example);
		
		Bbs bbs = bbsDao.selectByPrimaryKey(record.getBbsid());
		if (bbs.getPeople() != null && bbs.getPeople() != 0 && count >= bbs.getPeople()) {
			return false;
		}
		// 检查是否已经申请过
		example.clear();  
		example.createCriteria().andBbsidEqualTo(record.getBbsid()).andShengqinguseridEqualTo(record.getShengqinguserid());
		List<BbsShengqing> idList = bbsShengqingDao.selectByExample(example);
		//交流/授课id and 申请人di 同时重复，表示已经投过用户了
		if (idList.size() == 0) {
			int r = bbsShengqingDao.insertSelective(record);
			if (r > 0) {
				// 更新交流/授课 updatetime
				bbs.setUpdatetime(new Date());
				bbsDao.updateByPrimaryKeySelective(bbs);
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	/* 获取交流/授课List或者search交流/授课List
	 */
	@Override
	public Pagination search(String keyword, String type, Pagination p) {
		BbsExample example = new BbsExample();
		if (keyword != null && !StringUtils.isEmpty(keyword)) {
			String[] keys = keyword.split(" ");
			for (int i = 0; i < keys.length; i++) {
				String key = keys[i];
				if (i == 0) {
					if (!StringUtils.isEmpty(type)) {
						example.createCriteria().andTagLike("%" + key + "%").andTypeEqualTo(type);
						example.or(example.createCriteria().andTitleLike("%" + key + "%").andTypeEqualTo(type));
					} else {
						example.createCriteria().andTagLike("%" + key + "%");
						example.or(example.createCriteria().andTitleLike("%" + key + "%"));
					}
				} else {
					if (!StringUtils.isEmpty(type)) {
						example.or(example.createCriteria().andTagLike("%" + key + "%").andTypeEqualTo(type));
						example.or(example.createCriteria().andTitleLike("%" + key + "%").andTypeEqualTo(type));
					} else {
						example.or(example.createCriteria().andTagLike("%" + key + "%"));
						example.or(example.createCriteria().andTitleLike("%" + key + "%"));
					}
				}
			}
		} else {
			example.createCriteria().andTypeEqualTo(type);
		}
		
		example.setOrderByClause("updatetime desc");
		return this.queryByExample(example, p);
	}
	
	/**
	 * 我发布的交流/授课列表
	 */
	@Override
	public Pagination myList(Integer userid, String type, Pagination p) {
		BbsExample example = new BbsExample();
		Criteria c = example.createCriteria().andUseridEqualTo(userid);
		if (!StringUtils.isEmpty(type)) {
			c.andTypeEqualTo(type);
		}
		example.setOrderByClause("updatetime desc");
		return this.queryByExample(example, p);
	}
	
	/**
	 * 我申请的交流/授课列表
	 */
	@Override
	public Pagination myApplyList(Integer userid, String type, Pagination p) {
		BbsShengqingExample example1 = new BbsShengqingExample();
		example1.createCriteria().andShengqinguseridEqualTo(userid);
		List<BbsShengqing> idList = bbsShengqingDao.selectByExample(example1);
		if (idList.size() > 0) {
			// 子表查询出所有的授课id
			List<Integer> ids = new ArrayList<Integer>();
			for (BbsShengqing bbsShengqing : idList) {
				ids.add(bbsShengqing.getBbsid());
			}
			// 再查主表
			BbsExample example = new BbsExample();
			Criteria c = example.createCriteria().andBbsidIn(ids);
			if (!StringUtils.isEmpty(type)) {
				c.andTypeEqualTo(type);
			}
			example.setOrderByClause("updatetime desc");
			return this.queryByExample(example, p);
		} else {
			return p;
		}
	}
	
	/**
	 * 交流/授课的报名用户列表
	 */
	@Override
	public Pagination applyUserList(Integer bbsid, Boolean includePublishUser, Pagination p) {
		BbsShengqingExample example1 = new BbsShengqingExample();
		example1.createCriteria().andBbsidEqualTo(bbsid);
		example1.setOrderByClause("time asc");
		List<BbsShengqing> idList = bbsShengqingDao.selectByExample(example1);
		if (idList.size() > 0) {
			// 子表查询出所有的交流/授课申请 的 用户ids
			List<Integer> ids = new ArrayList<Integer>();
			if (includePublishUser != null && includePublishUser) {
				//用户是否包含发布者自己
				ids.add(idList.get(0).getPublishuserid());
			}
			for (BbsShengqing bbsShengqing : idList) {
				ids.add(bbsShengqing.getShengqinguserid());
			}
			// 再查主表 用户表
			UserExample example = new UserExample();
			example.createCriteria().andUseridIn(ids);
			example.setOrderByClause("userid asc");
			return userService.searchUserList(example, p);
		} else {
			return p;
		}
	}
	
	/**
	 * 我回复的交流帖子
	 */
	@Override
	public Pagination myReplyList(Integer userid, String type, Pagination p) {
		ReplyExample example1 = new ReplyExample();
		example1.createCriteria().andReplyuseridEqualTo(userid);
		List<Reply> idList = replyDao.selectByExample(example1);
		if (idList.size() > 0) {
			// 子表查询出所有的授课id
			List<Integer> ids = new ArrayList<Integer>();
			for (Reply reply : idList) {
				ids.add(reply.getPk());
			}
			// 再查主表
			BbsExample example = new BbsExample();
			Criteria c = example.createCriteria().andBbsidIn(ids);
			if (!StringUtils.isEmpty(type)) {
				c.andTypeEqualTo(type);
			}
			example.setOrderByClause("updatetime desc");
			return this.queryByExample(example, p);
		} else {
			return p;
		}
	}

}
