<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Database error page</title>
</head>
<body>
<h1 style="text-align:center;">${pageContext.exception.message}</h1>
<p style="text-align:center;">
<img src="<c:url value="/dolphin.jpeg" context="/MusicWebService/IMG"/>" alt="dolphin">
</p>
<h2 style="text-align:center;">We are sorry :( </h2>
<p style="text-align:center;">
<a href="<c:url value="/main.jsp" context="/MusicWebService"/>" title="Home">Back to main page</a>
</p>
</body>
</html>