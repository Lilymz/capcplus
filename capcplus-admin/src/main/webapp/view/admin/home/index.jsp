<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<%@include file="../../base/head-meta.jsp"%>
<%@include file="../../base/head-meta-js.jsp"%>
<link rel="stylesheet" href="/static/css/admin/index/index.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/fullPage.js/4.0.8/fullpage.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/fullPage.js/4.0.8/fullpage.css" integrity="sha512-71o4VLexkvCSZf9nAI/uWPoBJDV8q6g8q3kvuIth20F8kjI+Zg1zNbbJSa+V5S89Has+E6rMadf8xvLSO1Zlyw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<body>
    <div>
        <%--fullpage进行屏幕切换--%>
        <div id="fullpage">
            <%--第一屏 banner部分--%>
            <div class="section active" id="index-section">
                <div class="swiper-container banner1" style="position: relative">
                    <div class="swiper-wrapper">
                        <div class="swiper-slide">
                            <%--banner title--%>
                            <div class="banner-title-main">
                                <div class="banner-pro-title1">
                                    <p class="ani" swiper-animate-effect="zoomIn" swiper-animate-duration="0.5s" swiper-animate-delay="0.3s">大象能力平台</p>
                                </div>
                                <div class="banner-pro-title2">
                                    <p class="ani" swiper-animate-effect="zoomIn" swiper-animate-duration="0.5s" swiper-animate-delay="0.3s">
                                        您身边的自助式企业通信服务平台</p>
                                </div>
                                <div class="banner-pro-title3">
                                    <p class="ani" swiper-animate-effect="zoomIn" swiper-animate-duration="0.6s" swiper-animate-delay="0.3s">
                                        快速接入，获取统一认证一键登录能力
                                    </p>
                                </div>
                                <div class="ani banner-inner-img" swiper-animate-effect="bounceInUp" swiper-animate-duration="0.3s" swiper-animate-delay="0.3s">
                                    <img class="lazy" data-original="/resources/assets/home/index/banner1-img.png" alt="" width="409" height="412" src="/resources/assets/home/index/banner1-img.png">
                                </div>
                            </div>
                            <img src="/resources/assets/home/index/banner1.png" alt="title" style="position: relative;bottom: 17px;">
                        </div>
                        <div class="swiper-slide">
                            <%--banner title--%>
                            <div class="banner-title-main">
                                <div class="banner-pro-title1">
                                    <p class="ani" swiper-animate-effect="zoomIn" swiper-animate-duration="0.5s" swiper-animate-delay="0.3s">
                                        助力企业通信统一认证服务</p>
                                </div>
                                <div class="banner-pro-title2">
                                    <p class="ani" swiper-animate-effect="zoomIn" swiper-animate-duration="0.5s" swiper-animate-delay="0.3s">
                                        登录注册，一键认证</p>
                                </div>
                                <div class="banner-pro-title3">
                                    <p class="ani" swiper-animate-effect="zoomIn" swiper-animate-duration="0.5s" swiper-animate-delay="0.3s" >
                                        专注于为企业用户提供优质服务
                                    </p>
                                </div>
                                <div class="ani banner-inner-img" swiper-animate-effect="bounceInUp" swiper-animate-duration="0.3s" swiper-animate-delay="0.3s">
                                    <img class="lazy" data-original="/resources/assets/home/index/banner2-img.png" alt="" width="605" height="307" src="/resources/assets/home/index/banner2-img.png">
                                </div>
                            </div>
                            <img src="/resources/assets/home/index/banner2.png" alt="title" style="position: relative;bottom: 17px;">
                        </div>
                    </div>
                    <!-- 如果需要分页器 -->
                    <div class="swiper-pagination"></div>
                    <!-- 如果需要导航按钮 -->
                    <div class="swiper-button-prev swiper-button-white"></div>
                    <div class="swiper-button-next swiper-button-white"></div>
                    <!-- 如果需要滚动条 -->
                    <div class="swiper-scrollbar"></div>
                </div>
                <div class="bannerFooter">
                    <ul style="height:100%;max-width:1400px;margin:0 auto;">
                        <li>
                            <div class="bfooter-l"><img class="lazy" data-original="/resources/assets/home/index/adv1.png" 高度稳定="" width="200" alt="" height="200" src="/resources/assets/home/index/adv1.png" style=""></div>
                            <div class="bfooter-r">
                                <h4>高度稳定</h4>
                                <span>三网合一专属通道，多通道备用</span>
                            </div>
                        </li>
                        <li>
                            <div class="bfooter-l"><img class="lazy" data-original="/resources/assets/home/index/adv2.png" alt="高速率" width="200" height="200" src="/resources/assets/home/index/adv2.png" style=""></div>
                            <div class="bfooter-r">
                                <h4>高速率</h4>
                                <span>API简单易用，接入更高效</span>
                            </div>
                        </li>
                        <li>
                            <div class="bfooter-l"><img class="lazy" data-original="/resources/assets/home/index/adv3.png" alt="低成本" width="200" height="200" src="/resources/assets/home/index/adv3.png" style=""></div>
                            <div class="bfooter-r">
                                <h4>低成本</h4>
                                <span>按需付费，价格优惠</span>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
            <%--第二屏 应用场景--%>
            <div class="section" id="appScene-section" style="display: inherit;height:937px;">
                <ul class="nav nav-pills main_header" style="border-bottom: 1px solid silver;">
                    <li>
                        <div class="jw-content"><a>集微</a></div>
                        <img alt="logo" src="/resources/assets/home/others/logo1.png" style="width:150px;height:50px;margin-top: 5px">
                        <img src="/resources/assets/home/others/title.png" alt="title" style="position:relative;bottom: 10px;">
                    </li>
                    <li role="presentation" class="navbar-right"><a href="/admin/login">登录</a></li>
                    <li role="presentation" data-menuanchor="api" class="navbar-right"><a href="#api">接口API</a></li>
                    <li role="presentation" data-menuanchor="server" class="navbar-right"><a href="#server">产品服务</a></li>
                    <li role="presentation" data-menuanchor="scene" class="navbar-right active"><a href="#scene">应用场景</a></li>
                    <li role="presentation" data-menuanchor="index" class="navbar-right"><a href="#index">首页</a></li>
                </ul>
                <div class="widthWrap" style="width: inherit;height: 93%">
                    <div class="work_title">应用场景</div>
                    <div class="second_title">优质全通信能力服务平台</div>
                    <div class="container-fluid appScene-container" style="position: absolute;top: 33%;width: 100%;">
                        <div class="row">
                            <div class="col-sm-offset-2 col-md-3 marginBox">
                                <img class="lazy" data-original="/resources/assets/home/appScene/work1_1.png" alt="用户注册" width="50" height="50" src="/resources/assets/home/appScene/work1_1.png" style="opacity: 0.7;">
                                <div class="change-color-blue">
                                    <h3 style="color: rgb(51, 51, 51);">用户注册</h3>
                                    <p style="color: rgb(136, 136, 136);">一键登录：用户注册时，一键登录用于验证用户手机号码是否本机，杜绝恶意注册，确保用户的真实性，相比短信验证码更迅速便捷，确保用户不会轻易流失。</p>
                                </div>
                            </div>
                            <div class="col-sm-offset-2 col-md-3 marginBox">
                                <img class="lazy" data-original="/resources/assets/home/appScene/work2.png" alt="支付认证" width="50" height="50" src="/resources/assets/home/appScene/work2.png" style="opacity: 0.7;">
                                <h3 style="color: rgb(51, 51, 51);">支付认证</h3>
                                <p style="color: rgb(136, 136, 136);">一键登录：支付环节，需要验证号码是否对应本机，确保资金安全，如确认支付、快捷支付、闪付等，可以使用一键登录快捷验证。</p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-offset-2 col-md-3 marginBox" style="cursor: pointer!important;">
                                <img src="/resources/assets/home/appScene/work4.png" alt="用户快速登录" style="opacity: 0.7;">
                                <h3 style="color: rgb(51, 51, 51);">用户快速登录</h3>
                                <p style="color: rgb(136, 136, 136);">本机号码校验：通过用户注册时的预留手机号，通过本机号码校验，确认用户身份，完成登录，也可以用于密码重置。</p>
                            </div>
                            <div class="col-sm-offset-2 col-md-3 marginBox">
                                <img class="lazy" data-original="/resources/assets/home/appScene/work3.png" alt="账号认证" width="50" height="50" src="/resources/assets/home/appScene/work3.png" style="opacity: 0.7;">
                                <h3 style="color: rgb(51, 51, 51);">账号认证</h3>
                                <p style="color: rgb(136, 136, 136);">一键登录：用户账号在使用手机号码异常登录网站或者APP时，通过一键登录及时号码认证，制止账号异常登录，进行安全防护。</p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-10" style="margin-top:60px;left: 220px;">
                                <div class="adv-gray">
                                    <img src="/resources/assets/home/productServer/Ad1.png" alt="广告位">
                                    <img src="/resources/assets/home/productServer/Ad2.png" alt="广告位">
                                    <button class="close-adv" style="right: -82px;" onclick="closeAd();">X</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <%--第三屏 产品服务--%>
            <div class="section">
                <div class="widthWrap" style="background-color: #f4f7fb;">
                    <div class="work_title2">产品与服务</div>
                    <div class="second_title2">热门产品服务介绍及体验入口</div>
                    <span class="swiper-pagination-bullet swiper-pagination-bullet-active"
                          style="position: relative; left: 47%; display: inline-block; width: 14.5%;height: 45px;margin: 0 auto 35px;background-color: #f9fafc; opacity: 1;border-radius: 10px;color: #fff;">
                        <a style="font-size: 27px;color: #3498db !important; font-weight: bold;text-decoration:none;">统一认证</a>
                    </span>
                    <div class="container">
                        <div class="row">
                            <div class="col-sm-11">
                                <div class="swiper-container banner2-container">
                                    <div class="swiper-wrapper">
                                        <div class="swiper-slide">
                                            <%--banner title--%>
                                            <div class="p_slide1">
                                                <h2>统一认证</h2>
                                                <p>●&nbsp;&nbsp;一键登录，一个按钮替代短信验证多步骤</p>
                                                <p>●&nbsp;&nbsp;登录注册，验证更安全</p>
                                                <p>●&nbsp;&nbsp;极速认证，操作更便捷</p>
                                                <p>●&nbsp;&nbsp;优质资源确保高效、安全</p>
                                                <a class="layui-btn layui-btn-radius layui-btn-normal text-decoration-none" href="/cverify/go/detail">查看详情</a>
                                            </div>
                                            <img src="/resources/assets/home/productServer/product6.jpg" alt="product6" width="548" height="248" style="position: relative;bottom: 17px;">
                                        </div>
                                        <div class="swiper-slide">
                                            <%--banner title--%>
                                            <div class="p_slide1">
                                                <h2>统一认证</h2>
                                                <p>●&nbsp;&nbsp;一键登录，一个按钮替代短信验证多步骤</p>
                                                <p>●&nbsp;&nbsp;登录注册，验证更安全</p>
                                                <p>●&nbsp;&nbsp;极速认证，操作更便捷</p>
                                                <p>●&nbsp;&nbsp;优质资源确保高效、安全</p>
                                                <a class="layui-btn layui-btn-radius layui-btn-normal text-decoration-none" href="/cverify/go/detail">查看详情</a>
                                            </div>
                                            <img src="/resources/assets/home/index/banner2.png" alt="title" width="548" height="248" style="position: relative;bottom: 17px;">
                                        </div>
                                    </div>
                                    <div class="banner2-pagination">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <%--第四屏 接口API--%>
            <div class="section">
                <div class="widthWrap">
                    <div class="work_title2">API说明</div>
                    <div class="second_title2">大象能力平台主要产品服务API说明</div>
                    <div class="container" style="bottom: 43%;left: 19%;position: absolute;">
                        <div class="row">
                            <div class="col-sm-offset-5 col-sm-6">
                                <button class="layui-btn layui-btn-primary btn-active" id="btn-all">全部</button>
                                <button class="layui-btn layui-btn-primary" id="btn-cverify">统一认证类</button>
                            </div>
                        </div>
                        <div class="row cverify-module">
                            <div class="overlay-container">
                                <div class="col-sm-offset-5 col-sm-7">
                                    <img class="lazy" src="/resources/assets/home/api/category-10000001.jpg" alt="cverify_img" width="300" height="178">
                                </div>
                                <a class="col-sm-offset-5 col-sm-7 overlay" id="smsSort" href="javascript:apiFrame(this,'统一认证类');">
                                    <i class="glyphicon glyphicon-search"></i>
                                    <span>点击查看</span>
                                </a>
                            </div>
                            <div class="col-sm-offset-5 col-sm-3" style="position: relative;left: 16px;">
                                <a class="btn btn-default btn-block modal-part api_font">统一认证类</a>
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
            </div>
        </div>
    </div>
    <div class="addEnter">
        <ul>
            <li>
                <img id="robot" alt="kefu" src="/resources/assets/home/others/kefu_im_white.png">
            </li>
            <li>
                <img id="dimension" alt="dimension" src="/resources/assets/home/others/dimension.png">
            </li>
            <li>
                <img id="top" alt="top" src="/resources/assets/home/others/top.png">
            </li>
        </ul>
    </div>
</body>
<script>
    layer.load();
    //此处演示关闭
    setTimeout(function(){
        layer.closeAll('loading');
    }, 300);
</script>
<script src="/static/js/admin/index/index.js"></script>
</html>
