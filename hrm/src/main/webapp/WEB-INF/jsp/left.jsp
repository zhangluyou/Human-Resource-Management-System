<%@page import="com.jointem.hrm.entity.Educate"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>人力资源管理系统</title>
	
	<style type="text/css">
		body {
			margin-left: 0px;
			margin-top: 0px;
			margin-right: 0px;
			margin-bottom: 0px;
			background-image: url(${pageContext.request.contextPath}/images/left.gif);
		}
		.showClass{
		  position: absolute;
		  bottom:-210px;
		  left:17px;
		  width:90%;
		  transition:bottom 2s;
          -moz-transition:bottom 2s; /* Firefox 4 */
		  -webkit-transition:bottom 2s; /* Safari and Chrome */
          -o-transition:bottom 2s; /* Opera */
		}
		.showClassActivit{
		   bottom:5px;
		}
	</style>
	<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<script src="${pageContext.request.contextPath}/Js/jquery-3.2.1.min.js"></script>
<script
	src="${pageContext.request.contextPath}/Js/bootstrap.min.js"></script>
	<link href="${pageContext.request.contextPath}/css/css.css" rel="stylesheet" type="text/css" />
</head>
<SCRIPT language=JavaScript>
    function tupian(idt){
        var nametu="xiaotu"+idt;
        var tp = document.getElementById(nametu);
        tp.src="${pageContext.request.contextPath}/images/ico05.gif";//图片ico04为白色的正方形
        for(var i=1;i<30;i++)
        {

            var nametu2="xiaotu"+i;
            if(i!=idt*1)
            {
                var tp2=document.getElementById('xiaotu'+i);
                if(tp2!=undefined)
                {tp2.src="${pageContext.request.contextPath}/images/ico06.gif";}//图片ico06为蓝色的正方形
            }
        }
    }

    function list(idstr){
        var name1="subtree"+idstr;
        var name2="img"+idstr;
        var objectobj=document.all(name1);
        var imgobj=document.all(name2);


        //alert(imgobj);

        if(objectobj.style.display=="none"){
            for(i=1;i<10;i++){
                var name3="img"+i;
                var name="subtree"+i;
                var o=document.all(name);
                if(o!=undefined){
                    o.style.display="none";
                    var image=document.all(name3);
                    //alert(image);
                    image.src="${pageContext.request.contextPath}/images/ico04.gif";
                }
            }
            objectobj.style.display="";
            imgobj.src="${pageContext.request.contextPath}/images/ico03.gif";
        }
        else{
            objectobj.style.display="none";
            imgobj.src="${pageContext.request.contextPath}/images/ico04.gif";
        }
    }

</SCRIPT>

