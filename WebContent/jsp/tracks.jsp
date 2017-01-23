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
<title>Tracks</title>
  <link rel="stylesheet" href="<c:url value="/normalize.css" context="/MusicWebService/CSS"/>">
  <link rel="stylesheet" href="<c:url value="/screen.css" context="/MusicWebService/CSS"/>">
  <link rel="stylesheet" href="<c:url value="/tracks-content.css" context="/MusicWebService/CSS"/>">
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
  <section id="trackPlayer">
  <div style="color:red;text-align:center;">
  ${errorMessage}
  </div>
  <c:forEach  var="elem" items="${trackList}" varStatus="status">
  <form action="<c:url value="/MusicServiceServlet" context="/MusicWebService"/>" method="POST">
  <div class="track">
  <span onclick="showPlayer('<c:out value="${elem.id}"/>'); return false;">
  <c:out value="${elem.singer}"/> - 
  <c:out value="${elem.trackName}"/>
  </span>
  <input type="hidden" name="idTrack" value="<c:out value="${elem.id}"/>">
  <button type="submit" class="buy-button">buy ${elem.cost}$</button>
  <div id=<c:out value="${elem.id}"/> style="display:none;">
  <audio id="player" controls>
  <source src="<c:out value="${elem.filePath}"/>" type="audio/mpeg">
  </audio>
  <ctg:comments-custom user="${user}" track="${elem}"/>
  </div>
  <input type="hidden" name="command" value="buy-track">
  </div>
  </form>
  <form id="comment<c:out value="${elem.id}"/>" action="<c:url value="/MusicServiceServlet" context="/MusicWebService"/>" method="POST">
  <input type=hidden name=idTrack value="<c:out value="${elem.id}"/>">
  <input type=hidden name=command value=add-comment>
  </form>
  </c:forEach>
  <form id="edit-form" action="<c:url value="/MusicServiceServlet" context="/MusicWebService"/>" method="POST">
  <input type=hidden name=command value=edit-comment>
  <input type="hidden" id="commentId" name="commentId" value="">
  </form>

  <ctg:pagination-custom numberOfElements="${numberOfElements}"/>
  </section>  
<footer class="footer"> 
  EDM Portal<br>
  &copy; Maxim Gadalov, 2016<br>
  E-mail:gmaksim2012@gmail.com<br>
  Skype:gmaxim2111
  </footer>
  <script src="JS/track.js"></script>
</body>
</html>