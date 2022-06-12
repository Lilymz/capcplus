var stepTitle = {
    data: ['填写基本信息', '上传证件照片', '提交成功'],
    index: 0
}
var step =  $('#step');
//从第一个步骤开始
var stepIndex = 0;
$(function(){
    //初始化省市区
    var addressOne =addressInit('provinceId','cityId','areaId','','','');
    //经营期限
    laydate.render({
        elem: '#dueDate', //指定元素
        type:'datetime',
        format:'yyyy-MM-dd HH:mm:ss',
        min: '1970-01-01 00:00:00',
        max: '2099-12-31 23:59:59',
        position:'fixed',
        zIndex:9999
    });

    $("#type").selectpicker({
        width: 120,
        actionsBox:true,
        liveSearch:true,
    });
    $("#name").selectpicker({
        width: 237,
        actionsBox:true,
        liveSearch:true,
    });
    $("#bill").selectpicker({
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
    $('input').iCheck({
        checkboxClass: 'icheckbox_flat-purple',
        radioClass: 'iradio_flat-purple'
    });
    $('input').iCheck({
        checkboxClass: 'icheckbox_flat-purple',
        radioClass: 'iradio_flat-purple'
    });
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
var checkOne = true;
var checkTwo = true;
/*上传*/
function next(index){
    checkStep1();
    if (index === 1&&checkOne){
        $('.step1').hide();
        $('.step2').show();
        $('.step3').hide();
        step.toStep(index)
        return
    }
    checkStep2();
    if (index === 2&&checkTwo){
        $('.step1').hide();
        $('.step2').hide();
        $('.step3').show();
        step.toStep(index)
        return;
    }
    else if (index === 3){
        $('.step1').hide();
        $('.step2').hide();
        $('.step3').show();
    }
}
function checkStep1(){
    $.ajax({
        type: "POST",
        url: "/capcplus/check-step-one",
        data: $("#private-business").serialize(),
        async:false,
        success: function(data){
            if (data.code==0001){
                notify.error(data.msg);
                checkOne = false;
                return
            }
            checkOne = true;
        },
        error:function (data){
            console.log(data);
            return false;
        }
    });
}
function checkStep2(){
    $.ajax({
        type: "POST",
        url: "/capcplus/check-step-two",
        data: $("#private-business").serialize()+"&"+$("#step2-form").serialize(),
        async:false,
        success: function(data){
            if (data.code==0000){
                notify.success("绑定企业信息成功,请耐心等待审核")
                checkTwo = true;
                return
            }
            checkTwo = false;
            notify.error(data.msg);
        },
        error:function (data){
            console.log(data);
            return false;
        }
    });
}
/*用户协议窗*/
function    registerProtocol(_this){
    layer.open({
        type:1,
        title:'大象平台注册协议',
        content:'在使用我们的服务之前，请仔细阅读这些条款和条件。应用程序是指您在任何电子设备上下载的由公司提供的软件程序。\n' +
            '\n' +
            '设备是指可以访问服务的任何设备，例如计算机、手机或数字平板电脑。\n' +
            '\n' +
            '服务是指应用程序。\n' +
            '\n' +
            '条款和条件（也称为“条款”）是指构成您与公司之间关于使用服务的完整协议的这些条款和条件。本条款和条件协议是在条款和条件模板的帮助下创建的。\n' +
            '\n' +
            '第三方社交媒体服务是指由第三方提供的任何服务或内容（包括数据、信息、产品或服务），这些服务或内容可能由服务显示、包含或提供。\n' +
            '\n' +
            '您是指访问或使用服务的个人，或代表该个人访问或使用服务的公司或其他法律实体（如适用）。\n' +
            '\n' +
            '致谢\n' +
            '这些是管理本服务使用的条款和条件以及您与公司之间的协议。这些条款和条件规定了所有用户在使用服务方面的权利和义务。\n' +
            '\n' +
            '您访问和使用服务的前提是您接受并遵守这些条款和条件。这些条款和条件适用于所有访问或使用服务的访问者、用户和其他人。\n' +
            '\n' +
            '通过访问或使用服务，您同意受这些条款和条件的约束。如果您不同意这些条款和条件的任何部分，则您可能无法访问该服务。\n' +
            '\n' +
            '您声明您已年满 18 周岁。本公司不允许未满 18 周岁的人士使用本服务。\n' +
            '\n' +
            '您对服务的访问和使用还取决于您接受并遵守公司的隐私政策。我们的隐私政策描述了我们在您使用应用程序或网站时收集、使用和披露您的个人信息的政策和程序，并告诉您您的隐私权以及法律如何保护您。在使用我们的服务之前，请仔细阅读我们的隐私政策。\n',
        area:['1115px','739px'],
        btn:['已知悉','取消'],
        yes: function(index, layero){
            layer.close(index);
        }
        ,btn2: function(index, layero){
            layer.close(index);
        },
        shade:0.6,
        anim: 5
    })
}
