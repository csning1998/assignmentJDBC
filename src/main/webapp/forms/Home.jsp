<%--
  Created by IntelliJ IDEA.
  User: C.S. Ning
  Date: 2024/6/3
  Time: 08:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Some Clinical Medical Record System</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css">
    <script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>
    <style>
        .mdl-layout__content {
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 300px;
        }

        .button-container {
            display: flex;
            gap: 20px;
        }
    </style>
</head>
<body>
<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
    <header class="mdl-layout__header">
        <div class="mdl-layout__header-row">
            <span class="mdl-layout-title">Clinical Medical Record Query System</span>
            <div class="mdl-layout-spacer"></div>
                <% String employeeID = (String) session.getAttribute("employeeID"); %>
                <% if (employeeID != null) { %>
                   Welcome, <%= employeeID %>!
                <a href="/LogoutServlet">
                    <button class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect">
                        Logout
                    </button>
                </a>
                <% } %>
    </header>
    <main class="mdl-layout__content">
        <div class="button-container">
            <a href="/forms/PatientForm.jsp">
                <button class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent">
                    <i class="material-icons">person_add</i> Insert new medical record
                </button>
            </a>

            <a href="/forms/PatientSummary.jsp">
                <button class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent">
                    <i class="material-icons">search</i> Query a medical record
                </button>
            </a>
        </div>
    </main>
</div>
</body>
</html>

