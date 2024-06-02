package views;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "GetFormServlet", value = "/GetFormServlet")
public class GetFormServlet extends HttpServlet {

//    @Override
//    public void init() throws ServletException {
//        super.init();
//        System.out.println("We are calling initialise method.");
//    }
//
//    @Override
//    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//        super.service(req, res);
//        System.out.println("We are calling the service() method.");
//    }
//
//    @Override
//    public void destroy(){
//        super.destroy();
//        System.out.println("We are now exiting the web.");
//    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html; charset=UTF-8");

        // Receive parameters from get request.
        String courseName = req.getParameter("courseName");
        String courseCredit = req.getParameter("credits");
        String courseProf = req.getParameter("profName");

        // Display the parameters.
        PrintWriter out = res.getWriter();
        out.println("<html><head><title>Get Form</title></head><body>");
        out.println("<h3> The course name is: "+ courseName +"</h3>");
        out.println("<h3> The credit of the course is: "+ courseCredit +"</h3>");
        out.println("<h3> The professor of this course is: "+ courseProf +"</h3>");
        out.println("</body></html>");
        }
}