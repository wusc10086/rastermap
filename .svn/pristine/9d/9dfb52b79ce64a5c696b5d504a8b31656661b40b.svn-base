<%-- 
    Document   : batch-uploadjsp
    Created on : 2017-7-29, 10:30:18
    Author     : wuyiwei
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="org.rasterdb.domain.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <title>批量添加新设备</title>
    <link rel="stylesheet" href="<c:url value='/js/bootstrap/css/bootstrap.min.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/js/bootstrap-datetimepicker/bootstrap-datetimepicker.min.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/common.css'/>"/>
    <script src="<c:url value='/js/jquery-1.10.2.js'/>"></script>
    <script src="<c:url value='/js/bootstrap/bootstrap.min.css'/>"></script>
    <script src="<c:url value='/js/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js'/>" type="text/javascript"></script>
    <script src="<c:url value='/js/bootstrap-datetimepicker/locales/bootstrap-datetimepicker.zh-CN.js'/>" type="text/javascript"></script>
</head>
<body style=" width:100%;">
        <div class="page-title-breadcrumb">
            <h4 style="text-align: center;">批量添加新设备</h4>
        </div>
       <div class="container-fluid">
        <div class="row">
            <div class="col-lg-3"></div>
            <div class="col-lg-6">
                <form id="form" class="form-horizontal" action="<c:url value='/deviceManager/batchUploadSave'/>" enctype="multipart/form-data" method="post">
                <input type="hidden" name="pager" value="${obj.pagerString}"/>
                
                <div class="error-box">
                    <c:forEach var="errorMsg" items="${obj.errors.rowsError}">
                        <div>
                            ${errorMsg}
                        </div>
                    </c:forEach>
                </div>
                
                <div class="form-group">
                    <label for="deviceFile" class="col-sm-4 control-label">上传设备的Excel文件</label>
                    <div class="col-sm-8 input-group">
                        <input type="file" name="deviceFile" class="form-control" id="deviceFile">
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-sm-4 control-label"></label>
                    <div class="col-sm-4">
                        <button type="submit" class="btn btn-primary">保存</button>
                        <c:url var="doListUrl" value="/deviceManager/doList">
                            <c:param name="pager" value="${obj.pagerString}"/>
                        </c:url>
                        <a class="btn btn-default" href="${doListUrl}" role="button">返回列表页</a>
                    </div>
                    <div class="col-sm-4">
                        <c:url var="downDeviceTmplFileUrl" value="/deviceManager/downDeviceTmplFile">
                            <c:param name="pager" value="${obj.pagerString}"/>
                        </c:url>
                        <a class="btn btn-info" href="${downDeviceTmplFileUrl}" role="button">下载设备模板Excel文件</a>
                    </div>
                </div>
            </form>
            </div>
            <div class="col-lg-3"></div>
        </div>
    </div>
</body>
</html>
