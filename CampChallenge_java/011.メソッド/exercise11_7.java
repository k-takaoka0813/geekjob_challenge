/*
引数、戻り値2
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.camp;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;

/**
 *
 * @author kazukitakaoka
 */
@WebServlet(name = "exercise11_7", urlPatterns = {"/exercise11_7"})
public class exercise11_7 extends HttpServlet {

  /**
   * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
   * methods.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  
  private ArrayList<String> getData(String id){
    ArrayList<String> data = new ArrayList<String>();
    switch(id){
      case "abc":
        data.add("1933-12-23");
        data.add("東京都千代田区千代田１−１");
        break;
      case "def":
        data.add("1954-09-21");
        data.add(null);
        break;
      case "ghi":
        data.add("1906-06-14");
        data.add("ワシントンD.C.ペンシルベニア大通り1600");
        break;
      default:
        
    }
    return data;
  }
  
  private boolean printProfile(PrintWriter out,String id){
    ArrayList<String> data = (ArrayList<String>)getData(id).clone();
      out.print(id+"のプロフィールです。<br>");
      for(int i = 0; i < data.size(); i++){
        if(data.get(i) == null){
          continue;
        }
        out.print(data.get(i) + "<br>");
      }
    return true;
  }
  
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    try (PrintWriter out = response.getWriter()) {
      printProfile(out,"abc");
      printProfile(out,"def");
      printProfile(out,"ghi");
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
