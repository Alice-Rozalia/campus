package org.kuro.campus.service;

import org.kuro.campus.model.vo.CategoryVo;

import java.util.List;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/2/4 14:35
 */
public interface CategoryService {

    /**
     * 获取所有分类
     * @return
     */
    List<CategoryVo> findCategories();
}
