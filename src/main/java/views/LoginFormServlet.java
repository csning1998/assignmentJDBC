package views;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.sql.*;
import java.util.Base64;

public class LoginFormServlet extends HttpServlet {

    private static final String SIGNATURE_ALGORITHM = "SHA256withRSA";
    private static final int KEY_SIZE = 2048; // Use a stronger key size
    private KeyPair keyPair;

    public LoginFormServlet() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(KEY_SIZE);
        keyPair = keyPairGenerator.generateKeyPair();
    }

    // Hash algorithm
    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashedBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(hashedBytes); // Encode to Base64 for easier storage
    }

    // Encryption -> Sign the hash
    private byte[] signHash(byte[] hashedPassword) throws InvalidKeyException, SignatureException, NoSuchAlgorithmException {
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(keyPair.getPrivate());
        signature.update(hashedPassword);
        return signature.sign();
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html; charset=UTF-8");

        String employeeID = req.getParameter("employee_id");
        String originalPWD = req.getParameter("originalPWD");

        // Bring 'PrintWriter' out of scope of try catch as a global var of try-catch.
        PrintWriter out = null;
        try {
            String hashedPWD = hashPassword(originalPWD);
            byte[] signature = signHash(hashedPWD.getBytes(StandardCharsets.UTF_8)); // Sign the hash
            Boolean isLoginSucessful = checkLogin(employeeID, originalPWD);

            out = res.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html><head><title>Login Form</title></head><body>");
            out.println("<h1>Login Form</h1>");
            out.println("<p>employee_id: " + employeeID + "</p>");
            out.println("<p>originalPWD: " + originalPWD + "</p>");
            out.println("<p>Hashed Password: " + hashedPWD + "</p>");
            out.println("<p>Signature: " + Base64.getEncoder().encodeToString(signature) + "</p>");
            out.println("</body></html>");

        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            e.printStackTrace(out);
            System.out.println("Error");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean checkLogin(String employeeID, String originalPWD) throws SQLException, ClassNotFoundException {

        Boolean isLoginSuccessful = false;
        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://db:5432/postgres", "postgres", "postgres");
        System.out.println("Database connected...");

        String sql = "SELECT * FROM users WHERE employee_id = ? and originalPWD = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, employeeID);
        statement.setString(2, originalPWD);

        ResultSet result = statement.executeQuery();

        System.out.println("Result: " + result.toString());
        System.out.println("Login Check: " + isLoginSuccessful.toString());

        if (result.next()) {
//            user.setFullname(result.getString("originalPWD"));
            isLoginSuccessful = true;
            System.out.println("Login Check: " + isLoginSuccessful.toString());
        }
        conn.close();

        return isLoginSuccessful;
    }


}
