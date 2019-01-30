
var map;
var markData = new Array();
var marker = new Array();
//天地图图层数据
var htmlUrl = document.location.host,
        stringIndex = htmlUrl.indexOf("//"),
        subString = htmlUrl.substring(0, stringIndex - 1);
var tiandituUrl = 'http://t{s}.tianditu.com/DataServer?T=img_w&x={x}&y={y}&l={z}';
var tiandituAnoUrl = 'http://t{s}.tianditu.com/DataServer?T=cia_w&x={x}&y={y}&l={z}';

var gaodeUrl = "http://" + htmlUrl + '/rastermap/tile/maptile/321978823/{z}/{x}/{y}';
var gaodeUrlAnoUrl = "http://" + htmlUrl + '/rastermap/tile/maptile/2046959579/{z}/{x}/{y}';
var heatmap = new L.DivHeatmapLayer();
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


var level1Icon = L.divIcon({
    className: 'leaflet-div-icon3',
    iconSize: [20, 20]
});
var level2Icon = L.divIcon({
    className: 'leaflet-div-icon4',
    iconSize: [20, 20]
});
var level3Icon = L.divIcon({
    className: 'leaflet-div-icon5',
    iconSize: [20, 20]
});
/**
 * 初始化地图采用 EPSG:4326 模式
 * @returns {undefined}
 */
var initTDTmap = function () {
    var latlng = new L.LatLng(49.7896, 108.42307);
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
        layers: [tiandituLayer, tiandituAnoLayer],
        contextmenu: true,
        contextmenuWidth: 120
    });
    L.control.scale().addTo(map);

    var baseMaps = {
        "天地图": tiandituLayer,
        "高德地图": gdLayer
    };


    var overlayMaps = {
        "高德影像注记": tiandituAnoLayer
    };
    L.control.layers(baseMaps, overlayMaps).addTo(map);
};

/**
 * 显示坐标
 * @param {type} e
 * @returns {undefined}
 */
function showCoordinates(e) {
    alert("经度：" + e.latlng.lng + "  纬度：" + e.latlng.lat);
}
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
 * 获取预警信息
 * @return {undefined}
 */
function getWarnings() {
    $.ajax({
        type: "GET",
        url: "../warning/getWarnings",
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
            var data = [];
            data.splice(0, data.length);
            markData.splice(0, markData.length);
            markData = obj;

            $.each(obj, function (i, n) {
                var value = getValue(n.SendData);
                data.push({
                    "lat": n.Latitude,
                    "lon": n.Longitude,
                    "value": value
                });

            });
           // heatmap.addTo(map);
           // heatmap.clearData();
            heatmap.setData(data);
            drawMarker();
        }
    });
}
/**
 * 绘制电场预警marker
 * @return {undefined}
 */
function drawMarker()
{
    for (i = 0; i < marker.length; i++) {
        map.removeLayer(marker[i]);
    }
    while (marker.length !== 0)
    {
        marker.pop();
    }
    $.each(markData, function (i, n) {
        n.AlertArede = n.AlertArede.replace(/\s+/g, "");
        if (n.AlertArede === '1')//一级预警判断
        {
            var LamMarker = L.marker([n.Latitude, n.Longitude], {icon: level1Icon});
            marker.push(LamMarker);
            map.addLayer(marker[i]);
        } else if (n.AlertArede === '2')//二级预警判断
        {
            var LamMarker = L.marker([n.Latitude, n.Longitude], {icon: level2Icon});
            marker.push(LamMarker);
            map.addLayer(marker[i]);
        } else if (n.AlertArede === '3')//三级预警判断
        {
            var LamMarker = L.marker([n.Latitude, n.Longitude], {icon: level3Icon});
            marker.push(LamMarker);
            map.addLayer(marker[i]);
        }
    });

}
/**
 * 转化成透明度值
 * @param {type} value
 * @return {Number}
 */

function getValue(value)
{
    if (value < 500)
    {
        return 0.5;
    }
    if (value < 1000)
    {
        return 0.6;
    }
    if (value < 1500)
    {
        return 0.7;
    }
    if (value < 2000)
    {
        return 0.8;
    }
    if (value < 2500)
    {
        return 0.9;
    }
    if (value > 2500)
    {
        return 1;
    }
}





