<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>人力资源管理系统</title>
    <link rel="stylesheet" rev="stylesheet" href="${pageContext.request.contextPath}/css/style.css " type="text/css" media="all" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/Js/typem.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/Js/js.js"></script>


    <style type="text/css">
        <!--
        .atten {font-size:12px;font-weight:normal;color:#F00;}
        -->
    </style>
    <script type="text/javascript">
        function _tolist(){
            location.href ='/hrm/users/toListUser';
        }
    </script>
</head>
<body class="ContentBody">
<form name="userForm">
    <div class="MainDiv">
        <table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
            <tr>
                <th class="tablestyle_title" >人员信息详情</th>
            </tr>
        <%--<input type="hidden" name="id" value="<c:out value="${u.id}"/>"/>--%>
            <tr>
                <td class="CPanel">

                    <table width="90%" border="0" cellpadding="0" cellspacing="0" style="width:80%" align="center">
                        <tr>
                            <td align="left">

                            <input type="reset" value="返回" class="button" onclick="_tolist()"/>
                        </td>

                        </tr>

                        <TR>
                            <TD width="100%">
                                <fieldset style="height:100%;">
                                    <legend>人员信息</legend>
                                    <table width="100%" border="0" cellpadding="2" cellspacing="1" style="width:100%">
                                        <c:choose >
                                        <c:when test="${empty user}">
                                            <tr>
                                            <td height="22" colspan="2" align="center" >没有查到该人员信息！！！</td>
                                            </tr>
                                        </c:when>
                                        <c:otherwise>
                                        <tr>

                                            <td nowrap align="right" width="9%">用户名：</td>
                                            <td width="36%">
                                                <span>
                                                <c:out value="${user.username}"/>
                                            </span>
                                            </td>
                                            <td nowrap align="right" width="9%">真实姓名：</td>
                                            <td width="43%">
                                                ${user.name}
                                                </td></tr>
                                            <tr>

                                                <td nowrap align="right" width="9%">工号：</td>
                                                <td width="36%">
                                                <span>
                                                <c:out value="${user.jobId}"/>
                                            </span>
                                                </td>
                                                <td nowrap align="right" width="9%">职位：</td>
                                                <td width="43%">
                                                        ${user.positionId}
                                                </td></tr>
                                            <tr>
                                        <tr>



                                            <td nowrap align="right" width="9%">性别：</td>
                                            <td>
                                                <span>
                                                        <c:choose>
                                                            <c:when test="${user.sex==1}">
                                                                <c:out value="男"></c:out>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <c:out value="女" ></c:out>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </span></td>
                                            <td><div align="right">出生日期：</div></td>
                                            <td width="43%">
                                                <fmt:formatDate value="${user.birthday }" pattern="yyyy-MM-dd"/>

                                            </td>
                                        </tr>
                                        <tr>
                                            <td nowrap align="right">人员简介：</td>
                                            <td colspan="3"><span>  <c:out value="${user.content}" ></c:out></span></td>
                                        </tr>
                                        </c:otherwise>
                                        </c:choose>
                                    </table>
                                    <br />
                                </fieldset>
                            </TD>

                        </TR>
                    </TABLE>
                </td>
            </tr>

            <%--<tr>--%>
                <%--<td height="22" colspan="2" align="center" >没有查到该人员信息！！！</td>--%>
            <%--</tr>--%>
            <%--<%}%>--%>

        </TABLE>
    </div>
</form>
</body>
</html>

