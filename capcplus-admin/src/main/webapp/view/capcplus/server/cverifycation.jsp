<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%@include file="../../base/head-meta.jsp"%>
<%@include file="../../base/head-meta-js.jsp"%>
<link rel="stylesheet" href="/static/iCheck/skins/flat/blue.css">
<script src="/static/iCheck/icheck.min.js"></script>
<style>
    td{
        text-align: center;
        height: 60px;
    }
</style>
<body>
<div class="body-header">
    <%@include file="../../base/head-nav.jsp"%>
</div>
<div class="container-fluid">
    <div class="row">
        <div class="left-slide col-sm-2" style="padding: 15px;margin: 0">
            <%@include file="../../base/index-slide.jsp"%>
        </div>
        <div class="body-container container col-sm-10">
            <div class="row">
                <div class="col-sm-12 bread-nav">
                    <span class="layui-breadcrumb">
                      <a href="/capcplus/go/index"><i class="fa fa-server" aria-hidden="true"></i>&nbsp;服务管理</a>
                      <a href="/capcplus/go/recharge-list"><i class="fa fa-cube" aria-hidden="true"></i>服务配置</a>
                             <a href="/capcplus/go/recharge-list"><i class="fa fa-check-circle-o" aria-hidden="true"></i>统一认证</a>
                    </span>
                    <hr>
                </div>
                <%--内容--%>
                <div class="col-sm-12 server-list-content">
                    <div class="panel panel-success">
                        <div class="panel-body">
                            <form class="form-inline" onsubmit="return false">
                                <div class="form-group">
                                    <label for="appName">应用名称：</label>
                                    <input type="text" class="form-control" id="appName" name="appName" autocomplete="off" placeholder="请输入应用名称">
                                </div>
                                <div class="form-group">
                                    <label for="appId">AppId：</label>
                                    <input type="text" class="form-control" id="appId" name="appId" autocomplete="off" placeholder="请输入appId">
                                </div>
                                <div class="form-group">
                                    <label for="appPlatform">平台：</label>
                                    <select name="appPlatform" id="appPlatform">
                                        <option value="">全部</option>
                                        <option value="Android">Android</option>
                                        <option value="Android">ios</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="auditStatus">审核状态：</label>
                                    <select name="auditStatus" id="auditStatus">
                                        <option value="">全部</option>
                                        <option value="WAIT">待审核</option>
                                        <option value="SUCCESS">审核通过</option>
                                        <option value="REFUSE">审核拒绝</option>
                                    </select>
                                </div>
                                <button type="submit" class="btn btn-info" onclick="search()"><i class="fa fa-search" aria-hidden="true"></i>搜索</button>
                                <button type="button" class="btn btn-info" onclick="del()"><i class="fa fa-trash-o" aria-hidden="true"></i>删除</button>
                                <button type="button" class="btn btn-info" onclick="add()"><i class="fa fa-plus-square-o" aria-hidden="true"></i>新增</button>
                                <button type="button" class="btn btn-info" onclick="reset()"><i class="fa fa-undo" aria-hidden="true"></i>重置</button>
                                <button type="button" class="btn btn-info" onclick="whiteList()"><i class="fa fa-paperclip" aria-hidden="true"></i>白名单</button>
                            </form>
                            <%--表格内容块--%>
                            <div class="col-sm-12" style="margin-top: 25px;">
                                <table class="table table-striped table-bordered table-hover" id="cverifycation-table" style="width:100%">
                                    <thead>
                                    <tr>
                                        <th><input type="checkbox" id="all_checked"></th>
                                        <th class="text-center">序号</th>
                                        <th class="text-center">应用名称</th>
                                        <th class="text-center">appId</th>
                                        <th class="text-center">平台</th>
                                        <th class="text-center">应用能力</th>
                                        <th class="text-center">审核状态</th>
                                        <th class="text-center">操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row nav-slide-footer">
                <div class="footer" style="background-color: #fff;margin-bottom: 30px;">
                    <div class="col-sm-12">
                        <p class="text-center" style="margin-bottom: 0px">为了更好的页面体验，建议使用IE 11,chrome 51,Firefox 54及以上版本</p>
                        <%@include file="../../base/footer.jsp"%>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<div id="config-white-list" style="display: none;margin-top: 30px;">
    <input type="hidden" id="currentWhiteList">
    <form class="form-horizontal" id="form-white-list" onsubmit="return false">
        <div class="form-group">
            <label for="companyId" class="col-sm-3 control-label">IP白名单</label>
            <div class="col-sm-4">
                <textarea name="whiteList" id="whiteList" style="margin: 0px -180px 0px 0px; width: 349px; height: 205px;" class="form-control" placeholder="请输入用户IP，如多个IP请以单逗号隔开" rows="3" maxlength="240">
                    ${whiteList}
                </textarea>
            </div>
        </div>
        <div class="form-group">
            <label for="companyId" class="col-sm-1 control-label"></label>
            <label for="companyId" class="col-sm-9 control-label">
                <span style="color: red">注意：用户IP为用户实际请求的单个、多个IP或IP网段。</span>
            </label>
        </div>
    </form>
