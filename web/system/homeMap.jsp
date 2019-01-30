<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
 <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

 %>
  
<html>
    <head>
     <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="renderer" content="webkit|ie-stand|ie-comp">
        <!--<link rel="stylesheet" href="../js/ol3/ol.css"/>-->
        <link rel="stylesheet" href="../css/table.css"/>
        <link href="../js/leaflet/leaflet.css" rel="stylesheet" type="text/css"/>
        <link href="../js/layer/skin/layer.css" rel="stylesheet" type="text/css"/>
        <link href="../js/layer/skin/layer.ext.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="../css/layout.css"/>
        <link href="../js/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="../js/jquery-easyui/themes/icon.css" rel="stylesheet" type="text/css"/>
        <link href="../js/jquery-easyui/themes/bootstrap/easyui.css" rel="stylesheet" type="text/css"/>
        <link href="../css/fakeLoader.css" rel="stylesheet" type="text/css"/>
        <link href="../css/leaflet.contextmenu.css" rel="stylesheet" type="text/css"/>
        <link href="../css/tishipanel.css" rel="stylesheet" type="text/css"/>       
        <script src="../js/jquery-1.10.2.js" type="text/javascript"></script>
        <script src="../js/ajaxupload.js" type="text/javascript"></script>
        <script src="../js/jquery-easyui/jquery.easyui.min.js" type="text/javascript"></script>
        <script src="../js/jquery-easyui/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
        <script src="../js/bootstrap/bootstrap.min.js" type="text/javascript"></script>
        <script src="../js/spin.js" type="text/javascript"></script>
        <script src="../js/mytools/admin.js" type="text/javascript"></script>
        <script src="../js/layer/layer.js" type="text/javascript"></script>
         <script src="../js/leaflet/leaflet.js" type="text/javascript"></script>
        
        <script src="../js/leaflet/Leaflet.ImageOverlay.Rotated.js" type="text/javascript"></script>
       <script src="../js/leaflet/leaflet.contextmenu-src.js" type="text/javascript"></script>
        <script src="../js/leaflet/wicket.js" type="text/javascript"></script> 
        
         <script src="../js/heatmap/heatmap.js" type="text/javascript"></script>
         <script src="../js/heatmap/leaflet-heatmap.js" type="text/javascript"></script>         
        <script src="../js/mapobj.js" type="text/javascript"></script>
        
        <script src='../js/layout.js'></script>
        <script src='../js/rightlayout.js'></script>
        <script src='../js/adrightlayout.js'></script>        
        <script src="../js/mapquery.js" type="text/javascript"></script>
        <script src='../js/query.js'></script>
        <script src="../js/tool.js" type="text/javascript"></script>
        <script src="../js/initJS.js" type="text/javascript"></script>
        <script src="../js/mytools/wkt-wgs84-gcj.js" type="text/javascript"></script>
        <script src="../js/mytools/wgs84-gcj02.js" type="text/javascript"></script>
        <script src="../js/mytools/moasic.js" type="text/javascript"></script>
  <script src="../js/yanue.pop.js" type="text/javascript"></script>
        <script src="../js/heatlayer.js" type="text/javascript"></script>
        <script src="../js/rsmap.js" type="text/javascript"></script>        
   <link href="../css/pop.css" rel="stylesheet" type="text/css"/>
        <link href="../css/leftmenu.css" rel="Stylesheet" type="text/css" />
        <link href="../css/topleft.css" rel="Stylesheet" type="text/css" />
    
        <script>
            $(document).ready(function () {
//                var privalte = '<%=session.getAttribute("roles")%>';
//                var username='<%=session.getAttribute("user")%>';
//                checkPrivalte(privalte,username);
//                initTabs();
//                initMap();
                initTDTmap();

//                $("#uploadbtn").on("click", function () {
//                  
//                    vectorQuery();
//
//                });
//                $("vectorqueryclean").on("click", function () {
//                    cleanMarkers();
//                    cleanImgLayers();
//                });
//                //镶嵌
//                $("#MoasicDivbtn").on("click", function () {
//                    Moasic();
//                });
               //制图查询事件
//               $("#mapquerybtn").on("click", function () {
//                   mapproductquery();
//               })
//              $("#MoasicDivbtn").hide();
              
           });
        </script>
        <script>
