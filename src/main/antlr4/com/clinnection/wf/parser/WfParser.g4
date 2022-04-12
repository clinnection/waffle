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
    : DECLARE decl_var=IDENTIFIER decl_type=( DECL_BOOLEAN | DECL_INTEGER | DECL_DECIMAL | DECL_STRING | DECL_DATE )
    ;


assignment_stmt
    : id=IDENTIFIER ASSIGN expr=integerExpr                          # integerAssignExpr
    | id=IDENTIFIER ASSIGN expr=decimalExpr                          # decimalAssignStmt
//    | json_identifier ':=' expr                          # jsonAssignStmt
    ;

compareExpr
    : integerExpr op=( EQ | NE | LT | GT | LT | GE | LE ) integerExpr
    | decimalExpr op=( EQ | NE | LT | GT | LT | GE | LE ) decimalExpr
    ;

integerExpr
    : op=MINUS integerExpr                                             # unaryIntegerExpr
    | op=OPAR integerExpr CPAR                                         # unaryIntegerExpr
    | integerExpr op=(MULT | DIV | MOD) integerExpr                    # binaryIntegerExpr
    | integerExpr op=(ADD | MINUS) integerExpr                         # binaryIntegerExpr
    | INTEGER                                                          # literalIntegerExpr

    | id=IDENTIFIER                                                    # varIntegerExpr
    ;

decimalExpr
    : op=MINUS decimalExpr                                             # unaryDecimalExpr
    | op=OPAR decimalExpr CPAR                                         # unaryDecimalExpr
    | decimalExpr op=(MULT | DIV | MOD) decimalExpr                    # binaryDecimalExpr
    | decimalExpr op=(ADD | MINUS) decimalExpr                         # binaryDecimalExpr

    | arg=DECIMAL                                                      # literalDecimalExpr
    | arg=INTEGER                                                      # literalDecimalExpr

    | id=IDENTIFIER                                                    # varDecimalExpr
    ;
