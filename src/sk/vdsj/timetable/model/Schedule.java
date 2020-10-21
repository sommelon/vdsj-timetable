package sk.vdsj.timetable.model;

public class Schedule {
    private String type;
    private Time time;
    private String groups;
    private String[] persons;
    private String note;

    public Schedule() {
    }

    public Schedule(String type, Time time, String groups, String[] persons, String note) {
        this.type = type;
        this.time = time;
        this.groups = groups;
        this.persons = persons;
        this.note = note;
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

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    public String[] getPersons() {
        return persons;
    }

    public void setPersons(String[] persons) {
        this.persons = persons;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void validate() {
        if (type == null || time == null || persons == null) {
            throw new TimetableLanguageException("Missing required parameters in a Schedule");
        }

        if (persons.length < 1) {
            throw new TimetableLanguageException("Atleast one organiser is required for a Schedule");
        }

        time.validate();
    }
}
