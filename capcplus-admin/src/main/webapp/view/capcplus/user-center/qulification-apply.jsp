<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="../../base/head-meta.jsp"%>
<%@include file="../../base/head-meta-js.jsp"%>
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
                    </span>
                    <hr>
                </div>
                <%--资质申请内容--%>
                <div class="col-sm-12 qualification-content">
                    <div class="layui-collapse col-sm-8" lay-accordion>
                        <div class="layui-colla-item">
                            <h2 class="layui-colla-title">企业资质申请提示<i style="float: right;position:relative;top:11px" class="fa fa-pencil" aria-hidden="true"></i></h2>
                            <div class="layui-colla-content layui-show">
                                <c:choose>
                                    <%--审核通过--%>
                                    <c:when test="${company.auditStatus.code==1}">
                                        <p style="font-size:14px;">
                                            <i class="fa fa-smile-o" style="color:#69C550;" aria-hidden="true"></i>&nbsp;
                                            恭喜！您的企业信息已通过审核
                                        </p>
                                        <div style="text-align: center;">
                                            <a class="btn btn-info" value="查看企业信息" href="/capcplus/go/enterprise-info"><i class="ec-pencil"></i>&nbsp;查看企业信息</a>
                                        </div>
                                    </c:when>
                                    <%--审核驳回--%>
                                    <c:when test="${company.auditStatus.code==2}">
                                        <p style="font-size:14px;">
                                            <i class="fa fa-smile-o" style="color:#69C550;" aria-hidden="true"></i>&nbsp;
                                            sorry,你的企业信息未通过审核！
                                        </p>
                                        <p style="font-size:14px;">
                                            审核失败原因如下：
                                        </p>
                                        <p style="font-size:14px;">您的${company.auditReason},如有疑问，可联系系统管理员</p>
                                        <div style="text-align: center;">
                                            <a class="btn btn-info" value="查看企业信息" href="/capcplus/go/enterprise-info"><i class="ec-pencil"></i>&nbsp;重新申请</a>
                                        </div>
                                    </c:when>
                                    <%--待审核--%>
                                    <c:when test="${company.auditStatus.code==3}">
                                        <p style="font-size:14px;">
                                            <i class="fa fa-smile-o" style="color:#69C550;" aria-hidden="true"></i>&nbsp;
                                            信息正在审核中....我们会将审核结果以邮件的形式发送到您的邮箱
                                        </p>
                                    </c:when>
                                    <%--未进行企业信息提交--%>
                                    <c:otherwise>
                                        <p style="font-size:14px;">
                                            <i class="layui-icon layui-icon-set-fill" style="color: #1E9FFF;"></i>
                                            尊敬的用户您好，目前您还尚未登记企业信息。
                                        </p>
                                        <div style="text-align: center;">
                                            <a class="btn btn-info" value="查看企业信息" href="/capcplus/go/add-company"><i class="fa fa-file-text" aria-hidden="true"></i>&nbsp;去登记</a>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
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
<style>
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
    .qualification-content{
        display: flex;
        justify-content: center;
        align-items: center;
        height: 85%;
    }
</style>
</html>
