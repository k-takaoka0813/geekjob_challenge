/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package challenge_app;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
/**
 *
 * @author kazukitakaoka
 */
public class ex15_10 {

  /**
   * @param args the command line arguments
   * @throws java.io.IOException
   */
  public static void main(String[] args) throws IOException {
    File fp = new File("log.txt");
    
    // Mathクラスを利用して乱数を生成します
    try (FileWriter fw = new FileWriter(fp)) {
      // 書き込み
      Calendar c = Calendar.getInstance();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
      fw.write(sdf.format(c.getTime())+" 処理を開始します。\n");
      fw.write("生成された乱数は「" + Math.random() + "」です。\n");
      Calendar c2 = Calendar.getInstance();
      SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
      fw.write(sdf2.format(c2.getTime())+" 処理を終了します。\n");
      // クローズ
      fw.close();
    }
    
  }
  
}
