$(function() {
    var screenWidth = document.body.clientWidth;
    var screenHeight = document.body.clientHeight;

    var headHeight = 0;
    var splitwidth= 0;
    var leftWidth = 0;
    var mapLeftShown = true;

    var setHidenLeft = function() {
        $("#center").height(screenHeight - headHeight);
        $("#left").css({display: "none"});
        $("#map").css({left: "0px", width: screenWidth});
        $("#splitbt").css({left:"0",width:splitwidth});
        $("#splitbt").css({"background-position":"-110px -50px"});

    };
    var fnHideLeft = function() {
        setHidenLeft();
        if(map){
//              map.updateSize();
            map._onResize();
        }
      
        mapLeftShown = false;
    };
    var setShownLeft = function() {
        var divisionLeft = leftWidth;
        $("#center").height(screenHeight - headHeight);
        $("#left").css({display: "block"});
        $("#map").css({float: "right"}).width(screenWidth - divisionLeft);
        $("#map").css({left: divisionLeft + "px"});
        $("#splitbt").css({left:divisionLeft+"px",width:splitwidth});
        $("#splitbt").css({"background-position":"0px -50px"});
    };


    var fnShowLeft = function() {
        setShownLeft();
        if(map)
        {
//            map.updateSize();
            map._onResize();
        }
        mapLeftShown = true;
    };

    var _resetFrame = function() {
        screenWidth = document.body.clientWidth;
        screenHeight = document.body.clientHeight;

        if (mapLeftShown) {   //显示左侧的时候
            fnShowLeft();
        }
        else {              //隐藏左侧的时候 
            fnHideLeft();
        }
        
        $("#infotable").height(screenHeight - headHeight);
        
    };

    _resetFrame();
    $(window).resize(function() {
        _resetFrame();
    });

//    $("#splitbt").toggle(fnHideLeft, fnShowLeft);
    $("#splitbt").click(function(){
        if (!mapLeftShown) {   //显示左侧的时候
            fnShowLeft();
        }
        else {              //隐藏左侧的时候 
            fnHideLeft();
        }
    });
});
