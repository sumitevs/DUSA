package MUPXmlClasses;
/**
 *
 * @author Sumit_Saseendran
 */

public enum EnumReleaseTypesProgrammatic {

    BIOS("BIOS"),
    DRVR("DRVR"),
    APP("APP"),
    FRMW("FRMW"),
    APAC("APAC"),
    UTIL("UTIL"),
    BSD("BSD"),
    UEFICAPSULE("UEFICAPSULE");

    private final String value;

    EnumReleaseTypesProgrammatic(String v) {
        value = v;
    }

    
    public static EnumReleaseTypesProgrammatic fromValue(String v) {
        for (EnumReleaseTypesProgrammatic c: EnumReleaseTypesProgrammatic.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
