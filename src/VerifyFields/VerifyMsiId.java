/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VerifyFields;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Sumit_Saseendran
 */
public class VerifyMsiId extends InputVerifier{

    @Override
    public boolean verify(JComponent jc) {
        String text = ((JTextField) jc).getText();
        try {
            if(text.matches("(\\{{0,1}([0-9a-fA-F]){8}-([0-9a-fA-F]){4}-([0-9a-fA-F]){4}-([0-9a-fA-F]){4}-([0-9a-fA-F]){12}\\}{0,1})") || text.trim().isEmpty()){
                return true; 
            }else{
                JOptionPane.showMessageDialog(new JFrame(), "Invalid entry. Example: {22F661DE-52C9-490a-9423-705471708FBC}");
            }            
        } catch (NumberFormatException e) {
            System.out.println("error in format");
        }
        return false;
    }    
}


