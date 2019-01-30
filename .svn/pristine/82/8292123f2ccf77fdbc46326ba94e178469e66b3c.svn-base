  /* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function (){
    var screenWidth =document.body.clientWidth;
    var screenHeight =document.body.clientHeight;
    var mapRightShown = true;
    
    var splitwidth= 0;
    var rightWidth = 0;
    
    var setHidenRight =function (){
        $("#Checklist").css({display: "none"});
        $("#admrightsplitbt").css({right:"0",width:splitwidth});
        $("#admrightsplitbt").css({"background-position":"-0px -0px"});
    };
    var fnHideRight = function() {
        setHidenRight();
        mapRightShown = false;
    };
     var setShownRight = function() {
        var divisionLeft = rightWidth;
        $("#Checklist").css({display: "block"});
        $("#admrightsplitbt").css({right:"0",width:splitwidth});
        $("#admrightsplitbt").css({"background-position":"-0px -0px"});
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
    
    $("#admrightsplitbt").click(function(){
        if (!mapRightShown) {   //显示左侧的时候
            fnShowRight();
        }
        else {              //隐藏左侧的时候 
            fnHideRight();
        }
    });
    
});
