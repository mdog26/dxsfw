package com.dxsfw.common.base.jianli;

import java.util.List;

import com.dxsfw.common.base.jianli.pro.JianLiEducation;
import com.dxsfw.common.base.jianli.pro.JianLiExperience;
import com.dxsfw.common.base.jianli.pro.JianLiLanguage;
import com.dxsfw.common.base.jianli.pro.JianLiTrain;
import com.dxsfw.common.base.jianli.pro.JianLiZhengshu;
import com.dxsfw.pub.model.JianLi;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 简历接口包装类
 * @author riven
 *
 */
public class ResJianLi extends JianLi {

	@JsonInclude(Include.NON_NULL)
	private List<JianLiEducation> _education;
	@JsonInclude(Include.NON_NULL)
	private List<JianLiTrain> _train;
	@JsonInclude(Include.NON_NULL)
	private List<JianLiLanguage> _language;
	@JsonInclude(Include.NON_NULL)
	private List<JianLiZhengshu> _zhengshu;
	@JsonInclude(Include.NON_NULL)
	private List<JianLiExperience> _experience;
	public List<JianLiEducation> get_education() {
		return _education;
	}
	public void set_education(List<JianLiEducation> _education) {
		this._education = _education;
	}
	public List<JianLiTrain> get_train() {
		return _train;
	}
	public void set_train(List<JianLiTrain> _train) {
		this._train = _train;
	}
	public List<JianLiLanguage> get_language() {
		return _language;
	}
	public void set_language(List<JianLiLanguage> _language) {
		this._language = _language;
	}
	public List<JianLiZhengshu> get_zhengshu() {
		return _zhengshu;
	}
	public void set_zhengshu(List<JianLiZhengshu> _zhengshu) {
		this._zhengshu = _zhengshu;
	}
	public List<JianLiExperience> get_experience() {
		return _experience;
	}
	public void set_experience(List<JianLiExperience> _experience) {
		this._experience = _experience;
	}
}
