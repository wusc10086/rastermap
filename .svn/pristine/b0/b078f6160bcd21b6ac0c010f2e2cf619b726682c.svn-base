<%-- 
    Document   : rtalarm
    Created on : 2017-8-15, 16:14:04
    Author     : wuyiwei
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>实时预警</title>
        <link rel="stylesheet" href="<c:url value='/js/bootstrap/css/bootstrap.min.css'/>"/>
        <link rel="stylesheet" href="<c:url value='/css/common.css'/>"/>
        <link href="../res/leaflet/leaflet.css" rel="stylesheet" type="text/css" />
        
        <script src="<c:url value='/js/jquery-1.10.2.js'/>"></script>
        <script src="<c:url value='/js/bootstrap/bootstrap.min.js'/>"></script>
	<script src="../res/leaflet/leaflet-src.js" type="text/javascript"></script>
	<script src="../res/echarts/echarts.min.js" type="text/javascript"></script>
	<script src="../res/echarts/echarts-leaflet.js" type="text/javascript"></script>
        <style type="text/css">
            #main {
                width: 100%;
                height: 500px;
                margin: 0;
                padding: 0;
            }
        </style>
    </head>
    <body>
        <div id='main'></div>
            
        <script>
            var parentHeight = parent.document.body.offsetHeight - 80;
            document.getElementById('main').style.height= parentHeight+"px";
            
            var devicesWarningData = [
                {
                    deviceId: "01120",
                    deviceName: "海淀",
                    lng: 116.46,
                    lat: 39.92,
                    sendtime: "2017-07-22 00:50:00",
                    alertarede: 3,
                    senddata: "9.0",
                    senddata2: "344",
                    senddata3: "456",
                },
                {
                    deviceId: "01121",
                    deviceName: "大兴",
                    lng: 116.33,
                    lat: 39.73,
                    sendtime: "2017-07-21 00:30:00",
                    alertarede: 2,
                    senddata: "6.5",
                    senddata2: "434",
                    senddata3: "344",
                },
                {
                    deviceId: "01122",
                    deviceName: "顺义",
                    lng: 116.65,
                    lat: 40.13,
                    sendtime: "2017-07-22 00:40:00",
                    alertarede: 4,
                    senddata: "12.5",
                    senddata2: "344",
                    senddata3: "456",
                },
                {
                    deviceId: "01123",
                    deviceName: "延庆",
                    lng: 115.97,
                    lat: 40.47,
                    sendtime: "2017-07-22 00:40:00",
                    alertarede: 4,
                    senddata: "12.5",
                    senddata2: "344",
                    senddata3: "456",
                }
            ];
            
            var defaultSymbolSize = 15;
            var zoomAndSymboSize = ${obj.zoomAndSymboSize};
            
            var convertData = function(devicesData) {
                var res = [];
                if (devicesData) {
                    var device;
                    for (var i =0; i < devicesData.length; i++) {
                        device = devicesData[i];
                        var data = [];
                        data.push(device.lng, device.lat, device.senddata);
                        res.push(data);
                    }
                }
                return res;
            }
            
            var calSymbolSize = function(zoom) {
                var size = zoomAndSymboSize[zoom];
                if (!size) {
                    size = defaultSymbolSize;
                }
                return size;
            }
            
            var option = {
                title: {
                    text: '电场实时预警',
		    left: 'center',
		    textStyle: {
                        color: '#000000'
		    }
                },
                backgroundColor : '#404a59',
                visualMap: {
                    type: "continuous",
                    //left: "right",
                    right: "15px",
                    bottom: "50px",
                    min: 2.5,
                    max: 15,
                    text:['15kv/m','2.5kv/m'],
                    splitNumber: 5,
                    borderColor: '#FF0000',
                    borderWidth: '3px',
                    inRange: {
                        color: ['#0000FF','#99CCFF','#00FF00','#FFFF00','#FF0000']
                    },
		    outOfRange: {
                        color: "#AA0000",
		    },
		    textStyle: {
                        color: '#000'
		    }
		},
                leaflet : {
                    center : [ ${obj.centerLng}, ${obj.centerLat} ],
                    zoom : ${obj.mapZoom},
                    roam : true
		},
		series: [
                    {
                        name: "AQI",
                        type: "heatmap",
                        coordinateSystem: "leaflet",
                        //pointSize: 35,
                        //blurSize: 26,
			data: convertData(devicesWarningData),
                    }
		],
            };
        
            
            var myChart = echarts.init(document.getElementById('main'));
            
            myChart.setOption(option);
            
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
                
        	var pointSize = symbolSize;
                pointSize = calRadius(leafletMap, devicesWarningData[0])
        	myChart.setOption({
        		series: [{
        			pointSize: pointSize,
        		}],
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
                
                var nearstDevice = nearestDevice(leafletMap, containerPoint, devicesWarningData);
                if (nearstDevice) {
                    console.log("nearstDevice" + nearstDevice);
                    infoWinEle.style.display = "block";
                    infoWinEle.style.top = "20px";
                    infoWinEle.style.right = "10px";
                } else {
                    infoWinEle.style.display = "none";
                }
            });
            
            // 计算设备探测的范围
            function calRadius(leafletMap, device) {
                var lat = device.lat;
                var lng = device.lng;
                
                var distance = 20; // 到站点的距离
                var earthRadius = 6371.393; // 地球半径
                var equatorPerimeter = Math.PI * 2 * earthRadius; // 赤道周长
                var latPerimeter = Math.PI * 2 * earthRadius * Math.cos(Math.PI / 2 * lat); // 该纬度的周长
                
                var newLatS = lat - (distance / equatorPerimeter) * 360;
                var newLngS = lng;
                
                var distancePointS = leafletMap.latLngToContainerPoint(new L.LatLng(newLatS, newLngS));
                
                var newLatW = lat;
                var newLngW = lng - (distance / latPerimeter) * 360;
                
                var distancePointW = leafletMap.latLngToContainerPoint(new L.LatLng(newLatW, newLngW));
                
                var devicePoint = leafletMap.latLngToContainerPoint(new L.LatLng(lat, lng));
                var distanceS = devicePoint.distanceTo(distancePointS);
                var distanceW = devicePoint.distanceTo(distancePointW);
                var averageDistance = (Math.abs(distanceS) + Math.abs(distanceW)) / 2;
                
                return averageDistance;
            }
            
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
            
        </script>
        
        <div id="infoWin" style="width: 220px; border: 1px solid #AA0000; background-color:rgba(210,210,210,0.75); position: fixed; z-index: 10000; display:none;">
            <table class="table table-striped table-bordered" style="margin-bottom: 0;">
                <tr>
                    <td>站</td>
                    <td>海淀</td>
                </tr>
                <tr>
                    <td>预警级别</td>
                    <td>1级</td>
                </tr>
                <tr>
                    <td>当前秒电场强度</td>
                    <td>1.56kv/m</td>
                </tr>
                <tr>
                    <td>前一秒电场强度</td>
                    <td>4.56kv/m</td>
                </tr>
                <tr>
                    <td>前两秒电场强度</td>
                    <td>15.56kv/m</td>
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
    </body>
</html>
