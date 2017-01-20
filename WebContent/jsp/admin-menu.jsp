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
<title>Administration menu</title>
  <link rel="stylesheet" href="<c:url value="/normalize.css" context="/MusicWebService/CSS"/>">
  <link rel="stylesheet" href="<c:url value="/screen.css" context="/MusicWebService/CSS"/>">
  <link rel="stylesheet" href="<c:url value="/admin-menu-content.css" context="/MusicWebService/CSS"/>">
</head>
<body>
<header>
<ctg:header-custom user="${user}"/> 
</header>
<h1>Welcome to the administration menu</h1>
<section>
<div style="color:red;">
Good whatever time of day applies, ${user.nickname}. 
You are in the menu of web service administration. 
We remind you that you should not exercise the authority given to you by project managers for personal advantage. 
All decisions must be clear and consistent. 
Thank you for being with us. 
</div>
  <form class="admin-form" action=<c:url value="/MusicServiceServlet" context="/MusicWebService"/> method="POST" enctype="multipart/form-data">
  <div style="color:red; text-align:center;">
  ${addTrackError}
  </div>
  <button class="show-add-form" onclick="showAddTrackForm('add-track')">Add track</button>
  <div id="add-track" style="display:none;">
  Singer :<br>
  <input type="text" name="singer" required placeholder="*"><br>
  Track name :<br>
  <input type="text" name="track-name" required placeholder="*"><br>
  Album :<br>
  <input type="text" name="album"><br>
  Cost :<br>
  <input type="text" name="cost" required placeholder="*"><br>
  <select name="genre">
  <option value="Trance" selected>Trance</option>
  <option value="House">House</option>
  <option value="Dubstep">Dubstep</option>
  <option value="Drum & Bass">Drum & Bass</option>
  <option value="Techno">Techno</option>
  </select><br>
  <input name="file-path" type="file" accept="audio/*"><br>
  <input class="button" type="submit" value="Submit">
  <input class="button" type="reset" value="Reset">
  </div>
  <input type="hidden" name="command" value="addTrack">
  </form>
  <form action=<c:url value="/MusicServiceServlet" context="/MusicWebService"/> method="POST">
  <button class="show-add-form" onclick="showAddTrackForm('remove-track')">Remove track</button>
  <div id="remove-track" style="display:none;">
  Singer :<br>
  <input type="text" name="singer-remove" required placeholder="*"><br>
  Track name :<br>
  <input type="text" name="track-name-remove" required placeholder="*"><br>
  <input class="button" type="submit" value="Submit">
  <input class="button" type="reset" value="Reset">
  </div>
  <input type="hidden" name="command" value="remove-track">
  </form>
  <form action=<c:url value="/MusicServiceServlet" context="/MusicWebService"/> method="POST">
  <button class="show-add-form" onclick="showAddTrackForm('ban-user')">Ban user</button>
  <div id="ban-user" style="display:none;">
  Nickname or E-mail :<br>
  <input type="text" name="user-login" required placeholder="*"><br>
  <textarea name="reason" rows="5" cols="30" required>Reason of ban</textarea><br>
  <input class="button" type="submit" value="Submit">
  <input class="button" type="reset" value="Reset">
  </div>
  <input type="hidden" name="command" value="ban-user">
  </form>
  <form action=<c:url value="/MusicServiceServlet" context="/MusicWebService"/> method="POST">
  <button class="show-add-form" onclick="showAddTrackForm('unban-user')">Unban user</button>
  <div id="unban-user" style="display:none;">
  Nickname or E-mail :<br>
  <input type="text" name="user-login" required placeholder="*"><br>
  <input class="button" type="submit" value="Submit">
  <input class="button" type="reset" value="Reset">
  </div>
  <input type="hidden" name="command" value="unban-user">
  </form>
  
  <form action=<c:url value="/MusicServiceServlet" context="/MusicWebService"/> method="POST">
  <button class="show-add-form" onclick="showAddTrackForm('assign-discount')">Assign discount</button>
  <div id="assign-discount" style="display:none;">
  Nickname or E-mail :<br>
  <input type="text" name="user-login" required placeholder="*"><br>
  <select name="discount">
    <c:forEach  var="elem" items="${list_discounts}" varStatus="status">
    <option value="${elem.id}">${elem.bonus}%</option>
    </c:forEach>
  </select>
  <input class="button" type="submit" value="Submit">
  <input class="button" type="reset" value="Reset">
  </div>
  <input type="hidden" name="command" value="assign-discount">
  </form>
</section>
<aside>
<div class="users-list">
<table>
<caption> Users list</caption>
<tr>
<th>Role</th>
<th>Nickname</th>
<th>Email</th>
<th>Skype</th>
<th>Phone number</th>
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
<caption> Tracks list</caption>
<tr>
<th>Id</th>
<th>Singer</th>
<th>Track name</th>
<th>Cost</th>
<th>Genre</th>
<th>Admin</th>
</tr> 
<c:forEach  var="elem" items="${list_tracks}" varStatus="status">
  <tr>
  <td><c:out value="${ elem.id }" /></td>
    <td><c:out value="${ elem.singer }" /></td>
    <td><c:out value="${ elem.trackName }" /></td>
    <td><c:out value="${ elem.cost }" /></td>
    <td><c:out value="${ elem.genre }" /></td>
    <td><c:out value="${ elem.user.nickname }" /></td>
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