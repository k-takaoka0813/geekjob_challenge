package inventoryControl;
import java.sql.*;

public class UserAuthBean {
  
  private final Connection db_con;
  
  public UserAuthBean() throws ClassNotFoundException, IllegalAccessException, SQLException, InstantiationException{
    //DB接続
    Class.forName("com.mysql.jdbc.Driver").newInstance();
    db_con = DriverManager.getConnection("jdbc:mysql://localhost:8889/inventory","root","root");
  }

  public boolean execute(UserBean ub) throws SQLException {
    PreparedStatement db_st = db_con.prepareStatement("SELECT * FROM user WHERE name = ?");
    db_st.setString(1, ub.getaName());
    ResultSet db_data = db_st.executeQuery();
    if(db_data.next()){
      //ユーザーが存在する
      if (ub.getPassword().equals(db_data.getString("pass")) == true) {
        //パスワードが一致
        return true;
      } else {
        //パスワード不一致
        return false;
      }
    }
    else{
      //ユーザーが存在しない
      return false;
    }
  }
}