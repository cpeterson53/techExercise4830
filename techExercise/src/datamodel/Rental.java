package datamodel;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import util.UtilDBPeterson;

/**
 * @since J2SE-1.8
 CREATE TABLE rental (
  id INT NOT NULL AUTO_INCREMENT,    
  carMake VARCHAR(30) NOT NULL,
  carModel VARCHAR(30) NOT NULL,
  carYear INT NOT NULL,
  carImage VARCHAR(200) NOT NULL,  
  firstName VARCHAR(30) NOT NULL,
  lastName VARCHAR(30) NOT NULL,
  phone VARCHAR(30) NOT NULL,
  email VARCHAR(30) NOT NULL,
  startDate VARCHAR(30) NOT NULL,
  endDate VARCHAR(30) NOT NULL,   
  PRIMARY KEY (id));
 */
@Entity
@Table(name = "rental")
public class Rental {

   @Id  // primary key
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id") // specify the column name. Without it, it will use method name
   private Integer id;

   //Available
   @Column(name = "carMake")
   private String carMake;
   
   @Column(name = "carModel")
   private String carModel;
   
   @Column(name = "carYear")
   private Integer carYear;
   
   @Column(name = "carImage")
   private String carImage;
   
   //Taken
   @Column(name = "firstName")
   private String firstName;

   @Column(name = "lastName")
   private String lastName;
   
   @Column(name = "phone")
   private String phone;
   
   @Column(name = "email")
   private String email;
   
   
   @Column(name = "startDate")
   private String startDate;

   @Column(name = "endDate")
   private String endDate;
   
   public Rental() {
   }

   //Available Rental
   public Rental(String make, String model, Integer year) {
	      this.carMake = make;
	      this.carModel = model;
	      this.carYear = year;
	   }
   
   public Rental(String make, String model, Integer year, String carImage) {
	      this.carMake = make;
	      this.carModel = model;
	      this.carYear = year;
	      this.carImage = carImage;
	   }
   
   //Taken Rental
   public void Rent(String firstName, String lastName, String phone, String email) {
	   if(this.isAvailable())
	   {
	   		  this.firstName = firstName;
		      this.lastName = lastName;
		      this.phone = phone;
		      this.email = email;
		      
		      Calendar calendar = Calendar.getInstance();
		      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		      this.startDate = sdf.format(calendar.getTime());
		      calendar.add(Calendar.DATE, 7);
		      this.endDate = sdf.format(calendar.getTime());
		      UtilDBPeterson.UpdateRental(this);
	   }
	   else
	   {
		   System.out.println("Attempt to Rent" + this.carMake + ", " + this.carModel + ", " + this.carYear + this.firstName);
	   }
	   
   }

   public void makeAvailable()
   {
	   this.firstName = null;
	   this.lastName = null;
	   this.phone = null;
	   this.email = null;
	   this.startDate = null;
	   this.endDate = null;
	   UtilDBPeterson.UpdateRental(this);
   }
   
   public boolean isAvailable()
   {
	   return firstName == null;
   }
   
public Integer getId() {
	return id;
}


public String getFirstName() {
	return firstName;
}


public String getLastName() {
	return lastName;
}


public String getPhone() {
	return phone;
}

public String getEmail() {
	return email;
}

public String getStartDate() {
	return startDate;
}


public String getEndDate() {
	return endDate;
}


public String getCarMake() {
	return carMake;
}


public String getCarModel() {
	return carModel;
}


public Integer getCarYear() {
	return carYear;
}

public String getCarImage()
{
	return carImage;
}

public void setId(Integer id) {
	this.id = id;
}


public void setFirstName(String firstName) {
	this.firstName = firstName;
}


public void setLastName(String lastName) {
	this.lastName = lastName;
}


public void setPhone(String phone) {
	this.phone = phone;
}

public void setEmail(String email) {
	this.phone = email;
}


public void setStartDate(String startDate) {
	this.startDate = startDate;
}


public void setEndDate(String endDate) {
	this.endDate = endDate;
}


public void setCarMake(String carMake) {
	this.carMake = carMake;
}


public void setCarModel(String carModel) {
	this.carModel = carModel;
}


public void setCarYear(Integer carYear) {
	this.carYear = carYear;
}

public void setCarImage(String carImage)
{
	this.carImage = carImage;
}
   @Override
   public String toString() {
      if(this.firstName == null)
      {
    	  return this.basicString();
      }
      else
      {
    	  return "Leased By " + this.lastName + ", " + this.firstName + "from " + this.startDate + " to " + this.endDate + " " + this.basicString();
      }
   }
   
   public String basicString()
   {
	   return "Car Make: " + this.carMake + ", Car Model: " + this.carModel + ", Car Year: " + this.carYear;
   }
}