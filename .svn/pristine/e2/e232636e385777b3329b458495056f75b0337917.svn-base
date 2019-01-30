var wktparser = new Wkt.Wkt();
var cityLayer = L.geoJson(null, {
        style: function (f) {
            return {color: "red", fill: false, fillOpacity: 0.5};
        }
    });
function cityQuery() {
    $.ajax({
        type: "GET",
        url: "../xzqh/province/" + provinceid,
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
            console.log(obj);
            //无区域坐标时不绘制
            if (obj.geo.length < 1) {
//                alert("该区域无polygon数据！");
                return;
            }
            //map.panTo();
            drawWktFeatures(obj.geo, 6);
            map.panTo(new L.LatLng(latitude, longitude));
            map.setZoom(zoom);
        }
    });
}

/**
 *  批量绘制wkt格式的空间数据
 * @param {type} data
 * @param {type} zoomlevel
 * @returns {undefined}
 */
function drawWktFeatures(data, zoomlevel) {
    //cleanCityLayer();
    $.each(data, function (i, n) {
        var name = n.name;
        var wkt = n.theGeom;
        var data = wktparser.read(wkt).toJson();

        var feature = L.geoJson(data, {
            style: function (f) {
                return {color: "red", fill: false, fillOpacity: 0.5};
            }
        });
        cityLayer.addLayer(feature);
    });
    map.fitBounds(cityLayer.getBounds());
    //map.panTo()
}
