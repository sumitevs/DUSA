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
public class VerifyPciInfo extends InputVerifier{

    @Override
    public boolean verify(JComponent jc) {
        String text = ((JTextField) jc).getText();
        try {
            if(text.matches("^[A-F0-9]*$") || text.endsWith("")){
                return true; 
            }else{
                JOptionPane.showMessageDialog(new JFrame(), "Invalid entry. HexDecimal Value expected.");
            }            
        } catch (NumberFormatException e) {
            System.out.println("error in format");
        }
        return false;
    }
    
}
