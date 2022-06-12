<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="../../base/head-meta.jsp"%>
<%@include file="../../base/head-meta-js.jsp"%>
<body>
<table class="table table-striped table-bordered">
    <thead>
        <tr>
            <td colspan="2">1.1 &nbsp;&nbsp;&nbsp;接口描述</td>
        </tr>
    </thead>
    <tbody>
        <%--目前暂时没有数据--%>
        <c:forEach items="${descriptions}" var="description">
            <tr>
                <td class="text-center">${description.name}</td>
                <td class="text-center">${description.content}</td>
            </tr>
        </c:forEach>
        <tr>
            <td class="text-center">中文名称</td>
            <td class="text-center">大象能力-一键登录</td>
        </tr>
        <tr>
            <td class="text-center">接口备注</td>
            <td class="text-center">大象能力-一键登录(置换手机号)</td>
        </tr>
        <tr>
            <td class="text-center">传输协议</td>
            <td class="text-center">https/http</td>
        </tr>
        <tr>
            <td class="text-center">接口地址</td>
            <td class="text-center">https://api.capcplus.com/cverify/getmobile.do</td>
        </tr>
        <tr>
            <td class="text-center">调用方式</td>
            <td class="text-center">POST</td>
        </tr>
        <tr>
            <td class="text-center">Content-Type</td>
            <td class="text-center">application/x-www-form-urlencoded;charset=UTF-8</td>
        </tr>
    </tbody>
</table>
<table class="table table-striped table-bordered">
    <thead>
    <tr>
        <td colspan="5">1.2 &nbsp;&nbsp;&nbsp;请求参数说明</td>
    </tr>
    </thead>
    <tbody>
    <%--目前暂时没有数据--%>
    <c:forEach items="${params}" var="param">
        <tr>
            <td class="text-center">${param.name}</td>
            <td class="text-center">${param.required}</td>
            <td class="text-center">${param.type}</td>
            <td class="text-center">${param.content}</td>
            <td class="text-center">${param.remark}</td>
        </tr>
    </c:forEach>
    <tr>
        <td class="text-center">operType</td>
        <td class="text-center">是</td>
        <td class="text-center">String</td>
        <td class="text-center">运营商类型</td>
        <td class="text-center">cmcc：移动 <br/>cucc：联通<br/>ctcc：电信</td>
    </tr>
    <tr>
        <td class="text-center">ywId</td>
        <td class="text-center">是</td>
        <td class="text-center">String</td>
        <td class="text-center">业务ID</td>
        <td class="text-center">大象提供或平台查询</td>
    </tr>
    <tr>
        <td class="text-center">cusAppId</td>
        <td class="text-center">是</td>
        <td class="text-center">String</td>
        <td class="text-center">应用ID</td>
        <td class="text-center">大象提供或平台查询</td>
    </tr>
    <tr>
        <td class="text-center">token</td>
        <td class="text-center">是</td>
        <td class="text-center">String</td>
        <td class="text-center">待解析的凭证</td>
        <td class="text-center"></td>
    </tr>
    </tbody>
</table>
</body>
</html>
