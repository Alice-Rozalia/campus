package org.kuro.campus.service;

import org.kuro.campus.model.entity.Goods;
import org.kuro.campus.model.page.PageResult;
import org.kuro.campus.model.response.Result;
import org.kuro.campus.model.vo.GoodsDetailVo;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/2/4 18:05
 */
public interface GoodsService {

    /**
     * 发布商品
     *
     * @param goods
     * @return
     */
    Result publish(Goods goods);

    /**
     * 排序条件，1表示根据创建时间排序，2表价格，3表浏览量
     *
     * @param page
     * @param limit
     * @param qualification 查询条件
     * @return
     */
    Result indexGoods(Integer page, Integer limit, Integer qualification);

    /**
     * 商品详情
     *
     * @param goodsId
     * @return
     */
    GoodsDetailVo goodsDetail(Integer goodsId);

    /**
     * 根据id查询商品
     * @param goodsId
     * @return
     */
    Goods findGoodsById(Integer goodsId);

    /**
     * 根据用户 id 查询该用户发布的商品
     * @param page
     * @param limit
     * @param userId
     * @return
     */
    PageResult<Goods> findMyGoods(Integer page, Integer limit, Integer userId);

    /**
     * 修改商品
     * @param goods
     * @return
     */
    Integer updateGoods(Goods goods);
}
