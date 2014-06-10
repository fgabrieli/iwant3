package local;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.google.gson.Gson;

import java.sql.SQLException;

public class Category extends HttpServlet {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private DbConnection db;
  private Logger logger;

  public Category() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
    // Connect to the database
    db = new DbConnection(Config.DB_NAME, Config.DB_USER, Config.DB_PASSWORD);

    logger = new Logger();
  }

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String action = request.getParameter("action");

    try {
      executeAction(action, request.getParameter("data"));
    } catch (NullPointerException e) {
      if (request.getParameter("action") == null) {
        logger.log("action is not valid");
      } else if (request.getParameter("data") == null) {
        logger.log("data is not valid");
      }
    } catch (SQLException e) {
      logger.log("Failed executing action: " + action);
    }
  }

  private void executeAction(String action, String data) throws SQLException {
    logger.log("action=" + action);

    Gson gson = new Gson();
    CategoryData category = gson.fromJson(data, CategoryData.class);

    try {
      if (action.equals("add") == true) {
        db.insertQuery("INSERT INTO category (name) VALUES (\"" + category.name + "\")");
      } else if (action == "update") {

      } else if (action == "delete") {

      }
    } catch (SQLException e) {
      logger.log("Failed executing action");
    }
  }
}