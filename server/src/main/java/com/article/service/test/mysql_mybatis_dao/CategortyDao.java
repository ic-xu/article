//package com.tcl.community.test.mysql_mybatis_dao;
//
//import com.tcl.community.mvc.community.entity.Tags;
//import org.apache.ibatis.annotations.*;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//
//@Component
//public interface CategortyDao {
//
//
//    @Select("select * from categoryFirst")
//    @Results({@Result(property = "id", column = "id"),
//            @Result(property = "categoryName", column = "categoryName"),
//            @Result(property = "apare", column = "apare"),
//            @Result(property = "url", column = "url"),
//            @Result(property = "towClassificationList", javaType = List.class, column = "id",
//                    many = @Many(select = "findCategrotySecondary"))})
//    List<Tags> getAllCategory();
//
//
//    @Select("select * ,(select count(*) FROM invitation WHERE invitationId =categrotySecondary.id)  AS discussCount " +
//            "from categrotySecondary where categoryFirstId = #{id}")
//    List<Object> findCategrotySecondary(int id);
//
//
//    /**
//     * 插入数据
//     * @param tags
//     * @return
//     */
//    @Insert("insert into tags(id,categoryName,imag,url) values(#{id},#{categoryName},#{imag},#{url})")
//    int insert(Tags tags);
//}
