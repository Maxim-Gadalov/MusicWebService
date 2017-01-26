<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>       
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Buy track</title>
<link rel="stylesheet" href="<c:url value="/normalize.css" context="/MusicWebService/CSS"/>">
<link rel="stylesheet" href="<c:url value="/pay-content.css" context="/MusicWebService/CSS"/>">
</head>
<body>
<h1>Please enter your credit card details to carry out transaction</h1>
<h2>You want to buy :</h2>
<h3>${singer} - ${trackName} for ${cost}$</h3>
<form action="" method="POST">
<div class="input-content">
<span>Credit card number :</span>
<input type="text" name="card-number" maxlength="16" required placeholder="*"><br>
<span class="name">First name :</span>
<input type="text" name="first-name" required placeholder="*"><br>
<span class="name">Last name :</span>
<input type="text" name="last-name" required placeholder="*"><br>
<span>Expires :</span>
<input class="short" type="text" name="expires" maxlength="5" required placeholder="MM/YY">
<span>Card code :</span>
<input class="short" type="text" name="cvc" maxlength="3" required placeholder="CVC"><br>
</div>
<div class="button-content">
<div style="color:red;text-align:center;">
  ${errorMessage}
</div>
<input class="button" type="submit" value="Submit">
<input class="button" type="reset" value="Reset">
<a href="/MusicWebService/main.jsp"><input class="button" type="button" value="Back to main"></a>
</div>
<input type="hidden" name="idTrack" value="${idTrack}">
<input type="hidden" name="command" value="transaction">
</form>

</body>
</html>