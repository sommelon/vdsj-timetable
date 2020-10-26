package sk.vdsj.timetable.main;

import sk.vdsj.timetable.builder.NestedFunctionBuilder;
import sk.vdsj.timetable.model.Timetable;
import sk.vdsj.timetable.semantics.TimetablePrinter;

import static sk.vdsj.timetable.builder.NestedFunctionBuilder.*;

public class MainNestedFunction {
    public static void main(String[] args) {
        /***************************************************************/

        TimeTable("Informatika",
                  "LS 2020/2021",
                  "BC1",
                   Schedule("Vývoj doménovo-špecifických jazykov",
                            Event("CL",
                                  "ST 10:50-12:20",
                                  "ZP1",
                                  "1, 2",
                                  Person("Janko Hraško"),
                                  "Prezenčne"
                            ),
                            Event("P",
                                  "ST 13:30-15:00",
                                  "ZP1",
                                  "1, 2",
                                  Persons("Janko Hraško",
                                          "Jožko Mrkvička"),
                                  "Hybridne"
                            )
                   ),
                   Schedule("Paralelné programovanie",
                            Event("CL",
                                  "STV 10:50-12:20",
                                  "ZP1",
                                  "1, 2, 3",
                                  Person("Ivan Gašparovič"),
                                  "Prezenčne"
                            ),
                            Event("P",
                                  "STV 13:30-15:00",
                                  "ZP1",
                                  Persons("Ivan Gašparovič",
                                          "Ing. Andrej Danko, PhD")
                            )
                )
        )

        /***************************************************************/
        ;
        Timetable timetable = NestedFunctionBuilder.getTimeTable();
        timetable.validate();

        TimetablePrinter printer = new TimetablePrinter();
        printer.print(timetable);
    }
}
