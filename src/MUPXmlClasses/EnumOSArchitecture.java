

package MUPXmlClasses;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "Enum_OSArchitecture")
@XmlEnum
public enum EnumOSArchitecture {

    @XmlEnumValue("x86")
    x86("x86"),
    @XmlEnumValue("x64")
    x64("x64"),
    @XmlEnumValue("ia64")
    ia64("ia64"),
    @XmlEnumValue("ia32")
    ia32("ia32"),
    @XmlEnumValue("arm")
    arm("arm");
    private final String value;

    EnumOSArchitecture(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumOSArchitecture fromValue(String v) {
        for (EnumOSArchitecture c: EnumOSArchitecture.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
