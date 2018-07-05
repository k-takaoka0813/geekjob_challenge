/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 特定のレコードの削除
 * @author kazukitakaoka
 */
@WebServlet(urlPatterns = {"/ex17_specificUpdate"})
public class ex17_specificUpdate extends HttpServlet {

  /**
   * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
   * methods.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   * @throws java.text.ParseException
   */
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException, ParseException, SQLException {
    response.setContentType("text/html;charset=UTF-8");
    try (PrintWriter out = response.getWriter()) {
      Connection db_con = null;
      PreparedStatement db_st = null;
      ResultSet db_data = null;
      try{
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        db_con = DriverManager.getConnection("jdbc:mysql://localhost:8889/test","root","root");
        
        db_st = db_con.prepareStatement("UPDATE profiles SET tel = '0120-828-828' WHERE profilesID = ?");
        db_st.setInt(1, 4);
        db_st.executeUpdate();
        
        db_st = db_con.prepareStatement("SELECT * FROM profiles");
        db_data = db_st.executeQuery();
        out.print("<table><tr><th>ID</th><th>名前</th><th>電話番号</th><th>年齢</th><th>誕生日</th></tr>");
        while(db_data.next()){
          out.print("<tr><td>" + db_data.getInt("profilesID") + "</td><td>" + db_data.getString("name") + "</td><td>" + db_data.getString("tel") + "</td><td>" + db_data.getInt("age") + "</td><td>" + db_data.getDate("birthday") + "</td><td></tr>");
        }
        out.print("</table>");
        db_data.close();
        db_st.close();
        db_con.close();
      } catch (SQLException e_sql){
        out.println("接続エラー："+e_sql.toString());
      } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e){
        out.println(e.toString()+"エラー：");
      } finally {
        if (db_con != null){
          db_con.close();
        }
      }
      
    }
  }

  // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
  /**
   * Handles the HTTP <code>GET</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    try {
      processRequest(request, response);
    } catch (ParseException ex) {
      Logger.getLogger(ex17_tableInsert.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
      Logger.getLogger(ex17_tableInsert.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  /**
   * Handles the HTTP <code>POST</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    try {
      processRequest(request, response);
    } catch (ParseException ex) {
      Logger.getLogger(ex17_tableInsert.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
      Logger.getLogger(ex17_tableInsert.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  /**
   * Returns a short description of the servlet.
   *
   * @return a String containing servlet description
   */
  @Override
  public String getServletInfo() {
    return "Short description";
  }// </editor-fold>

}
