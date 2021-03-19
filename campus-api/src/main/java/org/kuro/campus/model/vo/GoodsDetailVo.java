package org.kuro.campus.model.vo;

import lombok.Data;
import org.kuro.campus.model.entity.Goods;
import org.kuro.campus.model.entity.Image;

import java.util.List;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/2/27 19:27
 */
@Data
public class GoodsDetailVo extends Goods {

    private List<Image> images;

    private String category;

    private String username;

    private String avatar;

    private String address;
}