//        function testssss(){
//            alert("sss\n\ ");
//        }
        </script>
        <title>大气电场仪业务处理系统</title>
    </head>
    <body>
<!--        <div id="header">
            <div id="framecontentTop" style="positon:absloute;top:0px;height:80px;">
            <div id="logo" style="position: relative">
                <image src="../images/logo2.png"/>
                <image src=" "/>
                <div style="background: url('../images/banner.png');position: relative; float: left;top:0;left: 0;height: 80px;width: 100%;"/>
                </div>
            <div id="search">
                                <input  type="text"id="keywords" style="width: 320px; height: 30px;margin-top:0;position: relative; font-size: 14px;">
                                <button id="searchbuton" class="button blue" >搜索</button>
                <img src="images/systemname.png" style="bottom: 5px;position: relative"/>
            </div>
            <div id="utils">
                            
                              <div id="usertop" style="top:2px;right: 5px;position: absolute;font-size: 12px;">
                                    <label class="user">欢迎：</label>
                                  <label id="names"><%=session.getAttribute("user")%></label><label>&nbsp;|&nbsp;</label>
                                      <a href="#">登录</a>
                                    <a href="http://localhost:8084/rastermap/login.jsp" onclick="logout($('#username').text())">安全退出</a>
                                </div>
                <div id="tab" style="top:28px;right: 3px;position: absolute">
                    <ul class="nav nav-pills" role="tablist">
                        <li role="presentation" class="active" style="height: 50px;"><a href="#">地图首页</a></li>
                        
                        <li id='admindcbtn' role="presentation" class="active" style="height: 50px;">&nbsp;</a></li>
                        
                    </ul>
                </div>
            </div>
         </div> 
        </div>  -->
