/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ageregister;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Oshan
 */
public class DBHandler2 {
     public static Connection getConnection(){
        String dbURL = "jdbc:sqlite:database2.db";

        
        try{
            Connection con =DriverManager.getConnection(dbURL);
            System.out.println("connected2");
            return  con;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
        
    }
}
