<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>人力资源管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
  <link href="http://www.jq22.com/jquery/bootstrap-3.3.4.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/css/fileinput.css" media="all" rel="stylesheet" type="text/css" />
  <script src="http://www.jq22.com/jquery/1.11.1/jquery.min.js"></script>
  <script src="${pageContext.request.contextPath}/Js/fileinput.js" type="text/javascript"></script>
  <script src="${pageContext.request.contextPath}/Js/locales/zh.js" type="text/javascript"></script>
  <script src="http://www.jq22.com/jquery/bootstrap-3.3.4.js" type="text/javascript"></script>

</head>

<body>
<%--<form method="POST"  enctype="multipart/form-data" id="form1" action="${pageContext.request.contextPath }/attendance/uploadExcel">--%>
  <%--<table>--%>
    <%--<tr>--%>
      <%--<td>上传文件: </td>--%>
      <%--<td> <input id="upfile" type="file" name="upfile"></td>--%>
    <%--</tr>--%>
    <%--<tr>--%>
      <%--<td><input type="submit" value="提交"></td>--%>
    <%--</tr>--%>
  <%--</table>--%>
<%--</form>--%>
<h4>考勤信息录入</h4>
<form method="POST"  enctype="multipart/form-data" id="form1" action="${pageContext.request.contextPath }/attendance/uploadExcel">
  <input id="file-zh" name="upfile" type="file" >

</form>


</body>
<script>
    $('#file-zh').fileinput({
        language: 'zh',
        uploadUrl: '${pageContext.request.contextPath }/attendance/uploadExcel',
        allowedFileExtensions : ['xls', 'xlsx']
    });




</script>
</html>