<!--   <div id="shadow_h" style="display: block;"></div>-->
<!--        <div id="left" >
            <div id="shadow_v" style="display: block;"></div>
            <div id="imageTab" class="easyui-tabs" data-options="tabWidth:100,tabHeight:60,padding:20" style="top:1px;width:330px;height:100%;position: absolute">
              <div title="模块页面" style="padding: 10px">
                        <div title="运行监控" style="padding:0px;font-size: 14px;">
                          <center>
                            <table>
                                <tr>
                                    <td><a href="" target="content">状态图</a></td>
                                </tr>
                                 <tr>
                                </tr>
                                 <tr>
                                </tr>
                                <tr>
                                   <td><a href="" target="content">序列图</a></td>
                                </tr>
                            </table>
                          </center>
                        </div>
                  <div id="yunxing" class="easyui-tabs" style="top:1px;width:315px;height:auto;padding-left: 0px;position: relative">
                        <div title="运行监控" style="padding:0px;font-size: 14px;">
                            
                        </div>
                    </div>
                          
             </div>
                <div title="影像数据" style="padding: 10px">
                    <br>
                    <div id="mytab" class="easyui-tabs" style="top:1px;width:315px;height:auto;padding-left: 0px;position: relative">
                        <div title="行政查询" style="padding:0px;font-size: 14px;">
                            <br>
                            <div id="city" style="position: relative;">
                                <table id="citytable" class="bordered" border ="0">
                                    <tbody>
                                        <tr>
                                            <td style='text-align: left'>省  /市 /自治区  :</td>

                                            <td style='text-align: left;'>
                                                <select id='sheng' name="sheng" style="width: 155px;">
                                                    <option style="text-align: center;">--------省(必选)--------</option>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td style='text-align: left'>地 &nbsp;级&nbsp; 市 &nbsp; /区:</td>
                                            <td style='text-align: left;'>
                                                <select id='shi' name="shi" style="width: 155px;">
                                                    <option id="000000">--------市--------</option>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td style='text-align: left'>县  级  市/县/区:</td>
                                            <td style='text-align: left;'>
                                                <select id='xian' name="xian" style="width: 155px;">
                                                    <option id="000001">--------县--------</option>
                                                </select>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                            <hr/>
                            <div id="mytab2" class="easyui-tabs" style="top:1px;width:310px;height:auto;position: relative;padding-left: 0px">

                                <div id='cgqcx' title="查询方式1" style="padding:5px">
                                    <div id='parm' style='position: relative;margin-top: 10px;'>
                                        <table id="parmtable2" class="bordered" border="0" align="center" >
                                            <tbody>
                                                <tr>
                                                    <td style='text-align:left;width: 113px'>参&nbsp;&nbsp;数&nbsp;一&nbsp;&nbsp;:</td>
                                                    <td style='text-align: left;'>
                                                        <select  id='wxmc' name="chuanganqi" style="width: 155px;">
                                                            <option>-------请选择-------</option>
                                                        </select>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td style='text-align:left;width: 113px'>参&nbsp;&nbsp;数&nbsp;二&nbsp;&nbsp;:</td>
                                                    <td style='text-align: left;'>
                                                        <select id='wxscms' name="yun" style="width: 155px;">
                                                            <option>-------请选择-------</option>
                                                            <option>RGB真彩色</option>
                                                        </select>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td style='text-align: left;'>参&nbsp;&nbsp;数&nbsp;三&nbsp;&nbsp;:</td>
                                                    <td style='text-align: left;'>
                                                        <select id='demsource' name="demsource" style="width: 155px;">
                                                            <option>-------请选择-------</option>

                                                        </select>
                                                    </td>
                                                </tr>

                                                <tr>
                                                    <td style='text-align: left;'>生&nbsp;&nbsp;产&nbsp;时&nbsp;&nbsp;间:</td>
                                                    <td style='text-align: left;'>
                                                        <input id="wxstartime" class="easyui-datebox" data-options="onSelect:onWxStartSelect" style="height:25px;width:120px;">
                                                        至
                                                        <input id="wxendtime" class="easyui-datebox" data-options="onSelect:onWxEndSelect"  style="height:25px;width:120px;" >
                                                    </td>

                                                </tr>
                                                                                                <tr>
                                                                                                    <td>云量:</td>
                                                                                                    <td style='text-align: left;'>
                                                                                                        <=<input type="text" id="yl"/>(0-100)
                                                                                                    </td>
                                                                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>

                                <div id="sxcx" title="属性查询" style="padding:5px">
                                    <div id='parm2' style='position: relative;margin-top: 10px;'>
                                        <table id="parmtable" class="bordered" border="0" align="center" >

                                            <tbody>
                                                <tr>
                                                    <td style='text-align:left;width: 113px'>参&nbsp;&nbsp;数&nbsp;一&nbsp;&nbsp;:</td>
                                                    <td style='text-align: left;'>
                                                        <select id='sjlx' name="leixing" style="width: 155px;">
                                                            <option>-------请选择-------</option>
                                                            <option>1</option>
                                                            <option>2</option>
                                                        </select>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td style='text-align:left;width: 113px'>参&nbsp;&nbsp;数&nbsp;一&nbsp;&nbsp;::</td>
                                                    <td style='text-align: left;'>
                                                        <select id="scdw" name="leixing" style="width: 155px;">
                                                            <option>-------请选择-------</option>
                                                            <option>1</option>
                                                            <option>2</option>
                                                        </select>
                                                    </td> 
                                                </tr>
                                                <tr>
                                                    <td style='text-align:left;width: 113px'>参&nbsp;&nbsp;数&nbsp;一&nbsp;&nbsp;:</td>
                                                    <td style='text-align: left;'>
                                                        <select id='scms' name="yun" style="width: 155px;">
                                                            <option>-------请选择-------</option>
                                                            <option id="M">多光谱</option>
                                                            <option id="P">全色</option>
                                                        </select>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td style='text-align:left;width: 113px'>参&nbsp;&nbsp;数&nbsp;一&nbsp;&nbsp;:</td>
                                                    <td style='text-align: left;'>
                                                        <select id='fbl' name="yun" style="width: 155px;">
                                                            <option>-------请选择-------</option>
                                                            <option id="5">大于0.5米</option>
                                                            <option id="10">大于1米</option>
                                                            <option id="20">大于2米</option>
                                                            <option id="6">大于6米</option>
                                                        </select>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td style='text-align:left;width: 113px'>生&nbsp;&nbsp;产&nbsp;时&nbsp;&nbsp;间:</td>
                                                    <td style='text-align: left;'>

                                                        <input id="startime" class="easyui-datebox" data-options="onSelect:onStartSelect" style="height:25px;width: 120px;"/>
                                                        至
                                                        <input id="endtime" class="easyui-datebox"  data-options="onSelect:onEndSelect" style="height:25px;width: 120px;" />
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>

                            <hr/>
                            <br>
                            <label>当前最新全色数据采集时间:</label> <label id ="pband" class="time"></label>
                            <label>当前最新多光谱数据采集时间:</label> <label id="rgb" class="time"></label>
                            <br>
                            <br>
                            <div id='query' align="center">
                                <input id='querybtn' type="button" class="btn btn-success" value="开始查询" />
                            </div>
                            <div id="spinprocess" align="center" style="position: relative;"></div>
                            <br>
                            <br>

                        </div>

                        <div title="矢量查询" style="padding:2px">

                            <div id="fileselect" style="position: relative">
                                <div class="panel" style="display: block;width: 300px;">
                                    <div class="panel-header" style="width: 300px;">
                                        <div class="panel-title">上传文件</div>
                                    </div>
                                    <div class="easyui-panel panel-body" title="" style="padding:30px 12px 50px; width:300px;">
                                        <div>
                                            <form action="" method="post"  target="upload" enctype="multipart/form-data">
                                                <input id="shpfile" type="file" name="shpfile" value="" width="100" accept='application|.gif' onChange="handleFiles(this.files)"/> 

                                                <button id="uploadbtn" type="commit" class='btn btn-success' value="上传文件" style='margin-top:15px;float:right;height: 30px;text-align:auto;'>执行查询</button>
                                                                                                                <div style="margin-bottom:20px">
                                                                                                                         <input class="easyui-filebox filebox-f textbox-f" data-options="prompt:'选择文件'" style=" margin-left: 2px; width: 270px; display: none; height:30px;position: absolute">
                                                                                                                    </div>
                                                                                                                    <div>
                                                                                                                         <button class="btn btn-success" id='CXfile' style='margin-left:35px; margin-bottom: 0px;'onClick="handleFiles(this.files)">上传</button>
                                                                                                                         <button class="btn btn-success" id='cx' style='margin-left:70px;'onClick="alert()">查询</button>
                                                                                                                   </div>    
                                            </form>
                                        </div>

                                    </div>
                                </div>
                                <br>
                                <br>
                                <br>
                                <br>
                                <br>
                                 提示框
                                <div class="panel" style="display: block; width: 300px;">
                                    <div class="panel-header" style="width: 300px;">
                                        <div class="panel-title">提示</div>
                                        <div class="panel-tool"></div>
                                    </div>
                                    <div id="p" class="easyui-panel panel-body" title="" style="padding: 10px; width: 300px; height: 151px;">
                                        <p style="font-size:14px">文件要求:</p>
                                        <ul>
                                            <li>上传shp文件必须为压缩的.zip或者.kml文件</li>
                                            <li>文件的坐标系统为4326坐标系</li>
                                        </ul>
                                    </div>
                                </div>
                                <iframe name="upload" style="display:none"></iframe>
                            </div> 
                            <br>
                        </div>

                        <div title="结果列表" style="padding:2px;height: 100%" data-options="fit:true">
                            <div id='result' style='width: 100%;height: 80%;position:relative;top:1px;left:1px;'>
                                <table  id="infotable"  class="easyui-datagrid" title="查询结果" style="width: 100%;height:500px;"
                                        data-options="singleSelect:false,collapsible:true, fitColumns:false, striped:true">
                                    <thead>
                                        <tr>
                                            <th data-options="field:'mzt',width:60,formatter:imgFormatter">拇指图</th>
                                            <th data-options="field:'sno',width:120,align:'center'">图号</th>
                                            <th data-options="field:'cgq',width:80,align:'center'">卫星名称</th>
                                            <th data-options="field:'date',width:80,align:'center'">生产时间</th>
                                            <th data-options="field:'preview',width:80,checkbox:true">预览</th>
                                        </tr>
                                    </thead>
                                </table>
                                <div id="infolable2"></div>
                            </div>
                            <hr/>
                            <div id='MoasicDiv' align='center'>
                                <button id="MoasicDivbtn" class='btn btn-success'style="margin-top: 34px">开始镶嵌</button>

                            </div>
                        </div>
                    </div>
                </div>
                <div title="制图产品" style="padding: 10px">
                    <div id="product_result" class="easyui-tabs" style="top:1px;width:315px;height:auto;padding-left: 0px;position: relative">
                        <div title="行政查询" style="padding:0px;font-size: 14px;">
                            <br>
                            <div id="city" style="position: relative;">
                                <table id="citytable" class="bordered" border ="0">
                                    <tbody>
                                        <tr>
                                            <td style='text-align: left'>省  /市 /自治区:</td>
                                            <td style='text-align: left;'>
                                                <select id='mapprovince' name="mapprovince" style="width: 155px;">
                                                    <option style="text-align: center;">-------请选择--------</option>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td style='text-align: left'>地 &nbsp;级&nbsp; 市 &nbsp; /区:</td>
                                            <td style='text-align: left;'>
                                                <select id='mapcity' name="mapcity" style="width: 155px;">
                                                    <option>-------请选择--------</option>
                                                </select>
                                            </td>
                                        </tr>
                                                                                <tr>
                                                                                    <td style='text-align: left'>县  级  市/县/区:</td>
                                                                                    <td style='text-align: left;'>
                                                                                        <select id='xian' name="xian" style="width: 155px;">
                                                                                            <option>--县--</option>
                                                                                        </select>
                                                                                    </td>
                                                                                </tr>
                                    </tbody>
                                </table>
                            </div>
                            <hr/>
                            <div id="product_result2" class="easyui-tabs" style="top:1px;width:310px;height:auto;position: relative;padding-left: 0px">

                                <div id='cgqcx' title="条件查询" style="padding:5px">
                                    <div id='parm' style='position: relative;margin-top: 10px;'>
                                        <table id="parmtable2" class="bordered" border="0" align="center" >
                                            <tbody>
                                                <tr>
                                                    <td style='text-align:left;width: 113px'>关&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;键&nbsp;&nbsp;&nbsp;&nbsp;词:</td>
                                                    <td style='text-align: left;'>
                                                        <text id='KeyWordsText' style='width:155px;height:40px;'>请输入</text>
                                                        <input type="text" id="KeyWordsText" class='textinput' style="width: 160px" value="请输入规划、污水..." onclick="JavaScript:this.value = ''">
                                                    </td>
                                                </tr>
                                                                                                <tr>
                                                                                                    <td style='text-align:left;width: 113px'>数&nbsp;&nbsp;据&nbsp;名&nbsp;&nbsp;称:</td>
                                                                                                    <td style='text-align: left;'>
                                                                                                        <select  id='wxmc' name="chuanganqi" style="width: 155px;">
                                                                                                            <option>-------请选择--------</option>
                                                                                                        </select>
                                                                                                    </td>
                                                                                                </tr>
                                                                                                <tr>
                                                                                                    <td style='text-align:left;width: 113px'>影&nbsp;&nbsp;像&nbsp;色&nbsp;&nbsp;彩:</td>
                                                                                                    <td style='text-align: left;'>
                                                                                                        <select id='wxscms' name="yun" style="width: 155px;">
                                                                                                            <option>-------请选择--------</option>
                                                                                                                                                                        <option>RGB真彩色</option>
                                                                                                        </select>
                                                                                                    </td>
                                                                                                </tr>
                                                                                                <tr>
                                                                                                    <td style='text-align: left;'>DEM&nbsp;数&nbsp;&nbsp;据:</td>
                                                                                                    <td style='text-align: left;'>
                                                                                                        <select id='demsource' name="demsource" style="width: 155px;">
                                                                                                            <option>-------请选择--------</option>
                                                
                                                                                                        </select>
                                                                                                    </td>
                                                                                                </tr>

                                                <tr>
                                                    <td style='text-align: left;'>生&nbsp;&nbsp;产&nbsp;时&nbsp;&nbsp;间:</td>
                                                    <td style='text-align: left;'>
                                                        <input id="mapwxstartime" class="easyui-datebox" data-options="onSelect:onWxStartSelect" style="height:25px;width:120px;">
                                                        至
                                                        <input id="mapwxendtime" class="easyui-datebox" data-options="onSelect:onWxEndSelect"  style="height:25px;width:120px;" >
                                                    </td>

                                                </tr>

                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <hr/>
                            <br>
                            <br>
                            <div id='mapquery' align="center">
                                <input id='mapquerybtn' type="button" class="btn btn-success" value="开始查询" />
                            </div>
                            <div id="spinprocess" align="center" style="position: relative;"></div>
                                                           <label>当前最新全色数据采集时间:</label> <label id ="pband" class="time"></label>
                                                        <label>当前最新多光谱数据采集时间:</label> <label id="rgb" class="time"></label>
                            <br>
                            <br>
                        </div>
                        <br>
                        <div title="结果列表" style="padding:2px;height: 100%" data-options="fit:true">
                            <div id='mapresult' style='width: 100%;height: 100%;position:relative;top:1px;left:1px;'>
                                <table id="mapinfotable"  class="easyui-datagrid" title="查询结果" style="width: 100%;height:500px;"
                                       data-options="collapsible:true, fitColumns:false , striped:true">
                                    <thead>
                                        <tr>
                                            <th data-options="field:'mapid',width:120,align:'left'">图号</th>
                                            <th data-options="field:'name',width:155,align:'left'">产品名称</th>
                                                                                        <th data-options="field:'shp',width:45,align:'center'">矢量</th>
                                            <th data-options="field:'map',width:45,align:'center'">加载</th>
                                                                                        <th data-options="field:'downloadshp',width:70,align:'center'">矢量下载</th>
                                            <th data-options="field:'downloadras',width:70,align:'center'">下载</th>
                                                                                        <th data-options="field:'preview',width:80,checkbox:true">预览</th>
                                        </tr>
                                    </thead>
                                </table>
                                <div id="mapinfoltable2"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>-->
<div id='splitbt'> </div>
<div id="map"></div>    
<!--   <div id="center">
     <div >
     </div>
   <div id="pop" style="display:none;">
            <div id="popHead">
                <a id="popClose" title="关闭">关闭</a>
                <h2 id="poptext"style="font-size: 30;">镶嵌成功</h2>
            </div>
            <div id="popContent">
                <dl>
                    <dt id="popTitle"><a href="http://yanue.info/" target="_blank"></a></dt>
                    <dd id="popIntro" style='margin: auto;padding:3px;'>
                        <img id='moaimg' src="../images/苏州市.png" alt="缩略图" style="width: 500px;height: 300px;padding-right:0px;">
                    </dd>
                                        <dd >
                                            <a  id="mosaurl" href="" style="margin: auto;padding-bottom: 50px;padding-left: 230px;;align-content: center;">查看</a>
                                        </dd>
                </dl>
                <p id="popMore"><a href="#" target="_blank">查看 »</a></p>
            </div>
        </div>
    </div>-->
 </form>   
</body>
</html>
