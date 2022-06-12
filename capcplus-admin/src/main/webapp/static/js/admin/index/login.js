$(function () {
    //加载输入框皮肤
    loadCheckBoxSkin();
    //密码提示
    tipPassword();
    //
    changeCode();
})

/**
 * 加载所有输入框样式
 */
function loadCheckBoxSkin(){
    $('input').iCheck({
        checkboxClass: 'icheckbox_flat-blue',
        radioClass: 'iradio_flat-blue'
    });
}

/**
 * 密码提示
 */
function tipPassword(){
    $("#password").togglePassword({
        el: '.tip-password'
    });
}
function login(){
    $.ajax({
        type: "POST",
        url: "/admin/post-login",
        data: $("#login-form").serialize(),
        success: function(data){
            if (data.code=='0001'){
                layer.msg(data.msg);
                //刷新验证码
                $("#codeImg").click();
                return;
            }
            document.location.href='/capcplus/go/index';
        },
        error:function (data){
            console.log(data);
        }
    });
}
function changeCode(){
    $("#codeImg").click(function () {
        var time = new Date().getTime();
        this.src = "/img/code?i=" + time;
        $("#code").val("");
    });
}