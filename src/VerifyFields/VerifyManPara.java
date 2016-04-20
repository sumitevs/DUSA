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
public class VerifyManPara extends InputVerifier{
    @Override
    public boolean verify(JComponent jc) {
        String text = ((JTextField) jc).getText();
        try {
            if(text.matches("^(\\\\[a-zA-Z]+\\s?)*$")){
                return true; 
            }else{
                JOptionPane.showMessageDialog(new JFrame(), "Invalid entry. Use only '\\' and alphabets. Example: \\d \\es.");
            }            
        } catch (NumberFormatException e) {
            System.out.println("error in format");
        }
        return false;
    }    
}