<%-- 
    Document   : device-state
    Created on : 2017-8-17, 8:41:58
    Author     : wuyiwei
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>设备状态图</title>
        <link rel="stylesheet" href="<c:url value='/js/bootstrap/css/bootstrap.min.css'/>"/>
        <link rel="stylesheet" href="<c:url value='/css/common.css'/>"/>
        <link href="../res/leaflet/leaflet.css" rel="stylesheet" type="text/css" />
        
        <script src="<c:url value='/js/jquery-1.10.2.js'/>"></script>
        <script src="<c:url value='/js/bootstrap/bootstrap.min.js'/>"></script>
	<script src="../res/leaflet/leaflet-src.js" type="text/javascript"></script>
	<script src="../res/echarts/echarts.min.js" type="text/javascript"></script>
	<script src="../res/echarts/echarts-leaflet.js" type="text/javascript"></script>
        <style type="text/css">
            html,body {
                margin: 0;
                padding: 0;
                height: 100%;
            }
            #main {
                width: 100%;
                margin: 0;
                padding: 0;
            }
        </style>
    </head>
    <body style="background-color: #E8E8E8;">
        <div id='main'></div>
        
        <script>
            var parentHeight = parent.document.body.offsetHeight - 80;
            document.getElementsByTagName("body")[0].style.height= parentHeight+"px";
            document.getElementById('main').style.height= (parentHeight-20)+"px";
            
            // stateValue: 0: 无数据；1：数据正常；2：数据异常。9: 数据报警。
            // -1：没有数据，0：系统异常，1：数据正常，2: 数据报警。
            var devicesStateData = [];
            /*
            var devicesStateData = [
                {
                    deviceId: "01120",
                    deviceName: "海淀",
                    lng: 116.46,
                    lat: 39.92,
                    stateValue: 1,
                    sendtime: "2017-07-22 00:50:00",
                    lowvalue: 3.85,
                    highvalue: 4.01,
                    average: 4.00,
                },
                {
                    deviceId: "01121",
                    deviceName: "大兴",
                    lng: 116.33,
                    lat: 39.73,
                    stateValue: 0,
                    sendtime: null,
                    lowvalue: null,
                    highvalue: null,
                    average: null,
                },
                {
                    deviceId: "01122",
                    deviceName: "顺义",
                    lng: 116.65,
                    lat: 40.13,
                    stateValue: 2,
                    sendtime: "2017-07-22 00:50:00",
                    lowvalue: 3.85,
                    highvalue: 4.01,
                    average: 4.00,
                },
                {
                    deviceId: "01123",
                    deviceName: "延庆",
                    lng: 115.97,
                    lat: 40.47,
                    stateValue: 9,
                    sendtime: "2017-07-22 00:50:00",
                    lowvalue: 13.85,
                    highvalue: 14.01,
                    average: 14.00,
                }
            ];
            */
            
            // 无数据的站
            var noneDataDevices = [];
            // 数据异常的站
            var invalidDataDevices = [];
            // 数据正常的站
            var normalDataDevices = [];
            // 数据报警的站
            var warningDataDevices = [];
            
            var defaultSymbolSize = 15;
            var zoomAndSymboSize = ${obj.zoomAndSymboSize};
            
            var convertData = function(devicesStateData) {
                if (!devicesStateData) return;
                
                // 清空各类数据
                noneDataDevices.slice(0, noneDataDevices.length);
                noneDataDevices.length = 0;
                invalidDataDevices.slice(0, invalidDataDevices.length);
                invalidDataDevices.length = 0;
                normalDataDevices.slice(0, normalDataDevices.length);
                normalDataDevices.length = 0;
                warningDataDevices.slice(0, warningDataDevices.length);
                warningDataDevices.length = 0;
                
                for (var i = 0; i< devicesStateData.length; i++) {
                    var stateData = devicesStateData[i];
                    stateData.value = [stateData.lng, stateData.lat, stateData.stateValue];
                    if (stateData.stateValue == -1) {
                        noneDataDevices.push(stateData);
                    } else if (stateData.stateValue == 1) {
                        normalDataDevices.push(stateData);
                    } else if (stateData.stateValue == 0) {
                        invalidDataDevices.push(stateData);
                    }  else if (stateData.stateValue == 2) {
                        warningDataDevices.push(stateData);
                    } else {
                        console.log("非法设备状态数据：" + stateData);
                    }
                }
                
            };
            
            var calSymbolSize = function(zoom) {
                var size = zoomAndSymboSize[zoom];
                if (!size) {
                    size = defaultSymbolSize;
                }
                return size;
            }
            
            var option = {
                title: {
                    text: '设备运行状态',
                    subtext: "正常：0，异常：0，无数据：0，预警：0",
		    left: 'center',
		    textStyle: {
                        color: '#111111',
		    },
                    subtextStyle: {
                        color: '#333333',
                        fontWeight: 'bolder',
                        fontSize: 14,
                    },
                },
                backgroundColor : '#404a59',
                tooltip : {
                    trigger: 'item',
                    formatter: function(params){
                        var seriesName = params.seriesName;
                        var data = params.data;
                        var msg = seriesName + "<br/>" + data.deviceName + "<br/>";
                        if (data.stateValue == 1 || data.stateValue == 9) {
                            msg += "电场强度：" + data.average.toFixed(2)+"kv/s";
                        }
                        return msg;
                    },
                },
                legend: {
                    orient: 'vertical',
                    right: '10px',
                    bottom: '135px',
                    data:['数据正常', '数据异常', '无数据'],
                    itemWidth: 18,
                    itemHeight: 18,
                    textStyle: {
                        color: '#111111',
                        fontSize: 13,
                    },
                },
                leaflet : {
                    center : [ ${obj.centerLng}, ${obj.centerLat} ],
                    zoom : ${obj.mapZoom},
                    roam : true
		},
                series: [
                    {
                        name: '无数据',
                        type: 'scatter',
                        coordinateSystem: 'leaflet',
                        data: noneDataDevices,
                        symbolSize: function (val) {
                            return calSymbolSize(${obj.mapZoom});
                        },
                        label: {
                            normal: {
                                formatter: '{b}',
                                position: 'right',
                                show: false
                            },
                           emphasis: {
                               show: true
                           }
                       },
                       itemStyle: {
                           normal: {
                               color: '#dc0e19'
                            }
                        }
                    },
                    {
                        name: '数据异常',
                        type: 'scatter',
                        coordinateSystem: 'leaflet',
                        data: invalidDataDevices,
                        symbolSize: function (val) {
                            return calSymbolSize(${obj.mapZoom});
                        },
                        label: {
                            normal: {
                                formatter: '{b}',
                                position: 'right',
                                show: false
                            },
                           emphasis: {
                               show: true
                           }
                       },
                       itemStyle: {
                           normal: {
                               color: '#0069e9'
                            }
                        }
                    },
                    {
                        name: '数据正常',
                        type: 'scatter',
                        coordinateSystem: 'leaflet',
                        data: normalDataDevices,
                        symbolSize: function (val) {
                            return calSymbolSize(${obj.mapZoom});
                        },
                        label: {
                            normal: {
                                formatter: '{b}',
                                position: 'right',
                                show: false
                            },
                           emphasis: {
                               show: true
                           }
                       },
                       itemStyle: {
                           normal: {
                               color: '#5aff24'
                            }
                        }
                    },
                    {
                        name: '数据报警',
                        type: 'effectScatter',
                        coordinateSystem: 'leaflet',
                        data: warningDataDevices,
                        symbolSize: function (val) {
                            return calSymbolSize(${obj.mapZoom});
                        },
                        showEffectOn: 'render',
                        rippleEffect: {
                            period: 3,
                            scale: 3,
                            brushType: 'fill',
                        },
                        hoverAnimation: true,
                        label: {
                            normal: {
                                formatter: '{b}',
                                position: 'right',
                                show: true
                            },
                        },
                        itemStyle: {
                           normal: {
                               color: '#00ff00',
                               shadowBlur: 10,
                               shadowColor: '#333',
                            }
                        },
                        zlevel: 1,
                    },
		],
            };
        
            var myChart = echarts.init(document.getElementById('main'));
            
            myChart.setOption(option);
            
            var refreshStateData = function() {
                convertData(devicesStateData);
                myChart.setOption({
                    title: {
                        subtext: "正常："+ normalDataDevices.length + "，异常：" + invalidDataDevices.length + "，无数据："+noneDataDevices.length+"，预警：" + warningDataDevices.length,
                    },
                    series: [
                    {
                        data: noneDataDevices,
                    },
                    {
                        data: invalidDataDevices,
                    },
                    {
                        data: normalDataDevices,
                    },
                    {
                        data: warningDataDevices,
                    },
                    ],
                });
            };
            
            // 发送ajax请求，获取最新设备状态等信息数据
            var doAjaxRequestForDevciesState= function() {
                 <c:url var = "currentDevicesEFDataUrl" value="/runmonitor/currentDevicesEFData"/>
                $.getJSON("${currentDevicesEFDataUrl}", function(jsonData){
                    devicesStateData = jsonData;
                    refreshStateData();
                });
            };
            
            
            
            var leafletMap = myChart.getModel().getComponent('leaflet').getLeaflet();
            leafletMap.setMaxZoom(${obj.mapMaxZoom});
            leafletMap.setMinZoom(${obj.mapMinZoom});
            leafletMap.eachLayer(function(layer){
                if (layer instanceof L.TileLayer) {
                    layer.setOpacity(0.8);
                }
            });
            
            leafletMap.on("zoomend ", function(event) {
        	var zoom = leafletMap.getZoom();
        	var symbolSize = calSymbolSize(zoom);
        	
        	myChart.setOption({
        		series: [
                            {
        			symbolSize: symbolSize,
                            },
                            {
        			symbolSize: symbolSize,
                            },
                            {
        			symbolSize: symbolSize,
                            },
                            {
        			symbolSize: symbolSize,
                            },
                        ],
        	});
                
            });
            
            leafletMap.on('click', function(e){
                var latlng = e.latlng;
                var containerPoint = e.containerPoint;
                //var popup = L.marker([116.65, 40.13]).addTo(leafletMap)
                //    .bindPopup('A pretty CSS3 popup.<br> Easily customizable.历史')
                //    .openPopup();
                var infoWinEle = document.getElementById("infoWin");
                //L.DomUtil.setPosition(infoWinEle, containerPoint);
                
                var nearstDevice = nearestDevice(leafletMap, containerPoint, devicesStateData);
                if (nearstDevice) {
                    console.log("nearstDevice" + nearstDevice);
                    infoWinEle.style.display = "block";
                    infoWinEle.style.top = "20px";
                    infoWinEle.style.right = "10px";
                } else {
                    infoWinEle.style.display = "none";
                }
            });
            
            /**
            *  获得点击点最近的设备，如果没有，则返回空。
             * @returns {undefined}             */
            function nearestDevice(leafletMap, clickPoint, devicesData) {
                var minDistance = Number.MAX_VALUE;
                var nearstDevice = null;
                
                if (devicesData) {
                    var device;
                    for (var i =0; i < devicesData.length; i++) {
                        device = devicesData[i];
                        
                        var devicePoint = leafletMap.latLngToContainerPoint(new L.LatLng(device.lat, device.lng));
                        var distance = clickPoint.distanceTo(devicePoint);
                        if (distance < minDistance) {
                            minDistance = distance;
                            nearstDevice = device;
                        }
                    }
                }
                
                if (minDistance >= 0 && minDistance <= 18) {
                    return nearstDevice;
                } else {
                    return null;
                }
            } 
            
            window.setTimeout(doAjaxRequestForDevciesState, 0);
            window.setInterval(doAjaxRequestForDevciesState, 60 * 1000);
        </script>
        
        
        
        <div id="infoWin" style="width: 300px; border: 1px solid #AA0000; background-color:rgba(210,210,210,0.75); position: fixed; z-index: 10000; display:none;">
            <table class="table table-striped table-bordered" style="margin-bottom: 0;">
                <tr>
                    <td>站号</td>
                    <td>01120</td>
                </tr>
                <tr>
                    <td>名称</td>
                    <td>海淀</td>
                </tr>
                <tr>
                    <td>经度</td>
                    <td>116.46</td>
                </tr>
                <tr>
                    <td>纬度</td>
                    <td>39.92</td>
                </tr>
                <tr>
                    <td>电场强度</td>
                    <td>1.56kv/m</td>
                </tr>
                <tr>
                    <td>预警级别</td>
                    <td>1级</td>
                </tr>
                <tr>
                    <td>厂商</td>
                    <td>厂商厂商厂商厂商厂商</td>
                </tr>
                <tr>
                    <td>安装时间</td>
                    <td>2016-07-20</td>
                </tr>
                <tr>
                    <td>最后巡检时间</td>
                    <td>2016-07-20</td>
                </tr>
                <tr>
                    <td>最后维修时间</td>
                    <td>2016-07-20</td>
                </tr>
            </table>
        </div>
        <div id="resetMap" title="还原" style="width: 32px; height: 32px; border: 1px solid #C8C8C8; background-color:#FFFFFF; position: fixed; z-index: 10000; top:70px; left: 8px;text-align: center; -webkit-border-radius:8px; -moz-border-radius: 8px; border-radius: 8px;">
           <span class="glyphicon glyphicon-paperclip" style="font-weight: bold; font-size: 14px; margin-top: 8px;"></span>
        </div>
        
        <script>
            $("#infoWin").mouseenter(function(){
                    //alert("eeeeee");
            });
            $("#resetMap").click(function(){
                leafletMap.setView(new L.latLng([${obj.centerLat}, ${obj.centerLng}]),${obj.mapZoom});
            });
        </script>
        
        <div id="efdataChartWrapper" style="width: 100%;  border: 1px solid #C8C8C8; display: none; background-color: rgba(230, 230, 230,0.65); position: absolute; z-index: 19999; bottom: 0; ">
            <div id="efdataChart" style="height: 200px; "></div>
        </div>
        <div id ="deviceSelectDiv" style="position: absolute; bottom:100px; left: 10px; z-index: 20003">
            <select id="deviceSelect">
                <c:forEach var="device" items="${obj.allDevices}">
                    <option value="${device.num}">${device.deviceName}</option>
                </c:forEach>
            </select>
        </div>
        <div id="hideBtn" title="隐藏" style="width: 120px; height: 12px; background-color: #C0C0C0; border: 1px solid #C8C8C8; position: absolute; z-index: 20003; bottom:115px; left: 50%;text-align: center; border-radius: 6px;">
           <span class="glyphicon glyphicon-chevron-down" style="position: relative; top: -3px; font-size: 12px; "></span>
        </div>
        <script>
            var deviceEfdatas = [];
            /*
            var obtainDeviceEfData = function() {
                var now = new Date();
                //var nowStr = now.getFullYear() + "/" + now.getMonth() + "/" + now.getDate() + " " 
                //        + now.getHours() + ":" + now.getMinutes() + ":" + now.getSeconds();
                var nowStr = now.getHours() + ":" + now.getMinutes() + ":" + now.getSeconds();
                var v = 100 + Math.floor((Math.random()*100)+1);
                //console.log("nowStr=" + nowStr +"，v=" + v);
                return [nowStr, v];
            }
            
            
            for (var i = 120; i < 0; i--) {
                var nowStr = now.getHours() + ":" + now.getMinutes()-1 + ":" + i;
                var v = 100 + Math.floor((Math.random()*100)+1);
                var newData = [nowStr, v];
                deviceEfdatas.push(newData);
            }
            */
            
            var dateList = deviceEfdatas.map(function(item){
                    return item[0];
                });
            var valueList = deviceEfdatas.map(function(item){
                    return item[1].toFixed(2);
                });
                
            
            var efdataChartOption = {
                tooltip: {
                    trigger: 'axis',
                    formatter: function (params) {
                        param = params[0];
                        var dateValue = param.axisValue;
                        return"时间：" + dateValue + "<br>" + ' 电场值：' + param.data;
                    },
                    axisPointer: {
                        //animation: false
                    }
                },

                xAxis:{
                    //type: 'value'
                    name: '时间',
                    data: dateList,
                    splitNumber: 12,
                    //max: 10,
                    splitLine: {
                        show: false
                    }
                },
                yAxis: {
                    name: 'kv/s',
                    nameLocation: 'middle',
                    nameRotate: 90,
                    nameGap: 40,
                    type: 'value',
                    //boundaryGap: ['0', '100%'],
                    splitLine: {
                        show: false
                    }
                },
                series: [
                    {
                        name: '电场强度数据',
                        type: 'line',
                        showSymbol: false,
                        hoverAnimation: false,
                        data: valueList,
                        zlevel: 20000,
                        z: 20002,
                    }
                ],
                color: ['#DD3333'],
            };
            
            var deviceEfdatasChart = echarts.init(document.getElementById('efdataChart'));
            deviceEfdatasChart.setOption(efdataChartOption);
            
            // 发送ajax请求，获取最新设备状态等信息数据
            var doAjaxRequestForGetDeviceEfdatas= function() {
                var deviceId = $("#deviceSelect").val();
                 <c:url var = "getDeviceEfdatasUrl" value="/runmonitor/getDeviceEfdatas"/>
                var data = {deviceId: deviceId};
                $.getJSON("${getDeviceEfdatasUrl}", data, function(jsonData){
                    deviceEfdatas = jsonData;
                    
                    var dateList = deviceEfdatas.map(function(item){
                        console.log(item);
                        return item[0];
                    });
                    var valueList = deviceEfdatas.map(function(item){
                        return item[1] == null ? null : item[1].toFixed(2);
                    });
                    
                    deviceEfdatasChart.setOption({
                        xAxis: [{
                            data: dateList,
                        }],
                        series: [
                            {
                                data: valueList
                            }, 
                        ]
                         
                    });
                });
                $("#efdataChartWrapper").css("display","");
            };
            
            window.setTimeout(doAjaxRequestForGetDeviceEfdatas, 0);
            window.setInterval(doAjaxRequestForGetDeviceEfdatas, 60 * 1000);
            
            $("#deviceSelect").on("change", function(){
                window.setTimeout(doAjaxRequestForGetDeviceEfdatas, 0);
            })
            
            /*
            setInterval(function () {
                if (deviceEfdatas.length > 120) {
                    deviceEfdatas.shift();
                }
                
                var newData = obtainDeviceEfData();
                deviceEfdatas.push(newData);
                
                var dateList = deviceEfdatas.map(function(item){
                    return item[0];
                });
                var valueList = deviceEfdatas.map(function(item){
                    return item[1];
                });
                
                deviceEfdatasChart.setOption({
                    xAxis: {
                        min: dateList[0],
                        data: dateList,
                    },
                    series: [{
                            data: valueList
                        }]
                });
                //console.log("deviceEfdatas=" + deviceEfdatas.length);
            }, 1000);
            */
        </script>
        <script>
            $("#hideBtn").on("click", function(){
                $("#deviceSelectDiv").toggle();
                $("#efdataChartWrapper").toggle();
                //$("#hideBtn").css("bottom","115px");
                if ($("#deviceSelectDiv").is(":hidden")) {
                    $("#hideBtn").css("bottom","0px");
                    $("#hideBtn").attr("title", "显示");
                    
                    var child = $("#hideBtn").children()[0];
                    $(child).removeClass("glyphicon-chevron-down");
                    $(child).addClass("glyphicon-chevron-up");
                } else {
                    $("#hideBtn").css("bottom","115px");
                    $("#hideBtn").attr("title", "隐藏");
                    var child = $("#hideBtn").children()[0];
                    $(child).removeClass("glyphicon-chevron-up");
                    $(child).addClass("glyphicon-chevron-down");
                }
            });
            
        </script>
    </body>
</html>
