var opts = {
    lines: 13, // 花瓣数目
    length: 20, // 花瓣长度
    width: 10, // 花瓣宽度
    radius: 10, // 花瓣距中心半径
    scale: 0.4, //缩放比例
    corners: 1, // 花瓣圆滑度 (0-1)
    rotate: 0, // 花瓣旋转角度
    direction: 1, // 花瓣旋转方向 1: 顺时针, -1: 逆时针
    color: '#000', // 花瓣颜色
    speed: 1, // 花瓣旋转速度
    trail: 60, // 花瓣旋转时的拖影(百分比)
    shadow: false, // 花瓣是否显示阴影
    hwaccel: false, //spinner 是否启用硬件加速及高速旋转            
    className: 'spinner', // spinner css 样式名称
    zIndex: 2e9, // spinner的z轴 (默认是2000000000)
    top: 'auto', // Top position relative to parent
    left: '80%' // Left position relative to parent
};

var target = document.getElementById('infolable2');
var spinner = new Spinner(opts);


/**
 *  创建数据库中的影像范围，利用postgis构建polygon，为以后的空间查询做准备
 * @returns {undefined}
 */
function createRasterExtent() {
    $.ajax({
        type: "POST",
        url: "extent",
        dataType: "json",
        processData: true,
        beforeSend: function () {
            //异步请求时spinner出现
            $("#extentsp").text("");
            var target = $("#extentsp").get(0);
            spinner.spin(target);
        },
        error: function (req, status, error) {
            spinner.stop();
            $("#createExtentBtn").removeAttr("disabled");
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
            if (obj) {
                $("#extentlabel").text("创建成功！");
                $("#extentlabel").addClass("label label-success");

            } else {
                $("#extentlabel").text("创建失败！");
                $("#extentlabel").addClass("label label-danger");
            }
            $("#createExtentBtn").removeAttr("disabled");
            spinner.stop();
        }
    });
}


/**
 * 创建缩略图
 * @returns {undefined}
 */
function createOverview() {
    $.ajax({
        type: "POST",
        url: "../dataprocess/createoverview",
        dataType: "json",
        processData: true,
        data: {basePath: $("#basePath").val(), quality: $("#quality").val()},
        beforeSend: function () {
            //异步请求时spinner出现
            $("#processsp").text("");
            var target = $("#processsp").get(0);
            spinner.spin(target);
        },
        error: function (req, status, error) {
            spinner.stop();
            $("#processbtn").removeAttr("disabled");
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

            if (obj) {
                $("#process").text("创建成功！");
                $("#process").addClass("label label-success");
            } else {
                $("#process").text("创建失败！");
                $("#process").addClass("label label-danger");
            }
            $("#processbtn").removeAttr("disabled");
            spinner.stop();

        }
    });
}

/**可用
 * glm
 *  矢量查询
 * @returns {undefined}
 */
