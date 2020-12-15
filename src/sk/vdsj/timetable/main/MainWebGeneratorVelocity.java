package sk.vdsj.timetable.main;

import sk.vdsj.timetable.builder.MethodChainingBuilder;
import sk.vdsj.timetable.model.Timetable;
import sk.vdsj.timetable.semantics.TimetableVelocityWebGenerator;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import static sk.vdsj.timetable.builder.MethodChainingBuilder.timetable;

public class MainWebGeneratorVelocity {
    public static void main(String[] args) throws IOException {
        /***************************************************************/
        timetable("Informatika", "LS 2020/2021","12.09.2020", "21.12.2020", "BC1").
                schedule("Vývoj doménovo-špecifických jazykov").
                event("CL", "STR 10:50-12:20", "ZP1").
                organiser("Ivan Gašparovič").
                organiser("Ing. Andrej Danko, PhD").
                groups("1, 2").
                note("Prezenčne").
                interval(4).
                event("P", "STR 13:30-15:00", "ZP1").
                organiser("Janko Hraško").
                organiser("Jožko Mrkvička").
                groups("1, 2").
                note("Hybridne").
                schedule("Paralelné programovanie").
                event("CL", "STV 10:50-12:20", "ZP1").
                organiser("Ivan Gašparovič").
                organiser("Ing. Andrej Danko, PhD").
                groups("1, 2").
                note("Prezenčne").
                event("P", "STV 13:30-15:00", "ZP1").
                organiser("Janko Hraško").
                organiser("Jožko Mrkvička").
                groups("1, 2")
        /***************************************************************/
        ;

        Timetable timetable = MethodChainingBuilder.getTimeTable();
        timetable.validate();

        try (Writer writer = new FileWriter("timetable.html")) {
            TimetableVelocityWebGenerator printer = new TimetableVelocityWebGenerator();
            printer.generate(timetable, writer);
        }

    }

}
