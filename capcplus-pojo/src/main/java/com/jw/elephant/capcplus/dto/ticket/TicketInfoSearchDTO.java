package com.jw.elephant.capcplus.dto.ticket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 发票记录搜索DTO
 * @author zhangjie
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketInfoSearchDTO {
    /**
     * 发票抬头
     */
    private String ticketMan;
    /**
     * 发票税务登记号
     */
    private String creditCode;
    /**
     * 发票项目
     */
    private String ticketProject;
    /**
     * 发票类型
     */
    private String ticketType;
    /**
     * 审核状态
     */
    private String status;
}
