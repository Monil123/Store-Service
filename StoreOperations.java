package com.Store;


import java.util.ArrayList;
import java.util.List; 
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.FormParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Store.Store;

@Path("/StoreService") 							//Path to this class

public class StoreOperations {  
	static Connection con;
	static PreparedStatement pst;
	static PreparedStatement pst1;
	static ResultSet rs;

   public void Connect(){					//Establishing Database connection
	   try
	   {
		    Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/StoreWeb";
			String user="root";
			String pass="monil";
	        con = DriverManager.getConnection(url,user,pass);		   
	   }
	   catch(Exception e)
	   {
		   System.out.println(e.getMessage());
	   }
   }
   
   @GET 
   @Path("/products") 
   @Produces(MediaType.APPLICATION_XML) 		//Generates a xml file for output
   public List<Store> ListProducts() throws SQLException{			//List all products in the store
	   ArrayList<Store> productList = new ArrayList<Store>();		//ArrayList to store each product
	   Connect();
	   pst = con.prepareStatement("select * from storedetails");
	   rs = pst.executeQuery();
	   while(rs.next())
	   {
		   Store store1 = new Store(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
		   productList.add(store1);
	   }
	   con.close();
	   return productList;
   }
   
   @GET 
   @Path("/get/{id}") 					//Get product by Id
   @Produces(MediaType.APPLICATION_XML)	
   public List<Store> getProduct(@PathParam("id") String id) throws SQLException{
	   Connect();
	   pst = con.prepareStatement("select * from storedetails where id = ?");
	   pst.setString(1,id);
	   rs = pst.executeQuery();
	   ArrayList<Store> productList = new ArrayList<Store>();
	while(rs.next())
	   {
		   Store store1 = new Store(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
		   productList.add(store1);
	   }
	   con.close();
	   return productList;
   }
   
   @POST 
   @Path("/create") 				//Create a new product entry
   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)		//Takes info from create.html file
   @Produces(MediaType.APPLICATION_XML)
   public List<Store> createProduct(@FormParam("id") String id, @FormParam("proName") String proName, @FormParam("proPrice") int proPrice, @FormParam("quantity") int quantity) {
	   ArrayList<Store> productList = new ArrayList<Store>();
	   try
	   {
		   Connect();
		   pst = con.prepareStatement("insert into storedetails values(?, ?, ?, ?)"); //Insert data into the table
		   pst.setString(1,id);
		   pst.setString(2,proName);
		   pst.setInt(3,proPrice);
		   pst.setInt(4,quantity);
		   pst.executeUpdate();
		   
		   pst1 = con.prepareStatement("select * from storedetails");	//Retrieve all data from the table
		   rs = pst1.executeQuery();
		   while(rs.next())
		   {
			   Store store1 = new Store(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
			   productList.add(store1);
		   }
		   con.close();
	   }
	   catch(SQLException e)
	   {
		   System.out.println("SQL Exception occurred");
	   }
	   return productList;
   }
   
  @POST
  @Path("/update")				//Update an existing product by its Id
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @Produces(MediaType.APPLICATION_XML)
  public List<Store> updateProduct(@FormParam("id") String id, @FormParam("proName") String proName, @FormParam("proPrice") int proPrice, @FormParam("quantity") int quantity) {
	  ArrayList<Store> productList = new ArrayList<Store>();
	  try
	  {
		  Connect();
		  pst = con.prepareStatement("update storedetails set ProName=?, ProPrice=?, Quantity=? where id=?");	//Update the info
		  pst.setString(4,id);
		  pst.setString(1,proName);
		  pst.setInt(2,proPrice);
		  pst.setInt(3,quantity);
		  pst.executeUpdate();
		  
		  pst1 = con.prepareStatement("select * from storedetails");	//Retrieve all entries
		  rs = pst1.executeQuery();
		  while(rs.next())
		   {
			   Store store1 = new Store(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
			   productList.add(store1);
		   }
		  con.close();
	  }
	  catch(SQLException e)
	  {
		  System.out.println("SQL Exception occurred"); 
	  }
	  return productList;
  }
  
  @POST
  @Path("/delete")			//Delete an existing entry
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)		//Takes Id of the product by which it is to be deleted
  @Produces(MediaType.APPLICATION_XML)
  public List<Store> deleteProduct(@FormParam("id") String id) throws SQLException{
	  Connect();
	  ArrayList<Store> productList = new ArrayList<Store>();
	  pst = con.prepareStatement("delete from storedetails where id = ?");	//Delete from database
	  pst.setString(1,id);
	  pst.executeUpdate();
	  
	  pst1 = con.prepareStatement("select * from storedetails");	//Retrieve entries from database
	  rs = pst1.executeQuery();
	  while(rs.next())
	   {
		   Store store1 = new Store(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
		   productList.add(store1);
	   }
	  con.close();
	  return productList;
  }
}