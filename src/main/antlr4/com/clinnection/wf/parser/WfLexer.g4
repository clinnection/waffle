lexer grammar WfLexer;

DECLARE  : 'declare' ;

DECL_BOOLEAN : 'boolean' ;
DECL_INTEGER : 'integer' ;
DECL_DECIMAL : 'decimal' ;
DECL_STRING : 'string' ;
DECL_DATE : 'date' ;

ASSIGN : ':=';
MINUS  : '-';
ADD    : '+';
MULT   : '*';
DIV    : '/';
MOD    : '%';
OPAR   : '(';
CPAR   : ')';

EQ : '==';
NE : '!=';
LT : '<';
GT : '>';
LE : '<=';
GE : '>=';

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
