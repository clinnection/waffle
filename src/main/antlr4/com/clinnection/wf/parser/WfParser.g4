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
    : assignmentStmt
    | whileStmt
    | ifStmt
    ;

decl
    : declType=DECL_BOOLEAN    declVar=VAR_BOOLEAN
    | declType=DECL_INTEGER    declVar=VAR_INTEGER
    | declType=DECL_DECIMAL    declVar=VAR_DECIMAL
    | declType=DECL_STRING     declVar=VAR_STRING
    | declType=DECL_DATE       declVar=VAR_DATE
    ;


assignmentStmt
    : id=VAR_BOOLEAN ASSIGN booleanExpr         # assignStmt
    | id=VAR_DECIMAL ASSIGN decimalExpr         # assignStmt
    | id=VAR_INTEGER ASSIGN integerExpr         # assignStmt
    | id=VAR_STRING  ASSIGN stringExpr          # assignStmt
    | id=VAR_DATE    ASSIGN dateExpr            # assignStmt
    | id=VAR_DATE    ASSIGN stringExpr          # assignStmt
    ;

whileStmt
    : WHILE booleanExpr whileDo block END WHILE
    ;

whileDo
    : DO
    ;

ifStmt
    : IF booleanExpr ifThen block elseIfStmt* elseStmt? END IF
    ;

elseIfStmt
    : ELSE IF booleanExpr ifThen block
    ;

elseStmt
    : ELSE block
    ;

ifThen
    : THEN
    ;

booleanExpr
    : op=OPAR booleanExpr CPAR                          # unaryBooleanExpr
    | op=NOT booleanExpr                                # unaryBooleanExpr
    | booleanExpr op=(EQ | NE | AND | OR) booleanExpr   # binaryBooleanExpr
    | compareExpr                                       # compareBooleanExpr
    | VAR_BOOLEAN                                       # varBooleanExpr
    | ( TRUE | FALSE )                                  # literalBooleanExpr
    ;

compareExpr
    : integerExpr op=( EQ | NE | LT | GT | GE | LE ) integerExpr
    | decimalExpr op=( EQ | NE | LT | GT | GE | LE ) decimalExpr
    | stringExpr  op=( EQ | NE | LT | GT | GE | LE ) stringExpr
    | dateExpr    op=( EQ | NE | LT | GT | GE | LE ) dateExpr
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
