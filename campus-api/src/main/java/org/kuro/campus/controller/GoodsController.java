package org.kuro.campus.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.kuro.campus.model.entity.Goods;
import org.kuro.campus.model.entity.User;
import org.kuro.campus.model.page.PageResult;
import org.kuro.campus.model.response.Result;
import org.kuro.campus.model.response.ResultCode;
import org.kuro.campus.model.vo.GoodsDetailVo;
import org.kuro.campus.model.vo.GoodsVo;
import org.kuro.campus.service.GoodsService;
import org.kuro.campus.utils.CurrentUser;
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
            @RequestParam(value = "limit", defaultValue = "8") Integer limit,
            @RequestParam(value = "sort", defaultValue = "create_date") String sort,
            @RequestParam(value = "order", defaultValue = "DESC") String order,
            @RequestParam(value = "key", required = false) String key
    ) {
        return goodsService.indexGoods(page, limit, sort, order, key);
    }

    @GetMapping("/pub/goods/{goodsId}")
    @ApiOperation(value = "商品详情", notes = "根据id查找商品详情")
    public Result goodsDetail(@PathVariable("goodsId") Integer goodsId) {
        GoodsDetailVo vo = goodsService.goodsDetail(goodsId);
        return Result.ok().data("goods", vo);
    }

    @RequiresPermissions({"goods:my"})
    @GetMapping("/pri/goods/my")
    @ApiOperation(value = "我的商品", notes = "查询用户发布的商品")
    public Result findMyGoods(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "limit", defaultValue = "8") Integer limit
    ) {
        User user = CurrentUser.getCurrentUser();
        PageResult<Goods> result = goodsService.findMyGoods(page, limit, user.getId());
        return Result.ok().data("myGoods", result);
    }

    @RequiresPermissions({"goods:delete"})
    @PutMapping("/pri/goods/delete/{goodsId}")
    @ApiOperation(value = "删除商品", notes = "逻辑删除商品")
    public Result deleteGoods(@PathVariable("goodsId") Integer goodsId) {
        Goods goods = new Goods();
        goods.setEnabled(false);
        goods.setId(goodsId);

        goodsService.updateGoods(goods);

        return Result.ok(ResultCode.DELETE_SUCCESS);
    }

    @GetMapping("/pub/goods/index/{categoryId}")
    @ApiOperation(value = "分页查首页商品", notes = "根据分类id查询首页商品")
    public Result findIndexGoodsByCategoryId(
            @PathVariable("categoryId") Integer categoryId,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "limit", defaultValue = "8") Integer limit
    ) {
        PageResult<GoodsVo> list = goodsService.findIndexGoodsByCategoryId(page, limit, categoryId);
        return Result.ok().data("goods", list);
    }

    @RequiresPermissions({"goods:list"})
    @GetMapping("/pri/goods/list")
    @ApiOperation(value = "全部商品", notes = "获取全部商品")
    public Result getAllGoodsList(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "limit", required = false) Integer limit
    ) {
        PageResult<Goods> goods = goodsService.findAllGoods(page, limit);
        return Result.ok().data("goods", goods);
    }
}
