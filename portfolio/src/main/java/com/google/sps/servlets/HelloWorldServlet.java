package com.google.sps.servlets;

import java.io.IOException;
import com.google.gson.Gson;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import java.util.Random; //Random numbers.
import java.util.ArrayList; //Arrays.

/** Handles requests sent to the /hello URL. Try running a server and navigating to /hello! */
@WebServlet("/hello")
public class HelloWorldServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    //response.setContentType("text/html;");

    //Random rand = new Random(); //Instance of Random class.

    /*String[] responses = {"Hello world!", "Hello AlejandroSparza!",
        "I love Google!", "Hi SPS!", "Greetings from Mexico!",
        "I'm studying at Tec de Monterrey!"}; //Random strings.
    */

    //Random messages:
    ArrayList<String> messages = new ArrayList<String>();
    messages.add("Winter is coming!"); //Additions:
    messages.add("One ring to rull them all!");
    messages.add("May the force be with you!");

    //int index = rand.nextInt(responses.length); //Random number from 0 to
        //(responses.length - 1), both inclusive.

    //response.getWriter().println(convertToJson(messages)); //Adding
        //a random response.

    // Convert the strings to JSON
    //String json = convertToJson(messages);
    String json = convertToJsonUsingGson(messages);

    // Send the JSON as the response
    response.setContentType("application/json;");
    response.getWriter().println(json);
  }

  /**
   * Converts a ServerStats instance into a JSON string using manual String concatentation.
   */
  private String convertToJson(ArrayList<String> messages) {
    String json = "{";
    json += "\"string0\": ";
    json += "\"" + messages.get(0) + "\"";
    json += ", ";
    json += "\"string1\": ";
    json += "\"" + messages.get(1) + "\"";
    json += ", ";
    json += "\"string2\": ";
    json += "\"" + messages.get(2) + "\"";
    json += "}";
    return json;
  }

   /**
   * Converts a ServerStats instance into a JSON string using the Gson library. Note: We first added
   * the Gson library dependency to pom.xml.
   */
  private String convertToJsonUsingGson(ArrayList<String> messages) {
    Gson gson = new Gson();
    String json = gson.toJson(messages);
    return json;
  }
}