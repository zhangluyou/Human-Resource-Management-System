<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>人力资源管理系统</title>
    <link rel="stylesheet" rev="stylesheet" href="${pageContext.request.contextPath}/css/style.css " type="text/css"
          media="all"/>
    <link rel="stylesheet"
                             href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/Js/typem.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/Js/js.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/Js/makeForm.js"></script>

    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <script
            src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style type="text/css">
        <!--
        .atten {
            font-size: 12px;
            font-weight: normal;
            color: #F00;
        }

        -->
    </style>
    <script type="text/javascript">
        function tiaozhuan(){
            location.href ='${pageContext.request.contextPath}/institution/tolistInstitution';
        }
    </script>
</head>
<body class="ContentBody">

<form name="institutionForm" method="post" action="modifyinstitution.do?action=updateInstitution"
      onSubmit="return institutionValidate()">
    <div class="MainDiv">
        <table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
            <tr>
                <th class="tablestyle_title">修改奖惩信息</th>
            </tr>

            <tr>
                <td class="CPanel">
                    <table border="0" cellpadding="0" cellspacing="0" style="width:80%" align="center">
                        <tr>
                            <td align="left">
                                <input type="button" value="返回"  onclick="tiaozhuan()" class="button"/>
                            </td>
                        </tr>
                        <tr>
                            <td width="100%">
                                <fieldset style="height:100%;">
                                    <legend>修改奖惩信息</legend>

                                    <table border="0" cellpadding="2" cellspacing="1" style="width:100%">
                                        <tr>
                                            <td nowrap align="right" width="11%">奖惩对象：</td>
                                            <td width="89%"><input name="name" type="text" class="input" value="${updateDetail.username}"
                                                                   placeholder="请选择" onclick="chooseUser('1')"
                                                                   data-toggle="modal"  data-target="#myModal" id="userName"
                                            />
                                                <span class="red">*</span></td>
                                        </tr>
                                        <tr>
                                            <input id="usernameTemp" type="hidden"  value="" />
                                            <input id="userIdTemp" type="hidden" name="userid" value=""/>
                                        </tr>
                                        <tr>
                                            <td nowrap align="right" width="11%">奖惩类型：</td>
                                            <td><input name="type" type="text" class="input"
                                                       value="${updateDetail.type}"/>
                                                <span class="red">*</span></td>
                                        </tr>

                                        <tr>
                                            <td width="11%" nowrap align="right">奖惩说明：</td>
                                            <td><textarea name="content" cols="100" rows="6" class="input"
                                                              id="explain" ><c:out value="${updateDetail.content}"/></textarea></td>
                                        </tr>
                                        <tr>
                                            <input  type="hidden" name="id" value="${updateDetail.id}"/>
                                            <input type="hidden" name="msgid" value="${updateDetail.msgid}"/>
                                            <input  type="hidden" name="userid" value="${updateDetail.userid}"/>
                                            <input  type="hidden" name="isRead" value="${updateDetail.isRead}"/>
                                        </tr>
                                    </table>
                                </fieldset>
                            </td>

                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center" height="50px">
                    <input name="提交" type="submit" class="button" value="保存"/>　

                    <input name="重置" type="reset" class="button" value="重置"/></TD>
            </tr>



            </table>
        </table>
    </div>
</form>
<%--遮罩窗体--%>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content" >
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                <h4 class="modal-title" id="selectRoleLabel">选择用户</h4>
            </div>
            <form id="chooseUsers" action="${pageContext.request.contextPath }/institution/chooseUsers" method="post">
                <div class="modal-body">
                    <form id="boxRoleForm">
                        <th><input type="checkbox" onclick="selectAll(this)">全选</th>
                        <div id="dynamicBox">
                            <!-- 此处为动态加载的input -->
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <!-- 	                onclick="document:chooseUsers.submit();" -->
                    <a type="button" class="btn btn-default" data-dismiss="modal">关闭</a>
                    <a type="button"  class="btn btn-primary" onclick="addUsersToInput()">确定</a>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>

