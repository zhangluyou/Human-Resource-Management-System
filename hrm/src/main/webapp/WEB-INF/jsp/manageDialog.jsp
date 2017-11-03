<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'manageDialog.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet"
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
	  <script type="text/javascript"
	src="${pageContext.request.contextPath}/Js/msgbox.js"></script>
	<script
		src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  </head>
  
  <body>
   <div class='showClass'>
   <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                    <h4 class="modal-title" id="selectRoleLabel">添加角色</h4>
                </div>
                <form id="selectRole" action="/hrm/roles/addUserRole" method="post">
	                <div class="modal-body">
	                    
	                    	<input value="1" type="hidden" name="currPage">
	                    	<input value="" type="hidden" name="searchText">
	                   		<div id="dynamicBox"><tr><td><input type="hidden" name="userId" id="userId" value="40"></td></tr><tr><td><label><input type="checkbox" id="roleIds" name="roleIds" value="63">admin</label></td></tr><tr><td><label><input type="checkbox" checked="checked" id="roleIds" name="roleIds" value="65">普通用户</label></td></tr></div>	
	                    
	                </div></form>
	                <div class="modal-footer">
	                    <a type="button" class="btn btn-default" data-dismiss="modal">关闭</a>
	                    <a type="button" class="btn btn-primary" onclick="document:selectRole.submit();">保存</a>
	                </div>
               
            </div>
</div>
  </body>
</html>
