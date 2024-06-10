package views;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "PatientSummaryServlet", urlPatterns = "/PatientSummary")
public class PatientSummaryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html; charset=UTF-8");
        PrintWriter out = res.getWriter();

        // Retrieve patient data from request attributes or session, assuming data is passed as attributes
        String patientName = (String) req.getAttribute("patientName");
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
        System.out.println("patientName" + patientName);
        System.out.println("patientIdentifier" + patientIdentifier);
        System.out.println("---------");

        out.println("<html><body>");
        out.println("<h2>Patient Medical Summary</h2>");
        out.println("<table>");
        out.println("<tr><th>Field</th><th>Value</th></tr>");
        out.println("<tr><td>Full Name</td><td>" + patientName + "</td></tr>");
        out.println("<tr><td>National ID Number</td><td>" + patientIdentifier + "</td></tr>");
        out.println("<tr><td>Birthdate</td><td>" + patientBirthdate + "</td></tr>");
        out.println("<tr><td>Gender</td><td>" + patientGender + "</td></tr>");
        out.println("<tr><td>Phone Number</td><td>" + phoneNumber + "</td></tr>");
        out.println("<tr><td>Address</td><td>" + patientAddress + "</td></tr>");
        out.println("<tr><td>Height (cm)</td><td>" + patientHeight + "</td></tr>");
        out.println("<tr><td>Weight (kg)</td><td>" + patientWeight + "</td></tr>");
        out.println("<tr><td>First Diagnosis Date</td><td>" + firstVisitDate + "</td></tr>");
        out.println("<tr><td>Patient History</td><td>" + personalHistory + "</td></tr>");
        out.println("<tr><td>Family History</td><td>" + familyHistory + "</td></tr>");
        out.println("<tr><td>Diagcnosis History</td><td>" + medicalRecord + "</td></tr>");
        out.println("</table>");
        out.println("</body></html>");
    }
}
