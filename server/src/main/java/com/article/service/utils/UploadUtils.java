package com.article.service.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class UploadUtils {


    /**
     * 可修改文件名的上传文件
     * @param file
     * @return
     * @throws IOException
     */
    public static String fileUpload01(MultipartFile file) throws IOException {

        String end = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."), file.getOriginalFilename().length());


        String id = new IdWorker().nextId() + "-" + System.currentTimeMillis();

        String name = file.getContentType() + "/" + id + end;

        Resource resource = new ClassPathResource("static/img/");
        File filePath = resource.getFile();
        if (!filePath.exists()) {
            filePath.mkdirs();
        }

        File tmpDir = new File(filePath.getAbsolutePath() + "/" + file.getContentType());

        if(!tmpDir.exists()){
            tmpDir.mkdirs();
        }
        File dest = new File(filePath.getAbsolutePath() + "/" + name);
        file.transferTo(dest);
        //TODO 真正文件上传的地方
        return name;
    }
}
