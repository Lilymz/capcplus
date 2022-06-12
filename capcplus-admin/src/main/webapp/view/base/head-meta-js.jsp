<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<%--jquery--%>
<script src="/static/js/jquery/jquery.min.js"></script>
<script src="/static/common/ajaxFileUpload.js"></script>
<%--swipper--%>
<script src="/static/Swiper-3.4.2/dist/js/swiper.jquery.min.js"></script>
<script src="/static/Swiper-3.4.2/dist/js/swiper.min.js"></script>
<script src="/static/Swiper-3.4.2/dist/js/swiper.animate1.0.2.min.js"></script>
<script src="/static/Swiper-3.4.2/dist/js/swiper.jquery.umd.min.js"></script>
<%--bootstrap--%>
<script src="/static/bootstrap3.4.1/js/bootstrap.min.js"></script>
<%--layui--%>
<script src="/static/layui-v2.6.9/layui/layui.js"></script>
<%--echarts--%>
<script src="/static/js/eCharts/echarts.min.js"></script>
<%--<link rel="stylesheet" href="/static/fullPage/dist/fullpage.min.js">--%>
<%--layui消息提示plugin--%>
<script src="/static/layui-v2.6.9/plugin/notify/notify.js"></script>
<%--dataTable +bootStrap--%>
<script src="/static/dataTable/js/jquery.dataTable.min.js"></script>
<script src="/static/dataTable/js/dataTable.bootstrap.min.js"></script>
<script src="/static/select/bootstrap-select.js"></script>
<script src="/static/date-select/bootstrap-date-picker.js"></script>
<%--自定义省市区js--%>
<script src="/static/js/address/adress.js"></script>
</body>
<script>
    //统一设置日期
    $.fn.datepicker.defaults.format = "yyyy-mm-dd";
    $.extend($.fn.dataTable.defaults,{
        'dom':'t"<\'row\'<\'col-md-4\'i><\'col-md-4\'l><\'col-md-4\'p>>"',
        language: {
            url: '/static/dataTable/zh.json'
        }
    });
    var laydate;
    //layui日期控件
    //消息通知控件
    var notify ;
    //element控件
    var element;
    layui.use(['notify','element','laydate'],function(){
        laydate = layui.laydate;
        notify=this.notify;
        element = layui.element;
    });
    $('.selectpicker').selectpicker({
        'deselectAllText': '全不选',
        'selectAllText': '全选'
    });
</script>
<%--放在这里是因为要用到消息通知控件--%>
<script src="/static/common/upload-file.js"></script>
</html>
