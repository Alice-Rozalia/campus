package org.kuro.campus.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.kuro.campus.mapper.GoodsMapper;
import org.kuro.campus.model.entity.Goods;
import org.kuro.campus.model.entity.User;
import org.kuro.campus.model.response.Result;
import org.kuro.campus.model.response.ResultCode;
import org.kuro.campus.service.GoodsService;
import org.kuro.campus.utils.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/2/4 18:05
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

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
        goods.setCreateDate(new Date());
        goods.setUserId(user.getId());
        goodsMapper.insertSelective(goods);
        return Result.ok(ResultCode.GOODS_PUBLISH_SUCCESS);
    }
}
