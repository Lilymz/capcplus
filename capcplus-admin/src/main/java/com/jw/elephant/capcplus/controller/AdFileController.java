package com.jw.elephant.capcplus.controller;

import com.alibaba.fastjson.JSONObject;
import com.jw.elephant.capcplus.annotation.ControllerLog;
import com.jw.elephant.capcplus.constant.TextConstant;
import com.jw.elephant.capcplus.helper.ControllerHelper;
import com.jw.elephant.capcplus.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 文件上传
 * @author zhangjie
 */
@Controller
@Slf4j
@RequestMapping
public class AdFileController {
    @Resource
    private FileService fileService;
    /**
     * 图片文件上传接口
     *
     * @param file 文件
     * @param request 当前请求
     * @param response 当前响应
     * @throws IOException
     */
    @ControllerLog("文件上传-图片文件上传")
    @PostMapping(value = "/file-img-upload")
    public void upload(MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject jobject = new JSONObject();
        try {
            if (file.getSize() > TextConstant.MB) {
                log.debug("文件大小为：" + file.getSize() /TextConstant.MB + "MB");
            }else {
                log.debug("文件大小为：" + file.getSize() / TextConstant.KB + "KB");
            }
            jobject = fileService.saveLocalFile(request, file);
            ControllerHelper.successJson(response, jobject);
        }catch (Exception e) {
            jobject.put("state", "上传失败");
            log.error("文件上传失败", e);
            ControllerHelper.sendErrorMsgJson(response, "文件上传失败：",e);
        }
    }
    /**
     * 文件上传接口
     *
     * @param file 文件
     * @param request 当前请求
     * @param response 当前响应
     * @throws IOException
     */
    @ControllerLog("文件上传-webuploader")
    @PostMapping(value = "/file-upload")
    public void fileUpload(MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject jobject = new JSONObject();
        try {
            if (file.getSize() > TextConstant.MB) {
                log.debug("文件大小为：" + file.getSize() /TextConstant.MB + "MB");
            }else {
                log.debug("文件大小为：" + file.getSize() / TextConstant.KB + "KB");
            }
            jobject = fileService.saveLocalFile(request, file);
            ControllerHelper.successJson(response, jobject);
        }catch (Exception e) {
            jobject.put("state", "上传失败");
            log.error("文件上传失败", e);
            ControllerHelper.sendErrorMsgJson(response, "文件上传失败：",e);
        }
    }
}
