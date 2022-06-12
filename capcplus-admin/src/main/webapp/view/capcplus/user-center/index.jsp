<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="../../base/head-meta.jsp"%>
<%@include file="../../base/head-meta-js.jsp"%>
<link rel="stylesheet" href="/static/css/capcplus/user-center/index.css">

<body>
<div class="body-header">
   <%@include file="../../base/head-nav.jsp"%>
</div>
<div class="container-fluid">
    <div class="row">
        <div class="left-slide col-sm-2" style="padding: 15px;margin: 0">
            <%@include file="../../base/index-slide.jsp"%>
        </div>
        <div class="body-container col-sm-10">
            <div class="container-fluid">
                <div class="row content-bread">
                    <div class="col-sm-12">
                        <div class="row nav-slide-header">
                            <span class="layui-breadcrumb">
                              <a href="/capcplus/go/index">用户中心</a>
                            </span>
                            <hr>
                        </div>
                        <div class="row nav-slide-body" style="padding-left: 0px;padding-top: 30px;">
                            <%--用户指导--%>
                            <div class="col-sm-12">
                                <div class="row user-guide">
                                    <div class="col-sm-12 guide-title">
                                        <small><i class="fa fa-glide" aria-hidden="true" style="color: gray;padding-right: 6px;"></i>操作指引</small>
                                    </div>
                                    <div class="col-sm-12 guid-content">
                                        <div class="col-sm-2 step step1" onclick="goQualification()">
                                            <div class="col-sm-12 step-content register-icon">
                                                <i class="col-sm-offset-3 col-sm-6 fa fa-pencil-square" aria-hidden="true"></i>
                                            </div>
                                            <div class="col-sm-12 step-content register-title">
                                                <h2 class="title text-center">1.企业注册</h2>
                                            </div>
                                        </div>
                                        <div class="col-sm-1 arrow arrow1">
                                            <i class="fa fa-arrow-right col-sm-offset-3 col-sm-6" aria-hidden="true"></i>
                                        </div>
                                        <div class="col-sm-2 step step2" onclick="goServer()">
                                            <div class="col-sm-12 step-content open-icon">
                                                <i class="col-sm-offset-3 col-sm-6 fa fa-plus-square" aria-hidden="true"></i>
                                            </div>
                                            <div class="col-sm-12 step-content open-title">
                                                <h2 class="title text-center">2.开通服务</h2>
                                            </div>
                                        </div>
                                        <div class="col-sm-1 arrow arrow2">
                                            <i class="fa fa-arrow-right col-sm-offset-3 col-sm-6" aria-hidden="true"></i>
                                        </div>
                                        <div class="col-sm-2 step step3" onclick="goServer()">
                                            <div class="col-sm-12 step-content download-icon">
                                                <i class="col-sm-offset-3 col-sm-6 fa fa-arrow-circle-o-down" aria-hidden="true"></i>
                                            </div>
                                            <div class="col-sm-12 step-content download-title">
                                                <h2 class="title text-center">3.下载DEMO</h2>
                                            </div>
                                        </div>
                                        <div class="col-sm-1 arrow arrow3">
                                            <i class="fa fa-arrow-right col-sm-offset-3 col-sm-6" aria-hidden="true"></i>
                                        </div>
                                        <div class="col-sm-2 step step4" onclick="goServer()">
                                            <div class="col-sm-12 step-content interface-icon">
                                                <i class="col-sm-offset-3 col-sm-6 fa fa-wrench" aria-hidden="true"></i>
                                            </div>
                                            <div class="col-sm-12 step-content interface-title">
                                                <h2 class="title text-center">4.接口调试</h2>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <%--用户余额相关内容--%>
                            <div class="col-sm-12">
                                <div class="row user-invoce-content-outer">
                                    <div class="col-sm-12">
                                        <div class="user-invoce-content">
                                            <div class="col-sm-6 balance-left">
                                                <div class="col-sm-12 title">
                                                    <h3>账户余额（元）</h3>
                                                    <div class="col-sm-3 padding-title">996.85￥</div>
                                                    <a type="button" class="layui-btn layui-btn-normal col-sm-2 padding-title" href="/capcplus/go/recharge">￥充值</a>
                                                    <a class="col-sm-2 col-sm-offset-3" href="/capcplus/go/recharge-list" style="color: #4CACE2;font-size: 5px;">充值记录</a>
                                                </div>
                                            </div>
                                            <div class="col-sm-3 balance-right">
                                                <div class="col-sm-12 user-status">
                                                    <h3 class="col-sm-6">账户状态 </h3>
                                                    <h3 class="col-sm-5 status">正常 </h3>
                                                </div>
                                                <div class="col-sm-5 user-status-tip">
                                                    余额预警值：
                                                </div>
                                                <div class="col-sm-1 user-status-tip">0</div>
                                                <div class="col-sm-3 user-status-tip-edit">元&nbsp;&nbsp;<i class="fa fa-pencil-square-o" aria-hidden="true" onclick="setWarnBalance()"></i> </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <%--产品服务--%>
                            <div class="col-sm-12">
                                <div class="row user-service-outer">
                                    <div class="col-sm-12">
                                        <div class="user-service">
                                            <div class="row">
                                                <div class="server-header">
                                                    <div class="col-sm-6 header header-left">
                                                        <div class="col-sm-1" style="width: 20px!important;" >
                                                            <i class="fa fa-server" aria-hidden="true"></i>
                                                        </div>
                                                        <div class="col-sm-10">
                                                            <small style="position:relative;bottom: 3px;">产品服务</small>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-6 header header-right">
                                                        <a href="#" style="color: #31BDEC;">
                                                            <div class="col-sm-10" style="padding-right: 0">
                                                                <small style="position:relative;bottom: 3px;float: right">更多服务</small>
                                                            </div>
                                                            <div class="col-sm-1" style="width: 20px!important;padding-left: 5px" >
                                                                >>>
                                                            </div>
                                                        </a>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="server-body">
                                                    <div class="col-sm-2 server server1">
                                                        <div class="col-sm-8" style="color: #31BDEC;margin-top: 14px;">
                                                            <i class="fa fa-shield" aria-hidden="true"></i>
                                                            号码归属
                                                        </div>
                                                        <div class="col-sm-4" style="float: right;margin-top: 14px;color: gray">
                                                            已关闭
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-2 server server2">
                                                        <div class="col-sm-8" style="color: #31BDEC;margin-top: 14px;">
                                                            <i class="fa fa-shield" aria-hidden="true"></i>
                                                            一键登录
                                                        </div>
                                                        <div class="col-sm-4" style="float: right;margin-top: 14px;color: gray">
                                                            已关闭
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-2 server server3">
                                                        <div class="col-sm-8" style="color: #31BDEC;margin-top: 14px;">
                                                            <i class="fa fa-shield" aria-hidden="true"></i>
                                                            国际短信
                                                        </div>
                                                        <div class="col-sm-4" style="float: right;margin-top: 14px;color: gray">
                                                            已关闭
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-2 server server4">
                                                        <div class="col-sm-8" style="color: #31BDEC;margin-top: 14px;">
                                                            <i class="fa fa-shield" aria-hidden="true"></i>
                                                            语音通知
                                                        </div>
                                                        <div class="col-sm-4" style="float: right;margin-top: 14px;color: gray">
                                                            已关闭
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-2 server server5">
                                                        <div class="col-sm-8" style="color: #31BDEC;margin-top: 14px;">
                                                            <i class="fa fa-shield" aria-hidden="true"></i>
                                                            短信验证码
                                                        </div>
                                                        <div class="col-sm-4" style="float: right;margin-top: 14px;color: gray">
                                                            已关闭
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
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
        </div>
    </div>
</div>
<div id="modify-warning" style="display: none;width: 523px;" class="container">
    <form onsubmit="return false">
        <label style="color: #768399;font-weight: lighter;font-size: 24px">预警范围值：<font color="red">1~1994</font></label>
        <div class="form-group">
            <label for="warn-balance" style="color: #768399;font-weight: lighter;font-size: 15px;margin-top: 20px;">预警值</label>
            <input type="text" class="form-control layui-input" id="warn-balance" name="balanceWarning" autocomplete="off" style="width: 190px;">
        </div>
        <label style="color: #FFB800;font-weight: lighter;float: bottom">当余额值低于预警值时，系统将以邮件方式提醒用户。</label>
    </form>
</div>
<script src="/static/js/capcplus/user-center/index.js"></script>
</body>
</html>
