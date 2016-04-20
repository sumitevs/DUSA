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
public class VerifyComponentId extends InputVerifier{
      @Override
    public boolean verify(JComponent jc) {
        String text = ((JTextField) jc).getText();
        try {
            if(text.matches("^[0-9]{0,10}$")){
                return true; 
            }else{
                JOptionPane.showMessageDialog(new JFrame(), "Invalid entry. Use only digits. Max 10 digits.");
            }            
        } catch (NumberFormatException e) {
            System.out.println("error in format");
        }
        return false;
    }    
}
