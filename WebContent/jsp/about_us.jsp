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
<title>About us</title>
<link rel="stylesheet" href="<c:url value="/normalize.css" context="/MusicWebService/CSS"/>">
<link rel="stylesheet" href="<c:url value="/screen.css" context="/MusicWebService/CSS"/>">
</head>
<body>
<header>
  <ctg:header-custom user="${user}"/> 
</header>
<h1 style="margin-top:80px;text-align:center;"><fmt:message key="about_us"/></h1>
<div style="margin-bottom:60%;">
<fmt:message key="about_us.message"/>
</div>

  <footer class="footer"> 
  EDM Portal<br>
  &copy; Maxim Gadalov, 2016<br>
  E-mail:gmaksim2012@gmail.com<br>
  Skype:gmaxim2111
  </footer>
</body>
</html>