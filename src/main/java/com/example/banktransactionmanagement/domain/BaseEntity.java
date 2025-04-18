package com.example.banktransactionmanagement.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;
/**
 * 基础实体类
 *
 * @author DENGWENJIAN1
 * @date 2025/4/17
 */
@Data
public class BaseEntity {

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createAt;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private LocalDateTime updateAt;

    /**
     * 是否删除
     */
    @TableLogic
    private Boolean deleted;
}    