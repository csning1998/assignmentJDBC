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
    <title>GET Form</title>
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
    <h2>Course Information</h2>
    <form action="http://localhost:8082/form-get" method="get">
        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
            <input class="mdl-textfield__input" type="text" id="courseName" name="courseName">
            <label class="mdl-textfield__label" for="courseName">Course Name</label>
        </div>
        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
            <input class="mdl-textfield__input" type="number" id="credits" name="credits">
            <label class="mdl-textfield__label" for="credits">Credits</label>
        </div>
        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
            <input class="mdl-textfield__input" type="text" id="profName" name="profName">
            <label class="mdl-textfield__label" for="profName">Professor</label>
        </div>

        <button type="submit" class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent">
            <i class="material-icons">send</i> Submit
        </button>
    </form>
</div>
</body>
</html>

