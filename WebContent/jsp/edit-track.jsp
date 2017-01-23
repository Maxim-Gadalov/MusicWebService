<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="ctg" uri="customtags" %>     
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
  Singer :
  <input type="text" name="singer" value="${track.singer}" required placeholder="*"><br>
  </div>
  <div>
  Track name :
  <input type="text" name="track-name" value="${track.trackName}" required placeholder="*"><br>
  </div>
  <div>
  Album :
  <input type="text" name="album" value="${track.album}"><br>
  </div>
  <div>
  Cost :
  <input type="text" name="cost" value="${track.cost}" required placeholder="*"><br>
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
  <input class="button" type="submit" value="Save">
  <input class="button" type="reset" value="Reset">
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