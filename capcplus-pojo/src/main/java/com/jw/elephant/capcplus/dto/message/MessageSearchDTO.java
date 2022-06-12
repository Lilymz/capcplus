package com.jw.elephant.capcplus.dto.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 消息搜索DTO
 * @author zhangjie
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageSearchDTO {
    /**
     * 消息类型
     */
    private String type;
}
