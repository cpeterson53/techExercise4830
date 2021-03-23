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
//      UtilDBPeterson.createRental("Honda", "Civic", 2018 , "https://cars.usnews.com/static/images/Auto/izmo/i41643682/2018_honda_civic_hatchback_angularfront.jpg");
//      UtilDBPeterson.createRental("Ford", "Escape", 2020 , "https://www.ford.com/cmslibs/content/dam/vdm_ford/live/en_us/ford/nameplate/escape/2021/collections/3-2/21_frd_esp_s_crgr_ps34_356x180.png/_jcr_content/renditions/cq5dam.web.1280.1280.png");      
      
//      UtilDBPeterson.createRental("Ford", "Focus", 2019 , "https://blogmedia.dealerfire.com/wp-content/uploads/sites/275/2018/05/side-view-of-a-tan-Asian-2019-Ford-Focus-Sedan_o.jpg");
//      UtilDBPeterson.createRental("Jeep", "Grand Cherokee", 2020 , "https://di-uploads-pod20.dealerinspire.com/midstatechryslerdodgejeepram/uploads/2019/12/20Jeep-GrandCherokee-Jellybean-Limited-VelvetRed.png");
//      UtilDBPeterson.createRental("Ford", "Ranger", 2021 , "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcTrFgyHTYNIR_tsy8ZyUleZN_TXq81g8PiY9f2HjVYNDSYAShj6");
//      UtilDBPeterson.createRental("Jeep ", "Liberty", 2018 , "https://www.iihs.org/api/ratings/model-year-images/1779");
      
//      UtilDBPeterson.createRental("Chevy", "Tahoe", 2021 , "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcSOV5j9K9yrvt6Axohht6itFgBlNy2cHvtAxpiRt3B-VJiaSM30");
//      UtilDBPeterson.createRental("Honda", "Odyssey", 2021 , "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcSwASpgVXVTgVAf4J6N4uynUuQ10eFZosjaZZvFPtzOykh_ZOZO");
//      UtilDBPeterson.createRental("Buick", "Lasabre", 2019 , "https://kelwaysvillage.com/2020-buick-lesabre/65-best-review-2020-buick-lesabre-spy-shoot-with-2020-buick-lesabre/");
//      UtilDBPeterson.createRental("Tesla", "T-Sportline", 2019 , "https://www.tuningblog.eu/wp-content/uploads/2018/03/T-Sportline-Tesla-Model-X-Carbon-Bodykit-Tuning-1.jpg");
//      
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
