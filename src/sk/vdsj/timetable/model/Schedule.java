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
        if (title == null || title.isEmpty()) {
            throw new TimetableLanguageException("Missing title in a schedule.");
        }

        if (events == null || events.length < 1) {
            throw new TimetableLanguageException("At least one event is required in a schedule.");
        }

        for (var event: events) {
            try {
                event.validate();
            } catch (TimetableLanguageException e) {
                System.err.println("Error in schedule '" + title + "'.");
                throw new TimetableLanguageException(e.getMessage());
            }
        }
    }
}
