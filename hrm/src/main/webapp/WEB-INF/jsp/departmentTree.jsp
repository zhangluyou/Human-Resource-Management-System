<%@page import="javax.swing.text.Document"%>
<%@page import="java.lang.*"%>
<%@ page import="org.aspectj.weaver.ast.Var"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page import="com.jointem.hrm.entity.*"%>
<%@ page import="java.util.Date"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<title>部门职位管理</title>
	<meta charset="UTF-8">
		<link rel="stylesheet"
			href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
			<script
				src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
			<script
				src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>

			<link rel="stylesheet"
				href="${pageContext.request.contextPath}/plug/ztree/css/demo.css"
				type="text/css"></link>
			<link rel="stylesheet"
				href="${pageContext.request.contextPath}/plug/ztree/css/zTreeStyle/zTreeStyle.css"
				type="text/css"></link>

			<script type="text/javascript"
				src="${pageContext.request.contextPath}/plug/ztree/js/jquery.ztree.core.js"></script>
			<script type="text/javascript"
				src="${pageContext.request.contextPath}/plug/ztree/js/jquery.ztree.excheck.js"></script>
			<script type="text/javascript"
				src="${pageContext.request.contextPath}/plug/ztree/js/jquery.ztree.exedit.js"></script>
			<style type="text/css">
.ztree li span.button.add {
	margin-left: 2px;
	margin-right: -1px;
	background-position: -144px 0;
	vertical-align: top;
	*vertical-align: middle
}
</style>
</head>

<body>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="62"
				background="${pageContext.request.contextPath}/images/nav04.gif">&nbsp;</td>
		</tr>
	</table>
	<div class="content_wrap">
		<div class="zTreeDemoBackground left">
			<ul id="treeDemo" class="ztree"></ul>
		</div>
		<div class="zTreeDemoBackground right">
			<div>
				<input id="positionName" type="text" placeholder="请输入职位"
					departmentId /> <select id="rankId"><span>
						<option value="0" selected>档</option></select> </span> <select id="fileId"><span>
						<option value="0" selected>级</option>" </select> </span> <input id="addPosition"
					type="button" value="添加职位" />
			</div>
			<div id="demo"></div>
		</div>
	</div>
	<div id="addConfirm" departId="" treeNode="" class="modal fade"
		tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">添加部门</h4>
				</div>
				<div class="modal-body">输入部门名称</div>
				<div class="modal-footer">
					<input id="departmentName" type="text"
						style="width: 100px; margin-top: 20px; margin-left: 15px" /> <input
						id="departAdd" type="button"
						style="margin-top: 10px; margin-left: 15px" value="确定"
						onclick="addDepart()" /> <input id="departCancel" type="button"
						style="margin-top: 10px; margin-left: 15px" value="取消"
						onclick="cancel()" />
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>




</body>
	<script type="text/javascript"
				src="${pageContext.request.contextPath}/Js/departmentTree.js"></script>
</html>