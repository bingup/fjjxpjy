package com.fjjxpjy.utils;

import java.util.List;

/**
 * @auth admin
 * @date 2020/9/16
 * @Description
 */
public class Page<T> {

    //当前页
    private Integer pageCurrent = 1;

    //总页数
    private Integer pageTotal;

    //总记录数
    private Integer count;

    //每页显示多少条数据
    private
    Integer size = 3;
    //当前页数据
    private List<T> data;

    public Integer getPageCurrent() {
        if (this.pageCurrent <= 0) {
            return 1;
        } else if (this.pageCurrent >= getPageTotal()) {
            return getPageTotal();
        }
        return pageCurrent;
    }

    public void setPageCurrent(Integer pageCurrent) {
        this.pageCurrent = pageCurrent;
    }

    public Integer getPageTotal() {
        return this.count % this.size == 0 ? this.count / this.size : this.count / this.size + 1;
    }

    public void setPageTotal(Integer pageTotal) {

        this.pageTotal = pageTotal;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public List<T>  getData() {
        return data;
    }

    public void setData(List<T>  data) {
        this.data = data;
    }
}
