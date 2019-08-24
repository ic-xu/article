package com.common.utils;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class UploadUtils {

    private static UploadUtils uploadUtils;

    private COSClient cosClient;

    public void setCosClient(COSClient cosClient) {
        this.cosClient = cosClient;
    }

    public COSClient getCosClient() {
        return cosClient;
    }


    private GridFsTemplate gridFsTemplate;

    @Autowired
    public void setGridFsTemplate(GridFsTemplate gridFsTemplate) {
        this.gridFsTemplate = gridFsTemplate;
    }

    private static final String DOMAIN = "https://head-image-1252740506.cos.ap-guangzhou.myqcloud.com";

    public static UploadUtils getInstance() {
        if (null == uploadUtils) {
            synchronized (UploadUtils.class) {
                if (null == uploadUtils) {
                    uploadUtils = new UploadUtils();
                    // 1 初始化用户身份信息（secretId, secretKey）。
                    String secretId = "AKIDsZ24tVw1zhHduydJaaQAh3aqNhU6kokQ";
                    String secretKey = "W5ScXDcmyZtxxm3HK7e2CGvOceQyGMI0";
                    COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
                    // 2 设置 bucket 的区域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
                    // clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
                    Region region = new Region("ap-guangzhou");
                    ClientConfig clientConfig = new ClientConfig(region);
                    // 3 生成 cos 客户端。
                    COSClient cosClient = new COSClient(cred, clientConfig);
                    uploadUtils.setCosClient(cosClient);
                }
            }
        }
        return uploadUtils;

    }


    /**
     * 可修改文件名的上传文件
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static String fileUpload01(MultipartFile file) throws IOException {

        String end = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."), file.getOriginalFilename().length());
        String id = IdWorker.getRandomString(1) + IdWorker.getInstance().nextId();
//        String name = "img/" + "/" + id + end;
//        Resource resource = new ClassPathResource("static/");
//        File filePath = resource.getFile();
//        if (!filePath.exists()) {
//            filePath.mkdirs();
//        }
        File tmpDir = new File(id + end);
        if (!tmpDir.exists()) {
            tmpDir.createNewFile();
        }
        file.transferTo(tmpDir);
        String s = uploadToClould("image" + File.separator + id + end, tmpDir, file);
        log.info("文件的原始路径为{}", file.getOriginalFilename());
        tmpDir.delete();
//        log.info("服务器返回的图片数据为{}", s);

        return s;
    }

    /**
     * @date  19-8-23 - 上午10:15
     * @apiNote  上传到腾讯云服务器
     */
    private static String uploadToClould(String name, File localFile, MultipartFile file) {

        try {
            // 指定要上传的文件
            // 指定要上传到的存储桶
            String bucketName = "head-image-1252740506";
            // 指定要上传到 COS 上对象键
//            String key = IdWorker.getInstance().getRandomString(1) + IdWorker.getInstance().nextId();
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, name, localFile);
            PutObjectResult putObjectResult = UploadUtils.getInstance().getCosClient().putObject(putObjectRequest);
            localFile.delete();
            return DOMAIN + File.separator + name;
        } catch (CosServiceException serverException) {
            serverException.printStackTrace();
        } catch (CosClientException clientException) {
            clientException.printStackTrace();
        }
        return "";
    }



//    /**
//     * @date  19-8-23 - 上午10:15
//     * @apiNote  上传到mongoDB服务器
//     */
//    private static String uploadToMongoDBFs(InputStream inputStream, String name, String end) {
//        gridFsTemplate.store()
//        return "";
//    }
}
