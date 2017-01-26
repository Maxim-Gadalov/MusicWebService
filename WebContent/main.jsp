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
  <title>Main page</title>
  <link rel="stylesheet" href="CSS/normalize.css">
  <link rel="stylesheet" href="CSS/screen.css">
  <link rel="stylesheet" href="CSS/content.css">
</head>
<body>
<header>
    <ctg:header-custom user="${user}"/> 
  </header>
  <h1><fmt:message key="welcome"/></h1>
 <section class="content"> 
  <div class="main-div">
   <img class="edm" src="IMG/EDM_music.jpg" onclick="hidetxt('span1'); return false;" alt="EDM" title="click for more info">
   <span id="span1" style="display:none;">
   <fmt:message key="about_edm"/>
   </span>
  </div><br>
  <h2><fmt:message key="genres"/> :</h2>
  <div class="about-trance">
  <img class="trance" src="IMG/trance_music_needcut.jpg" onclick="hidetxt('span2'); return false;" alt="trance" title="click for more info">
  <span id="span2" style="display:none;">
  <fmt:message key="about_trance"/>
  </span>
  </div>
  <div class="main-div">
  <img class="house" src="IMG/music_house.jpg" onclick="hidetxt('span3'); return false;" alt="house" title="click for more info">
  <span style="display:none;" id="span3">
  <fmt:message key="about_house"/>
  </span>
  </div>
  <div class="main-div">
  <img class="drum-and-bass" src="IMG/drum&bass.jpg" onclick="hidetxt('span4'); return false;" alt="drum%bass" title="click for more info">
  <span style="display:none;" id="span4">
  <fmt:message key="about_drum_and_bass"/>
  </span>
  </div>
  <div class="main-div">
  <img class="dubstep" src="IMG/dubstep.jpg" onclick="hidetxt('span5'); return false;" alt="dubstep" title="click for more info">
  <span style="display:none;" id="span5">
  <fmt:message key="about_dubstep"/>
  </span>
  </div>
  <div class="main-div">
  <img class="techno" src="IMG/techno.jpg" onclick="hidetxt('span6'); return false;" alt="techno" title="click for more info">
  <span style="display:none;" id="span6">
 <fmt:message key="about_techno"/>
  </span>
  </div>
  </section>
  <aside class="second-content">
  <h2><fmt:message key="Internationally_known"/></h2>
  <div class="about-dj">
  <div class="dj-name" onclick="showSecondContent('div1','pointer1'); return false;">
  Tiesto
  <img class="pointer"  id="pointer1" src="IMG/pointer.png" alt="pointer">
  </div>
  <div id="div1" style="display:none;">
  <img class="dj" src="IMG/tiesto.jpg" alt="Tiesto">
  <fmt:message key="about_tiesto"/>
  </div>
  </div>
  <div class="about-dj">
  <div class="dj-name" onclick="showSecondContent('div2','pointer2'); return false;">
  Armin Van Buuren
  <img class="pointer"  id="pointer2" src="IMG/pointer.png" alt="pointer">
  </div>
  <div id="div2" style="display:none;">
  <img class="dj" src="IMG/armin.jpg" alt="Armin Van Buuren">
  <fmt:message key="about_armin"/>
  </div>
  </div>
  <div class="about-dj">
   <div class="dj-name" onclick="showSecondContent('div3','pointer3'); return false;">
  Deadmau5
  <img class="pointer"  id="pointer3" src="IMG/pointer.png" alt="pointer">
  </div>
  <div id="div3" style="display:none;">
  <img class="dj" src="IMG/deadmau5.jpg" alt="Deadmau5">
  <fmt:message key="about_deadmau5"/>
  </div>
  </div>
  <div class="about-dj">
  <div class="dj-name" onclick="showSecondContent('div4','pointer4'); return false;">
  David Guetta
  <img class="pointer"  id="pointer4" src="IMG/pointer.png" alt="pointer">
  </div>
  <div id="div4" style="display:none;">
  <img class="dj" src="IMG/guetta.png" alt="David Guetta">
 <fmt:message key="about_guetta"/>
  </div>
  </div>
   <div class="about-dj">
  <div class="dj-name" onclick="showSecondContent('div5','pointer5'); return false;">
  Above & Beyond
  <img class="pointer"  id="pointer5" src="IMG/pointer.png" alt="pointer">
  </div>
  <div id="div5" style="display:none;">
  <img class="dj" src="IMG/above_and_beyond.jpg" alt="Above & Beyond">
  <fmt:message key="about_above_and_beyond"/>
  </div>
  </div>
  <div class="about-dj">
   <div class="dj-name" onclick="showSecondContent('div6','pointer6'); return false;">
  Paul Van Dyk
  <img class="pointer"  id="pointer6" src="IMG/pointer.png" alt="pointer">
  </div>
   <div id="div6" style="display:none;">
  <img class="dj" src="IMG/paul-van-dyk.jpg" alt="Paul Van Dyk">
 <fmt:message key="about_dyk"/>
  </div>
  </div>
  <div class="about-dj">
   <div class="dj-name" onclick="showSecondContent('div7','pointer7'); return false;">
  Hardwell
  <img class="pointer"  id="pointer7" src="IMG/pointer.png" alt="pointer">
  </div>
  <div id="div7" style="display:none;">
  <img class="dj" src="IMG/hardwell.jpg" alt="Hardwell">
  <fmt:message key="about_hardwell"/>
  </div>
  </div>
  </aside>
  <footer class="footer"> 
  EDM Portal<div class="locale"><a href="#" onclick="document.getElementById('localeValue').value = 'English';document.getElementById('locale-form').submit();return false;">English</a></div><br>
  &copy; Maxim Gadalov, 2016<div class="locale"><a href="#" onclick="document.getElementById('localeValue').value = 'Russian';document.getElementById('locale-form').submit();return false;">Русский</a></div><br>
  E-mail:gmaksim2012@gmail.com<br>
  Skype:gmaxim2111
  <form action="/MusicWebService/MusicServiceServlet" method="POST" id="locale-form">
  <input type="hidden" name="locale" id="localeValue" value="">
  <input type="hidden" name="command" value="locale">
  </form>
  </footer>
  <script src="JS/mainpage.js"></script>
</body>
</html>