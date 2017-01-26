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
<meta name="description" content="Order music web application">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="keywords" content="music order,trance,EDM,electronic dance music,electronic music,DJ,house music,music">
<meta name="author" content="Maxim Gadalov">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User profile</title>
<link rel="stylesheet" href="<c:url value="/normalize.css" context="/MusicWebService/CSS"/>">
<link rel="stylesheet" href="<c:url value="/screen.css" context="/MusicWebService/CSS"/>">
<link rel="stylesheet" href="<c:url value="/profile-content.css" context="/MusicWebService/CSS"/>">
<script>
  document.addEventListener('play', function(e){
	    var audios = document.getElementsByTagName('audio');
	    for(var i = 0, len = audios.length; i < len;i++){
	        if(audios[i] != e.target){
	            audios[i].pause();
	        }
	    }
	}, true);
  </script>
</head>
<body>
<header>
  <ctg:header-custom user="${user}"/> 
  </header>
  <section class="user-info"> 
  <form action=<c:url value="/MusicServiceServlet" context="/MusicWebService"/> method="POST">
  <input class="edit-profile" type="submit" value="<fmt:message key="my_profile.edit_profile"/>">
  <input type="hidden" value="edit-profile" name="command">
  </form>
  <img class="profile-img" src="IMG/user.png" alt="user">
  <div>
  <fmt:message key="my_profile.nickname"/> : ${user.nickname}
  </div>
  <div>
  <fmt:message key="my_profile.email"/> : ${user.email}
  </div>
  <div>
  <fmt:message key="my_profile.phone_number"/> : ${user.phoneNumber}
  </div>
  <div>
  Skype : ${user.skype}
  </div>
  <div>
  <fmt:message key="my_profile.discount"/> : ${user.bonus.bonus}%
  </div>
  <form action=<c:url value="/MusicServiceServlet" context="/MusicWebService"/> method="POST">
  <input class="change-password" type="submit" value="<fmt:message key="my_profile.change_pass"/>">
  <input type="hidden" name="command" value="change-password-page">
  </form>
  </section>
  <aside class="user-tracks">
  <h3 style="text-align:center;"><fmt:message key="purchase_music"/> : </h3>
  <div style="color:red;">
  ${errorMessage}
  </div>
  <c:forEach  var="elem" items="${trackList}" varStatus="status">
  <div class="track">
  <span onclick="showPlayer('<c:out value="${elem.id}"/>'); return false;">
  <c:out value="${elem.singer}"/> - 
  <c:out value="${elem.trackName}"/>
  </span>
  <div id=<c:out value="${elem.id}"/> style="display:none;">
  <audio id="player" controls>
  <source src="<c:out value="${elem.filePath}"/>" type="audio/mpeg">
  </audio>
  </div>
  </div>
  </c:forEach>
  </aside>
  <footer class="footer"> 
  EDM Portal<br>
  &copy; Maxim Gadalov, 2016<br>
  E-mail:gmaksim2012@gmail.com<br>
  Skype:gmaxim2111
  </footer>
  <script src="JS/track.js"></script>
</body>
</html>