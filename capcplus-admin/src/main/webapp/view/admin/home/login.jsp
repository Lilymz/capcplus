<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN"l>
<%@include file="../../base/head-meta.jsp"%>
<%@include file="../../base/head-meta-js.jsp"%>
<link rel="stylesheet" href="/static/iCheck/skins/flat/blue.css">
<link rel="stylesheet" href="/static/css/admin/index/login.css">
<script src="/static/iCheck/icheck.min.js"></script>
<script src="/static/iCheck/jquery.toggle-password.min.js"></script>
<link href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
<body>
    <div class="body-container">
        <div style="height: 75px;text-align: left;padding: 10px 0 10px 10px;position: relative">
            <a href="/" style="cursor: pointer" class="">
            <img src="/resources/assets/home/others/logo1.png" alt="logo" style="height: 100%;"></a>
        </div>
        <div class="container">
            <div class="row">
                <div class="login-module col-sm-12">
                    <div class="login-header">
                        <h3 class="wizard-title">用户登录</h3>
                    </div>
                    <div class="login-content">
                        <form id="login-form" onsubmit="return false">
                            <div class="form-group col-sm-12">
                                <label for="account" class="col-sm-2 control-label account-label">账号</label>
                                <input type="account" class="form-control col-sm-10 layui-input" id="account" autocomplete="off" name="account" placeholder="请输入账号">
                            </div>
                            <div class="form-group">
                                <div class="col-sm-12">
                                    <label for="password" class="col-sm-2 control-label  password-label">密码</label>
                                    <input type="password" class="form-control col-sm-8 layui-input" id="password" autocomplete="off" name="password" placeholder="请输入密码">
                                </div>
                                <div class="col-sm-12 password-tip">
                                    <i class="fa fa-eye fa-1x tip-password"></i>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="row row-no-gutters">
                                    <div class="col-sm-12 code-label">
                                        <label for="code" class="col-sm-2 control-label" style="position:relative;left: 30px;">验证码</label>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-7" style="position:relative;left: 18px;">
                                        <input type="text" class="form-control layui-input" id="code" autocomplete="off" name="code" placeholder="请输入验证码">
                                    </div>
                                    <div class="col-sm-offset-1 col-sm-3" style="position:relative;left:10px;bottom: 6px">
                                        <a href="javascript:void(0);" title="点击刷新" style="text-decoration: underline;" class="">
                                            <img id="codeImg" src="/img/code" alt="验证码" height="70" width="150">
                                        </a>
                                    </div>
                                </div>
                            </div>
                            <div class="remembeMe col-sm-12">
                                <label><input type="checkbox" name="isRememberMe"> 记住我</label>
                            </div>
                            <div class="col-sm-12 others-operator">
                                <div class="register col-sm-6">
                                    <i class="fa fa-registered"></i>
                                    <a href="/admin/go/register">没有账号?马上注册</a>
                                </div>
                                <div class="phone col-sm-6">
                                    <a href="#">手机号登录</a>
                                </div>
                                <div class="forget col-sm-12">
                                    <a href="/admin/go/find-account">忘记密码?</a>
                                </div>
                            </div>
                            <div class="col-sm-12 operator-option">
                                <a  class="layui-btn layui-btn-radius col-sm-4" href="/">回到首页</a>
                                <div class="col-sm-4"></div>
                                <button type="submit" class="layui-btn layui-btn-radius layui-btn-normal col-sm-4" onclick="login()">登录</button>
                            </div>
                        </form>
                    </div>
                    <div class="login-footer"></div>
                </div>
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
<script>
</script>
<script src="/static/js/admin/index/login.js"></script>
</html>
