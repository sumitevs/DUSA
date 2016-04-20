package MUPXmlClasses;

public enum EnumBehaviors {

    unattended("unattended"),
    attended("attended"),
    extractdrivers("extractdrivers"),
    freshinstall("freshinstall"),
    help("help"),
    driveronly("driveronly"),
    applicationonly("applicationonly"),
    logfile("logfile"),
    force("force");
    private final String value;

    EnumBehaviors(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumBehaviors fromValue(String v) {
        for (EnumBehaviors c: EnumBehaviors.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
