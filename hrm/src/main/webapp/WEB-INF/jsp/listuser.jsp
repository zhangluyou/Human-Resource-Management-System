<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>人力资源管理系统</title>
    <link href="${pageContext.request.contextPath}/css/css.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery.pagination.css"/>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/Js/jquery-1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/Js/timeFormat.js"></script>
    <script src="${pageContext.request.contextPath}/Js/jquery.pagination.min.js"></script>
    <script src="${pageContext.request.contextPath}/Js/handlebars-v4.0.2.js"></script>
    <style type="text/css">
        <!--
        body {
            margin-left: 0px;
            margin-top: 0px;
            margin-right: 0px;
            margin-bottom: 0px;
        }

        .tabfont01 {
            font-family: "宋体";
            font-size: 9px;
            color: #555555;
            text-decoration: none;
            text-align: center;
        }

        .font051 {
            font-family: "宋体";
            font-size: 12px;
            color: #333333;
            text-decoration: none;
            line-height: 20px;
        }

        .font201 {
            font-family: "宋体";
            font-size: 12px;
            color: #FF0000;
            text-decoration: none;
        }

        .button {
            font-family: "宋体";
            font-size: 14px;
            height: 37px;
        }

        html {
            overflow-x: auto;
            overflow-y: auto;
            border: 0;
        }
    </style>
    <style>
        * {
            margin: 0;
            padding: 0;
        }

        body {
            font-family: "微软雅黑";
            /*background: #eee;*/
        }

        .box {
            width: 800px;
            margin: 100px auto 0;
            height: 34px;
        }

        .page {
            width: 600px;
        }

        .info {
            width: 200px;
            height: 34px;
            line-height: 34px;
        }

        .fl {
            float: left;
        }
    </style>
</head>

<body>

<table width="100%" border="0" cellspacing="0" cellpadding="0">

    <tr>
        <td height="30">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td height="62" background="${pageContext.request.contextPath}/images/nav04.gif">&nbsp;</td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td>
            <%--style="display:none"--%>
            <table id="subtree1" class="table" width="100%" border="0" cellspacing="0" cellpadding="0" >
                <tr>

                    <td>
                    <th class="tablestyle_title">人员信息查看</th>
                    <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" >

                        <tr>
                            <%--<td height="20"><span class="newfont07">人员信息查看</span>--%>
                            </td>


                        </tr>
                        <tr>
                            <td height="40" style="text-align: right" class="td2">
                                <input type="button" onclick="delEdu()" value="批量删除"
                                       style="color:#0080FF;  border-radius:5px; vertical-align:middle;height:30px;  width:70px; "/>
                            </td>
                        </tr>
                        <tr>
                            <td class="td1">

                                <input name="jumpMenu" type="radio" value="1"> 入职时间
                                <input name="jumpMenu" type="radio" value="2">出生日期
                                <input type="date" value="${startTime}" id="startTime"
                                       class="input"/>
                                <input type="date" value="${endTime}" id="endTime"
                                       class="input"/>


                                <input type="text" placeholder="输入姓名关键字" value="" id="nameIn"
                                       class="input"/>
                                <input type="button" value="搜索"
                                       style="color:#0080FF;  border-radius:5px; vertical-align:middle;height:30px;  width:50px; "
                                       onclick="lookup()"/>
                            </td>
                        </tr>
                        <tr>
                            <td height="40" class="font42">
                                <table width="100%" border="0" cellpadding="4" cellspacing="1" bgcolor="#464646"
                                       class="newfont03">
                                    <thead>
                                    <tr class="CTitle">
                                        <td height="22" colspan="10" align="center" style="font-size:16px">人员信息列表
                                        </td>
                                    </tr>
                                    <tr bgcolor="#EEEEEE">
                                        <td width="10%" align="center" height="30">工号</td>
                                        <td width="10%" align="center" height="30">用户名</td>
                                        <td width="10%" align="center" height="30">真实姓名</td>

                                        <td width="10%" align="center">性别</td>
                                        <td width="10%" align="center">出生日期</td>
                                        <td width="10%" align="center">入职时间</td>
                                        <td width="15%" align="center">职位</td>
                                        <td width="10%" align="center">简介</td>
                                        <td width="15%" align="center">执行操作</td>
                                    </tr>
                                    </thead>
                                    <tbody id="usersTable">

                                    </tbody>

                                </table>
                            </td>

                        </tr>
                    </table>


                    </td>

                </tr>
                <%--<input type="hidden" id="tol" value="${tolcount}"/>--%>
                <%--<input type="hidden" id="pagesize" value="${pageSize}"/>--%>
                <%--<input type="hidden" id="pagenum" value="${pageNum}"/>--%>
            </table>
                <div class="box">
                    <div id="pagination1" class="page fl"></div>
                    <div class="info fl">
                        <p>当前页数：<span id="current1" class="currentpage">1</span></p>
                    </div>
                </div>
        </td>
    </tr>
