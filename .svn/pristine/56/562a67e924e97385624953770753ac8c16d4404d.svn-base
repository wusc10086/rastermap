var starttime;
var endtime;
var wxstarttime;
var wxendtime;
var currentid;
var provinceid;
var cityid;
var townid;
var citytype = "sheng";
var colorid;
var color;
var wktparser = new Wkt.Wkt();
var spinner = new Spinner();


$(document).ready(function () {
    
    initXZQuey();
    initSatelliteQuery();
    initSXQuery();
    initLastTimeQuery();
    $("#querybtn").on("click", function (evt) {
        cleanMarkers();
        cleanImgLayers();
        var select = $('#mytab2').tabs('getSelected');
        var index = $('#mytab2').tabs('getTabIndex', select);
        var id = select.context.id;
        if (id === "sxcx") {//属性查询
// queryProductInfoRaster();
            querySXCXDataSourceInfoRaster();
        } else {
            queryDataSourceInfoRaster();
            // queryProductInfoRaster();
        }
    });
});
function onStartSelect(date) {
    var m = parseInt(date.getMonth()) + 1;
    var d = parseInt(date.getDate());
    if (m < 10) {
        m = "0" + m;
    }
    if (d < 10) {
        d = "0" + d;
    }
    starttime = date.getFullYear() + "-" + m + "-" + d;
}
function onEndSelect(date) {
    var m = parseInt(date.getMonth()) + 1;
    var d = parseInt(date.getDate());
    if (m < 10) {
        m = "0" + m;
    }
    if (d < 10) {
        d = "0" + d;
    }
    endtime = date.getFullYear() + "-" + m + "-" + d;
}
function onWxStartSelect(date) {
    var m = parseInt(date.getMonth()) + 1;
    var d = parseInt(date.getDate());
    if (m < 10) {
        m = "0" + m;
    }
    if (d < 10) {
        d = "0" + d;
    }
    wxstarttime = date.getFullYear() + "-" + m + "-" + d;
}
function onWxEndSelect(date) {
    var m = parseInt(date.getMonth()) + 1;
    var d = parseInt(date.getDate());
    if (m < 10) {
        m = "0" + m;
    }
    if (d < 10) {
        d = "0" + d;
    }
    wxendtime = date.getFullYear() + "-" + m + "-" + d;
}

/**
 * 初始化行政查询界面
 * @returns {undefined}
 */
function initXZQuey() {
//初始化省信息
    initProvince();
    $("#sheng").change(function ()
    {
        cleanMarkers();
        cleanImgLayers();
        var st = $("#sheng option:selected").attr("id");
        if (st !== '') {
            currentid = st;
            pronvinceid = st;
            citytype = "province";
            cityQuery(pronvinceid);
        }
    });
    $("#shi").change(function ()
    {
        // console.log($("#shi option:selected"));
        var flag = $("#shi option:selected").attr("id");
        var st;
        if (typeof (flag) !== "undefined") {
            st = $("#shi option:selected").attr("id");
            if (st !== '') {
                currentid = st;
                cityid = st;
                citytype = "city"
                areaQuery(cityid);
            }
        }
        // var st = $("#shi option:selected").attr("id");

    });
    $("#xian").change(function ()
    {
        var flag = $("#xian option:selected").attr("id");
        var st;
        if (typeof (flag) !== "undefined") {
            st = $("#xian option:selected").attr("id");
            currentid = st;
            townid = st;
            citytype = "area";
            areaPolyQuery(currentid);
        }

//        if (st !== '') {
//            
//        }
    });
}

function cityQuery(provinceid) {
    $.ajax({
        type: "GET",
        url: "../xzqh/city/" + provinceid,
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
            //先清空内容
            $("#shi").html("");
            $("#shi").append("<option>----请选择(市)----</option>");
            //填充内容
            $.each(obj.data, function (i, n) {
                var name = n.name;
                var id = n.cityid;
                $("#shi").append("<option id='" + id + "'>" + name + "</option>");
            });
            //无区域坐标时不绘制
            if (obj.geo.length < 1) {
//                alert("该区域无polygon数据！");
                return;
            }
            drawWktFeatures(obj.geo, 7);
        }
    });
}

function areaQuery(cityid) {
    $.ajax({
        type: "GET",
        url: "../xzqh/area/" + cityid,
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
            //先清空内容
            $("#xian").html("");
            $("#xian").append("<option>----请选择(县/区)----</option>");
            //填充内容
            $.each(obj.data, function (i, n) {
                var name = n.name;
                var id = n.areaid;
                $("#xian").append("<option id='" + id + "'>" + name + "</option>");
            });
            //无区域坐标时不绘制
            if (obj.geo.length < 1) {
                return;
            }
            drawWktFeatures(obj.geo, 9);
        }
    });
}
function areaPolyQuery(areaid) {
    $.ajax({
        type: "GET",
        url: "../xzqh/area/poly/" + areaid,
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
            //无区域坐标时不绘制
            if (obj.length < 1) {
                alert("该区域无polygon数据！");
                return;
            }
            drawWktFeatures(obj, 10);
        }
    });
}

