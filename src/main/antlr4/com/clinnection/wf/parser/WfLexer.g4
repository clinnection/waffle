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
    public Map<String, Integer> declTypeMap = new HashMap<String,Integer>(){{
        put("boolean", WfLexer.VAR_BOOLEAN);
        put("integer", WfLexer.VAR_INTEGER);
        put("decimal", WfLexer.VAR_DECIMAL);
        put("string", WfLexer.VAR_STRING);
        put("date", WfLexer.VAR_DATE);
    }};

    public Map<String, Integer> identifierTypeMap = new HashMap<String,Integer>();

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

AND   : '&&';
OR    : '||';
NOT   : '!';

TRUE  : 'true';
FALSE : 'false';

EQ : '==';
NE : '!=';
LT : '<';
GT : '>';
LE : '<=';
GE : '>=';

//BOOLEAN
//    : 'true'
//    | 'false'
//    ;

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

        String name = getText();
        System.out.println("IDENTIFIER: " + name);

        if (varType != null) {
            System.out.println("varType: " + varType);
            if (declTypeMap.containsKey(varType)) {
                if (declTypeMap.containsKey(varType)) {
                    int type = declTypeMap.get(varType);
                    System.out.println("Declared type: " + type);
                    setType(type);
                    identifierTypeMap.put(name, type);
                }
            } else {
                throw new RuntimeException("I'm so confused");
            }
        } else {
            System.out.println("varType is null");
            if (identifierTypeMap.containsKey(name)) {
                int type = identifierTypeMap.get(name);
                System.out.println("Identifier type:" + type);
                setType(type);
            } else {
                System.out.println("Identifer unmodified");
            }
        }
        varType = null;
    }
    ;

SPACE
    : [ \t\n\r] -> skip
    ;
