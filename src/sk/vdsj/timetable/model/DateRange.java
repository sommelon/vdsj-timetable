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

    public static DateRange valueOf(String periodString) {
        LocalDate startDate;
        LocalDate endDate;
        String[] dates = periodString.split("-");
        try {
            startDate = LocalDate.parse(dates[0].trim(), formatter);
            endDate = LocalDate.parse(dates[1].trim(), formatter);
        } catch (Exception e) {
            throw new TimetableLanguageException("Invalid date format: " + periodString);
        }

        return new DateRange(startDate, endDate);
    }

    public void validate() {
        if (start.isAfter(end)) {
            throw new TimetableLanguageException("Starting date must be earlier than the ending date.");
        }
    }

    @Override
    public String toString() {
        return start.format(formatter) + "-" + end.format(formatter) ;
    }
}
