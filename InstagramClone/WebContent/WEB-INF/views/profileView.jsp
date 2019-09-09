<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Profile</title>
</head>
<body>

	<jsp:include page="_header.jsp"></jsp:include>
	<jsp:include page="_menu.jsp"></jsp:include>
	
	<h3>Profile</h3>
	
	<p>${errorString}</p>
	
	<p> ${loginedUser.bio}</p>
	<h4><a href="addPost">Upload Photo</a></h4>


	<c:forEach var="post" items="${posts}">
		<div style="text-align:center">
			<div >
				<img src="${post.value.pictureLink}" alt="${post.value.description}" width="300" height="300">
			</div>
			<br>
			<div>
				<c:out value="${post.value.postedDate}" />
			</div>
			<br>
			<div>
				<p><c:out value="${post.value.description}" /></p>
			</div>
			<br>
		</div>


	</c:forEach>


	<jsp:include page="_footer.jsp"></jsp:include>
	
</body>
</html>