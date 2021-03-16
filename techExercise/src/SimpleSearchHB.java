import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodel.Rental;
import util.Info;
import util.UtilDBPeterson;

@WebServlet("/SimpleSearchHB")
public class SimpleSearchHB extends HttpServlet implements Info {
   private static final long serialVersionUID = 1L;

   public SimpleSearchHB() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String keyword = request.getParameter("keyword").trim();

      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Database Result";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n"; //
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h1 align=\"center\">" + title + "</h1>\n");
      out.println("<ul>");

      List<Rental> listRentals = null;
      if (keyword != null && !keyword.isEmpty()) {
         listRentals = UtilDBPeterson.listRentalsByCarMake(keyword);
      } else {
         listRentals = UtilDBPeterson.listRentals();
      }
      display(listRentals, out);
      out.println("</ul>");
      out.println("<a href=/" + projectName + "/" + searchWebName + ">Search Data</a> <br>");
      out.println("</body></html>");
   }

   void display(List<Rental> listEmployees, PrintWriter out) {
      for (Rental rent : listEmployees) {
         System.out.println("[DBG] " + rent.getId() + ", " //
               + rent.getCarMake() + ", " //
               + rent.getCarModel() + ", " //
               + rent.getCarYear());

         out.println("<li>" + rent.getId() + ", " //
               + rent.getCarMake() + ", " //
               + rent.getCarModel() + ", " //
               + rent.getCarYear() + "</li>");
      }
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }
}
