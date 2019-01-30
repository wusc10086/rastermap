
var tempArrayMoasic = [];
var rows = [];
var orderrows=[];
var ussname;
/**
 * 数组是否包含某元素
 * @param {type} fileid
 * @returns {Boolean}
 */

function in_array(fileid) {
    for (var i = 0; i < tempArrayMoasic.length; i++) {
        if (tempArrayMoasic[i] == fileid) {
            return true;
        }
    }
    return false;
}
/**
 * 检查用户权限 
 * @returns {undefined}
 */
function checkPrivalte(pri, usename) {
    ussname = usename;
     $("#persion").text(ussname);
    if (pri === "admin") {
        $("#Checklist").css("display", "none");
        $("#rightsplitbt").css("display","none");
        $("#downLoadlist").css("display", "none");       
    }
    if (pri === "user") {
        $("#Checklist").css("display", "none");
        $("#downLoadlist").css("display", "block");
        $("#admindcbtn").css("display","none");
        queryOrder(ussname);
    }
}
/**
 *  收藏文件ID 且红色高亮显示
 * @param {type} e
 * @returns {undefined}
 */
function addToUSersFile(e) {
    $("#MoasicDivbtn").show();
    var str = e.id;
    var fileid = str.substring(1, 11);
    for (var i = 0; i <= tempArrayMoasic.length; i++) {
        if (!in_array(fileid)) {
            tempArrayMoasic.push(fileid);
            var layer = vecLayers.get(fileid);
            putinlist(fileid);
            changeStyle(layer);
        }
    }

    /**
     * 更改收藏图层的样式
     * @param {type} layer
     * @returns {undefined}
     */
    function changeStyle(layer) {
        if (layer) {
            layer.setStyle({
                color: "red", fill: true, fillOpacity: 0.5
            });
            layer.off({
                mouseover: highlightFeature,
                mouseout: resetHighlight
            });
        }
    }
}
/**
 * 申请数据
 * @param {type} fid
 * @returns {undefined}
 */
function putinlist(fid) {
    var strd, strrmv;
    strd = "<button title='选购' style='background:url(../images/ok.png);background-repeat:no-repeat;height:20px;width:20px'></button>";
    strrmv = "<button title='删除' style='background:url(../images/cancel.bmp);background-repeat:no-repeat;height:20px;width:20px'></button>";
    var now = new Date();
    var nowtime = now.getFullYear().toString() + "-" + (now.getMonth() + 1).toString() + "-" + now.getDate().toString();
    rows.push({
        dldfid: fid,
        intime: nowtime,
        dld: strd,
        remove: strrmv
    });
    $("#ddatalist").datagrid({data: rows, checkOnSelect: false});
    $("#ddatalist").datagrid({
        onClickCell: function (index, field, value) {
            if (field === "dld") {
                var sltdrow = $(this).datagrid("getData").rows[index];
                var col = sltdrow.dldfid;
                buyimage(ussname, col);
                orderrows.push({
                    orderiamge: col,
                    orderstatus: "<font color='#0000ff'>未审核</font>",
                    orderoperate: operation = "<button title='申请' style='background:url(../images/reload.png);background-repeat:no-repeat;height:20px;width:20px'></button>"
                });
                $("#orderstatis").datagrid({data:orderrows, checkOnSelect: false});
                $(this).datagrid("deleteRow", index);
                return;
            }
            if (field === "remove") {
                var sltdrow = $(this).datagrid("getData").rows[index];
                var col = sltdrow.dldfid;
                $(this).datagrid("deleteRow", index);
                return;
            }
        }
    });
}

/**
 * 删除收藏影像
 * @param {type} e
 * @returns {undefined}
 */
function removeUSersFile(e) {

    var str = e.id;
    var fileid = str.substring(1, 11);
    for (var i = 0; i <= tempArrayMoasic.length; i++) {
        if (!tempArrayMoasic[i])
            continue;
        if (tempArrayMoasic[i] === fileid) {

            delete(tempArrayMoasic[i]);
            var layer = vecLayers.get(fileid);
            if (layer) {
                layer.setStyle({
                    color: "blue", fill: true, fillOpacity: 0.1
                });
                layer.on({
                    mouseover: highlightFeature,
                    mouseout: resetHighlight
                });
                layer.bringToFront();
            }
        }
    }
}
/**
 * 镶嵌
 * @returns {undefined}
 */
