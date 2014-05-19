package local;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.google.gson.Gson;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class GetNames extends HttpServlet {
  private static final long serialVersionUID = 1L;

  // Db configuration
  private static final String DB_NAME = "test";
  private static final String DB_USER = "test";
  private static final String DB_PASSWORD = "test";

  private Db db;

  public GetNames() throws InstantiationException, IllegalAccessException,
      ClassNotFoundException, SQLException {
    // Connect to the database
    db = new Db(DB_NAME, DB_USER, DB_PASSWORD);
  }

  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    PrintWriter out = response.getWriter();

    Gson gson = new Gson();

    ArrayList<HashMap<String, String>> entries = db
        .select("SELECT name, surname FROM names LEFT JOIN surnames ON names.id=surnames.name_id");

    out.print(gson.toJson(entries));

    out.flush();
    out.close();
  }
}