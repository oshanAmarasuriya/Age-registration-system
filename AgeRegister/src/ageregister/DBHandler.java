/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ageregister;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 *
 * @author Oshan
 */
public class DBHandler {
//    public static void main(String args[]){
//        DBHandler.getConnection();
//    }
    public static Connection getConnection(){
        String dbURL = "jdbc:sqlite:database1.db";
//        String dbUsername="root";
//        String dbPassword ="root";
        
        try{
            //Class.forName("com.mysql.jdbc.Driver");
            Connection con =DriverManager.getConnection(dbURL);
            System.out.println("connected");
            return  con;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
        
    }
    public static void deleteRow(int id){
        String del_query="DELETE FROM datatable WHERE ID=?";
        Connection con =DBHandler.getConnection();
        
        try{
            PreparedStatement st=con.prepareStatement(del_query);
            st.setInt(1, id);
            st.execute();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
     public static boolean updateRow(int id,Container cn){
        String updateRow_query="UPDATE datatable SET name=? ,GNdiv=? ,address=? ,contactNo=? ,birthday=?  WHERE ID=?";
        Connection con =DBHandler.getConnection();
        
        try{
            PreparedStatement st=con.prepareStatement(updateRow_query);
            st.setInt(6,id);
            st.setString(1, cn.getName());
            st.setString(2, cn.getGNdiv());
            st.setString(3, cn.getAddress());
            st.setString(4, cn.getContact());
            st.setString(5, cn.getBirthday());
            
            st.execute();
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    
    public boolean addData(Container cnt){
        String add_query="INSERT INTO datatable (name,GNdiv,address,contactNo,birthday,flag,QR) VALUES (?,?,?,?,?,?,?)";
        Connection con =DBHandler.getConnection();
        
        try{
            PreparedStatement st=con.prepareStatement(add_query);
            st.setString(1, cnt.getName());
            st.setString(2, cnt.getGNdiv());
            st.setString(3, cnt.getAddress());
            st.setString(4, cnt.getContact());
            st.setString(5, cnt.getBirthday());
            st.setInt(6, 0);
            st.setString(7,"");
            
            st.executeUpdate();
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
    }
}
    
    public int[] getStats(){
         Connection con =DBHandler.getConnection();
         int[] counts=new int[4];//{all,not yet marked,marked, but not yet applied,appplied}
         
         try {
             PreparedStatement st1=con.prepareStatement("SELECT COUNT(ID) AS rowcount FROM datatable");
             ResultSet rs1=st1.executeQuery();
             while(rs1.next()){
                 counts[0]=rs1.getInt("rowcount");
             }
             PreparedStatement st2=con.prepareStatement("SELECT COUNT(ID) AS rowcount FROM datatable WHERE flag=0");
             ResultSet rs2=st2.executeQuery();
             while(rs2.next()){
                 counts[1]=rs2.getInt("rowcount");
             }
             PreparedStatement st3=con.prepareStatement("SELECT COUNT(ID) AS rowcount FROM datatable WHERE flag=1");
             ResultSet rs3=st3.executeQuery();
             while(rs3.next()){
                 counts[2]=rs3.getInt("rowcount");
             }
             PreparedStatement st4=con.prepareStatement("SELECT COUNT(ID) AS rowcount FROM datatable WHERE flag=2");
             ResultSet rs4=st4.executeQuery();
             while(rs4.next()){
                 counts[3]=rs4.getInt("rowcount");
             }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
      return counts;   
    }
   
}