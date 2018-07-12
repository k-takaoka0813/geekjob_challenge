package inventoryControl;

import com.sun.security.auth.login.ConfigFile;
import java.io.*;
import java.net.*;
import javax.servlet.*;
import javax.servlet.http.*;
import inventoryControl.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Control extends HttpServlet {
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
    //Beanの作成
    inventoryControl.UserBean ub = null;
    ub = new inventoryControl.UserBean();
    //ユーザーIDとパスワードのセット
    String formun = request.getParameter("username");
    String formps = request.getParameter("password");
    ub.setaName(formun);
    ub.setPassword(formps);
    //パスワードの正誤をチェック
    inventoryControl.UserAuthBean uab = null;
    try {
      uab = new inventoryControl.UserAuthBean();
    } catch (ClassNotFoundException ex) {
      Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
      Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
    }

    HttpSession session = null;
    RequestDispatcher rd = null;
    try {
      if (uab.execute(ub) == true) {
        //認証に成功
        //セッションオブジェクトを作成
        if (checkSession(request) == true) {
          //セッションオブジェクトがある場合は獲得
          session = request.getSession(false);
        } else {
          //セッションオブジェクトがない場合は新規作成
          session = request.getSession(true);
        }
        //Beansをセッションオブジェクトに保存
        session.setAttribute("userbeanses", ub);
        session.setAttribute("login", "true");
        
        rd = getServletConfig().getServletContext().getRequestDispatcher("/menu.jsp");
      } else {
        //認証に失敗
        rd = getServletConfig().getServletContext().getRequestDispatcher("/failed.jsp");
      }
    } catch (SQLException ex) {
      Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
    }
    rd.forward(request, response);
  }

  /** Returns a short description of the servlet. */
  public String getServletInfo() {
    return "Short description";
  }

  //セッションオブジェクトのチェックメソッド
  public boolean checkSession(HttpServletRequest req) {
    HttpSession session = req.getSession(false);
    if (session != null) {
      return true;
    } else {
      return false;
    }
  }
}