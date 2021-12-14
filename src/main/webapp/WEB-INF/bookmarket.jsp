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
<title>Book Lender Dashboard</title>
</head>
<body>
	<div class="container mt-5">
		<div class="row">
			<div class="d-flex justify-content-between">
				<h2>Hello, ${userName }. Welcome To...</h2>
				<a href="/books">back to the shelves</a>
			</div>
			<div>
				<h2>The Book Broker!</h2>
			</div>
			<div>

				<h4>Available Books to Borrow</h4>
			</div>
			<table class="table table-striped">
				<thead>
					<tr>
						<th>ID</th>
						<th>Title</th>
						<th>Author Name</th>
						<th>Owner</th>
						<th colspan="2">Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="book" items="${books }">
						<c:if test="${book.reader == null}">
							<tr>
								<td>${book.id }</td>
								<td><a href="/books/${book.id }">${book.title }</a></td>
								<td>${book.author }</td>
								<td>${book.writer.userName }</td>
								<c:choose>
									<c:when test="${book.writer.id == userId }">
										<td><a href="/books/${book.id}/edit"
											class="btn btn-success btn-sm">Edit</a></td>
										<td>
											<form action="/books/${book.id}/delete" method="post">
												<input type="hidden" name="_method" value="delete" />
												<button class="btn btn-danger btn-sm">Delete</button>
											</form>
										</td>
									</c:when>

									<c:otherwise>
										<td>
											<form action="/books/${book.id}/borrow" method="post">
												<input type="hidden" name="_method" value="put" />
												<button class="btn btn-primary btn-sm">Borrow</button>
											</form>
										</td>

										<td></td>
									</c:otherwise>
								</c:choose>
							</tr>
						</c:if>
					</c:forEach>
				</tbody>
			</table>


			<h4>Books I'm Borrowing...</h4>
			<table class="table table-striped">
				<thead>
					<tr>
						<th>ID</th>
						<th>Title</th>
						<th>Author Name</th>
						<th>Owner</th>
						<th colspan="2">Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="book" items="${books }">
						<c:if test="${book.reader.id == userId }">
							<tr>
								<td>${book.id }</td>
								<td><a href="/books/${book.id }">${book.title }</a></td>
								<td>${book.author }</td>
								<td>${book.writer.userName }</td>
								<td><form action="/books/${book.id}/return" method="post">
										<input type="hidden" name="_method" value="put" />
										<button class="btn btn-primary btn-sm">Return</button>
									</form>
								</td>
							</tr>
						</c:if>

					</c:forEach>
				</tbody>
			</table>



		</div>

	</div>
</body>
</html>