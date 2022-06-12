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
                      <a href="/capcplus/go/index"><i class="fa fa-ticket" aria-hidden="true"></i>&nbsp;发票管理</a>
                      <a href="/capcplus/go/index"><i class="fa fa-info" aria-hidden="true"></i>&nbsp;开票信息</a>
                    </span>
                    <hr>
                </div>
                <%--内容--%>
                <div class="col-sm-12 enterprise-content">
                    <div class="panel panel-info">
                        <div class="panel-heading"><i class="fa fa-pencil" aria-hidden="true"></i>修改开票信息</div>
                        <div class="panel-body">
                            <form class="form-horizontal body-info" id="update-ticket">
                                <input type="hidden" value="${ticket.id}" name="idStr">
                                <div class="form-group">
                                    <label for="ticketMan" class="col-sm-2 control-label"><font style="color: red">*</font>发票抬头</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" id="ticketMan" name="ticketMan" autocomplete="off" placeholder="请填写发票抬头" value="${ticket.ticketMan}">
                                    </div>
                                </div>
                                <hr>
                                <div class="form-group">
                                    <label for="creditCode" class="col-sm-2 control-label"><font style="color: red">*</font>统一社会信用代码/税务登记号</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" id="creditCode" name="creditCode" autocomplete="off" placeholder="请填写统一社会信用代码/税务登记号" value="${ticket.creditCode}">
                                    </div>
                                </div>
                                <hr>
                                <div class="form-group">
                                    <label for="bank" class="col-sm-2 control-label"><font style="color: red">*</font>开户行名称</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" id="bank" name="bank" autocomplete="off" placeholder="请填写开户行名称" value="${ticket.bank}">
                                    </div>
                                </div>
                                <hr>
                                <div class="form-group">
                                    <label for="bankAccount" class="col-sm-2 control-label"><font style="color: red">*</font>开户行账号</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" id="bankAccount" name="bankAccount" autocomplete="off" placeholder="请填写开户行账号" value="${ticket.bankAccount}">
                                    </div>
                                </div>
                                <hr>
                                <div class="form-group">
                                    <label for="businessPhone" class="col-sm-2 control-label"><font style="color: red">*</font>营业电话</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" id="businessPhone" name="businessPhone" autocomplete="off" placeholder="请填写营业电话" value="${ticket.businessPhone}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="businessAddress" class="col-sm-2 control-label"><font style="color: red">*</font>营业地址</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" id="businessAddress" name="businessAddress" autocomplete="off" placeholder="请填写营业地址" value="${ticket.businessAddress}">
                                    </div>
                                </div>
                                <hr>
                                <div class="form-group">
                                    <label for="typeEnum" class="col-sm-2 control-label"><font style="color: red">*</font>纳税人资格类型</label>
                                    <input type="radio" name="typeEnum" lass="icheckbox_flat-purple" value="SMALL_SCALE" ${ticket.type.code ==1?'checked':''}>
                                    <label>小规模纳税人</label>
                                    <input type="radio" name="typeEnum" class="icheckbox_flat-purple" value="ORDINARY" ${ticket.type.code ==2?'checked':''}>
                                    <label>一般纳税人</label>
                                </div>
                                <hr>
                                <div class="form-group">
                                    <label for="businessLicense" class="col-sm-2 control-label"><font style="color: red">*</font>营业执照/税务登记证扫描件</label>
                                    <div class="col-sm-6">
                                        <input type="text" readonly="readonly" class="form-control" id="businessLicense" name="businessLicense" autocomplete="off" value="${ticket eq null?'':"/resources/images/"}${ticket.businessLicense}">
                                    </div>
                                    <div class="col-sm-1">
                                        <button type="button" class="btn btn-active" id="businessLicense-upload">
                                            <i class="layui-icon">&#xe67c;</i>上传图片
                                        </button>
                                    </div>
                                    <div class="col-sm-1">
                                        <button type="button" class="layui-btn layui-btn-radius btn btn-info" id="businessLicense-view">
                                            <i class="glyphicon glyphicon-picture"></i>预览
                                        </button>
                                    </div>
                                </div>
                                <hr>
                                <div class="form-group">
                                    <label for="qualification" class="col-sm-2 control-label">一般纳税人资格认证扫描件</label>
                                    <div class="col-sm-6">
                                        <input type="text" readonly="readonly" class="form-control" id="qualification" name="qualification" autocomplete="off" value="${ticket eq null?'':"/resources/images/"}${ticket.qualification}" >
                                    </div>
                                    <div class="col-sm-1">
                                        <button type="button" class="btn btn-active" id="qualification-upload">
                                            <i class="layui-icon">&#xe67c;</i>上传图片
                                        </button>
                                    </div>
                                    <div class="col-sm-1">
                                        <button type="button" class="layui-btn layui-btn-radius btn btn-info" id="qualification-view">
                                            <i class="glyphicon glyphicon-picture"></i>预览
                                        </button>
                                    </div>
                                </div>
                                <hr>
                                <div class="form-group">
                                    <label for="otherFile" class="col-sm-2 control-label">其他文件</label>
                                    <div class="col-sm-6">
                                        <input type="text" readonly="readonly" class="form-control" id="otherFile" name="otherFile" autocomplete="off" value="${ticket eq null?'':"/resources/images/"}${ticket.otherFile}">
                                    </div>
                                    <div class="col-sm-1">
                                        <button type="button" class="btn btn-active" id="otherFile-upload">
                                            <i class="layui-icon">&#xe67c;</i>上传图片
                                        </button>
                                    </div>
                                    <div class="col-sm-1">
                                        <button type="button" class="layui-btn layui-btn-radius btn btn-info" id="otherFile-view">
                                            <i class="glyphicon glyphicon-picture"></i>预览
                                        </button>
                                    </div>
                                </div>
                                <hr>
                                <div class="form-group">
                                    <label for="remark" class="col-sm-2 control-label">备注</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" id="remark" autocomplete="请填写备注" name="remark"  value="${ticket.remark}">
                                    </div>
                                </div>
                                <hr>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">审核状态</label>
                                    <div class="col-sm-6" style="margin-left: 14px;margin-top: 6px">
                                        <label id="status">${ticket eq null ? '<small style="color:red">请提交开票信息~</small>':ticket.status.codeStr}</label>
                                    </div>
                                </div>
                                <hr>
                                <div class="form-group" style="display: flex;align-items: center;justify-content: center">
                                    <button type="button" class="layui-btn layui-btn-normal" onclick="updateTicket();"><i class="fa fa-file" aria-hidden="true"></i>提交</button>
                                    <button type="button" class="layui-btn layui-btn-normal" onclick="refresh();"><i class="fa fa-repeat" aria-hidden="true"></i>重置</button>
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
<script src="/static/js/capcplus/ticket/ticket-info.js"></script>
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
