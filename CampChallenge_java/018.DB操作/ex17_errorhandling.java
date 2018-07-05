/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * エラーハンドリングを含んだ接続の確立
 * @author kazukitakaoka
 */
@WebServlet(urlPatterns = {"/ex17_errorhandling"})
public class ex17_errorhandling extends HttpServlet {

  /**
   * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
   * methods.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    try (PrintWriter out = response.getWriter()) {
      Connection db_con = null;
      PreparedStatement db_st = null;
      ResultSet db_data = null;
      try{
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        db_con = DriverManager.getConnection("jdbc:mysql://localhost:8889/test","root","root");
        db_st = db_con.prepareStatement("SELECT * FROM profiles WHERE age = ?");
        db_st.setInt(1, 24);
        db_data = db_st.executeQuery();
        while(db_data.next()){
          out.print("名前：" + db_data.getString("name") + "</br>");
        }
        db_data.close();
        db_st.close();
        db_con.close();
      } catch (SQLException e_sql){
        out.println("接続エラー："+e_sql.toString());
      } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e){
        out.println(e.toString()+"エラー：");
      } finally {
        if (db_con != null){
          try {
            db_con.close();
          } catch (Exception e_con) {
              System.out.println(e_con.getMessage());
          }
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
    processRequest(request, response);
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
    processRequest(request, response);
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
