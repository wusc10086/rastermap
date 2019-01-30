/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var mapcurrentid;
var mapcitytype;
var wxstarttime;
var wxendtime;
$(document).ready(function () {
    initMapQueryProvince();
    MapinitXZQuery();
});
/**
 * 制图产品行政查询
 * @returns {undefined}
 */
function MapinitXZQuery() {

    $("#mapprovince").change(function () {
        cleanMarkers();
        cleanImgLayers();
        var st = $("#mapprovince option:selected").attr("id");
        if (st !== '') {
            mapcurrentid = st;
            mapcitytype = "province";
            //查询省级数据
            mapprovincequery(st);
        }
    });
    $("#mapcity").change(function () {
        var st = $("#mapcity option:selected").attr("id");
        if (st !== "") {
            mapcurrentid = st;
            mapcitytype = "city";
            //查询市级数据
            mapcityquery(mapcurrentid);
        }
    });
}
/**
 * 省级数据加载 
 * @param {type} provinceid
 * @returns {undefined}
 */
function mapprovincequery(provinceid) {
    $.ajax({
        type: "GET",
        url: "../map/province/" + provinceid,
        dataType: "json",
        processData: true,
        error: function (req, status, error) {
            if (status === "timeout") {

                alert("请求超时，请稍后再试!！");
                return;
            } else if (status === "error") {

                alert("数据请求失败，请稍后再试!如果还未解决，请联系管理员！");
                return;
            }
            return;
        },
        success: function (obj) {
            $("#mapcity").html("");
            $("#mapcity").append("<option>----请选择(市)----</option>");
            $.each(obj.data, function (i, n) {
                var name = n.name;
                var id = n.cityid;
                $("#mapcity").append("<option id='" + id + "'>" + name + "</option>");
                if (obj.geo.length < 1) {
                    alert("该区域无polygon数据！");
                    return;
                }
                drawWktFeatures(obj.geo, 7);
            });
        }
    });
}

function mapcityquery(cityid) {
    $.ajax({
        type: "GET",
        url: "../map/city/" + cityid,
        dataType: "json",
        processsData: true,
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
            if (obj.length < 1) {
                return;
            }
            drawWktFeatures(obj, 10);
        }
    });
}


/**
 * 初始化 省信息
 * @returns {undefined}
 */
function initMapQueryProvince() {
    $.ajax({
        type: 'GET',
        url: '../map/province',
        dataType: 'json',
        processData: true,
        error: function (req, status, error) {
            if (status === "timeout") {
                alert("请求超时，请稍后再试！");
            } else if (status === 'error') {
                alert("数据请求失败，请稍后再试!如果还未解决，请联系管理员！");
                return;
            }
        },
        success: function (obj) {
            $.each(obj, function (i, n) {
                var name = n.name;
                var id = n.province;
                $('#mapprovince').append("<option id='" + id + "'>" + name + "</option>");
            });
        }
    });
}

/**
 * 
 * 制图产品数据查询
 * @returns {undefined}
 */
