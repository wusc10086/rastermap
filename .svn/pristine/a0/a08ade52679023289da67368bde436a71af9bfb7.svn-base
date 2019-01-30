<%-- 
    Document   : datainfo
    Created on : 2016-5-12, 20:17:42
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String id = request.getParameter("sno");
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
        <script>
            var sno;
            $(document).ready(function () {
                sno = '<%=id%>';
                loadDataSourceInfo();
                loadProductInfo();
            });

            function loadDataProductionInfo() {
                $.ajax({
                    type: "GET",
                    url: "../datainfo/dataproduct/sno/" + sno + "",
                    dataType: "json",
                    processData: true,
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
                        //先清空内容


                    }
                });
            }

            function loadDataSourceInfo() {
                $.ajax({
                    type: "GET",
                    url: "../datainfo/datasourceinfo/sno/" + sno + "",
                    dataType: "json",
                    processData: true,
                    error: function (req, status, error) {

                        if (status == "timeout") {

                            alert("请求超时，请稍后再试!！");
                            return;
                        } else if (status === "error") {
                            alert("数据请求失败，请稍后再试!如果还未解决，请联系管理员！");
                            return;
                        }
                        return;
                    }
//                    success: function (obj) {
//                        var body = "";
//                        //填充内容
//                         body+="<tr><td>SN</td><td>"+obj.sn+"</td><td>卫星名称</td><td>"+obj.satename+"</td><td>全色影像传感器类型</td><td>"+obj.pbandsensortype+"</td></tr>";
//                         body+="<tr><td>全色卫星影像分辨率</td><td>"+obj.sateresolution+"</td><td>全色卫星影像轨道号</td><td>"+obj.pbandorbitcode+"</td><td>全色卫星影像获取时间</td><td>"+obj.pbanddate+"</td></tr>";
//                         body+="<tr><td>多光谱影像传感器类型</td><td>"+obj.multibandsensortype+"</td><td>多光谱波段数量</td><td>"+obj.multibandnu+"</td><td>多光谱波段名称</td><td>"+obj.multibandname+"</td></tr>";
//                         body+="<tr><td>影像色彩模式</td><td>"+obj.imgcolormodel+"</td><td>像素位数</td><td>"+obj.pixelbits+"</td><td>整景数据量大小</td><td>"+obj.imgsize+"</td></tr>";
//                         body+="<tr><td>多光谱卫星影像分辨率</td><td>"+obj.multibandresolution+"</td><td>多光谱卫星影像轨道号</td><td>"+obj.multibandorbitcode+"</td><td>多光谱卫星影像获取时间</td><td>"+obj.multibanddate+"</td></tr>";
//
//                        body += "<tr><td>卫星影像数据质量评价</td><td>" + obj.sateimgquality + "</td><td></td><td></td><td></td><td></td></tr>";
//
//                        $("#datasourceinfo").find("tbody").html(body);
////                         $("#datasourceinfo").datagrid();
//                        $('.tablelist tbody tr:odd').addClass('odd');
//                    }
                });
            }
            function loadProductInfo() {
                $.ajax({
                    type: "GET",
                    url: "../datainfo/productinfo/sno/" + sno + "",
                    dataType: "json",
                    processData: true,
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
                        //先清空内容
//                        $("#productinfo").append("<option>----请选择(县/区)----</option>");

                        var body = "";
                        //填充内容
                        body += "<tr><td>图号</td><td>" + obj.NumImage + "</td><td>数据名称</td><td>" + obj.dataName + "</td><td>卫星类型</td><td>" + obj.SataliteType + "</td></tr>";
                        body += "<tr><td>接边质量评价</td><td>" + obj.EdgeQuality + "</td><td>数据质量总评价</td><td>" + obj.DataOverallQuality + "<td>DEM源</td><td>" + obj.DEMSource + "</td></tr>";
                        body += "<tr><td>影像拍摄日期</td><td>" + obj.ImageDate + "</td><td>数据生产时间</td><td>" + obj.ProductDate + "</td>" + "<td>位深</td><td>" + obj.Deep + "</td></tr>";
                        body += "<tr><td>遥感传感器类型</td><td>" + obj.SensorType + "</td><td>波段选择</td><td>" + obj.BandSelection + "<td>影像地面分辨率</td><td>" + obj.Resolution +  "</td></tr>";
                        body += "<tr><td>数据量</td><td>" + obj.DataAmount + "</td><td>分幅经度范围</td><td>" + obj.DatelongitudeRange + "</td><td>分幅纬度范围</td><td>" + obj.DataLatitudeRang + "</td></tr>";
                        body += "<tr><td>数据所有权单位</td><td>" + obj.DataOwnership + "</td><td>数据管理单位</td><td>" + obj.management + "</td><td>数据生产单位</td><td>" + obj.productor + "</td></tr>";
//                        

                        var dec = "<hr>" + "&nbsp; &nbsp;&nbsp;&nbsp;"
                                + "该产品由中科九度（北京）空间信息技术有限责任公司生产加工。该公司是中国科学院电子学研究所的产业化公司，公司秉承科学院的创新文化与进取精神，先后研发出空间信息大数据承载服务平台、遥感信息工程化处理与应用平台、政务信息资源共享服务平台等基础平台，研究成果已在国防军工、数字城市、海洋监测、环境保护、气象水文等领域得到有效验证与市场应用。" 
                                +"<br>&nbsp; &nbsp;&nbsp;&nbsp;"
                                + "该数据产品联系购买方式，电话：58887209，邮箱：xuch@geodo.cn。";
                        //var dec="<br>"+obj.description;
                        dec += "<hr>";
                        $("#productinfo").find("tbody").html(body);
                        $("#decription").find("p").html(dec)
                        var url = "../query/image/" + sno;
                        console.log(url);
                        $("#suoluetu").attr("src", url);
//                         $("#productinfo").datagrid();
                        $('.tablelist tbody tr:odd').addClass('odd');
                    }
                });
            }
        </script>

        <title>影像详细信息</title>
    </head>
    <body>
        <div id="content" align="center" style="padding: 5px;">

            <!--        <div id="dsinfo">
                           <label style="font-size: 19px;">原始数据信息</label>
                        <table id="datasourceinfo" class="tablelist" style="border:1px solid #ccc;">
                            <thead><th>参数名称</th><th>参数值</th><th>参数名称</th><th>参数值</th><th>参数名称</th><th>参数值</th></thead>
                        <tbody></tbody>
                        </table>
                    </div>-->
            <div id="ptinfo">

                <label style="font-size: 19px;">影像产品信息</label>
                <table id="productinfo" class="tablelist" style="border:1px solid #ccc;width: 99.99%">
                    <thead><th>参数名称</th><th>参数值</th><th>参数名称</th><th>参数值</th><th>参数名称</th><th>参数值</th></thead>
                    <tbody></tbody>
                </table>
            </div>
            <br>
            <br>
            <div>
                <img  alt="产品图" id="suoluetu" src="../images/苏州市.png" style="width: 500px;height: 333px">  
                <span>
                </span>
            </div>

            <br>
            <div><label style="font-size:19px;">产品简介</label></div> 
            <div id="decription"><p style="font-size: 17px;text-align: left;width: 99.99%" ></p></div>
        </div>
    </body>
</html>