function initProvince() {
    $.ajax({
        type: "GET",
        url: "../xzqh/province",
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
            //先清空内容
            // $("#rdjc").html("");
            //填充内容
            $.each(obj, function (i, n) {
                var name = n.name;
                var id = n.provinceid;
                $("#sheng").append("<option id='" + id + "'>" + name + "</option>");
            });
        }
    });
}

/**
 * 初始化传感器查询界面
 * @returns {undefined}
 */
function initSXQuery() {
    $.ajax({
        type: "GET",
        url: "../query/cgq",
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
            //先清空内容
            // $("#rdjc").html("");
            //填充内容
            $.each(obj, function (i, n) {
                var name = n.name;
                var id = n.id;
                $("#cgq").append("<option id='" + id + "'>" + name + "</option>");
            });
        }
    });
    $.ajax({
        type: "GET",
        url: "../properties/product",
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
            //先清空内容
            // $("#rdjc").html("");
            //填充内容
            $.each(obj[0].product, function (i, n) {
                // var name = n.name;
                //var id = n.id;
                $("#sjlx").append("<option id='" + n + "'>" + n + "</option>");
            });
            $.each(obj[1].producer, function (i, n) {
                // var name = n.name;
                //var id = n.id;
                $("#scdw").append("<option id='" + n + "'>" + n + "</option>");
            });
        }
    });
}

/**
 * 初始化卫星查询界面
 * @returns {undefined}
 */
function initSatelliteQuery() {
    $.ajax({
        type: "GET",
        url: "../query/satelliteview",
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
            //先清空内容
            // $("#wx").html("");
            //填充内容
            $.each(obj, function (i, n) {
                var name = n.satename;
                var satenameid = n.satename;
                colorid = n.imagecolor;
                color = n.imagecolor;
                var dem = n.demsource;
                var demid = n.demsource;
                $("#wxmc").append("<option id='" + satenameid + "'>" + name + "</option>");
                $("wxscms").append("<option id='" + colorid + "'>" + color + "</option>");
                $("#demsource").append("<option id='" + demid + "'>" + dem + "</option>");
            });
        }
    });
}
/**
 * 初始化最新数据获取时间
 * @returns {undefined}
 */
function initLastTimeQuery() {
    $.ajax({
        type: "GET",
        url: "../query/lasttime",
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
            $("#pband").html(obj.pband);
            $("#rgb").html(obj.rgb);
        }
    });
}

/**
 *   yzz   JS  属性查询
 *  执行查询任务
 *  
 * @returns {undefined}
 */