<body>
<table width="198" border="0" cellpadding="0" cellspacing="0" class="left-table01">
	<tr>
		<TD>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="207" height="55" background="${pageContext.request.contextPath}/images/nav01.gif">
						<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
							<tr>
								<td width="25%" rowspan="2"><img src="${pageContext.request.contextPath}/images/ico02.gif" width="35" height="35" /></td>
								<td width="75%" height="22" class="left-font01">您好，${user.username }<span class="left-font02">${sessionScope.users.username}</span></td>
							</tr>
							<tr>
								<td height="22" class="left-font01">
									[&nbsp;<a href="/hrm/logout" target="_top" class="left-font01">退出</a>&nbsp;]</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>



			<!--  任务系统开始    -->
			<TABLE width="100%" border="0" cellpadding="0" cellspacing="0" class="left-table03">
				<tr>
					<td height="29">
						<table width="85%" border="0" align="center" cellpadding="0" cellspacing="0">
							<tr>
								<td width="8%"><img name="img8" id="img8" src="../images/ico04.gif" width="8" height="11" /></td>
								<td width="92%">
									<a href="javascript:" target="mainFrame" class="left-font03" onClick="list('8');" >人员管理</a></td>
							</tr>
						</table>
					</td>
				</tr>
			</TABLE>
			<table id="subtree8" style="DISPLAY: none" width="80%" border="0" align="center" cellpadding="0"
				   cellspacing="0" class="left-table02">
				<tr>
					<td width="9%" height="20" ><img id="xiaotu1" src="${pageContext.request.contextPath}/images/ico06.gif" width="8" height="12" /></td>
					<td width="91%"><a href="/hrm/users/toAddUser" target="mainFrame" class="left-font03" onClick="tupian('1');">人员信息录入</a></td>
				</tr>
				<tr>
					<td width="9%" height="21" ><img id="xiaotu2" src="${pageContext.request.contextPath}/images/ico06.gif" width="8" height="12" /></td>
					<td width="91%"><a href="/hrm/users/toListUser" target="mainFrame" class="left-font03" onClick="tupian('2');">人员信息查看</a></td>
				</tr>
				<tr>
					<td width="9%" height="21" ><img id="xiaotu3" src="${pageContext.request.contextPath}/images/ico06.gif" width="8" height="12" /></td>
					<td width="91%"><a href="${pageContext.request.contextPath }/roles/findRoleByPage?currPage=1" target="mainFrame" class="left-font03" onClick="tupian('3');">角色列表</a></td>
				</tr>
				<tr>
					<td width="9%" height="21" ><img id="xiaotu4" src="${pageContext.request.contextPath}/images/ico06.gif" width="8" height="12" /></td>
					<td width="91%"><a href="${pageContext.request.contextPath }/roles/roleAssign?currPage=1" target="mainFrame" class="left-font03" onClick="tupian('4');">角色分配</a></td>
				</tr>
				<tr>
					<td width="9%" height="21" ><img id="xiaotu5" src="${pageContext.request.contextPath}/images/ico06.gif" width="8" height="12" /></td>
					<td width="91%"><a href="${pageContext.request.contextPath }/permission/permissionList?currPage=1" target="mainFrame" class="left-font03" onClick="tupian('5');">权限列表</a></td>
				</tr>
				<tr>
					<td width="9%" height="21" ><img id="xiaotu6" src="${pageContext.request.contextPath}/images/ico06.gif" width="8" height="12" /></td>
					<td width="91%"><a href="${pageContext.request.contextPath }/permission/roleAndPermissionList?currPage=1" target="mainFrame" class="left-font03" onClick="tupian('6');">权限分配</a></td>
				</tr>
			</table>
			<!--  任务系统结束    -->



			<!--  消息系统开始    -->
			<TABLE width="100%" border="0" cellpadding="0" cellspacing="0" class="left-table03">
				<tr>
					<td height="29">
						<table width="85%" border="0" align="center" cellpadding="0" cellspacing="0">
							<tr>
								<td width="8%"><img name="img7" id="img7" src="${pageContext.request.contextPath}/images/ico04.gif" width="8" height="11" /></td>
								<td width="92%">
									<a href="javascript:" target="mainFrame" class="left-font03" onClick="list('7');" >招聘管理</a></td>
							</tr>
							
						</table>
					</td>
				</tr>
			</TABLE>
			<table id="subtree7" style="DISPLAY: none" width="80%" border="0" align="center" cellpadding="0"
				   cellspacing="0" class="left-table02">
				<tr>
					<td width="9%" height="20" ><img id="xiaotu3" src="${pageContext.request.contextPath}/images/ico06.gif" width="8" height="12" /></td>
					<td width="91%">
						<a href="/hrm/candidate/toSave" target="mainFrame" class="left-font03" onClick="tupian('3');">应聘信息录入</a></td>
				</tr>
				<tr>
					<td width="9%" height="20" ><img id="xiaotu4" src="${pageContext.request.contextPath}/images/ico06.gif" width="8" height="12" /></td>
					<td width="91%">
						<a href="/hrm/candidate/getlist" target="mainFrame" class="left-font03" onClick="tupian('4');">应聘信息查看</a></td>
				</tr>
				<tr>
					<td width="9%" height="20" ><img id="xiaotu5" src="${pageContext.request.contextPath}/images/ico06.gif" width="8" height="12" /></td>
					<td width="91%">
						<a href="/hrm/talent/getlist" target="mainFrame" class="left-font03" onClick="tupian('5');">人才库浏览
						</a></td>
				</tr>
			</table>
			<!--  消息系统结束    -->



			<TABLE width="100%" border="0" cellpadding="0" cellspacing="0" class="left-table03">
				<tr>
					<td height="29">
						<table width="85%" border="0" align="center" cellpadding="0" cellspacing="0">
							<tr>
								<td width="8%"><img name="img1" id="img1" src="${pageContext.request.contextPath}/images/ico04.gif" width="8" height="11" /></td>
								<td width="92%">
									<a href="javascript:" target="mainFrame" class="left-font03" onClick="list('1');" >培训管理</a></td>
							</tr>
						</table>
					</td>
				</tr>
			</TABLE>
			<%
			Educate educate=new Educate();
			%>
			<table id="subtree1" style="DISPLAY: none" width="80%" border="0" align="center" cellpadding="0"
				   cellspacing="0" class="left-table02">
				<tr>
					<td width="9%" height="20" ><img id="xiaotu15" src="${pageContext.request.contextPath}/images/ico06.gif" width="8" height="12" /></td>
					<td width="91%"><a href="/hrm/educate/educateView" target="mainFrame" class="left-font03" onClick="tupian('15');">培训计划录入</a></td>
				</tr>
				<tr>
					<td width="9%" height="20" ><img id="xiaotu7" src="${pageContext.request.contextPath}/images/ico06.gif" width="8" height="12" /></td>
					<td width="91%"><a href="/hrm/educate/listEducateView" target="mainFrame" class="left-font03" onClick="tupian('7');">培训计划查看</a></td>
				</tr>
				<tr>
					<td width="9%" height="20" ><img id="xiaotu8" src="${pageContext.request.contextPath}/images/ico06.gif" width="8" height="12" /></td>
					<td width="91%"><a href="/hrm/educate/skimSummarizeView" target="mainFrame" class="left-font03" onClick="tupian('8');">培训总结查看</a></td>
				</tr>
				<tr>
					<td width="9%" height="20" ><img id="xiaotu9" src="${pageContext.request.contextPath}/images/ico06.gif" width="8" height="12" /></td>
					<td width="91%"><a href="/hrm/educate/studentEducateRecordView" target="mainFrame" class="left-font03" onClick="tupian('9');">培训记录查看</a></td>
				</tr>
				
				<tr>
					<td width="9%" height="20" ><img id="xiaotu11" src="${pageContext.request.contextPath}/images/ico06.gif" width="8" height="12" /></td>
					<td width="91%"><a href="/hrm/educate/lookThroughView" target="mainFrame" class="left-font03" onClick="tupian('11');">培训审核</a></td>
				</tr>
				<tr>
					<td width="9%" height="20" ><img id="xiaotu20" src="${pageContext.request.contextPath}/images/ico06.gif" width="8" height="12" /></td>
					<td width="91%"><a href="/hrm/educate/chartView" target="mainFrame" class="left-font03" onClick="tupian('20');">培训统计</a></td>
				</tr>
				
			</table>
			<!--  项目系统结束    -->

			<!--  客户系统开始    -->
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="left-table03">
				<tr>
					<td height="29"><table width="85%" border="0" align="center" cellpadding="0" cellspacing="0">
						<tr>
							<td width="8%" height="12"><img name="img2" id="img2" src="${pageContext.request.contextPath}/images/ico04.gif" width="8" height="11" /></td>
							<td width="92%"><a href="javascript:" target="mainFrame" class="left-font03" onClick="list('2');" >奖惩管理</a></td>
						</tr>
					</table></td>
				</tr>
			</table>

			<table id="subtree2" style="DISPLAY: none" width="80%" border="0" align="center" cellpadding="0" cellspacing="0" class="left-table02">

				<tr>

					<td width="9%" height="20" ><img id="xiaotu9" src="${pageContext.request.contextPath}/images/ico06.gif" width="8" height="12" /></td>
					<td width="91%"><a href="${pageContext.request.contextPath }/institution/addInstitution" target="mainFrame" class="left-font03" onClick="tupian('9');">奖惩信息登记</a></td>

				</tr>
				<tr>
					<td width="9%" height="20" ><img id="xiaotu15" src="${pageContext.request.contextPath}/images/ico06.gif" width="8" height="12" /></td>
					<td width="91%"><a href="${pageContext.request.contextPath }/institution/tolistInstitution" target="mainFrame" class="left-font03" onClick="tupian('15');">奖惩信息查看</a></td>
				</tr>
			</table>

			<!--  客户系统结束    -->

			<!--  人员系统开始    -->
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="left-table03">
				<tr>
					<td height="29">
						<table width="85%" border="0" align="center" cellpadding="0" cellspacing="0">
							<tr>
								<td width="8%" height="12"><img name="img3" id="img3" src="${pageContext.request.contextPath}/images/ico04.gif" width="8" height="11" /></td>
								<td width="92%"><a href="javascript:" target="mainFrame" class="left-font03" onClick="list('3');" >薪金管理</a></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>

			<table id="subtree3" style="DISPLAY: none" width="80%" border="0" align="center" cellpadding="0" cellspacing="0" class="left-table02">
				<%-- <tr>
					<td width="9%" height="20" ><img id="xiaotu13" src="${pageContext.request.contextPath}/images/ico06.gif" width="8" height="12" /></td>
					<td width="91%"><a href="/hrm/stipend/addStipendView" target="mainFrame" class="left-font03" onClick="tupian('13');">固定薪金设置</a></td>
				</tr> --%>
				<tr>
					<td width="9%" height="20" ><img id="xiaotu19" src="${pageContext.request.contextPath}/images/ico06.gif" width="8" height="12" /></td>
					<td width="91%"><a href="/hrm/stipend/edittable" target="mainFrame" class="left-font03" onClick="tupian('19');">薪金数据录入</a></td>
				</tr>
				<tr>
					<td width="9%" height="20" ><img id="xiaotu12" src="${pageContext.request.contextPath}/images/ico06.gif" width="8" height="12" /></td>
					<td width="91%"><a href="/hrm/stipend/listStipendView" target="mainFrame" class="left-font03" onClick="tupian('12');">薪金数据查看</a></td>
				</tr>
				
			</table>

			<!--  人员系统结束    -->



			<!--  考勤管理开始   -->
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="left-table03">
				<tr>
					<td height="29">
						<table width="85%" border="0" align="center" cellpadding="0" cellspacing="0">
							<tr>
								<td width="8%" height="12"><img name="img5" id="img5" src="${pageContext.request.contextPath}/images/ico04.gif" width="8" height="11" /></td>
								<td width="92%"><a href="javascript:" target="mainFrame" class="left-font03" onClick="list('5');" >考勤管理</a></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>

			<table id="subtree5" style="DISPLAY: none" width="80%" border="0" align="center" cellpadding="0" cellspacing="0" class="left-table02">
				<tr>
					<td width="9%" height="20" ><img id="xiaotu21" src="${pageContext.request.contextPath}/images/ico06.gif" width="8" height="12" /></td>
					<td width="91%"><a href="${pageContext.request.contextPath }/attendance/addAttendance" target="mainFrame" class="left-font03" onClick="tupian('21');">考勤信息录入</a></td>
				</tr>
				<tr>
					<td width="9%" height="20" ><img id="xiaotu22" src="${pageContext.request.contextPath}/images/ico06.gif" width="8" height="12" /></td>
					<td width="91%"><a href="${pageContext.request.contextPath }/attendance/attendanceList" target="mainFrame" class="left-font03" onClick="tupian('22');">考勤信息查看</a></td>
				</tr>
			</table>

			<!--  考勤管理结束   -->

