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
		   
		      
		      String title = "Database Result";
		      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n"; //
		      out.println(docType + //
		    		  "<head><link rel='stylesheet' href='/techExercise/main.css'></head>" +
		    		  "<body><header><nav><a href=\"/techExercise/simpleSearchHB.html\">&lt; Back to Search</a></nav><h1 class='accent'>"+title+"</h1></header><main><section class=\"accent\">");
		      out.println("<ul>");
		      out.println("<li> Make: " + rental.getCarMake());
		      out.println("<li> Model: " + rental.getCarModel());
		      out.println("<li> Model: " + rental.getCarYear());
		      out.println("<hr>");
		      out.println("<li> First Name: " + rental.getFirstName());
		      out.println("<li> Last Name: " + rental.getLastName());
		      out.println("<li> Phone Number: " + rental.getPhone());
		      out.println("<li> Email: " + rental.getEmail());
		      out.println("</ul>");
		      out.println("</section>\n" + 
		      		"    </main>\n" + 
		      		"\n" + 
		      		"    <footer class=\"accent\">\n" + 
		      		"        ©Connor Peterson 2021\n" + 
		      		"    </footer>\n" + 
		      		"</body>\n" + 
		      		"\n" + 
		      		"</html>");
	   }
	   else
	   {
		   rental.makeAvailable();
		   response.sendRedirect("/techExercise/simpleSearchHB.html");
	   }
   }
}
