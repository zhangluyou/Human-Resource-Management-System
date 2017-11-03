<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>人力资源管理系统</title>
<link rel="stylesheet" rev="stylesheet" href="${pageContext.request.contextPath}/css/style.css "
	type="text/css"/>	
<link rel="stylesheet" rev="stylesheet" href="${pageContext.request.contextPath}/css/calendar/date.css "
	type="text/css"/>
<link rel="stylesheet" rev="stylesheet" href="${pageContext.request.contextPath}/css/calendar/laydate.css"
	type="text/css"/>

</head>
<body class="ContentBody">
	<form  action="" id="addForm" method="post" >
	<input type="hidden" id="proBaseId" value="${pageContext.request.contextPath}"/>
		<div class="MainDiv">
			<table width="99%" border="0" cellpadding="0" cellspacing="0"
				class="CContent">
				<tr>
					<th class="tablestyle_title" height="40px">应聘信息录入</th>
				</tr>
				<tr>
					<td class="CPanel">
						<table border="0" cellpadding="0" cellspacing="0"
							style="width: 80%" align="center">
							<tr>
								<td width="100%" height="300px">
									<fieldset style="height: 100%;">
										<legend>求职申请表</legend>
										<table border="0" cellpadding="2" cellspacing="1" style="width: 100%">
											<tr style="height: 60px">
												<td width="15%">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</td>
												<td width="50%">
												    <input name="name" type="text" class="input" /> 
												</td>
												<td width="15%">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</td>
												<td width="50%">
												    <input name="sex" type="radio" value="1" checked="checked" />男
												    <input name="sex" type="radio" value="0" />女
												</td>
										    </tr>
										    
										    <tr style="height: 60px">
												<td width="15%">出生日期：</td>
												<td width="50%">
												    <div class="demo2">
		                                                <input name="time" placeholder="选择日期" class="laydate-icon" onClick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" />
	                                                </div>
												</td>
												<td width="15%">毕业学校：</td>
												<td width="50%">
												    <input name="school" type="text" class="input" /> 
												</td>
											</tr>
											
											<tr style="height: 60px">
												<td width="15%">所学专业：</td>
												<td width="50%">
												    <input name="major" type="text" class="input" /> 
												</td>
												<td width="15%">学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;历：</td>
												<td width="50%">
												    <input name="educationBg" type="text" class="input" /> 
												</td>
											</tr>
											
											<tr style="height: 80px">
												<td width="15%">工作经历：</td>
												<td width="100%" style="">
												    <input name="phoneNo" type="text" class="input" style="width: 184px;border:  solid #C6C6C6;"/> 
												</td>
											</tr>
											
											<tr style="height: 60px">
												<td width="15%">联系电话：</td>
												<td width="50%">
												    <input name="phoneNo" type="text" class="input" /> 
												</td>
												<td width="15%">Email：</td>
												<td width="50%">
												    <input name="email" type="text" class="input" /> 
												</td>
											</tr>
											
											<tr>
												<td><div >职位：</div></td>
												<td>
												    <input name="job" type="text" class="input" />
												</td>
												<td width="15%">录入时间：</td> 
												<td width="50%">
												    <div class="demo2">
		                                                <input name="time" placeholder="选择日期" class="laydate-icon" onClick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" />
	                                                </div>
												</td>
											</tr>
											<%-- <tr style="height: 70px" >
												 <td>
												  <div class="message-content-title" style="position: relative">
                  										<label>简历上传：</label>
                  										<input type="hidden" name="resume" class="resume"/>
                  										<img class="upload-icon" src="${pageContext.request.contextPath}/images/upload2.png" alt="点击添加" id="logo"  style="width:150px;height:100px"/>
                  										<span class="msg" style="font-size: 12px;display:none">上传成功</span>
              										</div> 
												</td>
											</tr> --%>
										</table>
									</fieldset>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center" height="50px">
					    <input id="btn_submit" name="提交" type="button" class="button" value="保存" /> 
					    <input name="重置" type="reset" class="button" value="重置" />
					</td>
				</tr>
			</table>
		</div>
	</form>

<script type="text/javascript" src="${pageContext.request.contextPath}/Js/js.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/Js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/Js/typem.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/Js/upload/jquery.validate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/Js/upload/fileUpload.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/Js/upload/formCheck.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/Js/upload/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/Js/laydate.js"></script>

<script type="text/javascript">
$('#btn_submit').click(function(){
	var url = "/hrm/candidate/save.do";    
	$("#addForm").attr("action", url);  
	$('#addForm').submit();
});
/* $(function(){
		$("#logo").click(function(){
			 var url =$("#proBaseId").val()+"/fileOperation/fileUploadWithLoginOption";
			 return fileUpload.upload(this,function(obj,msg){
			 if(msg.error=="0"){
				 $(".msg").show();
				 $(".resume").val(msg.url);
			 }		 	
		},url); 
		});
}); */
</script>

</body>
</html>

