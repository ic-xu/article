package com.article.service.mvc.route.controller;

import com.alibaba.fastjson.JSON;
import com.article.service.cache.ServerCache;
import com.article.service.mvc.route.bean.*;
import com.article.service.mvc.route.service.AccountService;
import com.common.mvc.member.entity.Member;
import com.common.mvc.member.service.MemberService;
import com.common.utils.BaseResponseDto;
import com.common.utils.IdWorker;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

@RestController
@RequestMapping("user/")
@Api(tags = "路由相关")
public class RouteController {

    private ServerCache serverCache;
    private AccountService accountService;

    private RedisTemplate<String, String> redisTemplate;


    @Autowired
    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    @Autowired
    public void setServerCache(ServerCache serverCache) {
        this.serverCache = serverCache;
    }

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }


    private MemberService memberService;

    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }


    /**
     * 推送消息的路由
     *
     * @ param p2pRequest
     * @ return
     * @ return
     */
    @ApiOperation("推送消息")
    @RequestMapping(value = "pushRoute", method = RequestMethod.POST)
    public BaseResponseDto pushRoute(@RequestBody PushMessage pushMessage) {
        String pushMessageId = IdWorker.getRandomString(1) + IdWorker.getInstance().nextId();
        List<String> allServer = serverCache.getAll();
        if (null != allServer) {
            for (String value : allServer) {
                ServiceInfo serviceInfo = JSON.parseObject(value, ServiceInfo.class);
                pushMessage.setId(pushMessageId);
                PMessage pMessage = pushMessage.getMsg();
                String replace = pMessage.getContent().replaceAll("\\s+", "");
                pMessage.setContent(replace);
                pMessage.setTime(System.currentTimeMillis());
                pushMessage.setMsg(pMessage);
                redisTemplate.convertAndSend(serviceInfo.getOutIp(), JSON.toJSONString(pushMessage));
            }
        }
        return BaseResponseDto.success(pushMessageId);
    }


    /**
     * 推送消息的路由
     *
     * @ param p2pRequest
     * @ return
     * @ return
     */
    @ApiOperation("获取某条通知推送记录")
    @GetMapping(value = "pushMessageCount")
    public BaseResponseDto pushMessageCount(String pushMessageId) {

        return BaseResponseDto.success(redisTemplate.opsForValue().get(pushMessageId));
    }


    /**
     * 获取一台 nim server
     *
     * @ return
     */
    @ApiOperation("登录并获取服务器")
    @PostMapping("login")
    public BaseResponseDto login(Member member) throws Exception {


        TreeMap<Long, ServiceInfo> treeMap = new TreeMap<>(Comparator.naturalOrder());

        //登录校验
        Member user = memberService.save(member);

        //如果登录成功

        LoginResposeDate loginResposeDate = new LoginResposeDate();
        loginResposeDate.setUser(user);
        System.err.println(serverCache.getAll().size());
        serverCache.getAll().forEach(serverString -> {
            ServiceInfo serviceInfo = JSON.parseObject(serverString, ServiceInfo.class);
            String url = "http://" + serviceInfo.getOutIp() + ":" + serviceInfo.getHttpPort() + "/getUserCount";
            long serviceOnlneCount = Long.parseLong(accountService.getServiceOnlneCount(url));
            treeMap.put(serviceOnlneCount, serviceInfo);

        });
        if (null == treeMap.firstEntry())
            throw new Exception("IM服务器异常");
        ServiceInfo value = treeMap.firstEntry().getValue();
        ResposServiceInfo resposServiceInfo = new ResposServiceInfo();
        resposServiceInfo.setHttpPort(value.getHttpPort());
        resposServiceInfo.setServerPort(value.getNettyPort());
        resposServiceInfo.setUrl(value.getOutIp());
        loginResposeDate.setServiceInfo(resposServiceInfo);
        return BaseResponseDto.success(loginResposeDate);
    }


    /**
     * 获取一台 nim server
     *
     * @ return
     */
    @ApiOperation("获取服务器")
    @RequestMapping(value = "getRouteList", method = RequestMethod.POST)
    public BaseResponseDto getRouteList() {

        return BaseResponseDto.success(serverCache.getAll() + "");
    }


//    /**
//     * 获取所有在线用户
//     *
//     * @ return
//     */
//    @ApiOperation("获取所有在线用户")
//    @RequestMapping(value = "onlineUser", method = RequestMethod.POST)
//    public BaseResponseDto onlineUser() {
//
//        Set<String> nimUserInfos = userService.onlineUser();
//        List<UserEntity> userList = new ArrayList<>();
//        for (String userId : nimUserInfos) {
//            UserEntity userInfo = userService.getUserInfo(userId);
//            if (userInfo == null)
//                userInfo = new UserEntity();
//            userInfo.setUsername(String.valueOf(userId));
//            userList.add(userInfo);
//        }
//
//        return BaseResponseDto.success(userList);
//    }
}
