package com;
import model.Payment;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/Payments")
public class PaymentService
{
 Payment paymentObj = new Payment();
@GET
@Path("/readpayment")
@Produces(MediaType.TEXT_HTML)
public String readPayment()
{
return paymentObj.readPayment();
}

@POST
@Path("/insertpayment")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.TEXT_PLAIN)
public String insertPayment(@FormParam("name") String name,
		 @FormParam("billid") String billid,
		 @FormParam("cardno") String cardno,
		 @FormParam("cvc") String cvc,
		 @FormParam("expdate") String expdate)
		{
		 String output = paymentObj.insertPayment(name, billid, cardno, cvc, expdate);
			return output;
		}

@PUT
@Path("/updatepayment")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.TEXT_PLAIN)
public String updatePayment(String paymentData)
{
//Convert the input string to a JSON object
 JsonObject paymentObject = new JsonParser().parse(paymentData).getAsJsonObject();
//Read the values from the JSON object
 String id = paymentObject.get("id").getAsString();
 String name = paymentObject.get("name").getAsString();
 String billid = paymentObject.get("billid").getAsString();
 String cardno = paymentObject.get("cardno").getAsString();
 String cvc = paymentObject.get("cvc").getAsString();
 String expdate = paymentObject.get("expdate").getAsString();
 String output = paymentObj.updatePayment(id, name, billid, cardno, cvc, expdate);
 return output;
}

@DELETE
@Path("/deletepayment")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.TEXT_PLAIN)
public String deletePayment(String paymentData)
{
//Convert the input string to an XML document
 Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser());

//Read the value from the element <itemID>
 String id = doc.select("id").text();
 String output = paymentObj.deletePayment(id);
return output;
}


}

