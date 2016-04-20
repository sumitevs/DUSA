/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Sumit_Saseendran
 */
public class StartApp extends javax.swing.JFrame{
    
    private final javax.swing.JPanel panelConst  = new JPanel();
    Home home = new Home();
    
    public StartApp(){
       
        //setting base panel layout as card layout
        panelConst.setLayout(new BorderLayout());
        
        //adding all the panels to base panel
//        panelConst.add(p1,"1");
//        panelConst.add(p2,"2");
        panelConst.add(home,BorderLayout.CENTER);
        
        //initially showing first panel
//        cl.show(panelConst,"3");
        
//        setListeners();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("DUSA");
        setIconImage(new ImageIcon("src/assignment6/dell_icon.png").getImage());
        add(panelConst);
        setVisible(true);
        //setMinimumSize(new Dimension(1000,700));
        setResizable(false);
        setLocation(320,70);
        pack();
        //setSize(1300,850);
    }
    
    //private void setListeners(){
        
        //listening to actions in the first panel
//        p1.setButtonsActionListener(new ActionListener(){
//            public void actionPerformed(ActionEvent e){
//                if(e.getActionCommand().equals("MUP")){
//                    cl.show(panelConst,"2");                    
//                }
//                else if(e.getActionCommand().equals("PIE")){
//                    cl.show(panelConst,"2");
//                }
//                
//            }
//        });
        
        //listening to actions in the second panel
//        p2.setButtonsActionListener(new ActionListener(){
//            public void actionPerformed(ActionEvent e){
//                if(e.getActionCommand().equals("back")){
//                    cl.show(panelConst,"1");                    
//                }
//                else if(e.getActionCommand().equals("Create")){
//                    cl.show(panelConst,"3");
//                }
//                else if(e.getActionCommand().equals("Update")){
//                    cl.show(panelConst,"3");
//                }
//                else if(e.getActionCommand().equals("Update")){
//                    cl.show(panelConst,"3");
//                }
//                
//            }
//        });
        
        //listening to actions in the third panel
//        p3.setButtonsActionListener(new ActionListener(){
//            public void actionPerformed(ActionEvent e){
//                if(e.getActionCommand().equals("back")){
//                    cl.show(panelConst,"2");                    
//                }
//                else if(e.getActionCommand().equals("MUP")){
//                    cl.show(panelConst,"2");
//                }
//                
//            }
//        });
//    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         //TODO code application logic here
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StartApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StartApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StartApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StartApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StartApp().setVisible(true);
                
            }
        });
    }
   
}
