package sk.vdsj.timetable.main;

import sk.vdsj.timetable.builder.FunctionSequenceBuilder;
import sk.vdsj.timetable.model.Days;
import sk.vdsj.timetable.model.Timetable;
import sk.vdsj.timetable.semantics.TimetablePrinter;

import static sk.vdsj.timetable.builder.FunctionSequenceBuilder.*;

public class MainFunctionSequence {
    public static void main(String[] args) {
        /***************************************************************/
        timetable("Informatika", "LS 2020/2021", "BC1");

        event("Vývoj doménovo-špecifických jazykov");
            schedule("CL", "ST 10:50-12:20", "1, 2", "Prezenčne");
                person("Ivan Gašparovič");
                person("Andrej Danko");
            schedule("P", "ST 13:30-15:00", "1, 2", "Hibridne");
                person("Janko Hraško");
                person("Jožko Mrkvička");
        event("Paralelné programovanie");
            schedule("CL", "STV 10:50-12:20", "1, 2", "Prezenčne");
                person("Ivan Gašparovič");
                person("Andrej Danko");
            schedule("P", "STV 13:30-15:00", "1, 2", "Hibridne");
                person("Janko Hraško");
                person("Jožko Mrkvička");

        /***************************************************************/

        Timetable timetable = FunctionSequenceBuilder.getTimeTable();
        timetable.validate();

        TimetablePrinter printer = new TimetablePrinter();
        printer.print(timetable);

        System.out.println(Days.valueOf("MONDAY"));


    }
}
