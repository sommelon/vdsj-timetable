grammar Grammar;

timetable : timetableHeader schedule+ EOF;

timetableHeader : timetableName timetableYear '-' timetableGrade ':';

timetableName: STRING+;
timetableYear: STRING NUMBER'/'NUMBER;
timetableGrade: STRING;

schedule : scheduleName ':' event+?;

scheduleName : STRING+;

event : eventType '(' eventGroups ')' ':' day time_from '-' time_to ';' teacher (',' teacher)* ';';

eventType : STRING;
eventGroups : NUMBER (',' NUMBER)*;
day : STRING;
time_from : NUMBER ':' NUMBER;
time_to : NUMBER ':' NUMBER;
teacher : STRING+;


NUMBER: [0-9]+;

PARAMETER : '"'~["]+'"';

WS : ( ' ' | '\t' | '\r' | '\n' )+ -> skip;

COMMENT : ('***' .*? '***') -> skip;

STRING : ([A-Za-z\u0080-\uFFFF] ~( ' ' | '\t' | '\r' | '\n' | '(' | ')' )* [A-Za-z0-9\u0080-\uFFFF])|
         ([A-Za-z\u0080-\uFFFF]);