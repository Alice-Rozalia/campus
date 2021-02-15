package org.kuro.campus.model.vo;

import lombok.Data;
import org.kuro.campus.model.entity.Goods;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/2/15 19:01
 */
@Data
public class GoodsVo extends Goods {

    private String cover;

    private String category;
}
