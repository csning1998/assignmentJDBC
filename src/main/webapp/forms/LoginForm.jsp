<%--
  Created by IntelliJ IDEA.
  User: C.S. Ning
  Date: null
  Time: 00:00 GMT
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login Form</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css">
    <script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>
    <style>
        .mdl-textfield { width: 100%; }
        .form-container {
            max-width: 400px;
            margin: 50px auto;
            padding: 20px;
            border: 1px solid #eee;
        }
    </style>
</head>
<body>
    <div class="form-container">
        <h2>Login Form</h2>
        <form action="/LoginForm" method="post">
            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <input class="mdl-textfield__input" type="text" id="employee_id" name="employee_id">
                <label class="mdl-textfield__label" for="employee_id">Your employee ID number: </label>
            </div>
            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <input class="mdl-textfield__input" type="password" id="originalPWD" name="originalPWD">
                <label class="mdl-textfield__label" for="originalPWD">Your password: </label>
                <span class="mdl-textfield__error">Invalid password</span>
            </div>
            <button type="submit" class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent">
                <i class="material-icons">lock_open</i> Login
            </button>
            <a href="/forms/RegistrationForm.jsp">
                <button type="button" class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent">
                     <i class="material-icons">lock_open</i> Register
                </button>
            </a>
        </form>
    </div>
</body>
</html>

