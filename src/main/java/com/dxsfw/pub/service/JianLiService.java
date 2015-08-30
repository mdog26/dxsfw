package com.dxsfw.pub.service;

import java.util.List;

import com.dxsfw.pub.model.JianLi;

public interface JianLiService {
	public JianLi addJianLi(JianLi jl);
	
	public List<JianLi> getJianLiByUser(int userid);
	
	public JianLi updateJianLi(JianLi jl);
	
	public void deleteJianLi(int jianliid);
	
	public JianLi getJianLi(int jianliid);
	
}
