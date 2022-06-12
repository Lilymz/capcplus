package com.jw.elephant.capcplus.dto.server;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 服务条件DTO
 * @author zhangjie
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServerSearchDTO {

    private String serverName ;


    private Integer serverType;
}
