package com.jw.elephant.capcplus.service;

import com.alibaba.fastjson.JSONObject;
import com.jw.elephant.capcplus.constant.TextConstant;
import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

public interface FileService {
    /**
     * 保存单个文件到本地，接收参数为 multipartFile
     * @param request
     * @param file
     * @return
     * @throws IOException
     */
    default JSONObject saveLocalFile(HttpServletRequest request, MultipartFile file) throws IOException {
        JSONObject jobject = new JSONObject();
        if (file == null) {
            jobject.put("code","0001");
            jobject.put("msg", "文件为空");
            return jobject;
        }
        String oriName = file.getOriginalFilename().trim();
        String suffix = oriName.substring(oriName.lastIndexOf(".") + 1);
        String saveName = java.util.UUID.randomUUID().toString() + "." + suffix;
        // 获取当前请求上下文路径
        String realPath = request.getServletContext().getRealPath("/resources/");
        // 上传到本地
        String folder = realPath+TextConstant.UPLOADER_IMG_FLODER;
        FileUtils.copyInputStreamToFile(file.getInputStream(), new File(folder, saveName));
        // 单位：KB
        jobject.put("size", file.getSize());
        jobject.put("code", "0000");
        jobject.put("state", "SUCCESS");
        jobject.put("saveName", saveName);
        jobject.put("name", saveName);
        jobject.put("originalName", oriName);
        jobject.put("filePath", folder + "/" + saveName);
        return jobject;
    }

}
