import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodel.Rental;
import util.Info;
import util.UtilDBPeterson;

@WebServlet("/rent/*")
public class SimpleInsertHB extends HttpServlet implements Info {
   private static final long serialVersionUID = 1L;

   public SimpleInsertHB() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      

	  String carID = request.getPathInfo();
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      BufferedReader reader;
      try {
    	  reader = new BufferedReader(new FileReader(getServletContext().getRealPath("/simpleInsertHB.html")));
    	  String line = reader.readLine();
    	  while(line != null)
    	  {
    		  //%%postto%%
    		  out.println(line.replaceAll("%%postto%%", carID));
    		  line = reader.readLine();  
    	  }
      } catch(IOException e)
      {
    	  e.printStackTrace();
      }
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   
	   String carID = request.getPathInfo().substring(1);
	   Rental rental = UtilDBPeterson.getRentalById(carID);   
	   if(request.getParameter("return") == null)
	   {
		   
		   String firstname = request.getParameter("firstname").trim();
		   String lastname = request.getParameter("lastname").trim();
		   String phone = request.getParameter("phone").trim();
		   String email = request.getParameter("email").trim();
		   rental.Rent(firstname, lastname, phone, email);
		   response.setContentType("text/html");
		   PrintWriter out = response.getWriter();   
		   //UtilDBPeterson.rent(make, model, year); Change to rent indiv car
		      
		      String title = "Database Result";
		      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n"; //
		      out.println(docType + //
		            "<html>\n" + //
		            "<head><title>" + title + "</title></head>\n" + //
		            "<body bgcolor=\"#f0f0f0\">\n" + //
		            "<h1 align=\"center\">" + title + "</h1>\n");
		      out.println("<ul>");
		      out.println("<li> Make: " + rental.getCarMake());
		      out.println("<li> Model: " + rental.getCarModel());
		      out.println("<li> Model: " + rental.getCarYear());
		      out.println("<hr>"); //fix this someone please
		      out.println("<li> First Name: " + rental.getFirstName());
		      out.println("<li> Last Name: " + rental.getLastName());
		      out.println("<li> Phone Number: " + rental.getPhone());
		      out.println("<li> Email: " + rental.getEmail());
		      out.println("</ul>");
		      out.println("<a href=/" + projectName + "/" + searchWebName + ">Search Data</a> <br>");
		      out.println("</body></html>");
	   }
	   else
	   {
		   rental.makeAvailable();
		   response.sendRedirect("/techExercise/simpleSearchHB.html");
	   }
   }
}