<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
					class="left-table03">
					<tr>
						<td height="29">
							<table width="85%" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td width="8%"><img name="img19" id="img19"
										src="${pageContext.request.contextPath}/images/ico04.gif"
										width="8" height="11" /></td>
									<td width="92%"><a href="javascript:" target="mainFrame"
										class="left-font03" onClick="list('19');">部门职级管理</a></td>
								</tr>

							</table>
						</td>
					</tr>
				</TABLE>

				<table id="subtree19" style="DISPLAY: none" width="80%" border="0"
					align="center" cellpadding="0" cellspacing="0" class="left-table02">
					<tr>
						<td width="9%" height="20"><img id="xiaotu29"
							src="${pageContext.request.contextPath}/images/ico06.gif"
							width="8" height="12" /></td>
						<td width="91%"><a href="/hrm/department/departmentView"
							target="mainFrame" class="left-font03" onClick="tupian('29');">部门职位</a></td>
					</tr>
					<tr>
						<td width="9%" height="20"><img id="xiaotu29"
							src="${pageContext.request.contextPath}/images/ico06.gif"
							width="8" height="12" /></td>
						<td width="91%"><a href="/hrm/department/jobManage"
							target="mainFrame" class="left-font03" onClick="tupian('29');">职级管理</a></td>
					</tr>
				</table>

		</TD>
	</tr>
    
