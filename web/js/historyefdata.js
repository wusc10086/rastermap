/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function(){
var xA = new Array();
var yA = new Array();
var markData = new Array();
var marker = new Array();
    $(".submitSelectForm").click(function(){
          var checkbox = "10002,10003";
            $.ajax({      
             async: false,
             type: "POST",
             data:{startTime:$("#startTime").val(),endTime:$("#endTime").val(),
                       option:$("#option").val(),stations:checkbox},
             url: "../efdata/getEFDatas",
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
                 sub=eval("("+ obj.subData+")");
                 var datas=obj.efdatas;
                 alert(datas.deviceid);
                 hight(sub);
             }
         });
       });
         function hight(sub){
                    var seriesData = [];
        	    $.each(sub,function(i,item){
        	    var serieData={ name: '',data:[]};
        	$.each(item.data,function(j,dataitem){
        	var dataTemp=[];
        	var time=dataitem[0];
        	time = time.replace(/-/g, '/');
        	time =time + ' UTC';
        	time = Date.parse(time);
        	dataTemp.push(time);
        	dataTemp.push(dataitem[1]);
        	serieData.data.push(dataTemp);
        	});
        	serieData.name = item.name;
        	seriesData.push(serieData);
            });
            $('#container').highcharts({
            chart: {
                plotBorderColor: "black",     //图表的边框颜色
        	plotBorderWidth: 1,
                type: 'line'
            },
            credits :
             {
              enabled:false//不显示highCharts版权信息
              },
            title: {
                text: '大气场仪数据图'
            },
            xAxis : [{
                    	//gridLineColor: 'black',//纵向网格线颜色
                    	gridLineWidth: 1, //纵向网格线宽度
                    	LineWidth: 1,
                    	lineColor:'black',
                    	type: 'datetime',
                    	tickmarkPlacement: 'on',
                        labels: {
                            format: '{value:%H:%M}'
                        }
                    
                       
                    },{ // Top X axis
                        linkedTo: 0,
                        type: 'datetime',
                        tickInterval: 24 * 3600 * 1000,
                        labels: {
                            format: '{value:<span style="font-size: 12px; font-weight: bold">%Y/%m/%e</span> }',
                            align: 'center',
                            x: 12,
                            y: 5
                        }, 
                        lineWidth: 0,
                        opposite: false,
                        tickLength: 0,
                        gridLineWidth: 1
                        
                    }],
                    	
            yAxis: {
                gridLineWidth: 1, //纵向网格线宽度
                title: {
                    text: 'KV/m'
                }
            },
            plotOptions: {
                line: {
                    dataLabels: {
                        enabled: true          // 开启数据标签
                    }
                }
            },
            series:seriesData
          });
       };
});
