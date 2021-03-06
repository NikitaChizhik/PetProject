<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<c:url value="/resources/style.css" />" rel="stylesheet">
<title>allLessons</title>
</head>


<body>

	<div id="wrapper">
		<div id="header">
			<h2>University</h2>
		</div>
	</div>

	<div id="container">

		<div id="content">



			<form action="lesson/create" method="post">


				<table>

					<tr>
						<th>Number</th>
						<th>Subject</th>
						<th>Group</th>

					</tr>
					<tr>

						<td>
							<select name="number" class="button">

								<c:forEach var="number" items="${numbers}">
									<option value="${number }">${number}</option>
								</c:forEach>

							</select>
						</td>


						<!-- <td>  <select name="number" class="button">
								<c:forEach var="number" items="${numbers}">
									<option value="${number}">${number}</option>
								</c:forEach>
								
						</select></td> -->


						<td>
							<select name="subject.id" class="button">

								<c:forEach var="subject" items="${subjects}">
									<option value="${subject.id }">${subject.name}</option>
								</c:forEach>

							</select>
						</td>


						<!-- <td><select name="subjectId" class="button">
								<c:forEach var="subject" items="${subjects}">
									<option value="${subject.id }">${subject.name}</option>
								</c:forEach>
						</select></td> -->



						<td>
							<select name="group.id" class="button">

								<c:forEach var="group" items="${groups}">
									<option value="${group.id }">${group.name}</option>
								</c:forEach>

							</select>
						</td>

						<!-- <td><select name="groupId" class="button">
								<c:forEach var="group" items="${groups}">
									<option value="${group.id }">${group.name}</option>
								</c:forEach>
						</select></td> -->



					</tr>
				</table>

				<table>

					<tr>
						<th>Teacher</th>
						<th>Room</th>
						<th>Date</th>
						<th>Add Lesson</th>

					</tr>
					<tr>


						<td>
							<select name="teacher.id" class="button">

								<c:forEach var="teacher" items="${teachers}">
									<option value="${teacher.id }">${teacher.name}</option>
								</c:forEach>

							</select>
						</td>

						<!-- <td><select name="teacherId" class="button">
								<c:forEach var="teacher" items="${teachers}">
									<option value="${teacher.id }">${teacher.name}</option>
								</c:forEach>
						</select></td> -->

						<td>
							<select name="room.id" class="button">

								<c:forEach var="room" items="${rooms}">
									<option value="${room.id }">${room.number}</option>
								</c:forEach>

							</select>
						</td>

						<!-- <td><select name="roomId" class="button">
								<c:forEach var="room" items="${rooms}">
									<option value="${room.id }">${room.number}</option>
								</c:forEach>
						</select></td> -->


						<td>
							<input name="date" value="2017/03/30 12:30:00" class="button" />
						</td>

						<!-- <td><input type="text" name="date"
							value="2017/03/30 12:30:00" /></td> -->


						<td>
							<input type="submit" value="Add Lesson" class="button" />
						</td>

					</tr>
				</table>

			</form>



			<table>

				<tr>
					<th>Group</th>
					<th>Subject</th>
					<th>Date</th>
					<th>Number</th>
					<th>Teacher</th>
					<th>Delete</th>

				</tr>

				<c:forEach var="lesson" items="${lessons}">

					<c:url var="lessonLink" value="lesson">
						<c:param name="lessonId" value="${lesson.id}" />
					</c:url>



					<tr>
						<td>${lesson.group.name }</td>
						<td>
							<a href="lesson/${lesson.id}">${lesson.subject.name}</a>
						</td>



						<td>
							<fmt:formatDate pattern="dd/MM HH:mm" value="${lesson.date }" />
						</td>

						<td>${lesson.number }</td>
						<td>${lesson.teacher.name }</td>

						<td>
							<form action="lesson/delete/${lesson.id} " method="post">

								<input type="submit" value="Delete" class="button"
									onclick="if (!(confirm('Are you sure you want to delete this lesson?'))) return false" />
							</form>
						</td>

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