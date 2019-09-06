package com.article.service.mvc.upload;

import com.common.utils.BaseResponseDto;
import com.common.utils.UploadUtils;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.qcloud.cos.utils.IOUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.gridfs.GridFsCriteria.whereFilename;

/**
 * Created by chenxu
 * On 19-8-20 下午8:06
 */

@RequestMapping("/file")
@Api(tags = "文件上传")
@RestController
public class Upload {

    private GridFsTemplate gridFsTemplate;

    @Autowired
    public void setGridFsTemplate(GridFsTemplate gridFsTemplate) {
        this.gridFsTemplate = gridFsTemplate;
    }

    @ApiOperation("上传多张图片")
    @PostMapping("/uploadfile")
    public BaseResponseDto uploadFile(@RequestParam(required = false, value = "files") MultipartFile[] files) {

        if (files.length <= 0)
            return BaseResponseDto.error(202, "上传文件列表为null");
        List<Object> imageUrls = new ArrayList<>();
        for (MultipartFile file : files) {
            String s = null;
            try {
//                uploadToMongoDBFs(file.getInputStream(), fileName, file.getContentType());
                s = UploadUtils.fileUpload01(file);
                System.err.println("文件名字为：" + s);
//                imageUrls.add("file/getImage?imagefile=" + s);
                imageUrls.add(s);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        System.err.println(imageUrls);
        return BaseResponseDto.success(imageUrls);
    }


    /**
     * @date 19-8-23 - 上午10:15
     * @apiNote 上传到mongoDB服务器
     */
    private ObjectId uploadToMongoDBFs(InputStream inputStream, String name, String contentType) {
        return gridFsTemplate.store(inputStream, name, contentType);
    }


    @GetMapping("/getImage")
    public void dowloadFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);

        Query query = query(whereFilename().is(request.getParameter("imagefile")));
        GridFSFile gfsFile = gridFsTemplate.findOne(query);

//        gridFsTemplate.delete(query);删除文件
        // 查询单个文件
//        String fileName =

        gfsFile.getFilename().replace(",", "");
        //处理中文文件名乱码
//        if (request.getHeader("User-Agent").toUpperCase().contains("MSIE") ||
//                request.getHeader("User-Agent").toUpperCase().contains("TRIDENT")
//                || request.getHeader("User-Agent").toUpperCase().contains("EDGE")) {
//            fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
//        } else {
//            //非IE浏览器的处理：
//            fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
//        }

        // 通知浏览器进行文件下载
//        response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
        GridFsResource resource = gridFsTemplate.getResource(gfsFile);
        IOUtils.copy(resource.getInputStream(), response.getOutputStream());
    }

}
