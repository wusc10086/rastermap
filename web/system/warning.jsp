<%-- 
    Document   : device
    Created on : 2017-7-19, 22:49:14
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>实施预警</title>

        <link href="../css/highlight.css" rel="stylesheet" type="text/css" />
        <link href="../css/leaflet.zoomhome.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css"/>
        <link href="../js/leaflet/leaflet.css" rel="stylesheet" type="text/css"/>
        <link href="../js/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <script src="../js/jquery-1.10.2.js" type="text/javascript"></script>
        <script src="../js/bootstrap/bootstrap.min.js" type="text/javascript"></script>
        <script src="../js/leaflet/leaflet.js" type="text/javascript"></script>   
        <script src="../js/leaflet.zoomhome.js" type="text/javascript"></script>
        <script src="../js/leaflet/wicket.js" type="text/javascript"></script>
        <script src="../js/leaflet/leaflet-div-heatmap.js" type="text/javascript"></script>
        <script src="../js/highlight.js" type="text/javascript"></script> 
        <link href="../css/warning.css" rel="stylesheet" type="text/css"/>
        <script src="../js/heatmap/hm.js" type="text/javascript"></script>
        <script src="../js/heatmap/lhm.js" type="text/javascript"></script>  
        <script src="../js/configure.js" type="text/javascript"></script>
        <script src="../js/provinceQuery.js" type="text/javascript"></script>
        <script src="../js/htmap.js" type="text/javascript"></script>
        <script src="../js/warning.js" type="text/javascript"></script> 
        <!--        <link href="../css/devicey.css" rel="stylesheet" type="text/css"/>-->
    </head>
    <script >
        $(document).ready(function () {
            initTDTmap();
            cityQuery();
            getWarnings();
            window.setInterval(getWarnings, 1000);
        });
    </script>

    <body >
        <!--        <div class="menu">       
                    <ul>
                        <li><a href="../system/device.jsp"> 设备状态</a></li>
                        <li><a href="#">实时预警</a></li>
                        <li><a href="../system/trand.jsp">变化趋势</a></li>
                    </ul> 
                </div>-->
        <!--        <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
                    <div class="container-fluid">
                        <div class="navbar-header">
                            <a class="navbar-brand" href="#">雷电监测展示系统</a>
                        </div>
                        <div style="float:right;margin-right: 50px">
                            <ul class="nav navbar-nav">
                                <li ><a href="devicey.jsp">设备状态</a></li>
                                <li class="active"><a href="warning.jsp">雷电预警</a></li>
                                <li><a href="trand.jsp">变化趋势</a></li>
                            </ul>
                        </div>
                    </div>
                </nav>-->

        <div id="map" class="mainmap" style="z-index: 0;margin:0px;width:100%;height: 100%;top:0px;position: absolute">
        </div>
        <!--图例-->
        <div class="deviceselects">
            <select id="deviceNum" class="" onchange="getDeviceNum(this.options[this.options.selectedIndex].value)">
                <option value ="0">1分钟</option>
                <option value ="1">5分钟</option>
                <option value ="2">10分钟</option>

            </select>
        </div>
        <div style=" border-radius: 6px;
             color: white;
             position: absolute;
             box-shadow: 0px 0px 10px #4f76ba;
             background-color: rgba(0, 0, 0, 0.52);
             width: 185px;
             height: 236px;
             bottom: 20px;
             right: 5px;
             overflow: hidden">
            <img src="../images/dctl.png" style="
                 height: 236px;
                 width: 185px;
                 "></img>
        </div>
        <div id="tiesnow">
            <label id="datatime">数据时间：</label>
            <p id="datas"></p>
        </div>        
    </body>
    <style>
        div#tiesnow {
            height: 23px;
            position: absolute;
            z-index: 5;
            bottom: 50px;
            margin: 0 auto;
            left: 40%;
            background: #f5f5f5;
            width: 209px;
        }

        label#datatime {
            float: left;
        }
    </style>
</html>
