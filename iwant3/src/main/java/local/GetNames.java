package local;

import java.io.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class GetNames extends HttpServlet {
  private static final long serialVersionUID = 1L;

  // Db configuration
  private static final String DB_NAME = "test";
  private static final String DB_USER = "test";
  private static final String DB_PASSWORD = "test";

  private DbConnection db;

  public GetNames() throws InstantiationException, IllegalAccessException,
      ClassNotFoundException, SQLException {
    // Connect to the database
    db = new DbConnection(DB_NAME, DB_USER, DB_PASSWORD);
  }

  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    PrintWriter out = response.getWriter();

    Ford ford = new Ford();
    testPl(out, ford);

    Gson gson = new Gson();

    ArrayList<HashMap<String, String>> entries = db.select("SELECT name, surname FROM names");

    out.print(gson.toJson(entries));  

    out.flush();
    out.close();
  }
  
  public void testPl(PrintWriter out, Car car) {
    out.print("isOn=" + car.isOn());
    out.print("brand=" + car.getBrand());
  }
}