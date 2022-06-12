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
                      <a href="/capcplus/go/index"><i class="fa fa-server" aria-hidden="true"></i>&nbsp;服务管理</a>
                      <a href="/capcplus/server/go/list"><i class="fa fa-list" aria-hidden="true"></i>&nbsp;服务列表</a>
                    </span>
                    <hr>
                </div>
                <%--内容--%>
                <div class="col-sm-12 server-list-content">
                    <div class="panel panel-success">
                        <div class="panel-body">
                            <form class="form-inline" onsubmit="return false">
                                <div class="form-group">
                                    <label for="name">服务名称：</label>
                                    <input type="text" class="form-control" id="name" name="name" autocomplete="off" placeholder="请输入服务名称">
                                </div>
                                <div class="form-group">
                                    <label for="type">服务类型：</label>
                                    <select name="type" id="type">
                                        <option value="0">全部</option>
                                        <option value="1">手机号信息类</option>
                                        <option value="2">短信类</option>
                                        <option value="3">语音类</option>
                                        <option value="4">统一认证</option>
                                    </select>
                                </div>
                                <button type="submit" class="btn btn-info" onclick="search()"><i class="fa fa-search" aria-hidden="true"></i>搜索</button>
                            </form>
                            <%--表格内容块--%>
                            <div class="col-sm-12" style="margin-top: 25px;">
                                <table class="table table-striped table-bordered table-hover" id="server-table" style="width:100%">
                                    <thead>
                                    <tr>
                                        <th class="text-center">序号</th>
                                        <th class="text-center">服务类型</th>
                                        <th class="text-center">服务名称</th>
                                        <th class="text-center">业务参数</th>
                                        <th class="text-center">状态</th>
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
<link rel="stylesheet" href="/static/css/capcplus/server/server-list.css">
<script src="/static/js/capcplus/server/server-list.js"></script>
<script>
</script>
</html>
