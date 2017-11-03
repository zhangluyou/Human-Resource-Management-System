<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>人力资源管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css ">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/demo.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/Js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/Js/jquery.ztree.core.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/Js/jquery.ztree.excheck.js"></script>
    <%--<script type="text/javascript" src="${pageContext.request.contextPath}/Js/jquery.js"></script>--%>
    <%--<script type="text/javascript" src="${pageContext.request.contextPath}/Js/typem.js"></script>--%>
    <script type="text/javascript">
        window.onload = function () {
            var talentSex = $("#talentSex").val();
            $("input[name='sex']").each(function (index, element) {
                if ($(this).val() == talentSex) {
                    $(this).prop("checked", true);
                }
            });
            document.getElementById("username").onblur = function () {
                var name = $("#username").val();
                var filter = /^[0-9A-Za-z.@-_]{6,16}$/;
                var a = "1";
                $.ajax({
                    type: 'POST',
                    url: '${pageContext.request.contextPath}/users/verifyName?name=' + name,
                    contentType: "application/json; charset=utf-8",
                    //data : {"name":name},
                    dataType: 'json',
                    success: function (data) {
                        if (data.code == "000001") {
                            console.info(data.msg)
                            a = data.msg;
                            if (filter.test(name)) {
                                document.getElementById("msg").innerHTML = a + '，格式正确';
                            }

                            else {
                                document.getElementById("msg").innerHTML = '应为数字 字符 . @ - _ 字符组成 6到16个字符';
                                return;
                            }
                            // document.getElementById("msg").innerHTML=data.msg;
                        } else if (data.code == "000002") {
                            a = data.msg;
                            //document.getElementById("msg").innerHTML=data.msg;

                        }

                    }
                });


            }
        }
    </script>
    <script type="text/javascript">
        function formsubmit() {
            var params = {};
            var name = $("#username").val();
            var rel_name = $("#name").val();
            var password = $("#password").val();
            var birthday = $("#birthday").val();//获取值
            var sex = $('input:radio:checked').val();
            var content = $("#content").val();
            var position = $('#citySel').val();

            params.name = rel_name;
            params.username = name;
            params.password = password;
            params.birthday = birthday;
            params.sex = sex;
            params.content = content;
            params.positionId = position;

            if (name == null || "" == name) {
                alert("用户名不能为空");
                return false;
            }
            if (rel_name == null || "" == rel_name) {
                alert("姓名不能为空");
                return;
            }
            if ($("#msg").text() == "用户名不可用") {
                return;
            }

            if (password == null || "" == password) {
                alert("密码不能为空");
                return;
            }
            if (birthday == null || "" == birthday) {
                alert("出生日期不能为空");
                return;
            }
            //console.info(JSON.stringify($('#userForm').serialize()));
            $.ajax({
                type: 'POST',
                url: '${pageContext.request.contextPath}/users/addUser',
                data: {positionId:params.positionId,name:params.name,username:params.username,password:params.password,birthday:params.birthday,sex:params.sex,content:params.content},
                //data:JSON.stringify(params),
                dataType: 'json',
                error: function (data) {
                    alert("请求失败，网络异常")

                },
                success: function (data) {
                    if (data.code == "000000") {
                        alert(data.msg);

                    } else {
                        alert(data.msg);
                    }
                }
            });


        }
    </script>
    <script type="text/javascript">
        <!--
        var zNode;
        var setting = {
            check: {
                enable: true,
                chkboxType: {"Y": "", "N": ""}
            },
            view: {
                dblClickExpand: false
            },
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback: {
                beforeClick: beforeClick,
                onCheck: onCheck
            }
        };
        function getTree() {
            $.post("/hrm/users/assignjob", function (data) {
                zNode = data.data;
                for (var i = 0; i < zNode.length; i++) {
                    if (zNode[i].isParent == 1) {
                        zNode[i].open = true;
                        zNode[i].nocheck = true;
                    }
                }
                $.fn.zTree.init($("#treeDemo"), setting, zNode);

            })
        }
        function beforeClick(treeId, treeNode) {
            var zTree = $.fn.zTree.getZTreeObj("treeDemo");
            zTree.checkNode(treeNode, !treeNode.checked, null, true);
            return false;
        }

        function onCheck(e, treeId, treeNode) {
            var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
                nodes = zTree.getCheckedNodes(true),
                v = "";
           // vs = [];
            /*va = "";*/
            for (var i = 0, l = nodes.length; i < l; i++) {
                v +=nodes[i].getParentNode().name+":"+ nodes[i].name + ",";

//                var c={};//传入一个对象
////                c.pId=nodes[i].pId;
////                c.id=nodes[i].id+100000000;
//                vs.push(c);

            }

            if (v.length > 0) v = v.substring(0, v.length - 1);
            var cityObj = $("#citySel");
            cityObj.attr("value", v);
            /*if (vs.length > 0) v = vs.substring(0, v.length - 1);*/
            var cityObj2 = $("#assignId2");
        //    cityObj2.attr("value", JSON.stringify(vs));

        }

        function showMenu() {
            var cityObj = $("#citySel");
            var cityOffset = $("#citySel").offset();
            $("#menuContent").css({
                left: cityOffset.left + "px",
                top: cityOffset.top + cityObj.outerHeight() + "px"
            }).slideDown("fast");

            $("body").bind("mousedown", onBodyDown);
        }
        function hideMenu() {
            $("#menuContent").fadeOut("fast");
            $("body").unbind("mousedown", onBodyDown);
        }
        function onBodyDown(event) {
            if (!(event.target.id == "menuBtn" || event.target.id == "citySel" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length > 0)) {
                hideMenu();
            }
        }

        $(document).ready(function () {
            getTree();

        });
        //-->
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
<form id="userForm">
    <div class="MainDiv">
        <table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
            <tr>
                <th class="tablestyle_title">人员信息录入</th>
            </tr>
            <tr>
                <td class="CPanel">

                    <table width="90%" border="0" cellpadding="0" cellspacing="0" style="width:80%" align="center">
                        <tr>
                            <td align="left">
                                <%--<input type="submit"value="保存" class="button"/>　--%>


                            </td>
                        </tr>

                        <TR>
                            <TD width="100%">
                                <fieldset style="height:100%;">
                                    <legend>人员信息</legend>
                                    <table width="100%" border="0" cellpadding="2" cellspacing="1" style="width:100%">

                                        <tr>
                                            <td nowrap align="right" width="9%">用户名：</td>
                                            <td width="36%">
                                                <input name="username" type="text" class="input" id="username"/>
                                                <span class="red">*</span>&nbsp;<span id="msg"></span></td>
                                            <td nowrap align="right" width="9%">真实姓名：</td>
                                            <td width="36%">
                                                <input name="name" type="text" class="input" value="${name}" id="name"/>
                                                <span class="red">*</span>&nbsp;
                                            </td>

                                        </tr>
                                        <tr>
                                            <td width="12%">
                                                <div align="right">登陆密码：</div>
                                            </td>
                                            <td width="43%">
                                                <input name="password" type="password" class="input" id="password"/>
                                                <span class="red">*</span></td>

                                            <td>
                                                <div align="right"><label for="birthday">出生日期：</label></div>
                                            </td>
                                            <td>
                                                <input name="birthday" TYPE="date" value="<fmt:formatDate value="${birthday }"
                                                                            pattern="yyyy-MM-dd"/>" class="input"
                                                       id="birthday"/>
                                                <span class="red">*</span></td>

                                            <%--<input  name="birthday" id="birth
                                            day" type="text"  />--%>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td width="12%">
                                                <div align="right">部门及职位：</div>
                                            </td>
                                            <td width="43%">

                                                <input id="citySel" type="text" readonly style="width:120px;"
                                                       onclick="showMenu();"/>
                                                <a id="menuBtn" href="#" onclick="showMenu(); return false;">select</a>
                                                <span class="red">*</span></td>
                                            <input id="assignId2" name="positionId" type="hidden" value="">　
                                            <td nowrap align="right" width="9%">性别：</td>
                                            <td>
                                                <input id="talentSex" type="hidden" value="${sex}"/>
                                                <input name="sex" type="radio" value="1"> 男
                                                <input name="sex" type="radio" value="0" checked>女
                                            </td>
                                        </tr>

                                        <tr>
                                            <%--<td nowrap align="right">是否管理员：</td>--%>
                                            <%--<td>--%>
                                            <%--<input type="checkbox">--%>
                                            <%--<input type="hidden"  name="isadmin" id="isadmin"></td>--%>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td nowrap align="right">人员简介：</td>
                                            <td colspan="3">
                                                <textarea name="content" cols="100" rows="6" class="input"
                                                          id="content"></textarea></td>
                                        </tr>
                                    </table>
                                    <br/>
                                </fieldset>
                            </TD>

                        </TR>
                    </TABLE>
                </td>
            </tr>
            <TR>
                <TD colspan="2" align="center" height="50px">
                    <%--<button onclick="submit()">保存</button>--%>
                    <input name="提交" type="button" class="button" value="保存" onclick="formsubmit()">　
                    <input name="重置" type="reset" class="button" value="重置"/>


                </TD>

            </TR>
        </TABLE>
    </div>
    <div id="menuContent" class="menuContent" style="display:none; position: absolute;">
        <ul id="treeDemo" class="ztree" style="margin-top:0; width:180px; height: 300px;"></ul>
    </div>
</form>


</body>
</html>

