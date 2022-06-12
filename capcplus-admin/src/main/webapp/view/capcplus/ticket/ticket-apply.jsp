<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="../../base/head-meta.jsp"%>
<%@include file="../../base/head-meta-js.jsp"%>
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
                      <a href="/capcplus/go/index"><i class="fa fa-ticket" aria-hidden="true"></i>&nbsp;发票管理</a>
                      <a href="/capcplus/go/index"><i class="fa fa-info" aria-hidden="true"></i>&nbsp;开票信息</a>
                    </span>
                    <hr>
                </div>
                <%--内容--%>
                <div class="col-sm-12 step" style="border: 1px solid #e9e9e9;height: 40px; padding: 36px 0px;">
                    <div id="step"></div>
                </div>
                <div class="col-sm-12 step-content">
                    <div class="col-sm-12 enterprise-content step1" style="position: absolute">
                        <div class="panel panel-info">
                            <div class="panel-heading"><i class="fa fa-pencil" aria-hidden="true"></i>修改开票信息</div>
                            <div class="panel-body">
                                <form class="form-horizontal body-info">
                                    <div class="form-group">
                                        <label for="ticketMan" class="col-sm-2 control-label"><font style="color: red">*</font>发票抬头</label>
                                        <div class="col-sm-6">
                                            <input type="text" value="${ticket.ticketMan}" class="form-control" id="ticketMan" name="ticketMan" autocomplete="off" placeholder="请填写发票抬头">
                                        </div>
                                    </div>
                                    <hr>
                                    <div class="form-group">
                                        <label for="creditCode" class="col-sm-2 control-label"><font style="color: red">*</font>统一社会信用代码/税务登记号</label>
                                        <div class="col-sm-6">
                                            <input type="text" value="${ticket.creditCode}" class="form-control" id="creditCode" name="creditCode" autocomplete="off" placeholder="请填写统一社会信用代码/税务登记号">
                                        </div>
                                    </div>
                                    <hr>
                                    <div class="form-group">
                                        <label for="bank" class="col-sm-2 control-label"><font style="color: red">*</font>开户行名称</label>
                                        <div class="col-sm-6">
                                            <input type="text" value="${ticket.bank}" class="form-control" id="bank" name="bank" autocomplete="off" placeholder="请填写开户行名称">
                                        </div>
                                    </div>
                                    <hr>
                                    <div class="form-group">
                                        <label for="bankAccount" class="col-sm-2 control-label"><font style="color: red">*</font>开户行账号</label>
                                        <div class="col-sm-6">
                                            <input type="text" value="${ticket.bankAccount}" class="form-control" id="bankAccount" name="bankAccount" autocomplete="off" placeholder="请填写开户行账号">
                                        </div>
                                    </div>
                                    <hr>
                                    <div class="form-group">
                                        <label for="businessPhone" class="col-sm-2 control-label"><font style="color: red">*</font>营业电话</label>
                                        <div class="col-sm-6">
                                            <input type="text" value="${ticket.businessPhone}" class="form-control" id="businessPhone" name="businessPhone" autocomplete="off" placeholder="请填写营业电话">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="type" class="col-sm-2 control-label"><font style="color: red">*</font>纳税人资格类型</label>
                                        <input type="radio" name="type" lass="icheckbox_flat-purple" ${ticket.type.code==1?'checked':''}>
                                        <label>小规模纳税人</label>
                                        <input type="radio" name="type" class="icheckbox_flat-purple" ${ticket.type.code==2?'checked':''}>
                                        <label>一般纳税人</label>
                                    </div>
                                    <hr>
                                    <div class="form-group">
                                        <label for="businessLicense" class="col-sm-2 control-label"><font style="color: red">*</font>营业执照/税务登记证扫描件</label>
                                        <div class="col-sm-6">
                                            <input type="text"  value="${ticket eq null?'':"/resources/images/"}${ticket.businessLicense}" class="form-control" id="businessLicense" name="businessLicense" autocomplete="off" placeholder="营业执照/税务登记证扫描件">
                                        </div>
                                        <div class="col-sm-1">
                                            <button type="button" class="btn btn-active" id="businessLicense-upload">
                                                <i class="layui-icon">&#xe67c;</i>上传图片
                                            </button>
                                        </div>
                                        <div class="col-sm-1">
                                            <button type="button" class="layui-btn layui-btn-radius btn btn-info" id="businessLicense-view">
                                                <i class="glyphicon glyphicon-picture"></i>预览
                                            </button>
                                        </div>
                                    </div>
                                    <hr>
                                    <div class="form-group">
                                        <label for="qualification" class="col-sm-2 control-label">一般纳税人资格认证扫描件</label>
                                        <div class="col-sm-6">
                                            <input type="text" value="${ticket eq null?'':"/resources/images/"}${ticket.qualification}" class="form-control" id="qualification" name="qualification" autocomplete="off" placeholder="一般纳税人资格认证扫描件">
                                        </div>
                                        <div class="col-sm-1">
                                            <button type="button" class="btn btn-active" id="qualification-upload">
                                                <i class="layui-icon">&#xe67c;</i>上传图片
                                            </button>
                                        </div>
                                        <div class="col-sm-1">
                                            <button type="button" class="layui-btn layui-btn-radius btn btn-info" id="qualification-view">
                                                <i class="glyphicon glyphicon-picture"></i>预览
                                            </button>
                                        </div>
                                    </div>
                                    <hr>
                                    <div class="form-group">
                                        <label for="otherFile" class="col-sm-2 control-label">其他文件</label>
                                        <div class="col-sm-6">
                                            <input type="text" value="${ticket eq null?'':"/resources/images/"}${ticket.otherFile}" class="form-control" id="otherFile" name="otherFile" autocomplete="off" placeholder="其他文件">
                                        </div>
                                        <div class="col-sm-1">
                                            <button type="button" class="btn btn-active" id="otherFile-upload">
                                                <i class="layui-icon">&#xe67c;</i>上传图片
                                            </button>
                                        </div>
                                        <div class="col-sm-1">
                                            <button type="button" class="layui-btn layui-btn-radius btn btn-info" id="otherFile-view">
                                                <i class="glyphicon glyphicon-picture"></i>预览
                                            </button>
                                        </div>
                                    </div>
                                    <hr>
                                    <div class="form-group">
                                        <label for="remark" class="col-sm-2 control-label">备注</label>
                                        <div class="col-sm-6">
                                            <input type="text" class="form-control" id="remark" autocomplete="请填写备注" name="remark" value="${ticket.remark}" >
                                        </div>
                                    </div>
                                    <hr>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">审核状态</label>
                                        <div class="col-sm-6" style="margin-left: 14px;margin-top: 6px">
                                            <label id="status">${ticket eq null ? '<small style="color:red">请提交开票信息~</small>':ticket.status.codeStr}</label>
                                        </div>
                                    </div>
                                    <hr>
                                    <div class="form-group" style="display: flex;align-items: center;justify-content: center">
                                       <button type="button" class="layui-btn layui-btn-normal" onclick="next(1)"><i class="fa fa-chevron-right" aria-hidden="true"></i>下一步</button>
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
                    <div class="col-sm-12 enterprise-content step2" style="display:none;position: absolute">
                        <div class="panel panel-info">
                            <div class="panel-heading"><i class="fa fa-pencil" aria-hidden="true"></i>选择账单</div>
                            <div class="panel-body">
                                <form class="form-horizontal body-info" id="add-ticket-apply-form">
                                    <input type="hidden" id="ticketId" name="ticketId" value="${ticket.id}">
                                    <input type="hidden" id="ticketAddressId" name="ticketAddressId">
                                    <input type="hidden" id="ticketProject-input" name="ticketProject">
                                    <input type="hidden" id="ticketBalance" name="ticketBalance">
                                    <div class="form-group">
                                        <label for="ticketType" class="col-sm-4 control-label"><font style="color: red">*</font>发票类别：</label>
                                        <div class="col-sm-6">
                                            <select id="ticketType" name="ticketType">
                                                <option value="VAT_INVOICE">增值税普通发票</option>
                                                <option value="SPECIAL_INVOICE">增值税专用发票</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="serverIds" class="col-sm-4 control-label"><font style="color: red">*</font>服务名称：</label>
                                        <div class="col-sm-6">
                                            <select id="serverIds" name="serverIds" multiple title="请选择服务名称">
                                               <c:forEach items="${servers}" var="server" varStatus="status">
                                                   <option value="${server.id}">${server.name}</option>
                                               </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <hr>
                                    <div class="form-group">
                                        <label for="billIds" class="col-sm-4 control-label"><font style="color: red">*</font>开票账单：</label>
                                        <div class="col-sm-6">
                                            <select id="billIds" name="billIds" multiple title="开票选择" class="selectpicker" data-actions-box="true" onchange="billChange(this)">
                                                <c:forEach items="${bills}" var="bill" varStatus="status">
                                                    <option value="${bill.id}" id="bill-${bill.id}" data-bill="${bill.balance}">${bill.id}|${bill.updateTime}|${bill.balance}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <hr>
                                    <div class="form-group">
                                        <label for="sealType" class="col-sm-4 control-label"><font style="color: red">*</font>盖章类型：</label>
                                        <div class="col-sm-6">
                                            <select id="sealType" name="sealType" title="请选择盖章类型">
                                                <option value="FINANCE">财务章</option>
                                                <option value="CONTRACT">合同章</option>
                                                <option value="OFFICIAL">公章章</option>
                                            </select>
                                        </div>
                                    </div>
                                    <hr>
                                    <div class="form-group">
                                        <label class="col-sm-4 control-label"><font style="color: red">*</font>纸质对账单:</label>
                                        <input type="radio" name="isPaper" class="iradio_minimal-blue" checked value="NO">
                                        <label>不需要</label>
                                        <input type="radio" name="isPaper" class="iradio_minimal-blue" value="YES">
                                        <label>需要</label>
                                    </div>
                                    <hr>
                                    <div class="form-group">
                                        <label class="col-sm-4 control-label"><font style="color: red">*</font>发票项目:</label>
                                        <div class="col-sm-6">
                                            <label style="margin-top: 6px" id="ticketProject">信息服务费，通信服务费</label>
                                        </div>
                                    </div>
                                    <hr>
                                    <div class="form-group">
                                        <label class="col-sm-4 control-label"><font style="color: red">*</font>发票金额:</label>
                                        <label style="margin-top: 6px" id="ticket-total-balance">0.00</label>
                                    </div>
                                    <hr>
                                    <div class="form-group" style="display: flex;align-items: center;justify-content: center">
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
                            <div class="panel-heading"><i class="fa fa-pencil" aria-hidden="true"></i>选择收票地址</div>
                            <div class="panel-body">
                                <%--表格内容块--%>
                                <div class="col-sm-12" style="margin-top: 25px;">
                                    <table class="table table-striped table-bordered table-hover" id="address-table" style="width:100%">
                                        <thead>
                                        <tr>
                                            <th class="text-center">请勾选一个地址</th>
                                            <th class="text-center">序号</th>
                                            <th class="text-center">收货人姓名</th>
                                            <th class="text-center">所在地区</th>
                                            <th class="text-center">详细地址</th>
                                            <th class="text-center">手机号码</th>
                                            <th class="text-center">电话号码</th>
                                            <th class="text-center">邮政编码</th>
                                            <th class="text-center">是否默认地址</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        </tbody>
                                    </table>
                                </div>
                                <hr>
                                <div class="form-group" style="display: flex;align-items: center;justify-content: center">
                                    <button type="button" class="layui-btn layui-btn-normal" onclick="prev(3)"><i class="fa fa-chevron-left" aria-hidden="true"></i>上一步</button>
                                    <button type="button" class="layui-btn layui-btn-normal" onclick="submitForm()"><i class="fa fa-chevron-right" aria-hidden="true"></i>下一步</button>
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
<link rel="stylesheet" href="/static/css/capcplus/ticket/ticket-apply.css">
<script src="/static/js/capcplus/ticket/ticket-apply.js"></script>
<script>
    $('input').iCheck({
        checkboxClass: 'icheckbox_minimal-blue',
        radioClass: 'iradio_minimal-blue',
        increaseArea: '20%' // optional
    });
</script>
</body>
</html>
