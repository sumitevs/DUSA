/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIEXmlClasses;

/**
 *
 * @author Sumit_Saseendran
 */
public enum FmpComparisonEnum {
GreaterThan("GreaterThan"),
LessThan("LessThan"),
EqualTo("EqualTo"),
NotEqualTo("NotEqualTo"),
GreaterThanEqualTo("GreaterThanEqualTo"),
LessThanEqualTo("LessThanEqualTo");

private final String value;

    FmpComparisonEnum(String v) {
        value = v;
    }
    public String value() {
        return value;
    }

    
    public static FmpComparisonEnum fromValue(String v) {
        for (FmpComparisonEnum c : FmpComparisonEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        return null;
    }
    
}
