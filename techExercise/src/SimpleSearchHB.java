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
      String title;
      List<Rental> listRentals = null;
      boolean flagRent;
      if(keyword != null && !keyword.isEmpty())
      {
    	  keyword = keyword.trim();
    	  title = "Available Rentals";;
	      listRentals = UtilDBPeterson.listRentalsByCarMake(keyword);
	      flagRent = true;
      }
      else if(myRentals != null && !myRentals.isEmpty())
      {
    	  myRentals = myRentals.trim();
    	  title = "My Rentals";
	      listRentals = UtilDBPeterson.listRentalsByName(myRentals);
    	  flagRent = false;
      }
      else
      {
    	  title = "Available Rentals";
    	  flagRent = true;
    	  listRentals = UtilDBPeterson.listRentals();
      }
      String docType = "<!doctype html public \'-//w3c//dtd html 4.0 transitional//en\'><html><head><title>"+title+"</title><link rel='stylesheet' href='main.css'></head>";
      out.println(docType +
    		  "<body><header><nav><a href='/techExercise/simpleSearchHB.html'>&lt; Back to Search</a></nav><h1 class='accent'>"+title+"</h1></header><main>");
      display(listRentals, out, flagRent);
      out.println("</main></body></html>");
   }

   void display(List<Rental> listRentals, PrintWriter out, boolean flagRent) {
      for (Rental rent : listRentals) {
    	  out.println("<section class='accent result'>" + 
    		"<div class='imagebound' style='background-image:url(" + rent.getCarImage() + ");'></div>" +
    		"<h4>MAKE: " + rent.getCarMake() + "<h4>" +
    		"<h4>MODEL: " + rent.getCarModel() + "<h4>" + 
    		"<h4>YEAR: " + rent.getCarYear() + "<h4>" +
    		(flagRent?
    			"<a class='rentbutton' href=/" + projectName + "/rent/" + rent.getId() + ">Rent!</a>" : 
    			("<h4>Start Lease :" + rent.getStartDate() + "<h4>" +
    			  "<h4>End Lease :" + rent.getEndDate() + "<h4>" +
    	            "<form action=\"/techExercise/rent/" + rent.getId() + "?return=true\" method=\"POST\"> <input type=\"submit\" value=\"Return!\" /></form>"))
    		
    		+ "</section>");
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
