<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%@include file="../../base/head-meta.jsp"%>
<%@include file="../../base/head-meta-js.jsp"%>
<link rel="stylesheet" href="/static/iCheck/skins/flat/blue.css">
<script src="/static/iCheck/icheck.min.js"></script>
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
                      <a href="/capcplus/go/index">用户中心</a>
                      <a href="/capcplus/go/recharge-list">充值记录</a>
                    </span>
                        <hr>
                    </div>
                    <%--内容--%>
                    <div class="col-sm-12 recharge-list-content">
                        <div class="panel panel-success">
                            <div class="panel-body">
                                <form class="form-inline">
                                    <div class="form-group">
                                        <label for="orderNumber">订单号：</label>
                                        <input type="text" class="form-control" id="orderNumber" name="orderNumber" autocomplete="off" placeholder="请输入订单号">
                                    </div>
                                    <div class="form-group">
                                        <label for="paySerialNumber">支付流水号：</label>
                                        <input type="email" class="form-control" id="paySerialNumber" name="paySerialNumber" autocomplete="off" placeholder="请输入支付流水号">
                                    </div>
                                    <div class="form-group">
                                        <label for="payStatus">支付状态：</label>
                                        <select name="payStatus" id="payStatus">
                                            <option value="0">全部</option>
                                            <option value="1">等待支付</option>
                                            <option value="2">支付成功</option>
                                            <option value="3">支付失败</option>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="orderStatus">订单状态：</label>
                                        <select name="orderStatus" id="orderStatus" lay-verify="">
                                            <option value="0">全部</option>
                                            <option value="1">交易中..</option>
                                            <option value="2">交易成功</option>
                                            <option value="3">交易失败</option>
                                            <option value="4">交易取消</option>
                                            <option value="5">待财务审核</option>
                                            <option value="6">财务驳回</option>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="rechargeType">充值类型：</label>
                                        <select name="rechargeType" id="rechargeType">
                                            <option value="0">全部</option>
                                            <option value="1">充值..</option>
                                            <option value="2">消费</option>
                                            <option value="3">返现</option>
                                            <option value="4">扣费</option>
                                            <option value="5">实际充值</option>
                                            <option value="6">预充值</option>
                                            <option value="7">失败返利</option>
                                            <option value="8">拦截返利</option>
                                            <option value="9">删除返利</option>
                                            <option value="10">余额互转</option>
                                            <option value="11">后付费充值</option>
                                        </select>
                                    </div>
                                    <button type="submit" class="btn btn-info"><i class="fa fa-search" aria-hidden="true"></i>搜索</button>
                                    <a type="button" class="btn btn-info text-decoration-none" href="/capcplus/go/recharge"><i>￥充值</i></a>
                                </form>
                                <%--表格内容块--%>
                                <div class="col-sm-12" style="margin-top: 25px;">
                                    <table class="table table-striped table-bordered table-hover" id="recharge-table" style="width:100%">
                                        <thead>
                                        <tr>
                                            <th class="text-center">序号</th>
                                            <th class="text-center">订单号</th>
                                            <th class="text-center">充值金额</th>
                                            <th class="text-center">充值类型</th>
                                            <th class="text-center">备注信息</th>
                                            <th class="text-center">支付流水号</th>
                                            <th class="text-center">订单状态</th>
                                            <th class="text-center">支付状态</th>
                                            <th class="text-center">提交时间</th>
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
<link rel="stylesheet" href="/static/css/capcplus/user-center/recharge-list.css">
<script src="/static/js/capcplus/user-center/recharge-list.js"></script>
<script>
</script>
</html>
