
var map;
var nextdata = new Array();
var startTime;
var xA = new Array();
var yA = new Array();
var chartData = [];
var markData = new Array();
var marker = new Array();
//var yichangPoint = new Map();
//var yujingPont = new Map();
var chartTimer;
var seriTimer;
var selectValue = "01120";
var myChart;
//天地图图层数据
var htmlUrl = document.location.host,
        stringIndex = htmlUrl.indexOf("//"),
        subString = htmlUrl.substring(0, stringIndex - 1);
var tiandituUrl = 'http://t{s}.tianditu.com/DataServer?T=img_w&x={x}&y={y}&l={z}';
var tiandituAnoUrl = 'http://t{s}.tianditu.com/DataServer?T=cia_w&x={x}&y={y}&l={z}';

var gaodeUrl = "http://" + htmlUrl + '/rastermap/tile/maptile/788865972/{z}/{x}/{y}';
var gaodeUrlAnoUrl = "http://" + htmlUrl + '/rastermap/tile/maptile/788865972/{z}/{x}/{y}';

var tiandituLayer = new L.TileLayer(tiandituUrl, {
    maxZoom: 13,
    minZoom: 2,
    subdomains: '1234567',
    attribution: '天地图影像'
            // attribution: '高德影像'
});
var gdLayer = new L.TileLayer(gaodeUrl, {
    maxZoom: 13,
    minZoom: 2,
    subdomains: '1234567',
    //attribution: '天地图影像'
    attribution: '高德影像'
});
var tiandituAnoLayer = new L.TileLayer(tiandituAnoUrl, {
    maxZoom: 13,
    minZoom: 2,
    subdomains: '1234567',
//    attribution: '天地图影像注记'
    attribution: '高德影像注记'
});

var YellowIcon = L.Icon.Default.extend({
    options: {
        iconUrl: '../js/leaflet/images/w2.png'
    }
});
var yellowIcon = new YellowIcon();

var GreenIcon = L.Icon.Default.extend({
    options: {
        // iconSize: new L.Point(20, 15), 
        iconUrl: '../js/leaflet/images/w3.png'
    }
});

/**
 * 初始化地图采用 EPSG:4326 模式
 * @returns {undefined}
 */
var initTDTmap = function () {
    var latlng = new L.LatLng(39.92, 116.46);
    map = new L.Map('map', {
        crs: L.CRS.EPSG3857,
        keyboard: true,
        fadeAnimation: true,
        boxZoom: true,
        attributionControl: false,
        trackResize: true,
        center: latlng,
        zoom: 4,
        maxZoom: 13,
        zoomControl: false,
        //layers: [tiandituLayer, cityLayer, tiandituAnoLayer],
        layers: [gdLayer, cityLayer],
        contextmenu: true,
        contextmenuWidth: 120
    });
     var zoomHome = L.Control.zoomHome();
     zoomHome.addTo(map);
    
    var baseMaps = {
        "天地图": tiandituLayer,
        "高德地图": gdLayer
    };
    var overlayMaps = {
        "高德影像注记": tiandituAnoLayer,
        "行政区图层": cityLayer
    };
    L.control.layers(baseMaps, overlayMaps).addTo(map);
   
};
/**
 * 设置select值
 * @return {undefined}
 */
function setSelectValue()
{
    $.ajax({
        type: "GET",
        url: "../device/getAddress",
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
            var sel = document.getElementById('deviceNum');
            sel.options.length = 0;
            for (var i = 0; i < obj.length; i++)
            {
                sel.options.add(new Option(obj[i].DeviceName, obj[i].Num));
            }
        }
    });
}
/**
 * 显示坐标
 * @param {type} e
 * @returns {undefined}
 */

/**
 * 设置地图中心点
 * @param {type} e
 * @returns {undefined}
 */
function centerMap(e) {
    if (e.latlng) {
        map.panTo(e.latlng);
    } else {
        map.setZoom(15);
        map.panTo(e);
    }
}

/**
 * 放大地图
 * @param {type} e
 * @returns {undefined}
 */
function zoomIn(e) {
    map.zoomIn();
}

/**
 * 缩小地图
 * @param {type} e
 * @returns {undefined}
 */
function zoomOut(e) {
    map.zoomOut();
}


/**
 * 获取设备数据
 * @return {undefined}
 */
function deviceQuery() {
    $.ajax({
        type: "GET",
        url: "../device/getalldevice",
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
            markData.splice(0, markData.length);
            markData = obj;
            drawMarker();
        }
    });
}

/**
 * 绘制电场marker信息 10分钟更新
 * @return {undefined}
 */
