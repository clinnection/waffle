package com.clinnection.wf.lang.expr;

import com.clinnection.wf.lang.DataType;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public abstract class Expr {
    private DataType dataType = DataType.Invalid;
    private Operation op = Operation.Invalid;



    private static final Map<String, Operation> opMap = new HashMap<String, Operation>(){{
        put("Literal", Operation.Literal);
        put("Variable", Operation.Variable);
        put("(", Operation.Paren);
        put("-", Operation.Neg);
        put("*", Operation.Mult);
        put("+", Operation.Plus);
        put("/", Operation.Div);
        put("%", Operation.Mod);

        put("&&", Operation.And);
        put("||", Operation.Or);
        put("!", Operation.Not);

        put("==", Operation.EQ);
        put("!=", Operation.NE);
        put("<",  Operation.LT);
        put(">",  Operation.GT);
        put("<=", Operation.LE);
        put(">=", Operation.GE);

        put("#", Operation.Cast);
    }};

    public Expr(DataType dataType, Operation op) {
        this.dataType = dataType;
        this.op = op;
    }

    public Expr(DataType dataType, String op) {
        this.dataType = dataType;
        if (!opMap.containsKey(op)) {
            throw new RuntimeException(op + ": Invalid expression op");
        }
        this.op = opMap.get(op);
    }

    enum Operation {
        Invalid,
        Literal,
        Variable,
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
        Not,

        Cast
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public Operation getOp() {
        return op;
    }

    public void setOp(Operation op) {
        this.op = op;
    }

    public JSONObject toParseTree() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.putOnce("op", op);
        jsonObject.putOnce("dataType", getDataType() );
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