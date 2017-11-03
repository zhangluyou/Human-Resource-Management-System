<%@page import="javax.swing.text.Document"%>
<%@page import="java.lang.*"%>
<%@ page import="org.aspectj.weaver.ast.Var"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%-- <%@ page import="com.sanqing.po.Educate"%> --%>
<%@ page import="com.jointem.hrm.entity.*"%>
<%-- <%@ page import="com.sanqing.tool.*"%> --%>
<%@ page import="java.util.Date"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<script src="${pageContext.request.contextPath}/Js/jquery-3.2.1.min.js"></script>
<script
	src="${pageContext.request.contextPath}/Js/bootstrap.min.js"></script>
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
.font051 {font-family: "宋体";
	font-size: 12px;
	color: #333333;
	text-decoration: none;
	line-height: 20px;
}
.font201 {font-family: "宋体";
	font-size: 12px;
	color: #FF0000;
	text-decoration: none;
}
.button {
	font-family: "宋体";
	font-size: 14px;
	height: 37px;
}
html { overflow-x: auto; overflow-y: auto; border:0;} 
-->
</style>

<link href="${pageContext.request.contextPath}/css/css.css" rel="stylesheet" type="text/css" />
<script type="text/JavaScript">

</script>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  
  <tr>
    <td height="30">      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td height="62" background="${pageContext.request.contextPath}/images/nav04.gif">&nbsp;</td>
        </tr>
    </table></td></tr>
    <tr><th class="tablestyle_title">培训记录</th></tr>
  <tr>
    <td><table id="subtree1" style="DISPLAY: " width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td><table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
          	 <tr>
               <td height="20"><span class="newfont07">培训记录查看</span></td>
          	 </tr>
              <tr>
                <td height="40" class="font42">
				<table width="100%" border="1" cellpadding="4" cellspacing="1" bgcolor="#464646" class="newfont03">
				 <tr class="CTitle" >
                    	<td height="22" colspan="8" align="center" style="font-size:16px">
                    	
     			列表</td>
                  </tr>
                  <tr>
						<select id="dropSelect" class="selectpicker">
						<option <c:if test="${dropSelectValue eq'已完成'}">selected</c:if> value="已完成">已完成</option>
						<option <c:if test="${dropSelectValue eq'待参加'}">selected</c:if> value="未完成">待参加</option>
						<option <c:if test="${dropSelectValue eq'待确认'}">selected</c:if> value="未完成">待确认</option>
						</select>
					 <form name ="listForm" method="post" action="studentEducateRecordView"  onsubmit="return setselectDropValue();">
                  	 培训名称：<input type="text" name="ename" class="input" value="${ename}"/>
                  	  <input type="hidden" id="pageNum" name="pageNum" value=1 >
                  	  <input type="hidden" id="dropSelectValue" name="dropSelectValue" value="${dropSelectValue}">
                  	 <input type="submit" value="确定"/>
                  </form>
                  </tr>
                  <tr bgcolor="#EEEEEE">
				        <td height="22" align="center" >培训名称</td>
						<td height="22" align="center" >讲师</td>
						<td height="22" align="center" >开始时间</td>
						<td height="22" align="center" >执行操作</td>
                  </tr>
                   <c:forEach items="${pages.t}" var="t1" varStatus="status">
                   				<c:forEach items="${t1.educates}" var="t" varStatus="status">
								<tr bgcolor="#FFFFFF">
									<td height="22" align="center" >${t.name}</td>
									<td height="22" align="center" >${t.teacher}</td>
									<td height="22" align="center" ><fmt:formatDate value="${t.begintime}" pattern="yyyy-MM-dd"/></td>
									<td height="22" align="center" >
																		<c:if test="${dropSelectValue eq '待确认'}">
										&nbsp;&nbsp;<a data-toggle="modal" data-target="#confirm-change" onclick="activeUpdateButton(${t.id})">确认参加</a>
																		</c:if>
																		<c:if test="${dropSelectValue eq '待参加'}">
										&nbsp;&nbsp;<a data-toggle="modal" data-target="#confirm-cancel" onclick="activeaCancelButton(${t.id})">取消参加</a>
																		</c:if>
									</td>
								</tr>
								</c:forEach>
				</c:forEach>
            </table></td>
        </tr>
      </table>
          </td>
        </tr>
