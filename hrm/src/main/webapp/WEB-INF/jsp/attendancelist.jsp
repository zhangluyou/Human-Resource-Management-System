<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>人力资源管理系统</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-select.css">

	<script src="${pageContext.request.contextPath}/Js/jquery-3.2.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/Js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/Js/bootstrap-select.js"></script>
</head>
<body>
<div class="col-md-10">
	<h2>考勤列表</h2>
	<hr>
	<form class="form-inline" action="${pageContext.request.contextPath}/attendance/attendanceList" method="post">
		<div class="form-group">
			<select id="lunch" class="selectpicker" data-live-search="true" title=${searchMonth} name="searchMonth">
				<c:forEach items="${monthList}" var="month">
					<option value="${month }">${month}</option>
				</c:forEach>
			</select>
		</div>
		<span class="">
				<button type="submit" class="btn btn-primary">查询</button>
		</span>
	</form>
	<hr>
	<label>注：正常出勤：√  休息：×  事假：S  病假：B  婚假：H   陪护假：P   调休：T   离职：L  丧假：丧  年假：N  旷工：K 其他：□（请备注)</label>
	<hr>
		<table class="table table-hover">
			<tr>
				<th rowspan="2">部门</th>
				<th rowspan="2">姓名</th>
				<c:forEach items="${DayAndStatus}" var="day" varStatus="status">
					<th >${day.day}</th>
				</c:forEach>
				<th rowspan="2">应出勤/天</th>
				<th rowspan="2">实出勤/天</th>
				<th rowspan="2">迟到/分钟</th>
				<th rowspan="2">出勤率</th>
			</tr>
			<tr>
				<c:forEach items="${DayAndStatus}" var="day" varStatus="status">
					<th >${day.weekday}</th>
				</c:forEach>
			</tr>
			<c:forEach items="${attendenceList}" var="attendance" varStatus="status">
				<tr>
					<td>${attendance.department}</td>
					<td>${attendance.name}</td>
					<c:forEach items="${attendance.list}" var="dayStatus" varStatus="status">
						<c:choose>
							<c:when test="${dayStatus.status=='0'}">
								<td><img src="${pageContext.request.contextPath}/images/no.png" alt=""></td>
							</c:when>
							<c:when test="${dayStatus.status=='1'}">
								<td><img src="${pageContext.request.contextPath}/images/yes.png" alt=""></td>
							</c:when>

							<c:otherwise>
								<td>${dayStatus.status}</td>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<td>${attendance.should_attendance}</td>
					<td>${attendance.real_attendance}</td>
					<td>${attendance.late_time}</td>
					<td>${attendance.attendance_rate*100}%</td>
				</tr>
			</c:forEach>
		</table>
</div>
</body>
</html>

