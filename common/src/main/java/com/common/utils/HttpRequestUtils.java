package com.common.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by chenxu
 * On 19-8-26 上午9:47
 */

public class HttpRequestUtils {

    public static String sendRequest(CloseableHttpClient client,String url, String mapToJSONstring) throws IOException {

//        HttpGet httpGet = new HttpGet(url);
        HttpPost httpPost = new HttpPost(url);
        // 设置超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000)
                .setConnectionRequestTimeout(5000)
                .setSocketTimeout(5000).build();
        httpPost.setConfig(requestConfig);

        StringEntity stringEntity = new StringEntity(mapToJSONstring, "UTF-8");
        stringEntity.setContentEncoding("UTF-8");
        stringEntity.setContentType("application/json");
        httpPost.setEntity(stringEntity);
        CloseableHttpResponse response = null;
        StringBuilder responseString = new StringBuilder();
        BufferedReader reader = null;

        try {
            response = client.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity resEntity = response.getEntity();
                if (null != resEntity) {
                    reader = new BufferedReader(new InputStreamReader(resEntity.getContent()));
                    String inputLine;
                    while ((inputLine = reader.readLine()) != null) {
                        responseString.append(inputLine);
                    }
                    return responseString.toString();
                }
            }
            return null;
        } finally {
            try {
                assert reader != null;
                reader.close();
                response.close();
            } catch (Exception ignored) {

            }
        }
    }

}
