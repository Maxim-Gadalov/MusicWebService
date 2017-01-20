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
  <title>Registration</title>    
  <link rel="stylesheet" href="<c:url value="/normalize.css" context="/MusicWebService/CSS"/>">
  <link rel="stylesheet" href="<c:url value="/screen.css" context="/MusicWebService/CSS"/>">
  <link rel="stylesheet" href="<c:url value="/registration-content.css" context="/MusicWebService/CSS"/>">
</head>
<body>
<header>
  <ctg:header-custom user="${user}"/> 
  </header>
  <section>
  <form class="registration-form" id="registration" action=<c:url value="/MusicServiceServlet" context="/MusicWebService"/> method="POST">
  <h2>Registration</h2>
  <label for="nickname">
  Nickname:<br>
  <input type="text" name="nickname" id="username"  required placeholder="*">
  <ul class="checker">
    <li>At least 5 characters long</li>
    <li>Must only contain letters and numbers (no special characters)</li>
  </ul>
  </label>
  <label for="email">
  E-mail:<br>
  <input type="text" name="mail" id="email" required placeholder="*">
  <ul class="checker">
    <li> E-mail must be like "username@hostname"</li>
  </ul>
  </label>
  <label for="password">
  Password:<br>
  <input type="password" name="psw" id="password" maxlength="30" required placeholder="*">
  <ul class="checker">
    <li>At least 8 characters long (and less than 30 characters)</li>
	<li>Contains at least 1 number</li>
	<li>Contains at least 1 lowercase letter</li>
	<li>Contains at least 1 uppercase letter</li>
  </ul>
  </label>
  Confirm password:<br>
  <input type="password" name="cpsw" id="confirm_password" maxlength="30"  required placeholder="*"><br>
  <div style="color:red; padding-top:15px; text-aling:center;">
   ${errorMessage}
  </div>
  <input class="button" type="submit" value="Submit">
  <input class="button" type="reset" value="Reset">
  <input value="registration" type="hidden" name="command">
  </form>
  </section>
  <footer class="footer"> 
  EDM Portal<br>
  &copy; Maxim Gadalov, 2016<br>
  E-mail:gmaksim2012@gmail.com<br>
  Skype:gmaxim2111
  </footer>
    <script src="<c:url value="/JS/registration.js" context="/MusicWebService"/>"></script>
</body>
</html>