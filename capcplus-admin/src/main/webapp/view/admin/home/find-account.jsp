<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="../../base/head-meta.jsp"%>
<%@include file="../../base/head-meta-js.jsp"%>
<link rel="stylesheet" href="/static/css/admin/index/find-account.css">
<body>
<div style="height:100%;" class="body-container">
    <input type="hidden" id="userId">
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
    <div class="rebinding-box">
        <div class="box-title">
            <h2 class="mtb5">找回密码</h2>
            <i>为了保障您的帐户安全，请谨慎填写</i>
        </div>
        <div class="box-timeline">
            <ul class="text-center" style="width: 1000px;">
                <li>
                    输入账号
                    <div class="box-num1">
                        1
                    </div>
                </li>
                <li class="ml45">
                    邮箱验证
                    <div class="box-outside1 outside1ab" id="outside1abs">
                        <div class="box-num2 num2ab">
                            2
                        </div>
                    </div>
                </li>
                <li class="ml45">
                    填写新密码
                    <div class="box-outside2 outside2ab" id="outside2as">
                        <div class="box-num3 num3ab">
                            3
                        </div>
                    </div>
                </li>
                <li class="ml45">
                    完成
                    <div class="box-outside3 outside3a" id="outside3as">
                        <div class="box-num4 num4a">
                            4
                        </div>
                    </div>
                </li>
            </ul>


        </div>
        <!--第一步-->
        <div class="onebox-form" id="oneform">
            <div class="oneform">
                <div class="oneform-box">
                    <label class="oneform-label">登录账号</label>
                    <div class="oneform-input">
                        <input id="userName" autocomplete="off" placeholder="请填写要找回密码的帐号">
                    </div>
                </div>
                <div class="onebtn-box">
                    <button class="onebtn" id="onebtn" onclick="existAccount()">下一步</button>
                </div>
            </div>
        </div>
        <!--第二步-->
        <div class="twobox-form" id="twoform">
            <div class="twoform">
                <div class="twoform-box">
                    <div class="newtel">
                        <label class="twoform-label">邮箱</label>
                        <div class="twoform-input">
                            <input type="text" id="email" autocomplete="off" placeholder="请输入邮箱地址">
                        </div>
                    </div>
                    <div class="validatecode">
                        <label class="twoform-label2">验证码</label>
                        <div class="twoform-input2">
                            <input type="text" autocomplete="off" id="rndNum" placeholder="请输入验证码">

                        </div>
                        <button class="sendcode" id="sendRndnum">发送验证码</button>
                        <div class="sendtimer">
                            &nbsp;&nbsp;&nbsp;(<span id="timer1"></span>)s后可重新获取
                        </div>
                    </div>
                </div>
            </div>
            <div class="twobtn-box">
                <button class="twobtn" id="twobtn" onclick="verifyCode()">下一步</button>
            </div>
        </div>
        <!--第三步-->
        <div class="threebox-form twobox-form" id="threeform">
            <div class="threeform">
                <div class="threeform-box">
                    <div class="newtel">
                        <label class="threeform-label">新密码</label>
                        <div class="threeform-input3">
                            <input type="password" id="pwd" autocomplete="off" placeholder="请输入密码">
                        </div>
                    </div>
                    <div class="validatecode">
                        <label class="threeform-label3">确认密码</label>
                        <div class="threeform-input3">
                            <input type="password" autocomplete="off" id="repwd" placeholder="请确认输入密码">
                        </div>
                    </div>
                </div>
            </div>
            <div class="threebtn-box">
                <button class="threebtn" id="threebtn" onclick="changePassword()">下一步</button>
            </div>
        </div>
        <!--第四步-->
        <div class="fourbox-form" id="fourform">
            <div class="successr">
                <div class="symbol">

                </div>
                <p>恭喜您，修改密码成功！</p>
            </div>
        </div>
    </div>
