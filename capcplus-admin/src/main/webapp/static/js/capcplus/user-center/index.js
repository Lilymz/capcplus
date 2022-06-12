$(function (){
})
/*预警余额调整*/
function setWarnBalance(){
    layer.open({
        type: 1,
        title: "预警调整",
        content: $('#modify-warning'),
        btn: ['修改', '取消'],
        area: ['523px', '350px'], //宽高
        skin: 'layui-layer-blue',
        closeBtn: 2,
        yes: function (index, layero) {
            submitBalanceWarn();
            layer.close(index)
        },
        btn2: function (index, layero) { //驳回按钮
        }
    });
}
//企业资质申请
function goQualification(){
    document.location.href= "/capcplus/go/qulification-apply";
}
function goServer(){
    document.location.href= "/capcplus/server/go/list";
}
/**
 * 修改预警金额
 */
function submitBalanceWarn(){
    $.ajax({
        type: "POST",
        url: "/capcplus/balance-warning",
        data: {
            balanceWarning:$("#warn-balance").val()
        },
        success: function(data){
            if (data.code=='0001'){
                notify.error(data.msg)
                return;
            }
            notify.success("设置成功！")
        },
        error:function (data){
            console.log(data);
        }
    });
}