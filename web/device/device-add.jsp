<%-- 
    Document   : device-addjsp
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
    <title>添加新设备</title>
    <link rel="stylesheet" href="<c:url value='/js/bootstrap/css/bootstrap.min.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/js/bootstrap-datetimepicker/bootstrap-datetimepicker.min.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/common.css'/>"/>
    <script src="<c:url value='/js/jquery-1.10.2.js'/>"></script>
    <script src="<c:url value='/js/bootstrap/bootstrap.min.js'/>"></script>
    <script src="<c:url value='/js/laydate2/laydate.js'/>" type="text/javascript" charset="UTF-8"></script>
</head>
<body style=" width:100%;">
        <div class="page-title-breadcrumb">
            <h5 class="title">添加新设备</h5>
        </div>
       <div class="container-fluid">
        <div class="row">
            <div class="col-sm-3"></div>
            <div class="col-sm-6">
            <div class="col-sm-10">
                <form id="form" class="form-horizontal" action="<c:url value='/deviceManager/addSave'/>">
                <input type="hidden" name="pager" value="${obj.pagerString}"/>
                
                <div class="form-group">
                    <c:if test="${ not empty obj.errors.num }">
                        <c:set var="numError" value="field-error"/>
                    </c:if>
                    <label for="num" class="col-sm-3 control-label">站号*</label>
                    <div class="col-sm-9 input-group">
                        <input name="num" class="form-control ${numError}" id="num" value="${obj.device.num}" placeholder="站号">
                        <span>${obj.errors.num}</span>
                    </div>
                </div>
                    
                <div class="form-group">
                    <c:if test="${ not empty obj.errors.deviceName }">
                        <c:set var="deviceNameError" value="field-error"/>
                    </c:if>
                    <label for="deviceName" class="col-sm-3 control-label">站名*</label>
                    <div class="col-sm-9 input-group">
                        <input name="deviceName" class="form-control ${deviceNameError}" id="deviceName" value="${obj.device.deviceName}" placeholder="站名">
                        <span>${obj.errors.deviceName}</span>
                    </div>
                </div>
                    
                <div class="form-group">
                    <c:if test="${ not empty obj.errors.latitude }">
                        <c:set var="latitudeError" value="field-error"/>
                    </c:if>
                    <label for="latitude" class="col-sm-3 control-label">纬度*</label>
                    <div class="col-sm-9 input-group">
                        <input name="latitude" class="form-control ${latitudeError}" id="latitude" value="${obj.device.latitude}" placeholder="纬度">
                        <span>${obj.errors.latitude}</span>
                    </div>
                </div>
                    
                <div class="form-group">
                    <c:if test="${ not empty obj.errors.longitude }">
                        <c:set var="longitudeError" value="field-error"/>
                    </c:if>
                    <label for="longitude" class="col-sm-3 control-label">经度*</label>
                    <div class="col-sm-9 input-group">
                        <input name="longitude" class="form-control ${longitudeError}" id="longitude" value="${obj.device.longitude}" placeholder="经度">
                        <span>${obj.errors.longitude}</span>
                    </div>
                </div>
                    
                <div class="form-group">
                    <c:if test="${ not empty obj.errors.elevation }">
                        <c:set var="elevationError" value="field-error"/>
                    </c:if>
                    <label for="elevation" class="col-sm-3 control-label">拔海高度</label>
                    <div class="col-sm-9 input-group">
                        <input name="elevation" class="form-control ${elevationError}" id="elevation" value="${obj.device.elevation}" placeholder="海拔高度">
                        <span>${obj.errors.elevation}</span>
                    </div>
                </div>
                
                <div class="form-group">
                    <c:if test="${ not empty obj.errors.manufaceture }">
                        <c:set var="elevationError" value="field-error"/>
                    </c:if>
                    <label for="manufaceture" class="col-sm-3 control-label">制造商</label>
                    <div class="col-sm-9 input-group">
                        <input name="manufaceture" class="form-control ${manufacetureError}" id="manufaceture" value="${obj.device.manufaceture}" placeholder="制造商">
                        <span>${obj.errors.manufaceture}</span>
                    </div>
                </div>
                
                <div class="form-group">
                    <c:if test="${ not empty obj.errors.installTime }">
                        <c:set var="installTimeError" value="field-error"/>
                    </c:if>
                    <label for="installTime" class="col-sm-3 control-label">安装时间</label>
                    <div class="col-sm-9 input-group">
                        <input name="installTime" class="form-control ${installTimeError}" id="installTime" value="${obj.device.installTime}" placeholder="安装时间">
                        <span>${obj.errors.installTime}</span>
                    </div>
                </div>
                    
                <div class="form-group">
                    <c:if test="${ not empty obj.errors.cityid }">
                        <c:set var="cityidError" value="field-error"/>
                    </c:if>
                    <label for="cityid" class="col-sm-3 control-label">地市*</label>
                    <div class="col-sm-9 input-group">
                        <select name="cityid" class="form-control ${cityidError}" id="cityid">
                            <option value="">请选择</option>
                        <c:forEach var ="city" items="${obj.cities}">
                            <option value="${city.cityid}" <c:if test="${city.cityid eq obj.device.cityid}">selected</c:if> >${city.name}</option>
                        </c:forEach>
                        </select>
                        <span>${obj.errors.cityid}</span>
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-sm-3 control-label"></label>
                    <div class="col-sm-9">
                        <button type="submit" class="btn btn-primary">保存</button>
                        <button type="reset" class="btn btn-default">重置</button>
                        <c:url var="doListUrl" value="/deviceManager/doList">
                            <c:param name="pager" value="${obj.pagerString}"/>
                        </c:url>
                        <a class="btn btn-default" href="${doListUrl}" role="button">返回列表页</a>
                    </div>
                </div>
            </form>
            </div>
            <div class="col-sm-2"></div>
            </div>
            <div class="col-sm-3"></div>
        </div>
    </div>
    <script type="text/javascript">
    //执行一个laydate实例
    laydate.render({
        type: 'date',
        format: "yyyy-MM-dd",
        elem: '#installTime' //指定元素
    });

    </script>
    <script>
       $("button[type='reset']").on('click', function(){
            $('div.form-group > div > span').hide(); 
            $('div.form-group').find(".field-error").removeClass("field-error"); 
            return true;
       });         
     
    </script>
</body>
</html>
