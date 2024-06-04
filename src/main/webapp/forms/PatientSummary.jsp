<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css">
    <script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>
    <title>Patient's Diagnosis Summary</title>
    <style>
        body {
            font-family: Roboto, sans-serif;
        }
        .container {
            max-width: 600px;
            margin: 20px auto;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 2px 0 rgba(0,0,0,.14), 0 3px 1px -2px rgba(0,0,0,.12), 0 1px 5px 0 rgba(0,0,0,.2);
        }
        h2 {
            text-align: center;
            margin-bottom: 20px;
        }
        .data-table {
            width: 100%;
        }
        .data-table td {
            padding: 10px 0;
            border-bottom: 1px solid #ddd;
            vertical-align: top;
        }
        .field-name {
            font-weight: bold;
            width: 40%;
            display: inline-block;
        }
        .field-value {
            width: 60%;
            display: inline-block;
        }
        .material-icons {
            vertical-align: middle;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Patient Medical Summary</h2>

    <table class="data-table">
        <thead>
            <tr>
                <th class="mdl-data-table__cell--non-numeric">Field</th>
                <th class="mdl-data-table__cell--non-numeric">Value</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td class="mdl-data-table__cell--non-numeric"><i class="material-icons">person</i>  Full Name</td>
                <td class="mdl-data-table__cell--non-numeric">${patientName}</td>
            </tr>
            <tr>
                <td class="mdl-data-table__cell--non-numeric"><i class="material-icons">assignment_ind</i>  National ID Number</td>
                <td class="mdl-data-table__cell--non-numeric">${patientIdentifier}</td>
            </tr>
            <tr>
                <td class="mdl-data-table__cell--non-numeric"><i class="material-icons">cake</i>  Birthdate</td>
                <td class="mdl-data-table__cell--non-numeric">${patientBirthdate}</td>
            </tr>
            <tr>
                <td class="mdl-data-table__cell--non-numeric"><i class="material-icons">wc</i>  Gender</td>
                <td class="mdl-data-table__cell--non-numeric">${patientGender}</td>
            </tr>
            <tr>
                <td class="mdl-data-table__cell--non-numeric"><i class="material-icons">phone</i>  Number</td>
                <td class="mdl-data-table__cell--non-numeric">${phoneNumber}</td>
            </tr>
            <tr>
                <td class="mdl-data-table__cell--non-numeric"><i class="material-icons">home</i>  Address</td>
                <td class="mdl-data-table__cell--non-numeric">${patientAddress}</td>
            </tr>
            <tr>
                <td class="mdl-data-table__cell--non-numeric"><i class="material-icons">height</i>  Height (cm)</td>
                <td class="mdl-data-table__cell--non-numeric">${patientHeight}</td>
            </tr>
            <tr>
                <td class="mdl-data-table__cell--non-numeric"><i class="material-icons">fitness_center</i>  Weight (kg)</td>
                <td class="mdl-data-table__cell--non-numeric">${patientWeight}</td>
            </tr>
            <tr>
                <td class="mdl-data-table__cell--non-numeric"><i class="material-icons">event_note</i>  First Diagnosis Date</td>
                <td class="mdl-data-table__cell--non-numeric">${firstVisitDate}</td>
            </tr>
            <tr>
                <td class="mdl-data-table__cell--non-numeric"><i class="material-icons">description</i>  Patient History</td>
                <td class="mdl-data-table__cell--non-numeric">${personalHistory}</td>
            </tr>
            <tr>
                <td class="mdl-data-table__cell--non-numeric"><i class="material-icons">groups</i>  Family History</td>
                <td class="mdl-data-table__cell--non-numeric">${familyHistory}</td>
            </tr>
            <tr>
                <td class="mdl-data-table__cell--non-numeric"><i class="material-icons">receipt</i>  Diagnosis History</td>
                <td class="mdl-data-table__cell--non-numeric">${medicalRecord}</td>
            </tr>
        </tbody>
    </table>
    <a href="/forms/PatientForm.jsp">
        <button type="button" class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent">
            <i class="material-icons">arrow_back</i>Back to Form
        </button>
    </a>
</div>
</body>
</html>
