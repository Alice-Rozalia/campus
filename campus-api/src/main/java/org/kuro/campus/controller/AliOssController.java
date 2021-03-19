package org.kuro.campus.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.kuro.campus.model.response.Result;
import org.kuro.campus.service.AliOssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1")
@Api(value = "文件管理模块", tags = "文件管理")
public class AliOssController {

    @Autowired
    private AliOssService aliOssService;

    @RequiresPermissions({"upload:upload"})
    @ApiOperation(value = "上传图片", notes = "上传图片文件")
    @PostMapping("/pri/alioss/upload")
    public Result uploadImgFile(MultipartFile file) {
        String s = aliOssService.upload(file);
        return Result.ok().data("url", s);
    }

    @RequiresPermissions({"upload:delete"})
    @ApiOperation(value = "删除图片", notes = "根据图片名删除图片")
    @DeleteMapping("/pri/alioss/delete")
    public Result deleteImgFile(String file) {
        try {
            String[] split = file.split(".com/");
            aliOssService.deleteFile(split[1]);
            return Result.ok();
        } catch (Exception e) {
            return Result.error();
        }
    }

    @ApiOperation(value = "列举图片", notes = "列举图片")
    @GetMapping("show")
    public Result showImg() {
        return null;
    }
}
