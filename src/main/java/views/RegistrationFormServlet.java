//package views;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
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
//
//    private byte[] signHash(byte[] hashedPassword) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
//        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
//        signature.initSign(keyPair.getPrivate());
//        signature.update(hashedPassword);
//        return signature.sign();
//    }
//
//    private void initialiseJDBC throws ServletException(){
//        try{
//            Class.forName("org.postgresql.Driver");
//            System.out.println("Driver loaded...");
//
//            Connection
//
//        } catch(){
//
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//
//        String employeeId = request.getParameter("employee_id");
//        String employeeName = request.getParameter("employee_name");
//        String password = request.getParameter("originalPWD");
//        String confirmPassword = request.getParameter("confirmPWD");
//
//        // 簡單的密碼確認
//        if (!password.equals(confirmPassword)) {
//            response.getWriter().println("Passwords do not match!");
//            return;
//        }
//
//        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/javadb", "postgres", "postgres")) {
//            String hashedPWD = hashPassword(password);
//            byte[] signature = signHash(hashedPWD.getBytes(StandardCharsets.UTF_8));
//
//            String sql = "INSERT INTO users (employee_id, employee_name, hashedPWD, signature) VALUES (1, 2, 3, 4)";
//            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
//                pstmt.setString(1, employeeId);
//                pstmt.setString(2, employeeName);
//                pstmt.setString(3, hashedPWD);
//                pstmt.setString(4, Base64.getEncoder().encodeToString(signature));
//                pstmt.executeUpdate();
//            }
//
//            response.getWriter().println("Registration successful!");
//
//        } catch (SQLException | NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
//            e.printStackTrace(response.getWriter());
//        }
//    }
//}
