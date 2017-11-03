<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="com.jointem.hrm.entity.Educate"%>
<%-- <%@ page import="com.sanqing.tool.*"%> --%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>人力资源管理系统</title>
<link rel="stylesheet" rev="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css "
	type="text/css" media="all" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Js/typem.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/Js/js.js"></script>
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
                    <c:choose>
							<c:when test="${empty educate}"><c:out value="暂时没有用户信息"></c:out></c:when>
							<c:otherwise> 
	<form name="educateForm" method="post"
		action="modifyeducate.do?action=addeducate"
		onsubmit="return educateValidate();">
		<div class="MainDiv">
			<table width="99%" border="0" cellpadding="0" cellspacing="0"
				class="CContent">
				<tr>
					<th class="tablestyle_title">
					<!-- flag 为"1“表示总结界面发来的请求，为"0"是查看培训计划页面，为”2“是计划录入界面 -->
					<c:choose> 
					
                              <c:when test="${flag=='1'}"><c:out value="培训详细总结"></c:out></c:when>  
                            <c:when test="${flag =='0'}"><c:out value="培训计划详细信息"></c:out></c:when>
                            <c:when test="${flag =='2'}"><c:out value="添加的计划信息"></c:out></c:when>
                            </c:choose>
							</th>
				</tr>
				<TR>
					<TD width="100%">
						<fieldset style="height: 100%;">
							<table border="0" cellpadding="8" cellspacing="1"
								style="width: 100%">
								<tr>
									<td nowrap align="right" >培训名称：</td>
									<td ><c:out value="${educate.name }"></c:out></td>
									<td >审核状态：</td>
									<td ><c:choose><c:when test="${educate.isreviewed==1}"><c:out value="已审核"></c:out></c:when>
									<c:when test="${educate.isreviewed==0}"><c:out value="未审核"></c:out></c:when><c:when test="${educate.isreviewed==3 }">
									<c:out value="审核未通过"></c:out><span class="red">&nbsp;(&nbsp;${educate.remark }&nbsp;)</span></c:when>
									<c:otherwise><c:out value="待审核"></c:out></c:otherwise>
									</c:choose></td>
								</tr>
								<tr>
									<td nowrap align="right" width="11%">培训目的：</td>
									<td colspan="3"><c:out value="${educate.purpose}"></c:out>
									</td>
								</tr>
								<tr>
									<td nowrap align="right">培训开始时间：</td>
									<td width="29%"><fmt:formatDate
											value="${educate.begintime }" pattern="yyyy-MM-dd" /> 
									</td>
									<td width="18%">培训结束时间：</td>
									<td width="42%"><fmt:formatDate
											value="${educate.endtime }" pattern="yyyy-MM-dd" /> 
									</td>
								</tr>
								<tr>
									<td nowrap align="right">讲师：</td>
									<td><c:out value="${educate.teacher }"></c:out></td>
									<td>培训人员：</td>
									<td>
									 <a href="listStudent.do?students=${educate.student }&flag=${flag} &id=${educate.id}&pageNum=${pageNum}">点击查看</a>
									</td>
								</tr>

								<tr>
									<td width="11%" nowrap align="right">培训材料：</td>
									<td colspan="3"><c:out value="${educate.datum }"></c:out></td>
								</tr>
								<tr>
									<td width="11%" height="22" align="center">培训效果：</td>
									<td height="22" colspan="3" align="left"><c:choose>
											<c:when test="${educate.effect }==null">
												<c:out value="还未填写！"></c:out>
											</c:when>
											<c:otherwise>
												<c:out value="${educate.effect }"></c:out>
											</c:otherwise>
										</c:choose> &nbsp;</td>
									
								</tr>
								<tr>
									<td width="11%" height="22" align="center">培训总结：</td>
									<td height="22" colspan="3" align="left">
									<c:choose>
											<c:when test="${educate.summarize }==null">
												<c:out value="还未填写！"></c:out>
											</c:when>
											<c:otherwise>
												<c:out value="${educate.summarize }"></c:out>
											</c:otherwise>
										</c:choose>
									</td>
								</tr>	
							</table>
						</fieldset>
					</TD>
				</TR>
				<tr>
				
					<td>
					<c:choose>
					<c:when test="${flag=='1' }"><a href="skimSummarizeView?pageNum=${pageNum }">
							</c:when>
					<c:when test="${flag=='0' }"><a href="listEducateView?pageNum=${pageNum}"> 
					</c:when>
					<c:when test="${flag=='2' }"><a href="educateView?pageNum=${pageNum }"> </c:when>
					<c:when test="${flag=='7' }"><a href="lookThroughView?pageNum=${pageNum }"></c:when>
			</c:choose>
					 <input type="button" value="返回"
							class="button" /></a>
							</td>
								
				</tr>
			</TABLE>
		</div>
	</form>
 </c:otherwise></c:choose>
	
</body>
</html>

