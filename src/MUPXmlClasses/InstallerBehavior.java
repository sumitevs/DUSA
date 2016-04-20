/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MUPXmlClasses;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sumit_Saseendran
 */
public class InstallerBehavior {
    protected EnumBehaviors enumBehavior;
    protected String staticExtraction;
    protected List<PgmExtraction> pgmExtraction;

    public EnumBehaviors getEnumBehavior() {
        return enumBehavior;
    }

    public void setEnumBehavior(EnumBehaviors enumBehavior) {
        this.enumBehavior = enumBehavior;
    }

    public String getStaticExtraction() {
        return staticExtraction;
    }

    public void setStaticExtraction(String staticExtraction) {
        this.staticExtraction = staticExtraction;
    }

    public List<PgmExtraction> getPgmExtraction() {
         if (pgmExtraction == null) {
                pgmExtraction = new ArrayList<PgmExtraction>();
            }
        return this.pgmExtraction;
    }

       
}
