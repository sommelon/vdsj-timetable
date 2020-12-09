package sk.vdsj.timetable.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateRange {
    private LocalDate start;
    private LocalDate end;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy");

    public DateRange(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public static DateRange valueOf(String dateRangeString) {
        String[] dates = dateRangeString.split(" - ");
        LocalDate startDate = LocalDate.parse(dates[0], formatter);
        LocalDate endDate = LocalDate.parse(dates[1], formatter);

        return new DateRange(startDate, endDate);
    }

    @Override
    public String toString() {
        return start.format(formatter) + " - " + end.format(formatter) ;
    }
}
