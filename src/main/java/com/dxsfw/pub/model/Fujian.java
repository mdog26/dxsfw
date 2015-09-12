package com.dxsfw.pub.model;

import com.dxsfw.common.base.AbstractVo;

public class Fujian extends AbstractVo {
    private Integer fujianid;

    private String tablename;

    private Integer pk;

    private String path;

    public Integer getFujianid() {
        return fujianid;
    }

    public void setFujianid(Integer fujianid) {
        this.fujianid = fujianid;
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename == null ? null : tablename.trim();
    }

    public Integer getPk() {
        return pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }
}