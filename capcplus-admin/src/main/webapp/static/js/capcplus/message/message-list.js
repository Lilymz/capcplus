$(function (){
    $('input').iCheck({
        checkboxClass: 'icheckbox_minimal-blue',
        radioClass: 'iradio_minimal-blue'
    });
    loadData();
})
$(window.document).on('ifChanged',"#all_checked",function (){
    var checkStatus = $(this).is(":checked");
    allSelected(checkStatus);
});

/**
 * 全选和反选
 * @param checkStatus 选中状态
 */
function allSelected(checkStatus){
    // messageItem
    var $items = $('.messageItem');
    if (checkStatus){
        for (var i = 0; i < $items.length; i++) {
            if (!$($items[i]).is(":checked")){
                $($items[i]).iCheck('check');
            }
        }
    }else {
        for (var i = 0; i < $items.length; i++) {
            if ($($items[i]).is(":checked")){
                $($items[i]).iCheck('uncheck');
            }
        }
    }
}
function search(){
    params.type = $("#type").val();
    loadData();
}
var params = {
    'type':$("#type").val()
}
var dataTable;
function loadData(){
    dataTable = $("#message-table").dataTable({
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
            'url':'/capcplus/message/message-list',
            'data':params,
            'dataSrc':'data',
        },
        'columns':[
            {'data':'id','render':function (data, type, oData) {
                    var isRead = (oData.status==="已读")?'1':'0';
                    //拼接对应按钮
                    var checkBoxHtml = '<input type="checkbox" class="messageItem" id="message_check'+data+'" value="'+data+'" data-read="'+isRead+'" data-del="'+oData.isDel+'">';
                    $('.messageItem').iCheck({
                        checkboxClass: 'icheckbox_minimal-blue',
                        radioClass: 'iradio_minimal-blue'
                    });
                    return checkBoxHtml;
                }},
            {'data':'id'},
            {'data':'title'},
            {'data':'type'},
            {'data':'content','render':function (data, type, oData) {
                    var operatorHtml = '';
                    operatorHtml +='<a onclick="showContent('+oData.id+')" style="color: #75b9e6; cursor: pointer">'+data+'</a>';
                    return operatorHtml;
            }},
            {'data':'createTime'},
            {'data':'id','render':function (data, type, oData) {
                    var operatorHtml = '';
                    if (oData.status==='已读'){
                        operatorHtml +='<a style="color: white;text-decoration: none ;margin-right: 6px;cursor: text" class="layui-btn layui-btn-sm layui-btn-radius layui-btn-warm"><i class="layui-icon layui-icon-ok"></i>已读</a>';
                    }else {
                        operatorHtml +='<button type="button" class="layui-btn layui-btn-sm layui-btn-radius layui-btn-normal" onclick="batchRead(['+data+'])"><i class="layui-icon layui-icon-close "></i>已读</button>';
                    }
                    operatorHtml +='<button type="button" class="layui-btn layui-btn-sm layui-btn-radius layui-btn-danger" onclick="batchDel(['+data+'])"><i class="layui-icon layui-icon-delete "></i>删除</button>';
                    return operatorHtml;
            }},
        ]
    });
}
function showContent(id){
    $.ajax({
        type: "POST",
        url: "/capcplus/message/message-content",
        data: {
            id:id
        },
        success: function(data){
            if (data.code===0001){
                notify.error(data.msg);
                return;
            }
            attach(data);
            layer.open({
                type:1,
                title:'<i class="layui-icon layui-icon-speaker" aria-hidden="true"></i><b style="color: #768399;">消息详情</b>',
                content:$("#show-content"),
                area:['952px','400px'],
                btnAlign: 'c',
                shade:0.4,
                anim: 2,
                closeBtn:1,
                shadeClose:true,
                resize:false,
            });
        },
        error:function (data){
            console.log(data);
        }
    });
}
function attach(data){
    var message = data.message;
    var user =data.user;
    $("#receiveUser").text(user.name);
    $("#title").text(message.title);
    $("#sendTime").text(message.createTime);
    $("#content").text(message.content);
}
function isChecked(msg,type){
    //获取当前选中的项目
    var $items;
    if (type ===0){
        $items  = $('.messageItem[data-read=0]');
    }else if (type ===1){
        $items = $('.messageItem[data-del=0]');
    }
    var messageIds = [];
    for (var i = 0; i < $items.length; i++) {
        if ($($items[i]).is(":checked")){
            messageIds.push($($items[i]).val());
        }
    }
    if (messageIds.length<=0){
        notify.warning(msg)
        return null;
    }
    return messageIds;
}
function doBatchRead(){
   var messageIds = isChecked("目前尚未勾选任何一项！",0);
   if (messageIds){
       batchRead(messageIds);
   }
}
function doBatchDel(){
    var messageIds = isChecked("目前尚未勾选任何一项！",1);
    if (messageIds){
        batchDel(messageIds);
    }
}
/**
 * 批量删除
 * @param data
 */
function batchDel(data){
    $.ajax({
        type: "POST",
        url: "/capcplus/message/batch-del",
        data: {
            id:data.join(",")
        },
        success: function(data){
            if (data.code===0001){
                notify.error(data.msg);
                return;
            }
            notify.success('删除成功！');
            setTimeout('loadData();',500);
        },
        error:function (data){
            console.log(data);
        }
    });
}
/**
 * 批量已读
 * @param data
 */
function batchRead(data){
    $.ajax({
        type: "POST",
        url: "/capcplus/message/batch-read",
        data: {
            id:data.join(",")
        },
        success: function(data){
            if (data.code===0001){
                notify.error(data.msg);
                return;
            }
            notify.success('操作成功!');
            setTimeout('loadData();',500);
        },
        error:function (data){
            console.log(data);
        }
    });
}
