<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>人力资源管理系统</title>
<style type="text/css">
.send_msg {
   background-color: #679584;
   display: block;
   width: 30%;
   height: 40%;
   position: fixed;
   top: 25%;
   left: 35%;
   z-index:9999;
   border: solid 1px #679584;
}
.send_msg caption {
    padding-top: 10px;
    padding-bottom: 10px;
    background: #679584;
    font-size: 16px;
    color: #fff;
}

#cover {
    position: fixed;
    z-index: 999;
    top: 0px;
    left: 0px;
    display: none;
    width: 100%;
    height: 100%;
    opacity:0.5;
    background: #000 none repeat 0% 0%; 
}
</style>
<link href="${pageContext.request.contextPath}/css/css.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/style.css"
	rel="stylesheet" type="text/css" />	
<link rel="stylesheet"
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" />
</head>
<body>
	<input type="hidden" value="${sort}" id="dsort" />
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
			    <table id="subtree1" style="DISPLAY:" width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
						    <form action="/hrm/candidate/getlist" method="post" id="candidateForm">
						    <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
						        <tr>
									<td height="20"><span class="newfont07">人才信息查看</span></td>
								</tr>
								<tr>
									<td height="40" style="position: relative; left: 480px;">
										<span> 
										    <input id="inputTxt" name="job" type="text" value="${job}" placeholder="请输入职位"></input> 
										    <input id="search" type="submit" value="查询"></input>
										</span>
									</td>
								</tr>
								<tr>
									<td height="40" style="position: relative; left: 825px;">
									   <select id="sort" name="sort">
										  <option value ="asc">面试时间升序</option>
										  <option value ="desc">面试时间降序</option>
										</select>
									</td>
								</tr>
								<tr>
									<td height="40" class="font42">
										<table width="100%" border="1" cellpadding="4" cellspacing="1" bgcolor="#464646" class="newfont03">
											<tr class="CTitle">
												<td height="22" colspan="8" align="center" style="font-size: 16px">求职者信息列表</td>
											</tr>
											<tr bgcolor="#EEEEEE">
												<td width="7%" align="center">编号</td>
												<td width="15%" align="center" height="30">姓名</td>
												<td width="7%" align="center">性别</td>
												<td width="20%" align="center">应聘职位</td>
												<td width="20%" align="center">应聘时间</td>
												<td width="26%" align="center">执行操作</td>
											</tr>
											<c:forEach items="${page.t}" var="candidate">
										    <tr bgcolor="#FFFFFF" id="tb_data">
											    <td height="5" align="center">${candidate.id}</td>
											    <td height="20" align="center">${candidate.name}</td>
												<td height="5" align="center">
												<c:choose>
                                                    <c:when test="${candidate.sex==1}">
                                                        <c:out value="男"></c:out>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:out value="女" ></c:out>
                                                    </c:otherwise>
                                                </c:choose>
												</td>
												<td height="20" align="center">${candidate.job}</td>
												<td height="20" align="center"><fmt:formatDate value="${candidate.interviewTime}" type="date" pattern="yyyy-MM-dd"/></td>
												<td height="40" align="center">
												    <a href="/hrm/candidate/getcandidate.do/${candidate.id}">详情</a>&nbsp;&nbsp;
												    <a href="#" onclick="sendMsg();">邀请面试</a>&nbsp;&nbsp; 
													<a href="/hrm/candidate/stock.do/${candidate.id}">入库</a>&nbsp;&nbsp;
													<a href="#" onclick="del(${candidate.id});">删除</a>
												</td>
											</tr>
											</c:forEach>
										</table>
										<div id="send_msg"  class="send_msg">
										    <table width="100%" border="0">
										        <caption>发送消息</caption>
										        <tr>
										            <td align="right" width="80%" height="30">收件人：</td>
										            <td>
										                <input type="text" name="mobile" id="mobile" size="30"/>
										            </td>
										        </tr>
										        <tr>
										            <td>消息内容：</td>
										            <td><textarea rows="3" cols="30" id="inputText"></textarea></td>
										        </tr>
										        <tr>
										            <td colspan="2" align="center">
										                 <input id="msg_button" type="button" class="msg_button" value="发送" onclick="send(${candidate.phone});"/> 
					                                     <input type="reset" class="button" value="重置" />
										            </td>
										        </tr>
										    </table>
										</div>
										<div style="display: block;" id="cover"></div>
								    </td>  
								</tr>
							</table>
							<input type="hidden" id="pageNum" name="pageNum" value=1 />
							<div style="width:380px;margin:0 auto;margin-top:10px;">
			                    <ul class="pagination" style="text-align:center; margin-top:10px;">	
		                        
			                    <c:if test="${page.pageNum==1}">
					                <li class="disabled">
					                    <a href="javascript:void(0)" aria-label="Previous">
					                        <span aria-hidden="true">&laquo;</span>
					                    </a>
					                </li>
				                </c:if>
				                <c:if test="${page.pageNum!=1}">
					                <li>
					                    <a href="javascript:goPage(${page.pageNum-1});document:candidateForm.submit();" aria-label="Previous">
				    	                    <span aria-hidden="true">&laquo;</span>
				    	                </a>
					                </li>
				                </c:if>
		                       
		                        <!-- 展示所有页面 -->
				                <c:forEach begin="${page.pageNum-5>0?page.pageNum-5:1 }" end="${page.pageNum+4>page.totalPageNum?page.totalPageNum:page.pageNum+4}" var="n">
				                <!-- 判断是否是当前页 -->
					            <c:if test="${page.pageNum==n}"> 
					                <li class="active">
					                    <a href="javascript:void(0)">${n}</a>
					                </li>
					            </c:if>
					            <c:if test="${page.pageNum!=n}">
					                <li>
					                    <a href="javascript:goPage(${n});document:candidateForm.submit();">${n}</a>
					                </li>
					            </c:if> 
				                </c:forEach>		

				                <c:if test="${page.pageNum==page.totalPageNum}">
				                    <li class="disabled">
					                     <a href="javascript:void(0)" aria-label="Next">
						                     <span aria-hidden="true">&raquo;</span>
					                     </a>
				                    </li>
				                </c:if>
				                <c:if test="${page.pageNum!=page.totalPageNum}">
				                    <li>
					                    <a href="javascript:goPage(${page.pageNum+1});document:candidateForm.submit();" aria-label="Next">
						                    <span aria-hidden="true">&raquo;</span>
					                    </a>
				                    </li>
				                </c:if> 
			                    </ul>
		                    </div>
		                    
							</form>		
						</td>
					</tr>
		        </table>
		    </td>
        </tr>			
    </table>
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<script
		src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript">
$(function(){	
	var dsort=$("#dsort").val();
	$("#sort").val(dsort);
	$("#sort").change(function(){
	     $('#candidateForm').submit();
	});
})

function goPage(page) {
	$("#pageNum").val(page);
}

function del(id) {
	if(confirm('确定要删除该行信息?')){
		window.location.href="/hrm/candidate/delete.do/"+id;
    }
}

$(document).ready(function(){
	$('#send_msg').hide();//隐藏
	$('#cover').hide();
	$('#cover').click(function () {
		$('#send_msg').hide();
		$(this).hide();
	})
});
function sendMsg() {
	$('#send_msg').show();
	$('#cover').show();
}

function send() {
	var mobile = $('#mobile').val();
	var content = $('#inputText').val();
    $.ajax({
        type : 'POST',
        url : 'http://172.31.60.189/base/common/sms/sendCommonSms',
        contentType: "application/json; charset=utf-8",
        date : {
        	"mobile":mobile,
        	"content": content
        },
        dataType : 'json',
        success : function() {
        
        }
    });
}
</script> 							
</body>
</html>

