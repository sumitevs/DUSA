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
public enum EnumPackageTypes {
    single_package("single_package"),
    pair_driver_and_application("pair_driver_and_application"),
    pair_driver_reduced("pair_driver_reduced");
    private final String value;
    
    EnumPackageTypes(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumPackageTypes fromValue(String v) {
        for (EnumPackageTypes c: EnumPackageTypes.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
