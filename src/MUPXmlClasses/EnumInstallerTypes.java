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
public enum EnumInstallerTypes {
    msi("msi"),
    custom("custom"),
    installshield_installscript("installshield installscript"),
    installshield_installscript_msi("installshield installscript msi");
    private final String value;

    EnumInstallerTypes(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumInstallerTypes fromValue(String v) {
        for (EnumInstallerTypes c: EnumInstallerTypes.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
    
}
