<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	<jsp:include page="_header.jsp"></jsp:include>
	<jsp:include page="_menu.jsp"></jsp:include>
	
	<h3>Log in to your account</h3>
	<p stylr="color:red">${errorString}</p>
	
	<form action="${pageContext.request.contextPath}/login" method="POST">
		<table>
			<tr>
				<td>User Name</td>
				<td><input type="text" name="userName" value="${user.userName}"/></td>
			</tr>
			
			<tr>
				<td>Password</td>
				<td><input type="password" name="password" value="${user.password}"/></td>
			</tr>
			
			<tr>
				<td><input type="submit" value="Submit" /></td>
				<td><a href="${pageContext.request.contextPath}/">Cancel</a></td>
			</tr>
			
		</table>
	</form>
	
	<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>