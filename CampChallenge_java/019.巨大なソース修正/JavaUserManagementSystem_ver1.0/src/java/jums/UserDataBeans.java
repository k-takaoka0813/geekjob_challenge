package jums;

import java.util.Date;

/**
 * ユーザー情報を持ちまわるJavaBeans
 * データベースのカラムと型に対応させている(DTO)。データの挿入、取り出しどちらにも便利
 * @version 1.00
 * @author hayashi-s
 */
public class UserDataBeans {
    private String name;
    private int y;
    private int m;
    private int d;
    private String tell;
    private int type;
    private String comment;    
        
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    
    public int getY(){
        return y;
    }
    public void setY(int y){
        this.y = y;
    }
    
    public int getM(){
        return m;
    }
    public void setM(int m){
        this.m = m;
    }
    
    public int getD(){
        return d;
    }
    public void setD(int d){
        this.d = d;
    }
    
    public String getTell(){
        return tell;
    }
    public void setTell(String tell){
        this.tell = tell;
    }
    
    public int getType(){
        return type;
    }
    public void setType(int type){
        this.type = type;
    }
    
    public String getComment(){
        return comment;
    }
    public void setComment(String comment){
        this.comment = comment;
    }
    
}