</table>
<!--分页 -->
		<div style="width:380px;margin:0 auto;margin-top:10px;">
			<ul class="pagination" style="text-align:center; margin-top:10px;">
			
			
		<!-- 判断当前页是否是首页 -->
			    <c:if test="${pages.pageNum==1}">
					<li class="disabled">
					     <a href="javascript:void(0)" aria-label="Previous">
				    	 <span aria-hidden="true">&laquo;</span></a>
					</li>
				</c:if>
				
				<c:if test="${pages.pageNum!=1}">
					<li>
					     <a href="javascript:setPageNum(${pages.pageNum-1});document:listForm.submit();" aria-label="Previous">
				    	 <span aria-hidden="true">&laquo;</span></a>
					</li> 
				</c:if>
		<!-- 判断当前页是否是首页 -->
				
				
		<!-- 展示所有页面 -->
				<c:forEach begin="${pages.pageNum-5>0?pages.pageNum-5:1 }" end="${pages.pageNum+4>pages.totalPageNum?pages.totalPageNum:pages.pageNum+4}" var="n">
				<!-- 判断是否是当前页 -->
					<c:if test="${pages.pageNum==n}"> 
					  <li class="active"><a href="javascript:void(0)">${n}</a></li>
					</c:if>
					<c:if test="${pages.pageNum!=n }">
					  <li><a href="javascript:setPageNum(${n});document:listForm.submit();">${n}</a></li>
					</c:if> 
				</c:forEach>		
		<!-- 判断是否是最后一页-->
				<c:if test="${pages.pageNum==pages.totalPageNum}">
				<li class="disabled">
					<a href="javascript:void(0)" aria-label="Next">
						<span aria-hidden="true">&raquo;</span>
					</a>
				</li>
				</c:if>
				<c:if test="${pages.pageNum!=pages.totalPageNum}">
				<li>
					<a href="javascript:setPageNum(${pages.pageNum+1});document:listForm.submit();" aria-label="Next">
						<span aria-hidden="true">&raquo;</span>
					</a>
				</li>
				</c:if>
		<!-- 判断是否是最后一页-->
			</ul>
		</div>
		<!-- 分页结束======================-->


<div class="modal fade" id="confirm-change" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content" style="width: 280px">
            <div class="modal-header">
              <p align="center">是否修改状态</p> 
            </div>
            <form action="update.do" method="post" id="updatePlanFromId">
                <input type="hidden" name="sid" id="planId1">
                <div class="modal-body">
                    	更改为参加？
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <a class="btn btn-danger btn-ok" onclick="document:updatePlanFromId.submit();">更改</a>
                </div>
            </form>
        </div>
    </div>
</div>

<div class="modal fade" id="confirm-cancel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content" style="width: 280px">
            <div class="modal-header">
              <p align="center">是否修改状态</p> 
            </div>
            <form action="cancel.do" method="post" id="cancelPlanFromId">
                <input type="hidden" name="sid1" id="planId2">
                <div class="modal-body">
                    	更改为不参加？
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <a class="btn btn-danger btn-ok" onclick="document:cancelPlanFromId.submit();">更改</a>
                </div>
            </form>
        </div>
    </div>
</div>

<div class="modal fade" id="confirm-delete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content" style="width: 280px">
            <div class="modal-header">
              <p align="center">是否删除</p> 
            </div>
            <form action="deletEducateRecord.do" method="post" id="deletePlanFromId">
                <input type="hidden" name="sid" id="planId">
                <div class="modal-body">
                    	确定要删除这个计划吗？
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <a class="btn btn-danger btn-ok" onclick="document:deletePlanFromId.submit();">删除</a>
                </div>
            </form>
        </div>
    </div>
</div>

<script language="JavaScript">
//弹出删除确认按钮传id
function activeDeleteButton(planid)
{
		document.getElementById("planId").value=planid;
}
function activeUpdateButton(planid)
{
		document.getElementById("planId1").value=planid;
}
function activeaCancelButton(planid)
{
		document.getElementById("planId2").value=planid;
}
function setselectDropValue(){
	var s;
s=document.getElementById("dropSelect"); 
 $("#dropSelectValue").val(s.options[s.selectedIndex].text);	
}
function setPageNum(num){
	$("#pageNum").val(num);
}
</script>

</body>
</html>