function  Moasic() {
    var finalArrayMoasic = new Array();
    for (var i = 0; i < tempArrayMoasic.length; i++) {
        if (!tempArrayMoasic[i])
            continue;
        finalArrayMoasic.push(tempArrayMoasic[i]);
    }
    if (finalArrayMoasic.length <= 1) {
        alert("请选择至少两景影像,请重新选择！");
        return;
    }
    for (var i = 0; i < finalArrayMoasic.length; i++) {
        var layer = vecLayers.get(finalArrayMoasic[i]);
        if (layer) {
            layer.setStyle({
                color: "blue", fill: true, fillOpacity: 0.1
            });
        }
    }
    $.ajax({
        type: "POST",
        url: "../yzzprocess/createmosaic/" + JSON.stringify({filelist: finalArrayMoasic}),
        dataType: 'json',
        contentType: 'application/json',
        beforeSend: function () {
            // alert("数据镶嵌需要一定时间，请稍后登陆在个人中心获取拼接结果");
            tempArrayMoasic = [];
        },
        error: function (req, status, error) {
            //spinner.stop();
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
            if (obj.message === "创建拼接影像失败！" || obj.message === "传入的影像拼接参数初始化出错") {
                alert("创建拼接影像失败");
                return;
            }
            if (obj.message === "faslestring") {
                alert("创建拼接影像失败");
                return;
            }
            var name = obj.message;
            var name1 = name.substring(0, 10);
            var name2 = name.substring(10, 20);
            //横向div
            if (name1.substring(4, 7) === name2.substring(4, 7)) {
                $("#pop").css("width", "500px");
                $("#pop").css("height", "400px");
                $("#moaimg").css("width", "500px");
                $("#moaimg").css("height", "300px");
                $("#moaimg").css("max-hight", "300px");
                $("#moaimg").css("max-width", "500px");
                $("#moaimg").css("margin", "0 auto");
            }
            //纵向div
            if (name1.substring(7) === name2.substring(7)) {
                $("#pop").css("width", "300px");
                $("#pop").css("height", "500px");
                $("#moaimg").css("width", "300px");
                $("#moaimg").css("height", "400px");
                $("#moaimg").css("max-hight", "500px");
                $("#moaimg").css("max-width", "300px");
                $("#moaimg").css("margin", "0 auto");
            }
            $("#poptext").text("镶嵌成功");
            var url = "../query/mosaicimage/" + name;
            $('#moaimg').attr("src", url);
            var pop = new Pop();
        }
    });
}
/**
 * 下载影像
 * @param {type} fid
 * @returns {undefined}
 */
function downlaodImage(fid) {
    var url = "../map/imagedownload/" + fid;
    // 方式一可行
    var form = $("<form>");
    form.attr('style', 'display:none');
    form.attr('target', '');
    form.attr('method', 'post');
    form.attr('action', url);

    var input1 = $('<input>');
    input1.attr('type', 'hidden');
    input1.attr('name', 'url');
    input1.attr('value', url);
    $('body').append(form);
    form.append(input1);
    form.submit();
}

/**
 * 申请图像
 * @param {type} uid
 * @param {type} imageid
 * @returns {undefined}
 */
function buyimage(uid, imageid) {
    var data = uid + "--" + imageid;
    $.ajax({
        type: "GET",
        url: "../query/cellecttion/" + data,
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
            alert(obj);
        }
    });
}
/**
 * 删除申请
 * @param {type} imageid
 * @returns {undefined}
 */
function removeShenQing(uid,imageid){
    var data = uid + "--" + imageid;
    $.ajax({
        type: "GET",
        url: "../query/removeOrder/" + data,
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
            alert(obj);
        }
    });
}
/**
 * 初始化订单状态
 * @param {type} uerna
 * @returns {undefined}
 */
function queryOrder(uerna) {
    $.ajax({
        type: "GET",
        url: "../query/order/" + uerna,
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
            
            $.each(obj, function (i, n) {
                var image = n.image;
                var status = n.status;
                var operation = "";
                if (status == 0) {
                    status = "<font color='#0000ff'>未审核</font>";
                    operation = "<button title='申请' style='background:url(../images/reload.png);background-repeat:no-repeat;height:20px;width:20px'></button>";
                    
                }
                if (status == 1) {
                    status = "<font color='#00ff00'>通过</font>";
                    operation = "<button title='下载' style='background:url(../images/download.png);background-repeat:no-repeat;height:20px;width:20px'></button>";
                    ;
                }
                if (status == 2) {                    
                    status = "<font color='#ff0000'>未通过</font>";
                    operation = "<button title='删除' style='background:url(../images/clear.png);background-repeat:no-repeat;height:20px;width:20px'></button>";
                    
                }
                if (status == 3) {                    
                    status = "<font color='#1B8378'>已下载</font>";
                    operation = "<button title='删除' style='background:url(../images/clear.png);background-repeat:no-repeat;height:20px;width:20px'></button>";
                 
                }
                orderrows.push({
                    orderiamge: image,
                    orderstatus: status,
                    orderoperate: operation
                });
            });
            $('#orderstatis').datagrid({data: orderrows, checkOnSelect: false});
            $('#orderstatis').datagrid({
                onClickCell: function (index, field, value) {
                    var getrow=$(this).datagrid("getData").rows[index];
                    var imageid=getrow.orderiamge;
                    if (field !== 'orderoperate') {
                        return;
                    }
                    if (value.indexOf("申请")!==-1) {
                        alert("已为您提交了下载申请，若还不能下载数据请联系管理员！");
                        return;
                    }
                    if (value.indexOf("下载")!==-1) {
                        alert("开始下载数据" +imageid);
                        downlaodImage(imageid);
                        getrow.orderstatus="<font color='#654580'>下载中</font>";
                        $(this).datagrid("refreshRow",index);
                        var stsss="3";
                        orderDldStatus(ussname,imageid,stsss); 
                        return;
                    }
                    if (value.indexOf("删除")) {
                        alert("删除申请" + imageid);
                        removeShenQing(ussname,imageid);
                       $(this).datagrid("deleteRow", index);
                        return;
                    }
            }
            });
        }
    });
}
/**
 * 用户下载数据
 * @param {type} un
 * @param {type} imgeid
 * @param {type} sta
 * @returns {undefined}
 */
function orderDldStatus(un,imgeid,sta){
    var data=un+"--"+imgeid+"--"+sta;
    $.ajax({
        type: "GET",
        url: "../query/dldupdatesta/" + data,
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
           
        }
    });
}
