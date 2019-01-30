/* yzz 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var wkt2gcj=new Wkt.Wkt();
/**
 * 此方法 有bug 暂时不启用
 * 
 * @param {type} 是一个wkt传转换的object
 * @returns {transform.resultgeojson}
 */
function transform(geojson){
   // console.log(geojson);
    var resultgeojson={coordinates:null,type:"MultiPolygon"};
    var result00=[];
    var result0=[];
    var resultf=[];
    
    var t=geojson.coordinates;
    var t0=t[0];
    var t00=t0[0];
   // alert(t00);
    for(var i=0;i<t00.length;i++) {
        var resultlatlng;
        var latlon=t00[i];
        //console.log(latlon);
           resultlatlng=new WGS84_to_GCJ02().transform(latlon[1],latlon[0]);
           var temptrans=resultlatlng[0];
           resultlatlng[0]=resultlatlng[1];
           resultlatlng[1]=temptrans;
           //console.log(resultlatlng);
            result00.push(resultlatlng);
           }
           result0.push(result00);
           resultf.push(result0);
          // alert(resultlatlng===latlon);
           resultgeojson.coordinates=resultf;
           //alert(resultgeojson===geojson);
    return resultgeojson;
}


