<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        .container {
            width: 50%;
            padding: 20px;
        }
        input {
            padding: 5px;
            display: block;
            width: 100%;
        }
    </style>
    <meta charset="UTF-8"/>
    <title>Вход</title>
</head>
<body>
<div class="container">
    <h2>Вход в систему</h2>
    <form action="my" method="POST">
        <label for="login">Login:</label>
        <input type="text" id="login" name="login" required>
        <br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
        <br>
        <input type="submit" value="Войти">
    </form>
    <a href="registration">Зарегистрироваться</a>
</div>
</body>
</html>
