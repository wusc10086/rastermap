<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html >
<head>

<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">


    

<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>大气电场仪业务系统</title>

<link rel="stylesheet"  href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"  href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<link href="./css/leftmenu.css" rel="Stylesheet" type="text/css" />
<link href="./css/topleft.css" rel="Stylesheet" type="text/css" />
<style type="text/css">

 body 
  {  
  margin: 0; 
  padding: 0;  
  border: 0;  
  overflow: hidden;  
  height: 100%; 
  max-height: 100%;  
  position: absolute; 
  left:0px; 
  right:0px; 
  top:0px; 
  bottom:0px; 
  overflow:auto; 
 }  
 
  body 
  {  
margin: 0; 
  padding: 0;  
 border: 0;  
  overflow: hidden; 
  height: 100%;  
  max-height: 100%;  
 } 
</style>
<script language="javascript" type="text/javascript">
function removeScroll() { document.getElementById("content").scrolling="no"; } 

</script>


</head>
<body>
<form id="form1"  runat="server" >
<div style="width: 50%">
	<canvas id="canvas" height="450" width="600"></canvas>
	</div>
<form id="form1" runat="server">
<div id="framecontentLeft" style="positon:absolute;top:50px">
<div class="menu">
<li><a href="http://localhost:8084/rastermap/system/homeMap.jsp" target="content">首页</a></li>
</ul>  
</ul>
<h4>运行监控</h4>
<ul>
 <ul>
<li><a href="" target="content">序列图</a></li>
<li><a href="" target="content">状态图</a></li>
</ul>
<h4>实时预警</h4>
<ul>
<li><a href="http://localhost:8084/rastermap/system/device.jsp" target="content">电场实时预警</a></li>
<li><a href="" target="content">电场变化趋势</a></li>
<li><a href="" target="content">站点电场变化</a></li>
</ul>
<h4>数据回放</h4>
<ul>
<li><a href="" target="content">历史数据查询</a></li>
<li><a href="" target="content">数据统计分析</a></li>
</ul>
</div>
</div>
<div id="framecontentTop" style="positon:absloute;top:0px;height:50px">
<div style="text-align: center;positon:relative;top:0px;height:50px;padding-bottom:0px">
<div style="position:absolute;top:0px;margin-left:88%">
<script language="javascript">
    function quits(){
     if(confirm("真的要退出系统吗?")){
    	 //把请求提交到controller  由controller来跳转页面
     	window.location.href="logout.jsp"
     }
    }  
</script> 
欢迎您,<%=session.getAttribute("user")%>
<a  href="login.jsp"> 返回主页</a>
</div>
<h2 style="position:absolute;top:-10px;left:40%">

</h2>

</div>
</div>
<div id="maincontent" style="positon:absolute;top:50px">
<iframe id="content" name="content"  frameborder="0"
scrolling="auto" width="100%"  height="100%" src="http://localhost:8084/rastermap/system/homeMap.jsp">

</iframe>

</div>


</form>
</body>
</html>
