package sk.vdsj.timetable.model;

public class Event {
    private String title;
    private Schedule[] schedules;

    public Event() {
    }

    public Event(String title, Schedule[] schedules) {
        this.title = title;
        this.schedules = schedules;
    }

    // Getters and Setters
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Schedule[] getSchedules() {
        return schedules;
    }

    public void setSchedules(Schedule[] schedules) {
        this.schedules = schedules;
    }

    public void validate() {
        if (title == null) {
            throw new TimetableLanguageException("Missing title in an Event");
        }

        if (schedules.length < 1) {
            throw new TimetableLanguageException("Atleast one schedule is required in an Event");
        }

        for (var schedule: schedules) {
            schedule.validate();
        }
    }
}
