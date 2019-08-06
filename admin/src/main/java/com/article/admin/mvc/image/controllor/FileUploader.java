package com.article.admin.mvc.image.controllor;


import com.article.admin.utils.UploadUtils;
import io.swagger.annotations.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.common.mvc.image.entity.Images;
import com.common.utils.BaseResponseDto;
import springfox.documentation.annotations.ApiIgnore;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@RestController
@Api(tags = "文本文件上传相关")
@RequestMapping("/image")
public class FileUploader {


    /**
     * 文件上传
     *
     * @param file
     * @return
     */
    @PostMapping("/fileUpload")
    @ApiOperation(value = "文本内容里面的嵌套文件上传")
    @ApiResponses({
            @ApiResponse(code = 400, message = "上传失败"),
            @ApiResponse(code = 0, message = "上传成功")
    })
    @ResponseBody
    public BaseResponseDto fileUpload(@RequestParam(value = "file", required = false) MultipartFile file) {

        if (null == file || file.isEmpty())
            return BaseResponseDto.error(400, "上传文件为空");

        try {
            String path = UploadUtils.fileUpload01(file);
            //TODO
            return BaseResponseDto.successUpload(new Images(path, path, path, path));
        } catch (IOException e) {
            e.printStackTrace();
            return BaseResponseDto.error(400, "文件上传失败，请稍后再试");
        }
    }


    /**
     * @param file base64编码字符串
     * @return
     * @Description: 将base64编码字符串转换为图片
     * @Author:
     * @CreateTime:
     */
    @PostMapping("/generateImage")
    @ApiOperation(value = "单文件上传Bese64版本")
    @ApiImplicitParam()
    @ApiIgnore
    @ResponseBody
    public String generateImage(String file) throws IOException {

        String path;

        Resource resource = new ClassPathResource("static/img");
        File filePath = resource.getFile();
        if (!filePath.exists()) {
            filePath.mkdir();
        }

        File dest = new File(filePath.getAbsolutePath() + System.currentTimeMillis());


        if (file == null)
            return "没有数据";
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // 解密
            byte[] b = decoder.decodeBuffer(file);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }

            }
            OutputStream out = new FileOutputStream(dest);
            out.write(b);
            out.flush();
            out.close();
            System.out.println(dest);
            return dest.toString();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return "没有数据";
        }
    }


}
