<%-- 
    Document   : reset-passwdjsp
    Created on : 2017-7-31, 9:52:58
    Author     : wuyiwei
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="org.rasterdb.domain.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>重置用户密码</title>
        <link rel="stylesheet" href="<c:url value='/js/bootstrap/css/bootstrap.min.css'/>"/>
        <link rel="stylesheet" href="<c:url value='/css/common.css'/>"/>
        <script src="<c:url value='/js/jquery-1.10.2.js'/>"></script>
        <script src="<c:url value='/js/bootstrap/bootstrap.min.css'/>"></script>
    </head>
<body style=" width:100%;">
        <div class="page-title-breadcrumb">
            <h5 class="title">修改用户密码</h5>
        </div>
       <div class="container-fluid">
            <div class="row">
                <div class="col-sm-3"></div>
                <div class="col-sm-6">
                    <div class="col-sm-10">
                    <form id="form" class="form-horizontal" action="<c:url value='/userManager/resetPasswdSave'/>">
                    <input type="hidden" name="pager" value="${obj.pagerString}"/>
                    <input type="hidden" name="id" value="${obj.user.id}"/>
                    <input type="hidden" name="username" value="${obj.user.username}"/>
                    
                    <div class="form-group">
                        <label for="userRealName" class="col-sm-3 control-label">姓名</label>
                        <div class="col-sm-9">
                            <input name="userRealName" class="form-control ${userRealNameError}" id="userRealName" value="${obj.user.userRealName}" readonly>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <c:if test="${ not empty obj.errors.newPasswd }">
                            <c:set var="newPasswdError" value="field-error"/>
                        </c:if>
                        <label for="passwd" class="col-sm-3 control-label">用户新密码</label>
                        <div class="col-sm-9">
                            <input name="newPasswd" class="form-control ${newPasswdError}" id="passwd" placeholder="用户登录密码">
                            <span>${obj.errors.newPasswd}</span>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-sm-3 control-label"></label>
                        <div class="col-sm-9">
                            <button type="submit" class="btn btn-primary">保存</button>
                            <c:url var="doListUrl" value="/userManager/doList">
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
    </body>
</html>
