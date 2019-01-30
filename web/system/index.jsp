<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String contextPath = request.getContextPath();
contextPath = ("/".equals(contextPath) ? "" : contextPath);
%>
<html lang="en">
<head>
	<title>大气电场仪业务管理系统</title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<!-- VENDOR CSS -->
	<link rel="stylesheet" href="../css/bootstrap.min.css">
	<link rel="stylesheet" href="../css/font-awesome.min.css">
	<link rel="stylesheet" href="../css/linearicons/style1.css">
	<!-- MAIN CSS -->
	<link rel="stylesheet" href="../css/main.css">
</head>

<body>
	<!-- WRAPPER -->
	<div id="wrapper">
		<!-- NAVBAR -->
                
                <nav class="navbar navbar-default navbar-fixed-top">
			<div class="brand">
				<a href="index.jsp"><img src="../images/header1.png" alt="HY Logo" class="img-responsive logo"></a>
			</div>
			<div class="container-fluid">
				<div class="navbar-btn">
                                    <button type="button" class="btn-toggle-fullwidth"><i title="导航缩放" class="fa fa-bars"></i></button>
				</div>   <!--收放按钮-->

				<div class="navbar-btn navbar-btn-left">
                                    <div style=" font: 微软雅黑;font-size: 20px; padding-left: 100px; color:rgb(2,44,121);">
                                            <span style="font-weight:bold">欢迎使用大气电场仪业务处理系统</span>
                             <!--       <marquee width="400px" onmouseover="this.stop();" onmouseout="this.start();">
                                            <span style=" font: 微软雅黑;font-size: 10px; padding-left: 100px; color:rgb(2,44,121);">**************************预警</span>
                                        </marquee>  -->
                                    </div>
				</div>
				<div id="navbar-menu">
					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown">
                                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><img src="../images/user.png" alt=""/><span>&nbsp;用户&nbsp;&nbsp;</span> <i class="icon-submenu lnr lnr-chevron-down"></i></a>
                                                    
							<ul class="dropdown-menu">
								<li><a href="<%=contextPath%>/user/reset-passwd2.jsp"><i class="lnr lnr-user"></i> <span>用户信息</span></a></li>
								<li><a href="<%=contextPath%>/login.jsp"> <i class="lnr lnr-envelope"></i> <span>退出</span></a></li>
							</ul>
                                                </li>
					</ul>
				</div>
			</div>
		</nav>
		
		<!-- END NAVBAR -->
		<!-- LEFT SIDEBAR -->
		<div id="sidebar-nav" class="sidebar">
		
			<div class="sidebar-scroll">
				<nav>    
					<ul class="nav">
						<li>
							<a href="#1" data-toggle="collapse" class="collapsed"><i class="fa fa-home"></i> <span>运行监控</span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>	
							<div id="1" class="collapse ">
								<ul class="nav">
                                                                        <li><a href="<%=contextPath%>/system/devicey.jsp" target="content">设备状态图</a></li>
									<li><a href="<%=contextPath%>/runmonitor/listInit" target="content">设备列序图</a></li>
                                                                        
								</ul>
							</div>
						</li>
						<li>
							<a href="#4" data-toggle="collapse" class="collapsed"><i class="fa fa-bell"></i> <span>实时预警</span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>	
							<div id="4" class="collapse ">
								<ul class="nav">
									<li><a href="<%=contextPath%>/system/warning.jsp" target="content">电场实时预警<span class="label label-warning pull-right"></span></a></li>
									<li><a href="<%=contextPath%>/system/trand.jsp" target="content">电场变化趋势</a></li>
                                                                        
								</ul>
							</div>
						</li>
						<li>
                                                    <a href="#2" data-toggle="collapse" class="collapsed"><i class="lnr lnr lnr-map"></i> <span>数据回放</span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>	
							<div id="2" class="collapse ">
								<ul class="nav">
<!--									<li><a href="#">数据回放与分析<span class="label label-danger pull-right">警告</span></a></li>-->
									<li><a href="<%=contextPath%>/efdata/historyefdata" target="content">历史数据查询</a></li>
                                                                        <li><a href="<%=contextPath%>/efdata/dataStatistics" target="content">数据统计分析</a></li>
								</ul>
							</div>
						</li>
						<li>
							<a href="#3" data-toggle="collapse" class="collapsed"><i class="fa fa-cog "></i> <span>系统管理</span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>	
							<div id="3" class="collapse ">
								<ul class="nav">
									<li><a href="<%=contextPath%>/deviceManager/listInit" target="content">设备信息配置</a></li>
									<li><a href="<%=contextPath%>/userManager/listInit" target="content">用户信息管理<span class="label label-primary pull-right"></span></a></li>
								</ul>
							</div>
						</li>
					</ul>
				</nav>
			</div>
		</div>
		<!-- END LEFT SIDEBAR -->
		<!-- MAIN -->
		<div class="main">
                    <div class="main-content">
                   
			<!-- MAIN CONTENT -->
		<div id="maincontent" style="positon:absolute;top:0;overflow: hidden">
			<iframe id="content" name="content"  frameborder="0"
					scrolling="auto" width="100%"  height="600px" src="<%=contextPath%>/system/devicey.jsp">
			</iframe>
		</div>
                       </div>
			<!-- END MAIN CONTENT -->
		</div>
		<!-- END MAIN -->
		<div class="clearfix"></div>
		<footer>
			<div class="container-fluid">
				<p class="copyright">&copy; 2017 北京华云东方探测技术有限公司</p>
			</div>
		</footer>
	</div>
	
	<!-- END WRAPPER -->
	<!-- Javascript -->
	<script src="../js/jquery.min.js"></script><!--导航栏下拉-->
	<script src="../js/bootstrap/bootstrap.min.js"></script>
	<script src="../js/klorofil-common.js"></script> <!--收放导航栏-->
	<script src="../js/jquery.slimscroll.min.js"></script>
</body>

</html>

