/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIEXmlClasses;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sumit_Saseendran
 */
public class SupportedLanguagesType {

    public SupportedLanguagesType() {
        language = new ArrayList<>();
    }

    
    public List<DisplayType> getLanguage() {
        if(language==null){
            language = new ArrayList<>();
        }
        return language;
    }
    public void addLang(String val,String lang){
        getLanguage().add(new DisplayType(val,lang));
    }
    private List<DisplayType> language;
}
