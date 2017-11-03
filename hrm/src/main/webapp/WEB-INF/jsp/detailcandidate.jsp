<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>人力资源管理系统</title>
<link href="${pageContext.request.contextPath}/css/style.css"
	rel="stylesheet" type="text/css" />
	
<style type=text/css>
*{
margin: 0;
padding: 0;
border: none;
font-size: 12px;
}
#jianil{
width: 797px;
margin: 0 auto;
border: solid 1px #DCDDDF;
}
#jianil .one{
width: 797px;
height: 1px;
font-size: 30px;
color: white;
font-weight: bold;
text-align: center;
margin-bottom: 0;
line-height: 90px;
}
#jianil ul{
width: 771px;
margin-left: 13px;
margin-top: 40px;
}
#jianil ul li{
font-size: 20.5px;
list-style: none;
text-indent:1.8em;
line-height: 40px;
margin-bottom: 20px;
border-bottom: 1px solid #DCDDDF;
}
#jianil ul li.none1{
border-bottom: none;
}
#jianil ul li p{
font-size: 15px;
}
</style>
</head>
<body>
	<div id=jianil>
		<div class=one>个人简历</div>
		<ul>
			<li>基本信息
			    <p>
			           应聘职位：${talent.job}
			    </p>
				<p>
				姓名：${talent.name}
				性别：
				<c:choose>
				    <c:when test="${talent.sex == 1}">
				        <c:out value="男"></c:out>
				    </c:when>
				    <c:otherwise>
				        <c:out value="女"></c:out>
				    </c:otherwise>
				</c:choose>
				生日:<fmt:formatDate value="${talent.birthday}" type="date" pattern="yyyy-MM-dd"/>
				籍贯：${talent.nativePlace}
				</p>
				<p>
				学历：${talent.edu}
				手机：${talent.phone}
				Email：${talent.email}
				</p>
			</li>
			<li>教育经历
				<p>
				    ${talent.education}
				</p>
			</li>
			<li>工作经历
				<p>
				    ${talent.work}
				</p>
			</li> 
			<li>相关证书
			    <p>
				    ${talent.certificate}
				</p>
			</li>
		</ul>
	</div>
	<div class="header">
		<ul align="center">
			<li><a href="#" onclick="javascript:history.back();">返回</a></li>
			<li><a>下载word</a></li>
		</ul>
	</div>
</body>
<<%-- div class="MainDiv">
		<table style="width: 99%; height: 500px;border: 0; cellpadding:0; cellspacing:0 "  class="CContent">
			<tr>
				<th class="tablestyle_title">详细信息</th>
			</tr>

			<tr>
				<td class="CPanel">
					<table border="0" cellpadding="0" cellspacing="0"
						style="width: 80%" align="center">
						<tr>
							<td width="100%"><fieldset style="height: 100%;">
									<table border="0" cellpadding="8" cellspacing="1"
										style="width: 100%; height: 300px">
										<tr>
											<td align="right" width="9%">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</td>
											<td width="36%">${talent.name}</td>
											<td width="20%">
												<div align="right">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</div>
											</td>
											<td width="43%">
											    <c:choose>
                                                    <c:when test="${talents.sex==1}">
                                                        <c:out value="男"></c:out>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:out value="女" ></c:out>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
										</tr>
										<tr>
											<td align="right" width="9%">生&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日：</td>
											<td><fmt:formatDate value="${talents.birthday}" type="date" pattern="yyyy-MM-dd"/></td>
											<td>
												<div align="right">应聘职位：</div>
											</td>
											<td>${talent.job}</td>
										</tr>
										<tr>
											<td>
												<div align="right">毕业学校：</div>
											</td>
											<td>${talent.school}</td>
											<td align="right">学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;历：</td>
											<td>${talent.edu}</td>
										</tr>
										<tr>
											<td align="right">学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;历：</td>
											<td>${talent.education}</td>
											<td>
												<div align="right">工作经验：</div>
											</td>
											<td>${talent.work}</td>
										</tr>
										<tr>
											<td align="right">联系电话：</td>
											<td>${talent.phone}</td>
											<td>
												<div align="right">Email：</div>
											</td>
											<td>${talent.email}</td>
										</tr>
										<tr>
											<td colspan="4" align="center"><a href="#"
												onclick="javascript:history.back();">返回</a></td>
										</tr>
									</table>
						</fieldset>
						</td>
						</tr>
					</table>
				</td>
			</tr>
			</table>
	</div>
	<script type="text/javascript" src="Js/typem.js"></script>
	<script type="text/javascript" src="Js/js.js"></script> --%>
</body>
</html>

