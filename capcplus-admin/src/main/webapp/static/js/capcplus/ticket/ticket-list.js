$(function (){
    loadData();
});
function search(){
    params.ticketMan =$("#ticketMan").val();
    params.creditCode =$("#creditCode").val();
    params.ticketProject =$("#ticketProject").val();
    params.ticketType =$("#ticketType").val();
    params.status =$("#status").val();
    loadData();
}
function fresh(){
    document.location.href = '/capcplus/ticket/go/ticket-list';
}
var params = {
    'ticketMan':$("#ticketMan").val(),
    'creditCode':$("#creditCode").val(),
    'ticketProject':$("#ticketProject").val(),
    'ticketType':$("#ticketType").val(),
    'status':$("#status").val(),
}
var dataTable;
function loadData(){
    dataTable = $("#ticket-table").dataTable({
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
            'url':'/capcplus/ticket/ticket-list',
            'type':'post',
            'data':params,
            'dataSrc':'data',
        },
        'columns':[
            {'data':'id'},
            {'data':'ticketMan'},
            {'data':'creditCode'},
            {'data':'ticketType'},
            {'data':'ticketProject'},
            {'data':'ticketBalance'},
            {'data':'applyTime'},
            {'data':'status'},
            {'data':'remark',render:function (data,type,oData) {
                return data ===''?"--":data;
            }},
            {'data':'id',render:function (data,type,oData) {
                    var operatorHtml = '';
                    operatorHtml +='<button type="button" class="layui-btn layui-btn-sm layui-btn-radius layui-btn-normal" onclick="detail('+data+',\''+oData.status+'\')">详情</button>';
                    return operatorHtml;
            }},
        ]
    });
}

/**
 * 数据详情
 * @param _this 当前dom
 */
function detail(id,status){
    //相关细节
    $.ajax({
        type: "POST",
        url: "/capcplus/ticket/ticket-detail",
        data: {
            id:id
        },
        success: function(data){
            if (data.code===0001){
                notify.error(data.msg);
                return;
            }
            attach(data);
            // 窗口
            layer.open({
                type:1,
                title:'<i class="layui-icon layui-icon-edit" aria-hidden="true"><b style="color: #768399;">应用配置</b></i>',
                content:$("#ticket-info-detail"),
                area:['880px','606px'],
                btn:[status==='待审核'?'撤销':'确定'],
                btnAlign: 'c',
                shade:0.4,
                anim: 2,
                closeBtn:1,
                resize:false,
                yes:function (index,layero){
                    if (status!=='待审核'){
                        layer.close(index);
                        return
                    }
                    cancel(id,index);
                }
            });
        },
        error:function (data){
            console.log(data);
        }
    });
}
function cancel(id,index){
    $.ajax({
        type: "POST",
        url: "/capcplus/ticket/ticket-cancel",
        data: {
            id:id
        },
        success: function(data){
            if (data.code===0001){
                notify.error(data.msg);
                return;
            }
            notify.success('撤销成功');
            layer.close(index);
            setTimeout('loadData();',500);
        },
        error:function (data){
            console.log(data);
        }
    });
}
function attach(data){
    // 开票信息
    var ticket = data.ticket;
    $("#invoiceTitle").text(ticket.ticketMan);
    $("#invoiceBank").text(ticket.bank);
    $("#orgMobile").text(ticket.businessPhone);
    $("#bankCardNumber").text(ticket.bankAccount);
    $("#orgAddress").text(ticket.businessAddress);
    $("#taxType").text(ticket.type);
    $("#taxRegisterNumber").text(ticket.creditCode);
    // 发票内容
    var ticketInfo = data.ticketInfo;
    $("#invoiceAmount").text(ticketInfo.ticketBalance);
    $("#invoiceType").text(ticketInfo.ticketType);
    $("#invoiceCategory").text(ticketInfo.ticketProject);
    $("#stampType").text(ticketInfo.sealType);
    $("#needBill").text(ticketInfo.isPaper);
    $("#insertTime").text(ticketInfo.applyTime);
    // 收票地址
    var address =data.ticketAddress;
    $("#receiver").text(address.name);
    $("#receivedAddress").text(address.detailAddress);
    $("#receivedPhone").text(address.phone);
    var bills = data.bills;
    // 账单
    var billHtml ='';
    bills.forEach(function (item){
        billHtml +=" <tr style=\"line-height:35px;border:1px solid #ececec;\">\n" +
            "                        <td style=\"border:1px solid #ececec;\">"+item.id+"</td>\n" +
            "                        <td style=\"border:1px solid #ececec;\">"+item.submitTime+"</td>\n" +
            "                        <td style=\"border:1px solid #ececec;\">"+item.balance+"</td>\n" +
            "                        <td style=\"border:1px solid #ececec;\">充值类</td>\n" +
            "                    </tr>";
    });
    $("#invoiceApplyBills").html(billHtml);
    var statusHtml='<span style="color: #cc9900">'+ticketInfo.status+'</span>';
    if (ticketInfo.status==='审核通过'){
        statusHtml ='<span style="color: #5FB878">审核成功</span>'
    }
    $("#auditStatus").html(statusHtml);

}