function mapproductquery() {
    if (!mapcurrentid) {
        alert("请至少选择一个行政区域！");
        return;
    }

    if (wxstarttime && wxendtime)
    {
        var datestart = new Date($("#mapwxstartime").datebox('getValue'));
        var mstart = parseInt(datestart.getMonth()) + 1;
        var daystart = datestart.getDay();
        if (mstart < 10) {
            mstart = "0" + mstart;
        }
        if (daystart < 10) {
            daystart = "0" + daystart;
        }
        wxstarttime = datestart.getFullYear() + '-' + mstart + '-' + daystart;
        var dateend = new Date($("#mapwxendtime").datebox('getValue'));
        var mend = parseInt(dateend.getMonth()) + 1;
        var dayend = dateend.getDay();
        if (mend < 10) {
            mend = "0" + mend;
        }
        if (dayend < 10) {
            dayend = "0" + dayend;
        }
        wxendtime = dateend.getFullYear() + '-' + mend + '-' + dayend;
    }
    var mapkeywords = $("#KeyWordsText").val();

    var flag = "------请选择------";
    var parms = {
        mapxzqhid: mapcurrentid,
        citytype: mapcitytype,
        keywords: mapkeywords,
        starttime: wxstarttime,
        endtime: wxendtime
    };
    if (parms.keywords === "请输入规划、污水...") {
        parms.keywords = "";
    }
    if (parms.starttime === "NaN-NaN-NaN") {
        parms.starttime = "";
    }
    if (parms.endtime === "NaN-NaN-NaN") {
        parms.endtime = "";
    }

    parms = JSON.stringify(parms);
    $.ajax({
        type: "POST",
        url: "../map/product/" + parms,
        dataType: "json",
        contentType: "application/x-www.form-urlencode;charset=utf-8",
        processData: true,
        error: function (req, status, error) {
            //关闭动画
            ajaxLoadEnd();
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
            $('#product_result').tabs('select', 1);
            var rows = [];
            $.each(obj, function (i, n) { 
                var mn = n.mapname;
               // var shp = n.shpurl;
                var map = n.mapurl;
                var mapid = n.mapid;
               // var showshpbtn = "<button id=" + shp + " title='加载' style='background:url(../images/ok2.bmp);background-repeat:no-repeat;height:20px;width:20px'" + "></button>";
                var showmapbtn = "<button id=" + map + " title='加载' style='background:url(../images/ok2.bmp);background-repeat:no-repeat;height:20px;width:20px'" + "></button>";
               // var downloadshpbtn = "<button title='下载' style='background:url(../images/download.png);background-repeat:no-repeat;height:20px;width:20px'></button>";
                var downloarasterbtn = "<button title='下载' style='background:url(../images/download.png);background-repeat:no-repeat;height:20px;width:20px'></button>";
                rows.push({
                    name: mn,
                    mapid: mapid,
                  
                    map: showmapbtn,
                  
                    downloadras: downloarasterbtn
                });
            });
            $('#mapinfotable').datagrid({data: rows, checkOnSelect: false});
            $("#mapinfotable").datagrid({
                onClickCell: function (index, field, value) {

//                    if (field === "shp") {
//                        alert("矢量图层没有发布");
//                        return;
//                    }
                    if (field === 'downloadras') {
                        // $.(this).datagrid()
                        var row = $(this).datagrid('getData').rows[index];
                        var col = row.mapid;
                       // alert(col);
                        downlaodmoudle(col);
                        return;
                    }
//                    if (field === 'downloadshp') {
//                        //alert("请联系管理员获取下载权限。管理员：徐先生，电话：1058887100");
////                        var row = $(this).datagrid('getData').rows[index];
////                        var col = row.mapid;
////                        alert(col);
////                        downlaodmoudle(col);
////                        return;
////                        return;
//                        alert("矢量图层没有发布");
//                        return;
//                    }
                    if (field === 'mapid') {

                        return;
                    }
                    if (field === 'map') {
                        var i = value.indexOf("id");
                        var i2 = value.indexOf("title");
                        var layer = value.substring(i + 3, i2 - 1);
                        var sl = "#" + layer;
                        if ($(sl).attr("title") === "加载") {
                            addWMSLayer(layer, layer);
                            $(sl).attr("title", "移除");
                            $(sl).css("background", "url(../images/cancel.bmp)");
                        } else {
                            hidewmslayer(layer);
                            $(sl).attr("title", "加载");
                            $(sl).css("background", "url(../images/ok2.bmp)");
                        }
                        return;
                    }
                }

            });
            $('#mapinfotable').datagrid({
                onDblClickRow: function (index, row) {
                    var mapid = row.mapid;
                    var height = 700, width = 800;
                    var centerH = (window.screen.height - height) / 2;
                    var centerW = (window.screen.width - width) / 2;
                    window.open("mapdatainfo.jsp?mapid=" + mapid,
                            "影像详细信息", "height=700,width=800,top=" + centerH + ",left=" + centerW + " toolbar=no,menubar=no,status=no");
                }
            });
            ajaxLoadEnd();
        }
    });
}
/**
 * 制图缩略图查询
 * @param {type} mapid
 * @returns {undefined}
 */
function addmapimage(mapid) {
    var urlmap = "../map/mapimage/" + mapid;
    $("#mapsuoluetu").attr("src", urlmap);
}

   