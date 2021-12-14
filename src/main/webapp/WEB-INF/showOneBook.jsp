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
			<div class="d-flex justify-content-between">
				<h1>${book.title }</h1>

				<a href="/books">back to the shelves</a>
			</div>
			
			<div>
				<c:choose>
					<c:when test="${book.writer.id == userId }">
						<div class="d-flex justify-content-between" >
							<h4 class="text-primary">${book.writer.userName } </h4> read <h4 class="text-success">${book.title } </h4> by
								<h4 class="text-warning"> ${book.author }.</h4>
						</div>
						<div>
							<span>Here are ${book.writer.userName }'s thoughts:</span>
						</div>
						<div>
							<span>${book.thoughts }</span>
						</div>
					</c:when>
					<c:otherwise></c:otherwise>
				</c:choose>
				
			</div>
			
			<c:choose>
				<c:when test="${book.writer.id == userId }">
					<p>
						<a href="/books/${book.id}/edit" class="btn btn-success btn-sm">Edit</a>
					</p>
				</c:when>
				<c:otherwise>
					<p></p>
				</c:otherwise>
			</c:choose>

			
		</div>
	</div>
	
</body>
</html>