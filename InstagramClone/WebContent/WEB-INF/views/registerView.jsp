<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Register</title>
</head>
<body>
	<jsp:include page="_header.jsp"></jsp:include>
	<jsp:include page="_menu.jsp"></jsp:include>
	
	<h3>Register your account</h3>
	
	<p style="color:red">${errorString}</p>
	
	<form action="${pageContext.request.contextPath}/register" method="POST">
		<table>
			<tr>
				<td>User Name</td>
				<td><input type="text" name="userName" value="${user.userName}" /></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="text" name="password" value="${user.password}" /></td>
			</tr>
			<tr>
				<td>Biography</td>
				<td><input type="text" name="bio" value="${user.bio}" /></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="Submit" />
				</td>
			</tr>
		</table>
	</form>
	
	<jsp:include page="_footer.jsp"></jsp:include>


</body>
</html>