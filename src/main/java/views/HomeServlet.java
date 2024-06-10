package views;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "HomeServlet", value ="/HomeServlet")
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");

        // Check if the employeeID exists in the session
        HttpSession session = req.getSession(false);

        if (session != null && session.getAttribute("employeeID") != null) {
            // Logged in
            req.getRequestDispatcher("/forms/Home.jsp").forward(req, res);
        } else {
            // Not Logged in
            res.sendRedirect("/forms/LoginForm.jsp");
        }
    }
}