package views;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.security.*;
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

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html; charset=UTF-8");

        String studentID = req.getParameter("employee_id");
        String originalPWD = req.getParameter("originalPWD");

        // Bring 'PrintWriter' out of scope of try catch as a global var of try-catch.
        PrintWriter out = null;
        try {
            String hashedPWD = hashPassword(originalPWD);
            byte[] signature = signHash(hashedPWD.getBytes(StandardCharsets.UTF_8)); // Sign the hash

            // Now you can store the studentID, hashedPWD, and signature in your database

            out = res.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html><head><title>Login Form</title></head><body>");
            out.println("<h1>Login Form</h1>");
            out.println("<p>employee_id: " + studentID + "</p>");
            out.println("<p>originalPWD: " + originalPWD + "</p>");
            out.println("<p>Hashed Password: " + hashedPWD + "</p>");
            out.println("<p>Signature: " + Base64.getEncoder().encodeToString(signature) + "</p>");
            out.println("</body></html>");

        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            e.printStackTrace(out);
            System.out.println("Error");
        }
    }
}
