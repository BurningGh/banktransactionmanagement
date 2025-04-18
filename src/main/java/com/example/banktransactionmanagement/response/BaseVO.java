package com.example.banktransactionmanagement.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

import static com.example.banktransactionmanagement.common.Constant.YYYY_MM_DD_HH_MM_SS;

/**
 * 基础VO
 *
 * @author DENGWENJIAN1
 * @date 2025/4/17
 */
@Data
public class BaseVO {

    private String id;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = YYYY_MM_DD_HH_MM_SS)
    private LocalDateTime createAt;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = YYYY_MM_DD_HH_MM_SS)
    private LocalDateTime updateAt;


}    