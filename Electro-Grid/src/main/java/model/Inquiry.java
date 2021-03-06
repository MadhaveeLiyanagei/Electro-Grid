package model;
import java.sql.*;
public class Inquiry
{ //A common method to connect to the DB
private Connection connect()
 {
 Connection con = null;
 try
 {
 Class.forName("com.mysql.jdbc.Driver");
 //Provide the correct details: DBServer/DBName, username, password
 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/electrogrid", "root", "madhi99#");
}
 catch (Exception e)
{e.printStackTrace();}
 return con;
 }
public String insertInquiry(String refNo, String username, String contact, String email, String city,String date, String remark)
 {
String output = "";
try
{
Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for inserting."; }
// create a prepared statement
String query = " insert into inquiry(`id`,`refNo`,`username`,`contact`,`email`,`city`,`date`,`remark`)"+ " values (?, ?, ?, ?, ?,?,?,?)";
 PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
 preparedStmt.setInt(1, 0);
 preparedStmt.setString(2, refNo);
 preparedStmt.setString(3, username);
 preparedStmt.setString(4,contact);
 preparedStmt.setString(5, email);
 preparedStmt.setString(6, city);
preparedStmt.setString(7, date);
preparedStmt.setString(8, remark);
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
public String readInquiries()
{
String output = "";
try
 {
Connection con = connect();
 if (con == null)
{return "Error while connecting to the database for reading."; }
// Prepare the html table to be displayed
output = "<table border='1'><tr><th>refNo</th><th>username</th>" + "<th>contact</th>" + "<th>email</th>" + "<th>city</th>" + "<th>date</th>" + "<th>remark</th>" +"<th>Update</th><th>Remove</th></tr>";

String query = "select * from inquiry";
 Statement stmt = con.createStatement();
 ResultSet rs = stmt.executeQuery(query);
//iterate through the rows in the result set
 while (rs.next())
{
 String id = Integer.toString(rs.getInt("id"));
 String refNo = rs.getString("refNo");
 String username = rs.getString("username");
 String contact = rs.getString("contact");
 String email = rs.getString("email");
String city = rs.getString("city");
 String date = rs.getString("date");
 String remark = rs.getString("remark");
 // Add into the html table
 output += "<tr><td>" + refNo + "</td>";
output += "<td>" + username + "</td>";
 output += "<td>" + contact + "</td>";
 output += "<td>" + email + "</td>";
 output += "<td>" + city + "</td>";
 output += "<td>" + date + "</td>";
output += "<td>" + remark + "</td>";
// buttons
output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"+ "<td><form method='post' action='inquiries.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"+ "<input name='id' type='hidden' value='" + id+ "'>" + "</form></td></tr>";}
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
public String updateInquiry(String id, String refNo, String username, String contact, String email,  String city, String date, String remark)
{
String output = "";
try
{
Connection con = connect();
if (con == null)
{return "Error while connecting to the database for updating."; }
// create a prepared statement
String query = "UPDATE inquiry SET refNo=?,username=?,contact=?,email=?, city=?,date=?, remark=? WHERE id=?";
PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
preparedStmt.setString(1, refNo);
preparedStmt.setString(2, username);
preparedStmt.setString(3, contact);
preparedStmt.setString(4, email);
preparedStmt.setString(5, city);
preparedStmt.setString(6, date);
preparedStmt.setString(7, remark);
preparedStmt.setInt(8, Integer.parseInt(id));
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
public String deleteInquiry(String id)
{
String output = "";
try
{
Connection con = connect();
if (con == null)
{return "Error while connecting to the database for deleting."; }
// create a prepared statement
String query = "delete from inquiry where id=?";
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