</div>
<div id="modify-cverification" style="display: none;margin-top: 30px;">
    <form class="form-horizontal" id="form-modify-cverification" onsubmit="return false">
        <input type="hidden" id="modify-id" name="id" value="">
        <div class="form-group">
            <label for="companyId" class="col-sm-3 control-label">归属</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" value="${company.name}" readonly="readonly">
                <input type="hidden" id="modify-companyId" name="companyId" value="${company.id}">
            </div>
        </div>
        <div class="form-group">
            <label for="systemYwIds" class="col-sm-3 control-label"><font color="red">*</font>服务分类：</label>
            <select id="modify-systemYwIds" name="systemYwIds" multiple title="请选择服务分类">
                <c:forEach items="${servers}" var="server" varStatus="status">
                    <option value="${server.id}" id="server${server.id}">${server.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="appName" class="col-sm-3 control-label"><font color="red">*</font>应用名称</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" id="modify-appName" placeholder="appName" autocomplete="off"  name="appName">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label"><font color="red">*</font>应用logo</label>
            <div class="col-sm-5  upload-container">
                <label style="width: 78px;height: 78px;position: absolute"><i class="fa fa-plus-square-o upload-label" aria-hidden="true"></i></label>
                <input type="file" name="file" class="upload-input fileUpload" id="file" onchange="ajaxFileUpload($(this),700)">
                <input type="hidden" class="hidePicValue" name="appLogoUrl" id="modify-appLogoUrl" value="">
            </div>
        </div>
        <div class="form-group">
            <label for="appDescription" class="col-sm-3 control-label"><font color="red">*</font>应用简介</label>
            <div class="col-sm-5">
                <textarea style="height: 90px;width: 255px;" name="appDescription" id="modify-appDescription" class="form-control" placeholder="填写应用简介" maxlength="230" rows="3"></textarea>
            </div>
        </div>
        <div class="form-group">
            <label for="appPlatform-add" class="col-sm-3 control-label"><font color="red">*</font>应用平台</label>
            <div class="col-sm-5">
                <select name="appPlatform" id="modify-appPlatform" class="capcplus-select">
                    <option value="Android">Android</option>
                    <option value="iOS">iOS</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label for="appSign" class="col-sm-3 control-label"><font color="red">*</font>应用签名</label>
            <div class="col-sm-5">
                <input type="text" class="form-control" id="modify-appSign" name="appSign" autocomplete="off" placeholder="应用签名" value="">
            </div>
        </div>
        <div class="form-group">
            <label for="appPackage" class="col-sm-3 control-label"><font color="red">*</font>应用包名</label>
            <div class="col-sm-5">
                <input type="text" class="form-control" id="modify-appPackage" name="appPackage" autocomplete="off" placeholder="应用包名" value="">
            </div>
        </div>
        <div class="form-group">
            <label for="appLoginClazz" class="col-sm-3 control-label"><font color="red">*</font>登录类全类名</label>
            <div class="col-sm-5">
                <input type="text" class="form-control" id="modify-appLoginClazz" name="appLoginClazz" autocomplete="off" placeholder="登录类全类名" value="">
            </div>
        </div>
    </form>
</div>
<div id="add-cverification" style="display: none;margin-top: 30px;">
    <form class="form-horizontal" id="form-add-cverification" onsubmit="return false">
        <div class="form-group">
            <label for="companyId" class="col-sm-3 control-label">归属</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" value="${company.name}" readonly="readonly">
                <input type="hidden" id="companyId" name="companyId" value="${company.id}">
            </div>
        </div>
        <div class="form-group">
            <label for="systemYwIds" class="col-sm-3 control-label"><font color="red">*</font>服务分类：</label>
            <select id="systemYwIds" name="systemYwIds" multiple title="请选择服务分类">
                <c:forEach items="${servers}" var="server" varStatus="status">
                    <option value="${server.id}" id="server${server.id}">${server.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="appName" class="col-sm-3 control-label"><font color="red">*</font>应用名称</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="appName-2" placeholder="appName" autocomplete="off"  name="appName">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label"><font color="red">*</font>应用logo</label>
            <div class="col-sm-4  upload-container">
                <label style="width: 78px;height: 78px;position: absolute"><i class="fa fa-plus-square-o upload-label" aria-hidden="true"></i></label>
                <input type="file" name="file" class="upload-input fileUpload" id="file2" onchange="ajaxFileUpload($(this),700)">
                <input type="hidden" class="hidePicValue" name="appLogoUrl" id="appLogoUrl" value="">
            </div>
        </div>
        <div class="form-group">
            <label for="appDescription" class="col-sm-3 control-label"><font color="red">*</font>应用简介</label>
            <div class="col-sm-4">
                <textarea style="height: 90px;width: 255px;" name="appDescription" id="appDescription" class="form-control" placeholder="填写应用简介" maxlength="230" rows="3"></textarea>
            </div>
        </div>
        <div class="form-group">
            <label for="appPlatform-add" class="col-sm-3 control-label"><font color="red">*</font>应用平台</label>
            <div class="col-sm-4">
                <select name="appPlatform" id="appPlatform-add" class="capcplus-select">
                    <option value="Android">Android</option>
                    <option value="iOS">iOS</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label for="appSign" class="col-sm-3 control-label"><font color="red">*</font>应用签名</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="appSign" name="appSign" autocomplete="off" placeholder="应用签名" value="">
            </div>
        </div>
        <div class="form-group">
            <label for="appPackage" class="col-sm-3 control-label"><font color="red">*</font>应用包名</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="appPackage" name="appPackage" autocomplete="off" placeholder="应用包名" value="">
            </div>
        </div>
        <div class="form-group">
            <label for="appLoginClazz" class="col-sm-3 control-label"><font color="red">*</font>登录类全类名</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="appLoginClazz" name="appLoginClazz" autocomplete="off" placeholder="登录类全类名" value="">
            </div>
        </div>
    </form>
</div>
<link rel="stylesheet" href="/static/css/capcplus/server/cverifycation.css">
<script src="/static/js/capcplus/server/cverifycation.js"></script>
<script>
</script>
</html>
