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
                      <a href="/capcplus/statistics/go/list"><i class="fa fa-sellsy" aria-hidden="true"></i>&nbsp;数据统计</a>
                    </span>
                    <hr>
                </div>
                <%--内容--%>
                <div class="col-sm-12 server-list-content">
                    <div class="panel panel-success">
                        <div class="panel-heading"><i class="fa fa-calculator" aria-hidden="true"></i>使用情况</div>
                        <div class="panel-body">
                            <form class="form-inline" onsubmit="return false">
                                <div class="form-group">
                                    <label for="serverType">服务类型：</label>
                                    <select id="serverType" name="serverType" onchange="serverChange(this)">
                                        <option value="">全部</option>
                                        <c:forEach items="${serverTypes}" var="item">
                                            <option value="${item.name()}">${item.toString()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="serverName">服务名称：</label>
                                    <select id="serverName" name="serverName" multiple title="请选择服务名称">
                                    </select>
                                </div>
                                <div class="form-group  input-group date">
                                    <label for="billDate">账单日期</label>
                                    <div class="input-group date">
                                        <input type="text" class="form-control" id="billDate" name="billDate"><span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                    </div>
                                </div>
                                <button type="button" class="layui-btn layui-btn-radius layui-btn-normal" onclick="search()">检索</button>
                            </form>
                            <%--表格内容块--%>
                            <div class="col-sm-12" style="margin-top: 25px;">
                                <table class="table table-striped table-bordered table-hover" id="statistics-table" style="width:100%">
                                    <thead>
                                    <tr>
                                        <th class="text-center">序号</th>
                                        <th class="text-center">服务名称</th>
                                        <th class="text-center">使用总量（次）</th>
                                        <th class="text-center">成功使用量（次）</th>
                                        <th class="text-center">位置使用量（次）</th>
                                        <th class="text-center">失败使用量（次）</th>
                                        <th class="text-center">总花费（元）</th>
                                        <th class="text-center">测试费用（元）</th>
                                        <th class="text-center">返现费用（元）</th>
                                        <th class="text-center" style="color: darkred">实际费用（元）</th>
                                        <th class="text-center">结算总价（元）</th>
                                        <th class="text-center">结算返还（元）</th>
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
<link rel="stylesheet" href="/static/css/capcplus/statistics/statistics-list.css">
<script src="/static/js/capcplus/statistics/stctistics-list.js"></script>
<script>
</script>
</html>
