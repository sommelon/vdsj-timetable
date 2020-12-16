package sk.vdsj.timetable.model;

public enum Days {
    PON("MONDAY"), UTO("TUESDAY"), STR("WEDNESDAY"), STV("THURSDAY"), PIA("FRIDAY"), SOB("SATURDAY"), NED("SUNDAY"),
    MONDAY("PON"), TUESDAY("UTO"), WEDNESDAY("STR"), THURSDAY("STV"), FRIDAY("PIA"), SATURDAY("SOB"), SUNDAY("NED");

    private final String key;

    Days(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

}