function drawMarker1()
{
    for (i = 0; i < marker.length; i++) {
        map.removeLayer(marker[i]);

    }
    while (marker.length !== 0)
    {
        marker.pop();
    }
    $.each(markData, function (i, n) {
        console.log(n);
        /**
         * 数据异常 蓝
         */
        if (n.AlertArede !== "")
        {
            var blueIcon = L.divIcon({
                className: 'leaflet-div-blue',
                iconSize: [30, 30]
            });
            var LamMarker = L.marker([n.Latitude, n.Longitude], {icon: blueIcon}, {highlight: "permanent"}).bindPopup("设备编号：" + n.Num + "<br>" + "站点名称：" + n.DeviceName + "<br>"
                    + "设备经度：" + n.Latitude + "<br>"
                    + "设备纬度：" + n.Longitude + "<br>" + "设备高程：" + n.Elevation + "M" + "<br>"
                    + "制造商名：" + n.Manufaceture + "<br>" + "安装时间：" + n.InstallTime + "<br>"
                    + "巡检时间：" + n.LastInspect + "<br>" + "维修时间：" + n.LastRepair + "<br>");
            LamMarker.on({
                'click': function (e) {
                    console.log(e.target);
                },
            });
            marker.push(LamMarker);
            map.addLayer(marker[i]);
        }
        /**
         * 无数据 红
         */
        else if (n.Status === '2')
        {
            var LamMarker = L.marker([n.Latitude, n.Longitude], {icon: redIcon}).bindPopup("设备编号：" + n.Num + "<br>" + "站点名称：" + n.DeviceName + "<br>"
                    + "设备经度：" + n.Latitude + "<br>"
                    + "设备纬度：" + n.Longitude + "<br>" + "设备高程：" + n.Elevation + "M" + "<br>"
                    + "制造商名：" + n.Manufaceture + "<br>" + "安装时间：" + n.InstallTime + "<br>"
                    + "巡检时间：" + n.LastInspect + "<br>" + "维修时间：" + n.LastRepair + "<br>"
                    );
            LamMarker.on({
                'click': function (e) {
                    alert(e.target);
                },
            });
            LamMarker.setId(n.num);
            LamMarker.addTo(map);
        }
        /**
         * 预警 绿闪烁
         */
        else if (n.Status === '3') {
            marker.push(LamMarker);
            //获取上次预警时间
            var time = yujingPont.get(n.DeviceName);
            /**
             * 预警超过10分钟
             */
            if (time && ((getNowTime() - time) > 1000 * 60 * 10)) {
                var LamMarker = L.marker([n.Latitude, n.Longitude], {icon: greenIcon}).bindPopup("设备编号：" + n.Num + "<br>" + "站点名称：" + n.DeviceName + "<br>"
                        + "设备经度：" + n.Longitude + "<br>"
                        + "设备纬度：" + n.Latitude + "<br>" + "设备高程：" + n.Elevation + "M" + "<br>"
                        + "制造商名：" + n.Manufaceture + "<br>" + "安装时间：" + n.InstallTime + "<br>"
                        + "巡检时间：" + n.LastInspect + "<br>" + "维修时间：" + n.LastRepair + "<br>"
                        + "预警级别：" + n.AlertArede + "<br>" + "电场数据：" + n.SendData + "KV" + "<br>");

                yujingPont.remove(n.DeviceName);
                LamMarker.on({
                    'click': function (e) {
                        alert(e.target);
                    },
                });
            } else {
                var myIcon = L.divIcon({
                    className: 'leaflet-div-icon2',
                    iconSize: [25, 41]
                });
                var LamMarker = L.marker([n.Latitude, n.Longitude], {icon: myIcon}).bindPopup("设备编号：" + n.Num + "<br>" + "站点名称：" + n.DeviceName + "<br>"
                        + "设备经度：" + n.Latitude + "<br>"
                        + "设备纬度：" + n.Longitude + "<br>" + "设备高程：" + n.Elevation + "M" + "<br>"
                        + "制造商名：" + n.Manufaceture + "<br>" + "安装时间：" + n.InstallTime + "<br>"
                        + "巡检时间：" + n.LastInspect + "<br>" + "维修时间：" + n.LastRepair + "<br>"
                        );
                //记录站点预警时间
                yujingPont.put(n.DeviceName, getNowTime());

            }
            LamMarker.addTo(map);
        }
        /**
         * 正常 绿
         */
        else {
            var LamMarker = L.marker([n.Latitude, n.Longitude], {icon: greenIcon}).bindPopup("设备编号：" + n.Num + "<br>" + "站点名称：" + n.DeviceName + "<br>"
                    + "设备经度：" + n.Longitude + "<br>"
                    + "设备纬度：" + n.Latitude + "<br>" + "设备高程：" + n.Elevation + "M" + "<br>"
                    + "制造商名：" + n.Manufaceture + "<br>" + "安装时间：" + n.InstallTime + "<br>"
                    + "巡检时间：" + n.LastInspect + "<br>" + "维修时间：" + n.LastRepair + "<br>"
                    + "预警级别：" + n.AlertArede + "<br>" + "电场数据：" + n.SendData + "KV" + "<br>");
            LamMarker.on({
                'click': function (e) {
                    console.log(e);
                    console.log(e.target);
                },
            });
            LamMarker.addTo(map);
        }
    });

}
/**
 * 绘制电场marker信息 现在
 * @return {undefined}
 */
