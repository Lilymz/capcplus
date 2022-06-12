<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="../../base/head-meta.jsp"%>
<%@include file="../../base/head-meta-js.jsp"%>
<link href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
<body>
<div class="row cverify-module">
    <div class="cverify-container">
        <div id="developmentPart">
            <div class="col-sm-6 col-md-3 isotope-item app-development">
                <div class="image-box">
                    <div class="overlay-container" style="border: 1px solid #ccc; border-bottom: 0px;">
                        <img src="/resources/assets/cverify/oneclick.jpg" alt="">
                        <a class="overlay" href="javascript:directToSort('oneclick','一键登录');">
                            <i class="fa fa-book fa-3x"></i>
                            <span>点击查看</span>
                        </a>
                    </div>
                    <a class="btn btn-default btn-block modal-part api_font" data-toggle="modal" data-target="#project-4">一键登录</a>
                </div>
            </div>
            <div class="col-sm-6 col-md-3 isotope-item app-development">
                <div class="image-box">
                    <div class="overlay-container" style="border: 1px solid #ccc; border-bottom: 0px;">
                        <img src="/resources/assets/cverify/phonecheck.jpg" alt="">
                        <a class="overlay" href="javascript:getApi('phonecheck','本机号码校验');">
                            <i class="fa fa-book fa-3x"></i>
                            <span>点击查看</span>
                        </a>
                    </div>
                    <a class="btn btn-default btn-block modal-part api_font" data-toggle="modal" data-target="#project-4">本机号码校验</a>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    function getApi(type,sortName) {
        var screenHeight = document.documentElement.clientHeight;
        var screenWidth = document.documentElement.clientWidth;
        layer.open({
            title: '<b><i class="st-book2"/>' + sortName + '</b>',
            type: 2,
            area: [screenWidth * 1 + 'px', screenHeight * 1 + 'px'],
            fixed: false,
            resize: true,
            scrollbar: false,
            shadeClose: true,
            content: '/api/doc/go/detail',
            moveOut: true,
            anim: -1,
            btn: ['返回'],
            yes: function (index, layero) {
                layer.closeAll();
            }
        });
        $(".layui-layer-btn0").css("float","left");
        $(".layui-layer-setwin").css("display","none");
    }
</script>
<style>
    body{
        overflow: hidden;
    }
    .api_font{
        display: inline-block;
        margin-bottom: 0;
        font-weight: bold;
        text-align: center;
        vertical-align: middle;
        cursor: pointer;
        background-image: none;
        border: 1px solid transparent;
        white-space: nowrap;
        padding: 6px 12px;
        font-size: 14px;
        line-height: 1.42857143;
        border-radius: 4px;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
        color: #999999;
        -webkit-transition: all 0.3s ease-in-out;
        -moz-transition: all 0.3s ease-in-out;
        -ms-transition: all 0.3s ease-in-out;
        -o-transition: all 0.3s ease-in-out;
        transition: all 0.3s ease-in-out
    }
    .api_font:hover{
        background-color: #339BEB;
        border-color: #339BEB;
        color: white;
    }
    .overlay-container:hover .overlay {
        opacity: 1;
        filter: alpha(opacity=100);
        -webkit-transform: scale(1);
        transform: scale(1)
    }
    .isotope-item {
        margin-bottom: 20px;
    }
    .overlay-container {
        position: relative;
        display: block;
        overflow: hidden;
    }
    img {
        display: block;
        max-width: 100%;
        height: auto;
    }
    .overlay {
        position: absolute;
        top: 0;
        bottom: -1px;
        left: 0;
        right: -1px;
        background-color: #4595a8;;
        cursor: pointer;
        overflow: hidden;
        opacity: 0;
        filter: alpha(opacity=0);
        -webkit-transform: scale(0.8);
        transform: scale(0.8);
        -webkit-transition: all linear 0.2s;
        -moz-transition: all linear 0.2s;
        -ms-transition: all linear 0.2s;
        -o-transition: all linear 0.2s;
        transition: all linear 0.2s;
        z-index: 200;
    }
    .overlay i {
        position: absolute;
        left: 50%;
        top: 50%;
        font-size: 18px;
        line-height: 1px;
        color: #ffffff;
        margin-top: -8px;
        margin-left: -8px;
        text-align: center;
    }
    .overlay span {
        position: absolute;
        display: block;
        bottom: 10px;
        text-align: center;
        width: 100%;
        color: #ffffff;
        font-size: 13px;
        font-weight: 300;
    }
    a:hover {
        color: #4595a8;
    }
</style>
</body>
</html>
