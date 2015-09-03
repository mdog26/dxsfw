package com.dxsfw.jianzhi.service;

import com.dxsfw.common.base.BaseService;
import com.dxsfw.jianzhi.model.Jianzhi;

public interface JianzhiService extends BaseService<Jianzhi, Integer> {
	public Jianzhi addJianzhi(Jianzhi jianzhi);
	
}
