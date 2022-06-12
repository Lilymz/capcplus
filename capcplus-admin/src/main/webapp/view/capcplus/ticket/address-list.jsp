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
                      <a href="/capcplus/go/index"><i class="fa fa-ticket" aria-hidden="true"></i>&nbsp;发票管理</a>
                      <a href="/capcplus/ticket/go/address-list"><i class="fa fa-address-card" aria-hidden="true"></i>&nbsp;地址管理</a>
                    </span>
                    <hr>
                </div>
                <%--内容--%>
                <div class="col-sm-12 server-list-content">
                    <div class="panel panel-success">
                        <div class="panel-body">
                            <form class="form-inline" onsubmit="return false">
                                <button type="submit" class="btn btn-info" onclick="addAddress()"><i class="fa fa-plus" aria-hidden="true"></i> 新增地址</button>
                            </form>
                            <%--表格内容块--%>
                            <div class="col-sm-12" style="margin-top: 25px;">
                                <table class="table table-striped table-bordered table-hover" id="address-table" style="width:100%">
                                    <thead>
                                    <tr>
                                        <th class="text-center">序号</th>
                                        <th class="text-center">收货人姓名</th>
                                        <th class="text-center">所在地区</th>
                                        <th class="text-center">详细地址</th>
                                        <th class="text-center">手机号码</th>
                                        <th class="text-center">电话号码</th>
                                        <th class="text-center">邮政编码</th>
                                        <th class="text-center">是否默认地址</th>
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
<div id="modify-address" style="display: none;margin-top: 30px;">
    <form class="form-horizontal" id="form-modify-address" onsubmit="return false">
        <input type="hidden" id="modify-id" name="id" value="">
        <div class="form-group">
            <label for="name" class="col-sm-3 control-label"><font color=red>*</font>收货人姓名</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="modify-name" name="name" placeholder="收货人姓名">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label" style="text-align: right;"><font color=red>*</font>所在地区</label>
            <div class="col-sm-3 form-group" style="margin:0 0 0 -17px">
                <select id="modify-provinceId" name="provinceId" class="form-control" style="width:120px;">
                    <option value="">省份</option>
                    <option value="0">不限</option>
                </select>
            </div>
            <div class="col-sm-3 form-group" style="margin:0 0 0 -17px">
                <select id="modify-cityId" name="cityId" class="form-control" style="width:120px;">
                    <option value="">城市</option>
                    <option value="0">不限</option>
                </select>
            </div>
            <div class="col-sm-3 form-group" style="margin:0 0 0 -17px">
                <select id="modify-areaId" name="areaId" class="form-control" style="width:120px;">
                    <option value="">区域</option>
                    <option value="0">不限</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label for="detailAddress" class="col-sm-3 control-label"><font color="red">*</font>详细地址</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" id="modify-detailAddress" placeholder="详细地址" autocomplete="off"  name="detailAddress">
            </div>
        </div>
        <div class="form-group">
            <label for="postCode" class="col-sm-3 control-label"><font color="red">*</font>邮政编码</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" id="modify-postCode" placeholder="邮政编码" autocomplete="off"  name="postCode">
            </div>
        </div>
        <div class="form-group">
            <label for="phone" class="col-sm-3 control-label"><font color="red">*</font>手机号码</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" id="modify-phone" placeholder="手机号码" autocomplete="off"  name="phone">
            </div>
        </div>
        <div class="form-group">
            <label for="telephone" class="col-sm-3 control-label"><font color="red">*</font>座机号</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" id="modify-telephone" placeholder="座机号" autocomplete="off"  name="telephone">
            </div>
        </div>
        <div class="form-group">
            <label for="isDefault" class="col-sm-3 control-label"><font style="color: red">*</font>是否默认地址</label>
            <div class="col-sm-6" style="margin-top: 6px;">
                <input type="radio" name="isDefault" class="modify-noDefault icheckbox_flat-blue" value="NO" checked>
                <label>否</label>
                <input type="radio" name="isDefault" class="modify-isDefault icheckbox_flat-blue" value="YES">
                <label>是</label>
            </div>
        </div>
    </form>
</div>
<div id="add-address" style="display: none;margin-top: 30px;">
    <form class="form-horizontal" id="form-add-address" onsubmit="return false">
        <input type="hidden" id="id" name="id" value="">
        <div class="form-group">
            <label for="name" class="col-sm-3 control-label"><font color=red>*</font>收货人姓名</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="name" name="name" placeholder="收货人姓名">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label" style="text-align: right;"><font color=red>*</font>所在地区</label>
            <div class="col-sm-3 form-group" style="margin:0 0 0 -17px">
                <select id="provinceId" name="provinceId" class="form-control" style="width:120px;">
                    <option value="">省份</option>
                    <option value="0">不限</option>
                </select>
            </div>
            <div class="col-sm-3 form-group" style="margin:0 0 0 -17px">
                <select id="cityId" name="cityId" class="form-control" style="width:120px;">
                    <option value="">城市</option>
                    <option value="0">不限</option>
                </select>
            </div>
            <div class="col-sm-3 form-group" style="margin:0 0 0 -17px">
                <select id="areaId" name="areaId" class="form-control" style="width:120px;">
                    <option value="">区域</option>
                    <option value="0">不限</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label for="detailAddress" class="col-sm-3 control-label"><font color="red">*</font>详细地址</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" id="detailAddress" placeholder="详细地址" autocomplete="off"  name="detailAddress">
            </div>
        </div>
        <div class="form-group">
            <label for="postCode" class="col-sm-3 control-label"><font color="red">*</font>邮政编码</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" id="postCode" placeholder="邮政编码" autocomplete="off"  name="postCode">
            </div>
        </div>
        <div class="form-group">
            <label for="phone" class="col-sm-3 control-label"><font color="red">*</font>手机号码</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" id="phone" placeholder="手机号码" autocomplete="off"  name="phone">
            </div>
        </div>
        <div class="form-group">
            <label for="telephone" class="col-sm-3 control-label"><font color="red">*</font>座机号</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" id="telephone" placeholder="座机号" autocomplete="off"  name="telephone">
            </div>
        </div>
        <div class="form-group">
            <label for="isDefault" class="col-sm-3 control-label"><font style="color: red">*</font>是否默认地址</label>
            <div class="col-sm-6" style="margin-top: 6px;">
                <input type="radio" name="isDefault" class="icheckbox_flat-purple" value="NO" checked>
                <label>否</label>
                <input type="radio" name="isDefault" class="icheckbox_flat-purple" value="YES">
                <label>是</label>
            </div>
        </div>
    </form>
</div>
<link rel="stylesheet" href="/static/css/capcplus/ticket/address-list.css">
<script src="/static/js/capcplus/ticket/address-list.js"></script>
<script>
    //初始化省市区
    var addressOne =addressInit('provinceId','cityId','areaId','','','');
    $(function (){
        $('input').iCheck({
            checkboxClass: 'icheckbox_flat-blue',
            radioClass: 'iradio_flat-blue'
        });
    });
</script>
</html>