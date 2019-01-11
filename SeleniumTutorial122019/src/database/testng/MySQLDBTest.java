package database.testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.testng.annotations.AfterClass;

public class MySQLDBTest {
	
	// Connection object
		static Connection conn = null;
		// Statement object
		private static Statement stmt;
		// Result Set
		private static ResultSet results = null;
		// Constant for Database URL
		public static String DB_URL = "jdbc:mysql://localhost:3306/user_info"; // ORacle "jdbc:oracle:thin:@localhost:1521/sid"

		// Constant for Database Username
		public static String DB_USER = "root";
		// Constant for Database Password
		public static String DB_PASSWORD = "adit2688";
		// Driver
		public static String driver = "com.mysql.jdbc.Driver"; // "oracle.jdbc.driver.OracleDriver"
		
		
		@BeforeClass
		  public void beforeClass() {
			  
			// Properties for creating connection to database
					Properties props = new Properties();
					props.setProperty("user", "root");
					props.setProperty("password", "adit2688");
				    
					try {
						// STEP 1: Register JDBC driver
						Class.forName(driver).newInstance();
						
						// STEP 2: Get connection to DB
						System.out.println("Connecting to a selected database...");
						conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
						// conn = DriverManager.getConnection(DB_URL, props);		
						System.out.println("Connected database successfully...");
						
						// STEP 3: Statement object to send the SQL statement to the Database
						System.out.println("Creating statement...");
						stmt = conn.createStatement();
						
					} catch (Exception e) {
						e.printStackTrace();
					}
		  }
		
		
  @Test
  public void f() {
	  
	     String query = "select * from user";
	        try {
	        	// STEP 4: Extract data from result set
	        	results = stmt.executeQuery(query);
	        	while (results.next()) {
	        		String name = results.getString("name");
	        		String dob = results.getString("birth");
	        		String city = results.getString("city");
	        		
	        		// Display Values
	        		System.out.println("Name: " + name);
	        		System.out.println("BirthDate: " + dob);
	        		System.out.println("City: " + city);
	        		
	        		// From GUI
//	        		WebElement element = dv.findElement(By.id("uname"));
//	        		String actualUserName = element.getText();
//	        		Assert.assertEquals(actualUserName, first);
	            }
	        	results.close();
			} catch (SQLException se) {
				// Handle errors for JDBC
				se.printStackTrace();
			} catch (Exception e) {
				// Handle errors for Class.forName
				e.printStackTrace();
			}
  }
  
  
  

  @AfterClass
  public void afterClass() {
	  
	  try {
			if (results != null)
				results.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
  }

}
