package org.kuro.campus.mapper;

import org.apache.ibatis.annotations.Param;
import org.kuro.campus.model.entity.Goods;
import org.kuro.campus.model.vo.GoodsVo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/2/4 18:04
 */
public interface GoodsMapper extends Mapper<Goods> {

    List<GoodsVo> indexGoods(@Param("page") Integer page, @Param("limit") Integer limit);

    Integer goodsCount();
}
