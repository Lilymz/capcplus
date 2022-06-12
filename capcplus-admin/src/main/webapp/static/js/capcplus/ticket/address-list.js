$(function(){
    loadData();
});
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
            {'data':'id',render:function (data,type,oData) {
                var operatorHtml = '';
                operatorHtml +='<button type="button" data-id="'+oData.id+'" data-name="'+oData.name+'" data-provinceStr="'+oData.provinceStr+'" data-cityStr="'+oData.cityStr+'" ' +
                    'data-areaStr="'+oData.areaStr+'" data-detailAddress="'+oData.detailAddress+'" data-postCode="'+oData.postCode+'" data-phone="'+oData.phone+'" data-telephone="'+oData.telephone+'" ' +
                    'data-isDefault="'+oData.isDefault+'" class="layui-btn layui-btn-sm layui-btn-radius layui-btn-normal" onclick="modifyAddress(this)">修改</button>';
                    operatorHtml +='<button type="button" class="layui-btn layui-btn-sm layui-btn-radius layui-btn-danger" onclick="delAddress('+oData.id+')">删除</button>'
                return operatorHtml;
            }},
        ]
    });
}
function delAddress(id){
    layer.confirm('确定删除吗？', {
        btn: ['确定','取消'] //按钮
    }, function(index){
        $.ajax({
            type: "POST",
            url: "/capcplus/ticket/del-address",
            data: {
                id:id
            },
            success: function(data){
                if (data.code===0001){
                    notify.error(data.msg);
                    return;
                }
                notify.success("删除成功！")
                layer.close(index);
                setTimeout('loadData();',500);
            },
            error:function (data){
                console.log(data);
            }
        });
    }, function(){
    });
}
function modifyAddress(_this){
    var id = $(_this).attr("data-id");
    var name = $(_this).attr("data-name");
    var provinceStr = $(_this).attr("data-provinceStr");
    var cityStr = $(_this).attr("data-cityStr");
    var areaStr = $(_this).attr("data-areaStr");
    var detailAddress = $(_this).attr("data-detailAddress");
    var postCode = $(_this).attr("data-postCode");
    var phone = $(_this).attr("data-phone");
    var telephone = $(_this).attr("data-telephone");
    var isDefault = $(_this).attr("data-isDefault");
    initData(id,name,provinceStr,cityStr,areaStr,detailAddress,postCode,phone,telephone,isDefault);
    layer.open({
        type:1,
        title:'<i class="layui-icon layui-icon-edit" aria-hidden="true"></i>修改地址',
        content:$("#modify-address"),
        area:['600px','506px'],
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
function initData(id,name,provinceStr,cityStr,areaStr,detailAddress,postCode,phone,telephone,isDefault){
    $("#modify-id").val(id);
    $("#modify-name").val(name);
    addressInit('modify-provinceId','modify-cityId','modify-areaId',provinceStr,cityStr,areaStr);
    $("#modify-detailAddress").val(detailAddress);
    $("#modify-postCode").val(postCode);
    $("#modify-phone").val(phone);
    $("#modify-telephone").val(telephone);
    if (isDefault === '不默认'){
        $(".modify-noDefault").iCheck('destroy');
        $(".modify-isDefault").iCheck('check');
    }else {
        $(".modify-isDefault").iCheck('check');
        $(".modify-noDefault").iCheck('destroy');
    }
}
function modifyForm(index){
    $.ajax({
        type: "POST",
        url: "/capcplus/ticket/operator-address",
        data: $("#form-modify-address").serialize(),
        success: function(data){
            if (data.code===0001){
                notify.error(data.msg);
                return;
            }
            notify.success("提交成功！")
            layer.close(index);
            setTimeout('loadData();',500);
        },
        error:function (data){
            console.log(data);
        }
    });
}
function addAddress(){
    layer.open({
        type:1,
        title:'<i class="layui-icon layui-icon-edit" aria-hidden="true"></i>新增地址',
        content:$("#add-address"),
        area:['600px','476px'],
        btn:["新增","取消"],
        btnAlign: 'c',
        shade:0.4,
        anim: 2,
        closeBtn:0,
        skin:'layui-layer-lan',
        resize:false,
        yes:function (index,layero){
            addForm(index);
        },
        btn2: function(index, layero){
            layer.close(index);
            clearForm();
        }
    });
}
function addForm(index){
    $.ajax({
        type: "POST",
        url: "/capcplus/ticket/operator-address",
        data: $("#form-add-address").serialize(),
        success: function(data){
            if (data.code===0001){
                notify.error(data.msg);
                return;
            }
            notify.success("提交成功！")
            layer.close(index);
            setTimeout('loadData();',500);
        },
        error:function (data){
            console.log(data);
        }
    });
}
function clearForm(){
    $("#name").val('');
    $("#provinceId").val('');
    $("#cityId").val('');
    $("#areaId").val('');
    $("#detailAddress").val('');
    $("#postCode").val('');
    $("#phone").val('');
    $("#telephone").val('');
}