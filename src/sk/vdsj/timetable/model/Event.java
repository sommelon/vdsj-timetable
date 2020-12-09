package sk.vdsj.timetable.model;

public class Event {
    private String type;
    private Time time;
    private String location;
    private String groups;
    private String[] organisers;
    private String note;
    private int period;

    public Event(String type, Time time, String location, String groups, String[] organisers, String note, int period) {
        this.type = type;
        this.time = time;
        this.location = location;
        this.groups = groups;
        this.organisers = organisers;
        this.note = note;
        this.period = period;
    }

    // Getters and Setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    public String[] getOrganisers() {
        return organisers;
    }

    public void setOrganisers(String[] organisers) {
        this.organisers = organisers;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public void validate() {
        if (time == null) {
            throw new TimetableLanguageException("Time of event isn't specified.");
        } else if (type == null || type.isEmpty()) {
            throw new TimetableLanguageException("Type of event at " + time + " isn't specified.");
        } else if (location == null || location.isEmpty()) {
            throw new TimetableLanguageException("Location of event at " + time + " isn't specified.");
        } else if (organisers == null || organisers.length < 1) {
            throw new TimetableLanguageException("At least one organiser is required for an event at " + time);
        } else if (period < 1) {
            throw new TimetableLanguageException("Period must be a positive integer.");
        }

        time.validate();
    }
}
