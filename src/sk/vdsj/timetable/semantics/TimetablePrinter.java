package sk.vdsj.timetable.semantics;

import sk.vdsj.timetable.model.Event;
import sk.vdsj.timetable.model.Schedule;
import sk.vdsj.timetable.model.Timetable;

public class TimetablePrinter {

    public void print(Timetable timetable) {
        System.out.println("Študijný program: " + timetable.getProgramme());

        for(Schedule schedule : timetable.getSchedules())
            print(schedule);
    }

    private void print(Schedule schedule) {
        System.out.println("Predmet: " + schedule.getTitle());

        for(Event event : schedule.getEvents())
            print(event);

    }

    private void print(Event event) {
        System.out.println("Typ hodiny: " + event.getType());
        System.out.print("Vyučujúci: ");
        for(int i = 0; i < event.getPersons().length; i++) {
            if(i != 0) System.out.print(", ");
            System.out.print(event.getPersons()[i]);
        }
        System.out.println();
        System.out.println("Termín: " + event.getTime());
        System.out.println("Miestnosť: " + event.getRoom());
        System.out.println("Skupiny: " + event.getGroups());
        System.out.println("Poznámka: " + event.getNote());
        System.out.println();
    }

}
