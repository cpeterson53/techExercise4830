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
      String keyword = request.getParameter("keyword");
      //Second Search Parameter
      String myRentals = request.getParameter("myRentals");
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title, color;
      List<Rental> listRentals = null;
      boolean flagRent;
      if(keyword != null && !keyword.isEmpty())
      {
    	  keyword = keyword.trim();
    	  title = "Available Rentals";
    	  color = "\"#f0f0f0\"";
	      listRentals = UtilDBPeterson.listRentalsByCarMake(keyword);
	      flagRent = true;
      }
      else if(myRentals != null && !myRentals.isEmpty())
      {
    	  myRentals = myRentals.trim();
    	  title = "My Rentals";
    	  color = "\"#fff\"";
	      listRentals = UtilDBPeterson.listRentalsByName(myRentals);
    	  flagRent = false;
      }
      else
      {
    	  title = "Available Rentals";
    	  color = "\"#f0f0f0\"";
    	  flagRent = true;
    	  listRentals = UtilDBPeterson.listRentals();
      }
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n"; //
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=" + color + ">\n" + //
            "<h1 align=\"center\">" + title + "</h1>\n");
      display(listRentals, out, flagRent);
      out.println("<a href=/" + projectName + "/" + searchWebName + ">Search Data</a> <br>");
      out.println("</body></html>");
   }

   void display(List<Rental> listRentals, PrintWriter out, boolean flagRent) {
      for (Rental rent : listRentals) {
    	  out.println("<section>" + 
    		"<img src=" + rent.getCarImage() + "></img>" +
    		"<h4>MAKE:" + rent.getCarMake() + "<h4><br>" +
    		"<h4>MODEL:" + rent.getCarModel() + "<h4><br>" + 
    		"<h4>YEAR:" + rent.getCarYear() + "<h4><br>" +
    		(flagRent?
    			"<a href=/" + projectName + "/rent/" + rent.getId() + ">Rent!</a>" : 
    			"<form action=\"/techExercise/rent/" + rent.getId() + "?return=true\" method=\"POST\"> <input type=\"submit\" value=\"Return!\" /></form>"));
//         System.out.println("[DBG] " + rent.getId() + ", " //
//               + rent.getCarMake() + ", " //
//               + rent.getCarModel() + ", " //
//               + rent.getCarYear());
//
//         out.println("<li>" + rent.getId() + ", " //
//               + rent.getCarMake() + ", " //
//               + rent.getCarModel() + ", " //
//               + rent.getCarYear() + "</li>");
      }
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }
}
