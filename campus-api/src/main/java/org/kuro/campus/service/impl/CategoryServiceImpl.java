package org.kuro.campus.service.impl;

import org.kuro.campus.mapper.CategoryMapper;
import org.kuro.campus.model.entity.Category;
import org.kuro.campus.model.vo.CategoryVo;
import org.kuro.campus.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/2/4 14:35
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 获取所有分类
     * @return
     */
    @Override
    public List<CategoryVo> findCategories() {
        // 先查询 redis缓存 中是否存在 分类 集合
        List<CategoryVo> categoryVos = redisTemplate.opsForList().range("categoryVos", 0, -1);
        if (categoryVos == null || categoryVos.size() == 0) {
            // 获取所有的分类
            List<Category> all = categoryMapper.selectAll();
            // 先取出所有的父级分类
            for (Category category : all) {
                if (category.getParentId() == 0) {
                    CategoryVo categoryVo = new CategoryVo();
                    BeanUtils.copyProperties(category, categoryVo);
                    categoryVos.add(categoryVo);
                }
            }
            // 循环父级分类，获取子级分类
            for (CategoryVo vo : categoryVos) {
                List<Category> child = new ArrayList<>();
                for (Category category : all) {
                    if (category.getParentId() == 0) {
                        continue;
                    }
                    if (category.getParentId() == vo.getId()) {
                        child.add(category);
                    }
                }
                vo.setChildren(child);
            }
            // 将数据存储到 redis，再返回
            redisTemplate.opsForList().rightPushAll("categoryVos", categoryVos);
        }

        return categoryVos;
    }
}
