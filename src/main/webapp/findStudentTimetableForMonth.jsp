<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<c:url value="/resources/style.css" />" rel="stylesheet">
<title>StudentForMonth</title>
</head>


<body>

	<div id="wrapper">
		<div id="header">
			<h2>University</h2>
		</div>
	</div>

	<div id="container">

		<div id="content">

			<h2>Find Student Timetable for Month</h2>




			<form action="displayStudentTimetableForMonth" method="post">
				<table>

					<tr>
						<th>Student</th>
						<th>Date</th>
						<th></th>

					</tr>




					<tr>
						<td><select name="studentId" class="button">
								<c:forEach var="student" items="${students}">
									<option value="${student.id}">${student.name}</option>
								</c:forEach>
						</select></td>
						<td><input type="text" name="date" value="2017/03" /></td>

						<td><input type="submit" value="FIND" class="button" /></td>
					</tr>

				</table>
			</form>




			<h2>Student : ${student.name }</h2>


			<table>
				<c:forEach var="lesson" items="${lessons}">

					<tr>
						<th>Number</th>
						<th>Subject</th>
						<th>Group</th>
						<th>Teacher</th>
						<th>Room</th>
						<th>Date</th>

					</tr>




					<tr>
						<td>${lesson.number }</td>
						<td>${lesson.subject.name }</td>
						<td>${lesson.group.name }</td>
						<td>${lesson.teacher.name }</td>
						<td>${lesson.room.number }</td>
						<td><fmt:formatDate pattern="dd/MM HH:mm"
								value="${lesson.date }" /></td>
					</tr>
				</c:forEach>
			</table>



		</div>

	</div>
	<p>
		<a href="index.jsp">Back to University</a>
	</p>

</body>


</html>