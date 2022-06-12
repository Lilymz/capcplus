$(function (){
    loadData();
})
function search(){
    params.serverName = $("#name").val();
    params.serverType = $("#type").val();
    loadData();
}
var params = {
    'serverName':$("#name").val(),
    'serverType':$("#type").val()
}
var dataTable;
function loadData(){
    dataTable = $("#server-table").dataTable({
        // 'lengthChange':false,
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
            'url':'/capcplus/server/server-list',
            // 'type':'POST',
            'data':params,
            'dataSrc':'data',
        },
        'columns':[
            {'data':'id'},
            {'data':'type'},
            {'data':'name'},
            {'data':'ywId',"createdCell":function (td,sData,oData,row,col){
                var contentHtml = ' <div style="display: inline-block;min-width: 500px;max-width: 550px;">'+
                    '<div style="display: inline-block">'+
                       '<span style="text-align: right;min-width: 100px;display: inline-block">业务ID：</span>'+
                    ' <span class="ywId1" style="display: inline-block;position: relative;min-width: 370px;text-align: left">'+
                    '••••••••••••••••••••••••••••••••'+
                    '  </span>'+
                    '   <input type="hidden" class="ywIdFlag1" value="'+oData.ywId+'">'+
                    '  <input type="hidden" class="ywIdIsOpen" value="0">&nbsp;'+
                    ' <i class="fa fa-eye" aria-hidden="true" id="ywPwd_img1" onclick="getYwId(this);" style="cursor:pointer;width: 20px;height: 20px;z-index: 10000"></i>'+
                    ' </div>'+
                    '  <br>'+
                    '  <div style="display: inline-block">'+
                    '  <span style="text-align: right;min-width: 100px;display: inline-block">业务密码：</span>'+
                    '  <span class="ywPwd1" style="display: inline-block;position: relative;min-width: 370px;text-align: left">'+
                    '  ••••••••••••••••••••••••••••••••'+
                    '  </span>'+
                    '  <input type="hidden" class="ywFlag1" value="'+oData.ywPwd+'">'+
                    '  <input type="hidden" class="ywIsOpen" value="0">&nbsp;'+
                    ' <i class="fa fa-eye" aria-hidden="true" id="ywPwd_img1" onclick="getYwPwd(this);" style="cursor:pointer;width: 20px;height: 20px;z-index: 10000"></i>'+
                    '  </div>'+
                    '  </div>';
                $(td).html(contentHtml);
            }},
            {'data':'status','render':function (data, type, row) {
                 if (data ==='待开启'){
                     return '<font style="color: #FFB800;">待开启</font>';
                 }
                 else if (data ==='已开启'){
                     return '<font style="color: #009966;">已开启</font>';
                 }else if (data === '服务运行中'){
                     return '<font style="color: #31BDEC;">服务运行中</font>';
                 }else if (data ==='服务已暂停'){
                     return '<font style="color: red;">服务已暂停</font>';
                 }
             }},
            {'data':'id',"createdCell":function (td,sData,oData,row,col){
                var operatorHtml ='';
                if (oData.status === '服务运行中'){
                    operatorHtml += '<a id="closeServer" onclick="updateStatus('+oData.id+',\'PAUSE\')" style="margin-left: 10px;" class="btn btn-xs btn-primary"> <i class="fa fa-lock" aria-hidden="true"></i>&nbsp;暂停</a>';
                }else if (oData.status === '服务已暂停'){
                    operatorHtml += '<a id="closeServer" onclick="updateStatus('+oData.id+',\'RUNNING\')" style="margin-left: 10px;" class="btn btn-xs btn-primary"> <i class="fa fa-unlock" aria-hidden="true"></i>&nbsp;恢复 </a>';
                }
                operatorHtml +='<a id="deleteMsg" onclick="resetPassword('+oData.id+')" style="margin-left: 10px;" class="btn btn-xs btn-primary "><i class="fa fa-cog" aria-hidden="true"></i>&nbsp;重置密码 </a>';
                operatorHtml +='<div class="btn-group" role="group" aria-label="...">\n' +
                    '\n' +
                    '  <div class="btn-group" role="group" style="margin-left: 5px">\n' +
                    '    <button id="help" type="button" class="btn btn-xs btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">\n' +
                    '      使用帮助\n' +
                    '      <span class="caret"></span>\n' +
                    '    </button>\n' +
                        '<ul class="dropdown-menu right animated fadeInDown" role="menu" style="position:absolute;top: 30px;right: -23px" id="user-info-setting">' +
                        '<li>' +
                        '<a style="text-align:left;" href="javaScript:void(0)" onclick="helpUse(\'1\',\'一键登录\',\'\',\'oneclick\');"><i class="fa-eye-open">&nbsp;</i>查看API</a>' +
                        '</li>' +
                        '<li>' +
                        '<a style="text-align:left;" href="javaScript:void(0)" onclick="helpUse(\'3\',\'一键登录\',\'http://wwwpre.capcplus.com:34181\',\'oneclick\');"><i class="en-download">&nbsp;</i>下载DEMO</a>' +
                        '</li>' +
                        '</ul>'+
                    '  </div>\n' +
                    '</div>'
                $(td).html(operatorHtml);
            }}
        ]
    });
}
function resetPassword(id){
    layer.confirm('您确定要当前服务密码吗？', {
        btn: ['确认','取消'] //按钮
    }, function(index){
        $.ajax({
            type: "POST",
            url: "/capcplus/server/reset-pwd",
            data: {
                id:id,
            },
            success: function(data){
                if (data.code=='0000'){
                    notify.success("当前密码重置为："+data.pwd);
                    setTimeout('loadData();',1000);
                }else {
                    notify.error(data.msg);
                }
                layer.close(index);
            },
            error:function (data){
                console.log(data);
            }
        });
    }, function(index){
        layer.close(index);
    });
}
function updateStatus(id,status){
    var statusStr = status ==='RUNNING'?'恢复':'暂停';
    layer.confirm('您确定要'+statusStr+'服务吗？', {
        btn: ['确认','取消'] //按钮
    }, function(index){
        $.ajax({
            type: "POST",
            url: "/capcplus/server/close-open-server",
            data: {
                id:id,
                status:status
            },
            success: function(data){
                if (data.code=='0000'){
                    notify.success('已'+statusStr);
                    if (status ===3){
                        $("#closeServer").html('<i class="fa fa-lock" aria-hidden="true"></i>&nbsp;暂停');
                    }else if (status ===2){
                        $("#closeServer").html('<i class="fa fa-unlock" aria-hidden="true"></i>&nbsp;恢复');
                    }
                    loadData();
                }else {
                    notify.error(data.msg);
                }
                layer.close(index);
            },
            error:function (data){
                console.log(data);
            }
        });
    }, function(index){
        layer.close(index);
    });
}
function getYwId(_this){
    //获取当前是否是查看状态
    var $isOpen = $(_this).prev();
    if ($($isOpen).val()==='0'){
        //1.写入当前业务密码
        var pwd = $(_this).prev().prev().val();
        $(_this).parent().find('.ywId1').text(pwd);
        //改变当前样式
        $(_this).css("color",'#1E9FFF');
        $($isOpen).val(1);
    }else {
        $(_this).parent().find('.ywId1').text('••••••••••••••••••••••••••••••••');
        $(_this).css('color',"");
        $($isOpen).val(0);
    }
}
/**
 * 显示业务密码
 * @author zhangjie
 * @date 2022/5/31
 * @return
 */
function getYwPwd(_this){
    //获取当前是否是查看状态
    var $isOpen = $(_this).prev();
    if ($($isOpen).val()==='0'){
        //1.写入当前业务密码
        var pwd = $(_this).prev().prev().val();
        $(_this).parent().find('.ywPwd1').text(pwd);
        //改变当前样式
        $(_this).css("color",'#1E9FFF');
        $($isOpen).val(1);
    }else {
        $(_this).parent().find('.ywPwd1').text('••••••••••••••••••••••••••••••••');
        $(_this).css('color',"");
        $($isOpen).val(0);
    }
}
