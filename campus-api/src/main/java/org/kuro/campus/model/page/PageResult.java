package org.kuro.campus.model.page;

import java.util.List;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2020/12/23 15:38
 */
public class PageResult<T> {

    private Long total;
    private List<T> items;

    public PageResult() {
    }

    public PageResult(Long total, List<T> items) {
        this.total = total;
        this.items = items;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
