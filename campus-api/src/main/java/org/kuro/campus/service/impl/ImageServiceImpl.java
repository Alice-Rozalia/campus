package org.kuro.campus.service.impl;

import org.kuro.campus.mapper.ImageMapper;
import org.kuro.campus.model.entity.Image;
import org.kuro.campus.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/2/15 18:15
 */
@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageMapper imageMapper;

    @Override
    public void keepImage(Image image) {
        imageMapper.insertSelective(image);
    }
}
