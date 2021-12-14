<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<title>Read Share</title>
</head>
<body>

	<div class="container mt-5">
		<h1>Add a Book to Your Shelf!</h1>
		<a href="/books">back to the shelves</a>
		<form:form action="/books/new" method="post" modelAttribute="newBook" class="form">
				<p>
					<form:label path="title" class="form-label">Title</form:label>
					<form:errors path="title" class="text-danger"/>
					<form:input path="title" type="text" class="form-control"/>
				</p>
				<p>
					<form:label path="author" class="form-label">Author</form:label>
					<form:errors path="author" class="text-danger" />
					<form:input path="author" type="text" class="form-control"/>
				</p>
				<p>
					<form:label path="thoughts" class="form-label">My Thoughts</form:label>
					<form:errors path="thoughts" class="text-danger"/>
					<form:input path="thoughts" type="text" class="form-control"/>
				</p>
				<form:input type="hidden" path="writer" value="${userId }" />
				<button class="btn btn-primary">Submit</button>
		</form:form>
	</div>
	

</body>
</html>