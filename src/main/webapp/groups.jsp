<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link href="<c:url value="/resources/style.css" />" rel="stylesheet">


<title>allGroups</title>

</head>


<body>

	<div id="wrapper">
		<div id="header">
			<h2>University</h2>
		</div>
	</div>

	<div id="container">

		<div id="content">


			<form action="group/create" method="post">

				<input type="text" name="name" />
				<input type="submit" value="Add Group" class="button" />
			</form>



			<table>

				<tr>

					<th>Name</th>
					<th>Students</th>
					<th>Delete</th>

				</tr>

				<c:forEach var="group" items="${groups}">


					<tr>

						<td>
							<a href="group/${group.id}">${group.name}</a>
						</td>
						<td>${fn:length(group.students)}</td>

						<td>
							<form action="group/delete/${group.id} " method="post">

								<input type="submit" value="Delete" class="button"
									onclick="if (!(confirm('Are you sure you want to delete this group?'))) return false" />
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