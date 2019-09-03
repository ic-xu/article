package com.article.service.mvc.route.controller;

import com.alibaba.fastjson.JSON;
import com.article.service.cache.ServerCache;
import com.article.service.mvc.route.ChatReqVO;
import com.article.service.mvc.route.P2PReqVO;
import com.article.service.mvc.route.ServiceInfo;
import com.article.service.mvc.route.service.AccountService;
import com.common.mvc.member.entity.Member;
import com.common.mvc.member.service.MemberService;
import com.common.utils.BaseResponseDto;
import com.common.utils.IdWorker;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("/")
public class RouteController {

    private ServerCache serverCache;
    private AccountService accountService;


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
    public BaseResponseDto pushRoute(@RequestBody P2PReqVO p2pRequest) throws Exception {


        try {
            List<String> allServer = serverCache.getAll();
            if (null != allServer) {
                for (String value : allServer) {
                    ServiceInfo serviceInfo = JSON.parseObject(value, ServiceInfo.class);
                    //推送消息
                    String url = "http://" + serviceInfo.getOutIp() + ":" + serviceInfo.getHttpPort() + "/pushMsg";
                    ChatReqVO chatVO = new ChatReqVO();
                    chatVO.setId(IdWorker.getInstance().nextId());
                    chatVO.setMsg(p2pRequest.getMsg());
                    chatVO.setFromUserId(p2pRequest.getUserId());
                    chatVO.setToUserId(p2pRequest.getUserId());
                    accountService.pushMsg(url, p2pRequest.getUserId(), chatVO);
                }
            }
            return BaseResponseDto.success("推动成功");
        } catch (Exception e) {
            return BaseResponseDto.error(400, e.getMessage());
        }
    }


    /**
     * 获取一台 nim server
     *
     * @ return
     */
    @ApiOperation("登录并获取服务器")
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public BaseResponseDto login(HttpServletRequest request, @RequestBody Member member) {


        TreeMap<Long, ServiceInfo> treeMap = new TreeMap<>(Comparator.naturalOrder());
        //登录校验
         memberService.save(member);

        System.err.println(serverCache.getAll().size());

        serverCache.getAll().forEach(serverString -> {
            ServiceInfo serviceInfo = JSON.parseObject(serverString, ServiceInfo.class);
            String url = "http://" + serviceInfo.getOutIp() + ":" + serviceInfo.getHttpPort() + "/getUserCount";
            long serviceOnlneCount = Long.parseLong(accountService.getServiceOnlneCount(url));
            treeMap.put(serviceOnlneCount, serviceInfo);

        });
        if (treeMap.size() > 0) {
            ServiceInfo value = treeMap.firstEntry().getValue();

            return BaseResponseDto.success(value);
        }
        return BaseResponseDto.success("[]");
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
