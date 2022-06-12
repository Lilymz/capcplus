<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<ul class="layui-nav layui-nav-tree leftContainer" lay-filter="test">
    <li class="layui-nav-item layui-nav-itemed">
        <a href="/capcplus/go/index" class="user-center"><i class="fa fa-male" aria-hidden="true"></i>&nbsp;&nbsp;用户中心</a>
    </li>
    <li class="layui-nav-item">
        <a href="#"><i class="fa fa-server" aria-hidden="true"></i>&nbsp;服务管理</a>
        <dl class="layui-nav-child">
            <dd><a href="/capcplus/server/go/list">服务列表</a></dd>
            <dd>
                <a href="#">服务配置</a>
                <dl class="layui-nav-child">
                    <dd><a href="/capcplus/server/go/cverifycation">统一认证</a></dd>
                </dl>
            </dd>
        </dl>
    </li>
    <li class="layui-nav-item">
        <a href="/capcplus/statistics/go/list"><i class="fa fa-sellsy" aria-hidden="true"></i>&nbsp;数据统计</a>
    </li>
    <li class="layui-nav-item">
        <a href="#"><i class="fa fa-database" aria-hidden="true"></i>&nbsp;数据详情</a>
        <dl class="layui-nav-child">
            <dd><a href="/capcplus/detail/go/list">服务调用详情</a></dd>
        </dl>
    </li>
    <li class="layui-nav-item">
        <a href="#"><i class="fa fa-ticket" aria-hidden="true"></i>&nbsp;发票管理</a>
        <dl class="layui-nav-child">
            <dd><a href="/capcplus/ticket/go/ticket-info">开票信息</a></dd>
            <dd><a href="/capcplus/ticket/go/address-list">地址管理</a></dd>
            <dd><a href="/capcplus/ticket/go/ticket-apply">发票申请</a></dd>
            <dd><a href="/capcplus/ticket/go/ticket-list">发票记录</a></dd>
        </dl>
    </li>
    <li class="layui-nav-item">
        <a href="/capcplus/message/go/list" class="user-center"><i class="fa fa-bell" aria-hidden="true"></i>&nbsp;&nbsp;消息中心</a>
    </li>
</ul>