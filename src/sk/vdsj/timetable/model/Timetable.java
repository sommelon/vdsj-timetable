package sk.vdsj.timetable.model;

public class Timetable {
    private String programme;
    private String semester;
    private String grade;
    private Event[] events;

    public Timetable() {
    }

    public Timetable(String name, String semester, String grade, Event[] events) {
        this.programme = name;
        this.semester = semester;
        this.grade = grade;
        this.events = events;
    }

    // Getters and Setters

    public String getProgramme() {
        return programme;
    }

    public void setProgramme(String programme) {
        this.programme = programme;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Event[] getEvents() {
        return events;
    }

    public void setEvents(Event[] events) {
        this.events = events;
    }

    public void validate() {
        if (programme == null || semester == null || grade == null || events == null) {
            throw new TimetableLanguageException("Missing required parameters in a Timetable");
        }

        if (events.length < 1) {
            throw new TimetableLanguageException("Atleast one event is required in a Timetable");
        }
    }
}
