<%--
  Created by IntelliJ IDEA.
  User: janua
  Date: 2024/6/3
  Time: 09:30
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
        .mdl-textfield {
            width: 100%;
            margin-bottom: 16px;
        }
        .form-container {
            max-width: 600px;
            margin: 50px auto;
            padding: 20px;
            border: 1px solid #eee;
            box-sizing: border-box;
        }
        .full-width {
            width: 100%;
        }

        .mdl-textfield__label {
            top: -12px;
            font-size: 12px;
        }
        .mdl-textfield.is-dirty .mdl-textfield__label {
            top: -24px;
        }

    </style>
</head>
<body>
<div class="form-container">
    <h3>Clinical Medical Record System</h3>
    <form action="/PatientForm" method="post">
        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label full-width">
            <input class="mdl-textfield__input" type="text" id="patientName" name="patientName" required>
            <label class="mdl-textfield__label" for="patientName">Full Name</label>
        </div>

        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label full-width">
            <input class="mdl-textfield__input" type="text" id="patientIdentifier" name="patientIdentifier" required>
            <label class="mdl-textfield__label" for="patientIdentifier">National ID Number</label>
        </div>

        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label full-width">
            <input class="mdl-textfield__input" type="date" id="birthdate" name="birthdate" required>
            <label class="mdl-textfield__label" for="birthdate">Birthdate (yyyy/mm/dd)</label>
        </div>

        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label full-width">
            <select class="mdl-textfield__input" id="gender" name="gender" required>
                <option value="">Select Physiological Gender</option>
                <option value="male">Male</option>
                <option value="female">Female</option>
            </select>
            <label class="mdl-textfield__label" for="gender">Gender</label>
        </div>

        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label full-width">
            <input class="mdl-textfield__input" type="tel" id="phoneNumber" name="phoneNumber">
            <label class="mdl-textfield__label" for="phoneNumber">Phone Number</label>
        </div>

        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label full-width">
            <textarea class="mdl-textfield__input" rows="3" id="address" name="address"></textarea>
            <label class="mdl-textfield__label" for="address">Address</label>
        </div>

        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label full-width">
            <input class="mdl-textfield__input" type="number" id="height" name="height" required>
            <label class="mdl-textfield__label" for="height">Height (cm)</label>
        </div>

        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label full-width">
            <input class="mdl-textfield__input" type="number" id="weight" name="weight" required>
            <label class="mdl-textfield__label" for="weight">Weight (kg)</label>
        </div>

        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label full-width">
            <input class="mdl-textfield__input" type="date" id="firstVisitDate" name="firstVisitDate" required>
            <label class="mdl-textfield__label" for="firstVisitDate">First Diagnosis Date</label>
        </div>

        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label full-width">
            <textarea class="mdl-textfield__input" type="text" rows="3" id="personalHistory" name="personalHistory"></textarea>
            <label class="mdl-textfield__label" for="personalHistory">Patient History</label>
        </div>

        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label full-width">
            <textarea class="mdl-textfield__input" type="text" rows="3" id="familyHistory" name="familyHistory"></textarea>
            <label class="mdl-textfield__label" for="familyHistory">Family History</label>
        </div>

        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label full-width">
            <textarea class="mdl-textfield__input" type="text" rows="5" id="medicalRecord" name="medicalRecord"></textarea>
            <label class="mdl-textfield__label" for="medicalRecord">Diagnosis History</label>
        </div>

        <button type="submit" class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent">
            Submit
        </button>
    </form>
</div>
</body>
</html>

