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
public enum SystemIdTypeEnum {

    BIOS("BIOS"),
    MULTISYSTEMCHASSIS("MULTISYSTEMCHASSIS"),
    RAIDCHASSIS("RAIDCHASSIS"),
    ROUTER("ROUTER"),
    NETWORKSWITCH("NETWORKSWITCH");

    private final String value;

    SystemIdTypeEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SystemIdTypeEnum fromValue(String v) {
        for (SystemIdTypeEnum c : SystemIdTypeEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        return null;
    }

}
