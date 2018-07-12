package inventoryControl;
import java.io.*;

public class UserBean implements Serializable {
  private String name;
  private String password;
  private String address;

  /** Creates a new instance of UserBean */
  public UserBean() {
    name = "No Name";
    address = "No Address";
  }

  public String getaName() {
    return name;
  }

  public void setaName(String na) {
    name = na;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String ps) {
    password = ps;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String adr) {
    address = adr;
  }
}