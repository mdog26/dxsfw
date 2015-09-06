/**
 * 
 */
package com.dxsfw.party.service.imp;

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
import com.dxsfw.party.dao.PartyDao;
import com.dxsfw.party.dao.PartyShengqingDao;
import com.dxsfw.party.model.Party;
import com.dxsfw.party.model.PartyExample;
import com.dxsfw.party.model.PartyShengqing;
import com.dxsfw.party.model.PartyShengqingExample;
import com.dxsfw.party.service.PartyService;
import com.dxsfw.pub.dao.UserDao;
import com.dxsfw.pub.model.UserExample;
import com.dxsfw.pub.service.UserService;

/**
 * @author riven
 *
 */
@Repository("partyService")
public class PartyServiceImp extends BaseServiceImpl<Party, Integer> implements PartyService {
	private static Logger log = LoggerFactory.getLogger(PartyServiceImp.class);
	
	@Autowired
    private PartyDao partyDao;
	
	@Autowired
	private PartyShengqingDao partyShengqingDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserService userService;
	
	@Override
	public PartyDao getDao() {
		return partyDao;
	}
	
	/* 添加活动基本信息(不包括图片和附件)
	 */
	@Override
	public Party addParty(Party record) {
		int r = partyDao.insertSelective(record);
		if (r > 0) {
			PartyExample example = new PartyExample();
			example.createCriteria().andUseridEqualTo(record.getUserid());
			example.setOrderByClause("partyid desc");
			List<Party> list = partyDao.selectByExample(example);
			if (list.size() > 0) {
				return list.get(0);
			}
		}
		return null;
	}

	/* 报名申请活动
	 */
	@Override
	public boolean applyParty(PartyShengqing record) {
		//检查报名是否已经满员
		PartyShengqingExample example = new PartyShengqingExample();
		example.createCriteria().andPartyidEqualTo(record.getPartyid());
		int count = partyShengqingDao.countByExample(example);
		
		Party party = partyDao.selectByPrimaryKey(record.getPartyid());
		if (party.getPeople() != null && party.getPeople() != 0 && count >= party.getPeople()) {
			return false;
		}
		// 检查是否已经申请过
		example.clear();  
		example.createCriteria().andPartyidEqualTo(record.getPartyid()).andShengqinguseridEqualTo(record.getShengqinguserid());
		List<PartyShengqing> idList = partyShengqingDao.selectByExample(example);
		//活动id and 申请人di 同时重复，表示已经投过用户了
		if (idList.size() == 0) {
			int r = partyShengqingDao.insertSelective(record);
			if (r > 0) {
				// 更新活动 updatetime
				party.setUpdatetime(new Date());
				partyDao.updateByPrimaryKeySelective(party);
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	/* 获取活动List或者search活动List
	 */
	@Override
	public Pagination search(String keyword, Pagination p) {
		PartyExample example = new PartyExample();
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
	 * 我发布的活动列表
	 */
	@Override
	public Pagination myList(Integer userid, Pagination p) {
		PartyExample example = new PartyExample();
		example.createCriteria().andUseridEqualTo(userid);
		example.setOrderByClause("updatetime desc");
		return this.queryByExample(example, p);
	}
	
	/**
	 * 我申请的活动列表
	 */
	@Override
	public Pagination myApplyList(Integer userid, Pagination p) {
		PartyShengqingExample example1 = new PartyShengqingExample();
		example1.createCriteria().andShengqinguseridEqualTo(userid);
		List<PartyShengqing> idList = partyShengqingDao.selectByExample(example1);
		if (idList.size() > 0) {
			// 子表查询出所有的活动id
			List<Integer> ids = new ArrayList<Integer>();
			for (PartyShengqing partyShengqing : idList) {
				ids.add(partyShengqing.getPartyid());
			}
			// 再查主表
			PartyExample example = new PartyExample();
			example.createCriteria().andPartyidIn(ids);
			example.setOrderByClause("updatetime desc");
			return this.queryByExample(example, p);
		} else {
			return p;
		}
	}
	
	/**
	 * 活动的报名用户列表
	 */
	@Override
	public Pagination applyUserList(Integer partyid, Boolean includePublishUser, Pagination p) {
		PartyShengqingExample example1 = new PartyShengqingExample();
		example1.createCriteria().andPartyidEqualTo(partyid);
		example1.setOrderByClause("time asc");
		List<PartyShengqing> idList = partyShengqingDao.selectByExample(example1);
		if (idList.size() > 0) {
			// 子表查询出所有的活动申请 的 用户ids
			List<Integer> ids = new ArrayList<Integer>();
			if (includePublishUser != null && includePublishUser) {
				//用户是否包含发布者自己
				ids.add(idList.get(0).getPublishuserid());
			}
			for (PartyShengqing partyShengqing : idList) {
				ids.add(partyShengqing.getShengqinguserid());
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

}