function drawMarker()
{
    var zc = 0, yc = 0, wsj = 0, yj = 0;
    for (i = 0; i < marker.length; i++) {
        map.removeLayer(marker[i]);
    }
    while (marker.length !== 0)
    {
        marker.pop();
    }
    $.each(markData, function (i, n) {

        /**
         * 数据异常 蓝
         */
        if (n.status === '0')
        {
            var status = "数据异常";
            yc++;
            var blueIcon = L.divIcon({
                className: 'leaflet-div-blue',
                iconSize: [20, 20]
            });
            var LamMarker = L.marker([n.latitude, n.longitude], {icon: blueIcon}, {highlight: "permanent"}).bindPopup(
                    "编号：" + n.num + "<br>"
                    + "名称：" + n.deviceName + "<br>"
                    + "时间：" + n.laststatustime + "<br>"
                    + "状态：" + status + "<br>"
                    + "经度：" + n.latitude + "<br>"
                    + "纬度：" + n.longitude + "<br>"
                    + "强度：" + n.efvalue + "KV/s" + "<br>"
                    );
            marker.push(LamMarker);
            map.addLayer(marker[i]);
        }
        /**
         * 无数据 红
         */
        else if (n.status === '-1')
        {
            var status = "无数据";
            wsj++;
            var redIcon = L.divIcon({
                className: 'leaflet-div-red',
                iconSize: [20, 20]
            });
            var LamMarker = L.marker([n.latitude, n.longitude], {icon: redIcon}).bindPopup(
                    "编号：" + n.num + "<br>"
                    + "名称：" + n.deviceName + "<br>"
                    + "时间：" + n.laststatustime + "<br>"
                    + "状态：" + status + "<br>"
                    + "经度：" + n.latitude + "<br>"
                    + "纬度：" + n.longitude + "<br>"
                    + "强度：" + n.efvalue + "KV/s" + "<br>"
                    );
            marker.push(LamMarker);
            map.addLayer(marker[i]);
        }
        /**
         * 预警 绿闪烁
         */
        else if (n.status === '2') {
            var status = "预警";
            yj++;
            var myIcon = L.divIcon({
                className: 'leaflet-div-icon2',
                iconSize: [20, 20]
            });
            var LamMarker = L.marker([n.latitude, n.longitude], {icon: myIcon}).bindPopup(
                    "编号：" + n.num + "<br>"
                    + "名称：" + n.deviceName + "<br>"
                    + "时间：" + n.laststatustime + "<br>"
                    + "状态：" + status + "<br>"
                    + "经度：" + n.latitude + "<br>"
                    + "纬度：" + n.longitude + "<br>"
                    + "强度：" + n.efvalue + "KV/s" + "<br>"

                    );
            //记录站点预警时间
            // yujingPont.put(n.DeviceName, getNowTime());
            marker.push(LamMarker);
            map.addLayer(marker[i]);
        } else {
            zc++;
            var greenIcon = L.divIcon({
                className: 'leaflet-div-green',
                iconSize: [20, 20]
            });
            var status = "数据正常";
            var LamMarker = L.marker([n.latitude, n.longitude], {icon: greenIcon}).bindPopup(
                    "编号：" + n.num + "<br>"
                    + "名称：" + n.deviceName + "<br>"
                    + "时间：" + n.laststatustime + "<br>"
                    + "状态：" + status + "<br>"
                    + "经度：" + n.latitude + "<br>"
                    + "纬度：" + n.longitude + "<br>"
                    + "强度：" + n.efvalue + "KV/s" + "<br>"
                    );

            marker.push(LamMarker);
            map.addLayer(marker[i]);

        }
    });
    $('#zczd').html(zc);
    $('#yczd').html(yc);
    $('#wsj').html(wsj);
    $('#yyjgs').html(yj);

}

/**
 * 获取图表数据
 * @return {undefined}
 */
function getEFDataByNum() {
    var num = selectValue;
    $.ajax({
        type: "GET",
        async: false,
        url: "../efdata/getefdatabynum/" + num,
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
//            xA.splice(0, xA.length);
//            yA.splice(0, yA.length);
            $.each(obj, function (i, n) {
                xA.push(n.sendTime);
                yA.push(n.average / 100);
            });
        }
    });
}

/**
 * 选择触发获取编辑值
 * @param {type} v
 * @return {undefined}
 */
