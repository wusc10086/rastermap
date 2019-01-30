/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function (){
    var screenWidth =document.body.clientWidth;
    var screenHeight =document.body.clientHeight;
    var mapRightShown = true;
    
    var splitwidth= 20;
    var rightWidth = 300;
    
    var setHidenRight =function (){
        $("#downLoadlist").css({display: "none"});
        $("#rightsplitbt").css({right:"0",width:splitwidth});
        $("#rightsplitbt").css({"background-position":"-90px -50px"});
    };
    var fnHideRight = function() {
        setHidenRight();
        mapRightShown = false;
    };
     var setShownRight = function() {
        var divisionLeft = rightWidth;
        $("#downLoadlist").css({display: "block"});
        $("#rightsplitbt").css({right:divisionLeft+"px",width:splitwidth});
        $("#rightsplitbt").css({"background-position":"-110px -50px"});
    };
     var fnShowRight = function() {
        setShownRight();
        mapRightShown = true;
    };
    
    var _resetFrame = function() {
        screenWidth = document.body.clientWidth;
        screenHeight = document.body.clientHeight;

        if (mapRightShown) {   //显示左侧的时候
            fnShowRight();
        }
        else {              //隐藏左侧的时候 
            fnHideRight();
        }
    };
    _resetFrame();
    $(window).resize(function() {
        _resetFrame();
    });
    
    $("#rightsplitbt").click(function(){
        if (!mapRightShown) {   //显示左侧的时候
            fnShowRight();
        }
        else {              //隐藏左侧的时候 
            fnHideRight();
        }
    });
    
});
