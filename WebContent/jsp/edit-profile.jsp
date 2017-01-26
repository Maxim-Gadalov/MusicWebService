<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="property.content"/>    
<!DOCTYPE>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="description" content="Order music web application">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="keywords" content="music order,trance,EDM,electronic dance music,electronic music,DJ,house music,music">
<meta name="author" content="Maxim Gadalov">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit profile</title>
<link rel="stylesheet" href="<c:url value="/normalize.css" context="/MusicWebService/CSS"/>">
<link rel="stylesheet" href="<c:url value="/screen.css" context="/MusicWebService/CSS"/>">
<link rel="stylesheet" href="<c:url value="/edit-content.css" context="/MusicWebService/CSS"/>">
</head>
<body>
<header>
 <ctg:header-custom user="${user}"/> 
 </header>
 <form class="edit-form" action=<c:url value="/MusicServiceServlet" context="/MusicWebService"/> method="POST"> 
  <div>
  <fmt:message key="my_profile.nickname"/> :
  <input type="text" name="userNickname" value="${user.nickname}" required placeholder="*"><br>
  </div>
  <div>
  <fmt:message key="my_profile.email"/> :
  <input type="text" name="userEmail" value="${user.email}" required placeholder="*"><br>
  </div>
  <div>
  Skype :
  <input type="text" name="userSkype" value="${user.skype}"><br>
  </div>
  <div>
  <fmt:message key="my_profile.phone_number"/> :
  <input type="text" name="userNumber" value="${user.phoneNumber}"><br>
  </div>
  <div class="error">
  ${errorMessage}
  </div>
  <input class="button" type="submit" value="<fmt:message key="save"/>">
  <input class="button" type="reset" value="<fmt:message key="reset"/>">
  <input type="hidden" value="save-profile" name="command">
  </form>
  <footer class="footer"> 
  EDM Portal<br>
  &copy; Maxim Gadalov, 2016<br>
  E-mail:gmaksim2012@gmail.com<br>
  Skype:gmaxim2111
  </footer>
</body>
</html>