<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>人力资源管理系统</title>
    <link rel="stylesheet" rev="stylesheet" href="${pageContext.request.contextPath}/css/style.css " type="text/css"
          media="all"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/Js/typem.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/Js/js.js"></script>

    <script type="text/javascript">
        function _tolist(){
            location.href ='http://localhost:8080/hrm/institution/tolistInstitution';
        }
    </script>
    <style type="text/css">
        <!--
        .atten {
            font-size: 12px;
            font-weight: normal;
            color: #F00;
        }

        -->
    </style>
</head>
<body class="ContentBody">
<div class="MainDiv">
    <table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
        <tr>
            <th class="tablestyle_title">奖惩信息详情</th>
        </tr>
        <tr>
            <td class="CPanel">
                <table border="0" cellpadding="0" cellspacing="0" style="width:80%" align="center">
                    <tr>
                        <td width="100%">
                            <fieldset style="height:100%;">
                                <legend>奖惩信息详情</legend>
                                <table border="0" cellpadding="8" cellspacing="1" style="width:100%">
                                    <tr>
                                        <td nowrap align="right" width="11%">奖惩对象用户名：</td>
                                        <td> <span>
                                                <c:out value="${insLists.username}"/>
                                            </span></td>
                                    </tr>
                                    <tr>
                                        <td nowrap align="right" width="11%">奖惩对象姓名：</td>
                                        <td> <span>
                                                <c:out value="${insLists.name}"/>
                                            </span></td>
                                    </tr>
                                    <tr>
                                    <tr>
                                        <td nowrap align="right" width="11%">奖惩类型：</td>
                                        <td>${insLists.type}</td>
                                    </tr>
                                    <tr>
                                        <td width="11%" nowrap align="right">奖惩说明：</td>
                                        <td>${insLists.content}</td>
                                    </tr>
                                    <td width="11%" nowrap align="right">奖惩时间：</td>
                                    <td>${insLists.createtime}</td>
                                    </tr>
                                    <tr>
                                        <td height="22" colspan="2" align="center">
                                            <%--<a href="updateinstitution.do?action=detailinstitution&id=">修改</a>&nbsp;&nbsp;--%>
                                            <a href="#" onClick="_tolist()">返回</a>
                                        </td>
                                    </tr>
                                </table>
                            </fieldset>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</div>
</body>
</html>

