//package views;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.nio.charset.StandardCharsets;
//import java.security.*;
//import java.sql.*;
//import java.util.Base64;
//
//import static java.lang.Class.forName;
//
//@WebServlet("/register")
//public class RegistrationFormServlet extends HttpServlet {
//
//    public void init() throws ServletException {
//        initializeJDBC();
//    }
//
//    @Override
//    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//
//        String employeeId = request.getParameter("employee_id");
//        String employeeName = request.getParameter("employee_name");
//        String password = request.getParameter("originalPWD");
//        String confirmPassword = request.getParameter("confirmPWD");
//
//        PrintWriter out = response.getWriter();
//        out.println("<html><head></head><body>");
//        out.println("<h3> Your 身份證 / ARC ID: " + employeeId + "</h3>");
//        out.println("<h3> Your name: " + employeeName + "</h3>");
//        out.println("<h3> Your password: " + password + "</h3>");
//        out.println("<h3> Your Confirm password: " + confirmPassword + "</h3>");
//    }
//
//    private void initializeJDBC() {
//        try {
//            Class.forName("org.postgresql.Driver");
//            System.out.println("Driver loaded...");
//
//            Connection conn = DriverManager.getConnection(
//                    "jdbc:postgresql://localhost:5433/postgres",
//                    "postgres",
//                    "postgres");
//            System.out.println("Database connected...");
//
//            preparedStatement = conn.prepareStatement(
//                    "insert into course (employee_id, employee_name, courseTeacher) values (?, ?, ?);");
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//    }
//
//    private static final String SIGNATURE_ALGORITHM = "SHA256withRSA";
//    private static final int KEY_SIZE = 2048;
//    private KeyPair keyPair;
//
//    public RegistrationFormServlet() throws NoSuchAlgorithmException {
//        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
//        keyPairGenerator.initialize(KEY_SIZE);
//        keyPair = keyPairGenerator.generateKeyPair();
//    }
//
//    private String hashPassword(String password) throws NoSuchAlgorithmException {
//        MessageDigest digest = MessageDigest.getInstance("SHA-256");
//        byte[] hashedBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));
//        return Base64.getEncoder().encodeToString(hashedBytes);
//    }
//    private byte[] signHash(byte[] hashedPassword) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
//        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
//        signature.initSign(keyPair.getPrivate());
//        signature.update(hashedPassword);
//        return signature.sign();
//    }
//
//    private void saveRegistration(String employeeID, String password, String ){
//        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            pstmt.setString(1, employeeName);
//            pstmt.setString(2, employeeId);
//            pstmt.setString(3, hashedPWD);
//            pstmt.setBytes(4, signature);
//            // Base64 是用來避免密碼在轉換過程中變成亂碼
//            pstmt.executeUpdate();
//        }
//
//        response.getWriter().println("Registration successful!");
//
//    } catch (SQLException | NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
//        e.printStackTrace(response.getWriter());
//    }
//    }
//
//
//
//}
