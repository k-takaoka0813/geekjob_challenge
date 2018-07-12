package inventoryControl;

import com.mysql.cj.log.Log;
import com.sun.security.auth.login.ConfigFile;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import inventoryControl.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Add extends HttpServlet {
  /** Handles the HTTP <code>GET</code> method.
   * @param request servlet request
   * @param response servlet response
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    RequestDispatcher rd = null;
    rd = getServletConfig().getServletContext().getRequestDispatcher("/login.jsp");
    rd.forward(request, response);
    //processRequest(request, response);
  }

  /** Handles the HTTP <code>POST</code> method.
   * @param request servlet request
   * @param response servlet response
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    try {
      //ユーザーIDとパスワードのセット
      request.setCharacterEncoding("utf-8");
      String formName = request.getParameter("productName");
      String formPrice = request.getParameter("price");
      Class.forName("com.mysql.jdbc.Driver");
      Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:8889/inventory?" +
              "user=root&password=root&useUnicode=true&characterEncoding=utf-8");
      PreparedStatement st = conn.prepareStatement("INSERT INTO products (name,price) VALUE (?,?)");
      st.setString(1, formName);
      st.setInt(2, Integer.parseInt(formPrice));
      st.executeUpdate();
      RequestDispatcher rd = getServletConfig().getServletContext().getRequestDispatcher("/menu.jsp");
      rd.forward(request, response);
    } catch (ClassNotFoundException ex) {
      Logger.getLogger(Add.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
      Logger.getLogger(Add.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  /** Returns a short description of the servlet. */
  public String getServletInfo() {
    return "Short description";
  }

}