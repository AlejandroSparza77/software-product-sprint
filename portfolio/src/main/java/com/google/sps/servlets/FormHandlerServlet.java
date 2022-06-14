package com.google.sps.servlets;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.sampled.SourceDataLine;

@WebServlet("/form-handler")
public class FormHandlerServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    // Get the value entered in the form.
    String firstName = request.getParameter("first-name");
    String lastName = request.getParameter("last-name");
    boolean myTalent = Boolean.parseBoolean(getParameter(request, "talent", "false"));
    boolean myCuriosity = Boolean.parseBoolean(getParameter(request, "curiosity", "false"));
    String specialization = request.getParameter("specialization");
    String joke = request.getParameter("joke");

    // Print the value so you can see it in the server logs.
    System.out.println("Your first name is: " + firstName);
    System.out.println("Your last name is: " + lastName);
    System.out.println("Your specialization is: " + specialization);
    System.out.println("Your best joke is: " + joke);

    //Redirect the user:
    response.sendRedirect("https://sesparzagonzalez-sps-summer22.appspot.com/");

    if (myTalent == true && myCuriosity == true) {
        System.out.println("You are here for: your curiosity and your talent.");
        // Write the value to the response so the user can see it.
        response.getWriter().println("Your name is: " + firstName + " "
            + lastName + ", your specialization is: " + specialization + ", "
            + "you are here for your curiosity and your talent, and actually "
            + "you are funny. Now go back to the previous page.");
  
    }
    else if (myTalent == true && myCuriosity == false) {
        System.out.println("You are here for: your talent.");
        response.getWriter().println("Your name is: " + firstName + " "
        + lastName + ", your specialization is: " + specialization + ", you "
        + "are here for your talent, and actually you are funny. Now go back "
        + "to the previous page.");
    }
    else if (myTalent == false && myCuriosity == true) {
        System.out.println("You are here for: your curiosity.");
        response.getWriter().println("Your name is: " + firstName + " "
        + lastName + ", your specialization is: " + specialization + ", you "
        + "are here for your curiosity, and actually you are funny. Now go "
        + "back to the previous page.");
    }
    else{
        System.out.println("Why are you here?");
        response.getWriter().println("Your name is: " + firstName + " "
        + lastName + ", your specialization is: " + specialization + ", I do "
        + "not know why you are here, but actually you are funny. Now go back "
        + "to the previous page.");
    }

  }

    /**
   * @return the request parameter, or the default value if the parameter
   *         was not specified by the client
   */
    private String getParameter(HttpServletRequest request, String name, String defaultValue) {
        String value = request.getParameter(name);
        if (value == null) {
        return defaultValue;
        }
        return value;
    }

}