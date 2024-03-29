package com.common.mvc.route.bean;

import com.common.mvc.user.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by chenxu
 * On 19-9-5 上午10:53
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResposeDate {

    Member user;

    ResposServiceInfo  serviceInfo;
}
