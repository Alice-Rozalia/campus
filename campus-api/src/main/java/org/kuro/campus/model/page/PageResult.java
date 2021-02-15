package org.kuro.campus.model.page;

import java.util.List;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2020/12/23 15:38
 */
public class PageResult<T> {

    private Integer total;
    private List<T> items;

    public PageResult() {
    }

    public PageResult(Integer total, List<T> items) {
        this.total = total;
        this.items = items;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
