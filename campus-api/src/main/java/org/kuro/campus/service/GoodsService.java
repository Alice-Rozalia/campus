package org.kuro.campus.service;

import org.kuro.campus.model.entity.Goods;
import org.kuro.campus.model.response.Result;
import org.kuro.campus.model.vo.GoodsDetailVo;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/2/4 18:05
 */
public interface GoodsService {

    /**
     * 发布商品
     * @param goods
     * @return
     */
    Result publish(Goods goods);

    /**
     * 首页商品
     * @param page
     * @param limit
     * @param qualification 查询条件
     * @return
     */
    Result indexGoods(Integer page, Integer limit, String qualification);

    /**
     * 商品详情
     * @param goodsId
     * @return
     */
    GoodsDetailVo goodsDetail(Integer goodsId);
}
