<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="../../base/head-meta.jsp"%>
<%@include file="../../base/head-meta-js.jsp"%>
<body>
<link rel="stylesheet" href="/static/css/admin/index/register.css">
<script src="/static/pwd-strong/js/jquery.complexify.js"></script>
<link rel="stylesheet" href="/static/pwd-strong/css/style.css">
<script src="/static/pwd-strong/js/jquery.placeholder.min.js"></script>
<div class="body-container">
    <div class="container-fluid">
        <div class="row">
            <div class="col-xs-12">
                <div style="height: 75px;text-align: left;padding: 10px 0 10px 10px;position: relative">
                    <a href="/" style="cursor: pointer" class="">
                        <img src="/resources/assets/home/others/logo1.png" alt="logo" style="height: 100%;"></a>
                </div>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <form id="register" onsubmit="return false">
                <div class="register-form animated bounceInUp">
                    <div class="row">
                        <div class="register-header col-xs-offset-5 col-xs-3">
                            <i class="fa fa-registered fa-2x" style="float: right"></i>
                            <h3>注册</h3>
                        </div>
                    </div>
                    <div class="row">
                        <div class="register-content">
                            <div class="form-group">
                                <div class="row">
                                    <label for="name" class="col-xs-offset-1 col-xs-1 control-label">姓名<font style="color: red">*</font></label>
                                    <div class="col-xs-6 element">
                                        <input type="text" class="form-control" id="name" name="name"  autocomplete="off" placeholder="请输入姓名">
                                    </div>
                                    <div class="col-xs-4"></div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="row">
                                    <label for="account" class="col-xs-offset-1 col-xs-1 control-label">账号<font style="color: red">*</font></label>
                                    <div class="col-xs-6 element">
                                        <input type="text" class="form-control" id="account" name="account"  autocomplete="off" placeholder="请输入账号">
                                    </div>
                                    <div class="col-xs-4"></div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="row">
                                    <label for="password" class="col-xs-offset-1 col-xs-1 control-label">密码<font style="color: red">*</font></label>
                                    <div class="col-xs-6 element">
                                        <input type="password" class="form-control" id="password" name="password"  autocomplete="off" placeholder="请输入密码">
                                    </div>
                                    <div class="col-xs-4"></div>
                                </div>
                                <div class="row">
                                    <label for="password" class="col-xs-offset-1 col-xs-1 control-label" style="color: gray">密码强度：</label>
                                    <div class="col-xs-6 element">
                                        <div class=" col-xs-6"><div id="complexity">0%</div></div>
                                    </div>
                                    <div class="col-xs-4"></div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="row">
                                    <label for="company" class="col-xs-offset-1 col-xs-1 control-label">公司名称<font style="color: red">*</font></label>
                                    <div class="col-xs-6 element">
                                        <input type="text" class="form-control" id="company" name="company"  autocomplete="off" placeholder="请输入公司名称">
                                    </div>
                                    <div class="col-xs-4"></div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="row">
                                    <label for="email" class="col-xs-offset-1 col-xs-1 control-label">邮箱<font style="color: red">*</font></label>
                                    <div class="col-xs-6 element">
                                        <input type="text" class="form-control" id="email" name="email"  autocomplete="off" placeholder="请输入邮箱">
                                    </div>
                                    <div class="col-xs-4"></div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="row">
                                    <label for="phone" class="col-xs-offset-1 col-xs-1 control-label">联系电话<font style="color: red">*</font></label>
                                    <div class="col-xs-6 element">
                                        <input type="text" class="form-control" id="phone" name="phone"  autocomplete="off" placeholder="请输入联系电话">
                                    </div>
                                    <div class="col-xs-4"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="register-footer" style="margin-top: 20px;">
                            <a  class="layui-btn layui-btn-radius col-xs-3 col-xs-offset-1" href="/" style="text-decoration: none;">回到首页</a>
                            <div class="col-xs-3"></div>
                            <button type="submit" class="layui-btn layui-btn-radius layui-btn-normal col-sm-3" onclick="register()">注册</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="footer" style="background-color: #fff;margin-bottom: 30px;">
        <div class="col-md-12">
            <p class="text-center" style="margin-bottom: 0px">为了更好的页面体验，建议使用IE 11,chrome 51,Firefox 54及以上版本</p>
            <%@include file="../../base/footer.jsp"%>
        </div>
    </div>
</div>
</body>
<script src="/static/js/admin/index/register.js"></script>
</html>
