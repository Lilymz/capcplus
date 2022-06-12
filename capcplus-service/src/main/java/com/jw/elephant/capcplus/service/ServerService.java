package com.jw.elephant.capcplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jw.elephant.capcplus.common.datatable.DataTableParam;
import com.jw.elephant.capcplus.common.datatable.PageDT;
import com.jw.elephant.capcplus.dto.server.ServerSearchDTO;
import com.jw.elephant.capcplus.pojo.Server;

import javax.servlet.http.HttpServletRequest;

/**
 * 服务service interface
 * @author zhangjie
 */
public interface ServerService extends IService<Server> {
   /**
    *   分页获取充值记录数据
    *
    * @param dataTableParam 分页参数
    * @param searchDTO 搜索参数
    * @author zhangjie
    * @date 2022/5/31
    * @return com.jw.elephant.capcplus.common.datatable.PageDT<com.jw.elephant.capcplus.pojo.RechargeRecord>
    */
    PageDT<Server> query(DataTableParam dataTableParam, ServerSearchDTO searchDTO);

    /**
     * 开启或关闭当前服务
     *
     * @param id 服务id
     * @param status 服务状态
     * @author zhangjie
     * @date 2022/6/1
     * @return boolean
     */
    boolean closeOpen(Long id ,String status);
    /**
     *  重置服务密码
     * @param id 服务id
     * @author zhangjie
     * @date 2022/6/1
     * @return boolean
     */
    String resetPassword(Long id);
    /**
     *  检验：首先要先检查用户状态、企业审核状态、企业有效状态，检查通过才能查询统一认证列表信息。不通过暂时调列表页
     *
     * @param request 请求
     * @author zhangjie
     * @date 2022/6/6
     * @return boolean 是否通过
     */
    boolean checkCverifycation(HttpServletRequest request);
}
