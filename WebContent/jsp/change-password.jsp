<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
  <title>Change password</title>    
  <link rel="stylesheet" href="<c:url value="/normalize.css" context="/MusicWebService/CSS"/>">
  <link rel="stylesheet" href="<c:url value="/screen.css" context="/MusicWebService/CSS"/>">
  <link rel="stylesheet" href="<c:url value="/change-password-content.css" context="/MusicWebService/CSS"/>">
</head>
<body>
<header>
    <ctg:header-custom nickname="${nickname}" role="${role}"/>
  </header>
  <section>
  <form class="change-password-form" id="change-password" action=<c:url value="/MusicServiceServlet" context="/MusicWebService"/> method="POST">
  <h2>Change password</h2>
  <label for="password">
  Password:<br>
  <input type="password" name="psw" id="password" maxlength="30" required>
  <ul class="checker">
    <li>At least 8 characters long (and less than 30 characters)</li>
	<li>Contains at least 1 number</li>
	<li>Contains at least 1 lowercase letter</li>
	<li>Contains at least 1 uppercase letter</li>
  </ul>
  </label>
  Confirm password:<br>
  <input type="password" name="cpsw" id="confirm_password" maxlength="30"  required><br>
  <div style="color:red; padding-top:15px; text-aling:center;">
   ${errorMessage}
  </div>
  <input class="button" type="submit" value="Submit">
  <input class="button" type="reset" value="Reset">
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