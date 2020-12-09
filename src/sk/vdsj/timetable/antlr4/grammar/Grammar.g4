grammar Grammar;

timetable : timetableHeader schedule+ EOF;

timetableHeader : programme semester '-' grade ':';

programme: STRING+;
semester: STRING NUMBER'/'NUMBER;
grade: STRING;

schedule : scheduleTitle ':' event+?;

scheduleTitle : STRING+;

event : eventType '(' groups ')' ':' time '-' location ';' organiser (',' organiser)* ('"' note '"')? ';';

time: day time_from '-' time_to;
eventType : STRING;
groups : NUMBER (',' NUMBER)*;
day : STRING;
time_from : NUMBER ':' NUMBER;
time_to : NUMBER ':' NUMBER;
location : STRING+;
organiser : STRING+;
note : STRING*;


NUMBER: [0-9]+;

WS : ( ' ' | '\t' | '\r' | '\n' )+ -> skip;

COMMENT : ('***' .*? '***') -> skip;

STRING : ([A-Za-z\u0080-\uFFFF] ~( ' ' | '\t' | '\r' | '\n' | '(' | ')' )* [A-Za-z0-9\u0080-\uFFFF])|
         ([A-Za-z\u0080-\uFFFF]);