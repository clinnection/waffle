parser grammar WfParser;

options {
  tokenVocab=WfLexer;
}

program
    : block EOF
    ;

block
//    : decl* stmt*
    : integerExpr
    | decimalExpr
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

    | decimalExpr op=(MULT | DIV | MOD) integerExpr                    # binaryDecimalExprA
    | integerExpr op=(ADD | MINUS) decimalExpr                         # binaryDecimalExprB

    | DECIMAL                                                          # literalDecimalExpr
    ;
