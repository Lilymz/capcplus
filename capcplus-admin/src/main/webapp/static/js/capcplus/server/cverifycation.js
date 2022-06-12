$(function (){
    loadData();
    $('input').iCheck({
        checkboxClass: 'icheckbox_flat-blue',
        radioClass: 'iradio_flat-blue'
    });
    $("#systemYwIds").selectpicker({
        width: 237,
        actionsBox:true,
        liveSearch:true,
    });
    $("#modify-systemYwIds").selectpicker({
        width: 237,
        actionsBox:true,
        liveSearch:true,
    });
})
function reset(){
    loadData();
}
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
    dataTable = $("#cverifycation-table").dataTable({
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
            'url':'/capcplus/server/cverification-list',
            // 'type':'POST',
            'data':params,
            'dataSrc':'data',
        },
        'columns':[
            {'data':'id','render':function (data, type, row) {
                //拼接对应按钮
                var checkBoxHtml = '<input type="checkbox" class="cverificationItem" id="cverification_check'+data+'" value="'+data+'">';
                $('.cverificationItem').iCheck({
                    checkboxClass: 'icheckbox_flat-blue',
                    radioClass: 'iradio_flat-blue'
                });
                return checkBoxHtml;
            }},
            {'data':'id'},
            {'data':'appName'},
            {'data':'appId'},
            {'data':'appPlatform'},
            {'data':'systemYwNames','render':function (data, type, row) {
                var functionHtml = ''
                // 应用能力（服务表）
                data.forEach(function (item){
                    functionHtml +=item+'<br>';
                });
                return functionHtml;
            }},
            {'data':'auditStatus','render':function (data, type, row) {
                if (data ==='未提交'){
                    return '<font color="#FFB800" style="background-color: white;">'+data+'</font>';
                }
                else if (data ==='待审核'){
                    return '<font color="#8a2be2">'+data+'</font>';
                }
                else if (data ==='审核通过'){
                    return '<font color="#556b2f">'+data+'</font>';
                }
                else if (data ==='审核拒绝'){
                    return '<font color="red">'+data+'</font>';
                }
            }},
            {'data':'id',"createdCell":function (td,sData,oData,row,col){
               // 修改
               var operatorHtml ='<a id="modify" onclick="updateData($(this))" style="margin-left: 10px;" class="btn btn-xs btn-primary"' +
                   ' data-id="'+oData.id+'" data-systemYwIds="'+oData.systemYwIds+'" data-appName="'+oData.appName+'" data-appLogoUrl="'+oData.appLogoUrl+'" data-appDescription="'+oData.appDescription+'"' +
                   ' data-appPlatform="'+oData.appPlatform+'" data-appSign="'+oData.appSign+'" data-appPackage="'+oData.appPackage+'" data-appLoginClazz="'+oData.appLoginClazz+'" >' +
                                 '修改<i class="layui-icon layui-icon-edit"></i> ' +
                                 '</a>';
               $(td).html(operatorHtml);
            }}
        ]
    });
}
$(window.document).on('ifChanged',"#all_checked",function (){
    var checkStatus = $(this).is(":checked");
    allSelected(checkStatus);
});

/**
 * 全选和反选
 * @param checkStatus 选中状态
 */
