<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312"/>
    <title>人力资源管理系统</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css ">
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/Js/jquery.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/Js/typem.js"></script>



<link rel="stylesheet"
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
	<script
		src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body class="ContentBody">
<form name="institutionForm" method="post" action="${pageContext.request.contextPath}/institution/addRapAndRapMsg">
    <div class="MainDiv">
        <table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
            <tr>
                <th class="tablestyle_title">奖惩信息登记</th>
            </tr>
            <tr>
                <td class="CPanel">

                    <table border="0" cellpadding="0" cellspacing="0" style="width:80%" align="center">
                        <TR>
                            <TD width="100%">
                                <fieldset style="height:100%;">
                                    <legend>奖惩信息</legend>
                                    <table border="0" cellpadding="2" cellspacing="1" style="width:100%">
                                        <tr>
                                            <td nowrap align="right" width="11%">奖惩对象：</td>
                                            <td width="89%">
                                            	<input name="name" type="text" class="input"
                                            	 placeholder="请选择" onclick="chooseUser('1',this)"
                                            	 data-toggle="modal"  data-target="#myModal" id="userName"
                                            	 />
                                                <span class="red">*</span>
                                            </td>
                                        </tr>
                                        <tr>
                                        	<input id="usernameTemp" type="hidden"  value="" />
                                        	<input id="userIdTemp" type="hidden" name="userid" value=""/>
                                        </tr>
                                        <tr>
                                            <td nowrap align="right" width="11%">奖惩类型：</td>
                                            <td><input  type="text" class="input" name="type" />
                                                <span class="red">*</span></td>
                                        </tr>

                                        <tr>
                                            <td width="11%" nowrap align="right">奖惩说明：</td>
                                            <td><textarea name="content" cols="100" rows="6" class="input"
                                                          id="explain"></textarea></td>
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
                    <input name="提交" type="submit" class="button" value="保存"/>　

                    <input name="重置" type="reset" class="button" value="重置"/></TD>
            </TR>
        </TABLE>
    </div>
</form>


  <!-- 弹出选择用户的框========================================================= -->
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
    <!-- 弹出选择用户的框========================================================= -->