</table>
            <%--<script type="text/JavaScript">--%>
                <%--var toll = $('#tol').val();--%>
                <%--var psize = 5;--%>
                <%--var pNum = $('#pagenum').val();--%>
                <%--var total = Math.ceil(Math.ceil(toll) / Math.ceil(psize));--%>

                <%--$(function () {--%>

                    <%--$("#pagination1").pagination({--%>
                        <%--currentPage: pNum,--%>
                        <%--totalPage: total,--%>
                        <%--callback: function (current) {--%>
                            <%--// $("#current1").text(current)--%>
                            <%--pNum = current;--%>
                            <%--// location.href = '${pageContext.request.contextPath}/users/toListUser?pageNum='+pNum+"&pageSize="+psize;--%>

                            <%--$.ajax({--%>
                                <%--type: 'POST',--%>
                                <%--cache: false,--%>
                                <%--url: '${pageContext.request.contextPath}/users/toListUser?pageNum=' + pNum + "&pageSize=" + psize + "&",--%>
                                <%--contentType: "application/json; charset=utf-8",--%>
                                <%--//data : {"name":name},--%>

                                <%--success: function (data) {--%>
                                    <%--/*$(".table:gt(0)").hide();*/--%>

                                    <%--$("#subtree1").html(data);--%>
                                    <%--$(".box").eq(0).css("display", "none");--%>
                                    <%--if( $(".newfont03").length>1){--%>
                                        <%--$(".newfont03").eq(1).css("display", "none");--%>
                                        <%--$(".td1").eq(1).css("display", "none");--%>
                                        <%--$(".td2").eq(1).css("display", "none");--%>

                                    <%--}--%>

                                    <%--&lt;%&ndash;${".currentpage"}.eq(0).css("dispaly","block");&ndash;%&gt;--%>
                                    <%--$(".currentpage").text(current)--%>
                                <%--}--%>

                            <%--});--%>
                        <%--}--%>

                    <%--});--%>
                <%--});--%>
                <%--function lookup() {--%>
                    <%--var btn = $('#nameIn').val();--%>
                    <%--var drop = $('#jumpMenu option:selected').val();--%>
                    <%--var start = $('#startTime').val();--%>
                    <%--var end = $('#endTime').val();--%>
                    <%--if (btn == '') {--%>
                        <%--alert("请输入要查询的姓名!");--%>
                    <%--}--%>
                    <%--else {--%>
                        <%--location.href = '${pageContext.request.contextPath}/users/toListUser?sort=' + drop + "&startTime=" + start + "&endTime=" + end + "&username=" + btn;--%>

                    <%--}--%>

                <%--}--%>
            <%--</script>--%>
<script id="user_table" type="text/x-handlebars-template">
    {{#each usersList}}
    <tr bgcolor="#FFFFFF" id="userDateFor">
        <td height="22" align="center">
            {{jobId}}
        </td>
       <td height="22" align="center">
            {{username}}
        </td>
        <td height="22" align="center">
            {{name}}
        </td>
        <td height="22" align="center">
            {{sex}}
        </td>
        <td height="22" align="center">
            {{birthday}}
        </td>
        <td height="22" align="center">
        {{createtime}}
         </td>
        <td height="22" align="center">
            {{positionId}}
        </td>
        <td height="22" align="center">
            {{content}}
        </td>
        <td height="22" align="center">
            <a href="detailuserView.do?action=deleteUserView&id={{id}}">详情</a>
            <a href="modifyuser.do?action=modifyUser&id={{id}}">修改</a>
            <a href="deleteuser.do?action=deleteUser&id={{id}}">删除</a>
            <input type='checkbox' name='isSelect'value='{{id}}'/>
        </td>
    </tr>
    {{/each}}
</script>
<script src="${pageContext.request.contextPath}/Js/list3.js"></script>
</body>
</html>