//function vectorQuery() {
//    spinner.spin(target);
//    $.ajaxFileUpload({
//        url: '../upload/uploadfile',
//        fileElementId: 'shpfile',
//        dataType: 'json',
//        processData: false,
//        success: function (data, status) {
//            //upload success
//            var jsonres = data;
//
//            //填充内容
//            var rows = [];
//            //填充内容
//
//            $.each(jsonres, function (i, res) {
//                $.each(res, function (i, n) {
//                    var sno = n.sn;
//                    var date;
//                    var cgq;
//                    if (n.producedate) {
//                        date = n.producedate;
//                    } else {
//                        date = "无";
//                    }
//                    if (n.imgcolormodel) {
//                        cgq = n.imgcolormodel;
//                    } else {
//                        cgq = n.imgcolormodel;
//                    }
//                    rows.push({
//                        mzt: '../images/marker-gold.png',
//                        sno: sno,
//                        cgq: cgq,
//                        date: date,
//                        preview: "stst"
//                    });
//                    var extentwkt = n.extent;
//                    if (extentwkt) {
//                        drawWkt(extentwkt, sno, 10);
//                    } else {
//                        console.log("获取影像范围出错！");
//                    }
//                });
//            });
//            $('#infotable').datagrid({data: rows, checkOnSelect: false});
//            $('#infotable').datagrid({
//                onDblClickRow: function (index, row) {
//                    var sno = row.sno;
//                    var height = 650, width = 1180;
//                    var centerH = (window.screen.height - height) / 2;
//                    var centerW = (window.screen.width - width) / 2;
//                    window.open("datainfo.jsp?sno=" + sno,
//                            "影像详细信息", "height=650,width=1180,top=" + centerH + ",left=" + centerW + " toolbar=no,menubar=no,status=no");
//                }
//            });
//            $('#infotable').datagrid({
//                onCheck: function (index, row) {
//                    var sno = row.sno;
//                    queryDetail(sno);
//                },
//                onUncheck: function (index, row) {
//                    var sno = row.sno;
//                    hideStaticImgLayer(sno);
//                }
//            });
//
//            $('#mytab').tabs('select', 2);
//            spinner.stop();
//        },
//        error: function (data, status, e) {
//            spinner.stop();
//            //error
//            console.log(data);
//        }
//    });
//}
/**
 * yzz修改
 *  矢量查询
 * @returns {undefined}
 */
function vectorQuery() {
    spinner.spin(target);
    $.ajaxFileUpload({
        url: '../upload/uploadfile',
        fileElementId: 'shpfile',
        dataType: 'json',
        processData: false,
        success: function (data, status) {
            //upload success
            var QueryShp = data.splice(0, 1);
            $.each(QueryShp, function (i, res) {
                $.each(res, function (i, n) {
                    DrawQueryShp(n, i);
                });
            });

            var jsonres = data;

            //填充内容
            var rows = [];
            //填充内容
            $.each(jsonres, function (j, res) {
                $.each(res, function (i, n) {

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
                    var table = " <div><table id='devicetable' class='table-bordered table-hover'>"
                    var body = "<tbody><tr><td align='left'>ID:</td><td>" + sno + "</td></tr>" +
                            "<tr><td align='right'>数据类型:</td><td>" + date + "</td></tr>" +
                            "<tr><td align='right'>色彩模式:</td><td>" + cgq + "</td></tr></tbody>";
                    var end = "</table><div align='right'><button id='" + sno + "' class='btn btn-xs btn-success' style='margin-top: 3px;position: relative;' onclick='openDetailWindow(this)'>详细信息</button></div></div>"

                    var content = table + body + end;

                    var extentwkt = n.extent;
                    if (extentwkt) {
                        drawWkt(extentwkt, sno, content);
                    } else {
                        console.log("获取影像范围出错！");
                    }
                });
            });
            $('#infotable').datagrid({data: rows, checkOnSelect: false});
            $('#infotable').datagrid({
                onDblClickRow: function (index, row) {
                    var sno = row.sno;
                    var height = 650, width = 1180;
                    var centerH = (window.screen.height - height) / 2;
                    var centerW = (window.screen.width - width) / 2;
                    window.open("datainfo.jsp?sno=" + sno,
                            "影像详细信息", "height=650,width=1180,top=" + centerH + ",left=" + centerW + " toolbar=no,menubar=no,status=no");
                }
            });
            $('#infotable').datagrid({
                onCheck: function (index, row) {
                    var sno = row.sno;
                    queryDetail(sno);
                },
                onUncheck: function (index, row) {
                    var sno = row.sno;
                    hideStaticImgLayer(sno);
                }
            });
            $('#mytab').tabs('select', 2);
            spinner.stop();
        },
        error: function (data, status, e) {
            spinner.stop();
            //error
            console.log(data);
        }
    });
}

/**
 * 加载指定目录下的影像文件
 * @returns {undefined}
 */
