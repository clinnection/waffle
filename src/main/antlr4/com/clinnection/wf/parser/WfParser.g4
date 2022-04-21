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
    : decl_type=( DECL_BOOLEAN | DECL_INTEGER | DECL_DECIMAL | DECL_STRING | DECL_DATE ) decl_var=IDENTIFIER
    ;


assignment_stmt
    : id=IDENTIFIER ASSIGN expra=integerExpr                  # assignStmt
    | id=IDENTIFIER ASSIGN exprb=decimalExpr                  # assignStmt
    | id=IDENTIFIER ASSIGN exprc=stringExpr                   # assignStmt
    | id=IDENTIFIER ASSIGN exprd=dateExpr                     # assignStmt
//    | json_identifier ':=' expr                          # jsonAssignStmt
    ;

compareExpr
    : integerExpr op=( EQ | NE | LT | GT | LT | GE | LE ) integerExpr
    | decimalExpr op=( EQ | NE | LT | GT | LT | GE | LE ) decimalExpr
    ;

stringExpr
    : stringExpr ADD stringExpr                                         # binaryBinaryExpr
    | arg=STRING                                                        # literalStringExpr
    | id=IDENTIFIER                                                     # varStringExpr
    ;

dateExpr
    : dateExpr op=ADD integerExpr                                       # binaryDateExpr
    | dateExpr op=MINUS integerExpr                                     # binaryDateExpr
    | id=IDENTIFIER                                                     # varDateExpr
    ;

integerExpr
    : op=MINUS integerExpr                                             # unaryIntegerExpr
    | op=OPAR integerExpr CPAR                                         # unaryIntegerExpr
    | integerExpr op=(MULT | DIV | MOD) integerExpr                    # binaryIntegerExpr
    | integerExpr op=(ADD | MINUS) integerExpr                         # binaryIntegerExpr
    | INTEGER                                                          # literalIntegerExpr

//    | id=IDENTIFIER                                                    # varIntegerExpr
    ;

decimalExpr
    : op=MINUS decimalExpr                                             # unaryDecimalExpr
    | op=OPAR decimalExpr CPAR                                         # unaryDecimalExpr
    | decimalExpr op=(MULT | DIV | MOD) decimalExpr                    # binaryDecimalExpr
    | decimalExpr op=(ADD | MINUS) decimalExpr                         # binaryDecimalExpr

    | arg=VAR_DECIMAL                                                  # literalDecimalExpr
    | arg=INTEGER                                                      # literalDecimalExpr

    | id=IDENTIFIER                                                    # varDecimalExpr
    ;

varExpr
    : id=IDENTIFIER # varExprx
    ;
