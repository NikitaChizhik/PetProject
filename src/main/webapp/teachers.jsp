
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link href="<c:url value="/resources/style.css" />" rel="stylesheet">


<title>allTeachers</title>
</head>


<body>

	<div id="wrapper">
		<div id="header">
			<h2>University</h2>
		</div>
	</div>

	<div id="container">

		<div id="content">




			<form:form action="teacher/create" method="post"
				modelAttribute="teacher">

				<form:hidden path="id" />

				<table>

					<tr>
						<th>Subject</th>
						<th>Name</th>
						<th></th>
					</tr>
					<tr>

						<td><form:select path="subject.id">

								<c:forEach var="subject" items="${subjects}">
									<option value="${subject.id }">${subject.name}</option>
								</c:forEach>

							</form:select></td>



						<td><form:input path="name" /></td>
						<td><input type="submit" value="Add Teacher" class="button" /></td>

					</tr>
				</table>
			</form:form>



			<table>

				<tr>
					<th>Name</th>
					<th>Subject</th>
					<th>Delete</th>
				</tr>

				<c:forEach var="teacher" items="${teachers}">

					<c:url var="teacherLink" value="teacher">
						<c:param name="teacherId" value="${teacher.id}" />
					</c:url>



					<tr>
						<td><a href="teacher/${teacher.id}">${teacher.name}</a></td>
						<td>${teacher.subject.name}</td>

						<td><form action="teacher/delete/${teacher.id} "
								method="post">

								<input type="submit" value="Delete" class="button"
									onclick="if (!(confirm('Are you sure you want to delete this teacher?'))) return false" />
							</form></td>
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