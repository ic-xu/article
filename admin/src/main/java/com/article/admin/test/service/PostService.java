package com.article.admin.test.service;//package com.tcl.community.test.service;
//
//import com.tcl.community.test.mysql_mybatis_dao.InvitationDao;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.stereotype.Component;
//
//@Component
//public class PostService {
//
//    @Autowired
//    InvitationDao deptMapper;
//
//    @Cacheable(value = "deptcache", key = "#p0")
//    public String getPost(String name){
//        return deptMapper.findPost(name).toString();
//    }
//}
