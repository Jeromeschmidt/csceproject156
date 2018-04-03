package com.sf.ext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
/*
 * This is a collection of utility methods that define a general API for
 * interacting with the database supporting this application.
 * 16 methods in total, add more if required.
 * Do not change any method signatures or the package name.
 * 
 */

import com.sf.ext.DatabaseInfo;

public class InvoiceData {

	/**
	 * 1. Method that removes every person record from the database
	 */
	public static void removeAllPersons() {
		/** TODO*/
		Connection c = DatabaseInfo.getConnection();
		
		try
		{
			String query = "DELETE FROM Emails";
			PreparedStatement ps = c.prepareStatement(query);
			ps.executeUpdate();
			
			query = "DELETE FROM Persons";
			ps = c.prepareStatement(query);
			ps.executeUpdate();
	
			c.close();

		}
		catch (SQLException e)
		{
			System.out.println("SQLException: NOT FOUND");
			e.printStackTrace();
			throw new RuntimeException(e);
		}	
	}
		
	/**
	 * 2. Method to add a person record to the database with the provided data.
	 * 
	 * @param personCode
	 * @param firstName
	 * @param lastName
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 * @param country
	 */
	public static void addPerson(String personCode, String firstName, String lastName, String street, String city, String state, String zip, String country) {
		/** TODO*/
		
		Connection c = DatabaseInfo.getConnection();
		try
		{
			String query;
			PreparedStatement ps;		
			
			query = "INSERT INTO Persons (personCode, named, address) VALUES(?,?,?)";
			ps = c.prepareStatement(query);
			ps.setString(1, personCode);
			ps.setString(2, lastName + ", " +firstName);
			ps.setString(3, street+ "," +city + "," +state +"," + zip+"," + country);
			ps.executeUpdate();
			c.close();

		}
		catch (SQLException e)
		{
			System.out.println("SQLException: NOT FOUND");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		
	}

	/**
	 * 3. Adds an email record corresponding person record corresponding to the
	 * provided <code>personCode</code>
	 * 
	 * @param personCode
	 * @param email
	 */
	public static void addEmail(String personCode, String email) {
		/** TODO*/
		String query = "INSERT INTO Emails (idPersons, emails) VALUES(?,?)";
		String searchQuery = "Select idPersons from Persons where personCode = ?";
		Connection c = DatabaseInfo.getConnection();
		int id = 0;
		try
		{
			PreparedStatement ps = c.prepareStatement(searchQuery);
			ps.setString(1, personCode);
			ResultSet r = ps.executeQuery();
			if(r.next()) {
				id = r.getInt("idPersons");
			}
			
			PreparedStatement p = c.prepareStatement(query);
			p.setInt(1, id);
			p.setString(2, email);
			p.executeUpdate();
			
			c.close();

		}
		catch (SQLException e)
		{
			System.out.println("SQLException: NOT FOUND");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	/**
	 * 4. Method that removes every member record from the database
	 */
	public static void removeAllMembers() {
		/** TODO*/
		Connection c = DatabaseInfo.getConnection();
		
		try
		{
			String query = "DELETE FROM Membership";
			PreparedStatement ps = c.prepareStatement(query);
			ps.executeUpdate();
			c.close();

		}
		catch (SQLException e)
		{
			System.out.println("SQLException: NOT FOUND");
			e.printStackTrace();
			throw new RuntimeException(e);
		}	
		
	}
	/**
	 * 5. Method to add a member record to the database with the provided data
	 * @param memberCode
	 * @param memberType
	 * @param primaryContactPersonCode
	 * @param name
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 * @param country
	 */
	public static void addMember(String memberCode, String memberType, String primaryContactPersonCode,String name, String street, String city, String state, String zip, String country) {
		/** TODO*/
		//memNum,memType,person, company, address
		Connection c = DatabaseInfo.getConnection();
		try
		{
			String query;
			PreparedStatement ps;		
			
			query = "INSERT INTO Membership (memNum, memType, person, company, address) VALUES(?,?,?,?,?)";
			ps = c.prepareStatement(query);
			ps.setString(1, memberCode);
			ps.setString(2, memberType);
			ps.setString(3, primaryContactPersonCode);
			ps.setString(4, name);
			ps.setString(5, street+ "," +city + "," +state +"," + zip+"," + country);
			ps.executeUpdate();
			c.close();

		}
		catch (SQLException e)
		{
			System.out.println("SQLException: NOT FOUND");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	/**
	 * 6. Removes all product records from the database
	 */
	public static void removeAllProducts() {
		/** TODO*/
		Connection c = DatabaseInfo.getConnection();
		
		try
		{
			String query = "DELETE FROM YearMem";
			PreparedStatement ps = c.prepareStatement(query);
			ps.executeUpdate();
			
			query = "DELETE FROM Equipment";
			ps = c.prepareStatement(query);
			ps.executeUpdate();
			
			
			query = "DELETE FROM Parking";
			ps = c.prepareStatement(query);
			ps.executeUpdate();
			
			
			query = "DELETE FROM Day";
			ps = c.prepareStatement(query);
			ps.executeUpdate();
			
			
			query = "DELETE FROM Service";
			ps = c.prepareStatement(query);
			ps.executeUpdate();
			c.close();

		}
		catch (SQLException e)
		{
			System.out.println("SQLException: NOT FOUND");
			e.printStackTrace();
			throw new RuntimeException(e);
		}	
		
	}

	/**
	 * 7. Adds a day-pass record to the database with the provided data.
	 */
	public static void addDayPass(String productCode, String dateTime, String street, String city, String state, String zip, String country, double pricePerUnit) {
		/** TODO*/
		String query = "INSERT INTO Day (prodCode, prodType, dateBuy, timeBuy, addressBought, price, idService) VALUES(?,?,?,?,?,?,?)";
		
		Connection c = DatabaseInfo.getConnection();

		try
		{
			String idQuery = "INSERT INTO Service(idService) VALUES (NULL);";
			String getID = "SELECT LAST_INSERT_ID();";
			
			PreparedStatement p = c.prepareStatement(idQuery);
			p.executeUpdate();
			PreparedStatement s = c.prepareStatement(getID);
			ResultSet r = s.executeQuery();
			r.next();
			int id = r.getInt(1);
			PreparedStatement ps = c.prepareStatement(query);
			ps.setString(1, productCode);
			ps.setString(2, dateTime);
			String[] daTime = dateTime.split(" ");			
			ps.setString(3, daTime[0]);
			ps.setString(4, daTime[1]);
			ps.setString(5, street+ "," +city + "," +state +"," + zip+"," + country);
			ps.setDouble(6, pricePerUnit);
			ps.setInt(7, id);
			ps.executeUpdate();
			
			c.close();

		}
		catch (SQLException e)
		{
			System.out.println("SQLException: NOT FOUND");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 8. Adds a year-long-pass record to the database with the provided data.
	 */
	public static void addYearPass(String productCode, String StartDate, String EndDate,String street, String city, String state, String zip, String country, String name, double pricePerUnit) {
		/** TODO*/
		
		String query = "INSERT INTO YearMem (prodCode, prodType, dateBuy, dateExpire, addressBought, package, price, idService) VALUES(?,?,?,?,?,?,?,?)";
		String idQuery = "INSERT INTO Service(idService) VALUES (NULL);";
		String getID = "SELECT LAST_INSERT_ID();";
		Connection c = DatabaseInfo.getConnection();

		try
		{
			PreparedStatement p = c.prepareStatement(idQuery);
			p.executeUpdate();
			PreparedStatement s = c.prepareStatement(getID);
			ResultSet r = s.executeQuery();
			r.next();
			int id = r.getInt(1);
			PreparedStatement ps = c.prepareStatement(query);
			ps.setString(1, productCode);
			ps.setString(2, "Y");
			ps.setString(3, StartDate);			
			ps.setString(4, EndDate);
			ps.setString(5, street+ "," +city + "," +state +"," + zip+"," + country);
			ps.setString(6, name);
			ps.setDouble(7, pricePerUnit);
			ps.setInt(8, id);
			ps.executeUpdate();
			
			c.close();

		}
		catch (SQLException e)
		{
			System.out.println("SQLException: NOT FOUND");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 9. Adds a ParkingPass record to the database with the provided data.
	 */
	public static void addParkingPass(String productCode, double parkingFee) {
        /** TODO*/
		String query = "INSERT INTO Parking(prodCode, prodType, price, idService) VALUES(?,?,?,?)";
		String idQuery = "INSERT INTO Service(idService) VALUES (NULL);";
		String getID = "SELECT LAST_INSERT_ID();";
		Connection c = DatabaseInfo.getConnection();

		try
		{
			
			PreparedStatement p = c.prepareStatement(idQuery);
			p.executeUpdate();
			PreparedStatement s = c.prepareStatement(getID);
			ResultSet r = s.executeQuery();
			r.next();
			int id = r.getInt(1);
			PreparedStatement ps = c.prepareStatement(query);
			ps.setString(1, productCode);
			ps.setString(2, "P");
			ps.setDouble(3, parkingFee);
			ps.setInt(4, id);
			ps.executeUpdate();
			
			c.close();

		}
		catch (SQLException e)
		{
			System.out.println("SQLException: NOT FOUND");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	/**
	 * 10. Adds an equipment rental record to the database with the provided data.
	 */
	public static void addRental(String productCode, String name, double cost) {
        /** TODO*/
		String query = "INSERT INTO Equipment(prodCode, prodType, prodName, price, idService) VALUES(?,?,?,?,?)";
		String idQuery = "INSERT INTO Service(idService) VALUES (NULL);";
		String getID = "SELECT LAST_INSERT_ID();";
		Connection c = DatabaseInfo.getConnection();

		try
		{
			PreparedStatement p = c.prepareStatement(idQuery);
			p.executeUpdate();
			PreparedStatement s = c.prepareStatement(getID);
			ResultSet r = s.executeQuery();
			r.next();
			int id = r.getInt(1);
			PreparedStatement ps = c.prepareStatement(query);
			ps.setString(1, productCode);
			ps.setString(2, "R");
			ps.setString(3, name);
			ps.setDouble(4, cost);
			ps.setInt(5, id);
			ps.executeUpdate();
			
			c.close();

		}
		catch (SQLException e)
		{
			System.out.println("SQLException: NOT FOUND");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 11. Removes all invoice records from the database
	 */
	public static void removeAllInvoices() {
        /** TODO*/
	Connection c = DatabaseInfo.getConnection();
		
		try
		{
			String query =  "DELETE FROM YearMem";
			PreparedStatement ps = c.prepareStatement(query);
			ps.executeUpdate();
			
			query = "DELETE FROM Equipment";
			ps = c.prepareStatement(query);
			ps.executeUpdate();
			
			query = "DELETE FROM Parking";
			ps = c.prepareStatement(query);
			ps.executeUpdate();
			
			query = "DELETE FROM Day";
			ps = c.prepareStatement(query);
			ps.executeUpdate();
			
			
			query = "DELETE FROM Service";
			ps = c.prepareStatement(query);
			ps.executeUpdate();
			
			query = "DELETE FROM InvoiceItems";
			ps = c.prepareStatement(query);
			ps.executeUpdate();
			
			query = "DELETE FROM Invoice";
			ps = c.prepareStatement(query);
			ps.executeUpdate();
			
			query = "DELETE FROM Membership";
			ps = c.prepareStatement(query);
			ps.executeUpdate();
			
			query = "DELETE FROM Emails";
			ps = c.prepareStatement(query);
			ps.executeUpdate();
			
			query = "DELETE FROM Persons";
			ps = c.prepareStatement(query);
			ps.executeUpdate();
			
			c.close();
			

		}
		catch (SQLException e)
		{
			System.out.println("SQLException: NOT FOUND");
			e.printStackTrace();
			throw new RuntimeException(e);
		}	
		
	}

	/**
	 * 12. Adds an invoice record to the database with the given data.
	 */
	public static void addInvoice(String invoiceCode, String memberCode, String personalTrainerCode, String invoiceDate) {
		/** TODO*/
		String searchQuery = "Select idPersons from Persons where personCode = ?";
		String searchQuery2 = "Select idMembership from Membership where memNum = ?";
		String query = "INSERT INTO Invoice(invoiceNum, memNum, serviceRep, dateOfPurchase, idPersons, idMembership) VALUES(?,?,?,?,?,?)";
		Connection c = DatabaseInfo.getConnection();
		int id =0;
		int idd = 0;
		try
		{
			PreparedStatement s = c.prepareStatement(searchQuery2);
			s.setString(1, memberCode);
			ResultSet rt = s.executeQuery();
			if(rt.next()) {
				idd = rt.getInt("idMembership");
			}
			PreparedStatement ps = c.prepareStatement(searchQuery);
			ps.setString(1, personalTrainerCode);
			ResultSet r = ps.executeQuery();
			if(r.next()) {
				id = r.getInt("idPersons");
			}
			PreparedStatement p = c.prepareStatement(query);
			p.setString(1, invoiceCode);
			p.setString(2, memberCode);
			p.setString(3, personalTrainerCode);
			p.setString(4, invoiceDate);
			p.setInt(5, id);
			p.setInt(6, idd);
			p.executeUpdate();
			
			c.close();

		}
		catch (SQLException e)
		{
			System.out.println("SQLException: NOT FOUND");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	/**
	 * 13. Adds a particular day-pass (corresponding to <code>productCode</code>
	 * to an invoice corresponding to the provided <code>invoiceCode</code> with
	 * the given number of units
	 */

	public static void addDayPassToInvoice(String invoiceCode, String productCode, int quantity) {
		/** TODO*/
		String searchQuery = "Select idInvoice from Invoice where invoiceNum = ?";
		String query = "INSERT INTO InvoiceItems(idInvoice, item, quant) VALUES(?,?,?)";
		Connection c = DatabaseInfo.getConnection();
		int id= 0;
		try
		{
			PreparedStatement ps = c.prepareStatement(searchQuery);
			ps.setString(1, invoiceCode);
			ResultSet r = ps.executeQuery();
			if(r.next()) {
				id = r.getInt("idInvoice");
			}
			PreparedStatement p = c.prepareStatement(query);
			p.setInt(1, id);
			p.setString(2, productCode);
			p.setInt(3, quantity);
			p.executeUpdate();
			
			c.close();

		}
		catch (SQLException e)
		{
			System.out.println("SQLException: NOT FOUND");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 14. Adds a particular year-long-pass (corresponding to <code>productCode</code>
	 * to an invoice corresponding to the provided <code>invoiceCode</code> with
	 * the given begin/end dates
	 */
	public static void addYearPassToInvoice(String invoiceCode, String productCode, int quantity) {
		/** TODO*/
		String searchQuery = "Select idInvoice from Invoice where invoiceNum = ?";
		String query = "INSERT INTO InvoiceItems(idInvoice, item, quant) VALUES(?,?,?)";
		Connection c = DatabaseInfo.getConnection();
		int id = 0;
		try
		{
			PreparedStatement ps = c.prepareStatement(searchQuery);
			ps.setString(1, invoiceCode);
			ResultSet r = ps.executeQuery();
			if(r.next()) {
				id = r.getInt("idInvoice");
			}
			PreparedStatement p = c.prepareStatement(query);
			p.setInt(1, id);
			p.setString(2, productCode);
			p.setString(3, Integer.toString(quantity));
			p.executeUpdate();
			
			c.close();

		}
		catch (SQLException e)
		{
			System.out.println("SQLException: NOT FOUND");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

     /**
     * 15. Adds a particular ParkingPass (corresponding to <code>productCode</code> to an 
     * invoice corresponding to the provided <code>invoiceCode</code> with the given
     * number of quantity.
     * NOTE: membershipCode may be null
     */
    public static void addParkingPassToInvoice(String invoiceCode, String productCode, int quantity, String membershipCode) {
    	/** TODO*/
    	String searchQuery = "Select idInvoice from Invoice where invoiceNum = ?";
    	String query = "INSERT INTO InvoiceItems(idInvoice, item, quant, memOFitem) VALUES(?,?,?,?)";
		Connection c = DatabaseInfo.getConnection();
		int id =0;
		try
		{
			PreparedStatement ps = c.prepareStatement(searchQuery);
			ps.setString(1, invoiceCode);
			ResultSet r = ps.executeQuery();
			if(r.next()) {
				id = r.getInt("idInvoice");
			}
			PreparedStatement p = c.prepareStatement(query);
			p.setInt(1, id);
			p.setString(2, productCode);
			p.setString(3, Integer.toString(quantity));
			p.setString(4, membershipCode);
			p.executeUpdate();
			
			c.close();

		}
		catch (SQLException e)
		{
			System.out.println("SQLException: NOT FOUND");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
    }
	
    /**
     * 16. Adds a particular equipment rental (corresponding to <code>productCode</code> to an 
     * invoice corresponding to the provided <code>invoiceCode</code> with the given
     * number of quantity. 
     * NOTE: membershipCode may be null
     */
    public static void addRentalToInvoice(String invoiceCode, String productCode, int quantity, String membershipCode) {
    	/** TODO*/
    	String searchQuery = "Select idInvoice from Invoice where invoiceNum = ?";
    	String query = "INSERT INTO InvoiceItems(idInvoice, item, quant, memOFitem) VALUES(?,?,?,?)";
		Connection c = DatabaseInfo.getConnection();
		int id= 0;
		try
		{
			PreparedStatement ps = c.prepareStatement(searchQuery);
			ps.setString(1, invoiceCode);
			ResultSet r = ps.executeQuery();
			if(r.next()) {
				id = r.getInt("idInvoice");
			}
			PreparedStatement p = c.prepareStatement(query);
			p.setInt(1, id);
			p.setString(2, productCode);
			p.setString(3, Integer.toString(quantity));
			p.setString(4, membershipCode);
			p.executeUpdate();
			
			c.close();

		}
		catch (SQLException e)
		{
			System.out.println("SQLException: NOT FOUND");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
    }

   
}
