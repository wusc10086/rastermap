<%-- 
    Document   : datainfo
    Created on : 2016-5-12, 20:17:42
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String id = request.getParameter("mapid");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../js/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="../js/jquery-easyui/themes/icon.css" rel="stylesheet" type="text/css"/>
        <link href="style.css" rel="stylesheet" type="text/css"/>
        <link href="../js/jquery-easyui/themes/bootstrap/easyui.css" rel="stylesheet" type="text/css"/>
        <script src="../js/jquery-easyui/jquery.min.js" type="text/javascript"></script>
        <script src="../js/jquery-easyui/jquery.easyui.min.js" type="text/javascript"></script>
        <script src="../js/mapquery.js" type="text/javascript"></script>
        <script>
            var mapid;
            var htmlUrl = document.location.host + "/rastermap";
            $(document).ready(function () {
                mapid = '<%=id%>';
                loadMapProductInfo();
            });
            function loadMapProductInfo() {
                $.ajax({
                    type: "GET",
                    url: "../mapdatainfo/mapdatasourceinfo/mapid/" + mapid + "",
                    dataType: "json",
                    processData: true,
                    contentType:"application/x-www-form-urlencode;charset=utf-8",
                    error: function (req, status, error) {

                        if (status == "timeout") {
                            
                            alert("请求超时，请稍后再试!！");
                            return;
                        } else if (status === "error") {

                            alert("数据请求失败，请稍后再试!如果还未解决，请联系管理员！");
                            return;
                        }
                        return;
                    },
                    success: function (obj) {
                        var body = "";
                        //填充内容
                        body += "<tr><td style='text-align: left'>产品名称</td><td>" + obj.mapname + "</td><td>产品代号</td><td>" + obj.mapid + "</td></tr>";
                        body += "<tr><td>数据源</td><td>" + obj.datasource + "</td><td>影像拍摄日期</td><td>" + obj.photodate + "</td></tr>";
                        body += "<tr><td>数据生产时间</td><td>" + obj.productdate + "</td><td>数据量</td><td>" + obj.dataamount + "</td></tr>";
                        body += "<tr><td>坐标系统</td><td>" + obj.cor + "</td><td>数据格式</td><td>" + obj.format + "</td></tr>"
                        body += "<tr><td>数据所有权单位名称</td><td>" + obj.ownership + "<td>数据生产单位名称</td><td>" + obj.productor + "</td></tr>";
                        body += "<tr><td>联系电话</td><td>" + obj.tel + "<td>电子邮箱</td><td>" + obj.email + "</td></tr>";

                        var dec = "<hr>" + obj.description;
                        dec += "<hr>";
                        $("#mapproductinfo").find("tbody").html(body);
                        $("#mapdecription").find("p").html(dec);
//                        var url = "../map/mapimage/" + mapid;
//                        $("#mapsuoluetu").attr("src",url);
                        
                        console.log(mapid);
                        //$("#suoluetu").attr("src", url);
                        addmapimage(mapid);
                        $('.tablelist tbody tr:odd').addClass('odd');
                    }
                });
            }

        </script>

        <title>产品详细信息</title>
    </head>
    <body>
        <div id="content" align="center" style="padding: 5px;">

            <div id="ptinfo">
                <br>
                <label style="font-size: 19px;">制图产品信息</label>
                <table id="mapproductinfo" class="tablelist" style="border:1px solid #ccc;">
                    <thead><th>参数名称</th><th>参数值</th><th>参数名称</th><th>参数值</th></thead>
                    <tbody></tbody>
                </table>
            </div>
            <br>
            <br>

            <img id="mapsuoluetu" alt="产品图"  src="../images/苏州市.png" style="width: 500px;height: 333px">
            <br>
            <br>
            <div><label style="font-size: 19px;">产品简介</label></div>             
            <div id="mapdecription"><p style="font-size: 19px;text-align: left;" ></p></div>
        </div>
    </body>
</html>
