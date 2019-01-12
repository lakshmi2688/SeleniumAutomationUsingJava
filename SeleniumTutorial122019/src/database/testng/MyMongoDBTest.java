package database.testng;

import org.testng.annotations.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.testng.annotations.BeforeClass;
import org.bson.Document;
import org.testng.Assert;
import org.testng.annotations.AfterClass;


//Download Driver - http://mongodb.github.io/mongo-java-driver/3.0/driver/getting-started/installation-guide/
public class MyMongoDBTest {
	
	MongoClient mongoClient = null;
	MongoDatabase db = null;
  
  @BeforeClass
  public void beforeClass() {
	  try {
			// STEP 1: Connect to database
			mongoClient = new MongoClient("localhost", 27017);
			db = mongoClient.getDatabase("user_info");
			System.out.println("Connect to database successfully");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
  }
  
  
  @Test
  public void f() {
	  try {
			// STEP 2: Get Collection
			MongoCollection<Document> table = db.getCollection("user");
			// STEP 3: Extract Data
			FindIterable<Document> cursor = table.find();
			
			// STEP 4: Iterate over data
			for (Document obj : cursor) {
				System.out.println(obj.toString());
			}
			
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}
  }

  @AfterClass
  public void afterClass() {
  }

}
