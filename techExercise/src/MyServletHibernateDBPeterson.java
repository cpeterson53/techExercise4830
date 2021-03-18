import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodel.Rental;
import util.UtilDBPeterson;

@WebServlet("/MyServletHibernateDBPeterson")
public class MyServletHibernateDBPeterson extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public MyServletHibernateDBPeterson() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.setContentType("text/html");

      // #1
      UtilDBPeterson.createRental("Honda", "Civic", 2018 , "https://cars.usnews.com/static/images/Auto/izmo/i41643682/2018_honda_civic_hatchback_angularfront.jpg");
      UtilDBPeterson.createRental("Ford", "Escape", 2020 , "https://www.ford.com/cmslibs/content/dam/vdm_ford/live/en_us/ford/nameplate/escape/2021/collections/3-2/21_frd_esp_s_crgr_ps34_356x180.png/_jcr_content/renditions/cq5dam.web.1280.1280.png");      
      // #2
      retrieveDisplayData(response.getWriter());
   }

   void retrieveDisplayData(PrintWriter out) {
      String title = "Database Result";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + //
            "transitional//en\">\n"; //
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h1 align=\"center\">" + title + "</h1>\n");
      out.println("<ul>");
      List<Rental> listRentals = UtilDBPeterson.listRentals();
      for (Rental rental : listRentals) {
         System.out.println("[DBG] " + rental.getId() + ", " //
               + rental.getCarMake() + ", " //
               + rental.getCarModel() + ", " //
               + rental.getCarYear());

         out.println("<li>" + rental.getId() + ", " //
                 + rental.getCarMake() + ", " //
                 + rental.getCarModel() + ", " //
               + rental.getCarYear() + "</li>");
      }
      out.println("</ul>");
      out.println("</body></html>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }
}
