$(function(){
    $('input[placeholder]').placeholder();
    $("#password").complexify({}, function(valid, complexity){
        if (!valid) {
            $('#complexity').animate({'width':complexity + '%'}).removeClass('valid').addClass('invalid');
        } else {
            $('#complexity').animate({'width':complexity + '%'}).removeClass('invalid').addClass('valid');
        }
        $('#complexity').html(Math.round(complexity) + '%');
    });
});
function register(){
    $.ajax({
        type: "POST",
        url: "/admin/post-register",
        data: $("#register").serialize(),
        success: function(data){
            if (!data.success){
                layer.msg(data.msg);
                return;
            }
            layer.msg("注册成功");
            setTimeout(function (){
                document.location.href = data.url;
            },700);
        },
        error:function (data){
            console.log(data);
        }
    });
}