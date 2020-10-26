package sk.vdsj.timetable.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

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

    public ArrayList<Event> getEventsByDay(String day){
        ArrayList<Event> events = new ArrayList<>();

        for(Schedule schedule : schedules){
            for(Event event : schedule.getEvents()){
                if( event.getTime().getDay().toString().equals(day) ){
                    events.add(event);
                }
            }
        }

        // sort by time_from
        events.sort(Comparator.comparing((Event event) -> event.getTime().getTime_from()));

        return events;
    }

    public Schedule getScheduleByEvent(Event event){
        Schedule schedule_that_i_wanted = null;
        for (Schedule schedule : schedules) {
            if(Arrays.asList(schedule.getEvents()).contains(event)){
                schedule_that_i_wanted = schedule;
            }
        }

        return schedule_that_i_wanted;
    }

    public ArrayList<String> getDays(){

        ArrayList<String> days = new ArrayList<>();
        days.add("MONDAY");
        days.add("TUESDAY");
        days.add("WEDNESDAY");
        days.add("THURSDAY");
        days.add("FRIDAY");

        return days;
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
