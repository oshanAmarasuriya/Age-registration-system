/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ageregister;

import ageregister.view.AddData;
import ageregister.view.MainDashboard;
import ageregister.view.Splash;

/**
 *
 * @author Oshan
 */
public class AgeRegister {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Splash splash =new Splash();
        splash.setVisible(true);
        try {
            for(int i=0; i<=100 ;i++){
                Thread.sleep(40);
                splash.progressbar.setValue(i);
            }
        } catch (Exception e) {
        }
        
        
        splash.dispose();
        
        
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
               new MainDashboard().setVisible(true);
        
// TODO code application logic here
    }
    
}
