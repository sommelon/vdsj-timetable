package sk.vdsj.timetable.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Timetable implements Serializable {
    private String programme;
    private String semester;
    private String grade;
    private Schedule[] schedules;

    public Timetable(String programme, String semester, String grade, Schedule[] schedules) {
        this.programme = programme;
        this.semester = semester;
        this.grade = grade;
        this.schedules = schedules;
    }

    // Helper Methods:
    public String getFullTitle(){
        return programme + " - " + semester + " - " + grade;
    }

    public HashMap<String, Event> getEventsByDay(String day){
        HashMap<String, Event> events = new HashMap<String, Event>();

        for(Schedule schedule : schedules){
            for(Event event : schedule.getEvents()){
                if( event.getTime().getDay().toString().equals(day) ){
                    events.put(schedule.getTitle(), event);
                }
            }
        }

        return events;
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

    public Schedule[] getSchedules() {
        return schedules;
    }

    public void setSchedules(Schedule[] schedules) {
        this.schedules = schedules;
    }

    public void validate() {
        if (programme == null || semester == null || grade == null || schedules == null) {
            throw new TimetableLanguageException("Missing required parameters in a Timetable");
        }

        if (schedules.length < 1) {
            throw new TimetableLanguageException("Atleast one event is required in a Timetable");
        }

        for (var event: schedules) {
            event.validate();
        }
    }
}
