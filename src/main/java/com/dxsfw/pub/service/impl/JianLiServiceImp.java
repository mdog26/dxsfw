/**
 * 
 */
package com.dxsfw.pub.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dxsfw.pub.dao.JianLiDao;
import com.dxsfw.pub.model.JianLi;
import com.dxsfw.pub.model.JianLiExample;
import com.dxsfw.pub.service.JianLiService;

/**
 * @author riven
 *
 */
@Repository("jianLiService")
public class JianLiServiceImp implements JianLiService {
	private static Logger log = LoggerFactory.getLogger(JianLiServiceImp.class);
	
	@Autowired
    private JianLiDao jianLiDao;
	
	/* 添加简历基本信息(不包括图片和附件)
	 */
	@Override
	public JianLi addJianLi(JianLi record) {
		int r = jianLiDao.insertSelective(record);
		if (r > 0) {
			JianLiExample example = new JianLiExample();
			example.createCriteria().andUseridEqualTo(record.getUserid());
			example.setOrderByClause("jianliid desc");
			List<JianLi> list = jianLiDao.selectByExample(example);
			if (list.size() > 0) {
				return list.get(0);
			}
		}
		return null;
	}

	/* 获取个人所有简历基本信息
	 */
	@Override
	public List<JianLi> getJianLiByUser(int userid) {
		JianLiExample example = new JianLiExample();
		example.createCriteria().andUseridEqualTo(userid);
		example.setOrderByClause("jianliid desc");
		List<JianLi> list = jianLiDao.selectByExample(example);
		return list;
	}

	/* 更新简历基本信息
	 */
	@Override
	public JianLi updateJianLi(JianLi jl) {
		jianLiDao.updateByPrimaryKeySelective(jl);
		return this.getJianLi(jl.getJianliid());
	}

	/* 删除简历
	 */
	@Override
	public void deleteJianLi(int jianliid) {
		jianLiDao.deleteByPrimaryKey(jianliid);
	}

	/**
	 * 获取简历blobs
	 */
	@Override
	public JianLi getJianLi(int jianliid) {
		return jianLiDao.selectByPrimaryKey(jianliid);
	}

}
