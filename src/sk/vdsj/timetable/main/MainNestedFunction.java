package sk.vdsj.timetable.main;

import sk.vdsj.timetable.builder.NestedFunctionBuilder;
import sk.vdsj.timetable.model.Timetable;
import sk.vdsj.timetable.semantics.TimetablePrinter;

import static sk.vdsj.timetable.builder.NestedFunctionBuilder.*;

public class MainNestedFunction {
    public static void main(String[] args) {
        /***************************************************************/

        timetable("Informatika",
                  "LS 2020/2021",
                "12.09.2020",
                "21.12.2020",
                "BC1",
                   schedule("Vývoj doménovo-špecifických jazykov",
                            event("CL",
                                  "STR 10:50-12:20",
                                  "ZP1",
                                  "1, 2",
                                  organiser("Janko Hraško"),
                                  "Prezenčne",
                                  2
                            ),
                            event("P",
                                  "STR 13:30-15:00",
                                  "ZP1",
                                  "1, 2",
                                  organisers("Janko Hraško",
                                          "Jožko Mrkvička"),
                                  2
                            )
                   ),
                   schedule("Paralelné programovanie",
                            event("CL",
                                  "STV 10:50-12:20",
                                  "ZP1",
                                  "1, 2, 3",
                                  organiser("Ivan Gašparovič"),
                                  "Prezenčne"
                            ),
                            event("P",
                                  "STV 13:30-15:00",
                                  "ZP1",
                                  organisers("Ivan Gašparovič",
                                          "Ing. Andrej Danko, PhD"),
                                  2
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
