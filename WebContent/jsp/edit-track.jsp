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
<title>Edit track</title>
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
  <fmt:message key="track_list.singer"/> :
  <input type="text" name="singer" value="${track.singer}" maxlength="60" required placeholder="*"><br>
  </div>
  <div>
  <fmt:message key="track_list.track_name"/> :
  <input type="text" name="track-name" value="${track.trackName}" maxlength="60" required placeholder="*"><br>
  </div>
  <div>
  <fmt:message key="track_list.album"/> :
  <input type="text" name="album" value="${track.album}" maxlength="45"><br>
  </div>
  <div>
  <fmt:message key="track_list.cost"/> :
  <input type="text" name="cost" value="${track.cost}" maxlength="5" required placeholder="*"><br>
  </div>
  <select name="genre">
  <option value="Trance" selected>Trance</option>
  <option value="House">House</option>
  <option value="Dubstep">Dubstep</option>
  <option value="Drum_&_Bass">Drum & Bass</option>
  <option value="Techno">Techno</option>
  </select><br>
  <div class="error">
  ${errorMessage}
  </div>
  <input class="button" type="submit" value="<fmt:message key="save"/>">
  <input class="button" type="reset" value="<fmt:message key="reset"/>">
  <input type="hidden" value="${track.id}" name="trackId">
  <input type="hidden" value="save-track" name="command">
  </form>
  <footer class="footer"> 
  EDM Portal<br>
  &copy; Maxim Gadalov, 2016<br>
  E-mail:gmaksim2012@gmail.com<br>
  Skype:gmaxim2111
  </footer>

</body>
</html>