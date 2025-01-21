/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ageregister;

import ageregister.view.All_search_results;
import ageregister.view.SearchResultsOnGND;
import ageregister.view.SearchResultsOnQR;
import ageregister.view.ViewAll;
import java.awt.Color;
import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Oshan
 */
public class FetchData {
 
    static Connection con=DBHandler.getConnection();
    ViewAll vall,v2;
    DefaultTableModel tbm,m_main,m;
    FetchFilteredData ffd;
    public FetchData(){}
    public FetchData(DefaultTableModel m_main,DefaultTableModel m,FetchFilteredData ffd,ViewAll v){
        this.m_main=m_main;
        this.m=m;
        this.ffd=ffd;
        this.v2=v;
    }
  
    
    public  void getData(DefaultTableModel tableMod,ViewAll va){
     this.vall=va;
     
     this.tbm=tableMod;
     ResultSet rst;
     PreparedStatement obj;
     String sql="SELECT * FROM datatable";
        try{
            obj=con.prepareStatement(sql);
            rst=obj.executeQuery();
            
           

            while(rst.next()){


                switch(rst.getInt("flag")){
                    case 0 : 
                        tableMod.addRow(new Object[]{rst.getInt("ID"),rst.getString("name"),rst.getString("GNdiv"),rst.getString("address"),rst.getString("contactNo"),rst.getString("birthday"),"",""});
                        break;
                    case 1:
                        tableMod.addRow(new Object[]{rst.getInt("ID"),rst.getString("name"),rst.getString("GNdiv"),rst.getString("address"),rst.getString("contactNo"),rst.getString("birthday"),"Marked",""});
                        break;
                    case 2:
                        tableMod.addRow(new Object[]{rst.getInt("ID"),rst.getString("name"),rst.getString("GNdiv"),rst.getString("address"),rst.getString("contactNo"),rst.getString("birthday"),"Applied",rst.getString("QR")});
                        break;
//                    case 3:
//                        tableMod.addRow(new Object[]{rst.getInt("ID"),rst.getString("name"),rst.getString("GNdiv"),rst.getString("address"),rst.getString("contactNo"),rst.getString("birthday"),"Completed",rst.getString("QR")});
//                        break;
                }
            }
            //foriegn
    
            vall.jTable1.setDefaultRenderer(Object.class, new TableCellRenderer(){
        private DefaultTableCellRenderer DEFAULT_RENDERER =  new DefaultTableCellRenderer();

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
       
                Component c = DEFAULT_RENDERER.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
     c.setBackground(Color.WHITE);
     c.setForeground(Color.BLACK);
//     if(table.getModel().getValueAt(row, 6).toString().equals("Completed")){
//
//        c.setBackground(new Color(5, 250, 107));  
//}   
     
     if((table.getColumnModel().getColumn(column).getIdentifier().equals("Marked or not"))&&(table.getModel().getValueAt(row, 6).toString().equals("Marked"))){//Here `OK` is the value of row

       
        c.setBackground(new Color(250,44,12));
        c.setForeground(Color.WHITE);
    
     }
     


     else if((table.getColumnModel().getColumn(column).getIdentifier().equals("QR"))&&!(table.getModel().getValueAt(row, 7).toString().equals(""))){//Here `OK` is the value of row

       
        c.setBackground(Color.YELLOW);
    
     }
     
    

 if(isSelected){
                   c.setBackground(Color.BLACK); //new Color(0, 120, 215)
                   c.setForeground(Color.WHITE);
                }

           return c;
            }

        });
       
        }
        catch(Exception e){
            e.printStackTrace();
            
        }
    }
    public Integer getFlagData(int id){
        String sql1="SELECT flag FROM datatable WHERE ID=?";
        Integer flag=0;
        try{
            PreparedStatement obj1=con.prepareStatement(sql1);
            obj1.setInt(1, id);
            ResultSet rst1=obj1.executeQuery();
            while(rst1.next()){
                flag=rst1.getInt("flag");
            }
            return flag;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public void setFlagData(int id,int flag){
        String sql1="UPDATE datatable SET flag=? WHERE ID=?";
        
        try{
            PreparedStatement obj1=con.prepareStatement(sql1);
            obj1.setInt(1, flag);
            obj1.setInt(2, id);
            obj1.execute();
            
        }
        catch(Exception e){
            e.printStackTrace();
            
        }
    }
    
     public void updateBunchOfData(ArrayList<Integer> list){
        String query="UPDATE datatable SET flag=1 WHERE ID=?";
//        Integer[] i=(Integer)list.toArray();
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
     
//     public void deleteBunchOfData(ArrayList<Integer> list,String query){
//        try{
//            for(int i=0;i<list.size();i++){
//            PreparedStatement obj1=con.prepareStatement(query);           
//            obj1.setInt(1, list.get(i));
//            obj1.execute();
//            }
//        }
//        catch(Exception e){
//            e.printStackTrace();
//            
//        }
//     }
     
      public static void deleteAll(){
        String del_query="DELETE FROM datatable";
        Connection con =DBHandler.getConnection();
        
        try{
            PreparedStatement st=con.prepareStatement(del_query);
            
            st.execute();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
      
     public Container getDataById(int id){
         String sql="SELECT * FROM datatable WHERE ID=?";
         Container cnt=new Container();
         try{
            PreparedStatement obj1=con.prepareStatement(sql);
            obj1.setInt(1, id);
            ResultSet rs=obj1.executeQuery();
            while(rs.next()){
                cnt.setName(rs.getString("name"));
                cnt.setGNdiv(rs.getString("GNdiv"));
                cnt.setAddress(rs.getString("address"));
                cnt.setContact(rs.getString("contactNo"));
                cnt.setBirthday(rs.getString("birthday"));
                }
            return cnt; 
             }
         catch(Exception e){
             e.printStackTrace();
             return null;
         }
    
}
      public void setQRData(int id,String qr){
        String sql1="UPDATE datatable SET QR=? WHERE ID=?";
        
        try{
            PreparedStatement obj1=con.prepareStatement(sql1);
            obj1.setString(1, qr);
            obj1.setInt(2, id);
            obj1.execute();
            
        }
        catch(Exception e){
            e.printStackTrace();
            
        }
    }
       public String getQRData(int id){
        String sql1="SELECT QR FROM datatable WHERE ID=?";
        String qr="";
        try{
            PreparedStatement obj1=con.prepareStatement(sql1);
            obj1.setInt(1, id);
            ResultSet rst1=obj1.executeQuery();
            while(rst1.next()){
                qr=rst1.getString("QR");
            }
            return qr;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
      public void getSearchResultsOnGN(String key) throws SQLException{
          String query="SELECT * FROM datatable WHERE GNdiv=? AND (flag=1)";
          try {
              PreparedStatement ps=con.prepareStatement(query);
              ps.setString(1, key);
              ResultSet rs=ps.executeQuery();
              
              if (rs.isBeforeFirst() ) {    
                    
               SearchResultsOnGND srgn=new SearchResultsOnGND(tbm,this,vall);
                  srgn.setVisible(true);
                  DefaultTableModel tabm=(DefaultTableModel)srgn.GNResTable.getModel();
              while(rs.next()){
                  tabm.addRow(new Object[]{rs.getInt("ID"),rs.getString("name"),rs.getString("GNdiv"),rs.getString("address"),rs.getString("contactNo"),rs.getString("birthday")});
              }}
              else{vall.jComboBox_marked.setSelectedIndex(0);
                  JOptionPane.showMessageDialog(vall,"No valid search result found!");}
          } catch (Exception e) {
              e.printStackTrace();
              
          }
          
      }
      
       public void getSearchResultsOnQR(String key) throws SQLException{
          String query="SELECT * FROM datatable WHERE QR=? AND flag=2";
          try {
              PreparedStatement ps=con.prepareStatement(query);
              ps.setString(1, key);
              ResultSet rs=ps.executeQuery();
              
              if (rs.isBeforeFirst() ) {    
                    
               SearchResultsOnQR srgn=new SearchResultsOnQR(tbm,vall);
                  srgn.setVisible(true);
                  DefaultTableModel tabm=(DefaultTableModel)srgn.QRResTable.getModel();
              while(rs.next()){
                  tabm.addRow(new Object[]{rs.getInt("ID"),rs.getString("name"),rs.getString("GNdiv"),rs.getString("address"),rs.getString("contactNo"),rs.getString("birthday"),"Applied",key});
              }}
              else{JOptionPane.showMessageDialog(vall,"No valid search result found!");}
          } catch (Exception e) {
              e.printStackTrace();
              
          }
          
      }
       
        public void getAllSearchResultsOnGN(String key) throws SQLException{
          String query="SELECT * FROM datatable WHERE GNdiv=? ";
          try {
              PreparedStatement ps=con.prepareStatement(query);
              ps.setString(1, key);
              ResultSet rs=ps.executeQuery();
              
              if (rs.isBeforeFirst() ) {    
                    
                  All_search_results asr=new All_search_results(m,m_main,v2,this,ffd);
                  asr.setVisible(true);
                  DefaultTableModel tabm=(DefaultTableModel)asr.all_res_Table.getModel();
              while(rs.next()){
                  switch(rs.getInt("flag")){
                    case 0 : 
                        tabm.addRow(new Object[]{rs.getInt("ID"),rs.getString("name"),rs.getString("GNdiv"),rs.getString("address"),rs.getString("contactNo"),rs.getString("birthday"),"",""});
                        break;
                    case 1:
                        tabm.addRow(new Object[]{rs.getInt("ID"),rs.getString("name"),rs.getString("GNdiv"),rs.getString("address"),rs.getString("contactNo"),rs.getString("birthday"),"Marked",""});
                        break;
                    case 2:
                        tabm.addRow(new Object[]{rs.getInt("ID"),rs.getString("name"),rs.getString("GNdiv"),rs.getString("address"),rs.getString("contactNo"),rs.getString("birthday"),"Applied",rs.getString("QR")});
                        break;
                  }
              }
               asr.all_res_Table.setDefaultRenderer(Object.class, new TableCellRenderer(){
        private DefaultTableCellRenderer DEFAULT_RENDERER =  new DefaultTableCellRenderer();

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
       
                Component c = DEFAULT_RENDERER.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
     c.setBackground(Color.WHITE);
     c.setForeground(Color.BLACK);
                if((table.getColumnModel().getColumn(column).getIdentifier().equals("Marked or not"))&&(table.getModel().getValueAt(row, 6).toString().equals("Marked"))){//Here `OK` is the value of row

       
        c.setBackground(new Color(250,44,12));
        c.setForeground(Color.WHITE);
    
     }
     


     else if((table.getColumnModel().getColumn(column).getIdentifier().equals("QR"))&&!(table.getModel().getValueAt(row, 7).toString().equals(""))){//Here `OK` is the value of row

       
        c.setBackground(Color.YELLOW);
    
     }
     
    

        if(isSelected){
                   c.setBackground(Color.BLACK);
                   c.setForeground(Color.WHITE);
                }

           return c;
            }

        });
              
              }
              else{JOptionPane.showMessageDialog(vall,"No valid search result found!");}
          } catch (Exception e) {
              e.printStackTrace();
              
          }
          
      }

      
}
