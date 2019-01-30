/**
 * Leaflet 版本地图显示方案
 * @type L.Map
 */
var map;
var view;
var markerLayer;
var cityLayer;
var ZTLayer;
var wktparser = new Wkt.Wkt();
var vectorSource;
var dragpan;
var dragbox;
var imgLayer;
var ProductLayer;
var Layer;
var WMSOpt = {};
var popup;

//自定义了一个Map对象，类似Java里的Map 对象
var imgLayers = new Map();
var vecLayers = new Map();
var wmsLayers = new Map();

var htmlUrl = document.location.host,
        stringIndex = htmlUrl.indexOf("//"),
        subString = htmlUrl.substring(0, stringIndex - 1);
//var tiandituUrl = 'http://t{s}.tianditu.com/DataServer?T=img_w&x={x}&y={y}&l={z}';
//var tiandituAnoUrl = 'http://t{s}.tianditu.com/DataServer?T=cia_w&x={x}&y={y}&l={z}';
var tiandituUrl = "http://" + htmlUrl + '/rastermap/tile/maptile/47626774/{z}/{x}/{y}';
var tiandituAnoUrl = "http://" + htmlUrl + '/rastermap/tile/maptile/788865972/{z}/{x}/{y}';
var tiandituLayer = new L.TileLayer(tiandituUrl, {
    maxZoom: 13,
    minZoom: 2,
    subdomains: '1234567',
//    attribution: '天地图影像'
    attribution: '高德影像'
});
var tiandituAnoLayer = new L.TileLayer(tiandituAnoUrl, {
    maxZoom: 13,
    minZoom: 2,
    subdomains: '1234567',
//    attribution: '天地图影像注记'
    attribution: '高德影像注记'
});

/**
 * 初始化地图采用 EPSG:4326 模式
 * @returns {undefined}
 */
