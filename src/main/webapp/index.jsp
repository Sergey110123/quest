<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>Квесты от Сереги</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
<div class="d-flex justify-content-center"><h2><%= "Добро пожаловать в увлекательный мир квестов!" %></h2></div>
<br>
<div class="d-flex justify-content-center"><h4><%= "Для начала давайте познакомимся. Введите ваше имя, пожалуйста:" %></h4></div>
<br>
<div class="d-flex justify-content-center"><form action="${pageContext.request.contextPath}/start-servlet" method="post">
  <input class="form-control form-control-lg" id="playerName" type="text" maxlength="30" name="name">
  <br>
  <div class="d-flex justify-content-center"><button class="btn btn-primary" id="submit" name="submit"><%= "Перейти к списку квестов" %></button></div>
</form></div>


</body>
</html>