function loadMosaicImages() {

    $.ajax({
        type: "GET",
        url: "../dataprocess/loadimages/" + JSON.stringify({basepath: $("#mosaicPath").val()}),
        dataType: "json",
        processData: true,
        beforeSend: function () {
            //异步请求时spinner出现
            $("#loadlabel").text("");
            var target = $("#loadsp").get(0);
            spinner.spin(target);
            $("#loadImagesBtn").attr("disabled", true);
        },
        error: function (req, status, error) {
            spinner.stop();
            $("#loadImagesBtn").removeAttr("disabled");
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
            spinner.stop();
            $("#loadImagesBtn").removeAttr("disabled");

            if (obj.code === 0) {
                $("#loadlabel").text("加载成功！");
                $("#loadlabel").removeClass("label label-danger");
                $("#loadlabel").addClass("label label-success");
                var data = obj.data;
                $("#selList").html("");
                $.each(data, function (i, res) {
                    $("#selList").append("<option id='" + i + "'>" + res + "</option>");
                });

            } else {
                $("#loadlabel").text(obj.message);
                $("#loadlabel").removeClass("label label-success");
                $("#loadlabel").addClass("label label-danger");
            }
        }
    });


}

/**
 * 请求创建影像拼接
 * @returns {undefined}
 */
function crateMosaicImages() {

    var list = [];
    $("#selList option").each(function () {
        list.push($(this).val());
    });
    $.ajax({
        type: "POST",
        url: "../dataprocess/createmosaic/" + JSON.stringify({basepath: $("#mosaicPath").val(), filelist: list, distname: $("#mosaicname").val()}),
        dataType: 'json',
        contentType: 'application/json',
        beforeSend: function () {
            //异步请求时spinner出现
            $("#mosaicsp").text("");
            var target = $("#mosaicsp").get(0);
            spinner.spin(target);
            $("#createMosaicBtn").attr("disabled", true);
        },
        error: function (req, status, error) {
            spinner.stop();
            $("#createMosaicBtn").removeAttr("disabled");
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
            $("#createMosaicBtn").removeAttr("disabled");
            spinner.stop();

            if (obj.code === 0) {
                $("#mosaiclabel").text("创建成功！");
                $("#mosaiclabel").addClass("label label-success");
            } else {
                $("#mosaiclabel").text("创建失败！");
                $("#mosaiclabel").addClass("label label-danger");
            }
        }
    });


}
/**
 * 请求融合影像
 * @returns {undefined}
 */
function crateFusionImages() {

    $.ajax({
        type: "POST",
        url: "../dataprocess/fusionimage/" + JSON.stringify({basepath: $("#fusionImagePath").val(), rp: $("#rpPath").val(), rm: $("#rmPath").val(), distname: $("#fusionname").val()}),
        dataType: 'json',
        processData: true,
        beforeSend: function () {
            //异步请求时spinner出现
            $("#fusionsp").text("");
            var target = $("#fusionsp").get(0);
            spinner.spin(target);
            $("#createFusionBtn").attr("disabled", true);
        },
        error: function (req, status, error) {
            spinner.stop();
            $("#createFusionBtn").removeAttr("disabled");
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
            $("#createFusionBtn").removeAttr("disabled");
            spinner.stop();

            if (obj.code === 0) {
                $("#fusionlabel").text("创建成功！");
                $("#fusionlabel").addClass("label label-success");
            } else {
                $("#fusionlabel").text("创建失败！");
                $("#fusionlabel").addClass("label label-danger");
            }
        }
    });
}
/**
 *  @returns {undefined}
 */
function downlaodmoudle(col) {   
      var url="../map/download/" + col;
  
   // 方式一可行
     var form =$("<form>");
      form.attr('style','display:none');
      form.attr('target','');
      form.attr('method','post');
      form.attr('action',url);
      
      var input1=$('<input>');
      input1.attr('type','hidden');
      input1.attr('name','url');
      input1.attr('value',url);
      $('body').append(form);
      form.append(input1);
      form.submit();
}
