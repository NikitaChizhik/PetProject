<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>allGroups</title>
<link type="text/css" rel="stylesheet" href="css/style.css">
</head>


<body>

	<div id="wrapper">
		<div id="header">
			<h2>University</h2>
		</div>
	</div>

	<div id="container">

		<div id="content">

			<form action="groups" method="post">

				<input type="text" name="name" /> <input type="submit"
					value="Add Group" class="add-student-button" />
			</form>

			<table>

				<tr>
					<th>Name</th>
					<th>Size</th>
					<th>Delete</th>

				</tr>

				<c:forEach var="group" items="${groups}">

					<c:url var="groupLink" value="group">
						<c:param name="groupId" value="${group.id}" />
					</c:url>

					<c:url var="deleteLink" value="groupDelete">
						<c:param name="groupId" value="${group.id}" />
					</c:url>

					<tr>
						<td><a href="${groupLink}">${group.name}</a></td>
						<td>${fn:length(group.students)}</td>
						<td><a href="${deleteLink}"
							onclick="if (!(confirm('Are you sure you want to delete this group?'))) return false">
								Delete</a></td>
					</tr>

				</c:forEach>

			</table>

		</div>

	</div>
	<p>
		<a href="index.html">Back to University</a>
	</p>
</body>


</html>