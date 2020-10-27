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
        timetable("Informatika", "LS 2020/2021", "BC1").
                schedule("Vývoj doménovo-špecifických jazykov").
                event("CL", "STR 10:50-12:20", "ZP1", "1, 2", "Prezenčne").
                organiser("Ivan Gašparovič").
                organiser("Ing. Andrej Danko, PhD").
                event("P", "STR 13:30-15:00", "ZP1", "1, 2", "Hybridne").
                organiser("Janko Hraško").
                organiser("Jožko Mrkvička").
                schedule("Paralelné programovanie").
                event("CL", "STV 10:50-12:20", "ZP1", "1, 2", "Prezenčne").
                organiser("Ivan Gašparovič").
                organiser("Ing. Andrej Danko, PhD").
                event("P", "STV 13:30-15:00", "ZP1", "1, 2", "Hybridne").
                organiser("Janko Hraško").
                organiser("Jožko Mrkvička")
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
