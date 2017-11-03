<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>培训统计</title>
<script src="${pageContext.request.contextPath}/Js/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/Js/highcharts.js"></script>
</head>
<body>
<input type="month" id="selectDate" name="date" class="input" /><input type="button" value="提交" onclick="selectDate();"/>
<div id="container" style="width: 700px; height: 550px; margin: 0 auto">
</div>
<script language="JavaScript">
 $(document).ready(function() {  
	 selectDate();
 });
function  statistics(series)	{ 
	
	var total1 = 0;
	var total2= 0;
	var total3 = 0;
	
	for(i=0;i<series[0].data.length;i++){
		total1+=series[0].data[i];
	}
	
	for(i=0;i<series[1].data.length;i++){
		total2+=series[1].data[i];
	}
	
	for(i=0;i<series[2].data.length;i++){
		total3+=series[2].data[i];
	}
	
	var title = { 
	text: '培训统计一览'   
   };
   var xAxis = {
      categories: ['一月', '二月', '三月', '四月', '五月','六月','七月','八月','九月','十月','十一月','十二月']
   };
   var labels = {
      items: [{
         html: '培训效果',
            style: {
               left: '50px',
               top: '18px',
               color: (Highcharts.theme && Highcharts.theme.textColor) || 'black'
            }
      }]
   };
   var marker = {marker: {
       lineWidth: 2,
       lineColor: Highcharts.getOptions().colors[3],
       fillColor: 'white'
   }}
  series[3]= $.extend({}, series[3], marker);
   
  var pie = {
          type: 'pie',
          name: '总次数',
          data: [{
              name: '好',
              y: total1,
              color: Highcharts.getOptions().colors[0] // Jane 的颜色
          }, {
              name: '中',
              y: total2,
              color: Highcharts.getOptions().colors[1] // John 的颜色
          }, {
              name: '差',
              y: total3,
              color: Highcharts.getOptions().colors[2] // Joe 的颜色
          }],
          center: [500, 0],
          size: 50,
          showInLegend: false,
          dataLabels: {
              enabled: false
          }
    }
   series.push(pie); 

   var json = {};   
   json.title = title;   
   json.xAxis = xAxis;
   json.labels = labels;  
   json.series = series;
/*    console.info(JSON.stringify(json)) */
   $('#container').highcharts(json);  
};


function selectDate(){
	
	var t=$("#selectDate").val().split("-")[0]||"2018";
	/* console.info(t); */
	$.ajax({
		   type: "POST",
		   url: "getData",
		   data: "date="+t,
		   success: function(data){
		    var series =  data.data;
		    statistics(series);
		   }
		});
}
</script>
</body>
</html>
