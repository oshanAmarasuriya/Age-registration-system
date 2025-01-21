/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ageregister;

import static ageregister.FetchData.con;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Oshan
 */
public class FetchFilteredData {
    static Connection con=DBHandler.getConnection();
    
   
    public void getAndFilterData(Date date,DefaultTableModel tableMod){
        AgeCalculator agc=new AgeCalculator(date);
        String sql="SELECT * FROM datatable WHERE flag=0";
         try{
            PreparedStatement obj=con.prepareStatement(sql);
            ResultSet rst=obj.executeQuery();
            while(rst.next()){
                if(agc.calc(rst.getString("birthday"))){
                    tableMod.addRow(new Object[]{rst.getInt("ID"),rst.getString("name"),rst.getString("GNdiv"),rst.getString("address"),rst.getString("contactNo"),rst.getString("birthday")});
                }
            }
    }
         catch(Exception e){
             e.printStackTrace();
         }
}
}
