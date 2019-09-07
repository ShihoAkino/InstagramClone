<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Home Page</title>
</head>
<body>
 
     <jsp:include page="_header.jsp"></jsp:include>
     <jsp:include page="_menu.jsp"></jsp:include>
     
     <h3>Home page of Insta clone!</h3>
     
     <p>This is my first web application</p>
     <h4>Goals of this application is the following:</h4>
     <ol>
     	<li>A user can register to the app.</li>
     	<li>A user can log in to the app.</li>
     	<li>A user can upload posts.</li>
     	<li>A user can search posts</li>
     	<li>A user can follow other users.</li>
     	<li>A user can like other users' posts</li>
     </ol>
     
     <jsp:include page="_footer.jsp"></jsp:include>
     
     
</body>
</html>