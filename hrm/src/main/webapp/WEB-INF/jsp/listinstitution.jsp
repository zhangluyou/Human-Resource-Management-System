<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>人力资源管理系统</title>
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

        -->
    </style>

    <link href="${pageContext.request.contextPath}/css/css.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/jquery.pagination.css" rel="stylesheet" type="text/css"/>

    <script type="text/javascript" src="${pageContext.request.contextPath}/Js/timeFormat.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/Js/jquery-1.js"></script>
    <script src="${pageContext.request.contextPath}/Js/jquery.pagination.min.js"></script>
    <script src="${pageContext.request.contextPath}/Js/handlebars-v4.0.2.js"></script>
    <script type="text/javascript">

      <%----%>
                <%--$.ajax({--%>
                    <%--type : 'POST',--%>
                    <%--url : '${pageContext.request.contextPath}/users/verifyName?name='+name,--%>
                    <%--contentType: "application/json; charset=utf-8",--%>
                    <%--//data : {"name":name},--%>
                    <%--dataType : 'json',--%>
                    <%--success : function(data) {--%>
                        <%--if(data.code =="000001"){--%>
                            <%--console.info(data.msg)--%>
                            <%--a=data.msg;--%>
                            <%--if (filter.test(name))--%>
                            <%--{--%>
                                <%--document.getElementById("msg").innerHTML=a+'，格式正确';--%>
                            <%--}--%>

                            <%--else{--%>
                                <%--document.getElementById("msg").innerHTML='应为数字 字符 . @ - _ 字符组成 6到16个字符';--%>
                                <%--return ;--%>
                            <%--}--%>
                            <%--// document.getElementById("msg").innerHTML=data.msg;--%>
                        <%--}else if(data.code =="000002"){--%>
                            <%--a=data.msg;--%>
                            <%--//document.getElementById("msg").innerHTML=data.msg;--%>

                        <%--}--%>

                    <%--}--%>
                <%--});--%>


            <%--}--%>
        <%--}--%>
    </script>

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
            <table id="subtree1" style="DISPLAY: " width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td>
                        <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
                            <tr>
                                <td height="20"><span class="newfont07">奖惩信息查看<br><br></span></td>
                            </tr>

                            <tr>

                                <td>

                                    <input type="button" value="搜索"
                                           style="float:right;color:#0080FF;  border-radius:5px; vertical-align:middle;height:30px;  width:50px; "
                                           onclick="lookup()"/>
                                    <input type="hidden" placeholder="输入姓名关键字" id="nameIn"
                                           style=" float:right;color:#95a5a6;  border-radius:5px; vertical-align:middle;height:30px;  width:140px; "/>

                                    <select name="isRead" style="float:right;color:#0080FF;  border-radius:5px; vertical-align:middle;height:30px;  width:140px; "   id="isRead">

                                        <option value="0"  >未读</option>
                                        <option value="1" selected="selected">已读</option>

                                    </select>&nbsp;&nbsp;
                                </td>
                                <br><br>
                            </tr>
                            <tr>
                                <td height="40" class="font42">
                                    <table width="100%" border="0" cellpadding="4" cellspacing="1" bgcolor="#464646"
                                           class="newfont03">
                                        <tr class="CTitle">
                                            <td height="22" colspan="8" align="center" style="font-size:16px">奖惩信息列表
                                            </td>
                                        </tr>
                                        <tr bgcolor="#EEEEEE">
                                            <td height="22" align="center">奖惩对象用户名</td>
                                            <td height="22" align="center">奖惩对象姓名</td>
                                            <td height="22" align="center">奖惩类型</td>
                                            <td height="22" align="center">奖惩说明</td>
                                            <td height="22" align="center">创建时间</td>
                                            <td height="22" align="center">操作</td>
                                        </tr>
                                        <%--<% List list=(List)request.getAttribute("list");--%>
                                        <%--if(list!=null&&list.size()>0){--%>
                                        <%--Iterator it = list.iterator();--%>
                                        <%--while (it.hasNext()) {--%>
                                        <%--Institution j = (Institution) it.next();--%>
                                        <%----%>
                                        <%--%>--%>
                                        <tbody id="institutionTableSmall">

                                        </tbody>


                                    </table>
                                </td>
                            <tr bgcolor="#FFFFFF" id="no_instution_list_data" style="display: none">
                                <td height="22" colspan="3" align="center">对不起，没有添加奖惩信息！！！</td>
                            </tr>
                            </tr>
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
            <script id="institutionSmall_table" type="text/x-handlebars-template">
                {{#each insSmallList}}
                <tr bgcolor="#FFFFFF">
                    <td height="22" align="center">{{username}}</td>
                    <td height="22" align="center">{{name}}</td>
                    <td height="22" align="center">{{type}}</td>
                    <td height="22" align="center">{{content}}</td>
                    <td height="22" align="center">{{createtime}}</td>
                    <td height="22" align="center">
                        <a href="detailinstitution.do?action=detailinstitution&id={{id}}">详细</a>&nbsp;&nbsp;
                        <%--<input  id="sure"type="button" value="确认" onclick="toSure()" style="color:#0080FF;  border-radius:5px; vertical-align:middle;height:20px;  width:40px; "/>--%>
                        <a href="isReadinstitution.do?action=isreadinstitution&msgid={{msgid}}"style=" color:red" id="msg2">{{isread}}</a>
                        <%--<span style=" color:#aaaaaa" id="msg">已读</span>--%>
                    </td>

                </tr>
                {{/each}}

            </script>

    </body>
<script type="text/javascript">
    Handlebars.registerHelper('isread', function () {
        if (this.isread == 1) {
            return "已读";
        } else {
            return "未读";
        }
    });
</script>
    <script src="${pageContext.request.contextPath}/Js/list2.js"></script>
</html>

