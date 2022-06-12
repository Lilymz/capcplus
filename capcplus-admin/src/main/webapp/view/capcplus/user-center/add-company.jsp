<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="../../base/head-meta.jsp"%>
<%@include file="../../base/head-meta-js.jsp"%>
<link rel="stylesheet" href="/static/iCheck/skins/flat/purple.css">
<script src="/static/iCheck/icheck.min.js"></script>
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
                      <a href="/capcplus/go/index"><i class="fa fa-male" aria-hidden="true"></i>&nbsp;&nbsp;用户中心</a>
                      <a style="cursor: inherit"><i class="fa fa-registered" aria-hidden="true"></i>企业申请</a>
                      <a href="/capcplus/go/add-company"><i class="layui-icon layui-icon-file-b"></i>企业资质申请</a>
                    </span>
                    <hr>
                </div>
                <%--内容--%>
                <div class="col-sm-12 step" style="border: 1px solid #e9e9e9;height: 40px; padding: 36px 0px;">
                    <div id="step" style="margin-left: 0!important;"></div>
                </div>
                <div class="col-sm-12 step-content" style="padding-left: 0;">
                    <div class="col-sm-12 enterprise-content step1" style="position: absolute">
                        <div class="panel panel-info">
                            <div class="panel-heading"><i class="fa fa-pencil" aria-hidden="true"></i>基本信息</div>
                            <div class="panel-body">
                                <div class="layui-tab">
                                    <ul class="layui-tab-title">
                                        <li>企业</li>
                                        <li>事业单位</li>
                                        <li>民办非企业单位</li>
                                        <li class="layui-this">个体工商户</li>
                                        <li>社会团体</li>
                                        <li>党政和国际机关</li>
                                    </ul>
                                    <div class="layui-tab-content">
                                        <div class="layui-tab-item">内容1</div>
                                        <div class="layui-tab-item">内容2</div>
                                        <div class="layui-tab-item">内容3</div>
                                        <div class="layui-tab-item layui-show">
                                            <form class="form-horizontal" id="private-business">
                                                <div class="panel">
                                                    <div class="panel-heading" style="color: black">单位类型<hr class="layui-border-blue"></div>
                                                    <div class="panel-body">
                                                        <div class="col-sm-7 col-sm-offset-5 upload-container">
                                                            <label style="width: 78px;height: 78px;position: absolute"><i class="fa fa-plus-square-o upload-label" aria-hidden="true"></i></label>
                                                            <input type="file" name="file" class="upload-input fileUpload" id="file" onchange="ajaxFileUpload($(this),700)">
                                                            <input type="hidden" class="hidePicValue" value="">
                                                        </div>
                                                        <div class="col-sm-7 col-sm-offset-5" style="margin-top: 15px;">
                                                            普通营业执照
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="panel">
                                                    <div class="panel-heading" style="color: black">个体工商户&nbsp;&nbsp;<small style="color: gray">按证书上内容逐一填写</small><hr class="layui-border-blue"></div>
                                                    <div class="panel-body">
                                                        <div class="form-group">
                                                            <label for="name" class="col-sm-2 control-label"><font color=red>*</font>名称</label>
                                                            <div class="col-sm-6">
                                                                <input type="text" class="form-control" id="name" name="name" placeholder="请输入名称" autocomplete="off">
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="registerCode" class="col-sm-2 control-label"><font color=red>*</font>注册号</label>
                                                            <div class="col-sm-6">
                                                                <input type="text" class="form-control" id="registerCode" name="registerCode" placeholder="请输入注册号" autocomplete="off">
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="col-sm-2 control-label" style="text-align: right;"><font color=red>*</font>单位所在地</label>
                                                            <div class="col-sm-2 form-group" style="margin:0 0 0 -17px">
                                                                <select id="provinceId" name="provinceId" class="form-control" style="width:160px;margin-left:15px">
                                                                    <option value="">省份</option>
                                                                    <option value="0">不限</option>
                                                                </select>
                                                            </div>
                                                            <div class="col-sm-2 form-group" style="margin:0 0 0 -17px">
                                                                <select id="cityId" name="cityId" class="form-control" style="width:160px;margin-left:15px">
                                                                    <option value="">城市</option>
                                                                    <option value="0">不限</option>
                                                                </select>
                                                            </div>
                                                            <div class="col-sm-2 form-group" style="margin:0 0 0 -17px">
                                                                <select id="areaId" name="areaId" class="form-control" style="width:160px;margin-left:15px">
                                                                    <option value="">区域</option>
                                                                    <option value="0">不限</option>
                                                                </select>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="companyAddress" class="col-sm-2 control-label"><font color=red>*</font>详细地址</label>
                                                            <div class="col-sm-6">
                                                                <input type="text" class="form-control" id="companyAddress" name="companyAddress" placeholder="请输入详细地址" autocomplete="off">
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="telPhone" class="col-sm-2 control-label"><font color=red>*</font>固定号码</label>
                                                            <div class="col-sm-6">
                                                                <input type="text" class="form-control" id="telPhone" name="telPhone" placeholder="请输入固定号码" autocomplete="off">
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="companyScope" class="col-sm-2 control-label"><font color=red>*</font>经营范围</label>
                                                            <div class="col-sm-6">
                                                                <input type="text" class="form-control" id="companyScope" name="companyScope" placeholder="请输入经营范围" autocomplete="off">
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="dueDate" class="col-sm-2 control-label">经营期限</label>
                                                            <div class="input-group date col-sm-6">
                                                                <input type="text" class="form-control" id="dueDate" name="dueDate"><span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="panel">
                                                    <div class="panel-heading" style="color: black">推荐人/公司&nbsp;&nbsp;<hr class="layui-border-blue"></div>
                                                    <div class="panel-body">
                                                        <div class="form-group">
                                                            <label for="introduce" class="col-sm-2 control-label">推荐人/公司名称</label>
                                                            <div class="col-sm-6">
                                                                <input type="text" class="form-control" id="introduce" name="introduce" placeholder="请输入推荐人/公司名称,没有可不填写" autocomplete="off">
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="panel">
                                                    <div class="panel-heading" style="color: black">联系方式&nbsp;&nbsp;<hr class="layui-border-blue"></div>
                                                    <div class="panel-body">
                                                        <div class="form-group">
                                                            <label for="telPhone" class="col-sm-2 control-label"><font color=red>*</font>联系人手机号码</label>
                                                            <div class="col-sm-6">
                                                                <input type="text" class="form-control" id="tempPhone" name="tempPhone" placeholder="请输入联系人手机号码" autocomplete="off">
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="telPhone" class="col-sm-2 control-label"></label>
                                                            <div class="col-sm-6">
                                                                您注册时已同意
                                                                <a class="text-decoration-none" style="color: #31BDEC" onclick="registerProtocol($(this))">大象平台用户注册协议</a>，请知悉
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                        <div class="layui-tab-item">内容5</div>
                                        <div class="layui-tab-item">内容6</div>
                                    </div>
                                    <div class="form-group" style="display: flex;align-items: center;justify-content: center">
                                        <button type="button" class="layui-btn layui-btn-normal" onclick="next(1)"><i class="fa fa-chevron-right" aria-hidden="true"></i>下一步</button>
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
                    <div class="col-sm-12 enterprise-content step2" style="display:none;position: absolute">
                        <div class="panel panel-info">
                            <div class="panel-heading"><i class="fa fa-pencil" aria-hidden="true"></i>上传证件照片</div>
                            <div class="panel-body">
                                <form class="form-horizontal body-info" id="step2-form">
                                    <div class="form-group">
                                        <label for="type" class="col-sm-offset-4 col-sm-3 text-center control-label" style="padding-left: 13px;">上传个体工商户证件照片，个体工商户法人照片</label>
                                        <div class="col-sm-12">
                                            <div class="col-sm-offset-4 col-sm-5 text-center" style="margin-left: 37.333333%;">
                                                <div class="col-sm-2 upload-container" style="margin-right: 20px;">
                                                    <label style="width: 78px;height: 78px;position: absolute"><i class="fa fa-plus-square-o upload-label" aria-hidden="true"></i></label>
                                                    <input type="file" name="file" class="upload-input fileUpload" id="file2" onchange="ajaxFileUpload($(this),700)" style="position: relative;left: 72px;">
                                                    <input type="hidden" class="hidePicValue" name="IdPhoto" value="">
                                                </div>
                                                <div class="col-sm-2 upload-container" style="margin-right: 20px;">
                                                    <label style="width: 78px; height: 78px; position: absolute; background-image: url(/resources/assets/home/others/business_template.jpg); background-size: cover;"></label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <hr>
                                    <div class="form-group" style="display: flex;align-items: center;justify-content: center ;position:relative;right: 20px;">
                                        <button type="button" class="layui-btn layui-btn-normal" onclick="prev(2)"><i class="fa fa-chevron-left" aria-hidden="true"></i>上一步</button>
                                        <button type="button" class="layui-btn layui-btn-normal" onclick="next(2)"><i class="fa fa-chevron-right" aria-hidden="true"></i>下一步</button>
                                    </div>
                                </form>
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
                    <div class="col-sm-12 enterprise-content step3" style="display:none;position: absolute">
                        <div class="panel panel-info">
                            <div class="panel-heading"><i class="fa fa-pencil" aria-hidden="true"></i>提交成功</div>
                            <div class="panel-body">
                                <hr>
                                <div class="form-group" style="display: flex;align-items: center;justify-content: center">
                                    <p style="font-size:14px;">
                                        <i class="layui-icon layui-icon-ok" style="color: #1E9FFF;"></i>
                                        企业信息提交完成，审核需要3-5个工作日，请耐心等待审核。
                                    </p>
                                </div>
                                <div class="form-group" style="display: flex;align-items: center;justify-content: center">
                                    <p>
                                        <a href="/capcplus/go/qulification-apply" class="layui-btn layui-btn-info text-decoration-none" style="background-color: #31BDEC;"><i class="layui-icon layui-icon-return" aria-hidden="true"></i>返回资质申请</a>
                                    </p>
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
        </div>
    </div>
</div>
<link rel="stylesheet" href="/static/step/step.css">
<script src="/static/step/step.js"></script>
<link rel="stylesheet" href="/static/css/capcplus/user-center/add-company.css">
<script src="/static/js/capcplus/user-center/add-company.js"></script>
<style>
    #step{
        margin: 0!important;
    }
</style>
</body>
</html>
