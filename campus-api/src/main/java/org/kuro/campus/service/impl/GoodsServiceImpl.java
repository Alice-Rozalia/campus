package org.kuro.campus.service.impl;

import org.kuro.campus.mapper.GoodsMapper;
import org.kuro.campus.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/2/4 18:05
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;
}
