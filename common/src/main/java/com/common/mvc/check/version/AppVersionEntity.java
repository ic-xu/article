package com.common.mvc.check.version;

import lombok.Data;

@Data
public class AppVersionEntity {

    private String id;

    private String desc;

    private String versionShow;

    private String versionCode;

    private Boolean isFoces;

    private String url;

    private Boolean isUpdate;

}
