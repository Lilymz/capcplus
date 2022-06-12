<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%@include file="../../base/head-meta.jsp"%>
<%@include file="../../base/head-meta-js.jsp"%>
<link rel="stylesheet" href="/static/iCheck/skins/flat/blue.css">
<script src="/static/iCheck/icheck.min.js"></script>
<style>
    #ticket-table td{
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
                      <a href="/capcplus/detail/go/list"><i class="fa fa-list" aria-hidden="true"></i>&nbsp;发票记录</a>
                    </span>
                    <hr>
                </div>
                <%--内容--%>
                <div class="col-sm-12 server-list-content">
                    <div class="panel panel-success">
                        <div class="panel-heading"><i class="fa fa-table" aria-hidden="true"></i>使用情况</div>
                        <div class="panel-body">
                            <form class="form-inline" onsubmit="return false">
                                <div class="col-sm-12">
                                    <div class="form-group">
                                    </div>
                                    <div class="form-group">
                                   </div>
                                </div>
                                <br>
                                <br>
                                <div class="col-sm-12" style="padding-top: 5px">
                                    <div class="form-group">
                                        <input type="text" class="form-control" autocomplete="off" id="ticketMan" name="ticketMan" placeholder="请输入发票抬头">
                                    </div>
                                    <div class="form-group">
                                        <input type="text" class="form-control" autocomplete="off" id="creditCode" name="creditCode" placeholder="请输入税务登记号">
                                    </div>
                                    <div class="form-group">
                                        <input type="text" class="form-control" autocomplete="off" id="ticketProject" name="ticketProject" placeholder="请输入发票项目">
                                    </div>
                                    <div class="form-group">
                                        <label for="ticketType">发票类型：</label>
                                        <select id="ticketType" name="ticketType" class="capcplus-select">
                                            <option value="">全部</option>
                                            <option value="VAT_INVOICE">增值税普通发票</option>
                                            <option value="SPECIAL_INVOICE">增值税专用发票</option>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="status">审核状态：</label>
                                        <select id="status" name="status" class="capcplus-select">
                                            <option value="">全部</option>
                                            <option value="WAIT">待审核</option>
                                            <option value="SUCCESS">审核通过</option>
                                            <option value="FAIL">驳回</option>
                                            <option value="CANCEL">撤销</option>
                                        </select>
                                    </div>
                                    <button type="submit" class="layui-btn layui-btn-radius layui-btn-normal" style="color:white;background-color: #75b9e6!important; border-color: #75b9e6!important;" onclick="search()"><i class="glyphicon glyphicon-search" aria-hidden="true"></i>查询</button>
                                    <button type="button" class="layui-btn layui-btn-radius layui-btn-normal" style="color:white;background-color: #75b9e6!important; border-color: #75b9e6!important;" onclick="fresh();"><i class="glyphicon glyphicon-repeat" aria-hidden="true"></i>重置</button>
                                </div>
                            </form>
                            <%--表格内容块--%>
                            <div class="col-sm-12" style="margin-top: 25px;">
                                <table class="table table-striped table-bordered table-hover" id="ticket-table" style="width:100%">
                                    <thead>
                                    <tr>
                                        <th class="text-center">序号</th>
                                        <th class="text-center">发票抬头</th>
                                        <th class="text-center">税务登记号</th>
                                        <th class="text-center">发票类型</th>
                                        <th class="text-center">发票项目</th>
                                        <th class="text-center">开票总额(元)</th>
                                        <th class="text-center">申请时间</th>
                                        <th class="text-center">审核状态</th>
                                        <th class="text-center">审核备注</th>
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
<div id="ticket-info-detail" style="display: none">
    <form method="post" onsubmit="return false" style="margin-left: 26px;">
        <input id="id" value="2d419875c5c64b8e9ec33f09b0f4d908" hidden="">
        <input id="url" value="" hidden="">
        <div style="border-bottom: 1px solid #ddd; height: 35px; line-height: 35px; font-size: 15px;">
            <strong>开票信息</strong>
        </div>
        <table width="100%" cellpadding="0" cellspacing="8" style="margin-top:10px;margin-bottom:10px;">
            <tbody><tr style="line-height:25px;">
                <td class="addtab_tit" style="width:18%;">发票抬头：</td>
                <td style="width:40%;"> <span id="invoiceTitle"></span></td>

                <td class="addtab_tit" style="width:18%;padding-left:10px;">开户银行：</td>
                <td style="width:40%;"><span id="invoiceBank"></span></td>
            </tr>
            <tr style="line-height:20px;">
                <td class="addtab_tit">营业电话：</td>
                <td><span id="orgMobile"></span></td>

                <td class="addtab_tit" style="width:18%;padding-left:10px;">银行账号：</td>
                <td><span id="bankCardNumber"></span></td>
            </tr>
            <tr style="line-height:20px;">
                <td class="addtab_tit">营业地址：</td>
                <td><span id="orgAddress"></span></td>

                <td class="addtab_tit" style="width:18%;padding-left:10px;">纳税人资格类型：</td>
                <td><span id="taxType"></span></td>
            </tr>
            <tr style="line-height:20px;">
                <td class="addtab_tit" style="width:18%;">税务登记号：</td>
                <td><span id="taxRegisterNumber"></span></td>
            </tr>
            </tbody></table>

        <div style="border-bottom: 1px solid #ddd; height: 35px; line-height: 35px; font-size: 15px;">
            <strong>发票内容</strong>
        </div>
        <table width="100%" cellpadding="0" cellspacing="8" style="margin-top:10px;margin-bottom:10px;">
            <tbody>
                <tr style="line-height:25px;">
                    <td class="addtab_tit" style="width:18%;">开票金额(元)：</td>
                    <td style="width:40%;"><span id="invoiceAmount"></span></td>

                    <td class="addtab_tit" style="width:18%;padding-left:10px;">发票类别：</td>
                    <td><span id="invoiceType"></span></td>
                </tr>
                <tr style="line-height:25px;">
                    <td class="addtab_tit" style="width:12%;">发票项目：</td>
                    <td style="width:40%;"> <span id="invoiceCategory"></span></td>

                    <td class="addtab_tit" style="width:18%;padding-left:10px;">盖章类型：</td>
                    <td><span id="stampType"></span></td>
                </tr>
                <tr style="line-height:25px;">
                    <td class="addtab_tit" style="width:12%;">纸质账单：</td>
                    <td style="width:40%;"> <span id="needBill"></span></td>

                    <td class="addtab_tit" style="width:18%;padding-left:10px;">开票日期：</td>
                    <td><span id="insertTime"></span></td>
                </tr>
                <tr style="line-height:25px;width: 100%">
                    <td class="addtab_tit">服务名称：</td>
                    <td colspan="3"><span id="ywCategory"></span></td>
                </tr>
            </tbody>
        </table>

        <div style="border-bottom: 1px solid #ddd;line-height:35px;font-size:15px;margin-bottom:10px;">
            <strong>收票地址 </strong>
        </div>
        <table width="100%" cellpadding="0" border="0" cellspacing="0" align="left" style="border:1px solid #ececec;font-size: 14px;text-align:center; margin-bottom: 20px">
            <tbody>
                <tr style="line-height:35px;">
                    <td width="15%" style="border-bottom:1px solid #ececec;border-right:1px solid #ececec;">
                        收货人姓名
                    </td>
                    <td style="border-bottom:1px solid #ececec;border-right:1px solid #ececec;">收货地址</td>
                    <td style="border-bottom:1px solid #ececec;border-right:1px solid #ececec;">联系电话</td>
                </tr>
                <tr style="line-height:35px;">
                    <td style="border-right:1px solid #ececec;"><span id="receiver"></span> </td>
                    <td style="border-right:1px solid #ececec;"><span id="receivedAddress"></span></td>
                    <td style="border-right:1px solid #ececec;"><span id="receivedPhone"></span></td>
                </tr>
            </tbody>
        </table>
        <div>
            <div style="border-bottom: 1px solid #ddd; line-height:35px;font-size:15px;margin-bottom:10px;">
                <strong> 选中账单 </strong>
            </div>
            <table width="100%" cellpadding="0" border="0" cellspacing="0" align="left" style="border:1px solid #ececec;font-size: 14px;text-align:center;">
                <thead>
                <tr style="line-height:35px;">
                    <td width="5%" style="border-bottom:1px solid #ececec;border-right:1px solid #ececec;">序号</td>
                    <td width="30%" style="border-bottom:1px solid #ececec;border-right:1px solid #ececec;">账单时间</td>
                    <td style="border-bottom:1px solid #ececec;border-right:1px solid #ececec;">账单金额(元)</td>
                    <td style="border-bottom:1px solid #ececec;border-right:1px solid #ececec;">业务类别</td>
                </tr>
                </thead>
                <tbody id="invoiceApplyBills">
                </tbody>

            </table>
            <div style="border-bottom: 1px solid #ddd;  line-height: 35px; font-size: 15px;margin-top:20px;margin-bottom:10px;">
                <strong>审核信息</strong>
            </div>
            <table width="100%" cellpadding="0" cellspacing="8" style="margin-top:10px;margin-bottom:10px;word-wrap: break-word;word-break: break-all">
                <tbody><tr style="line-height:25px;">
                    <td class="addtab_tit" style="width:8%;">审核状态：</td>
                    <td style="width:40%;" id="auditStatus"></td>
                </tr>
                <tr style="line-height:20px;" hidden="" id="showAuditRemark">
                    <td class="addtab_tit">审核备注：</td>
                    <td><span id="auditRemark"></span></td>
                </tr>
                </tbody>
            </table>
        </div>
    </form>
</div>
<link rel="stylesheet" href="/static/css/capcplus/ticket/ticket-list.css">
<script src="/static/js/capcplus/ticket/ticket-list.js"></script>
<script>
</script>
</html>
