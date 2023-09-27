<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Квесты от Сереги</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
<h1><%= "Добро пожаловать в увлекательный мир квестов!" %></h1>
<br>
<h3><%= "Для начала давайте познакомимся. Введите ваше имя, пожалуйста:" %></h3>

<form action="/start-servlet" method="post">
  <input id="playerName" type="text" maxlength="30" name="name">
  <button id="submit" name="submit">Перейти к списку квестов</button>
</form>


</body>
</html>