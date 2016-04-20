/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIEXmlClasses;

import gui.pie.DisplayDetail;

/**
 *
 * @author Sumit_Saseendran
 */
public class DisplayType {

    public DisplayType() {
    }

    public DisplayType(String name, String lang) {
        this.name = name;
        this.lang = lang;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
    
    
    private String name;
    private String lang;

//    void load(DisplayDetail displayDetail) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//    
}
