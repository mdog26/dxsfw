 /**
 * 
 */
package com.dxsfw.pub.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dxsfw.pub.dao.PictureDao;
import com.dxsfw.pub.model.Picture;
import com.dxsfw.pub.model.PictureExample;
import com.dxsfw.pub.service.PubService;

/**
 * @author riven
 *
 */
@Repository("pubService")
public class PubServiceImp implements PubService {
	private static Logger log = LoggerFactory.getLogger(PubServiceImp.class);
	
	@Autowired
    private PictureDao pictureDao;
	
	/* 添加或更新图片(业务唯一图片)
	 */
	@Override
	public void addorUpdatePicture(Picture p) {
		if (p.getPictureid() == null) {
			// 如果没有id, 表明单一业务的图片关系
			PictureExample example = new PictureExample();
			example.createCriteria().andTablenameEqualTo(p.getTablename()).andPkEqualTo(p.getPk());
			List<Picture> list = pictureDao.selectByExample(example);
			if (list.size() > 0) {
				p.setPictureid(list.get(0).getPictureid());
				pictureDao.updateByPrimaryKeySelective(p);
			} else {
				pictureDao.insertSelective(p);
			}
		} else {
			// 如果有唯一id, 证明肯定有数据
			pictureDao.updateByPrimaryKeySelective(p);
		}
	}
	
	/* 添加新图片
	 */
	@Override
	public Picture addPicture(Picture p) {
		int r = pictureDao.insertSelective(p);
		if (r > 0) {
			PictureExample example = new PictureExample();
			example.createCriteria().andTablenameEqualTo(p.getTablename()).andPkEqualTo(p.getPk());
			example.setOrderByClause("pictureid desc");
			List<Picture> list = pictureDao.selectByExample(example);
			if (list.size() > 0) {
				return list.get(0);
			}
		}
		return null;
	}

	/* 删除图片
	 */
	@Override
	public void deletePicture(Picture p) {
		if (p.getPictureid() == null) {
			// 如果没有唯一id
			PictureExample example = new PictureExample();
			example.createCriteria().andPathEqualTo(p.getPath());
			List<Picture> list = pictureDao.selectByExample(example);
			if (list.size() > 0) {
				pictureDao.deleteByPrimaryKey(list.get(0).getPictureid());
			} else {
				pictureDao.deleteByPrimaryKey(p.getPictureid());
			}
		} else {
			// 如果有唯一id, 证明肯定有数据
			pictureDao.deleteByPrimaryKey(p.getPictureid());
		}
	}

	/**
	 * 获取图片
	 */
	@Override
	public Picture getPicture(Integer pictureid) {
		return pictureDao.selectByPrimaryKey(pictureid);
	}
	
	/**
	 * 获取图片集
	 */
	@Override
	public List<Picture> getPicture(Picture p) {
		PictureExample example = new PictureExample();
		example.createCriteria().andTablenameEqualTo(p.getTablename()).andPkEqualTo(p.getPk());
		return pictureDao.selectByExample(example);
	}

}
