package org.kuro.campus.controller;

import io.swagger.annotations.Api;
import org.kuro.campus.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/2/4 18:06
 */
@RestController
@RequestMapping("/api/v1")
@Api(value = "系统管理模块", tags = "商品管理")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;


}
