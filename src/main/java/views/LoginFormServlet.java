package views;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.sql.*;
import java.util.Arrays;
import java.util.Base64;

public class LoginFormServlet extends HttpServlet {

    private static final int KEY_SIZE = 2048; // Use a stronger key size
    private static KeyPair keyPair;

    static {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(KEY_SIZE);
            keyPair = keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

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

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html; charset=UTF-8");

        String employeeID = req.getParameter("employee_id");
        String originalPWD = req.getParameter("originalPWD");

        // Bring 'PrintWriter' out of scope of try catch as a global var of try-catch.
        PrintWriter out = res.getWriter();
        try {
            boolean isLoginSuccessful = checkLogin(employeeID, originalPWD);

            if (isLoginSuccessful) {
                // Set up the session
                HttpSession session = req.getSession();
                session.setAttribute("employeeID", employeeID);
                session.setMaxInactiveInterval(360 * 60);

                // Implement JavaScript's alert('') function.
                // The IDE suggests using String.join(Arrays.asList()) method.
                // and then refer to the line below of using JavaScript in this case.
                // Ref: https://www.sitepoint.com/community/t/servlet-and-javascript/75233
                out.println("<script>");
                Arrays.asList(
                        "  alert('Login Successful');",
                        "  window.location.href = '/forms/Home.jsp';").forEach(out::println);
            } else {
                // Login Error
                out.println("<script>");
                // Correct the form name
                Arrays.asList(
                        "  alert('Invalid username / password. \\n You may recheck your username and password.');",
                        "  window.location.href = '/forms/LoginForm.jsp';").forEach(out::println);
            }
            out.println("</script>");

        } catch (SQLException | ClassNotFoundException e) {
            out.print(String.join("\n", Arrays.asList(
                    "<script>",
                    "window.onload = function() {",
                    "    alert('(500 Unexpected error occurred).');",
                    "    setTimeout(function() {",
                    "        window.location.href = '/forms/LoginFrom.jsp';",
                    "    }, 500);",
                    "}",
                    "</script>")
            ));
        }
    }

    // IDEA Suggests adding @SuppressWarning:
    @SuppressWarnings("ThrowablePrintedToSystemOut")
    private Boolean checkLogin(String employeeID, String originalPWD) throws SQLException, ClassNotFoundException {

        boolean isLoginSuccessful = false;

        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Driver loaded...");
            Connection conn = DriverManager.getConnection(
                    "jdbc:postgresql://db:5432/postgres", "postgres", "postgres");
            System.out.println("Database connected...");

            PreparedStatement sql = conn.prepareStatement(
                    "SELECT * FROM users WHERE employee_id = ? and hashedPWD  = ?");
            sql.setString(1, employeeID);
            String hashedPWD = hashPassword(originalPWD);
            sql.setString(2, hashedPWD);

            ResultSet result = sql.executeQuery();

            System.out.println("Result: " + result.toString());

            if (result.next()) {
                // user.setFullname(result.getString("originalPWD"));
                isLoginSuccessful = true;
                System.out.println("Login Check: " + isLoginSuccessful);
            }
            conn.close();
        } catch (ClassNotFoundException | SQLException | NoSuchAlgorithmException err){
            System.out.println(err);
        }
        return isLoginSuccessful;
    }
}
