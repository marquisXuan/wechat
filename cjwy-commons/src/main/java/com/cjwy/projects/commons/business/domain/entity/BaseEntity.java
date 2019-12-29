package com.cjwy.projects.commons.business.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 数据库基础实体类
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2019/12/28 11:56 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity {

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    @TableLogic
    private Short status;

    @Version
    private Long version;

    private Date createTime;

    private Date modifyTime;

    private String createId;

    private String modifyId;
}
