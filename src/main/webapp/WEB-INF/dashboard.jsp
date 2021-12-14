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
		<div class="row">
			<div>
				<h1>Welcome, ${userName }</h1>
			</div>
			<div>
				<p>Books from everyone's shelves:</p>
			</div>
			<div class="d-flex justify-content-end">
				<a href="/logout" class="btn btn-success btn-sm">Logout</a>
			</div>
			<div class="d-flex justify-content-end">
				<a href="/books/new" class="btn btn-primary btn-sm">Add Book</a>
			</div>
			<div class="d-flex justify-content-end">
				<a href="/bookmarket" class="btn btn-info btn-sm">Book Market</a>
			</div>

			<table class="table table-striped">
				<thead>
					<tr>
						<th>ID</th>
						<th>Title</th>
						<th>Author Name</th>
						<th>Posted By</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="book" items="${books }">
						<tr>
							<td>${book.id }</td>	
							<td><a href="/books/${book.id }">${book.title }</a></td>	
							<td>${book.author }</td>	
							<td>${book.writer.userName }</td>				
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>