function getDeviceNum(v)
{
    var num = v;
    xA.splice(0, xA.length);
    yA.splice(0, yA.length);
    chartData.splice(0, chartData.length);
    clearInterval(chartTimer);
    clearInterval(seriTimer);
    myChart.dispose();
    getEFDataByNum(num);
    drawChart();
}

/**
 * 
 * @param {type} name
 * @param {type} time
 * @returns {addChartData.deviceAnonym$32}
 */
function addChartData(name, time) {
    return {
        name: time,
        value: [name, time]
    }
}

/**
 * 绘制表格
 * @return {undefined}
 */
function drawChart()
{
    for (var i = 0; i < 60; i++) {
        //一次显示1分钟数据
        chartData.push(addChartData(xA[i], yA[i]));
    }
    myChart = echarts.init(document.getElementById('chart'));
    option = {
        //backgroundColor: 'rgba(128, 128, 128, 0.5)',
        toolbox: {//可视化的工具箱
            show: true,
            feature: {
                saveAsImage: {//保存图片
                    show: true
                }
            }
        },
        tooltip: {
            trigger: 'axis',
            formatter: function (params) {
                params = params[0];
                var date = new Date(params.axisValue);
                return"时间值:" + date.getFullYear() + '/' + (date.getMonth() + 1) + '/' + date.getDate() + ' ' + date.getHours() + ':' + date.getMinutes() + "<br>" + ' 电场值: ' + params.value[1];
            },
            axisPointer: {
                animation: false
            }
        },
        xAxis: {
            type: 'time',
            name: '时间',
            splitLine: {
                show: false
            },
            nameTextStyle: {
                color: '#000000'
            },
            axisLabel: {
                show: true,
                textStyle: {
                    color: '#000000'
                }
            },
            axisLine: {
                lineStyle: {
                    color: '#000000',
                    width: 1 //这里是为了突出显示加上的  
                }
            }
        },
        yAxis: {
            type: 'value',
            boundaryGap: [0, '100%'],
            splitLine: {
                show: false
            },
            name: "电场强度 KV",
            nameTextStyle: {
                color: '#000000'
            },
            axisLine: {
                lineStyle: {
                    color: '#000000',
                    width: 1 //这里是为了突出显示加上的  
                }
            },
            axisLabel: {
                formatter: '{value}',
                textStyle: {
                    color: '#000000'
                }
            }
        },
        series: [{
                name: '实时数据',
                type: 'line',
                smooth: true,
                showSymbol: false,
                hoverAnimation: false,
                data: chartData
            }]
    };
    //
    myChart.setOption(option);
    seriTimer = setInterval(function () {
        var obj = [];
        obj = getNextData();
        if (obj.length < 1) {

        } else {
            for (var i = 0; i < 1; i++) {
                var avg=obj[0].average;
                avg=avg/100;
                console.log(obj[0].average);
                parseInt(obj[0].average);
                console.log(parseInt(obj[0].average));
                chartData.shift();
                console.log( parseInt(obj[0].average));
                chartData.push(addChartData(obj[0].sendTime, avg));

            }
        }
        // console.log(obj);

        myChart.setOption(option);
        myChart.setOption({
            series: [{
                    data: chartData
                }]
        });
    },60000);
}

/**
 * 获取当前时间
 */
function getNowTime() {
    var time = new Date().getTime();
    console.log(time);
}
/**
 * 获取站点统计
 */
function getStationStatics() {
    $.ajax({
        type: "GET",
        async: false,
        url: "../device/getDeviceStatus",
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
            $('#zczd').html(obj.Z);
            $('#yczd').html(obj.Y);
            $('#wsj').html(obj.W);
            $('#yyjgs').html(obj.YY);
        }
    });
}

$(document).ready(function () {
    cityQuery();
    shanshuo();
    initTDTmap();
    deviceQuery();
    // getDeviceNum("10001");
    getEFDataByNum("01120");
    drawChart();
    setSelectValue();
    //getEFDataByNum();
    // getStationStatics();
    window.setInterval(deviceQuery, 30000);////设备
    // window.setInterval(getStationStatics, 5000);
    //chartTimer = window.setInterval(getEFDataByNum, 90000);//表格数据 
    //drawChart();

});

/**闪烁
 * 
 * @returns {undefined}
 */
function shanshuo() {
    var imgid = document.getElementById("yjimg");
    if (imgid.style.visibility == "visible")
        imgid.style.visibility = "hidden";
    else
        imgid.style.visibility = "visible";
    setTimeout('shanshuo()', 1000);
}
/**
 * 获取吓一跳数据
 * @returns {undefined}
 */
function getNextData() {
    var datas = [];
    var num = selectValue;
    $.ajax({
        type: "GET",
        async: false,
        url: "../efdata/getefdatabynumNext/" + num,
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
            datas = obj;
        }
    });
    return datas;
}
