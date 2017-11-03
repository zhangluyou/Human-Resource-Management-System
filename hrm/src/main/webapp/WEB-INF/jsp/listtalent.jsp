<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>人力资源管理系统</title>
<style type="text/css">
</style>
<link href="${pageContext.request.contextPath}/css/css.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/style.css"
	rel="stylesheet" type="text/css" />	
<link rel="stylesheet"
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">

<body>
	<input type="hidden" value="${sort}" id="dsort">
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
						    <form action="/hrm/talent/getlist" method="get" id="candidateForm">
						    <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
						        <tr>
									<td height="20"><span class="newfont07">人才库浏览</span></td>
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
												<td height="22" colspan="8" align="center" style="font-size: 16px">人才库列表</td>
											</tr>
											<tr bgcolor="#EEEEEE">
												<td width="7%" align="center">编号</td>
												<td width="15%" align="center" height="30">姓名</td>
												<td width="7%" align="center">性别</td>
												<td width="20%" align="center">应聘职位</td>
												<td width="20%" align="center">应聘时间</td>
												<td width="26%" align="center">执行操作</td>
											</tr>
											<c:forEach items="${page.t}" var="talents">
										    <tr bgcolor="#FFFFFF" id="tb_data">
											    <td height="10" align="center">${talents.id}</td>
											    <td height="20" align="center">${talents.name}</td>
												<td height="20" align="center">
												<c:choose>
                                                    <c:when test="${talents.sex==1}">
                                                        <c:out value="男"></c:out>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:out value="女" ></c:out>
                                                    </c:otherwise>
                                                </c:choose>
												</td>
												<td height="20" align="center">${talents.job}</td>
												<td height="20" align="center"><fmt:formatDate value="${talents.interviewTime}" type="date" pattern="yyyy-MM-dd"/></td>
												<td height="30" align="center">
												    <a href="/hrm/talent/getdetails/${talents.id}">详情</a>&nbsp;&nbsp;
												    <a href="#" onclick="employment(${talents.id});">录用</a>&nbsp;&nbsp;
												    <a href="#" onclick="del(${talents.id})">删除</a>
												</td>
											</tr>
											</c:forEach>
											<!-- <tr bgcolor="#FFFFFF">
												<td height="22" colspan="7" align="center">对不起，没有信息了！！！</td>
											</tr> -->
										</table>
								    </td>  
								</tr>
							</table>
							<input type="hidden" id="pageNum" name="pageNum" value=1 ></input>
							<div style="width:380px;margin:0 auto;margin-top:10px;">
			                    <ul class="pagination" style="text-align:center; margin-top:10px;">	
		                        
			                    <c:if test="${page.pageNum==1}">
					                <li class="disabled">
					                    <!-- void(0)表示不执行动作，实际上就是不跳转的意思 -->
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

function employment(id) {
	if(confirm('确定录用吗？')){
		window.location.href="/hrm/talent/employment.do?id="+id;
    }
}

function del(id) {
	if(confirm('确定删除吗？')){
		window.location.href="/hrm/talent/deltalent.do"+id;
    }
}


</script> 							
</body>
</html>

