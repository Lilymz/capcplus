$(function (){
    $("#serverType").selectpicker({
        width: 120,
        actionsBox:true,
        liveSearch:true,
    });
    $("#serverName").selectpicker({
        width: 237,
        actionsBox:true,
        liveSearch:true,
    });
    //账单日期
    $('.input-group.date').datepicker();
    loadData()
})
function search(){
    params.serverType =$("#serverType").val();
    params.serverName =$("#serverName").val();
    params.billDate =$("#billDate").val();
    loadData();
}
var params = {
    'serverType':$("#serverType").val(),
    'serverName':$("#serverName").val(),
    'billDate':$("#billDate").val(),
}
var dataTable;
function loadData(){
    dataTable = $("#statistics-table").dataTable({
        "pagingType": "full_numbers",
        "order" : [],
        'autoWidth':true,
        ordering:false,
        processing:true,
        serverSide:true,
        'deferRender':true,
        "destroy": true,
        "pageLength": 10,
        "oLanguage": {  //语言转换
            "sInfo": "显示第 _START_ 到第 _END_ 条记录，总共_TOTAL_ 条记录， ",
            "sLengthMenu": "每页显示 _MENU_ 条记录",
            "sZeroRecords": "暂无数据",
        },
        'ajax':{
            'url':'/capcplus/statistics/statistics-list',
            'data':params,
            'dataSrc':'data',
        },
        'columns':[
            {'data':'id'},
            {'data':'serverName'},
            {'data':'useTotal'},
            {'data':'successTotal'},
            {'data':'unknownTotal'},
            {'data':'failTotal'},
            {'data':'costTotal'},
            {'data':'devCostTotal'},
            {'data':'backTotal'},
            {'data':'realTotal'},
            {'data':'settleTotal'},
            {'data':'settleBackTotal'},
            {'data':'id',"render":function (data,type,oData) {
                return "--";
            }}
        ]
    });
}

/**
 * 服务类型改变控制
 * @param _this
 */
function serverChange(_this){
    $.ajax({
        type: "POST",
        url: "/capcplus/statistics/get-server-name",
        data: {
            typeEnum:$(_this).val()
        },
        success: function(data){
            if (data.code===0001){
                notify.error(data.msg);
                return;
            }
            var optionHtml ='';
            //渲染服务名称
            data.serverNames.forEach(function (item){
                optionHtml +='<option value="'+item+'">'+item+'</option>';
            });
            //写入
            $("#serverName").html(optionHtml);
            //刷新select
            $("#serverName").selectpicker('refresh');
        },
        error:function (data){
            console.log(data);
        }
    });
}