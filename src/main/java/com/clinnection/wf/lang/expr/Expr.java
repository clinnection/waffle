package com.clinnection.wf.lang.expr;

import com.clinnection.wf.lang.DataType;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public abstract class Expr {
    DataType dataType = DataType.Invalid;
    Type type = Type.Invalid;



    private static final Map<String, Expr.Type> map = new HashMap<String, Expr.Type>(){{
        put("Literal", Type.Literal);
        put("(", Type.Paren);
        put("-", Type.Neg);
        put("*", Type.Mult);
        put("+", Type.Plus);
        put("/", Type.Div);
        put("%", Type.Mod);

        put("&&", Type.And);
        put("||", Type.Or);

        put("==", Type.EQ);
        put("!=", Type.NE);
        put("<",  Type.LT);
        put(">",  Type.GT);
        put("<=", Type.LE);
        put(">=", Type.GE);

        put("#", Type.Cast);
    }};


    public Expr(DataType dataType, String op) {
        this.dataType = dataType;
        if (!map.containsKey(op)) {
            throw new RuntimeException(op + ": Invalid expression op");
        }
        this.type = map.get(op);
    }

    enum Type {
        Invalid,
        Literal,
        Paren,
        Neg,
        Plus,
        Mult,
        Div,
        Mod,

        EQ,
        NE,
        LT,
        GT,
        LE,
        GE,

        And,
        Or,

        Cast
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public JSONObject toParseTree() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.putOnce("type", type);
        return jsonObject;
    }
}


/*
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
 */