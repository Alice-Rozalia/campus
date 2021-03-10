package org.kuro.campus.service.impl;

import org.kuro.campus.mapper.SwiperMapper;
import org.kuro.campus.service.SwiperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/3/9 21:05
 */
@Service
public class SwiperServiceImpl implements SwiperService {

    @Autowired
    private SwiperMapper swiperMapper;

}
