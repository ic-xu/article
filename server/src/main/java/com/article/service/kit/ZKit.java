package com.article.service.kit;

import com.alibaba.fastjson.JSON;
import com.article.service.cache.ServerCache;
import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ Date 19-7-4 - 下午3:34
 * @ Description Zookeeper 工具
 * @ parms a
 * @ Return a
 */
@Component
@Slf4j
public class ZKit {

    private ZkClient zkClient;

    private ServerCache serverCache;

    @Autowired
    public void setServerCache(ServerCache serverCache) {
        this.serverCache = serverCache;
    }

//    @Autowired
//    public void setZkClient(ZkClient zkClient) {
//        this.zkClient = zkClient;
//    }

    /**
     * 监听事件
     *
     * @ param path
     */
    public void subscribeEvent(String path) {
        zkClient.subscribeChildChanges(path, (parentPath, currentChilds) -> {
            log.info("清除/更新本地缓存 parentPath=【{}】,currentChilds=【{}】", parentPath, currentChilds.toString());

            //更新所有缓存/先删除 再新增
            serverCache.updateCache(currentChilds);
        });
    }


    /**
     * 获取所有注册节点
     *
     * @return List<String> /route下所有节点的字符串
     */
    public List<String> getAllNode() {
        List<String> children = zkClient.getChildren("/route");
        log.info("查询所有节点成功=【{}】", JSON.toJSONString(children));
        return children;
    }


}
