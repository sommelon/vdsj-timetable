package sk.vdsj.timetable.main;

import sk.vdsj.timetable.builder.FunctionSequenceBuilder;
import sk.vdsj.timetable.model.Timetable;
import sk.vdsj.timetable.semantics.TimetablePrinter;

import static sk.vdsj.timetable.builder.FunctionSequenceBuilder.*;

public class MainFunctionSequence {
    public static void main(String[] args) {
        /***************************************************************/
        timetable("Informatika", "LS 2020/2021", "BC1");

        schedule("Vývoj doménovo-špecifických jazykov");
            event("CL", "STR 10:50-12:20", "ZP1", "1, 2", "Prezenčne");
                organiser("Ivan Gašparovič");
                organiser("Ing. Andrej Danko, PhD");
            event("P", "STR 13:30-15:00", "ZP1", "1, 2", "Hybridne");
                organiser("Janko Hraško");
                organiser("Jožko Mrkvička");
        schedule("Paralelné programovanie");
            event("CL", "STV 10:50-12:20","ZP1", "1, 2", "Prezenčne");
                organiser("Ivan Gašparovič");
                organiser("Ing. Andrej Danko, PhD");
            event("P", "STV 13:30-15:00", "ZP1","1, 2", "Hybridne");
                organiser("Janko Hraško");
                organiser("Jožko Mrkvička");

        /***************************************************************/

        Timetable timetable = FunctionSequenceBuilder.getTimeTable();
        timetable.validate();

        TimetablePrinter printer = new TimetablePrinter();
        printer.print(timetable);

    }
}
