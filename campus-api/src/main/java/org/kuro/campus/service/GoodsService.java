package org.kuro.campus.service;

import org.kuro.campus.model.entity.Goods;
import org.kuro.campus.model.response.Result;

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
     * @return
     */
    Result indexGoods(Integer page, Integer limit);
}
