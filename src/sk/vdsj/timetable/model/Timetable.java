package sk.vdsj.timetable.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Timetable implements Serializable {
    private String programme;
    private String semester;
    private DateRange period;
    private String grade;
    private Schedule[] schedules;

    public Timetable(String programme, String semester, DateRange period, String grade, Schedule[] schedules) {
        this.programme = programme;
        this.semester = semester;
        this.period = period;
        this.grade = grade;
        this.schedules = schedules;
    }

    // Helper Methods:
    public String getFullTitle(){
        return programme + " - " + semester + " - " + grade;
    }

    public List<Event> getEventsByDay(String day){
        List<Event> events = new ArrayList<>();

        for(Schedule schedule : schedules){
            for(Event event : schedule.getEvents()){
                if( event.getTime().getDay().toString().equals(day) ){
                    events.add(event);
                }
            }
        }

        // sort by time_from
        events.sort(Comparator.comparing((Event event) -> event.getTime().getStartTime()));

        return events;
    }

    public Schedule getScheduleByEvent(Event event){
        for (Schedule schedule : schedules) {
            if(Arrays.asList(schedule.getEvents()).contains(event)){
                return schedule;
            }
        }

        return null;
    }

    public List<String> getDays(){

        List<String> days = new ArrayList<>();
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

    public DateRange getPeriod() {
        return period;
    }

    public void setPeriod(DateRange period) {
        this.period = period;
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
        if (programme == null || programme.isEmpty()) {
            throw new TimetableLanguageException("Study programme isn't specified.");
        } else if (semester == null || semester.isEmpty()) {
            throw new TimetableLanguageException("Semester isn't specified.");
        } else if (grade == null || grade.isEmpty()) {
            throw new TimetableLanguageException("Grade isn't specified.");
        } else if (schedules == null || schedules.length < 1) {
            throw new TimetableLanguageException("At least one schedule is required in a timetable.");
        }

        period.validate();

        for (var schedule: schedules) {
            schedule.validate();
        }
    }
}
