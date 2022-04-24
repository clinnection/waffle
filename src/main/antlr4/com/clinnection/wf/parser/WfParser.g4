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
    : decl_type=DECL_BOOLEAN    decl_var=VAR_BOOLEAN
    | decl_type=DECL_INTEGER    decl_var=VAR_INTEGER
    | decl_type=DECL_DECIMAL    decl_var=VAR_DECIMAL
    | decl_type=DECL_STRING     decl_var=VAR_STRING
    | decl_type=DECL_DATE       decl_var=VAR_DATE
    ;


assignment_stmt
    : id=VAR_DECIMAL ASSIGN decimalExpr         # assignStmt
    | id=VAR_INTEGER ASSIGN integerExpr         # assignStmt
    | id=VAR_STRING  ASSIGN stringExpr          # assignStmt
    | id=VAR_DATE    ASSIGN dateExpr            # assignStmt
    | id=VAR_DATE    ASSIGN stringExpr          # assignStmt
    ;

booleanExpr
    : OPAR booleanExpr CPAR                     # booleanUnaryExpr
    | NOT booleanExpr                           # booleanUnaryExpr
    | booleanExpr op=(AND | OR) booleanExpr     # booleanBinaryExpr
    | compareExpr                               # booleanComapreExpr
    | ( TRUE | FALSE )                          # booleanLiteralExpr
    ;

compareExpr
    : integerExpr op=( EQ | NE | LT | GT | LT | GE | LE ) integerExpr # integerCompareExpr
    | decimalExpr op=( EQ | NE | LT | GT | LT | GE | LE ) decimalExpr # decimalCompareExpr
    | stringExpr  op=( EQ | NE | LT | GT | LT | GE | LE ) stringExpr  # stringCompareExpr
    | dateExpr    op=( EQ | NE | LT | GT | LT | GE | LE ) dateExpr    # dateCompareExpr
    ;


stringExpr
    : op=OPAR stringExpr CPAR                                           # unaryStringExpr
    | stringExpr op=ADD stringExpr                                      # binaryStringExpr
    | VAR_STRING                                                        # varStringExpr
    | STRING                                                            # literalStringExpr
    ;

integerExpr
    : op=MINUS integerExpr                                             # unaryIntegerExpr
    | op=OPAR integerExpr CPAR                                         # unaryIntegerExpr
    | integerExpr op=(MULT | DIV | MOD) integerExpr                    # binaryIntegerExpr
    | integerExpr op=(ADD | MINUS) integerExpr                         # binaryIntegerExpr

    | dateExpr op=MINUS dateExpr                                       # binaryIntegerExpr

    | VAR_INTEGER                                                      # varIntegerExpr
    | INTEGER                                                          # literalIntegerExpr
    ;

decimalExpr
    : op=MINUS decimalExpr                                             # unaryDecimalExpr
    | op=OPAR decimalExpr CPAR                                         # unaryDecimalExpr
    | decimalExpr op=(MULT | DIV | MOD) decimalExpr                    # binaryDecimalExpr
    | decimalExpr op=(ADD | MINUS) decimalExpr                         # binaryDecimalExpr

    | arg=(VAR_DECIMAL | VAR_INTEGER )                                 # varDecimalExpr
    | arg=(DECIMAL | INTEGER )                                         # literalDecimalExpr
    ;

dateExpr
    : op=OPAR dateExpr CPAR                                             # unaryDateExpr
    | dateExpr op=(ADD | MINUS) integerExpr                             # binaryDateExpr
    | VAR_DATE                                                          # varDateExpr
    ;
