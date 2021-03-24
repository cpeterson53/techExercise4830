/**
 */
package util;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import datamodel.Rental;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @since JavaSE-1.8
 */
public class UtilDBPeterson {
   static SessionFactory sessionFactory = null;

   public static SessionFactory getSessionFactory() {
      if (sessionFactory != null) {
         return sessionFactory;
      }
      Configuration configuration = new Configuration().configure();
      StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
      sessionFactory = configuration.buildSessionFactory(builder.build());
      return sessionFactory;
   }

   //Retrieves all Rentals in a resultList
   public static List<Rental> listRentals() {
      List<Rental> resultList = new ArrayList<Rental>();

      Session session = getSessionFactory().openSession();
      Transaction tx = null;  // each process needs transaction and commit the changes in DB.

      try {
         tx = session.beginTransaction();
         List<?> rentals = session.createQuery("FROM Rental").list();
         for (Iterator<?> iterator = rentals.iterator(); iterator.hasNext();) {
            Rental rent = (Rental) iterator.next();
            if (rent.isAvailable()) resultList.add(rent);
         }
         tx.commit();
      } catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } finally {
         session.close();
      }
      return resultList;
   }

   
   //Retrieves all Rentals with keyword by CarMake
   public static List<Rental> listRentalsByCarMake(String keyword) {
      List<Rental> resultList = new ArrayList<Rental>();

      Session session = getSessionFactory().openSession();
      Transaction tx = null;

      try {
         tx = session.beginTransaction();
         System.out.println((Rental)session.get(Rental.class, 1)); // use "get" to fetch data
        // Query q = session.createQuery("FROM Employee");
         List<?> rentals = session.createQuery("FROM Rental").list();
         for (Iterator<?> iterator = rentals.iterator(); iterator.hasNext();) {
            Rental rent = (Rental) iterator.next();
            if (rent.isAvailable() && rent.getCarMake().startsWith(keyword)) {
               resultList.add(rent);
            }
         }
         tx.commit();
      } catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } finally {
         session.close();
      }
      return resultList;
   }

   
   public static List<Rental> listRentalsByName(String keyword) {
	      List<Rental> resultList = new ArrayList<Rental>();

	      Session session = getSessionFactory().openSession();
	      Transaction tx = null;

	      try {
	         tx = session.beginTransaction();
	         System.out.println((Rental)session.get(Rental.class, 1)); // use "get" to fetch data
	        // Query q = session.createQuery("FROM Employee");
	         List<?> rentals = session.createQuery("FROM Rental").list();
	         for (Iterator<?> iterator = rentals.iterator(); iterator.hasNext();) {
	            Rental rent = (Rental) iterator.next();
	            if (rent.getFirstName() != null && rent.getFirstName().startsWith(keyword)) {
	               resultList.add(rent);
	            }
	         }
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx != null)
	            tx.rollback();
	         e.printStackTrace();
	      } finally {
	         session.close();
	      }
	      return resultList;
	   }
   public static Rental getRentalById(String id) {
	      Rental rental = null;
	      Session session = getSessionFactory().openSession();
	      Transaction tx = null;
	      try {
	         tx = session.beginTransaction();
	         System.out.println((Rental)session.get(Rental.class, 1)); // use "get" to fetch data
	        // Query q = session.createQuery("FROM Employee");
	         rental =(Rental) session.createQuery("FROM Rental R WHERE R.id = " + id).list().get(0);
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx != null && tx.isActive())
	            tx.rollback();
	         e.printStackTrace();
	      } finally {
	         session.close();
	      }
	      return rental;
	   }
   
   
 //Creates Rental and commits it to DB
   public static void createRental(String make,String model, Integer year , String img) {
      Session session = getSessionFactory().openSession();
      Transaction tx = null;
      try {
         tx = session.beginTransaction();
         session.save(new Rental(make, model,year, img));
         tx.commit();
      } catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } finally {
         session.close();
      }
   }
   //added tx.isactive to each if statement to try and combat rollback exception
   public static void UpdateRental(Rental rent) {
	      Session session = getSessionFactory().openSession();
	      Transaction tx = null;
	      try {
	         tx = session.beginTransaction();
	         session.saveOrUpdate(rent);
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx != null)
	            tx.rollback();
	         e.printStackTrace();
	      } finally {
	         session.close();
	      }
	   }
}
