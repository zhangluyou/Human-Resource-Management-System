<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html >
  <head>
    <%-- <base href="<%=basePath%>"> --%>
    <meta charset="utf-8">
    <!--支持 浏览器内核 webkit、ie-comp IE兼容、ie-stand IE标准 -->
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <!--开启IE兼容模式  -->
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
    <!--移动设备支持， width=device-width 自适应宽度,initial-scale=1.0 初始缩放比例为1:1,user-scalable 用户是否可以手动缩放-->
    <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-transform"/>
    <!-- <script type="text/javascript" src="${pageContext.request.contextPath}/bootstraptable/plus/import.inc.js"></script> -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bootstraptable/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bootstraptable/plus/table/bootstrap-table.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bootstraptable/plus/datatime/bootstrap-datetimepicker.min.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/bootstraptable/js/html5shiv.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/bootstraptable/js/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/bootstraptable/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/bootstraptable/plus/table/bootstrap-table.min.js">
	</script><script type="text/javascript" src="${pageContext.request.contextPath}/bootstraptable/plus/table/bootstrap-table-zh-CN.min.js">
	</script><script type="text/javascript" src="${pageContext.request.contextPath}/bootstraptable/plus/table/bootstrap-table-edit.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/bootstraptable/plus/datatime/bootstrap-datetimepicker.min.js">
	</script><script type="text/javascript" src="${pageContext.request.contextPath}/bootstraptable/plus/datatime/bootstrap-datetimepicker.zh-CN.js">
	</script><script type="text/javascript" src="${pageContext.request.contextPath}/bootstraptable/plus/bootstrap-select.js"></script>
    <script type="text/javascript">
    $(function() {  
    	querys();
    }) ;
    function querys() {  
			//编辑表格
			var table = {
	    			 /*  url:'/hrm/stipend/edittable', */  
					  method: 'get',
					editable:true,  //开启编辑模式
					striped : true, // 是否显示行间隔色  
					clickToSelect: true,
					 pagination : true, // 分页   
				    toolbar : "#toolbar",// 指定工具栏  
			        showRefresh : false, // 显示刷新按钮  
			        pageList : [5,10,20],  
			      /*   sidePagination : "server", // 服务端处理分页   */
					columns: [
	//////////////////////////////////////--------------------顶部第一行--------------------------/////////////////////
						[
						    
						   { rowspan:3,field:"department", edit:false, title:"部门名称",align:"center", valign:'middle'},
						   {rowspan:3,field:"position", edit:false,title:"职位名称",align:"center",valign:'middle'},
						   {rowspan:3,field:"name",edit:false, title:"职员姓名",align:"center",valign:'middle'},
	                       {rowspan:3,field:"emp_id",edit:false, title:"工号", align:"center",valign:'middle' },
						   {colspan:7,title:"固定部分(元)",align:"center"},
	                       {colspan:9,title:"浮动部分(元)", align:"center"},
						   {colspan:6,title:"出勤（天）",align:"center"},
	                       {colspan:18,title:"税前扣款(元)",align:"center"},
	                       {rowspan:3,field:"granttime", title:"发放月份", align:"center",valign:'middle',edit:false},
	                       {rowspan:3,field:"should_pay", title:"应付工资", align:"center",valign:'middle',edit:false},
	                       {rowspan:3,field:"personal_tax",title:"个税",align:"center",valign:'middle' ,edit:false},
	                       {rowspan:3,field:"real_stipend", title:"实发工资", align:"center",valign:'middle',edit:false},        	  
						],
	//////////////////////////////////////////////////------------顶部第二行-----------------////////////////////////////////////
						[
						{field:"basic",rowspan:2,title:"基本工资",align:"center",edit:false},
						{field:"level",rowspan:2, title:"职级工资",align:"center", width:"200px",edit:false},
						{field:"phone_call",rowspan:2,title:"通讯补贴",align:"center", width:"200px" ,edit:false},
						{field:"traffic",rowspan:2, title:"交通补贴", align:"center", width:"200px",edit:false},
						{field:"eat",rowspan:2,title:"餐费补贴", align:"center", width:"200px" ,edit:false},
						{field:"secret", rowspan:2,title:"保密费",align:"center",width:"200px",edit:false},
						{field:"fixed_sum",rowspan:2,edit:false,title:"固定部分合计",align:"center",width:"200px" },
						{field:"duty_everyDay", rowspan:2,title:"全勤奖",align:"center",width:"200px",edit:false},
						{field:"overtime_pay",rowspan:2,title:"加班费", align:"center", width:"200px",edit:false},
						{field:"ep_age", rowspan:2,title:"司龄补贴",align:"center",width:"200px",edit:false},
						{field:"performance",rowspan:2,title:"绩效工资",align:"center",width:"200px",edit:false},
						{field:"bd_benefits",rowspan:2, title:"生日福利",align:"center", width:"200px",edit:false},
						{field:"commission",rowspan:2,title:"提成",align:"center",width:"200px",edit:false},
						{field:"award",rowspan:2,title:"奖金",align:"center", width:"200px",edit:false},
						{field:"other",rowspan:2, title:"其他补贴",align:"center",width:"200px",edit:false },
						{field:"float_sum",rowspan:2,edit:false, title:"浮动部分合计",align:"center",width:"200px"},
						{field:"should_attendance",rowspan:2, title:"应出勤天数", align:"center",edit:false},
						{colspan:4,title:"缺勤天数", align:"center" ,edit:false}, 
						{field:"real_attendance", rowspan:2, title:"实出勤天数",align:"center",edit:false },
						{field:"private_affairs", rowspan:2,title:"事假扣款",align:"center",width:"200px",edit:false},
						{field:"sick_leave",rowspan:2, title:"病假扣款",align:"center",width:"200px" ,edit:false},
						{field:"entry_leave_cut", rowspan:2, title:"入/离职扣款",align:"center", width:"200px",edit:false },
						{field:"arrive_late",rowspan:2,title:"迟到扣款", align:"center", width:"200px",edit:false},
						{field:"neglect_work",rowspan:2,title:"旷工扣款", align:"center", width:"200px",edit:false},
						{colspan:4,title:"本月社会保险",align:"center" }, 
	                    {colspan:6,title:"补扣保险", align:"center" }, 
	                    {field:"house_pay", rowspan:2, title:"住房公积金个人费用", align:"center", width:"200px" ,edit:false},
	  					{field:"other_cut",rowspan:2, title:"其他扣款", align:"center", width:"200px" ,edit:false},
	 				    {field:"cut_sum",  rowspan:2,edit:false, title:"税前扣款合计", align:"center", width:"200px" },  	   
						],
	/////////////////////////////////////////////////////////////////////-----------顶部第三行--------------------//////////////////////////////////////////
						[
							{field:"private_leaveDay",title:"事假",align:"center",edit:false},
							{field:"sick_leaveDay",title:"病假",align:"center",edit:false },
							{field:"neglect_workDay", title:"旷工", align:"center",edit:false },
							{field:"entry_leaveDay",title:"入/离职当月未出勤",align:"center",edit:false},
							{field:"social_pension",title:"养老",align:"center",edit:false},
							{field:"social_medical",title:"医疗",align:"center" ,edit:false},
							{field:"social_unemployment",title:"失业",align:"center",edit:false},
							{field:"social_sum",title:"小计",edit:false,align:"center"},
							{field:"take_pension",title:"养老",align:"center",edit:false},
							{field:"take_medical",title:"医疗",align:"center",edit:false},
							{field:"take_unemployment",title:"失业",align:"center",edit:false},
							{field:"take_injury",title:"工伤",align:"center",edit:false},
							{field:"take_birth",title:"生育",align:"center",edit:false},
							{field:"take_sum",title:"小计", edit:false, align:"center"},
						]
	/////////////////////////////////////////////////////////----------------顶部标题完--------------------///////////////////////////////////////
					],

	    		 responseHandler : function(res) {  
	                 return {  
	                     total : res.total,  
	                     rows : res.records  
	                 };  
	             }
				}
			    $('#selectDateBtn').click(function(){
				   var t=$("#selectDate").val();
				   
				   $.get("/hrm/stipend/getSalaryRecord",{selectmonth:t},
							function(data){
						table.data = data.data;
						$('#reportTable').bootstrapTable('destroy');
						 $('#reportTable').bootstrapTable(table);});
				   
			   }); 
    }   
function deleteHr(value,row,rowIndex){
        var strHtml = '<a href="javascript:void(0);" onclick="removeRow('+$(reportTable).bootstrapTable('getSelections')+')"><i class="glyphicon glyphicon-trash"></i>  </a>';
         return strHtml;
    }
function removeRow(rowIndex){
			$('#reportTable').bootstrapTable('removeRow',rowIndex);
		}
		
function submitRow(rowIndex){
			$('#reportTable').bootstrapTable('submitRow',rowIndex);
	}	
    </script>
  </head>
  <body>
	   <!-- 列表 -->
	   <div class="tab-pane fade in active" id="tab2">	 
	    <div id="toolbar" class="btn-group"> 
	  选择月份： <input type="month" id="selectDate" name="date" class="input" />
	   <input type="button"  id="selectDateBtn" value="查看"/>
			</div>
	      <table class="table table-striped table-hover" id="reportTable"></table>
	   </div>
  </body>
</html>
