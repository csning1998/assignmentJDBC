package views;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

@WebServlet(name = "PatientSummaryServlet", urlPatterns = "/PatientSummary")
public class PatientSummaryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html; charset=UTF-8");
        PrintWriter out = res.getWriter();

        HttpSession session = req.getSession(false); // Get existing session
        String employeeID = (String) session.getAttribute("employeeID");

        if (employeeID != null) {
            // Retrieve patient data from request attributes or session, assuming data is passed as attributes
            String patientName = req.getAttribute("patientName").toString();
            String patientIdentifier = req.getAttribute("patientIdentifier").toString();
            String patientBirthdate = req.getAttribute("patientBirthdate").toString();
            String patientGender = req.getAttribute("patientGender").toString();
            String phoneNumber = req.getAttribute("phoneNumber").toString();
            String patientAddress = req.getAttribute("patientAddress").toString();
            String patientHeight = req.getAttribute("patientHeight").toString();
            String patientWeight = req.getAttribute("patientWeight").toString();
            String firstVisitDate = req.getAttribute("firstVisitDate").toString();
            String personalHistory = req.getAttribute("personalHistory").toString();
            String familyHistory = req.getAttribute("familyHistory").toString();
            String medicalRecord = req.getAttribute("medicalRecord").toString();

            System.out.println("---------");
            Arrays.asList(
                    "patientName" + patientName,
                    "patientIdentifier" + patientIdentifier,
                    "---------"
            ).forEach(System.out::println);

            out.println("<html><body>");
            out.println("<h2>Patient Medical Summary</h2>");
            out.println("<table>");
            Arrays.asList(
                    "<tr><th>Field</th><th>Value</th></tr>",
                    "<tr><td>Full Name</td><td>" + patientName + "</td></tr>",
                    "<tr><td>National ID Number</td><td>" + patientIdentifier + "</td></tr>",
                    "<tr><td>Birthdate</td><td>" + patientBirthdate + "</td></tr>",
                    "<tr><td>Gender</td><td>" + patientGender + "</td></tr>",
                    "<tr><td>Phone Number</td><td>" + phoneNumber + "</td></tr>",
                    "<tr><td>Address</td><td>" + patientAddress + "</td></tr>",
                    "<tr><td>Height (cm)</td><td>" + patientHeight + "</td></tr>",
                    "<tr><td>Weight (kg)</td><td>" + patientWeight + "</td></tr>",
                    "<tr><td>First Diagnosis Date</td><td>" + firstVisitDate + "</td></tr>",
                    "<tr><td>Patient History</td><td>" + personalHistory + "</td></tr>",
                    "<tr><td>Family History</td><td>" + familyHistory + "</td></tr>",
                    "<tr><td>Diagnosis History</td><td>" + medicalRecord + "</td></tr>",
                    "</table>",
                    "</body></html>"
            ).forEach(out::println);

            out.println("<button onclick=\"logout()\">Logout</button>");
            // Call LogoutServlet
            Arrays.asList(
                    "<script>function logout() {",
                    "    window.location.href = '/LogoutServlet';",
                    "}</script>",
                    "</body></html>"
            ).forEach(out::println);
        } else {
            res.sendRedirect("/forms/LoginForm.jsp");
        }


    }
}
