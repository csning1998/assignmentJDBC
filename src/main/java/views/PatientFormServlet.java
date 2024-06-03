package views;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "PatientFormServlet", value = "/PatientFormServlet")
public class PatientFormServlet extends HttpServlet {

    private PreparedStatement sql;

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html; charset=UTF-8");

        String patientName = req.getParameter("patientName");
        String patientIdentifier = req.getParameter("patientIdentifier");
        String patientBirthdate = req.getParameter("birthdate");
        String patientGender = req.getParameter("gender");
        String phoneNumber = req.getParameter("phoneNumber");
        String patientAddress = req.getParameter("address");
        String patientHeight = req.getParameter("height");
        String patientWeight = req.getParameter("weight");
        String firstVisitDate = req.getParameter("firstVisitDate");
        String personalHistory = req.getParameter("personalHistory");
        String familyHistory = req.getParameter("familyHistory");
        String medicalRecord = req.getParameter("medicalRecord");

        PrintWriter out = res.getWriter();

        // Form is empty check according to SQL query console
        if (patientName == null || patientName.isEmpty() ||
                patientIdentifier == null || patientIdentifier.isEmpty() ||
                patientBirthdate == null || patientBirthdate.isEmpty() ||
                patientGender == null || patientGender.isEmpty() ||
                patientAddress == null || patientAddress.isEmpty() ||
                patientHeight == null || patientHeight.isEmpty() ||
                patientWeight == null || patientWeight.isEmpty() ||
                firstVisitDate == null || firstVisitDate.isEmpty()) {

            out.println("<html><body><p>Re-examine if you filled all the forms.</p></body></html>");
            return;
        }

        try {
            initializeJDBC();
            saveMedicalRecord(patientName, patientIdentifier, patientBirthdate, patientGender,
                    phoneNumber, patientAddress, patientHeight, patientWeight,
                    firstVisitDate, personalHistory, familyHistory, medicalRecord);

            // redirect to PatientSummary.jsp
            res.sendRedirect(req.getContextPath() + "/forms/PatientSummary.jsp");

        } catch (Exception e) {
            out.println("<html><body><p>資料插入失敗：" + e.getMessage() + "</p></body></html>");
        }
    }

    private void initializeJDBC() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        System.out.println("Driver loaded...");

        Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://db:5432/postgres", "postgres", "postgres");
        System.out.println("Database connected...");

        sql = conn.prepareStatement(
                "insert into medical_record (patient_name, patient_identifier, patient_birthdate, patient_gender, " +
                        "phone_number, patient_address, patient_height, patient_weight, " +
                        "first_visit_date, family_history, personal_history, medical_record) " +
                        "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
    }

    private void saveMedicalRecord(String patientName, String patientIdentifier, String patientBirthdate, String patientGender,
                                   String phoneNumber, String patientAddress, String patientHeight, String patientWeight,
                                   String firstVisitDate, String personalHistory, String familyHistory, String medicalRecord) throws SQLException {

        sql.setString(1, patientName);
        sql.setString(2, patientIdentifier);
        sql.setDate(3, java.sql.Date.valueOf(patientBirthdate));
        sql.setString(4, patientGender);
        sql.setString(5, phoneNumber);
        sql.setString(6, patientAddress);
        sql.setBigDecimal(7, new java.math.BigDecimal(patientHeight));
        sql.setBigDecimal(8, new java.math.BigDecimal(patientWeight));
        sql.setDate(9, java.sql.Date.valueOf(firstVisitDate));
        sql.setString(10, familyHistory);
        sql.setString(11, personalHistory);
        sql.setString(12, medicalRecord);

        sql.executeUpdate();
    }
}
