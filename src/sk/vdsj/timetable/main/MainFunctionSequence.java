package sk.vdsj.timetable.main;

import sk.vdsj.timetable.builder.FunctionSequenceBuilder;
import sk.vdsj.timetable.model.Timetable;
import sk.vdsj.timetable.semantics.TimetablePrinter;

import static sk.vdsj.timetable.builder.FunctionSequenceBuilder.*;

public class MainFunctionSequence {
    public static void main(String[] args) {
        /***************************************************************/
        timetable("Informatika", "LS 2020/2021","12.09.2020", "21.12.2020", "BC1");

        schedule("Vývoj doménovo-špecifických jazykov");
            event("CL", "STR 10:50-12:20", "ZP1");
                groups("1, 2");
                organiser("Ivan Gašparovič");
                organiser("Ing. Andrej Danko, PhD");
                period(2);
                note("Prezenčne");
            event("P", "STR 13:30-15:00", "ZP1");
                groups("1, 2");
                organiser("Janko Hraško");
                organiser("Jožko Mrkvička");
                note("Hybridne");
        schedule("Paralelné programovanie");
            event("CL", "STV 10:50-12:20","ZP1");
                groups("1, 2");
                organiser("Ivan Gašparovič");
                organiser("Ing. Andrej Danko, PhD");
                note("Prezenčne");
            event("P", "STV 13:30-15:00", "ZP1");
                groups("1, 2");
                organiser("Janko Hraško");
                organiser("Jožko Mrkvička");
                note("Hybridne");
        /***************************************************************/

        Timetable timetable = FunctionSequenceBuilder.getTimeTable();
        timetable.validate();

        TimetablePrinter printer = new TimetablePrinter();
        printer.print(timetable);

    }
}
