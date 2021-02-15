package org.kuro.campus.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.kuro.campus.model.entity.Image;
import org.kuro.campus.model.response.Result;
import org.kuro.campus.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/2/15 18:16
 */
@RestController
@RequestMapping("/api/v1")
@Api(value = "系统管理模块", tags = "图片管理")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @RequiresPermissions({"image:keep"})
    @PostMapping("/pri/image/keep")
    @ApiOperation(value = "记录图片", notes = "记录商品的图片")
    public Result keep(@RequestBody Image image) {
        imageService.keepImage(image);
        return Result.ok();
    }
}
