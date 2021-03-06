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
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <title>设备列表</title>
    <link rel="stylesheet" href="<c:url value='/js/bootstrap/css/bootstrap.min.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/css/common.css'/>"/>
    <script src="<c:url value='/js/jquery-1.10.2.js'/>"></script>
    <script src="<c:url value='/js/bootstrap/bootstrap.min.js'/>"></script>
    <script src="<c:url value='/js/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js'/>" type="text/javascript"></script>
    <script src="<c:url value='/js/bootstrap-datetimepicker/locales/bootstrap-datetimepicker.zh-CN.js'/>" type="text/javascript"></script>
</head>
<body style=" width:100%;">
        <div class="page-title-breadcrumb">
            <h5 class="title">设备信息</h5>
        </div>
       <div class="container-fluid">
        <div class="row" style="padding: 8px 5px;">
            <form class="form-inline" action="<c:url value='/deviceManager/searchInit'/>">
                <div class="form-group">
                    <label for="cityid">&nbsp;&nbsp;地市</label>
                    <select name="cityid" class="form-control" id="cityid">
                        <option value="">全部</option>
                        <c:forEach var ="city" items="${obj.cities}">
                            <option value="${city.cityid}" <c:if test="${city.cityid eq obj.pager.queryForm.cityid}">selected</c:if> >${city.name}</option>
                        </c:forEach>
                    </select>
                </div>
                &nbsp;&nbsp;
                <button type="submit" class="btn btn-default">查询</button>
                &nbsp;&nbsp;
                <c:url var="addInit" value="/deviceManager/addInit">
                    <c:param name="pager" value="${obj.pagerString}"/>
                </c:url>
                <a class="btn btn-default" href="${addInit}" role="button">添加</a>
                &nbsp;&nbsp;
                <c:url var="batchUploadInit" value="/deviceManager/batchUploadInit">
                    <c:param name="pager" value="${obj.pagerString}"/>
                </c:url>
                <a class="btn btn-default" href="${batchUploadInit}" role="button">批量添加</a>
            </form>
        </div>
        
        <div class="row">
            <table id="dataTable" class="table table-striped table-bordered">
                <thead>
                    <tr>   
                        <td style="width:60px;">序号</td>
                        <td>站号</td>
			<td>站名</td>
			<td>纬度</td>
                        <td>经度</td>
                        <td>拔海高度</td>
                        <td>地市</td>
                        <td>制造商</td>
                        <td>安装时间</td>
                        <td style="width:100px;"><b>操作</b></td>
                    </tr>
		</thead>
		<tbody>
                    <c:forEach var="device" items="${obj.deviceList}" varStatus="status">
                        <tr>
                            <td>${status.index + 1}</td>
                            <td>${device.num}</td>
                            <td>${device.deviceName}</td>
                            <td>${device.latitude}</td>
                            <td>${device.longitude}</td>
                            <td>${device.elevation}</td>
                            <td>${device.cityName}</td>
                            <td>${device.manufaceture}</td>
                            <td>${device.installTime}</td>
                            <td>
                                <c:url var="editInitUrl" value="/deviceManager/editInit">
                                    <c:param name="num" value="${device.num}"/>
                                    <c:param name="pager" value="${obj.pagerString}"/>
                                </c:url>
                                <a href="${editInitUrl}">编辑</a>
                                |
                                <c:url var="deleteUrl" value="/deviceManager/delete">
                                    <c:param name="num" value="${device.num}"/>
                                    <c:param name="pager" value="${obj.pagerString}"/>
                                </c:url>
                                <%-- 
                                <a href="#" data-href="${deleteUrl}" data-toggle="modal" data-target="#confirm-delete">删除</a>
                            --%>
                                <a href="${deleteUrl}" onclick="var b = window.confirm('您真的要删除此条记录吗？'); return b;">删除</a>
                            </td>
                        </tr>
                    </c:forEach>
		</tbody>
                <tfoot>
                    
                    
                </tfoot>
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
                                    <c:url var="doListUrl" value="/deviceManager/doList">
                                        <c:param name="pageNo" value="${startPageNumber - pageSize}"/>
                                        <c:param name="pager" value="${obj.pagerString}"/>
                                    </c:url>
                                        <a href="${doListUrl}" aria-label="Previous" <c:if test="${startPageNumber eq 1}">onclick="return false;"</c:if>>
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                                <c:forEach var="curPageNo" begin="${startPageNumber}" end="${endPageNumber}" varStatus="status">
                                    <li <c:if test="${pageNumber eq curPageNo}">class="active"</c:if> >
                                        <c:url var="doListUrl" value="/deviceManager/doList">
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
                                    <a href="${doListUrl}" aria-label="Next" <c:if test="${endPageNumber eq pageCount}">onclick="return false;"</c:if>>
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                            </ul>
                </div>

    </div>
    
                        
                        
                        
  <%--                  
   <!-- 删除对话框 -->
   <div class="modal fade" id="confirm-delete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
       <div class="modal-dialog">
           <div class="modal-content">
               <div class="modal-header">
                   <i class="fa fa-question-circle"></i>请确认
               </div>
               <div class="modal-body">
                   您真的要删除该记录吗？
               </div>
               <div class="modal-footer">
                   <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                   <a class="btn btn-danger btn-ok">删除记录</a>
               </div>
           </div>
       </div>
   </div>
   <script>
       $('#confirm-delete').on('show.bs.modal', function(e) {
            $(this).find('.btn-ok').attr('href', $(e.relatedTarget).data('href'));
        });
   </script>
  --%> 
   
  </body>
</html>
