<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="ctg" uri="customtags" %>     
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
    <ctg:header-custom nickname="${nickname}" role="${role}"/>
 </header>
 <form class="edit-form" action=<c:url value="/MusicServiceServlet" context="/MusicWebService"/> method="POST"> 
  <div>
  Nickname :
  <input type="text" name="userNickname" value="${userNickname}" required placeholder="*"><br>
  </div>
  <div>
  Email :
  <input type="text" name="userEmail" value="${userEmail}" required placeholder="*"><br>
  </div>
  <div>
  Skype :
  <input type="text" name="userSkype" value="${skype}"><br>
  </div>
  <div>
  Phone number :
  <input type="text" name="userNumber" value="${phnumber}"><br>
  </div>
  <div class="error">
  ${errorMessage}
  </div>
  <input class="button" type="submit" value="Save">
  <input class="button" type="reset" value="Reset">
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