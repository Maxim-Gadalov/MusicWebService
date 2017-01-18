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
<title>User profile</title>
<link rel="stylesheet" href="<c:url value="/normalize.css" context="/MusicWebService/CSS"/>">
<link rel="stylesheet" href="<c:url value="/screen.css" context="/MusicWebService/CSS"/>">
<link rel="stylesheet" href="<c:url value="/profile-content.css" context="/MusicWebService/CSS"/>">
</head>
<body>
<header>
    <ctg:header-custom nickname="${nickname}" role="${role}"/>
  </header>
  <section class="user-info"> 
  <form action=<c:url value="/MusicServiceServlet" context="/MusicWebService"/> method="POST">
  <input class="edit-profile" type="submit" value="Edit profile">
  <input type="hidden" value="edit-profile" name="command">
  </form>
  <img class="profile-img" src="IMG/user.png" alt="user">
  <div>
  Nickname : ${userNickname}
  </div>
  <div>
  Email : ${userEmail}
  </div>
  <div>
  Phone number : ${phnumber}
  </div>
  <div>
  Skype : ${skype}
  </div>
  <form action=<c:url value="/MusicServiceServlet" context="/MusicWebService"/> method="POST">
  <input class="change-password" type="submit" value="Change password">
  <input type="hidden" name="command" value="change-password-page">
  </form>
  </section>
  <aside class="user-tracks">
  tracks<br>
  Track one<br>
  Track one<br>
  Track one<br>
  Track one<br>
  Track one<br>
  Track one<br>
  Track one<br>
  Track one<br>
  </aside>
  <footer class="footer"> 
  EDM Portal<br>
  &copy; Maxim Gadalov, 2016<br>
  E-mail:gmaksim2012@gmail.com<br>
  Skype:gmaxim2111
  </footer>
</body>
</html>