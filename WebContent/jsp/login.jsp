<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="/errors/database_error.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="ctg" uri="customtags" %> 
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="description" content="Order music web application">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="keywords" content="music order,trance,EDM,electronic dance music,electronic music,DJ,house music,music">
  <meta name="author" content="Maxim Gadalov">
  <title>Log in</title>
  <link rel="stylesheet" href="<c:url value="/normalize.css" context="/MusicWebService/CSS"/>">
  <link rel="stylesheet" href="<c:url value="/screen.css" context="/MusicWebService/CSS"/>">
  <link rel="stylesheet" href="<c:url value="/login-content.css" context="/MusicWebService/CSS"/>">
</head>
<body>
<header>
  <ctg:header-custom user="${user}"/>  
  </header>
  <section>
    <form class="login-form" action=<c:url value="/MusicServiceServlet" context="/MusicWebService"/> method="POST">
	<h2>Log in</h2>
	Enter email or nickname:<br>
	<input class="textfield" type="text" name="nick-or-mail" required><br>
	Enter password:<br>
	<input class="textfield" type="password"name="password" required>
	<div class="message">
	${errorMessage}
	</div>
	<input class="button" type="submit" value="Submit">
    <input class="button" type="reset" value="Reset">
    <input value="login" type="hidden" name="command">
	</form>
  </section>
   <footer class="footer"> 
  EDM Portal<br>
  &copy; Maxim Gadalov, 2016<br>
  E-mail:gmaksim2012@gmail.com<br>
  Skype:gmaxim2111
  </footer>
</body>
</html>