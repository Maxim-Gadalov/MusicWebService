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
<title>Administration menu</title>
  <link rel="stylesheet" href="<c:url value="/normalize.css" context="/MusicWebService/CSS"/>">
  <link rel="stylesheet" href="<c:url value="/screen.css" context="/MusicWebService/CSS"/>">
  <link rel="stylesheet" href="<c:url value="/admin-menu-content.css" context="/MusicWebService/CSS"/>">
</head>
<body>
<header>
<ctg:header-custom user="${user}"/> 
</header>
<h1><fmt:message key="admin_welcome"/></h1>
<h2>${databaseError}</h2>
<section>
<div style="color:red;">
<fmt:message key="admin_message"/>
</div>
  <form class="admin-form" action=<c:url value="/MusicServiceServlet" context="/MusicWebService"/> method="POST" enctype="multipart/form-data">
  <div style="color:red; text-align:center;">
  ${addTrackError}
  </div>
  <button class="show-add-form" onclick="showAddTrackForm('add-track')"><fmt:message key="track_list.add"/></button>
  <div id="add-track" style="display:none;">
  <fmt:message key="track_list.singer"/> :<br>
  <input type="text" name="singer" maxlength="60" required placeholder="*"><br>
  <fmt:message key="track_list.track_name"/> :<br>
  <input type="text" name="track-name" maxlength="60" required placeholder="*"><br>
  <fmt:message key="track_list.album"/> :<br>
  <input type="text" name="album" maxlength="45"><br>
  <fmt:message key="track_list.cost"/> :<br>
  <input type="text" name="cost" maxlength="5" required placeholder="*"><br>
  <select name="genre">
  <option value="Trance" selected>Trance</option>
  <option value="House">House</option>
  <option value="Dubstep">Dubstep</option>
  <option value="Drum_&_Bass">Drum & Bass</option>
  <option value="Techno">Techno</option>
  </select><br>
  <input name="file-path" type="file" accept="audio/*"><br>
  <input class="button" type="submit" value="<fmt:message key="submit"/>">
  <input class="button" type="reset" value="<fmt:message key="reset"/>">
  </div>
  <input type="hidden" name="command" value="addTrack">
  </form>
  <form action=<c:url value="/MusicServiceServlet" context="/MusicWebService"/> method="POST">
  <div style="color:red;">
  ${removeErrorMessage}
  </div>
  <button class="show-add-form" onclick="showAddTrackForm('remove-track')"><fmt:message key="track_list.remove"/></button>
  <div id="remove-track" style="display:none;">
  <fmt:message key="track_list.singer"/> :<br>
  <input type="text" name="singer-remove" maxlength="60" required placeholder="*"><br>
  <fmt:message key="track_list.track_name"/> :<br>
  <input type="text" name="track-name-remove" maxlength="60" required placeholder="*"><br>
  <input class="button" type="submit" value="<fmt:message key="submit"/>">
  <input class="button" type="reset" value="<fmt:message key="reset"/>">
  </div>
  <input type="hidden" name="command" value="remove-track">
  </form>
  <div style="color:red;">
  ${editErrorMessage}
  </div>
  <form action=<c:url value="/MusicServiceServlet" context="/MusicWebService"/> method="POST">
  <button class="show-add-form" onclick="showAddTrackForm('edit-track')"><fmt:message key="track_list.edit"/></button>
  <div id="edit-track" style="display:none;">
  <fmt:message key="track_list.id"/> :<br>
  <input type="number" name="idTrack" required placeholder="*"><br>
  <input class="button" type="submit" value="<fmt:message key="submit"/>">
  <input class="button" type="reset" value="<fmt:message key="reset"/>">
  </div>
  <input type="hidden" name="command" value="edit-track">
  </form>
  <form action=<c:url value="/MusicServiceServlet" context="/MusicWebService"/> method="POST">
  <div style="color:red;">
  ${banErrorMessage}
  </div>
  <button class="show-add-form" onclick="showAddTrackForm('ban-user')"><fmt:message key="user_list.ban"/></button>
  <div id="ban-user" style="display:none;">
  <fmt:message key="my_profile.login"/> :<br>
  <input type="text" name="user-login" maxlength="60" required placeholder="*"><br>
  <textarea name="reason" rows="5" cols="30" maxlength="255" required><fmt:message key="user_list.reason"/></textarea><br>
  <input class="button" type="submit" value="<fmt:message key="submit"/>">
  <input class="button" type="reset" value="<fmt:message key="reset"/>">
  </div>
  <input type="hidden" name="command" value="ban-user">
  </form>
  <form action=<c:url value="/MusicServiceServlet" context="/MusicWebService"/> method="POST">
  <div style="color:red;">
  ${unbanErrorMessage}
  </div>
  <button class="show-add-form" onclick="showAddTrackForm('unban-user')"><fmt:message key="user_list.unban"/></button>
  <div id="unban-user" style="display:none;">
  <fmt:message key="my_profile.login"/> :<br>
  <input type="text" name="user-login" maxlength="60" required placeholder="*"><br>
  <input class="button" type="submit" value="<fmt:message key="submit"/>">
  <input class="button" type="reset" value="<fmt:message key="reset"/>">
  </div>
  <input type="hidden" name="command" value="unban-user">
  </form>
  
  <form action=<c:url value="/MusicServiceServlet" context="/MusicWebService"/> method="POST">
  <div style="color:red;">
  ${discountErrorMessage}
  </div>
  <button class="show-add-form" onclick="showAddTrackForm('assign-discount')"><fmt:message key="user_list.assign_discount"/></button>
  <div id="assign-discount" style="display:none;">
  <fmt:message key="my_profile.login"/> :<br>
  <input type="text" name="user-login" maxlength="60" required placeholder="*">
  <select name="discount">
    <c:forEach  var="elem" items="${list_discounts}" varStatus="status">
    <option value="${elem.id}">${elem.bonus}%</option>
    </c:forEach>
  </select><br>
  <input class="button" type="submit" value="<fmt:message key="submit"/>">
  <input class="button" type="reset" value="<fmt:message key="reset"/>">
  </div>
  <input type="hidden" name="command" value="assign-discount">
  </form>
