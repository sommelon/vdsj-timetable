package sk.vdsj.timetable.model;

import java.time.DayOfWeek;

public enum Days {
    PON(DayOfWeek.MONDAY), UT(DayOfWeek.TUESDAY), ST(DayOfWeek.WEDNESDAY), STV(DayOfWeek.THURSDAY), PIA(DayOfWeek.FRIDAY);

    private final DayOfWeek key;

    Days(DayOfWeek key) {
        this.key = key;
    }

    public DayOfWeek getKey() {
        return key;
    }

}
