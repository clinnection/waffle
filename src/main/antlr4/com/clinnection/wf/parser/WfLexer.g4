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
        put("string",  WfLexer.VAR_STRING);
        put("date",    WfLexer.VAR_DATE);
    }};

    public Stack<Map<String, Integer>> types;

    public void startScope() {
        types.push(new HashMap<String, Integer>());
    }
    public void endScope() {
        types.pop();
    }

    public String varType = null;

}

DECL_BOOLEAN : 'boolean'  { varType = getText(); };
DECL_INTEGER : 'integer'  { varType = getText(); };
DECL_DECIMAL : 'decimal'  { varType = getText(); };
DECL_STRING  : 'string'   { varType = getText(); };
DECL_DATE    : 'date'     { varType = getText(); };

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


WHILE   : 'while';
DO      : 'do' { startScope(); };
IF      : 'if';
THEN    : 'then' { startScope(); };
ELSE    : 'else'{ endScope(); };
END     : 'end'{ endScope(); };

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

        if (types == null) {
            System.out.println("Initializing types");
            types = new Stack<>();
            startScope();
        }

        if (varType != null) {
            if (types.peek().containsKey(name)) {
                throw new RuntimeException(name + ": redeclared");
            }

            System.out.println("varType: " + varType);
            if (declTypeMap.containsKey(varType)) {
                int type = declTypeMap.get(varType);
                System.out.println("Declared type: " + type);
                setType(type);
                types.peek().put(name, type);
            } else {
                throw new RuntimeException("I'm so confused");
            }
        } else {
            System.out.println("varType is null, looking for type");

            int type = -1;

            for (int i = types.size() - 1; i >= 0; i--) {
                if (types.get(i).containsKey(name)) {
                    type = types.get(i).get(name);
                    System.out.println("Identifier type:" + type);
                    break;
                }
            }

            if (type != -1) {
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
