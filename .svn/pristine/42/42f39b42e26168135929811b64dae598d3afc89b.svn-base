/* yang zz
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function initTabs(){
     $('#mytab').tabs({
                    plain: true,
                    narrow: false,
                    pill: true,
                    justified: true,
                    onSelect: function (title) {
                        if (title != "结果列表") {
                            //alert(title +'is sletcted');
                            cleanMarkers();
                            cleanImgLayers();
                            tempArrayMoasic=[];
                            vecLayers.clear()
                            $("#infolable2").html("<div id='infolable2'></div>");
                        }
                    }
                });
                $("#product_result").tabs({
                    plain: true,
                    narrow: true,
                    pill: true,
                    justified: false
                });
                  $("#product_result2").tabs({
                    plain: true,
                    narrow: true,
                    pill: true,
                    justified: false
                });
                
              
                $('#mytab2').tabs({
                    plain: true,
                    narrow: true,
                    pill: true,
                    justified: false
                });
                $('#imageTab').tabs({
                    plain: true,
                    narrow: true,
                    pill: true,
                    justified: false,
                    onSelect: function (title) {
                        //alert(title +'is sletcted');
                        if (title === "制图产品") {
                            cleanMarkers();
                            cleanImgLayers();
                            MapinitXZQuery();
                        }
                        if (title === "影响数据")
                        if(Layer){
                             map.removeLayer(Layer);
                        }
                    }
                });
}
