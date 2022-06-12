<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="../../base/head-meta.jsp"%>
<%@include file="../../base/head-meta-js.jsp"%>
<link rel="stylesheet" href="/static/iCheck/skins/flat/purple.css">
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
                      <a style="cursor: inherit">企业申请</a>
                      <a href="/capcplus/go/qulification-apply">企业资质申请</a>
                      <a href="/capcplus/go/enterprise-info">企业信息</a>
                    </span>
                    <hr>
                </div>
                <%--内容--%>
                <div class="col-sm-12 enterprise-content">
                    <div class="panel panel-info">
                        <div class="panel-heading">单位信息</div>
                        <div class="panel-body">
                            <form class="form-horizontal body-info">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">单位类型</label>
                                    <div class="col-sm-6">
                                        <label id="lblUnitType" class="control-label" style="text-align: right;">${company.type.toString()}</label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">证件类型</label>
                                    <div class="col-sm-6" style="margin-top: 3px;">
                                        <input type="radio" name="iCheck" checked>
                                        <label>多证合一营业执照（不存在独立的组织机构代码证）</label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="name" class="col-sm-2 control-label">企业名称</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" id="name" value="${company.name}" readonly="readonly">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="registerNumber" class="col-sm-2 control-label">注册号</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" id="registerNumber" value="${company.registerCode}" readonly="readonly">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="position" class="col-sm-2 control-label">单位所在地</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" id="position" value="${company.companyAddress}" readonly="readonly">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="telephone" class="col-sm-2 control-label">固定电话</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" id="telephone" value="${company.telPhone}" readonly="readonly">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="business-range" class="col-sm-2 control-label">经营范围</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" id="business-range" value="${company.companyScope}" readonly="readonly">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="business-date" class="col-sm-2 control-label">营业期限</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" id="business-date" value="${company.dueDate}" readonly="readonly">
                                    </div>
                                </div>

                            </form>
                        </div>
                        <div class="panel-footer panel panel-warning">联系方式</div>
                        <div class="panel-body">
                            <form class="form-horizontal pannel-footer-body">
                                <div class="form-group">
                                    <label for="phone" class="col-sm-2 control-label">联系人手机号</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" id="phone" value="${company.tempPhone}" readonly="readonly">
                                    </div>
                                </div>
                            </form>
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
<style>
    *{
        margin:0;
        padding: 0;
    }
    body{
        color: #768399;
    }
    /*顶部导航*/
    .body-header{
        height: 6%!important;
    }
    .navbar-brand{
        padding: 0!important;
    }
    .nav-pills{
        margin-bottom: 0;
        padding-right: 20px;
        background: #75b9e6;
        border-radius: 0;
        border: none;
        box-shadow: 0 1px 1px rgba(0, 0, 0, 0.1);
    }
    /*侧边栏*/
    .left-slide .leftContainer{
        height:100%;
        z-index: 1400;
        position: fixed;
    }
    .leftContainer li a{
        text-decoration: none;
    }
    /*窗口小于1200的时候*/
    @media screen and (max-width: 1200px) {
        .left-slide{
            visibility: hidden;
            width: 0%;
            height: 0px;
        }
        .body-container{
            width: 100%;
        }
        .body-container .row{
            margin-left: 0;
        }
    }
    /*面包屑*/
    .bread-nav{
        margin-top: 15px;
    }
    .bread-nav a{
        text-decoration: none;
    }
    .bread-nav a:hover{
        color: #1E9FFF!important;
    }
</style>
<script>
    $(document).ready(function(){
        $('input').iCheck({
            checkboxClass: 'icheckbox_flat-purple',
            radioClass: 'iradio_flat-purple'
        });
    });
</script>
</body>
</html>
