package com;

import java.sql.*;

public class Admin
{
	private Connection connect()
	{
		Connection con = null;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
 
			con = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/adminlog", "root", "MyNewPass");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
	
	public String readItems()
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			
			if (con == null)
			{
				return "Error while connecting to the database for reading.";
			}
 
			// Prepare the html table to be displayed
			output = "<table border='1' class='table'>"
					+ "  <thead><tr><th>Name</th><th>Email</th><th>Phone</th>"
					+"<th>Username</th><th>Password</th><th>Update</th><th>Remove</th></tr>  <thead>";
 
			String query = "select * from log";
			
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
 
			// iterate through the rows in the result set
			while (rs.next())
			{
				String id = Integer.toString(rs.getInt("id"));
				String name = rs.getString("name"); 
				String email = rs.getString("email");
				String phone = Double.toString(rs.getDouble("phone"));
				String username = rs.getString("username");
				String password = rs.getString("password");
 
				// Add into the html table
				output += "<tbody><tr><td><input id='hidItemIDUpdate'name='hidItemIDUpdate'type='hidden' value='" + id+ "'>" + name + "</td>";
				output += "<td>" + email + "</td>";
				output += "<td>" + phone + "</td>";
				output += "<td>" + username + "</td>";
				output += "<td>" + password + "</td>";
 
				// buttons
				output += "<td><input name='btnUpdate'type='button' value='Update'class='btnUpdate btn btn-secondary'></td>"+ "<td><input name='btnRemove'type='button' value='Remove'class='btnRemove btn btn-danger'data-itemid='"+ id + "'>" + "</td></tr><tbody>";
			}
 
			con.close();
 
			// Complete the html table
			output += "</table>";
		}
 
		catch (Exception e)
		{
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
 
		return output;
	}
	
	public String insertItem(String aname, String aemail,String aphone, String ausername,String apassword)
    {
		String output = "";

		try
		{
			Connection con = connect();
			
			if (con == null)
			{
				return "Error while connecting to the database for inserting.";
			}
 
			// create a prepared statement
			String query = " insert into log(`id`,`name`,`email`,`phone`,`username`,`password`)"+ " values (?, ?, ?, ?, ?, ?)";
		 
			PreparedStatement preparedStmt = con.prepareStatement(query);
		 
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, aname);
			preparedStmt.setString(3, aemail);
			preparedStmt.setString(4, aphone);
			preparedStmt.setString(5, ausername);
			preparedStmt.setString(6, apassword);
		 
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newItems = readItems();
			output = "{\"status\":\"success\", \"data\": \"" +newItems + "\"}";
		 }
		
		 catch (Exception e)
		 {
			 output = "{\"status\":\"error\", \"data\":\"Error while inserting the item.\"}";
			 System.err.println(e.getMessage());
		 }
		
		 return output;
		 
		 }
	
		 public String updateItem(String aid, String aname, String aemail, String aphone, String ausername, String apassword)
		 {
			 String output = "";
			 try
			 {
				 Connection con = connect();
				 if (con == null)
				 {
					 return "Error while connecting to the database for updating.";
				 }
				 
				 // create a prepared statement
				 String query = "UPDATE log SET name=?,email=?,phone=?,username=?,password=? WHERE id=?";
				 PreparedStatement preparedStmt = con.prepareStatement(query);
		 
				 // binding values
				 preparedStmt.setString(1, aname);
				 preparedStmt.setString(2, aemail);
				 preparedStmt.setString(3, aphone);
				 preparedStmt.setString(4, ausername);
				 preparedStmt.setString(4, apassword);
				 preparedStmt.setInt(5, Integer.parseInt(aid)); 
		
				 // execute the statement
				 preparedStmt.execute();
				 con.close();
		
				 String newItems = readItems();
				 output = "{\"status\":\"success\", \"data\": \"" +
				 newItems + "\"}";
		 }
			 
		 catch (Exception e)
		 {
			 output = "{\"status\":\"error\", \"data\": \"Error while updating the item.\"}";
			 System.err.println(e.getMessage());
		 }
		 
		return output;
	}
		
	public String deleteItem(String id)
	{
		 String output = "";
		 try
		 {
			 Connection con = connect();
			 if (con == null)
			 {
				 return "Error while connecting to the database for deleting.";
			 }
		 
			 // create a prepared statement
			 String query = "delete from log where id=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
		 
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(id));
		 
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 String newItems = readItems();
			 output = "{\"status\":\"success\", \"data\": \"" +
			 newItems + "\"}";
		 }
		 
		 catch (Exception e)
		 {
			 output = "{\"status\":\"error\", \"data\": \"Error while deleting the item.\"}";
			 System.err.println(e.getMessage());
		 }
		 
		 return output;
		 
		 }
	}
		 