</body>
<script>

	//加载弹出选择用户框的数据
	function chooseUser(currPage,obj)
	{
	
		//发送ajax请求
		var url="${pageContext.request.contextPath }/institution/getStudentsList";
		var params=
		{
			currPage:currPage
		};
		$("#dynamicBox").html("");

        //加入全选
        if(obj==true|| obj=='true')
        {
            var content="<th>";
            content +="<input type='checkbox' checked='checked' onclick=selectAll(this,'"+currPage+"')>";
            content +="全选</th>";
            $("#dynamicBox").append(content);
        }
        else
        {
            var content="<th>";
            content +="<input type='checkbox'  onclick=selectAll(this,'"+currPage+"')>";
            content +="全选</th>";
            $("#dynamicBox").append(content);
        }

		$.post(url,params,function(result){

			$.each(result.list,function(n,value)
			{
				var userName=$("#usernameTemp").val();
				if(userName!="")
				{
					var userNameList=userName.split(",");
					if($.inArray(value.username,userNameList)!=-1)
					{
						var content = "<tr><td>";
	           			content += "<label><input type='checkbox' checked='checked'  id='userIds' name='userIds' data-value='"+value.username+"' value='"+value.id+"' onclick=javascript:window.isChecked(this,'"+currPage+"') >"+value.username+"</label>";
	           			content +="</td></tr>";
						$("#dynamicBox").append(content);
					}
					else
					{
						var content = "<tr><td>";
		           		content += "<label><input type='checkbox'   id='userIds' name='userIds' data-value='"+value.username+"' value='"+value.id+"' onclick=javascript:window.isChecked(this,'"+currPage+"') >"+value.username+"</label>";
		           		content +="</td></tr>";
						$("#dynamicBox").append(content);
					}
				}
				else
				{
					var content = "<tr><td>";
		           	content += "<label><input type='checkbox'   id='userIds' name='userIds' data-value='"+value.username+"' value='"+value.id+"' onclick=javascript:window.isChecked(this,'"+currPage+"') >"+value.username+"</label>";
		           	content +="</td></tr>";
					$("#dynamicBox").append(content);
				}
				
			
				
			});
			if(result.currPage!=1)
			{
				page=result.currPage-1;
				var content="<a onclick=chooseUser('"+page+"','"+obj+"')>上一页</a>";
				$("#dynamicBox").append(content);
			}
					
									
			for (var i=1;i<=result.totalPage;i++)
			{
				var content="<a onclick=chooseUser('"+i+"','"+obj+"')>"+i+"</a>";
				$("#dynamicBox").append(content);
				$("#dynamicBox").append("   ");
			}
			if(result.currPage!=result.totalPage)
			{
				page=result.currPage+1;
				var content="<a onclick=chooseUser('"+page+"','"+obj+"')>下一页</a>";
			    $("#dynamicBox").append(content);     
			}
		},"json");


	}
	//选中用户之后添加到隐藏域中
	function isChecked(obj,currPage)
	{
		if($(obj).is(":checked"))
		{
			var tmp=$("#userIdTemp").val();
			var tmpUserName=$("#usernameTemp").val();
			if(tmpUserName!="")
			{

				$("#usernameTemp").val(tmpUserName+","+$(obj).attr('data-value'));
			}
			else
			{
				$("#usernameTemp").val($(obj).attr('data-value'));
			}
			if(tmp!="")
			{
				$("#userIdTemp").val(tmp+","+$(obj).val());
			}
			else
			{
				$("#userIdTemp").val($(obj).val());
			}
		}
		else
		{
			var tmpUserName=$("#usernameTemp").val();
			var tmp=$("#userIdTemp").val();
			var tmplist=tmp.split(",");
			var tmpUserNameList=tmpUserName.split(",");
			if($.inArray($(obj).attr('data-value'),tmpUserNameList)!=-1)
			{
				if(tmpUserNameList.length==1)
				{
					var tmpUserName=tmpUserName.replace($(obj).attr('data-value'),"");
				}

				if($.inArray($(obj).attr('data-value'),tmpUserNameList)==0)
                {
                    var tmpUserName=tmpUserName.replace($(obj).attr('data-value')+",","");

                }
				var tmpUserName=tmpUserName.replace(","+$(obj).attr('data-value'),"");
			}
			if($.inArray($(obj).val() ,tmplist)!=-1)
			{
				if(tmplist.length==1)
				{
					var tmp=tmp.replace($(obj).val(),"");
				}
				if($.inArray($(obj).val() ,tmplist)==0)
                {
                    var tmp=tmp.replace($(obj).val()+",","");
                }
				var tmp=tmp.replace(","+$(obj).val(),"");
			}
            $("#userIdTemp").val(tmp);
            $("#usernameTemp").val(tmpUserName);
		}
        chooseUser(currPage);
	}
	
	//全选,将复选框全部选中，并将所有选中的值加入隐藏域中
	function selectAll(obj,currPage)
	{
		var arr=document.getElementsByName("userIds");
		
		//遍历数组 修改选中状态
		for(var i=0;i<arr.length;i++)
		{
			arr[i].checked=obj.checked;
		}
		isCheckedAll(arr,obj.checked,currPage)
	}

	function isCheckedAll(arr,obj,currPage)
    {
        for (var i = 0; i < arr.length; i++)
        {
            if ($(arr[i]).is(":checked")) {
                var tmp = $("#userIdTemp").val();
                var tmpUserName = $("#usernameTemp").val();
                if (tmpUserName != "") {
                    $("#usernameTemp").val(tmpUserName + "," + $(arr[i]).attr('data-value'));
                }
                else {
                    $("#usernameTemp").val($(arr[i]).attr('data-value'));
                }
                if (tmp != "") {
                    $("#userIdTemp").val(tmp + "," + $(arr[i]).val());
                }
                else {
                    $("#userIdTemp").val($(arr[i]).val());
                }
            }
            else {
                var tmpUserName = $("#usernameTemp").val();
                var tmp = $("#userIdTemp").val();
                var tmplist = tmp.split(",");
                var tmpUserNameList = tmpUserName.split(",");
                if ($.inArray($(arr[i]).attr('data-value'), tmpUserNameList) != -1) {
                    if (tmpUserNameList.length == 1) {
                        var tmpUserName = tmpUserName.replace($(arr[i]).attr('data-value'), "");
                    }

                    if ($.inArray($(arr[i]).attr('data-value'), tmpUserNameList) == 0) {
                        var tmpUserName = tmpUserName.replace($(arr[i]).attr('data-value') + ",", "");

                    }
                    var tmpUserName = tmpUserName.replace("," + $(arr[i]).attr('data-value'), "");
                }
                if ($.inArray($(arr[i]).val(), tmplist) != -1) {
                    if (tmplist.length == 1) {
                        var tmp = tmp.replace($(arr[i]).val(), "");
                    }
                    if ($.inArray($(arr[i]).val(), tmplist) == 0) {
                        var tmp = tmp.replace($(arr[i]).val() + ",", "");
                    }
                    var tmp = tmp.replace("," + $(arr[i]).val(), "");
                }
                $("#userIdTemp").val(tmp);
                $("#usernameTemp").val(tmpUserName);
            }
        }
        chooseUser(currPage,obj);
    }
	
	//将选择的用户加入输入框中，并将模态框隐藏
	function addUsersToInput()
	{
		$("#userName").val($("#usernameTemp").val());
		$("#myModal").modal('hide');
	}
</script>
</html>

