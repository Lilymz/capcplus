<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                      <a href="/capcplus/go/recharge">充值</a>
                    </span>
                    <hr>
                </div>
                <%--内容--%>
                <div class="col-sm-12 enterprise-content">
                    <div class="panel panel-success">
                        <div class="panel-heading">充值</div>
                        <div class="panel-body">
                            <form class="form-horizontal body-info">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">账户余额</label>
                                    <div class="col-sm-6">
                                        <label id="lblUnitType" class="control-label" style="text-align: right;">&nbsp;${user.balance}&nbsp;&nbsp;元</label>
                                    </div>
                                </div>
                                <hr>
                                <div class="form-group">
                                    <label for="name" class="col-sm-2 control-label"><font color="red">*</font>充值金额 </label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" id="name" value="0.00">
                                    </div>
                                </div>
                                <hr>
                                <div class="form-group">
                                    <label for="registerNumber" class="col-sm-2 control-label">充值备注</label>
                                    <div class="col-sm-6">
                                        <textarea style="max-width: 400px;" cols="20" rows="5" class="form-control" id="remark" name="remark" maxlength="81" onkeyup="javascript:textareaCount()" placeholder="备注不超过81个字符"></textarea>
                                    </div>
                                    <label id="count">0</label>/81
                                </div>
                                <div class="form-group">
                                   <div class="col-sm-offset-2 col-sm-6">
                                       <button type="submit" class="layui-btn layui-btn-primary layui-border-blue">支付宝充值￥</button>
                                       <a class="layui-btn layui-btn-primary layui-border-green text-decoration-none" href="/capcplus/go/index">返回</a>
                                   </div>
                                </div>
                            </form>
                        </div>
                        <div class="panel-footer panel panel-warning">Tips</div>
                        <div class="panel-body">
                          <pre>
                              <code class=" hljs cs">
                                <br> <label class="hljs-keyword" style="color: red">支付宝充值将在财务审批后到账，请耐心等待</label>
                                <br> 1. 单笔充值金额范围(0.01~999,999.99)元。
                                <br> 2. 除了支付宝，您还可以选择银行公对公转账，然后联系客服或管理员确认。<button type="button" class="btn btn-info" id="btn-info" onclick="showInfo();"><i class="fa fa-check-circle-o" aria-hidden="true">我要对公</i></button>
                                <br> 3. 个人支付宝充值可开具增值税普通发票，公对公转账可开具增值税普通/专用发票。
                              </code>
                          </pre>
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
            checkboxClass: 'icheckbox_flat-blue',
            radioClass: 'iradio_flat-blue'
        });
    });
    function textareaCount() {
        $("#count").text($("#remark").val().length);
    }

    /**
     * 我要对公
     */
    function showInfo(){
        layer.open({
            type:1,
            title:'对公信息',
            content:
                '<div class="layui-layer-content" style="height: 333px;margin-left: 20px;">' +
                '    <span style="color: red;">对公后请及时与客服或管理员确认</span><br> ' +
                         '<br>开户单位：厦门集微科技有限公司    <br>开户账号：424 770 494 600   ' +
                         '<br>开户银行：中国银行集美支行  ' +
                        ' <br>纳税人识别号：91350211M0001D4C5A   ' +
                        ' <br>开票地址：厦门市软件园三期集美大道1302号1202单元    ' +
                         '<br>单位电话：0592-6190616    ' +
                        '<br>邮编：361021    ' +
                        '<br>传真：0592-6190616-808    ' +
                        '<br>集微联行号：104393040004    ' +
                        '<br>经营地址：福建省厦门市集美区诚毅北大街63号（软件园三期B20栋）1704' +
                '</div>',
            // skin:'layui-layer-molv',
            area:['554px','350px'],
            btn:["确认"],
            closeBtn:1,
            shade:0.6,
            shadeClose:true,
            anim: 3,
            yes: function(index, layero){
                layer.close(index); //如果设定了yes回调，需进行手工关闭
            }
        })
    }
</script>
</body>
</html>