</table>
<div class='showClass' id="showClass" >
   <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close" ><span aria-hidden="true">×</span></button>
                    <h4 class="modal-title" id="selectRoleLabel">消息提示</h4>
                </div>
                <form id="selectRole" action="/hrm/roles/addUserRole" method="post">
	                <div class="modal-body" id="addMessageContent">
	                    
							<!--  此处动态添加消息内容链接-->
	                    
	                </div></form>
	                <div class="modal-footer">
	                    <a type="button" class="btn btn-primary" id="save"
	                    onclick="ignoreCurrentMessage()">忽略</a>
	                </div>
            </div>
</div>
</body>
<script>
$(document).ready(function()
{
	//ajax轮询查询信息
	setInterval(function(){
		showMsg();
	},20000);
	function showMsg()
    {
        var url="${pageContext.request.contextPath }/message/findIsRead";
        var params=
        {
        };

        $.post(url,params,function(result)
        {


//            console.info(result);
            //培训消息数量
            var educateMessageCount=0;
            //奖惩消息数量
            var institutionMessageCount=0;
            //培训通知数量
            callStudentCount=0;
          //每次加载消息内容前清空
            $("#addMessageContent").html("");
            if(result!=null)
            {
           		
                $.each(result,function(n,value)
                {
                    // 培训消息的数量
                    if(value.isRead==false&&value.message_type==0)
                    {
                        educateMessageCount++;
                    }
                    //奖惩消息的数量
                    if(value.isRead==false&&value.message_type==1)
                    {

                        institutionMessageCount++;
                        //alert("奖惩信息"+institutionMessageCount);
                    }
                    //培训通知的数量
                    if(value.isRead==false&&value.message_type==2)
                    {
                        callStudentCount++;
                      //  alert("培训通知数量"+callStudentCount);
                    }
                });
//                console.info(institutionMessageCount);
                if(institutionMessageCount>0)
                {
                        var institutionMessage="<a id='hideFn' href='/hrm/institution/tolistInstitution'";
                        institutionMessage+="target='mainFrame' >";
                        institutionMessage+="您有"+institutionMessageCount+"条新的奖惩信息！";
                        institutionMessage+="</a>";
                        $("#addMessageContent").append(institutionMessage);
             
                        showDiv();
                        $("#hideFn").click(function()
                        {
                         	$('.showClass').removeClass('showClassActivit');
	                    });
                }
                if(educateMessageCount>0)
                {
                	 var educateMessage="<a id='hiddenFn' href='/hrm/educate/lookThroughView'";
                     educateMessage+="target='mainFrame' id='educateMessageLink'>";
                     educateMessage+="您有"+educateMessageCount+"个新的培训计划待审核！";
                     educateMessage+="</a>";
                     $("#addMessageContent").append("<br>");
                     $("#addMessageContent").append(educateMessage);
                     showDiv();
                      $("#hiddenFn").click(function()
                      {
                         $('.showClass').removeClass('showClassActivit');
	                  });
                }  
                if(callStudentCount>0)
                {
                        var callStudentMessage="<a id='hideFn' href='/hrm/educate/studentEducateRecordView'";
                        callStudentMessage+="target='mainFrame' >";
                        callStudentMessage+="您有新的培训通知！";
                        callStudentMessage+="</a>";
                        $("#addMessageContent").append("<br>");
                        $("#addMessageContent").append(callStudentMessage);
             
                        showDiv();
                        $("#hideFn").click(function()
                        {
                         	$('.showClass').removeClass('showClassActivit');
	                    });
                }
            }
        },"json");

    }

	//显示消息推送框
	function showDiv(){
	  $('.showClass').addClass('showClassActivit');
	}
	
	//点击忽略事件，点击后认为该消息已读
	$("#save").click(function(){
		//发送ajax请求，异步将状态改为已读
		var url="${pageContext.request.contextPath }/message/ignoreCurrentMessage";
        var params=
        {
        };

        $.post(url,params,function(result)
        {
        });
		
		 $('.showClass').removeClass('showClassActivit');

  	});
  	//关闭消息推送框事件
  	$(".close").click(function(){
		 $('.showClass').removeClass('showClassActivit');
  	});
  	


	
});

	
</script>
</html>
