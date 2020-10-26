package sk.vdsj.timetable.model;

public class Event {
    private String type;
    private Time time;
    private String room;
    private String groups;
    private String[] persons;
    private String note;

    public Event(String type, Time time, String room, String groups, String[] persons, String note) {
        this.type = type;
        this.time = time;
        this.room = room;
        this.groups = groups;
        this.persons = persons;
        this.note = note;
    }

    // Helper Methods:
    public String renderEvent(String subject){

        String html = "";

        html += "<small>";
            html += "<strong style='font-size: 15px;'>"+time.getTime_from()+" - "+time.getTime_to()+"</strong><br/>";
            html += subject+"<br/>";
            html += type+": "+room+" - "+this.getGroups()+"<br/>";

            for (int i = 0; i < this.getPersons().length; i++) {
                if (i != 0) html +="; ";
                html += "<strong>"+this.getPersons()[i]+"</strong>";
            }
            html += "<br/>";
        html += "</small>";

        html += "<h5 class='m-0'>"+note+"</h5>";

        return html;
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

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
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
        if (type == null || time == null || room == null || persons == null) {
            throw new TimetableLanguageException("Missing required parameters in an Event");
        }

        if (persons.length < 1) {
            throw new TimetableLanguageException("Atleast one organiser is required for an Event");
        }

        time.validate();
    }
}
