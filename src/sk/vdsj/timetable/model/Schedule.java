package sk.vdsj.timetable.model;

public class Schedule {
    private String title;
    private Event[] events;

    public Schedule(String title, Event[] events) {
        this.title = title;
        this.events = events;
    }

    // Getters and Setters
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Event[] getEvents() {
        return events;
    }

    public void setEvents(Event[] events) {
        this.events = events;
    }

    public void validate() {
        if (title == null) {
            throw new TimetableLanguageException("Missing title in a Schedule");
        }

        if (events.length < 1) {
            throw new TimetableLanguageException("Atleast one schedule is required in a Schedule");
        }

        for (var schedule: events) {
            schedule.validate();
        }
    }
}
