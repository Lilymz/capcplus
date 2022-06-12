<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<%@include file="../../base/head-meta.jsp"%>
<%@include file="../../base/head-meta-js.jsp"%>
<link rel="stylesheet" href="/static/css/admin/index/cverify.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/fullPage.js/4.0.8/fullpage.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/fullPage.js/4.0.8/fullpage.css" integrity="sha512-71o4VLexkvCSZf9nAI/uWPoBJDV8q6g8q3kvuIth20F8kjI+Zg1zNbbJSa+V5S89Has+E6rMadf8xvLSO1Zlyw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<body>
<div>
    <ul class="nav nav-pills main_header" style="border-bottom: 1px solid silver;">
        <li>
            <img alt="logo" src="/resources/assets/home/others/logo1.png" style="width:150px;height:50px;margin-top: 5px">
            <img src="/resources/assets/home/others/title.png" alt="title" style="position:relative;bottom: 10px;">
        </li>
        <li role="presentation" class="navbar-right"><a href="#">登录</a></li>
        <li role="presentation" class="navbar-right"><a href="/#api">接口API</a></li>
        <li role="presentation" class="navbar-right"><a href="/#server">产品服务</a></li>
        <li role="presentation" class="navbar-right active"><a href="/#scene">应用场景</a></li>
        <li role="presentation" class="navbar-right"><a href="/#index">首页</a></li>
    </ul>
    <div class="container">
        <%--content--%>
        <div class="row">
            <div class="col-sm-12">
                <div class="row">
                    <div class="cverify-title col-sm-offset-5 col-sm-5">
                        <a href="javascript:showDetail(0);"><img src="/resources/assets/cverify/detail_1_2.png" alt="统一认证"><span class="a-color">
                    统一认证</span></a>
                    </div>
                </div>
                <div class="row animated bounceInDown">
                    <div class="col-sm-12">
                        <div class="applicScence">
                            <div class="applicScence_title col-sm-offset-3 col-sm-5">产品特性</div>
                            <div class="col-md-12 applicScenceMain">
                                <div class="col-md-6 applicScenceImg">
                                    <img src="/resources/assets/cverify/product1.jpg" alt="产品特性">
                                </div>
                                <div class="col-md-5 applicScenceAccout" style="margin-top: 10px;">
                                    <h4>一键登录三网合一</h4>
                                    <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;“一键授权，便捷秒登”。应用客户端中植入运营商认证的集成SDK后，在注册或登录阶段，用户同意授权即可完成本机号码免密登录，可以说是一种APP登录方式的革新，可以真正体验到“秒级登录”的快感。</p>
                                    <h4 style="margin-top: 10px">相比短信更便捷与安全</h4>
                                    <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;操作简单，助力注册拉新/登录留存，替代目前行业普遍采用短信验证码的方式，短验存在等待时间长、步骤较多、文本泄露等问题，使用一键登录极大提升用户体验。</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row animated bounceInUp">
                    <div class="col-sm-12">
                        <div class="productAdv">
                            <div class="productAdv_title">产品优势</div>
                            <div class="productAdvMain">
                                <ul>
                                    <li class="col-md-4 productAdvList">
                                        <div class="advList_l"><img src="/resources/assets/cverify/quicksend.png" alt="快速验证"></div>
                                        <div class="advList_R">
                                            <p>快速验证</p>
                                            <span>优质通道支持确保快速验证</span>
                                        </div>
                                    </li>
                                    <li class="col-md-4 productAdvList">
                                        <div class="advList_l"><img src="/resources/assets/cverify/selfcontrol.png" alt="免密快捷"></div>
                                        <div class="advList_R">
                                            <p>免密快捷</p>
                                            <span>助力用户留存，本机号码校验免密更快捷</span>
                                        </div>
                                    </li>
                                    <li class="col-md-4 productAdvList">
                                        <div class="advList_l"><img src="/resources/assets/cverify/highcrash.png" alt="高效对接"></div>
                                        <div class="advList_R">
                                            <p>高效对接</p>
                                            <span>支持IOS\安卓三网合一SDK开发工具快速对接</span>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row animated lightSpeedIn">
                    <div class="col-sm-12">
                        <div class="usingClients">
                            <div class="interfaceDoc_title">接口文档</div>
                            <div class="interfaceDocMain">
                                <button onclick="directToSort('10000006','统一认证类','');">统一认证API文档</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row animated lightSpeedIn">
                    <div class="col-sm-12">
                        <div class="interfaceDoc">
                            <div class="commonFAQ_title">常见FAQ</div>
                            <div class="commonFAQMain">
                                <button onclick="straightFAQ('');">统一认证常见FAQ</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="footer" style="background-color: #fff;margin-bottom: 30px;">
        <div class="col-md-12">
            <%@include file="../../base/footer.jsp"%>
        </div>
    </div>
</div>
</body>
<script>

</script>
</html>
