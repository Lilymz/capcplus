function updateTicket(){
    $.ajax({
        type: "POST",
        url: "/capcplus/ticket/update-ticket",
        data: $("#update-ticket").serialize(),
        success: function(data){
            if (data.code===0001){
                notify.error(data.msg);
                return;
            }
            notify.success("已提交,请耐心等待审核！")
            setTimeout('document.location.href="/capcplus/ticket/go/ticket-info"',500);
        },
        error:function (data){
            console.log(data);
        }
    });
}
function refresh(){
    document.location.href="/capcplus/ticket/go/ticket-info";
}