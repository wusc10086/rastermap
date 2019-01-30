/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var map;
var xA = new Array();
var yA = new Array();
var startTime;//开始时间
var endTime;//结束时间
var timeCell;//时间间隔
var trandTimer;
var heatmap = new L.DivHeatmapLayer();
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
    attribution: '高德影像'
});
var tiandituAnoLayer = new L.TileLayer(tiandituAnoUrl, {
    maxZoom: 13,
    minZoom: 2,
    subdomains: '1234567',
    attribution: '高德影像注记'
});

var yheatmapLayer;
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
        zoom: 5,
        maxZoom: 13,
        zoomControl: false,
        // layers: [tiandituLayer, tiandituAnoLayer,cityLayer],
        layers: [gdLayer, cityLayer],
        contextmenu: true,
        contextmenuWidth: 120
    });
    L.control.scale().addTo(map);

    var zoomHome = L.Control.zoomHome();
    zoomHome.addTo(map);
    var baseMaps = {
        "天地图": tiandituLayer,
        "高德地图": gdLayer
    };


    var overlayMaps = {
        "高德影像注记": tiandituAnoLayer
    };
    L.control.layers(baseMaps, overlayMaps).addTo(map);
    map.on('zoomend', function (e) {
        console.log(e.target.getZoom());
        var zoom = e.target.getZoom();
        drawHM(zoom);
    });
};


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
 * 开始播放
 * @return {undefined}
 */
function playStart()
{
    if (trandTimer) {
        playEnd();
    }
    startTime = getLastThreeHours();
    //获取页面的数据
    timeCell = document.getElementById("playInt").value;
    interval = document.getElementById("interval").value;
    interval = 1000 * interval;
    ;//时间间隔
    if (interval === "" || timeCell === "")
    {  //默认数据间隔 5分钟
        timeCell = "15";
        //默认播放间隔3秒
        interval = 1000 * 3
    } else {
        trandTimer = window.setInterval(getTrandData, interval);
    }

}

function playEnd()
{
    clearInterval(trandTimer);
}

/**
 *获取趋势数据
 * @return {undefined}
 */
function getTrandData()
{
    var timestamp = Date.parse(new Date(startTime));
    //分钟转毫秒
    var newTimeStamp = timestamp + timeCell * 1000 * 60;
    var newDate = new Date();
    newDate.setTime(newTimeStamp);
    var tempEndTime = newDate.format('yyyy-MM-dd hh:mm:ss');

    if (startTime > getNow()) {
        startTime = getLastThreeHours();
        var timestamp = Date.parse(new Date(startTime));
        //分钟转毫秒
        var newTimeStamp = timestamp + timeCell * 1000 * 60;
        var newDate = new Date();
        newDate.setTime(newTimeStamp);
        var tempEndTime = newDate.format('yyyy-MM-dd hh:mm:ss')
        console.log(tempEndTime);
    }
    $.ajax({
        type: "GET",
        url: "../trand/getTrandData/" + startTime + "/" + tempEndTime,
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
            //   通过数据绘制热力图 2：设置新的起始时间
            startTime = tempEndTime;
            console.log(startTime);
            datas.splice(0, datas.length);
            var datatime;
            $.each(obj, function (i, n) {

                if (n.average && n.Latitude && n.Longitude) {
                    datas.push({
                        "lat": parseFloat(n.Latitude),
                        "lng": parseFloat(n.Longitude),
                        "count":parseFloat(parseFloat(Math.abs(n.average)))
                    });
                    datatime = n.time;
                    datatime = datatime.substring(0, datatime.length - 2);
                }
            });
            if (yheatmapLayer) {
                map.removeLayer(yheatmapLayer);
            }
            // alert(datas);
            drawHM(map.getZoom());
            if (datatime) {
                document.getElementById("datas").innerHTML = datatime;
            }else{
                 document.getElementById("datas").innerHTML = "无数据";
            }
        }
    });
}

/**
 * 转化时间格式
 * @param {type} format
 * @return {unresolved}
 */
Date.prototype.format = function (format) {
    var date = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S+": this.getMilliseconds()
    };
    if (/(y+)/i.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
    }
    for (var k in date) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1
                    ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
        }
    }
    return format;
}

/**
 * 获取当前时间
 * @returns {undefined}
 */
function getNow() {
    var date = new Date();
    var now = date.format('yyyy-MM-dd hh:mm:ss');
    var mils = date.getTime();
    return now;
}

/**
 * 当前过去三小时
 * @returns {undefined}
 */
function getLastThreeHours() {
    var mils = new Date().getTime();
    var startTime = mils -3 * 60 * 60 * 1000;
    var last3hours = new Date(startTime);
    var last3 = last3hours.format('yyyy-MM-dd hh:mm:ss');
    return last3;
}