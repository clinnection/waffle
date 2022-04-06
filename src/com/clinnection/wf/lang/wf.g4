grammar wf;

program
    : block EOF
    ;

block
//    : decl* stmt*
    : integerExpr
    | decimalExpr
    ;

compareExpr
    : integerExpr '=='  integerExpr # a
    | decimalExpr '=='  decimalExpr # c
    ;

integerExpr
    : op='-' integerExpr                                               # unaryIntegerExpr
    | op='(' integerExpr ')'                                           # unaryIntegerExpr
    | integerExpr op=('*' | '/' | '%' ) integerExpr                    # binaryIntegerExpr
    | integerExpr op=('+' | '-' ) integerExpr                          # binaryIntegerExpr
    | INTEGER                                                          # literalIntegerExpr
    ;

decimalExpr
    : op='-' decimalExpr                                               # unaryDecimalExpr
    | op='(' decimalExpr ')'                                           # unaryDecimalExpr
    | decimalExpr op=('*' | '/' | '%' ) decimalExpr                    # binaryDecimalExpr
    | decimalExpr op=('+' | '-' ) decimalExpr                          # binaryDecimalExpr
    | DECIMAL                                                          # literalDecimalExpr
    | INTEGER                                                          # literalIntDecimalExpr
    ;

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