</section>
<aside>
<div class="users-list">
<table>
<caption><fmt:message key="user_list"/></caption>
<tr>
<th><fmt:message key="user_list.role"/></th>
<th><fmt:message key="my_profile.nickname"/></th>
<th><fmt:message key="my_profile.email"/></th>
<th>Skype</th>
<th><fmt:message key="my_profile.phone_number"/></th>
</tr> 
<c:forEach  var="elem" items="${list_users}" varStatus="status">
  <tr>
    <td><c:out value="${ elem.role.roleName }" /></td>
    <td><c:out value="${ elem.nickname }" /></td>
    <td><c:out value="${ elem.email }" /></td>
    <td><c:out value="${ elem.skype }" /></td>
    <td><c:out value="${ elem.phoneNumber }" /></td>
 </tr>
  </c:forEach>
</table>
</div>
<div class="track-list">
<table>
<caption><fmt:message key="track_list"/></caption>
<tr>
<th><fmt:message key="track_list.id"/></th>
<th><fmt:message key="track_list.singer"/></th>
<th><fmt:message key="track_list.track_name"/></th>
<th><fmt:message key="track_list.cost"/></th>
<th><fmt:message key="track_list.genre"/></th>
<th><fmt:message key="track_list.admin"/></th>
<th><fmt:message key="track_list.visibility"/></th>
</tr> 
<c:forEach  var="elem" items="${list_tracks}" varStatus="status">
  <tr>
  <td><c:out value="${ elem.id }" /></td>
    <td><c:out value="${ elem.singer }" /></td>
    <td><c:out value="${ elem.trackName }" /></td>
    <td><c:out value="${ elem.cost }" /></td>
    <td><c:out value="${ elem.genre }" /></td>
    <td><c:out value="${ elem.user.nickname }" /></td>
    <td><c:out value="${ elem.visibility }" /></td>
 </tr>
  </c:forEach>
</table>
</div>

</aside>

  <footer class="footer"> 
  EDM Portal<br>
  &copy; Maxim Gadalov, 2016<br>
  E-mail:gmaksim2012@gmail.com<br>
  Skype:gmaxim2111
  </footer>
  <script src="/MusicWebService/JS/admin-menu.js"></script>
</body>
</html>