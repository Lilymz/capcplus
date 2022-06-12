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
                      <a href="/capcplus/go/index"><i class="fa fa-database" aria-hidden="true"></i>&nbsp;数据详情</a>
                      <a href="/capcplus/detail/go/list"><i class="fa fa-list" aria-hidden="true"></i>&nbsp;服务调用详情</a>
                    </span>
                    <hr>
                </div>
                <%--内容--%>
                <div class="col-sm-12 server-list-content">
                    <div class="panel panel-success">
                        <div class="panel-heading"><i class="fa fa-table" aria-hidden="true"></i>使用情况</div>
                        <div class="panel-body">
                            <form class="form-inline" onsubmit="return false">
                                <div class="col-sm-12">
                                    <div class="form-group">
                                        <button type="button" class="layui-btn layui-btn-radius layui-btn-normal" style="color:white;background-color: #75b9e6!important; border-color: #75b9e6!important;"><i class="fa fa-file-excel-o" aria-hidden="true"></i>数据导出</button>
                                    </div>
                                    <div class="form-group">
                                        <button type="button" class="layui-btn layui-btn-radius layui-btn-normal" style="color:white;background-color: #75b9e6!important; border-color: #75b9e6!important;" onclick="calculate();">成功率统计</button>
                                    </div>
                                </div>
                                <br>
                                <br>
                                <div class="col-sm-12" style="padding-top: 5px">
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
                                        <select id="serverName" name="serverName" multiple title="选择服务">
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="serialNumber">流水号：</label>
                                        <input type="text" class="form-control" autocomplete="off" id="serialNumber" name="serialNumber" placeholder="请输入流水号">
                                    </div>
                                    <div class="form-group">
                                        <select id="resultType" name="resultType" class="capcplus-select">
                                            <option value="CODE">返回码</option>
                                            <option value="CODE_DESCRIPTION">返回码描述</option>
                                        </select>
                                        <input type="text" class="form-control" autocomplete="off" id="result" name="result" placeholder="请输入返回码描述">
                                    </div>
                                    <div class="form-group">
                                        <label for="supplierType">运营商属性：</label>
                                        <select id="supplierType" name="supplierType" class="capcplus-select">
                                            <option value="">全部</option>
                                            <option value="CMCC">移动</option>
                                            <option value="CUCC">联通</option>
                                            <option value="CTCC">电信</option>
                                            <option value="ALL">全网</option>
                                            <option value="OTHERS">非三网</option>
                                        </select>
                                    </div>
                                    <br><br>
                                    <div class="form-group">
                                        <label for="appName">应用名称：</label>
                                        <input type="text" class="form-control" autocomplete="off" id="appName" name="appName" placeholder="请输入应用名称">
                                    </div>
                                    <div class="form-group">
                                        <label for="appId">appId：</label>
                                        <input type="text" class="form-control" autocomplete="off" id="appId" name="appId" placeholder="请输入appId">
                                    </div>
                                    <div class="form-group  input-group date">
                                        <label for="beginTime">开始日期</label>
                                        <div class="input-group date">
                                            <input type="text" class="form-control" id="beginTime" name="beginTime"><span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                        </div>
                                    </div>
                                    <div class="form-group  input-group date">
                                        <label for="endTime">结束日期</label>
                                        <div class="input-group date">
                                            <input type="text" class="form-control" id="endTime" name="endTime"><span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                        </div>
                                    </div>
                                    <button type="button" class="layui-btn layui-btn-radius layui-btn-normal" onclick="search()"><i class="fa fa-search" aria-hidden="true"></i>检索</button>
                                    <button type="button" class="layui-btn layui-btn-radius layui-btn-normal" onclick="reset()"><i class="glyphicon glyphicon-repeat" aria-hidden="true"></i>重置</button>
                                </div>
                            </form>
                            <%--表格内容块--%>
                            <div class="col-sm-12" style="margin-top: 25px;">
                                <table class="table table-striped table-bordered table-hover" id="detail-table" style="width:100%">
                                    <thead>
                                    <tr>
                                        <th class="text-center">序号</th>
                                        <th class="text-center">服务类型</th>
                                        <th class="text-center">服务名称</th>
                                        <th class="text-center">接收手机号</th>
                                        <th class="text-center">返回码</th>
                                        <th class="text-center">请求时间</th>
                                        <th class="text-center">消费金额</th>
                                        <th class="text-center">运营商属性</th>
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
<div id="success-statistics" style="width: 924px;height:630px;display:none;">

</div>
<link rel="stylesheet" href="/static/css/capcplus/statistics/statistics-list.css">
<script src="/static/js/capcplus/detail/data-detail.js"></script>
<script>
</script>
</html>
