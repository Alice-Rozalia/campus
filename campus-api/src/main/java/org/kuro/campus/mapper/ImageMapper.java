package org.kuro.campus.mapper;

import org.apache.ibatis.annotations.Param;
import org.kuro.campus.model.entity.Image;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/2/15 18:14
 */
public interface ImageMapper extends Mapper<Image> {

    Image findOneImageByGoodsId(@Param("goodsId") Integer goodsId);
}
