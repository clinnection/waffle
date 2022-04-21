lexer grammar WfLexer;

@lexer::header {
import java.util.*;
}

tokens {
    VAR_BOOLEAN,
    VAR_INTEGER,
    VAR_DECIMAL,
    VAR_STRING,
    VAR_DATE
}

@lexer::members {
    public Map<String, Integer> vars = new HashMap<String,Integer>(){{
	    put("boolean", WfLexer.DECL_BOOLEAN);
	    put("integer", WfLexer.DECL_INTEGER);
	    put("decimal", WfLexer.DECL_DECIMAL);
	    put("string", WfLexer.DECL_STRING);
	    put("date", WfLexer.DECL_DATE);
    }};
    public String varType = null;

}

DECL_BOOLEAN : 'boolean'  { varType = getText(); };
DECL_INTEGER : 'integer'  { varType = getText(); };
DECL_DECIMAL : 'decimal'  { varType = getText(); };
DECL_STRING : 'string'  { varType = getText(); };
DECL_DATE : 'date' { varType = getText(); };

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
    : [A-Za-z][A-Za-z0-9_]* {
        System.out.println("IDENTIFIER: " + getText());
        if (varType != null) {
            System.out.println("varType: " + varType);
            if (vars.containsKey(varType)) {
                setType(vars.get(varType));
            } else {
                throw new RuntimeException("I'm so confused");
            }


        } else {
            System.out.println("varType is null");
        }
        varType = null;
    }
    ;

SPACE
    : [ \t\n\r] -> skip
    ;
