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
public enum EnumReturnCodeMap {
    SUCCESS("SUCCESS"),
    ERROR("ERROR"),
    REBOOT_REQUIRED("REBOOT_REQUIRED"),
    DEP_SOFT_ERROR("DEP_SOFT_ERROR"),
    DEP_HARD_ERROR("DEP_HARD_ERROR"),
    ERROR_INSTALL_PLATFORM_UNSUPPORTED("ERROR_INSTALL_PLATFORM_UNSUPPORTED"),
    REBOOTING_SYSTEM("REBOOTING_SYSTEM"),
    PASSWORD_REQUIRED("PASSWORD_REQUIRED"),
    NO_DOWNGRADE("NO_DOWNGRADE"),
    REBOOT_UPDATE_PENDING("REBOOT_UPDATE_PENDING"),
    UNKNOWN_OPTION("UNKNOWN_OPTION");
    
    private final String value;

    EnumReturnCodeMap(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumReturnCodeMap fromValue(String v) {
        for (EnumReturnCodeMap c: EnumReturnCodeMap.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
