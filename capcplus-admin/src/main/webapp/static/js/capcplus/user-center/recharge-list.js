$(function(){
    $("#recharge-table").DataTable({
        /*自适应宽度*/
        autoWidth:true,
        /*datatable壳渲染dom*/
        dom:"tpl",
        /*开启则dom搜索框在最左下*/
        info:false,
        /*是否可排序*/
        ordering:true,
        /*本地分页*/
        // paging:true,
        "pagingType": "full_numbers",
        /*开启加载提示*/
        processing: true,
        /*是否允许用户改变表格记录*/
        lengthChange: false,
        /*延迟加载（当数据源是ajax方式时可用）*/
        deferRender:true,
        /*是否可本地搜索*/
        searching:false,
        /*状态保存，页码，条数，条件等（当刷新页面，table id未变化）*/
        stateSave: true,
        stateSaveCallback: function(settings,data) {
            localStorage.setItem( 'DataTables_' + settings.sInstance, JSON.stringify(data) )
        },
        stateLoadCallback: function(settings) {
            return JSON.parse( localStorage.getItem( 'DataTables_' + settings.sInstance ) )
        },
        'serverSide':true,
        "ajax":{
            'url':'/capcplus/recharge-list',
             "type":'POST',
            // 'data':params,
        },
        "columns": [
            {'data':'id'},
            {'data':'name'},
            {'data':'account'},
            {'data':'password'},
            {'data':'balance'},
            {'data':'balanceWarn'},
            {'data':'headPic'},
            {'data':'type'},
            {'data':'status'},
            {'data':'ip'},
        ]
    });
    //在分页器中添加返回按钮
    $("#recharge-table_paginate").append('' +
        '<a href="/capcplus/go/index" class="btn btn-primary" style="float:right">\n' +
        '<i class="en-reply-all"></i> 返回\n' +
        '</a>');
})