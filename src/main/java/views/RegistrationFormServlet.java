package views;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.sql.*;
import java.util.Base64;

@WebServlet(name = "RegistrationFormServlet", value = "/RegistrationFormServlet")
public class RegistrationFormServlet extends HttpServlet {

    private PreparedStatement sql;

    @Override
    public void init() {
        initializeJDBC();
        try {
            initializeKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static final String SIGNATURE_ALGORITHM = "SHA256withRSA";
    private static final int KEY_SIZE = 2048;
    private static KeyPair keyPair;

    private void initializeKeyPair() throws NoSuchAlgorithmException {
        if (keyPair == null) {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(KEY_SIZE);
            keyPair = keyPairGenerator.generateKeyPair();
        }
    }

    public RegistrationFormServlet() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(KEY_SIZE);
        keyPair = keyPairGenerator.generateKeyPair();
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashedBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(hashedBytes);
    }
    private byte[] signHash(byte[] hashedPassword) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(keyPair.getPrivate());
        signature.update(hashedPassword);
        return signature.sign();
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html;charset=UTF-8");

        String employeeID = req.getParameter("employee_id");
        String employeeEmail = req.getParameter("employee_email");
        String originalPWD = req.getParameter("originalPWD");
        String confirmPWD = req.getParameter("confirmPWD");
        String employeeName = req.getParameter("employee_name");

        PrintWriter out = res.getWriter();

        // Check if the password are matched, return if it's failed.
        if (!originalPWD.equals(confirmPWD)){
            out.println("<html><body>");
            out.println("<h3> Password is not matched!</h3>");
            out.println("<body></html>");
            return;
        }
        try{
            String hashedPWD = hashPassword(originalPWD);
            byte[] signature = signHash(hashedPWD.getBytes(StandardCharsets.UTF_8));

            // Follow the correction instructions given by Intellij IDEA.
            saveRegistration(employeeEmail, employeeID, employeeName, hashedPWD, Base64.getEncoder().encodeToString(signature));
            out.println("<!DOCTYPE html>");
            out.println("<html><head><title>Registration Form</title></head><body>");
            out.println("<h1>Registration Successful</h1>");
            out.println("</body></html>");

        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException | SQLException err){
            err.printStackTrace();
            out.println("<html><body>");
            out.println("<h3> (500) Unexpected error occurred</h3>");
            out.println("<body></html>");
        }


        out.println("<!DOCTYPE html>");
        out.println("<html><head><title>Registration Form</title></head><body>");
        out.println("<h1>Registration Form</h1>");
        out.println("<h3> Your 身份證 / ARC ID: " + employeeID + "</h3>");
        out.println("<h3> Your email: " + employeeEmail + "</h3>");
        out.println("<h3> Your name: " + employeeName + "</h3>");
        out.println("<h3> Your password: " + originalPWD + "</h3>");
        out.println("<h3> Your Confirm password: " + confirmPWD + "</h3>");
        out.println("</body></html>");
        out.close();
    }

    private void initializeJDBC() {
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Driver loaded...");

            Connection conn = DriverManager.getConnection(
                    "jdbc:postgresql://db:5432/postgres", "postgres", "postgres");
            System.out.println("Database connected...");

            sql = conn.prepareStatement(
                    "insert into users (employee_email, employee_name, employee_id, hashedPWD, signature) values (?, ?, ?, ?, ?);");

        } catch (ClassNotFoundException | SQLException err) {
            err.printStackTrace();
            throw new RuntimeException(err);
        }
    }

    private void saveRegistration(String employeeID, String employeeEmail, String employeeName, String hashedPWD, String signature) throws SQLException {
        try {
            sql.setString(1, employeeID);
            sql.setString(2, employeeName);
            sql.setString(3, employeeEmail);
            sql.setString(4, hashedPWD);
            sql.setString(5, signature);
            // Base64 是用來避免密碼在轉換過程中變成亂碼
            sql.executeUpdate();
        } catch (SQLException err) {
             err.printStackTrace();
             throw err;
        }
    }
}
