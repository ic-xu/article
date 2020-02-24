package com.article.service.mvc.check.update;

import com.common.mvc.check.version.AppVersionEntity;
import com.common.mvc.route.bean.ResposServiceInfo;
import com.common.utils.BaseResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CheckNewVersionControllor {


    @PostMapping("/check/app/version")
    public BaseResponseDto checkNewVersion(@RequestBody AppVersionEntity appVersionEntity){
        Map<String,Object> resposeDate = new HashMap<>();

//        System.err.println(serverCache.getAll().size());
//        serverCache.getAll().forEach(serverString -> {
//            ServiceInfo serviceInfo = JSON.parseObject(serverString, ServiceInfo.class);
//            String url = "http://" + serviceInfo.getOutIp() + ":" + serviceInfo.getHttpPort() + "/getUserCount";
//            long serviceOnlneCount = Long.parseLong(accountService.getServiceOnlneCount(url));
//            treeMap.put(serviceOnlneCount, serviceInfo);
//
//        });
//        if (null == treeMap.firstEntry())
//            throw new Exception("IM服务器异常");
//        ServiceInfo value = treeMap.firstEntry().getValue();
        ResposServiceInfo resposServiceInfo = new ResposServiceInfo();
//        resposServiceInfo.setHttpPort(value.getHttpPort());
//        resposServiceInfo.setServerPort(value.getNettyPort());
//        resposServiceInfo.setUrl(value.getOutIp());
        resposeDate.put("serviceInfo",resposServiceInfo);
        resposeDate.put("appVersion",appVersionEntity);
        return BaseResponseDto.success(resposeDate);
    }
}
