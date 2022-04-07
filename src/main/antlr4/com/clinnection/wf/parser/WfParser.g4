parser grammar WfParser;

options {
  tokenVocab=WfLexer;
}
program
    : block EOF
    ;

block
    : decl* stmt*
    ;

stmt
    : assignment_stmt
    ;

decl
    : DECLARE decl_var=IDENTIFIER decl_type=( DECL_BOOLEAN | DECL_INTEGER | DECL_DECIMAL | DECL_STRING )
    ;


assignment_stmt
    : IDENTIFIER ASSIGN decimalExpr                          # varAssignStmt
//    | json_identifier ':=' expr                          # jsonAssignStmt
    ;

compareExpr
    : integerExpr EQUALS integerExpr # a
    | decimalExpr EQUALS  decimalExpr # c
    ;

integerExpr
    : op=MINUS integerExpr                                             # unaryIntegerExpr
    | op=OPAR integerExpr CPAR                                         # unaryIntegerExpr
    | integerExpr op=(MULT | DIV | MOD) integerExpr                    # binaryIntegerExpr
    | integerExpr op=(ADD | MINUS) integerExpr                         # binaryIntegerExpr
    | INTEGER                                                          # literalIntegerExpr
    ;

decimalExpr
    : op=MINUS decimalExpr                                             # unaryDecimalExpr
    | op=OPAR decimalExpr CPAR                                         # unaryDecimalExpr
    | decimalExpr op=(MULT | DIV | MOD) decimalExpr                    # binaryDecimalExpr
    | decimalExpr op=(ADD | MINUS) decimalExpr                         # binaryDecimalExpr

    | arg=DECIMAL                                                      # literalDecimalExpr
    | arg=INTEGER                                                      # literalDecimalExpr

    | IDENTIFIER                                                       # varDecimalExpr
    ;
