package com.cjwy.projects.commons.http.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分页 VO
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2020/1/2 1:58 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageVO<T> {

    private Long total;

    private T data;

}
