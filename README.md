# vdsj-timetable

A domain specific language for building timetables.
Language structure:
```
*** COMMENT ***
FIELD OF STUDY SEMESTER START_YEAR/END_YEAR (TIMETABLE_RANGE_START - TIMETABLE_RANGE_END) - GRADE:
	SUBJECT_TITLE:
		EVENT_TYPE[(STUDY_GROUP1[,STUDY_GROUP2]*)]: [INTERVAL**]DAY_OF_WEEK TIME_FROM-TIME_TO - LOCATION; ORGANISER1[,ORGANISER2]* ["NOTE"];
```
Language example:
```
*** External DSL Example: ***
Informatika LS 2020/2021 (21.10.2020 - 22.12.2020) - BC1:
	Vývoj domenovo špecifických jazykov:
		CL(1,2): 2**STR 10:50-12:20 - ZP1; Janko Hraško;
		P: STR 13:30-15:00 - ZP2; Ing. Mgr. Janko Hraško JUDr., Jurko Mrkvička "Specialna Poznamka";
	Počítačová Grafika:
		CL(1): STV 09:10-10:40 - ZP1; Janko Hraško;
		CL(2): STV 10:50-12:20 - ZP2; Janko Hraško;
```

### How to run
Generate ANTLR4 files
```
antlr4 -o <PATHTOPROJECT>vdsj-timetable\src\sk\vdsj\timetable\antlr4\grammar -package sk.vdsj.timetable.antlr4.grammar -listener -visitor -lib <PATHTOPROJECT>vdsj-timetable/src/sk/vdsj/timetable/antlr4/grammar <PATHTOPROJECT>/vdsj-timetable/src/sk/vdsj/timetable/antlr4/grammar\Grammar.g4
```

Run either of these files (Requires Java 15):
- `MainFunctionSequence.java` - Proof of concept using a function sequence pattern
- `MainMethodChainingBuilder.java` - Proof of concept using a method chaining pattern
- `MainNestedFunction.java` - Proof of concept using a nested function pattern
- `MainParserListener.java` - Implementation of the language using the Listener pattern
- `MainParserVisitor.java` - Implementation of the language using the Visitor pattern
- `MainICalExport.java` - Generate the timetable as an iCal file
- `MainWebGeneratorVelocity.java` - Generate frontend for the generated timetable
