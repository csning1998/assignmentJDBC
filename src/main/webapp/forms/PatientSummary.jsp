<jsp:useBean id="gender" scope="request" type=""/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html>
<head>
    <title>病患病歷資料</title>
    <style>
        body {
            font-family: sans-serif;
        }
        .container {
            max-width: 600px;
            margin: 20px auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        h2 {
            text-align: center;
            margin-bottom: 20px;
        }
        .data-table {
            width: 100%;
            border-collapse: collapse;
        }
        .data-table th,
        .data-table td {
            padding: 10px;
            border-bottom: 1px solid #ddd;
        }
        .data-table th {
            text-align: left;
            background-color: #f2f2f2;
        }
        .field-name {
            font-weight: bold;
        }
        .field-value {
            margin-left: 10px;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Patient Medical Summary</h2>

    <table class="data-table">
        <tr>
            <th>Field</th>
            <th>Value</th>
        </tr>
        <tr>
            <td class="field-name">Full Name</td>
            <td class="field-value">${patientName}</td>
        </tr>
        <tr>
            <td class="field-name">National ID Number</td>
            <td class="field-value">${patientIdentifier}</td>
        </tr>
        <tr>
            <td class="field-name">Birthdate</td>
            <td class="field-value">${birthdate}</td>
        </tr>
        <tr>
            <td class="field-name">Gender</td>
            <td class="field-value">${gender}</td>
        </tr>
        <tr>
            <td class="field-name">Phone Number</td>
            <td class="field-value">${phoneNumber}</td>
        </tr>
        <tr>
            <td class="field-name">Address</td>
            <td class="field-value">${address}</td>
        </tr>
        <tr>
            <td class="field-name">Height (cm)</td>
            <td class="field-value">${height}</td>
        </tr>
        <tr>
            <td class="field-name">Weight (kg)</td>
            <td class="field-value">${weight}</td>
        </tr>
        <tr>
            <td class="field-name">First Diagnosis Date</td>
            <td class="field-value">${firstVisitDate}</td>
        </tr>
        <tr>
            <td class="field-name">Patient History</td>
            <td class="field-value">${personalHistory}</td>
        </tr>
        <tr>
            <td class="field-name">Family History</td>
            <td class="field-value">${familyHistory}</td>
        </tr>
        <tr>
            <td class="field-name">Diagnosis History</td>
            <td class="field-value">${medicalRecord}</td>
        </tr>
    </table>
</div>
</body>
</html>
