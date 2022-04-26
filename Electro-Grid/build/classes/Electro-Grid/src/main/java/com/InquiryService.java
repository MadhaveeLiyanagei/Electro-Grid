package com;
import model.Inquiry;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/Inquiries")
public class InquiryService
{
Inquiry inquiryObj = new Inquiry();
@GET
@Path("/readinquiry")
@Produces(MediaType.TEXT_HTML)
public String readInquiries()
{
return inquiryObj.readInquiries();
}

@POST
@Path("/insertinquiry")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.TEXT_PLAIN)
public String insertInquiry(@FormParam("refNo") String refNo,
		 @FormParam("username") String username,
		 @FormParam("contact") String contact,
		 @FormParam("email") String email,
		 @FormParam("city") String city,
		 @FormParam("date") String date,
		 @FormParam("remark") String remark)
		{
		 String output = inquiryObj.insertInquiry(refNo, username,contact, email, city,date,remark);
			return output;

		}

@PUT
@Path("/updateinquiry")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.TEXT_PLAIN)
public String updateInquiry(String inquiryData)
{
//Convert the input string to a JSON object
 JsonObject inquiryObject = new JsonParser().parse(inquiryData).getAsJsonObject();
//Read the values from the JSON object
 String id = inquiryObject.get("id").getAsString();
 String refNo = inquiryObject.get("refNo").getAsString();
 String username = inquiryObject.get("username").getAsString();
 String contact = inquiryObject.get("contact").getAsString();
 String email = inquiryObject.get("email").getAsString();
 String city = inquiryObject.get("city").getAsString();
 String date = inquiryObject.get("date").getAsString();
 String remark = inquiryObject.get("remark").getAsString();
 String output = inquiryObj.updateInquiry(id, refNo, username, contact, email,city, date, remark);
 return output;
}

@DELETE
@Path("/deletinquiry")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.TEXT_PLAIN)
public String deleteInquiry(String inquiryData)
{
//Convert the input string to an XML document
 Document doc = Jsoup.parse(inquiryData, "", Parser.xmlParser());

//Read the value from the element <itemID>
 String id = doc.select("id").text();
 String output = inquiryObj.deleteInquiry(id);
return output;
}
}

