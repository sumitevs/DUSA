/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MUPXmlClasses;

/**
 *
 * @author Sumit_Saseendran
 */
public class PgmExtraction {
    protected String switchValue;
    protected String delimiterValue;
    protected String value;
    protected Boolean requireValue;

    public String getSwitch() {
        return switchValue;
    }

    public void setSwitch(String _switch) {
        this.switchValue = _switch;
    }

    public String getDelimiterValue() {
        return delimiterValue;
    }

    public void setDelimiterValue(String delimiterValue) {
        this.delimiterValue = delimiterValue;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Boolean getRequireValue() {
        return requireValue;
    }

    public void setRequireValue(Boolean requireValue) {
        this.requireValue = requireValue;
    }

    public String getEnclose() {
        return enclose;
    }

    public void setEnclose(String enclose) {
        this.enclose = enclose;
    }
    protected String enclose;
}
