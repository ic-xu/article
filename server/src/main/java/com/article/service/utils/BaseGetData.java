package com.article.service.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * Created by ubuntu
 * On 19-7-20 下午2:14
 */

@Data
@AllArgsConstructor
@ToString
public class BaseGetData<T> {

    private int currentPage;

    private T data;

    private int totalPage;
}