</div>
<script src="/static/js/admin/index/find-account.js"></script>
<script>
    var onebtns = document.getElementById("onebtn");
    var twobtns = document.getElementById("twobtn");
    var soutside1ab = document.getElementById("outside1abs");
    var soutside2as = document.getElementById("outside2as");
    var soutside3as = document.getElementById("outside3as");
    var oneforms = document.getElementById("oneform");
    var twoforms = document.getElementById("twoform");
    var threeforms = document.getElementById("threeform");
    var fourforms = document.getElementById("fourform");

    var timer1 = 60;
    //倒计时
    function TimeDown() {
        $("#timer1").html(timer1);
        if (timer1 > 0) {
            setTimeout(function () { TimeDown(); }, 1000);
        } else if (timer1 <= 0) {
            timer1 = 61;
            $("#sendRndnum").css("display", "block");
            $(".sendtimer").css("display", "none");
        }
        --timer1;
    };


    $("#sendRndnum").click(function () {
        var userName = $("#userName").val();
        var email = $("#email").val();

        if (email == "" || email == null) {
            alert("未绑定邮箱");
            return false;
        }
        $.ajax({
            type: "POST",
            url: "/admin/send-email",
            data: {
                account:userName,
                email:email,
                type:1
            },
            success: function(data){
                if (data.success!=undefined&&!data.success){
                    layer.msg(data.msg);
                    return;
                }
                if (data.isSuccess){
                    $("#sendRndnum").css("display", "none");
                    $(".sendtimer").css("display", "block");
                    TimeDown();
                    $("#userId").val(data.userId);
                }
            },
            error:function (data){
                console.log(data);
            }
        });
    });



    ///验证账号
    function existAccount() {
        var userName = $("#userName").val();
        if (userName == "") {
            layer.msg("请输入要找回密码的账户");
            return false;
        }
        $.ajax({
            type: "POST",
            url: "/admin/exist-account",
            data: {
                account:userName
            },
            success: function(data){
                if (data.success!=undefined&&!data.success){
                    layer.msg(data.msg);
                }
                if (data.isExist){
                    soutside1ab.classList.remove("outside1ab");
                    oneforms.style.display = "none";
                    twoforms.style.display = "block";
                }else {
                    layer.msg('当前账号不存在');
                }
            },
            error:function (data){
                console.log(data);
            }
        });

    }
    //验证邮箱验证码
    function verifyCode() {
        var rndNum = $("#rndNum").val();
        if (rndNum == "") {
            alert("请输入验证码");
            return false;
        }
        $.ajax({
            type: "POST",
            url: "/admin/send-email",
            data: {
                type:2,
                code:rndNum
            },
            success: function(data){
                if (data.success!=undefined&&!data.success){
                    layer.msg(data.msg);
                    return;
                }
                if (data.isCorrect){
                    threeforms.style.display = "block";
                    twoforms.style.display = "none";
                    soutside2as.classList.remove("outside2ab");
                }else {
                    layer.msg("验证码错误")
                }
            },
            error:function (data){
                console.log(data);
            }
        });


    }
    //修改密码
    function changePassword() {
        var pwd= $("#pwd").val();
        var repwd = $("#repwd").val();

        if (pwd != repwd) {
            alert("2次密码不一致", { icon: 5 });
            return false;
        }
        $.ajax({
            type: "POST",
            url: "/admin/change-password",
            data: {
                id:$("#userId").val(),
                password:$("#pwd").val(),
                againPassword:$("#repwd").val()
            },
            success: function(data){
                if (data.success!=undefined&&!data.success){
                    layer.msg(data.msg);
                }
                if (data.isSuccess){
                    fourforms.style.display = "block";
                    threeforms.style.display = "none";
                    soutside3as.classList.remove("outside3a");
                    setTimeout('document.location.href = "/admin/login";',1500);
                }else {
                    layer.msg('内部错误');
                }
            },
            error:function (data){
                console.log(data);
            }
        });

    }
</script>
</body>
</html>
