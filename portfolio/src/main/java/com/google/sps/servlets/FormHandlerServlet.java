package com.google.sps.servlets;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.sampled.SourceDataLine;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.KeyFactory;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery.OrderBy;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import com.Task;
//import org.jsoup.Jsoup;
//import org.jsoup.safety.Whitelist;

@WebServlet("/form-handler")
public class FormHandlerServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    // Get the value entered in the form.
    String firstName = request.getParameter("first-name");
    String lastName = request.getParameter("last-name");
    boolean yourTalent = Boolean.parseBoolean(getParameter(request, "talent", "false"));
    boolean yourCuriosity = Boolean.parseBoolean(getParameter(request, "curiosity", "false"));
    String specialization = request.getParameter("specialization");
    String joke = request.getParameter("joke");
    long timestamp = System.currentTimeMillis();

    // Print the value so you can see it in the server logs.
    System.out.println("Your first name is: " + firstName);
    System.out.println("Your last name is: " + lastName);
    System.out.println("Your specialization is: " + specialization);
    System.out.println("Your best joke is: " + joke);
    System.out.println("Your timestamp is: " + timestamp);

    //Datastore:
    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    KeyFactory keyFactory = datastore.newKeyFactory().setKind("Task");
    FullEntity taskEntity =
        Entity.newBuilder(keyFactory.newKey())
            .set("firstName", firstName)
            .set("lastName", lastName)
            .set("yourTalent", yourTalent)
            .set("yourCuriosity", yourCuriosity)
            .set("specialization", specialization)
            .set("joke", joke)
            .set("timestamp", timestamp)
            .build();
    datastore.put(taskEntity);

    Query<Entity> query =
        Query.newEntityQueryBuilder().setKind("Task").setOrderBy(OrderBy.desc("timestamp")).build();
    QueryResults<Entity> results = datastore.run(query);
    
    List<Task> tasks = new ArrayList<>();
    while (results.hasNext()) {
      Entity entity = results.next();

      long id = entity.getKey().getId();
      //firstName = entity.getString("firstName");
      //lastName = entity.getString("lastName");
      yourTalent = entity.getBoolean("yourTalent");
      yourCuriosity = entity.getBoolean("yourCuriosity");
      specialization = entity.getString("specialization");
      joke = entity.getString("joke");
      timestamp = entity.getLong("timestamp");

      Task task = new Task(id, yourTalent, yourCuriosity,
        specialization, joke, timestamp);
      tasks.add(task);
    }

    Gson gson = new Gson();

    //Redirect the user:
    //response.sendRedirect("https://sesparzagonzalez-sps-summer22.appspot.com/");

    //Responses:

    if (yourTalent == true && yourCuriosity == true) {
        System.out.println("You are here for: your curiosity and your talent.");
        // Write the value to the response so the user can see it.
        /*
        response.getWriter().println("Your name is: " + firstName + " "
            + lastName + ", your specialization is: " + specialization + ", "
            + "you are here for your curiosity and your talent, and actually "
            + "you are funny. Now go back to the previous page.");
        */
    }
    else if (yourTalent == true && yourCuriosity == false) {
        System.out.println("You are here for: your talent.");
        /*
        response.getWriter().println("Your name is: " + firstName + " "
        + lastName + ", your specialization is: " + specialization + ", you "
        + "are here for your talent, and actually you are funny. Now go back "
        + "to the previous page.");
        */
    }
    else if (yourTalent == false && yourCuriosity == true) {
        System.out.println("You are here for: your curiosity.");
        /*
        response.getWriter().println("Your name is: " + firstName + " "
        + lastName + ", your specialization is: " + specialization + ", you "
        + "are here for your curiosity, and actually you are funny. Now go "
        + "back to the previous page.");
        */
    }
    else{
        System.out.println("Why are you here?");
        /*
        response.getWriter().println("Your name is: " + firstName + " "
        + lastName + ", your specialization is: " + specialization + ", I do "
        + "not know why you are here, but actually you are funny. Now go back "
        + "to the previous page.");
        */
    }

    response.setContentType("application/json;");
    response.getWriter().println(gson.toJson(tasks));

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