function allSelected(checkStatus){
    //获取所有cverificationItem
    var $items = $('.cverificationItem');
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
function add(){
    layer.open({
        type:1,
        title:'<i class="layui-icon layui-icon-edit" aria-hidden="true"></i>应用配置',
        content:$("#add-cverification"),
        area:['600px','746px'],
        btn:["新增","取消"],
        btnAlign: 'c',
        shade:0.4,
        anim: 2,
        closeBtn:0,
        skin:'layui-layer-lan',
        resize:false,
        yes:function (index,layero){
            addForm();
            layer.close(index);
        },
        btn2: function(index, layero){
           debugger
           clearForm();
           layer.close(index);
        }
    })
}
function addForm(){
    $.ajax({
        type: "POST",
        url: "/capcplus/server/add-cverification",
        data: $("#form-add-cverification").serialize(),
        success: function(data){
            if (data.code===0001){
                notify.error(data.msg);
                return;
            }
            notify.success("提交成功！")
            setTimeout('loadData();',500);
        },
        error:function (data){
            console.log(data);
        }
    });
}
/**
 * 清空表单信息
 */
function clearForm(){
    $('#systemYwIds').selectpicker('val',['noneSelectedText'])
    $("#systemYwIds").selectpicker('refresh');
    $("#appName-2").val('');
    $("#appLogoUrl").parent().find('label').remove();
    $("#appLogoUrl").parent().find('#file').remove();
    $("#appLogoUrl").before('<label style="width: 78px;height: 78px;position: absolute"><i class="fa fa-plus-square-o upload-label" aria-hidden="true"></i></label>\n' +
        '<input type="file" name="file" class="upload-input fileUpload" id="file2" onchange="ajaxFileUpload($(this),700)">');
    $("#appLogoUrl").val('');
    $("#appDescription").val('');
    $("#appPlatform-add").val('Android');
    $("#appSign").val('');
    $("#appPackage").val('');
    $("#appLoginClazz").val('');
}

/**
 * 数据删除
 */
function del(){
    //获取当前选中的项目
    var $items = $('.cverificationItem');
    var cverificationIds = [];
    for (var i = 0; i < $items.length; i++) {
        if ($($items[i]).is(":checked")){
            cverificationIds.push($($items[i]).val());
        }
    }
    if (cverificationIds.length<=0){
        notify.warning('请勾选需要删除的应用！')
        return;
    }
    //传入到后台删除
    $.ajax({
        type: "POST",
        url: "/capcplus/server/del-cverification",
        data: {
            appIds:cverificationIds.join(",")
        },
        success: function(data){
            if (data.code===0001){
                notify.error(data.msg);
                return;
            }
            notify.success("删除成功！")
            //重新渲染table
            setTimeout('loadData();',500);
        },
        error:function (data){
            console.log(data);
        }
    });
}
function whiteList(){
    layer.open({
        type:1,
        title:'<i class="layui-icon layui-icon-edit" aria-hidden="true"></i>服务ip白名单配置',
        content:$("#config-white-list"),
        area:['600px','746px'],
        btn:["配置","取消"],
        shade:0.4,
        anim: 2,
        closeBtn:0,
        skin:'layui-layer-lan',
        resize:false,
        yes:function (index,layero){
            configWhiteList(index);
        },
        btn2: function(index, layero){
            clearWhiteForm();
            layer.close(index);
        }
    });
}
/**
 *  白名单form
 * @author zhangjie
 */
function configWhiteList(index){
    $.ajax({
        type: "GET",
        url: "/capcplus/server/white-list",
        data: $("#form-white-list").serialize(),
        success: function(data){
            if (data.code===0001){
                notify.error(data.msg);
                return;
            }
            notify.success("配置成功！")
            layer.close(index);
            setTimeout(function (){
                document.location.href='/capcplus/server/go/cverifycation';
            },500);
        },
        error:function (data){
            console.log(data);
        }
    });
}

/**
 * 清除白名单form
 */
function clearWhiteForm(){
    $("#whiteList").val($("#currentWhiteList").val());
}
/**
 * 修改认证信息
 *
 * @param obj
 * @author zhangjie
 */
function updateData(_this){
    var id = $(_this).attr("data-id");
    var appName = $(_this).attr("data-appName");
    var appLogoUrl = $(_this).attr("data-appLogoUrl");
    var appDescription = $(_this).attr("data-appDescription");
    var appPlatform = $(_this).attr("data-appPlatform");
    var appSign = $(_this).attr("data-appSign");
    var appPackage = $(_this).attr("data-appPackage");
    var appLoginClazz = $(_this).attr("data-appLoginClazz");
    var systemYwIds = $(_this).attr("data-systemYwIds");
    initData(id,systemYwIds,appName,appLogoUrl,appDescription,appPlatform,appSign,appPackage,appLoginClazz);
    layer.open({
        type:1,
        title:'<i class="layui-icon layui-icon-edit" aria-hidden="true"></i>应用配置',
        content:$("#modify-cverification"),
        area:['600px','746px'],
        btn:["修改","取消"],
        btnAlign: 'c',
        shade:0.4,
        anim: 2,
        closeBtn:0,
        skin:'layui-layer-lan',
        resize:false,
        yes:function (index,layero){
            modifyForm(index);
        },
        btn2: function(index, layero){
            layer.close(index);
        }
    });
}
/**
 *  给定数据
 * @param obj 当前应用
 * @author zhangjie
 */
function initData(id,systemYwIds,appName,appLogoUrl,appDescription,appPlatform,appSign,appPackage,appLoginClazz){
    $("#modify-id").val(id);
    //服务分类（未初始化）
    $('#modify-systemYwIds').selectpicker('val', systemYwIds.split(","));
    $('#modify-systemYwIds').selectpicker('refresh');
    $("#modify-appName").val(appName);
    $("#modify-appLogoUrl").val(appLogoUrl);
    $("#modify-appLogoUrl").parent().find('label').remove();
    $("#modify-appLogoUrl").parent().find('#file').remove();
    $("#modify-appLogoUrl").before('<label style="width: 78px; height: 78px; position: absolute; background-image: url(&quot;/resources/images/'+appLogoUrl+'&quot;); background-size: cover;"></label>');
    $("#modify-appDescription").val(appDescription);
    $("#modify-appPlatform").val(appPlatform);
    $("#modify-appSign").val(appSign);
    $("#modify-appPackage").val(appPackage);
    $("#modify-appLoginClazz").val(appLoginClazz);
}
function modifyForm(index){
    $.ajax({
        type: "POST",
        url: "/capcplus/server/modify-cverification",
        data: $("#form-modify-cverification").serialize(),
        success: function(data){
            if (data.code===0001){
                notify.error(data.msg);
                return;
            }
            notify.success("提交成功！")
            setTimeout('loadData();',500);
            layer.close(index);
        },
        error:function (data){
            console.log(data);
        }
    });
}