var initTDTmap = function () {
    var latlng = new L.LatLng(37.98936, 111.42307);
    map = new L.Map('map', {
       crs:L.CRS.EPSG3857,
        keyboard:true,
        fadeAnimation:true,
        boxZoom:true,
        attributionControl: false,
        trackResize: true, 
        center: latlng, 
        zoom: 2, 
        maxZoom: 13, 
        layers: [tiandituLayer, tiandituAnoLayer,heatmapLayer],
        contextmenu: true,
        contextmenuWidth: 120,
        contextmenuItems: [{
                text: '显示坐标',
                callback: showCoordinates
            }, {
                text: '地图居中',
                callback: centerMap
            }, '-', {
                text: '地图放大',
                icon: '../js/leaflet/images/zoom-in.png',
                callback: zoomIn
            }, {
                text: '地图缩小',
                icon: '../js/leaflet/images/zoom-out.png',
                callback: zoomOut
            }]
    });
    // map.fitBounds([[31.05, 117.05],[35, 122]]);
    
    //初始化GeoJSON图层，提高加载数据的性能
    markerLayer = L.geoJson(null, {
        style: function (f) {
            return {color: "blue", fill: false, fillOpacity: 0.5};
        }
    });

    cityLayer = L.geoJson(null, {
        style: function (f) {
            return {color: "red", fill: false, fillOpacity: 0.5};
        }
    });
    
    map.on('overlayadd', function (e) {
//        if (e.name = "高德影像注记") {
//            tiandituAnoLayer.setOpacity(1);
//        }
        if (e.name == "专题图层") {
            //map.panTo([31.318, 120.6099]);
            tiandituAnoLayer.setOpacity(0);
            //map.fitBounds([[31.239, 120.48], [31.367, 120.8005]]);
        }
        if (e.name == "栅格成果") {
            // map.fitBounds([[31.230, 120.48], [31.375, 120.8005]]);
        }

    });
    map.on('overlayremove', function (e) {
        if (e.name == "专题图层") {
            tiandituAnoLayer.setOpacity(1);
        }
    });
    markerLayer.addTo(map);
    cityLayer.addTo(map);

    var baseMaps = {
        "高德影像": tiandituLayer,
    };


    var overlayMaps = {
        "高德影像注记": tiandituAnoLayer,
        "行政区划": cityLayer
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
 * 清除所有影像图范围数据
 * @returns {undefined}
 */
function cleanMarkers() {
    if (markerLayer) {
        markerLayer.clearLayers();
    }
}

/**
 * 清除行政区划图层
 */
function cleanCityLayer() {
    if (cityLayer) {
        cityLayer.clearLayers();
    }
}

/**
 *  清除所有绘制的数据
 * @returns {undefined}
 */
function clean() {
    cleanMarkers();
    cleanCityLayer();
}


/**
 *  批量绘制wkt格式的空间数据
 * @param {type} data
 * @param {type} zoomlevel
 * @returns {undefined}
 */
function drawWktFeatures(data, zoomlevel) {
    cleanCityLayer();
    $.each(data, function (i, n) {
        var name = n.name;
        var wkt = n.theGeom;
        var data = wktparser.read(wkt).toJson();
        var dataout = transform(data);
        var feature = L.geoJson(data, {//创建一个GeoJson 图层
            style: function (f) {
                return {color: "red", fill: false, fillOpacity: 0.5};
            }
        });
        cityLayer.addLayer(feature);
    });
    map.fitBounds(cityLayer.getBounds());
}


/**
 *  绘制wkt 格式的空间数据
 * @param {type} wkt
 * @param {type} id
 * @param {type} content
 * @returns {undefined}
 */
function drawWkt(wkt, id, content) {

    var data = wktparser.read(wkt).toJson();
    
    var dataout = transform(data);
//    alert("转换结果")
//    alert(dataout===data)
    var feature = L.geoJson(dataout, {
        style: function (f) {
            return {color: "blue", fill: true, fillOpacity: 0.1};
        }
    });
    feature.bindPopup(content);
    feature.on({
        mouseover: highlightFeature,
        mouseout: resetHighlight
    });
    vecLayers.put(id, feature);
    drawFeature(feature);
}


/**  已经废出
 * 绘制查询矢量的图层
 * @author yzz
 * @param {type} feature
 * @returns {undefined}
 */
function DrawQueryShp(wkt, id) {
    var data = wktparser.read(wkt).toJson();
    var feature = L.geoJson(data, {
        style: function (f) {
            return {color: "red", fill: false, fillOpacity: 0.5};
        }
    });
    drawFeature(feature);
    vecLayers.put(id, feature);
    map.fitBounds(feature.getBounds());
}

//绘制要素
function drawFeature(feature) {
    if (feature) {
        markerLayer.addLayer(feature);
    }
}

/**
 *  删除已经添加的要素
 * @param {type} id  要素id
 * @returns {undefined}
 */
function hideFeature(id) {

    var f = vecLayers.get(id);
    if (f) {
        markerLayer.removeLayer(f);
    }
}


/**
 * 清除所有静态图片图层
 * @returns {undefined}
 */
function OcleanImgLayers() {
    if (imgLayers.size() > 0) {
        imgLayers.clear();
    }
}
/**
 * yzz   清除所有静态图片
 * 
 * @returns {undefined}
 */
function cleanImgLayers() {
    if (imgLayers.size() > 0) {
        var e = imgLayers["elements"];
        for (var i = 0; i < e.length; i++) {
            var id = e[i].key;
            var layer = imgLayers.get(id);
            if (layer) {
                map.removeLayer(layer);
            }
        }
        imgLayers.clear();
    }
}

/*
 *  添加自定义的缩略图到地图上
 * @param {type} id
 * @param {type} url
 * @returns {undefined}
 */
function addCustomImageLayer(id, url) {
    var f = vecLayers.get(id);
    var extent = f.toGeoJSON();
    addStaticImageLayer(id, map, url, extent, "EPSG:4326");
    // map.fitBounds(f.getBounds());
}



/**
 *  添加指定范围的静态图片
 * @param {type} id 图层唯一ID
 * @param {type} mapobj 地图容器
 * @param {type} url  静态图像地址 
 * @param {type} imageExtent 静态图像空间范围
 * @param {type} proj  静态图象的空间参考
 * @returns {undefined}  
 */
function addStaticImageLayer(id, mapobj, url, extent, proj) {
    if (id) {
        var imgLayer = imgLayers.get(id);
        //如果已存在指定id的图层，直接返回。
        if (imgLayer) {
            console.log("已经存在 id = " + id + " 对应的图层！");
            showStaticImgLayer(id);
            return;
        }
        var imageExtent = extent.features[0].geometry.coordinates[0];
        var pointArray = imageExtent[0],
                FSWPoint_lng_lat = pointArray[3], FNEPoint_lng_lat = pointArray[1],
                imageBounds = [[FSWPoint_lng_lat[1], FSWPoint_lng_lat[0]], [FNEPoint_lng_lat[1], FNEPoint_lng_lat[0]]];
        imgLayer = L.imageOverlay(url, imageBounds, {
            opacity: 1,
            interactive: true
        });
        map.addLayer(imgLayer);
        imgLayer.bringToFront();

        if (mapobj) {
            mapobj.addLayer(imgLayer);
        } else {
            //采用全局的map对象，如果没有全局的map对象则输出
            map.addLayer(imgLayer);
        }
        imgLayers.put(id, imgLayer);
    } else {
        throw "静态图片ID为null！URL=" + url;
    }
}

/**
 * 显示指定id的静态图片图层
 * @param {type} id
 * @returns {undefined}
 */
function showStaticImgLayer(id) {
    var layer = imgLayers.get(id);
    if (layer) {
        map.addLayer(layer);
    }
}
/**
 * 高亮显示
 * @param {type} e
 * @returns {undefined}
 */
function highlightFeature(e) {
    var layer = e.target;
    layer.setStyle({
        weight: 3,
        color: 'yellow',
        dashArray: '',
        fillopacity: 0.4
    });
    if (!L.Browser.ie && !L.Browser.opera) {
        layer.bringToFront();
    }
}
function resetHighlight(e) {
    var layer = e.target;
    layer.setStyle({
        color: "blue",
        fill: true,
        fillOpacity: 0.1
    });
    if (!L.Browser.ie && !L.Browser.opera) {
        layer.bringToFront();
    }
}
/**
 *  隐藏指定id的静态图片图层
 * @param {type} id
 * @returns {undefined}
 */
function hideStaticImgLayer(id) {
    var layer = imgLayers.get(id);
    if (layer) {
        map.removeLayer(layer);
    }
}
/**
 * 矢量上传绘制
 * @author yzz
 * @param {type} files
 * @returns {undefined}
 */
function YZZOhandleFiles(files) {
    if (files.length) {
        var wktparser = new Wkt.Wkt();
        $.ajaxFileUpload({
            url: '../upload/uploadfile2',
            fileElementId: 'shpfile',
            dataType: 'json',
            processData: false,
            success: function (data, status) {
                $.each(data, function (i, n) {
                    var polyjson = wktparser.read(n);
                    DrawQueryShp(polyjson, i);
                });
            }
        });
    }
}
function handleFiles(files) {
    if (files.length) {
        //alert("上传文件应为.zip或者KML文件");
    } else {
        alert("您确定选择了文件？");
    }
}

/**
 * 展示wms
 * @param {type} layername
 * @returns {undefined}
 */
function showWMSLayer(layername) {
    //initWMS();
    Layer = L.tileLayer.wms(wmsurl, {
        layers: layername,
        format: 'image/png',
        transparent: true,
    });
    Layer.addTo(map);
    //tiandituAnoLayer.setOpacity(0);
    Layer.bringToFront();
    // map.fitBounds([[31.239, 120.48], [31.367, 120.8005]]);
}
/**
 * 移除wms
 * @param {type} layername
 * @returns {undefined}
 */
function removeWMSLayer(layername) {
    Layer.setOpacity(0);
    //tiandituAnoLayer.setOpacity(1);
    map.removeLayer(Layer);
}
function removeallWMSLayer() {
    map.removeLayer()
}
/**
 * 
 * @param {type} id 图层id
 * @param {type} layer 图层名
 * @returns {undefined}
 */
function addWMSLayer(id, layer) {
    if (id) {
        var wmslayer = wmsLayers.get(id);
        if (wmslayer) {
            console.log("已经存在 id = " + id + " 对应的图层！");
            showStaticwmsLayer(wmslayer);
            return;
        }
    }
    wmslayer = L.tileLayer.wms(wmsurl, {
        format: 'image/png',
        transparent: true,
        layers: layer,
    });

    map.addLayer(wmslayer);
    wmslayer.bringToFront();
    wmsLayers.put(id, wmslayer);
    // map.fitBounds([[31.239, 120.48], [31.367, 120.8005]]);
}
/**
 * 显示WMSlayer
 * @param {type} id
 * @returns {undefined}
 */
function  showStaticwmsLayer(id) {
    map.addLayer(id);
    // map.fitBounds([[31.239, 120.48], [31.367, 120.8005]]);
}

/**
 * 隐藏wmslayer
 * @param {type} id
 * @returns {undefined}
 */
function hidewmslayer(id) {
    var layer = wmsLayers.get(id);
    if (layer) {
        map.removeLayer(layer);
    }
}
/**
 * 删除wmslayer
 * @param {type} id
 * @returns {undefined}
 */
function deletewmslayer(id) {
    var layer = wmsLayers.get(id);
    if (layer) {
        map.removeLayer(layer);
    }
    wmsLayers.remove(id);
}
