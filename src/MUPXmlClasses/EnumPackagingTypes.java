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
public enum EnumPackagingTypes {
    executable("executable"),
    zip("zip");
    private final String value;
    
    EnumPackagingTypes(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumPackagingTypes fromValue(String v) {
        for (EnumPackagingTypes c: EnumPackagingTypes.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
