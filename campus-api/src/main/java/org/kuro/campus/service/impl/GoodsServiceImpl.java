package org.kuro.campus.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.kuro.campus.mapper.GoodsMapper;
import org.kuro.campus.mapper.ImageMapper;
import org.kuro.campus.model.entity.Goods;
import org.kuro.campus.model.entity.Image;
import org.kuro.campus.model.entity.User;
import org.kuro.campus.model.page.PageResult;
import org.kuro.campus.model.response.Result;
import org.kuro.campus.model.response.ResultCode;
import org.kuro.campus.model.vo.GoodsDetailVo;
import org.kuro.campus.model.vo.GoodsVo;
import org.kuro.campus.service.GoodsService;
import org.kuro.campus.utils.CurrentUser;
import org.kuro.campus.utils.SensitiveFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/2/4 18:05
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private ImageMapper imageMapper;

    @Autowired
    private SensitiveFilter sensitiveFilter;

    /**
     * 发布商品
     *
     * @param goods
     * @return
     */
    @Override
    public Result publish(Goods goods) {
        User user = CurrentUser.getCurrentUser();
        if (StringUtils.isBlank(user.getPhone()) || StringUtils.isBlank(user.getAddress())) {
            return Result.error(ResultCode.PHONE_IS_NOT_BIND);
        }
        // 过滤敏感词
        goods.setIntroduce(sensitiveFilter.filter(goods.getIntroduce()));
        goods.setName(sensitiveFilter.filter(goods.getName()));

        goods.setCreateDate(new Date());
        goods.setUserId(user.getId());
        goodsMapper.insertSelective(goods);
        return Result.ok(ResultCode.GOODS_PUBLISH_SUCCESS).data("id", goods.getId());
    }

    /**
     * 首页商品
     *
     * @param page
     * @param limit
     * @param sort
     * @param order
     * @return
     */
    @Override
    public Result indexGoods(Integer page, Integer limit, String sort, String order, String key) {
        page = (page - 1) * limit;
        List<GoodsVo> goods = goodsMapper.indexGoods(page, limit, sort, order, key);
        Integer total = goodsMapper.goodsCount();
        for (GoodsVo good : goods) {
            Image image = imageMapper.findOneImageByGoodsId(good.getId());
            good.setCover(image.getUrl());
        }
        PageResult<GoodsVo> result = new PageResult<>(total, goods);
        return Result.ok().data("goods", result);
    }

    /**
     * 商品详情
     *
     * @param goodsId
     * @return
     */
    @Override
    public GoodsDetailVo goodsDetail(Integer goodsId) {
        return goodsMapper.goodsDetailById(goodsId);
    }

    /**
     * 根据id查询商品
     *
     * @param goodsId
     * @return
     */
    @Override
    public Goods findGoodsById(Integer goodsId) {
        return goodsMapper.findGoodsById(goodsId);
    }

    /**
     * 根据用户 id 查询该用户发布的商品
     *
     * @param page
     * @param limit
     * @param userId
     * @return
     */
    @Override
    public PageResult<Goods> findMyGoods(Integer page, Integer limit, Integer userId) {
        List<Goods> goods = goodsMapper.findMyGoods(page, limit, userId);
        Integer total = goodsMapper.findMyGoodsCount(userId);
        return new PageResult<>(total, goods);
    }

    /**
     * 修改商品
     *
     * @param goods
     * @return
     */
    @Override
    public Integer updateGoods(Goods goods) {
        return goodsMapper.updateByPrimaryKeySelective(goods);
    }

    /**
     * 根据分类id查询首页商品
     *
     * @param page       当前页
     * @param limit      每页显示条数
     * @param categoryId 分类id
     * @return GoodsVo的分页对象
     */
    @Override
    public PageResult<GoodsVo> findIndexGoodsByCategoryId(Integer page, Integer limit, Integer categoryId) {
        page = (page - 1) * limit;
        List<GoodsVo> goods = goodsMapper.indexGoodsByCategoryId(page, limit, categoryId);
        Integer total = goodsMapper.indexGoodsByCategoryIdCount(categoryId);
        for (GoodsVo good : goods) {
            Image image = imageMapper.findOneImageByGoodsId(good.getId());
            good.setCover(image.getUrl());
        }
        return new PageResult<>(total, goods);
    }

    @Override
    public PageResult<Goods> findAllGoods(Integer page, Integer limit) {
        if (page != null && limit != null) {
            PageHelper.startPage(page, limit);
        }
        List<Goods> goods = goodsMapper.selectAll();
        PageInfo<Goods> pageInfo = new PageInfo<>(goods);
        return new PageResult<>(Math.toIntExact(pageInfo.getTotal()), goods);
    }
}
