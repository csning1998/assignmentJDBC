package views;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "TestServlet", value = "/testServlet")
public class TestServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("We are calling initialise method.");
    }

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        super.service(req, res);
        System.out.println("We are calling the service() method.");
    }

    @Override
    public void destroy(){
        super.destroy();
        System.out.println("We are now exiting the web.");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("<p> This is a test paragraph of the Servlet.</p>");
        out.println("</body>");
        out.println("</html>");
    }
}