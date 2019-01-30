var map;
var markData = new Array();
var marker = new Array();
//天地图图层数据
var htmlUrl = document.location.host,
        stringIndex = htmlUrl.indexOf("//"),
        subString = htmlUrl.substring(0, stringIndex - 1);
var tiandituUrl = 'http://t{s}.tianditu.com/DataServer?T=img_w&x={x}&y={y}&l={z}';
var tiandituAnoUrl = 'http://t{s}.tianditu.com/DataServer?T=cia_w&x={x}&y={y}&l={z}';
var gaodeUrl = "http://" + htmlUrl + '/rastermap/tile/maptile/788865972/{z}/{x}/{y}';
var gaodeUrlAnoUrl = "http://" + htmlUrl + '/rastermap/tile/maptile/788865972/{z}/{x}/{y}';
var heatmap = new L.DivHeatmapLayer();


var tiandituLayer = new L.TileLayer(tiandituUrl, {
    maxZoom: 18,
    minZoom: 2,
    subdomains: '1234567',
    attribution: '天地图影像'
            // attribution: '高德影像'
});
var gdLayer = new L.TileLayer(gaodeUrl, {
    maxZoom: 18,
    minZoom: 2,
    subdomains: '1234567',
    //attribution: '天地图影像'
    attribution: '高德影像'
});
var tiandituAnoLayer = new L.TileLayer(tiandituAnoUrl, {
    maxZoom: 13,
    minZoom: 2,
    subdomains: '1234567',
    attribution: '高德影像注记'
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
        zoom: 5,
        maxZoom: 21,
        zoomControl: false,
        //layers: [tiandituLayer, tiandituAnoLayer, cityLayer],
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
        //"热力图": yheatmapLayer,
        "高德影像注记": tiandituAnoLayer,
        "行政区划": cityLayer
    };
    L.control.layers(baseMaps, overlayMaps).addTo(map);


};

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
            var time;
            datas.splice(0, datas.length);
            $.each(obj, function (i, n) {
                var value;
                value = n.average;
                if (value && n.Latitude && n.Longitude) {
                    datas.push({
                        "lat": parseFloat(n.Latitude),
                        "lng": parseFloat(n.Longitude),
                        "count": parseFloat(Math.abs(n.average))
                    });
                     time = n.sendtime;
                     time = time.substring(0, time.length - 2);
                }
            });
            drawHM(map.getZoom());
            if (time) {
                document.getElementById("datas").innerHTML = time;
            }
        }
    });
}