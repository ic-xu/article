package com.article.service.cache;

import com.article.service.kit.ZKit;
import com.google.common.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ Author chenxu
 * @ Date 19-7-4 - 下午3:25
 * @ Description des 服务器节点缓存
 */
@Component
public class ServerCache {

    private LoadingCache<String, String> cache;

    private ZKit zkUtil;

    @Autowired
    public void setZkUtil(ZKit zkUtil) {
        this.zkUtil = zkUtil;
    }

    @Autowired
    public void setCache(LoadingCache<String, String> cache) {
        this.cache = cache;
    }

    /**
     * @ Author chenxu
     * @ Date 19-7-4 - 下午3:29
     * @ Description 添加缓存
     * @ parms key
     * @ Return void
     */
    private void addCache(String key) {
        cache.put(key, key);
    }


    /**
     * @ Author chenxu
     * @ Date 19-7-4 - 下午3:27
     * @ Description 新所有缓存/先删除 再新增
     * @ Return void
     */
    public void updateCache(List<String> currentChilds) {
        cache.invalidateAll();
        for (String currentChild : currentChilds) {
            addCache(currentChild);
        }
    }


    /**
     * @ Author chenxu
     * @ Date 19-7-4 - 下午3:27
     * @ Description 获取所有的服务器列表
     * @ Return a
     */
    public List<String> getAll() {
        List<String> list = new ArrayList<>();
        if (cache.size() == 0) {
            List<String> allNode = zkUtil.getAllNode();
            for (String node : allNode) {
                addCache(node);
            }
        }
        for (Map.Entry<String, String> entry : cache.asMap().entrySet()) {
            list.add(entry.getKey());
        }
        return list;

    }
}
