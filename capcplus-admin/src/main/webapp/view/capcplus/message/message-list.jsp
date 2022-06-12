<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%@include file="../../base/head-meta.jsp"%>
<%@include file="../../base/head-meta-js.jsp"%>
<link rel="stylesheet" href="/static/iCheck/skins/flat/blue.css">
<script src="/static/iCheck/icheck.min.js"></script>
<style>
    td{
        text-align: center;
        height: 60px;
    }
</style>
<body>
<div class="body-header">
    <%@include file="../../base/head-nav.jsp"%>
</div>
<div class="container-fluid">
    <div class="row">
        <div class="left-slide col-sm-2" style="padding: 15px;margin: 0">
            <%@include file="../../base/index-slide.jsp"%>
        </div>
        <div class="body-container container col-sm-10">
            <div class="row">
                <div class="col-sm-12 bread-nav">
                    <span class="layui-breadcrumb">
                      <a href="/capcplus/go/index"><i class="fa fa-male" aria-hidden="true"></i>&nbsp;用户中心</a>
                      <a><i class="fa fa-bell" aria-hidden="true"></i>消息中心</a>
                    </span>
                    <hr>
                </div>
                <%--内容--%>
                <div class="col-sm-12 server-list-content">
                    <div class="panel panel-danger">
                        <div class="panel-heading"><i class="fa fa-table" aria-hidden="true"></i>消息列表</div>
                        <div class="panel-body">
                            <form class="form-inline" onsubmit="return false">
                                <div class="col-sm-12" style="padding-top: 5px">
                                    <div class="form-group">
                                        <label for="type">消息类型：</label>
                                        <select id="type" name="type" class="capcplus-select">
                                            <option value="">全部</option>
                                            <option value="SERVICE_MESSAGE">服务消息</option>
                                            <option value="SYSTEM_MESSAGE">系统消息</option>
                                            <option value="BALANCE_ALERT">余额预警</option>
                                            <option value="DATA_MODIFY">资料修改</option>
                                        </select>
                                    </div>
                                    <button type="submit" class="layui-btn layui-btn-radius layui-btn-normal" style="color:white;background-color: #75b9e6!important; border-color: #75b9e6!important;" onclick="search()"><i class="glyphicon glyphicon-search" aria-hidden="true"></i>查询</button>
                                    <button type="button" class="layui-btn layui-btn-radius layui-btn-normal" style="color:white;background-color: #75b9e6!important; border-color: #75b9e6!important;" onclick="doBatchRead()"><i class="layui-icon layui-icon-ok-circle" aria-hidden="true"></i>批量已读</button>
                                    <button type="button" class="layui-btn layui-btn-radius layui-btn-normal" style="color:white;background-color: #75b9e6!important; border-color: #75b9e6!important;" onclick="doBatchDel()"><i class="layui-icon layui-icon-delete "></i>批量删除</button>
                                </div>
                            </form>
                            <%--表格内容块--%>
                            <div class="col-sm-12" style="margin-top: 25px;">
                                <table class="table table-striped table-bordered table-hover" id="message-table" style="width:100%">
                                    <thead>
                                        <tr>
                                            <th><input type="checkbox" id="all_checked"></th>
                                            <th class="text-center">序号</th>
                                            <th class="text-center">标题</th>
                                            <th class="text-center">消息类型</th>
                                            <th class="text-center">消息内容</th>
                                            <th class="text-center">日期</th>
                                            <th class="text-center">操作</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row nav-slide-footer">
                <div class="footer" style="background-color: #fff;margin-bottom: 30px;">
                    <div class="col-sm-12">
                        <p class="text-center" style="margin-bottom: 0px">为了更好的页面体验，建议使用IE 11,chrome 51,Firefox 54及以上版本</p>
                        <%@include file="../../base/footer.jsp"%>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<div id="show-content" style="display: none">
    <table class="table table-hover">
        <tbody>
        <tr>
            <td width="15%" style="text-align: right;">&nbsp;&nbsp;发信人：</td>
            <td id="sendUser">大象能力平台</td>
        </tr>
        <tr>
            <td width="15%" style="text-align: right;">&nbsp;&nbsp;收信人：</td>
            <td id="receiveUser">--</td>
        </tr>
        <tr>
            <td width="15%" style="text-align: right;">&nbsp;&nbsp;标题：</td>
            <td id="title">--</td>
        </tr>
        <tr>
            <td width="15%" style="text-align: right;">&nbsp;&nbsp;发送时间：</td>
            <td id="sendTime">--</td>
        </tr>
        <tr>
            <td width="15%" style="text-align: right;">&nbsp;&nbsp;内容：</td>
            <td><span id="content">-</span></td>
        </tr>
        <tr>
        </tr>
        </tbody>
    </table>
</div>
<link rel="stylesheet" href="/static/css/capcplus/message/message-list.css">
<script src="/static/js/capcplus/message/message-list.js"></script>
<script>
</script>
</html>