function querySXCXDataSourceInfoRaster() {
    var datestart = new Date($("#wxstartime").datebox('getValue'));
    var mstart = parseInt(datestart.getMonth()) + 1;
    var daystart = datestart.getDay();
    if (mstart < 10) {
        mstart = "0" + mstart;
    }
    if (daystart < 10) {
        daystart = "0" + daystart;
    }
    wxstarttime = datestart.getFullYear() + '-' + mstart + '-' + daystart;
    var dateend = new Date($("#wxendtime").datebox('getValue'));
    var mend = parseInt(dateend.getMonth()) + 1;
    var dayend = dateend.getDay();
    if (mend < 10) {
        mend = "0" + mend;
    }
    if (dayend < 10) {
        dayend = "0" + dayend;
    }
    wxendtime = dateend.getFullYear() + '-' + mend + '-' + dayend;
    if (!starttime || !endtime) {
        alert("请选择时间范围！");
        return;
    }
    if (!currentid) {
        alert("请至少选择一个行政区域！");
        return;
    } else {
        var flag = '----请选择----';
        var parm = {
            producttype: $("#sjlx").val(),
            producer: $("#scdw").val(),
            colormode: encodeURI($("#scms").val()),
            resolution: $("#fbl option:selected").attr("id"),
            starttime: starttime,
            endtime: endtime,
            xzqhid: currentid,
            citytype: citytype
        };
        if (parm.producer === flag) {
            parm.producer = "";
        }
        if (parm.producttype === flag) {
            parm.producttype = "";
        }
        if (parm.resolution === flag) {
            parm.resolution = "";
        }
        if (parm.colormode === flag) {
            parm.colormode = "";
        }
        ajaxLoading();
        parm = JSON.stringify(parm);
        $.ajax({
            type: "POST",
            url: "../query/productinfo/" + parm,
            dataType: "json",
            processData: true,
            error: function (req, status, error) {
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
                $('#mytab').tabs('select', 2);
                //先清空内容
                // $("#wx").html("");
                //填充内容
                var rows = [];
                //填充内容
                $.each(obj.data, function (i, n) {
                    var sno = n.sn;
                    var date;
                    var cgq;
                    if (n.producedate) {
                        date = n.producedate;
                    } else {
                        date = "无";
                    }
                    if (n.imgcolormodel) {
                        cgq = n.imgcolormodel;
                    } else {
                        cgq = n.imgcolormodel;
                    }

                    rows.push({
                        mzt: '../images/marker-gold.png',
                        sno: sno,
                        cgq: cgq,
                        date: date,
                        preview: "stst"
                    });
                    var extentwkt = n.extent;
                    if (extentwkt) {
                        drawWkt(extentwkt, sno, 10);
                    } else {
                        alert("获取影像范围出错！");
                    }

                });
                $('#infotable').datagrid({data: rows, checkOnSelect: false});
                $('#infotable').datagrid({
                    onDblClickRow: function (index, row) {
                        var sno = row.sno;
                        var height = window.screen.height, width = window.screen.width;
//                        var centerH = (window.screen.height - height) / 2;
//                        var centerW = (window.screen.width - width) / 2;
                        var centerH = 0;
                        var centerW = 0;
                        window.open("datainfo.jsp?sno=" + sno,
                                "影像详细信息", "height=" + height + ",width=" + width + ",top=" + centerH + ",left=" + centerW);
                    }
                });
                $('#infotable').datagrid({
                    onCheck: function (index, row) {
                        var sno = row.sno;
                        queryDetail(sno);
                    },
                    onUncheck: function (index, row) {
                        var sno = row.sno;
//                        hideFeature(sno);
                        hideStaticImgLayer(sno);
                    }
                });
                ajaxLoadEnd();
            }
        });
    }
}
/**     JS 传感器查询
 *  执行查询任务
 *  //TODO 实现后台查询
 * @returns {undefined}
 */
