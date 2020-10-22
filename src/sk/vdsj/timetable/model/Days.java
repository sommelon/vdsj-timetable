package sk.vdsj.timetable.model;

public enum Days {
    PON("MONDAY"), UT("TUESDAY"), ST("WEDNESDAY"), STV("THURSDAY"), PIA("FRIDAY"),
    MONDAY("PON"), TUESDAY("UT"), WEDNESDAY("ST"), THURSDAY("STV"), FRIDAY("PIA");

    private final String key;

    Days(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

}
