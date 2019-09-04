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
	
	<p>User name: ${loginedUser.userName}</p>
	
	<table>
		
		<c:forEach var="post" items="${posts}" >
			<tr>
				<c:out value="${post.value.description}" />
			</tr><br>
	
		</c:forEach>
	
	</table>
	
	<jsp:include page="_footer.jsp"></jsp:include>
	
</body>
</html>