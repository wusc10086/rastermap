/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * 初始化订单
 * @returns {undefined}
 */
function initadminorder() {
    $.ajax({
        type: "GET",
        url: "../query/adminorder",
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
            var rows = [];
            $.each(obj, function (i, n) {
                var image = n.image;
                var status = n.status;
                var username = n.usernameString;
                var passin = "<button  title='通过' style='background:url(../images/ok.png);background-repeat:no-repeat;height:20px;width:20px'" + "></button>";
                var passout = "<button title='不通过' style='background:url(../images/no.png);background-repeat:no-repeat;height:20px;width:20px'></button>";
                if (status == 0) {
                    status="<font color='#0000ff'>未审核</font>";
                    rows.push({
                    adimage: image,
                    adstatus: status,
                    aduser: username,
                    adpsibtn: passin,
                    adpsobtn: passout
                });
                    return ;
                }

                if (status == 2) {
                    status="<font color='#ff0000'>未通过</font>";
                    rows.push({
                    adimage: image,
                    adstatus: status,
                    aduser: username,
                    adpsibtn: passin,
                    adpsobtn: passout
                });
                    return ;
                }
                if (status == 3) {
                    return ;
                }
            });
            $('#adminddatalist').datagrid({data: rows, checkOnSelect: false});
            $('#adminddatalist').datagrid({
                onClickCell: function (index, field, value) {
                    var getrow = $(this).datagrid("getData").rows[index];
                    var imageid = getrow.adimage;
                    var usernameup = getrow.aduser;
                    if (field === "adpsibtn") {
                        var sta = "1";
                        adUpdateStatus(usernameup, imageid, sta);
                        getrow.adstatus="<font color='#00ff00'>通过</font>";
                        $(this).datagrid("refreshRow",index);
                    }
                    if (field === "adpsobtn") {
                        var sta = "2";
                        adUpdateStatus(usernameup, imageid, sta);
                        getrow.adstatus="<font color='#ff0000'>不通过</font>";
                        $(this).datagrid("refreshRow",index);
                    }
                }
            });
        }
    });
}
function adUpdateStatus(username, image, status) {
    var parameters = username + "--" + image + "--" + status;
    $.ajax({
        type: "GET",
        url: "../query/adUpdateUIStatus/" + parameters,
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
/**
 * 数据审核面板
 * @returns {undefined}
 */
function ShowPanel() {
//    var height = 600, width = 800;
//    var centerH = (window.screen.height - height) / 2;
//    var centerW = (window.screen.width - width) / 2;
//    window.open("filesDownLoad.jsp", "数据申请", "height=" + height + ",width=" + width + ",top=" + centerH + ",left=" + centerW + " toolbar=no,menubar=no,status=no");
$("#Checklist").css("display","block");
initadminorder();
$("#rightsplitbt").css("display","none");
$("#admrightsplitbt").css("display","block");
$("#admrightsplitbt").css("right","0px");
}