package com.common.mvc.route.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.common.mvc.route.bean.ChatReqVO;
import com.common.mvc.route.service.AccountService;
import com.common.utils.HttpRequestUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AccountServiceRedisImpl implements AccountService {

    private CloseableHttpClient closeableHttpClient;

    @Autowired
    public void setCloseableHttpClient(CloseableHttpClient closeableHttpClient) {
        this.closeableHttpClient = closeableHttpClient;
    }


    @Override
    public void pushMsg(String url, long sendUserId, ChatReqVO groupReqVO) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg", groupReqVO.getMsg());
        jsonObject.put("toUserId", groupReqVO.getToUserId());
        jsonObject.put("fromUserId", groupReqVO.getFromUserId());
        jsonObject.put("id", groupReqVO.getId());

        HttpRequestUtils.sendRequest(closeableHttpClient, url, JSON.toJSONString(jsonObject));

    }

    /**
     * @param url 服务器地址
     * @date 19-8-26 - 上午9:44
     * @apiNote des
     */
    @Cacheable(value = "60s", key = "#p0")
    @Override
    public String getServiceOnlneCount(String url) {

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("msg", "23423");


        try {
            String resposeString = HttpRequestUtils.sendRequest(closeableHttpClient, url, JSON.toJSONString(jsonObject1));

            JSONObject jsonObject = JSON.parseObject(resposeString);
            return jsonObject.getLong("dataBody") + "";

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "0";
    }


}
