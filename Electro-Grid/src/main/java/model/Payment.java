package model;
import java.sql.*;
public class Payment
{ //A common method to connect to the DB
private Connection connect()
 {
 Connection con = null;
 try
 {
 Class.forName("com.mysql.jdbc.Driver");

 //Provide the correct details: DBServer/DBName, username, password
 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/payment", "root", "admin");
 }
 catch (Exception e)
 {e.printStackTrace();}
 return con;
 }
public String insertPayment(String name,String billid, String cardno, String cvc, String expdate)
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for inserting."; }
 // create a prepared statement
 String query = " insert into payment(`id`,`name`, `billid`,`cardno`,`cvc`,`expdate`)"+ " values (?, ?, ?, ?, ?, ?)";
 PreparedStatement preparedStmt = con.prepareStatement(query);
 // binding values
 preparedStmt.setInt(1, 0);
 preparedStmt.setString(2, name);
 preparedStmt.setString(3, billid);
 preparedStmt.setString(4, cardno);
 preparedStmt.setString(5,cvc);
 preparedStmt.setString(6, expdate);
 // execute the statement
 preparedStmt.execute();
 con.close();
 output = "Inserted successfully";
 }
 catch (Exception e)
 {
 output = "Error while inserting the item.";
 System.err.println(e.getMessage());
 }
 return output;
 }
public String readPayment()
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for reading."; }
 // Prepare the html table to be displayed
 output = "<table border='1'><tr><th>User Name</th><th>Bill Id</th>"+"<th>Card Number</th>" + "<th>CVC</th>" + "<th>Expire Date</th>" +"<th>Update</th><th>Remove</th></tr>";
 //output = "<table border='1'><tr><th>User Name</th><th>Contact Number</th>" + "<th>Email</th>" + "<th>Password</th>" +"<th>Update</th><th>Remove</th></tr>";

 String query = "select * from payment";
 Statement stmt = con.createStatement();
 ResultSet rs = stmt.executeQuery(query);
 // iterate through the rows in the result set
 while (rs.next())
 {
 String id = Integer.toString(rs.getInt("id"));
 String name = rs.getString("name");
 String billid = rs.getString("billid");
 String cardno = rs.getString("cardno");
 String cvc = rs.getString("cvc");
 String expdate = rs.getString("expdate");
 // Add into the html table
 output += "<tr><td>" + name + "</td>";
 output += "<td>" + billid + "</td>";
 output += "<td>" + cardno + "</td>";
 output += "<td>" + cvc + "</td>";
 output += "<td>" + expdate + "</td>";
 // buttons
 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"+ "<td><form method='post' action='payment.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"+ "<input name='id' type='hidden' value='" + id+ "'>" + "</form></td></tr>";}
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
public String updatePayment(String id, String name , String billid, String cardno, String cvc, String expdate)
{
String output = "";
try
{
Connection con = connect();
if (con == null)
{return "Error while connecting to the database for updating."; }
// create a prepared statement
String query = "UPDATE payment SET name=?, billid=?, cardno=?,cvc=?,expdate=?WHERE id=?";
PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
preparedStmt.setString(1, name);
preparedStmt.setString(2, billid);
preparedStmt.setString(3, cardno);
preparedStmt.setString(4, cvc);
preparedStmt.setString(5, expdate);
preparedStmt.setInt(6, Integer.parseInt(id));
// execute the statement
preparedStmt.execute();
con.close();
output = "Updated successfully";
}
catch (Exception e)
{
output = "Error while updating the item.";
System.err.println(e.getMessage());
}
return output;
}
public String deletePayment(String id)
{
String output = "";
try
{
Connection con = connect();
if (con == null)
{return "Error while connecting to the database for deleting."; }
// create a prepared statement
String query = "delete from payment where id=?";
PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
preparedStmt.setInt(1, Integer.parseInt(id));
// execute the statement
preparedStmt.execute();
con.close();
output = "Deleted successfully";
}
catch (Exception e)
{
output = "Error while deleting the item.";
System.err.println(e.getMessage());
}
return output;
}
} 
