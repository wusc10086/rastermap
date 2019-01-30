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
        <title>变化趋势</title>
        <link href="../css/highlight.css" rel="stylesheet" type="text/css" />
        <link href="../js/leaflet/leaflet.css" rel="stylesheet" type="text/css"/>
        <link href="../css/leaflet.zoomhome.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css"/>
        <link href="../js/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <script src="../js/jquery-1.10.2.js" type="text/javascript"></script>
        <script src="../js/bootstrap/bootstrap.min.js" type="text/javascript"></script>
        <script src="../js/leaflet/leaflet.js" type="text/javascript"></script>
        <script src="../js/leaflet.zoomhome.js" type="text/javascript"></script>
        <script src="../js/leaflet/wicket.js" type="text/javascript"></script>
        <script src="../js/leaflet/leaflet-div-heatmap.js" type="text/javascript"></script>
        <script src="../js/highlight.js" type="text/javascript"></script> 
        <link href="../css/trand.css" rel="stylesheet" type="text/css"/>
        <script src="../js/heatmap/heatmap.js" type="text/javascript"></script>
        <script src="../js/heatmap/leaflet-heatmap.js" type="text/javascript"></script>
        <script src="../js/laydate/laydate.js"></script>
        <script src="../js/echarts.min.js"></script>
        <script src="../js/configure.js" type="text/javascript"></script>
        <script src="../js/provinceQuery.js" type="text/javascript"></script>
        <script src="../js/htmap.js" type="text/javascript"></script>
        <script src="../js/trand.js"></script>
    </head>
    <script >
        $(document).ready(function () {
            document.getElementById("playInt").value = 5;
            document.getElementById("interval").value = 3;
            initTDTmap();
            cityQuery();
            getLastThreeHours();
            playStart();
        });
    </script>
    <body>

        <div id="map" style="z-index:0;overflow: hidden">  

        </div>
        <div id="palyDiv">
            <div id="playIntDiv" class="startPaly" >
                数据间隔：<input id="playInt" type="text" > 分钟
            </div>
            <div id="intervalDiv" class="startPaly" >
                播放间隔：<input id="interval" type="text" > 秒
            </div>
            <div id="playSButDiv" class="BtnPaly" >
                <button type="button" onclick="playStart()">开始</button>
            </div>
        </div>
        <!--图例-->
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
