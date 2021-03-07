package org.kuro.campus.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.kuro.campus.model.entity.Goods;
import org.kuro.campus.model.response.Result;
import org.kuro.campus.model.vo.GoodsDetailVo;
import org.kuro.campus.service.CommentService;
import org.kuro.campus.service.GoodsService;
import org.kuro.campus.service.UserService;
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

    @RequiresPermissions({"goods:publish"})
    @PostMapping("/pri/goods/publish")
    @ApiOperation(value = "发布商品", notes = "发布商品")
    public Result publish(@RequestBody @Valid Goods goods) {
        return goodsService.publish(goods);
    }

    @GetMapping("/pub/goods/index")
    @ApiOperation(value = "首页商品", notes = "获取未下架的商品")
    public Result indexGoods(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "limit", defaultValue = "8") Integer limit
    ) {
        return goodsService.indexGoods(page, limit);
    }

    @GetMapping("/pub/goods/{goodsId}")
    @ApiOperation(value = "商品详情", notes = "根据id查找商品详情")
    public Result goodsDetail(@PathVariable("goodsId") Integer goodsId) {
        GoodsDetailVo vo = goodsService.goodsDetail(goodsId);
        return Result.ok().data("goods", vo);
    }
}
