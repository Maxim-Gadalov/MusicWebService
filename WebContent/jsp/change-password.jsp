<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
  <title>Change password</title>    
  <link rel="stylesheet" href="<c:url value="/normalize.css" context="/MusicWebService/CSS"/>">
  <link rel="stylesheet" href="<c:url value="/screen.css" context="/MusicWebService/CSS"/>">
  <link rel="stylesheet" href="<c:url value="/change-password-content.css" context="/MusicWebService/CSS"/>">
</head>
<body>
<header>
  <ctg:header-custom user="${user}"/> 
  </header>
  <section>
  <form class="change-password-form" id="change-password" action=<c:url value="/MusicServiceServlet" context="/MusicWebService"/> method="POST">
  <h2><fmt:message key="change_password"/></h2>
  <label for="password">
  <fmt:message key="my_profile.pass"/>:<br>
  <input type="password" name="psw" id="password" maxlength="30" required>
  <ul class="checker">
    <li><fmt:message key="registration.psw_long"/></li>
	<li><fmt:message key="registration.psw_number"/></li>
	<li><fmt:message key="registration.psw_lcase"/></li>
	<li><fmt:message key="registration.psw_ucase"/></li>
  </ul>
  </label>
  <fmt:message key="my_profile.cpsw"/>:<br>
  <input type="password" name="cpsw" id="confirm_password" maxlength="30"  required><br>
  <div style="color:red; padding-top:15px; text-aling:center;">
   ${errorMessage}
  </div>
  <input class="button" type="submit" value="<fmt:message key="submit"/>">
  <input class="button" type="reset" value="<fmt:message key="reset"/>">
  <input value="change-password" type="hidden" name="command">
  </form>
  </section>
  <footer class="footer"> 
  EDM Portal<br>
  &copy; Maxim Gadalov, 2016<br>
  E-mail:gmaksim2012@gmail.com<br>
  Skype:gmaxim2111
  </footer>
    <script src="<c:url value="/JS/change-password.js" context="/MusicWebService"/>"></script>
</body>
</html>