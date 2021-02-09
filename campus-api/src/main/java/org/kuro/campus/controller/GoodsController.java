package org.kuro.campus.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.kuro.campus.model.entity.Goods;
import org.kuro.campus.model.response.Result;
import org.kuro.campus.model.response.ResultCode;
import org.kuro.campus.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @PostMapping("/pri/goods/publish")
    @ApiOperation(value = "发布商品", notes = "发布商品")
    public Result publish(@RequestBody @Valid Goods goods) {
        return goodsService.publish(goods);
    }
}
