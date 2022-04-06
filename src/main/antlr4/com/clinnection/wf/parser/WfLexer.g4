lexer grammar WfLexer;

EQUALS : '==';
MINUS  : '-';
ADD    : '+';
MULT   : '*';
DIV    : '/';
MOD    : '%';
OPAR   : '(';
CPAR   : ')';

BOOLEAN
    : 'true'
    | 'false'
    ;

INTEGER
    : [0-9][0-9]*
    ;

DECIMAL
    : [0-9][0-9]*  '.' [0-9][0-9]*
    ;

STRING
    : ["] ( ~["\r\n\\] | '\\' ~[\r\n] )* ["]
    ;

IDENTIFIER
    : [A-Za-z][A-Za-z0-9_]*
    ;

SPACE
    : [ \t\n\r] -> skip
    ;
