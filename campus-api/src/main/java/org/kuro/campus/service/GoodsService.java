package org.kuro.campus.service;

import org.kuro.campus.model.entity.Goods;
import org.kuro.campus.model.page.PageResult;
import org.kuro.campus.model.response.Result;
import org.kuro.campus.model.vo.GoodsDetailVo;
import org.kuro.campus.model.vo.GoodsVo;

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
     * 首页商品
     *
     * @param page
     * @param limit
     * @param sort
     * @param order
     * @return
     */
    Result indexGoods(Integer page, Integer limit, String sort, String order, String key);

    /**
     * 商品详情
     *
     * @param goodsId
     * @return
     */
    GoodsDetailVo goodsDetail(Integer goodsId);

    /**
     * 根据id查询商品
     *
     * @param goodsId
     * @return
     */
    Goods findGoodsById(Integer goodsId);

    /**
     * 根据用户 id 查询该用户发布的商品
     *
     * @param page
     * @param limit
     * @param userId
     * @return
     */
    PageResult<Goods> findMyGoods(Integer page, Integer limit, Integer userId);

    /**
     * 修改商品
     *
     * @param goods 商品对象
     * @return 影响的行数
     */
    Integer updateGoods(Goods goods);

    /**
     * 根据分类id查询首页商品
     *
     * @param page       当前页
     * @param limit      每页显示条数
     * @param categoryId 分类id
     * @return GoodsVo的分页对象
     */
    PageResult<GoodsVo> findIndexGoodsByCategoryId(Integer page, Integer limit, Integer categoryId);

    PageResult<Goods> findAllGoods(Integer page, Integer limit);
}
