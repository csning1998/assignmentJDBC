<%--
  Created by IntelliJ IDEA.
  User: janua
  Date: 2024/6/2
  Time: 12:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.security.MessageDigest" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<%@ page import="java.util.Base64" %>

<!DOCTYPE html>
<html>
<head>
    <title>Registration Form</title>
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
    <h2>Registration</h2>
    <form action="register" method="post">

        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
            <input class="mdl-textfield__input" type="text" id="studentID" name="studentID">
            <label class="mdl-textfield__label" for="studentID">Your student ID number: </label>
        </div>

        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
            <input class="mdl-textfield__input" type="password" id="password" name="password">
            <label class="mdl-textfield__label" for="password">Password</label>
        </div>

        <button type="submit" class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent">
            <i class="material-icons">person_add</i> Register
        </button>
    </form>
</div>
</body>
</html>

