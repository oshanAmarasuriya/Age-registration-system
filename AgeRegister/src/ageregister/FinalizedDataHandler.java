/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ageregister;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Oshan
 */


public class FinalizedDataHandler {
    
    
    static Connection con=DBHandler2.getConnection();
    public Date date=new Date();
    SimpleDateFormat sm= new SimpleDateFormat("yyyy-MM-dd");
 
     public  void getData(DefaultTableModel tableMod){
    
     
     
     ResultSet rst;
     PreparedStatement obj;
     String sql="SELECT * FROM finalizedRows";
        try{
            obj=con.prepareStatement(sql);
            rst=obj.executeQuery();
            
           

            while(rst.next()){
                 tableMod.addRow(new Object[]{rst.getInt("ID"),rst.getString("name"),rst.getString("GNdiv"),rst.getString("address"),rst.getString("contactNumber"),rst.getString("birthday"),rst.getString("QR"),rst.getString("completedDate")});
            }
             }
        catch(Exception e){
            e.printStackTrace();
            
        }
    }
     
     public boolean addData(Container cnt){
        String add_query="INSERT INTO finalizedRows (name,GNdiv,address,contactNumber,birthday,QR,completedDate) VALUES (?,?,?,?,?,?,?)";
//        Connection con =DBHandler.getConnection();
        
        try{
            PreparedStatement st=con.prepareStatement(add_query);
            st.setString(1, cnt.getName());
            st.setString(2, cnt.getGNdiv());
            st.setString(3, cnt.getAddress());
            st.setString(4, cnt.getContact());
            st.setString(5, cnt.getBirthday());
            st.setString(6, cnt.getQR());
            st.setString(7,sm.format(date));
            
            st.executeUpdate();
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
    }
}
     
     public static void deleteRow(int id){
        String del_query="DELETE FROM finalizedRows WHERE ID=?";
        Connection con =DBHandler2.getConnection();
        
        try{
            PreparedStatement st=con.prepareStatement(del_query);
            st.setInt(1, id);
            st.execute();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
     
     public void deleteBunchOfData(ArrayList<Integer> list,String query){
        try{
            for(int i=0;i<list.size();i++){
            PreparedStatement obj1=con.prepareStatement(query);           
            obj1.setInt(1, list.get(i));
            obj1.execute();
            }
        }
        catch(Exception e){
            e.printStackTrace();
            
        }
     }
     
      public int getStats(){
         int count=0;
         try {
             PreparedStatement st1=con.prepareStatement("SELECT COUNT(ID) AS rowcount FROM finalizedRows");
             ResultSet rs1=st1.executeQuery();
             while(rs1.next()){
                 count=rs1.getInt("rowcount");
             }
         }
          catch(Exception e){
            e.printStackTrace();
            
        }
          return count;
      }
      
      public static void deleteAll(){
        String del_query="DELETE FROM finalizedRows";
        Connection con =DBHandler2.getConnection();
        
        try{
            PreparedStatement st=con.prepareStatement(del_query);
            
            st.execute();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
     
    
    
    
}
