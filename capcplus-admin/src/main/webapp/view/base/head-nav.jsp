<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="nav nav-pills navbar-fixed-top">
    <div class="container-fluid">
        <div class="collapse navbar-collapse" id="nav-menu">
            <ul class="nav navbar-nav">
                <a class="navbar-brand" href="/">
                    <img alt="logo" src="/resources/assets/home/others/logo1.png" style="position:relative;width: 165px;padding: 0;margin: 0">
                </a>
                <li role="presentation" class="active"><a href="#"><i class="glyphicon glyphicon-arrow-left"></i></a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a href="/capcplus/message/go/list">
                      <i class="fa fa-bell"><span class="badge message" style="background-color: red;" id="message-count"></span></i>
                    </a>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                        <c:choose>
                            <c:when test="${user.headPic!=null&&user.headPic!=''}">
                                <img src="${user.headPic}" alt="headPic" style="width: 35px;height: 35px">
                            </c:when>
                            <c:otherwise>
                                <i class="glyphicon glyphicon-user" style="font-size: 25px;bottom: 5px;position:relative;"></i>
                            </c:otherwise>
                        </c:choose>
                        <small style="position: relative;bottom: 10px;color: white">尊敬的${user.account}，你好</small>
                        <span class="caret" style="position: relative;bottom: 10px;"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="#">用户资料</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="#">用户手册</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="#">退出登录</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
<script>
    $.ajax({
        url:'/capcplus/message/message-unread-count',
        type:'GET',
        success:function (data){
            if (data.code===0001){
                notify.error(data.msg);
                return;
            }
            $("#message-count").text(data.count===0?'':data.count);
        },
        error:function (data) {
            console.log(data);
        }
    });
</script>