var stepTitle = {
    data: ['确认开票信息', '选择账单', '选择收票地址'],
    index: 0
}
var step =  $('#step');
//从第一个步骤开始
var stepIndex = 0;
//选中按钮监听
function billChange(_this){
    var prices = 0.00;
    //获取当前账单
    var bills = $(_this).val();
    //便利当前账单
    bills.forEach(function (item){
        var balance = $('#bill-'+item).attr("data-bill");
        if (balance){
            prices += new Number(balance);
        }
    });
    //赋值金额
    $("#ticket-total-balance").text(prices);
}
$(function(){
    $("#ticketType").selectpicker({
        width: 237,
        actionsBox:true,
        liveSearch:true,
    });
    $("#serverIds").selectpicker({
        width: 237,
        actionsBox:true,
        liveSearch:true,
    });
    $("#billIds").selectpicker({
        width: 237,
        actionsBox:true,
        liveSearch:true,
    });
    $("#sealType").selectpicker({
        width: 237,
        actionsBox:true,
        liveSearch:true,
    });
    step.stepInit(stepTitle);
    //修改信息禁掉
    $('.step1 input').attr('disabled','disabled');
    // 加载地址数据、
    loadData();
});
function prev(index){
    if (index === 3){
        $('.step1').hide();
        $('.step2').show();
        $('.step3').hide();
        step.toStep(index-2)
        return
    }
    else if (index === 2){
        $('.step1').show();
        $('.step2').hide();
        $('.step3').hide();
        step.toStep(index-2)
        return;
    }else if (index === 1){
        $('.step1').show();
        $('.step2').hide();
        $('.step3').hide();
    }
}
function next(index){
    if (index === 1){
        $('.step1').hide();
        $('.step2').show();
        $('.step3').hide();
        step.toStep(index)
        return
    }
    else if (index === 2){
        $('.step1').hide();
        $('.step2').hide();
        $('.step3').show();
        step.toStep(index)
        return;
    }else if (index === 3){
        $('.step1').hide();
        $('.step2').hide();
        $('.step3').show();
    }
}

/**
 * 提交所有表单信息
 */
function submitForm(){
    //填充存在数据
    var $address = $(".ticketAddressId");
    for (var i = 0; i < $address.length; i++) {
        if ($($address[i]).is(":checked")){
            $("#ticketAddressId").val($($address[i]).val());
            break;
        }
    }
    $("#ticketProject-input").val($("#ticketProject").text());
    $("#ticketBalance").val($("#ticket-total-balance").text());
    $.ajax({
        type: "POST",
        url: "/capcplus/ticket/ticket-apply",
        data: $("#add-ticket-apply-form").serialize(),
        success: function(data){
            if (data.code===0001){
                notify.error(data.msg);
                return;
            }
            notify.success("提交成功！")
            setTimeout('document.location.href="/capcplus/ticket/go/ticket-apply;"',500);
            layer.close(index);
        },
        error:function (data){
            console.log(data);
        }
    });
}
var params = {
}
var dataTable;
function loadData(){
    dataTable = $("#address-table").dataTable({
        'dom':'tip',
        'lengthChange':false,
        "pagingType": "full_numbers",
        "order" : [],
        'autoWidth':true,
        'ordering':false,
        'processing':true,
        'serverSide':true,
        'deferRender':true,
        "destroy": true,
        "pageLength": 10,
        "oLanguage": {  //语言转换
            "sInfo": "显示第 _START_ 到第 _END_ 条记录，总共_TOTAL_ 条记录， ",
            "sLengthMenu": "每页显示 _MENU_ 条记录",
            "sZeroRecords": "暂无数据",
        },
        'ajax':{
            'url':'/capcplus/ticket/address-list',
            'type':'post',
            'data':params,
            'dataSrc':'data',
        },
        'columns':[
            {'data':'id',render:function (data, type, oData) {
                var radioHtml = '<input type="radio" class="ticketAddressId iradio_flat-blue" name="list-ticketAddressId" value="'+data+'"' ;
                    if (oData.isDefault=='默认'){
                        radioHtml +=' checked ';
                    }
                    radioHtml += '>';
                    return radioHtml;
            }},
            {'data':'id'},
            {'data':'name'},
            {'data':'id',render:function (data,type,oData) {
                    return oData.provinceStr+oData.cityStr+oData.areaStr;
            }},
            {'data':'detailAddress'},
            {'data':'phone'},
            {'data':'telephone'},
            {'data':'postCode'},
            {'data':'isDefault'},
        ]
    });
}