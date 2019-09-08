<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Post</title>
</head>
<body>
	<jsp:include page="_header.jsp"></jsp:include>
	<jsp:include page="_menu.jsp"></jsp:include>
	
	<form method="POST" action="${pageContext.request.contextPath}/addPost">
		<table>
			<tr>
				<td>Link</td>
				<td><p>Please insert a link of the picture.</p></td>
				<td><input type="text" name="pictureLink" value="${post.pictureLink}" /></td>
			</tr>
			<tr>
				<td>Description</td>
				<td><input type="text" name="description" value="${post.description}" /></td>
			</tr>			
			<tr>
				<td>Category</td>
				<td><input type="text" name="category" value="${post.category}" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Submit" /><td>
			</tr>					
		</table>
	</form>
	
	<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>