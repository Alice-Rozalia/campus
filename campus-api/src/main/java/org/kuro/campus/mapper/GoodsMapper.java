package org.kuro.campus.mapper;

import org.apache.ibatis.annotations.Param;
import org.kuro.campus.model.entity.Goods;
import org.kuro.campus.model.vo.GoodsDetailVo;
import org.kuro.campus.model.vo.GoodsVo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/2/4 18:04
 */
public interface GoodsMapper extends Mapper<Goods> {

    List<GoodsVo> indexGoods(@Param("page") Integer page, @Param("limit") Integer limit, @Param("qualification") Integer qualification);

    Integer goodsCount();

    GoodsDetailVo goodsDetailById(@Param("id") Integer id);

    Goods findGoodsById(@Param("id") Integer id);

    List<Goods> findMyGoods(@Param("page") Integer page, @Param("limit") Integer limit, @Param("userId") Integer userId);

    Integer findMyGoodsCount(@Param("userId") Integer userId);
}
