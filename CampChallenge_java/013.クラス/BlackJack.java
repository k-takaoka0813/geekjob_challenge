/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.camp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kazukitakaoka
 */
@WebServlet(name = "BlackJack", urlPatterns = {"/BlackJack"})
public class BlackJack extends HttpServlet {

  //設定
  public static final int GAME_MAX = 1000; //何回ゲームやるか
  
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    try (PrintWriter out = response.getWriter()) {
      int win = 0;
      int lose = 0;
      int draw = 0;
      int upcard = 0;
      log(out,"[システム]ブラックジャックシミュレーター（"+GAME_MAX+"回）を開始します。※このゲームではAは1として扱います。ナチュラル21は成立しません。");
      //ゲーム処理 GAME_MAXまで繰り返す
      for(int match_num = 1; match_num <= GAME_MAX ; match_num++){
        log(out,"[システム]"+ match_num + "回戦を開始します…");
        //ディーラーとプレイヤーの用意
        Dealer dealer = new Dealer();
        int dealer_hit = 0;
        int dealer_pt = 0;
        User player = new User();
        int player_hit = 0;
        int player_pt = 0;
        //ディール処理
        dealer.setCard(dealer.deal());
        player.setCard(dealer.deal());
        log(out,"[システム]プレイヤーの手札");
        for (int num : player.getHands()){
          log(out,num);
        }
        upcard = dealer.getUpCard();
        log(out,"[ディーラー]私のUPカードは" + upcard + "です。");
        
        while(true){
          //プレイヤーの思考フェイズ
          log(out,"[プレイヤー]今の私の合計値は" + player.open() + "なので…");
          if( player.checkSum(upcard) ){
            log(out,"[プレイヤー]hitします。");
            player.setCard(dealer.hit());
            ++player_hit;
          }
          else{
            log(out,"[プレイヤー]standします。");
            player_pt = player.open();
            break;
          }
        }
        
        while(true){
          //ディーラーの調整フェイズ
          log(out,"[システム]ディーラーの合計値：" + dealer.open());
          if( dealer.checkSum(upcard) ){
            log(out,"[ディーラー]17以下なので引きます。");
            dealer.setCard(dealer.hit());
            ++dealer_hit;
          }
          else{
            log(out,"[ディーラー]17以上になりました。");
            dealer_pt = dealer.open();
            break;
          }
        }
        
        log(out,"[システム]プレイヤーの最終合計値：" + player_pt);
        log(out,"[システム]ディーラーの最終合計値：" + dealer_pt);
        
        //勝敗判定
        if( player_pt == 21 ){
          //プレイヤーが２１で
          if( dealer_pt != 21 ){
            //ディーラーが２１でなければプレイヤーの勝利
            ++win;
            log(out,"[システム]プレイヤーの勝利です。");
          }
          else if( player_hit < dealer_hit ){
            //プレイヤーの方がヒット数がすくないのでプレイヤーの勝ち
            ++win;
            log(out,"[システム]プレイヤーの方がヒット数が少ないのでプレイヤーの勝利です。");
          }
          else if( dealer_hit < player_hit ){
            //ディーラーの方がヒット数が少ないのでディーラーの勝ち
            ++lose;
            log(out,"[システム]ディーラーの方がヒット数が少ないのでプレイヤーの敗北です。");
          }
          else if( dealer_hit == player_hit ){
            //同じ点数で同じ回数だけヒットしているので引き分け
            ++draw;
            log(out,"[システム]お互いに同じ数だけヒットしているので引き分けです。");
          }
        }
        else if( 21 < player_pt ){
          //プレイヤーが２１を超えている（ディーラーがバストしていてもディーラーの勝ち）
          ++lose;
          log(out,"[システム]プレイヤーのバストによりプレイヤーの敗北です。");
        }
        else if( 21 < dealer_pt ){
          //プレイヤーが２１を超えておらずディーラーが２１を超えている
          ++win;
          log(out,"[システム]ディーラーのバストによりプレイヤーの勝利です。");
        }
        else if( dealer_pt < player_pt ){
          //両者とも２１を超えてないので大きい方が勝ち
          ++win;
          log(out,"[システム]21に近いのでプレイヤーの勝利です。");
        }
        else if(  player_pt < dealer_pt ){
          //両者とも２１を超えてないので大きい方が勝ち
          ++lose;
          log(out,"[システム]ディーラーの方が21に近いのでプレイヤーの敗北です。");
        }
        else if(  player_pt == dealer_pt ){
          //両者とも２１を超えてないので大きい方が勝ち
          ++draw;
          log(out,"[システム]同じ数字なので引き分けです。");
        }
      }
      log(out,"[システム]"+ GAME_MAX + "回中" + win + "勝"+lose+"敗"+draw+"引き分け。勝率は" + (((float)win/(float)GAME_MAX)*100) + "%です。");
    }
  }

  public void log(PrintWriter out,Object str){
    out.println(str+"</br>");
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


abstract class Human{
  protected ArrayList<Integer> myCards; //手札
  
  abstract int open();  //手札の合計値を返す
  abstract void setCard(ArrayList<Integer> cards);  //引数を手札に加える
  abstract boolean checkSum(int upCard);  //現在の状況でヒットするかしないか
  
  public ArrayList<Integer> getHands(){
    return this.myCards;
  }
}

//ディーラーのクラス
class Dealer extends Human{
  
  protected ArrayList<Integer> cards;
  
  Dealer(){
    this.cards = new ArrayList<Integer>();
    this.myCards = new ArrayList<Integer>();
    //デッキの用意
    for(int i = 0; i <= 3; i++){
      for(int num = 1 ; num <= 13; num++){
        this.cards.add(num);
      }
    }
    Collections.shuffle(this.cards);
  }
  
  @Override
  public int open(){
    //手札の合計値を返す
    int total = 0;
    for (int num : this.myCards){
      total += num;
    }
    return total;
  }
  
  @Override
  public void setCard(ArrayList<Integer> cards){
    //手札にカードを追加する
    for (int card : cards ) {
      if( 10 <= card ){
        //J〜K（11〜13）は10として扱う
        this.myCards.add(10);
      }
      else{
        this.myCards.add(card);
      }
    }
  }

  @Override
  public boolean checkSum(int upCard){
    //ディーラーは17以下の場合は必ず引かなければならない
    if(open() < 17){
      //自分が17未満は無条件でHitする。
      return true;
    }
    else{
      //17以上になった時点で無条件でStandする。
      return false;
    }
  }
  
  public ArrayList<Integer> deal(){
    ArrayList<Integer> result = new ArrayList<Integer>();
    result.add(this.cards.get(0));
    result.add(this.cards.get(1));
    this.cards.remove(0);
    this.cards.remove(1);
    return result;
  }
  
  public ArrayList<Integer> hit(){
    ArrayList<Integer> result = new ArrayList<Integer>();
    result.add(this.cards.get(0));
    this.cards.remove(0);
    return result;
  }
  
  public int getUpCard(){
    return this.myCards.get(0);
  }
}


//プレイヤーのクラス
class User extends Human{
  
  User(){
    this.myCards = new ArrayList<Integer>(); 
  }
  
  @Override
  public int open(){
    //手札の合計値を返す
    int total = 0;
    for (int num : this.myCards){
      total += num; 
    }
    return total;
  }
  
  @Override
  public void setCard(ArrayList<Integer> cards){
    //手札にカードを追加する
    for (int card : cards ) {
      if( 10 <= card ){
        //J〜K（11〜13）は10として扱う
        this.myCards.add(10);
      }
      else{
        this.myCards.add(card);
      }
    }
  }

  @Override
  public boolean checkSum(int upCard){
    //プレイヤーがヒットすべきか判断
    int total = open();
    
    if(total < 11){
      //自分が11以下は無条件でHitする。
      return true;
    }
    else if(total == 21){
      //自分が２１の場合は無条件でStandする。
      return false;
    }
    else if(12 <= total && upCard <= 6){
      //自分が12以上でディーラーのUPカードが6以下の場合はstandする。
      return false;
    }
    else if(total < 17 && 7 <= upCard){
      //自分が16以下でディーラーのUPカードが7以上の場合はhitする。
      return true;
    }
    return false;
  }
}