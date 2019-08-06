package com.common.mvc.feedback.service;//package com.tcl.community.admin.mvc.feedback.service;
//
//import com.tcl.community.admin.mvc.feedback.entity.ReplyFeedBack;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public class ReplyFeedBackService {
//
//    @Autowired
//    ReplyFeedBackRepository replyFeedBackRepository;
//
//
//    /**
//     * 保存或者修改
//     *
//     * @param replyFeedBack
//     * @return
//     */
//    public ReplyFeedBack save(ReplyFeedBack replyFeedBack) {
//        return replyFeedBackRepository.save(replyFeedBack);
//    }
//
//
//    /**
//     * 根据状态查询回复信息
//     *
//     * @param replyUserId 用户Id
//     * @return
//     */
//    public List<ReplyFeedBack> findAllByReplyUserIdAndOrderByCreateTimeDesc(String replyUserId) {
//        return replyFeedBackRepository.findAllByReplyUserIdAndOrderByCreateTimeDesc(replyUserId);
//    }
//
//
//}
