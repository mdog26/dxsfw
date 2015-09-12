package com.dxsfw.pub.model;

import java.util.ArrayList;
import java.util.List;

import com.dxsfw.common.base.BaseExample;

public class FujianExample extends BaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FujianExample() {
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

        public Criteria andFujianidIsNull() {
            addCriterion("fujianid is null");
            return (Criteria) this;
        }

        public Criteria andFujianidIsNotNull() {
            addCriterion("fujianid is not null");
            return (Criteria) this;
        }

        public Criteria andFujianidEqualTo(Integer value) {
            addCriterion("fujianid =", value, "fujianid");
            return (Criteria) this;
        }

        public Criteria andFujianidNotEqualTo(Integer value) {
            addCriterion("fujianid <>", value, "fujianid");
            return (Criteria) this;
        }

        public Criteria andFujianidGreaterThan(Integer value) {
            addCriterion("fujianid >", value, "fujianid");
            return (Criteria) this;
        }

        public Criteria andFujianidGreaterThanOrEqualTo(Integer value) {
            addCriterion("fujianid >=", value, "fujianid");
            return (Criteria) this;
        }

        public Criteria andFujianidLessThan(Integer value) {
            addCriterion("fujianid <", value, "fujianid");
            return (Criteria) this;
        }

        public Criteria andFujianidLessThanOrEqualTo(Integer value) {
            addCriterion("fujianid <=", value, "fujianid");
            return (Criteria) this;
        }

        public Criteria andFujianidIn(List<Integer> values) {
            addCriterion("fujianid in", values, "fujianid");
            return (Criteria) this;
        }

        public Criteria andFujianidNotIn(List<Integer> values) {
            addCriterion("fujianid not in", values, "fujianid");
            return (Criteria) this;
        }

        public Criteria andFujianidBetween(Integer value1, Integer value2) {
            addCriterion("fujianid between", value1, value2, "fujianid");
            return (Criteria) this;
        }

        public Criteria andFujianidNotBetween(Integer value1, Integer value2) {
            addCriterion("fujianid not between", value1, value2, "fujianid");
            return (Criteria) this;
        }

        public Criteria andTablenameIsNull() {
            addCriterion("tablename is null");
            return (Criteria) this;
        }

        public Criteria andTablenameIsNotNull() {
            addCriterion("tablename is not null");
            return (Criteria) this;
        }

        public Criteria andTablenameEqualTo(String value) {
            addCriterion("tablename =", value, "tablename");
            return (Criteria) this;
        }

        public Criteria andTablenameNotEqualTo(String value) {
            addCriterion("tablename <>", value, "tablename");
            return (Criteria) this;
        }

        public Criteria andTablenameGreaterThan(String value) {
            addCriterion("tablename >", value, "tablename");
            return (Criteria) this;
        }

        public Criteria andTablenameGreaterThanOrEqualTo(String value) {
            addCriterion("tablename >=", value, "tablename");
            return (Criteria) this;
        }

        public Criteria andTablenameLessThan(String value) {
            addCriterion("tablename <", value, "tablename");
            return (Criteria) this;
        }

        public Criteria andTablenameLessThanOrEqualTo(String value) {
            addCriterion("tablename <=", value, "tablename");
            return (Criteria) this;
        }

        public Criteria andTablenameLike(String value) {
            addCriterion("tablename like", value, "tablename");
            return (Criteria) this;
        }

        public Criteria andTablenameNotLike(String value) {
            addCriterion("tablename not like", value, "tablename");
            return (Criteria) this;
        }

        public Criteria andTablenameIn(List<String> values) {
            addCriterion("tablename in", values, "tablename");
            return (Criteria) this;
        }

        public Criteria andTablenameNotIn(List<String> values) {
            addCriterion("tablename not in", values, "tablename");
            return (Criteria) this;
        }

        public Criteria andTablenameBetween(String value1, String value2) {
            addCriterion("tablename between", value1, value2, "tablename");
            return (Criteria) this;
        }

        public Criteria andTablenameNotBetween(String value1, String value2) {
            addCriterion("tablename not between", value1, value2, "tablename");
            return (Criteria) this;
        }

        public Criteria andPkIsNull() {
            addCriterion("pk is null");
            return (Criteria) this;
        }

        public Criteria andPkIsNotNull() {
            addCriterion("pk is not null");
            return (Criteria) this;
        }

        public Criteria andPkEqualTo(Integer value) {
            addCriterion("pk =", value, "pk");
            return (Criteria) this;
        }

        public Criteria andPkNotEqualTo(Integer value) {
            addCriterion("pk <>", value, "pk");
            return (Criteria) this;
        }

        public Criteria andPkGreaterThan(Integer value) {
            addCriterion("pk >", value, "pk");
            return (Criteria) this;
        }

        public Criteria andPkGreaterThanOrEqualTo(Integer value) {
            addCriterion("pk >=", value, "pk");
            return (Criteria) this;
        }

        public Criteria andPkLessThan(Integer value) {
            addCriterion("pk <", value, "pk");
            return (Criteria) this;
        }

        public Criteria andPkLessThanOrEqualTo(Integer value) {
            addCriterion("pk <=", value, "pk");
            return (Criteria) this;
        }

        public Criteria andPkIn(List<Integer> values) {
            addCriterion("pk in", values, "pk");
            return (Criteria) this;
        }

        public Criteria andPkNotIn(List<Integer> values) {
            addCriterion("pk not in", values, "pk");
            return (Criteria) this;
        }

        public Criteria andPkBetween(Integer value1, Integer value2) {
            addCriterion("pk between", value1, value2, "pk");
            return (Criteria) this;
        }

        public Criteria andPkNotBetween(Integer value1, Integer value2) {
            addCriterion("pk not between", value1, value2, "pk");
            return (Criteria) this;
        }

        public Criteria andPathIsNull() {
            addCriterion("path is null");
            return (Criteria) this;
        }

        public Criteria andPathIsNotNull() {
            addCriterion("path is not null");
            return (Criteria) this;
        }

        public Criteria andPathEqualTo(String value) {
            addCriterion("path =", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathNotEqualTo(String value) {
            addCriterion("path <>", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathGreaterThan(String value) {
            addCriterion("path >", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathGreaterThanOrEqualTo(String value) {
            addCriterion("path >=", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathLessThan(String value) {
            addCriterion("path <", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathLessThanOrEqualTo(String value) {
            addCriterion("path <=", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathLike(String value) {
            addCriterion("path like", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathNotLike(String value) {
            addCriterion("path not like", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathIn(List<String> values) {
            addCriterion("path in", values, "path");
            return (Criteria) this;
        }

        public Criteria andPathNotIn(List<String> values) {
            addCriterion("path not in", values, "path");
            return (Criteria) this;
        }

        public Criteria andPathBetween(String value1, String value2) {
            addCriterion("path between", value1, value2, "path");
            return (Criteria) this;
        }

        public Criteria andPathNotBetween(String value1, String value2) {
            addCriterion("path not between", value1, value2, "path");
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