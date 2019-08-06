package com.common.test;//package com.tcl.community.test;
//
//
//import com.tcl.community.test.entity.Invitation;
//import com.tcl.community.test.service.PostService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//
//@RestController
//@Api("帖子相关")
//public class InvitationController {
//
//    @Autowired
//    PostService deptService;
//
//    @Autowired
//    RedisTemplate redisTemplate;
//
//    @GetMapping("/getPost")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "pageNumber", value = "请求第几页，默认第一页开始", dataType = "int"),
//            @ApiImplicitParam(name = "pageSize", value = "分页大小", dataType = "int")})
//    public String getDept(Invitation invitation) {
//        return deptService.getPost("name");
//
//    }
//
//
//    public void PageQuery2(){
//        //mongodb中有两个表，一个是人物表 一个是宠物表，一个人可以有多个宠物
//        //人物表字段为 String id, Integer age,String remark;
//        //宠物表字段为 String id, String manId,String age,String remark;
//        //拼装分页信息
//        SpringbootMongoDBPageable pageable = new SpringbootMongoDBPageable();
//        MongoDBPageModel pm=new MongoDBPageModel();
//        pm.setPagesize(2);
//        pm.setPagenumber(1);
//        List<Order> orders = new ArrayList<>();  //排序
//        orders.add(new Order(Direction.DESC, "age"));
//        Sort sort = new Sort(orders);
//        pm.setSort(sort);
//        pageable.setPage(pm);
//
//        //拼装关联信息
//        LookupOperation lookupOperation = LookupOperation.newLookup().
//                from("dogData").//关联表名
//                localField("_id").//关联字段
//                foreignField("manId").//主表关联字段对应的次表字段
//                as("dogs");//查询结果集合名
//
//        //拼装具体查询信息
//        //次表
//        Criteria ordercri = Criteria.where("dogs").not().size(0);//只查询有宠物的人
//        ordercri.and("age").gte(1).lte(5);//只查询1岁到5岁的宠物
//        AggregationOperation match = Aggregation.match(ordercri);
//        //主表
//        Criteria qqq= Criteria.where("name").regex("文");//只查询名字中带有文的人
//        AggregationOperation match1= Aggregation.match(qqq);
//
//        //分页查询
//        Aggregation aggregation = Aggregation.newAggregation(match1,lookupOperation,match,Aggregation.sort(pageable.getSort()),//排序
//                Aggregation.skip(pageable.getPageNumber()>1?(pageable.getPageNumber()-1)*pageable.getPageSize():0),//pagenumber
//                Aggregation.limit(pageable.getPageSize()));//pagesize
//        //总数查询
//        Aggregation counts = Aggregation.newAggregation(match1,lookupOperation,match).;
//        int count = mongoTemplate.aggregate(counts, "manEntry", BasicDBObject.class).getMappedResults().size();
//        List<BasicDBObject> results = mongoTemplate.aggregate(aggregation, "manEntry", BasicDBObject.class).getMappedResults();
//        //查询出的结果集为BasicDBObject类型
//        //解析过程
//        for (BasicDBObject b :results
//        ) {
//            //转化为jsonobject对象
//            JSONObject jsonObject = new JSONObject(b);
//            String id = jsonObject.get("id").toString();
//            Integer age = ((int) jsonObject.get("age"));
//            String remark = jsonObject.get("remark").toString();
//            //转化为jsonarray
//            JSONArray dogs = jsonObject.getJSONArray("dogs");
//            if (dogs.size() > 0) {
//                for (int i = 0; i < dogs.size(); i++) {
//                    JSONObject job = dogs.getJSONObject(i);
//                    String dogId = job.get("id").toString();
//                    String manId = job.get("manId").toString();
//                }
//            }
//
//        }
//    }
//}
