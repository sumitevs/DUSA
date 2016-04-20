

package MUPXmlClasses;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;



@XmlType(name = "Enum_OSNames")
@XmlEnum
public enum EnumOSNames {

    @XmlEnumValue("Vista")
    VISTA("Vista"),
    @XmlEnumValue("XP")
    XP("XP"),
    @XmlEnumValue("Windows7")
    WINDOWS_7("Windows7"),
    @XmlEnumValue("Windows8")
    WINDOWS_8("Windows8"),
    @XmlEnumValue("Windows8.1")
    WINDOWS_8_1("Windows8.1"),
    @XmlEnumValue("W2K")
    W_2_K("W2K"),
    @XmlEnumValue("W2K3")
    W_2_K_3("W2K3"),
    @XmlEnumValue("W2K8")
    W_2_K_8("W2K8"),
    @XmlEnumValue("W2K8R2")
    W_2_K_8_R_2("W2K8R2"),
    @XmlEnumValue("W2K12")
    W_2_K_12("W2K12"),
    @XmlEnumValue("W2K12R2")
    W_2_K_12_R_2("W2K12R2"),
    @XmlEnumValue("winpe")
    WINPE("winpe"),
    @XmlEnumValue("winpe3x")
    WINPE_3_X("winpe3x"),
    @XmlEnumValue("winpe4x")
    WINPE_4_X("winpe4x"),
    @XmlEnumValue("winpe5x")
    WINPE_5_X("winpe5x"),
    @XmlEnumValue("DOS-ODI")
    DOS_ODI("DOS-ODI"),
    @XmlEnumValue("DOS-NDIS2")
    DOS_NDIS_2("DOS-NDIS2");
    private final String value;

    EnumOSNames(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumOSNames fromValue(String v) {
        for (EnumOSNames c: EnumOSNames.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
