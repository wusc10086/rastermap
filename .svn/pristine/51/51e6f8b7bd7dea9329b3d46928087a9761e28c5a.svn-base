/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * 左移动
 * @param {type} div
 * @returns {undefined}
 */
function hideLeftrtoolbar_left(div) {
    var offset = $(div).offset();
    if (offset.left > 10) {
        startMove($("#toolbar")[0], {
            // width: 0
            left: -273
        }, function () {
            startMove($(div)[0], {
                right: 16
                        //left: 120
            });
            $(div).css({
                "-moz-transform": "rotate(-180deg)",
                "-webkit-transform": "rotate(-180deg)",
                "-o-transform": "rotate(-180deg)",
                "-ms-transform": "rotate(-180deg)",
                "transform": "rotate(-180deg)"
            });
        });
    } else {
        startMove($("#toolbar")[0], {
            left: 0
        }, function () {
            startMove($(div)[0], {
                right: 0
            });
            $(div).css({
                "-moz-transform": "rotate(0deg)",
                "-webkit-transform": "rotate(0deg)",
                "-o-transform": "rotate(0deg)",
                "-ms-transform": "rotate(0deg)",
                "transform": "rotate(0deg)"
            });
        });
    }
}
/**
 * 右移动
 * @param {type} div
 * @returns {undefined}
 */
function hideRgihtrtoolbar(div) {
    var windowwidth = $(window).width();
    var offset = $(div).offset();
    if (windowwidth - offset.left >= $("#toolbar").width()-10) {
        startMove($("#toolbar")[0], {
            // width: 0
            left: windowwidth
        }, function () {
            startMove($(div)[0], {
                left: -16
                        //left: 120
            });
            $(div).css({
                "-moz-transform": "rotate(-180deg)",
                "-webkit-transform": "rotate(-180deg)",
                "-o-transform": "rotate(-180deg)",
                "-ms-transform": "rotate(-180deg)",
                "transform": "rotate(-180deg)"
            });
        });
    } else {
        startMove($("#toolbar")[0], {
            left: windowwidth -$("#toolbar").width()
        }, function () {
            startMove($(div)[0], {
                left: 0
            });
            $(div).css({
                "-moz-transform": "rotate(0deg)",
                "-webkit-transform": "rotate(0deg)",
                "-o-transform": "rotate(0deg)",
                "-ms-transform": "rotate(0deg)",
                "transform": "rotate(0deg)"
            });
        });
    }
}
/**
 * 隐藏下边滑动框
 * @param div
 */
function hideDownBar(div) {
    var offset = $(div).offset();
    var top = $(window).height() - $(div).offset().top;
    if (top <= 20) {
        startMove($("#footer")[0], {
            height:180
        }, function () {
//            startMove($(div)[0], {
//                top: 0
//            });
        });
    } else {
        startMove($("#footer")[0], {
            height: 0
        }, function () {
            startMove($(div)[0], {
                top: -20
            });
            
        });
    }
}

