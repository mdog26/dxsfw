package com.dxsfw.pub.dao;

import org.springframework.stereotype.Repository;

import com.dxsfw.common.base.BaseDao;
import com.dxsfw.pub.model.Picture;

@Repository("pictureDao")
public interface PictureDao extends BaseDao<Picture, Integer> {
}