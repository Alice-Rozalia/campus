package org.kuro.campus.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.kuro.campus.model.response.Result;
import org.kuro.campus.model.vo.CategoryVo;
import org.kuro.campus.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/2/4 14:38
 */
@RestController
@RequestMapping("/api/v1")
@Api(value = "系统管理模块", tags = "分类管理")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/pub/category")
    @ApiOperation(value = "分类列表", notes = "获取所有的分类")
    public Result findCategories() {
        List<CategoryVo> categories = categoryService.findCategories();
        return Result.ok().data("categories", categories);
    }
}
