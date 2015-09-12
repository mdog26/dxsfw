package com.dxsfw.chuangye.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dxsfw.common.base.BaseExample;

public class ChuangYeExample extends BaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ChuangYeExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andChuangyeidIsNull() {
            addCriterion("chuangyeid is null");
            return (Criteria) this;
        }

        public Criteria andChuangyeidIsNotNull() {
            addCriterion("chuangyeid is not null");
            return (Criteria) this;
        }

        public Criteria andChuangyeidEqualTo(Integer value) {
            addCriterion("chuangyeid =", value, "chuangyeid");
            return (Criteria) this;
        }

        public Criteria andChuangyeidNotEqualTo(Integer value) {
            addCriterion("chuangyeid <>", value, "chuangyeid");
            return (Criteria) this;
        }

        public Criteria andChuangyeidGreaterThan(Integer value) {
            addCriterion("chuangyeid >", value, "chuangyeid");
            return (Criteria) this;
        }

        public Criteria andChuangyeidGreaterThanOrEqualTo(Integer value) {
            addCriterion("chuangyeid >=", value, "chuangyeid");
            return (Criteria) this;
        }

        public Criteria andChuangyeidLessThan(Integer value) {
            addCriterion("chuangyeid <", value, "chuangyeid");
            return (Criteria) this;
        }

        public Criteria andChuangyeidLessThanOrEqualTo(Integer value) {
            addCriterion("chuangyeid <=", value, "chuangyeid");
            return (Criteria) this;
        }

        public Criteria andChuangyeidIn(List<Integer> values) {
            addCriterion("chuangyeid in", values, "chuangyeid");
            return (Criteria) this;
        }

        public Criteria andChuangyeidNotIn(List<Integer> values) {
            addCriterion("chuangyeid not in", values, "chuangyeid");
            return (Criteria) this;
        }

        public Criteria andChuangyeidBetween(Integer value1, Integer value2) {
            addCriterion("chuangyeid between", value1, value2, "chuangyeid");
            return (Criteria) this;
        }

        public Criteria andChuangyeidNotBetween(Integer value1, Integer value2) {
            addCriterion("chuangyeid not between", value1, value2, "chuangyeid");
            return (Criteria) this;
        }

        public Criteria andUseridIsNull() {
            addCriterion("userid is null");
            return (Criteria) this;
        }

        public Criteria andUseridIsNotNull() {
            addCriterion("userid is not null");
            return (Criteria) this;
        }

        public Criteria andUseridEqualTo(Integer value) {
            addCriterion("userid =", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotEqualTo(Integer value) {
            addCriterion("userid <>", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThan(Integer value) {
            addCriterion("userid >", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThanOrEqualTo(Integer value) {
            addCriterion("userid >=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThan(Integer value) {
            addCriterion("userid <", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThanOrEqualTo(Integer value) {
            addCriterion("userid <=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridIn(List<Integer> values) {
            addCriterion("userid in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotIn(List<Integer> values) {
            addCriterion("userid not in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridBetween(Integer value1, Integer value2) {
            addCriterion("userid between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotBetween(Integer value1, Integer value2) {
            addCriterion("userid not between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andPayuseridIsNull() {
            addCriterion("payuserid is null");
            return (Criteria) this;
        }

        public Criteria andPayuseridIsNotNull() {
            addCriterion("payuserid is not null");
            return (Criteria) this;
        }

        public Criteria andPayuseridEqualTo(Integer value) {
            addCriterion("payuserid =", value, "payuserid");
            return (Criteria) this;
        }

        public Criteria andPayuseridNotEqualTo(Integer value) {
            addCriterion("payuserid <>", value, "payuserid");
            return (Criteria) this;
        }

        public Criteria andPayuseridGreaterThan(Integer value) {
            addCriterion("payuserid >", value, "payuserid");
            return (Criteria) this;
        }

        public Criteria andPayuseridGreaterThanOrEqualTo(Integer value) {
            addCriterion("payuserid >=", value, "payuserid");
            return (Criteria) this;
        }

        public Criteria andPayuseridLessThan(Integer value) {
            addCriterion("payuserid <", value, "payuserid");
            return (Criteria) this;
        }

        public Criteria andPayuseridLessThanOrEqualTo(Integer value) {
            addCriterion("payuserid <=", value, "payuserid");
            return (Criteria) this;
        }

        public Criteria andPayuseridIn(List<Integer> values) {
            addCriterion("payuserid in", values, "payuserid");
            return (Criteria) this;
        }

        public Criteria andPayuseridNotIn(List<Integer> values) {
            addCriterion("payuserid not in", values, "payuserid");
            return (Criteria) this;
        }

        public Criteria andPayuseridBetween(Integer value1, Integer value2) {
            addCriterion("payuserid between", value1, value2, "payuserid");
            return (Criteria) this;
        }

        public Criteria andPayuseridNotBetween(Integer value1, Integer value2) {
            addCriterion("payuserid not between", value1, value2, "payuserid");
            return (Criteria) this;
        }

        public Criteria andFeeidIsNull() {
            addCriterion("feeid is null");
            return (Criteria) this;
        }

        public Criteria andFeeidIsNotNull() {
            addCriterion("feeid is not null");
            return (Criteria) this;
        }

        public Criteria andFeeidEqualTo(Integer value) {
            addCriterion("feeid =", value, "feeid");
            return (Criteria) this;
        }

        public Criteria andFeeidNotEqualTo(Integer value) {
            addCriterion("feeid <>", value, "feeid");
            return (Criteria) this;
        }

        public Criteria andFeeidGreaterThan(Integer value) {
            addCriterion("feeid >", value, "feeid");
            return (Criteria) this;
        }

        public Criteria andFeeidGreaterThanOrEqualTo(Integer value) {
            addCriterion("feeid >=", value, "feeid");
            return (Criteria) this;
        }

        public Criteria andFeeidLessThan(Integer value) {
            addCriterion("feeid <", value, "feeid");
            return (Criteria) this;
        }

        public Criteria andFeeidLessThanOrEqualTo(Integer value) {
            addCriterion("feeid <=", value, "feeid");
            return (Criteria) this;
        }

        public Criteria andFeeidIn(List<Integer> values) {
            addCriterion("feeid in", values, "feeid");
            return (Criteria) this;
        }

        public Criteria andFeeidNotIn(List<Integer> values) {
            addCriterion("feeid not in", values, "feeid");
            return (Criteria) this;
        }

        public Criteria andFeeidBetween(Integer value1, Integer value2) {
            addCriterion("feeid between", value1, value2, "feeid");
            return (Criteria) this;
        }

        public Criteria andFeeidNotBetween(Integer value1, Integer value2) {
            addCriterion("feeid not between", value1, value2, "feeid");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createtime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createtime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("createtime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("createtime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("createtime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createtime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("createtime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("createtime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("createtime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("createtime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("createtime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("createtime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andExpiretimeIsNull() {
            addCriterion("expiretime is null");
            return (Criteria) this;
        }

        public Criteria andExpiretimeIsNotNull() {
            addCriterion("expiretime is not null");
            return (Criteria) this;
        }

        public Criteria andExpiretimeEqualTo(Date value) {
            addCriterion("expiretime =", value, "expiretime");
            return (Criteria) this;
        }

        public Criteria andExpiretimeNotEqualTo(Date value) {
            addCriterion("expiretime <>", value, "expiretime");
            return (Criteria) this;
        }

        public Criteria andExpiretimeGreaterThan(Date value) {
            addCriterion("expiretime >", value, "expiretime");
            return (Criteria) this;
        }

        public Criteria andExpiretimeGreaterThanOrEqualTo(Date value) {
            addCriterion("expiretime >=", value, "expiretime");
            return (Criteria) this;
        }

        public Criteria andExpiretimeLessThan(Date value) {
            addCriterion("expiretime <", value, "expiretime");
            return (Criteria) this;
        }

        public Criteria andExpiretimeLessThanOrEqualTo(Date value) {
            addCriterion("expiretime <=", value, "expiretime");
            return (Criteria) this;
        }

        public Criteria andExpiretimeIn(List<Date> values) {
            addCriterion("expiretime in", values, "expiretime");
            return (Criteria) this;
        }

        public Criteria andExpiretimeNotIn(List<Date> values) {
            addCriterion("expiretime not in", values, "expiretime");
            return (Criteria) this;
        }

        public Criteria andExpiretimeBetween(Date value1, Date value2) {
            addCriterion("expiretime between", value1, value2, "expiretime");
            return (Criteria) this;
        }

        public Criteria andExpiretimeNotBetween(Date value1, Date value2) {
            addCriterion("expiretime not between", value1, value2, "expiretime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNull() {
            addCriterion("updatetime is null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNotNull() {
            addCriterion("updatetime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeEqualTo(Date value) {
            addCriterion("updatetime =", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotEqualTo(Date value) {
            addCriterion("updatetime <>", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThan(Date value) {
            addCriterion("updatetime >", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updatetime >=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThan(Date value) {
            addCriterion("updatetime <", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThanOrEqualTo(Date value) {
            addCriterion("updatetime <=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIn(List<Date> values) {
            addCriterion("updatetime in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotIn(List<Date> values) {
            addCriterion("updatetime not in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeBetween(Date value1, Date value2) {
            addCriterion("updatetime between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotBetween(Date value1, Date value2) {
            addCriterion("updatetime not between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andLevelIsNull() {
            addCriterion("level is null");
            return (Criteria) this;
        }

        public Criteria andLevelIsNotNull() {
            addCriterion("level is not null");
            return (Criteria) this;
        }

        public Criteria andLevelEqualTo(String value) {
            addCriterion("level =", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotEqualTo(String value) {
            addCriterion("level <>", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThan(String value) {
            addCriterion("level >", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThanOrEqualTo(String value) {
            addCriterion("level >=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThan(String value) {
            addCriterion("level <", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThanOrEqualTo(String value) {
            addCriterion("level <=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLike(String value) {
            addCriterion("level like", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotLike(String value) {
            addCriterion("level not like", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelIn(List<String> values) {
            addCriterion("level in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotIn(List<String> values) {
            addCriterion("level not in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelBetween(String value1, String value2) {
            addCriterion("level between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotBetween(String value1, String value2) {
            addCriterion("level not between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andJieshaoIsNull() {
            addCriterion("jieshao is null");
            return (Criteria) this;
        }

        public Criteria andJieshaoIsNotNull() {
            addCriterion("jieshao is not null");
            return (Criteria) this;
        }

        public Criteria andJieshaoEqualTo(String value) {
            addCriterion("jieshao =", value, "jieshao");
            return (Criteria) this;
        }

        public Criteria andJieshaoNotEqualTo(String value) {
            addCriterion("jieshao <>", value, "jieshao");
            return (Criteria) this;
        }

        public Criteria andJieshaoGreaterThan(String value) {
            addCriterion("jieshao >", value, "jieshao");
            return (Criteria) this;
        }

        public Criteria andJieshaoGreaterThanOrEqualTo(String value) {
            addCriterion("jieshao >=", value, "jieshao");
            return (Criteria) this;
        }

        public Criteria andJieshaoLessThan(String value) {
            addCriterion("jieshao <", value, "jieshao");
            return (Criteria) this;
        }

        public Criteria andJieshaoLessThanOrEqualTo(String value) {
            addCriterion("jieshao <=", value, "jieshao");
            return (Criteria) this;
        }

        public Criteria andJieshaoLike(String value) {
            addCriterion("jieshao like", value, "jieshao");
            return (Criteria) this;
        }

        public Criteria andJieshaoNotLike(String value) {
            addCriterion("jieshao not like", value, "jieshao");
            return (Criteria) this;
        }

        public Criteria andJieshaoIn(List<String> values) {
            addCriterion("jieshao in", values, "jieshao");
            return (Criteria) this;
        }

        public Criteria andJieshaoNotIn(List<String> values) {
            addCriterion("jieshao not in", values, "jieshao");
            return (Criteria) this;
        }

        public Criteria andJieshaoBetween(String value1, String value2) {
            addCriterion("jieshao between", value1, value2, "jieshao");
            return (Criteria) this;
        }

        public Criteria andJieshaoNotBetween(String value1, String value2) {
            addCriterion("jieshao not between", value1, value2, "jieshao");
            return (Criteria) this;
        }

        public Criteria andContentIsNull() {
            addCriterion("content is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("content is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("content =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("content <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("content >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("content >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("content <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("content <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("content like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("content not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("content in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("content not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("content between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("content not between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andJiamiIsNull() {
            addCriterion("jiami is null");
            return (Criteria) this;
        }

        public Criteria andJiamiIsNotNull() {
            addCriterion("jiami is not null");
            return (Criteria) this;
        }

        public Criteria andJiamiEqualTo(String value) {
            addCriterion("jiami =", value, "jiami");
            return (Criteria) this;
        }

        public Criteria andJiamiNotEqualTo(String value) {
            addCriterion("jiami <>", value, "jiami");
            return (Criteria) this;
        }

        public Criteria andJiamiGreaterThan(String value) {
            addCriterion("jiami >", value, "jiami");
            return (Criteria) this;
        }

        public Criteria andJiamiGreaterThanOrEqualTo(String value) {
            addCriterion("jiami >=", value, "jiami");
            return (Criteria) this;
        }

        public Criteria andJiamiLessThan(String value) {
            addCriterion("jiami <", value, "jiami");
            return (Criteria) this;
        }

        public Criteria andJiamiLessThanOrEqualTo(String value) {
            addCriterion("jiami <=", value, "jiami");
            return (Criteria) this;
        }

        public Criteria andJiamiLike(String value) {
            addCriterion("jiami like", value, "jiami");
            return (Criteria) this;
        }

        public Criteria andJiamiNotLike(String value) {
            addCriterion("jiami not like", value, "jiami");
            return (Criteria) this;
        }

        public Criteria andJiamiIn(List<String> values) {
            addCriterion("jiami in", values, "jiami");
            return (Criteria) this;
        }

        public Criteria andJiamiNotIn(List<String> values) {
            addCriterion("jiami not in", values, "jiami");
            return (Criteria) this;
        }

        public Criteria andJiamiBetween(String value1, String value2) {
            addCriterion("jiami between", value1, value2, "jiami");
            return (Criteria) this;
        }

        public Criteria andJiamiNotBetween(String value1, String value2) {
            addCriterion("jiami not between", value1, value2, "jiami");
            return (Criteria) this;
        }

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(Double value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(Double value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(Double value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(Double value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(Double value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(Double value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<Double> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<Double> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(Double value1, Double value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(Double value1, Double value2) {
            addCriterion("price not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andSellstatusIsNull() {
            addCriterion("sellstatus is null");
            return (Criteria) this;
        }

        public Criteria andSellstatusIsNotNull() {
            addCriterion("sellstatus is not null");
            return (Criteria) this;
        }

        public Criteria andSellstatusEqualTo(String value) {
            addCriterion("sellstatus =", value, "sellstatus");
            return (Criteria) this;
        }

        public Criteria andSellstatusNotEqualTo(String value) {
            addCriterion("sellstatus <>", value, "sellstatus");
            return (Criteria) this;
        }

        public Criteria andSellstatusGreaterThan(String value) {
            addCriterion("sellstatus >", value, "sellstatus");
            return (Criteria) this;
        }

        public Criteria andSellstatusGreaterThanOrEqualTo(String value) {
            addCriterion("sellstatus >=", value, "sellstatus");
            return (Criteria) this;
        }

        public Criteria andSellstatusLessThan(String value) {
            addCriterion("sellstatus <", value, "sellstatus");
            return (Criteria) this;
        }

        public Criteria andSellstatusLessThanOrEqualTo(String value) {
            addCriterion("sellstatus <=", value, "sellstatus");
            return (Criteria) this;
        }

        public Criteria andSellstatusLike(String value) {
            addCriterion("sellstatus like", value, "sellstatus");
            return (Criteria) this;
        }

        public Criteria andSellstatusNotLike(String value) {
            addCriterion("sellstatus not like", value, "sellstatus");
            return (Criteria) this;
        }

        public Criteria andSellstatusIn(List<String> values) {
            addCriterion("sellstatus in", values, "sellstatus");
            return (Criteria) this;
        }

        public Criteria andSellstatusNotIn(List<String> values) {
            addCriterion("sellstatus not in", values, "sellstatus");
            return (Criteria) this;
        }

        public Criteria andSellstatusBetween(String value1, String value2) {
            addCriterion("sellstatus between", value1, value2, "sellstatus");
            return (Criteria) this;
        }

        public Criteria andSellstatusNotBetween(String value1, String value2) {
            addCriterion("sellstatus not between", value1, value2, "sellstatus");
            return (Criteria) this;
        }

        public Criteria andPingjiaIsNull() {
            addCriterion("pingjia is null");
            return (Criteria) this;
        }

        public Criteria andPingjiaIsNotNull() {
            addCriterion("pingjia is not null");
            return (Criteria) this;
        }

        public Criteria andPingjiaEqualTo(String value) {
            addCriterion("pingjia =", value, "pingjia");
            return (Criteria) this;
        }

        public Criteria andPingjiaNotEqualTo(String value) {
            addCriterion("pingjia <>", value, "pingjia");
            return (Criteria) this;
        }

        public Criteria andPingjiaGreaterThan(String value) {
            addCriterion("pingjia >", value, "pingjia");
            return (Criteria) this;
        }

        public Criteria andPingjiaGreaterThanOrEqualTo(String value) {
            addCriterion("pingjia >=", value, "pingjia");
            return (Criteria) this;
        }

        public Criteria andPingjiaLessThan(String value) {
            addCriterion("pingjia <", value, "pingjia");
            return (Criteria) this;
        }

        public Criteria andPingjiaLessThanOrEqualTo(String value) {
            addCriterion("pingjia <=", value, "pingjia");
            return (Criteria) this;
        }

        public Criteria andPingjiaLike(String value) {
            addCriterion("pingjia like", value, "pingjia");
            return (Criteria) this;
        }

        public Criteria andPingjiaNotLike(String value) {
            addCriterion("pingjia not like", value, "pingjia");
            return (Criteria) this;
        }

        public Criteria andPingjiaIn(List<String> values) {
            addCriterion("pingjia in", values, "pingjia");
            return (Criteria) this;
        }

        public Criteria andPingjiaNotIn(List<String> values) {
            addCriterion("pingjia not in", values, "pingjia");
            return (Criteria) this;
        }

        public Criteria andPingjiaBetween(String value1, String value2) {
            addCriterion("pingjia between", value1, value2, "pingjia");
            return (Criteria) this;
        }

        public Criteria andPingjiaNotBetween(String value1, String value2) {
            addCriterion("pingjia not between", value1, value2, "pingjia");
            return (Criteria) this;
        }

        public Criteria andPingjiafenshuIsNull() {
            addCriterion("pingjiafenshu is null");
            return (Criteria) this;
        }

        public Criteria andPingjiafenshuIsNotNull() {
            addCriterion("pingjiafenshu is not null");
            return (Criteria) this;
        }

        public Criteria andPingjiafenshuEqualTo(Integer value) {
            addCriterion("pingjiafenshu =", value, "pingjiafenshu");
            return (Criteria) this;
        }

        public Criteria andPingjiafenshuNotEqualTo(Integer value) {
            addCriterion("pingjiafenshu <>", value, "pingjiafenshu");
            return (Criteria) this;
        }

        public Criteria andPingjiafenshuGreaterThan(Integer value) {
            addCriterion("pingjiafenshu >", value, "pingjiafenshu");
            return (Criteria) this;
        }

        public Criteria andPingjiafenshuGreaterThanOrEqualTo(Integer value) {
            addCriterion("pingjiafenshu >=", value, "pingjiafenshu");
            return (Criteria) this;
        }

        public Criteria andPingjiafenshuLessThan(Integer value) {
            addCriterion("pingjiafenshu <", value, "pingjiafenshu");
            return (Criteria) this;
        }

        public Criteria andPingjiafenshuLessThanOrEqualTo(Integer value) {
            addCriterion("pingjiafenshu <=", value, "pingjiafenshu");
            return (Criteria) this;
        }

        public Criteria andPingjiafenshuIn(List<Integer> values) {
            addCriterion("pingjiafenshu in", values, "pingjiafenshu");
            return (Criteria) this;
        }

        public Criteria andPingjiafenshuNotIn(List<Integer> values) {
            addCriterion("pingjiafenshu not in", values, "pingjiafenshu");
            return (Criteria) this;
        }

        public Criteria andPingjiafenshuBetween(Integer value1, Integer value2) {
            addCriterion("pingjiafenshu between", value1, value2, "pingjiafenshu");
            return (Criteria) this;
        }

        public Criteria andPingjiafenshuNotBetween(Integer value1, Integer value2) {
            addCriterion("pingjiafenshu not between", value1, value2, "pingjiafenshu");
            return (Criteria) this;
        }

        public Criteria andTagIsNull() {
            addCriterion("tag is null");
            return (Criteria) this;
        }

        public Criteria andTagIsNotNull() {
            addCriterion("tag is not null");
            return (Criteria) this;
        }

        public Criteria andTagEqualTo(String value) {
            addCriterion("tag =", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotEqualTo(String value) {
            addCriterion("tag <>", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagGreaterThan(String value) {
            addCriterion("tag >", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagGreaterThanOrEqualTo(String value) {
            addCriterion("tag >=", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagLessThan(String value) {
            addCriterion("tag <", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagLessThanOrEqualTo(String value) {
            addCriterion("tag <=", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagLike(String value) {
            addCriterion("tag like", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotLike(String value) {
            addCriterion("tag not like", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagIn(List<String> values) {
            addCriterion("tag in", values, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotIn(List<String> values) {
            addCriterion("tag not in", values, "tag");
            return (Criteria) this;
        }

        public Criteria andTagBetween(String value1, String value2) {
            addCriterion("tag between", value1, value2, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotBetween(String value1, String value2) {
            addCriterion("tag not between", value1, value2, "tag");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andPicturesIsNull() {
            addCriterion("pictures is null");
            return (Criteria) this;
        }

        public Criteria andPicturesIsNotNull() {
            addCriterion("pictures is not null");
            return (Criteria) this;
        }

        public Criteria andPicturesEqualTo(String value) {
            addCriterion("pictures =", value, "pictures");
            return (Criteria) this;
        }

        public Criteria andPicturesNotEqualTo(String value) {
            addCriterion("pictures <>", value, "pictures");
            return (Criteria) this;
        }

        public Criteria andPicturesGreaterThan(String value) {
            addCriterion("pictures >", value, "pictures");
            return (Criteria) this;
        }

        public Criteria andPicturesGreaterThanOrEqualTo(String value) {
            addCriterion("pictures >=", value, "pictures");
            return (Criteria) this;
        }

        public Criteria andPicturesLessThan(String value) {
            addCriterion("pictures <", value, "pictures");
            return (Criteria) this;
        }

        public Criteria andPicturesLessThanOrEqualTo(String value) {
            addCriterion("pictures <=", value, "pictures");
            return (Criteria) this;
        }

        public Criteria andPicturesLike(String value) {
            addCriterion("pictures like", value, "pictures");
            return (Criteria) this;
        }

        public Criteria andPicturesNotLike(String value) {
            addCriterion("pictures not like", value, "pictures");
            return (Criteria) this;
        }

        public Criteria andPicturesIn(List<String> values) {
            addCriterion("pictures in", values, "pictures");
            return (Criteria) this;
        }

        public Criteria andPicturesNotIn(List<String> values) {
            addCriterion("pictures not in", values, "pictures");
            return (Criteria) this;
        }

        public Criteria andPicturesBetween(String value1, String value2) {
            addCriterion("pictures between", value1, value2, "pictures");
            return (Criteria) this;
        }

        public Criteria andPicturesNotBetween(String value1, String value2) {
            addCriterion("pictures not between", value1, value2, "pictures");
            return (Criteria) this;
        }

        public Criteria andFujianIsNull() {
            addCriterion("fujian is null");
            return (Criteria) this;
        }

        public Criteria andFujianIsNotNull() {
            addCriterion("fujian is not null");
            return (Criteria) this;
        }

        public Criteria andFujianEqualTo(String value) {
            addCriterion("fujian =", value, "fujian");
            return (Criteria) this;
        }

        public Criteria andFujianNotEqualTo(String value) {
            addCriterion("fujian <>", value, "fujian");
            return (Criteria) this;
        }

        public Criteria andFujianGreaterThan(String value) {
            addCriterion("fujian >", value, "fujian");
            return (Criteria) this;
        }

        public Criteria andFujianGreaterThanOrEqualTo(String value) {
            addCriterion("fujian >=", value, "fujian");
            return (Criteria) this;
        }

        public Criteria andFujianLessThan(String value) {
            addCriterion("fujian <", value, "fujian");
            return (Criteria) this;
        }

        public Criteria andFujianLessThanOrEqualTo(String value) {
            addCriterion("fujian <=", value, "fujian");
            return (Criteria) this;
        }

        public Criteria andFujianLike(String value) {
            addCriterion("fujian like", value, "fujian");
            return (Criteria) this;
        }

        public Criteria andFujianNotLike(String value) {
            addCriterion("fujian not like", value, "fujian");
            return (Criteria) this;
        }

        public Criteria andFujianIn(List<String> values) {
            addCriterion("fujian in", values, "fujian");
            return (Criteria) this;
        }

        public Criteria andFujianNotIn(List<String> values) {
            addCriterion("fujian not in", values, "fujian");
            return (Criteria) this;
        }

        public Criteria andFujianBetween(String value1, String value2) {
            addCriterion("fujian between", value1, value2, "fujian");
            return (Criteria) this;
        }

        public Criteria andFujianNotBetween(String value1, String value2) {
            addCriterion("fujian not between", value1, value2, "fujian");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}