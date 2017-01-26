<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="/errors/database_error.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="ctg" uri="customtags" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="property.content"/> 
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
	<h2><fmt:message key="log_in"/></h2>
	<fmt:message key="my_profile.login"/>:<br>
	<input class="textfield" type="text" name="nick-or-mail" maxlength="60" required><br>
	<fmt:message key="my_profile.pass"/>:<br>
	<input class="textfield" type="password"name="password" maxlength="30" required>
	<div class="message">
	${errorMessage}
	</div>
	<input class="button" type="submit" value="<fmt:message key="submit"/>">
    <input class="button" type="reset" value="<fmt:message key="reset"/>">
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