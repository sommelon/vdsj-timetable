package sk.vdsj.timetable.model;

public class Timetable {
    private String name;
    private String semester;
    private String grade;
    private Event[] events;

    public Timetable() {
    }

    public Timetable(String name, String semester, String grade, Event[] events) {
        this.name = name;
        this.semester = semester;
        this.grade = grade;
        this.events = events;
    }

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
