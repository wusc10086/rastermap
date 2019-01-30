<%-- 
    Document   : historyefdata-table
    Created on : 2017-8-8, 14:42:12
    Author     : wuyiwei
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%> 
<%@ page import="java.util.*" %>
<%@ page import="org.rasterdb.domain.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--  
<div class="row" id="tablePane">
--%>
    <table id="dataTable" class="table table-striped table-bordered">
        <thead>
            <tr>   
                <td style="width:60px;">序号</td>
                <td>站号</td>
                <td >站名</td>
                <td  >时间日期</td>
                <td >平均电场强度(KV/m)</td>
                
            </tr>
        </thead>
        <tbody>
        <c:forEach var="efData" items="${obj.efDataList}" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${efData.deviceid}</td>
                <td>${efData.deviceName}</td>
                <td>${efData.sendTime}</td>
                <td>${efData.average/100}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<div class="row" id="pagerPane">
    <ul id="pager" class="pagination center-block" style="margin: 0 auto; margin-top: 5px; width: 240px;">
        <c:set var="startPageNumber" value="${obj.pager.startPageNumber}"/>
        <c:set var="endPageNumber" value="${obj.pager.endPageNumber}"/>
        <c:set var="showPageCount" value="${obj.pager.showPageCount}"/>
        <c:set var="pageNumber" value="${obj.pager.pageNumber}"/>
        <c:set var="pageSize" value="${obj.pager.pageSize}"/>
        <c:set var="pageCount" value="${obj.pager.pageCount}"/>
        <c:set var="totalCount" value="${obj.pager.totalCount}"/> 
        <li <c:if test="${startPageNumber eq 1}">class="disabled"</c:if> >
        <c:url var="doListUrl" value="/efdata/historyefdata-table">
            <c:param name="pageNo" value="${startPageNumber - pageSize}"/>
            <c:param name="pager" value="${obj.pagerString}"/>
        </c:url>
        <a  aria-label="Previous" data-pageNo="${startPageNumber - pageNumber}" data-totalCount="${totalCount}" <c:if test="${startPageNumber eq 1}"> data-disabled="1"</c:if> >
            <span aria-hidden="true">&laquo;</span>
        </a>
        </li>
        <c:forEach var="curPageNo" begin="${startPageNumber}" end="${endPageNumber}" varStatus="status">
            <li <c:if test="${pageNumber eq curPageNo}">class="active"</c:if> >
            <c:url var="doListUrl" value="/efdata/historyefdata-table">
                <c:param name="pageNo" value="${curPageNo}"/>
                <c:param name="pager" value="${obj.pagerString}"/>
            </c:url>
            <a  data-pageNo="${curPageNo}"  data-totalCount="${totalCount}">${curPageNo}</a>
            </li>
        </c:forEach>
        <li <c:if test="${endPageNumber eq pageCount}">class="disabled"</c:if> >
        <c:url var="doListUrl" value="/efdata/historyefdata-table">
            <c:param name="pageNo" value="${endPageNumber + 1}"/>
            <c:param name="pager" value="${obj.pagerString}"/>
        </c:url>
        <a aria-label="Next" data-pageNo="${endPageNumber + 1}"  data-totalCount="${totalCount}" <c:if test="${endPageNumber eq pageCount}">onclick="return false;"</c:if>>
            <span aria-hidden="true">&raquo;</span>
        </a>
        </li>
    </ul>
<%--
</div> 
--%>

