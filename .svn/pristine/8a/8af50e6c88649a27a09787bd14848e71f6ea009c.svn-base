<%-- 
    Document   : login
    Created on : 2016-3-2, 21:27:39
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>  
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>大气电场仪业务处理系统</title>
        <link href="css/style.css" rel="stylesheet" type="text/css" />
        <script src="js/jquery-1.10.2.js" type="text/javascript"></script>
        <script src="js/cloud.js" type="text/javascript"></script>
        <script language="javascript">
            $(function () {
                $('.loginbox').css({'position': 'absolute', 'left': ($(window).width() - 692) / 2});
                $(window).resize(function () {
                    $('.loginbox').css({'position': 'absolute', 'left': ($(window).width() - 692) / 2});
                })

                $('.loginpwd').keydown(function (e) {
                    if (e.keyCode == 13) {
                        if(!$('.loginpwd').val()){
                              alert("密码不能为空！");
                              return;
                        }
                        login();
                    }
                });
            });

            function login() {
                var user = $(".loginuser").val();
                var pwd = $(".loginpwd").val();

                $.ajax({
                    type: "POST",
                    url: "user/login",
                    dataType: "json",
                    data: "name=" + user + "&passwd=" + pwd,
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
                        if (obj === true) {
                            window.top.location = 'system/index.jsp'
                        } else {
                            alert("登录失败,请检查用户名和密码是否正确！");
                        }

                    }
                });
            }
 
        </script> 

    </head>

    <body style=" background-image:url(images/1111.png); ">



        <div id="mainBody">
            <div id="cloud1" class="cloud"></div>
            <div id="cloud2" class="cloud"></div>
        </div>  
  

        <div class="logintop">    
            
            <center>
            <span></span>    
            </center>
        </div>

        <div class="loginbody">

            <span class="systemlogo"></span> 

            <div class="loginbox">

                <ul>
                    <li><input name="" type="text" class="loginuser" value="admin" onclick="JavaScript:this.value = ''"/></li>
                    <li><input name="" type="password" class="loginpwd" value="admin" onclick="JavaScript:this.value = ''"/></li>
                    <li><input name="" type="button" class="loginbtn" value="登录"  onclick="login()"  /><label><input name="" type="checkbox" value="" checked="checked" />记住密码</label></li>
                </ul>


            </div>

        </div>



        <div class="loginbm">版权所有@<a href="#"></a>北京华云东方探测技术有限公司</div>


    </body>

</html>
