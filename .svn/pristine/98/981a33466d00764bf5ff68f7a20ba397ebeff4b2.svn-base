<%@ page language="java" import="java.util.*" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.rasterdb.domain.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="pragma" content="no-cache" />
    <meta http-equiv="cache-control" content="no-cache" />
    <meta http-equiv="expires" content="0"/>
    <meta http-equiv="refresh" content="60" />
    <title>设备列序图</title>
    <link rel="stylesheet" href="<c:url value='/js/bootstrap/css/bootstrap.min.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/css/common.css'/>"/>
    <script src="<c:url value='/js/jquery-1.10.2.js'/>"></script>
    <script src="<c:url value='/js/bootstrap/bootstrap.min.js'/>"></script>
</head>
<body style="margin: 0 5px 0 1px;">
    <div class="container-fluid">
        <div class="page-title-breadcrumb">
            <h5 class="title">设备列序图</h5>
        </div>
        <div class="row" style="padding: 0px 5px;">
            <div style="margin: 0 auto; margin-top: 5px; width: 300px;">
                <ul class="circle-box">
                    <li class="circle-green"></li>
                    <li style="display:inline-block;">系统正常</li>
                </ul>
                <ul class="circle-box">
                    <li class="circle-blue"></li>
                    <li style="display:inline-block;">系统异常</li>
                </ul>
                <ul class="circle-box">
                    <li class="circle-red"></li>
                    <li style="display:inline-block;">无数据</li>
                </ul>
            </div>
        </div>
        
        <div class="row">
            <table id="dataTable" class="table table-striped table-bordered">
                <thead>
                    <tr>   
                        <td style="width:60px;">序号</td>
                        <td>站号</td>
			<td>站名</td>
                        <c:set var="index" value="0"/>
                        <c:forEach var="hour" begin="${obj.hourStart}" end="${obj.hourStart + obj.hourCount - 1}" varStatus="status">
                           <td>${obj.hourStart + obj.hourCount - 1 - index}时</td>
                           <c:set var="index" value="${index + 1}"/>
                        </c:forEach>
                    </tr>
		</thead>
		<tbody>
                    <c:forEach var="xefData" items="${obj.xefDataList}" varStatus="status">
                        <tr>
                            <td>${status.index + 1}</td>
                            <td>${xefData.deviceNum}</td>
                            <td>${xefData.deviceName}</td>
                            
                            <c:set var="statuses" value="${xefData.statuses}"/>
                            <c:forEach var="hour" begin="0" end="${obj.hourCount-1}" varStatus="hourVarStatus">
                                <td style="padding: 2px 8px;">
                                    <ul  class="circle-box">
                                        <c:forEach begin="0" end="5" varStatus="minVarStatus">
                                            <c:set var="s" value="${statuses[hourVarStatus.index * 6 + minVarStatus.index]}"/>
                                            <c:choose>
                                                <c:when test="${s eq -2}">
                                                    <li class="circle-gray">${s}</li>
                                                </c:when>
                                                <c:when test="${s eq -1}">
                                                    <li class="circle-red">${s}</li>
                                                </c:when>
                                                <c:when test="${s eq 1}">
                                                    <li class="circle-green">${s}</li>
                                                </c:when>
                                                <c:when test="${s eq 0}">
                                                    <li class="circle-blue">${s}</li>
                                                </c:when>
                                                <c:otherwise>
                                                    <li class="circle-yellow">${s}</li>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </ul>
                                </td>
                            </c:forEach>
                        </tr>
                    </c:forEach>
		</tbody>
            </table>
        </div>

        <div class="row">
            <ul id="pager" class="pagination center-block" style="margin: 0 auto; margin-top: 5px; width: 120px;">
                <c:set var="startPageNumber" value="${obj.pager.startPageNumber}"/>
                <c:set var="endPageNumber" value="${obj.pager.endPageNumber}"/>
                <c:set var="showPageCount" value="${obj.pager.showPageCount}"/>
                <c:set var="pageNumber" value="${obj.pager.pageNumber}"/>
                <c:set var="pageSize" value="${obj.pager.pageSize}"/>
                <c:set var="pageCount" value="${obj.pager.pageCount}"/>
                <li <c:if test="${startPageNumber eq 1}">class="disabled"</c:if> >
                    <c:url var="doListUrl" value="/deviceSeqPlan/doList">
                            <c:param name="pageNo" value="${startPageNumber - pageSize}"/>
                            <c:param name="pager" value="${obj.pagerString}"/>
                    </c:url>
                    <a href="${doListUrl}" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <c:forEach var="curPageNo" begin="${startPageNumber}" end="${endPageNumber}" varStatus="status">
                    <li <c:if test="${pageNumber eq curPageNo}">class="active"</c:if> >
                        <c:url var="doListUrl" value="/deviceSeqPlan/doList">
                            <c:param name="pageNo" value="${curPageNo}"/>
                            <c:param name="pager" value="${obj.pagerString}"/>
                        </c:url>
                        <a href="${doListUrl}">${curPageNo}</a>
                    </li>
                </c:forEach>
                <li <c:if test="${endPageNumber eq pageCount}">class="disabled"</c:if> >
                    <c:url var="doListUrl" value="/deviceManager/doList">
                            <c:param name="pageNo" value="${endPageNumber + 1}"/>
                            <c:param name="pager" value="${obj.pagerString}"/>
                    </c:url>
                    <a href="${doListUrl}" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </ul>
        </div>

    </div>

  </body>
</html>