function queryDataSourceInfoRaster() {
    if (!wxstarttime || !wxendtime) {
        alert("请选择时间范围！");
        return;
    }

    if (!currentid) {
        alert("请至少选择一个行政区域！");
        return;
    }
    var datestart = new Date($("#wxstartime").datebox('getValue'));
    var mstart = parseInt(datestart.getMonth()) + 1;
    var daystart = datestart.getDay();
    if (mstart < 10) {
        mstart = "0" + mstart;
    }
    if (daystart < 10) {
        daystart = "0" + daystart;
    }
    wxstarttime = datestart.getFullYear() + '-' + mstart + '-' + daystart;
    var dateend = new Date($("#wxendtime").datebox('getValue'));
    var mend = parseInt(dateend.getMonth()) + 1;
    var dayend = dateend.getDay();
    if (mend < 10) {
        mend = "0" + mend;
    }
    if (dayend < 10) {
        dayend = "0" + dayend;
    }
    wxendtime = dateend.getFullYear() + '-' + mend + '-' + dayend;
    if (!wxstarttime || !wxendtime) {
        alert("请选择时间范围！");
        return;
    }

    if (!currentid) {
        alert("请至少选择一个行政区域！");
        return;
    } else {
        var flag = '-------请选择-------';
        var parm = {
            satallite: $("#wxmc option:selected").attr("id"),
            colormode: $("#wxscms").val(),
            cgq: $("#demsource").val(),
            // cloud: $("#yl").val(),
            starttime: wxstarttime,
            endtime: wxendtime,
            xzqhid: currentid,
            citytype: citytype
        };
        if (parm.satallite === flag) {
            parm.satallite = "";
        }
        if (parm.cgq === "-------请选择-------") {
            parm.cgq = "";
        }
        if (parm.colormode === "-------请选择-------") {
            parm.colormode = "";
        }
        if (parm.wxscms === flag) {
            parm.wxscms = "";
        }
//加载提示动画
        ajaxLoading();
        parm = JSON.stringify(parm);
        $.ajax({
            type: "POST",
            url: "../query/datasourceinfo/" + parm,
            dataType: "json",
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
                var rows = [];
                //填充内容
                $.each(obj.data, function (i, n) {
                    var sno = n.NumImage;
                    var date = n.ProductDate;
                    var cgq = n.SataliteType;
                    var extent = n.extent;
                    rows.push({
                        mzt: '../images/marker-gold.png',
                        sno: sno,
                        cgq: cgq,
                        date: date,
                        extent: extent,
                        preview: "stst"
                    });
                    var table = "<div><table id='devicetable' class='table-bordered table-hover'>"
                    var body = "<tbody><tr><td align='left'>ID:</td><td>" + sno + "</td></tr>" +
                            "<tr><td align='right'>生产时间:</td><td>" + date + "</td></tr>" +
                            "<tr><td align='right'>色彩模式:</td><td>" + cgq + "</td></tr></tbody>";
//                    var end = "</table><div align='right'><button id='" + sno 
//                            + "' class='btn btn-xs btn-success' style=margin-top: 3px;position: relative;' onclick='openDetailWindow(this)'>详细信息</button></div></div>";
                    var end = "</table><div>"
                            + "<button id='a" + sno + extent + "' class='btn btn-xs btn-success' style=margin-left:1px;margin-top: 5px;position: relative;' onclick='addToUSersFile(this)'>收藏</button>"
                            + "<button id='r" + sno + "' class='btn btn-xs btn-success' style=margin-left:15px;margin-top: 5px;position: relative;' onclick='removeUSersFile(this)'>移除</button>"
                            + "<button id='" + sno + "' class='btn btn-xs btn-success'  style= margin-left:45px;margin-top:1px;position: relative;' onclick='openDetailWindow(this)'>详细</button>"
                            + " </div></div>";
                    var content = table + body + end;
                    var extentwkt = n.extent;
                    if (extentwkt) {
                        drawWkt(extentwkt, sno, content);
                    } else {
                        alert("获取影像范围出错！");
                    }
                });

                $('#infotable').datagrid({data: rows, checkOnSelect: false});
                $('#infotable').datagrid({
                    onDblClickRow: function (index, row) {
                        var sno = row.sno;
                        var height = 600, width = 800;
                        var centerH = (window.screen.height - height) / 2;
                        var centerW = (window.screen.width - width) / 2;
                        window.open("datainfo.jsp?sno=" + sno,
                                "影像详细信息", "height=" + height + ",width=" + width + ",top=" + centerH + ",left=" + centerW + " toolbar=no,menubar=no,status=no");
                    }
                });
                $('#infotable').datagrid({
                    onCheck: function (index, row) {
                        var sno = row.sno;
                        //查询缩略图
//                        tempArrayMoasic.push(sno);
//                        alert(tempArrayMoasic);


                        queryDetail(sno);
                        var indexextent = row.extent;
//                        hideFeature(sno);
                        var data = wktparser.read(indexextent).toJson();
                        var feature = L.geoJson(data, {
                        });
                        map.fitBounds(feature.getBounds());
                    },
                    onUncheck: function (index, row) {
                        var sno = row.sno;
                        hideStaticImgLayer(sno);
//                        for (var i = 0; i <= tempArrayMoasic.length; i++) {
//                            if (!tempArrayMoasic[i])
//                                continue;
//                            if (tempArrayMoasic[i] === sno) {
//                                delete(tempArrayMoasic[i]);
//                            }
//                        }
//                        alert(tempArrayMoasic);
                    }
                });
                ajaxLoadEnd();
                $('#mytab').tabs('select', 2);
            }
        });
    }

}
function imgFormatter(value, row, index) {
    if ('' != value && null != value)
        value = '<img style="width:32px; height:32px" src="' + value + '">';
    return  value;
}
function openDetailWindow(e) {
    var sno = e.id;
    var centerH = window.screen.height / 2;
    var centerW = window.screen.width / 2;
    window.open("datainfo.jsp?sno=" + sno,
            "影像详细信息", "height=650,width=1180,top=" + centerH + ",left=" + centerW + "toolbar=no,menubar=no,status=no");
}

/**
 *  获取影像缩略图
 *  @param {type} sno 
 * @returns {undefined}
 */
function queryDetail(sno) {
    if (!sno) {
        alert("影像id不能为空 ！");
        return;
    } else {
        var url = "../query/image/" + sno;
        addCustomImageLayer(sno, url);
    }
}

function ajaxLoading() {
//    layer.msg('加载中', {icon: 16});
    layer.load(2, {offset: ['90%', '150px']});
}
function ajaxLoadEnd() {
    layer.closeAll('loading');
}

function initMapQueryCity() {
    $.ajax({
        type: "GET",
        url: "../map/city",
        dataType: "json",
        error: function (req, status, error) {
            if (status === "timeout") {
                alert("请求超时，请稍后再试！");
            } else if (status === "error") {
                alert("数据请求失败，请稍后再试!如果还未解决，请联系管理员！");
                return;
            }
        },
        success: function (obj) {
            $.each(obj, function (i, n) {
                var name = n.name;
                var id = n.cityid;
                $('#mapcity').append("<option id='" + id + "'>" + name + "</option>");
            });
        }
    });
}

