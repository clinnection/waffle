package com.clinnection.wf.lang.expr;

import com.clinnection.wf.lang.DataType;

import java.util.HashMap;
import java.util.Map;

public abstract class Expr {
    DataType dataType = DataType.Invalid;
    Type type = Type.Invalid;

    private static final Map<String, Expr.Type> map = new HashMap(Map.of(
            "Literal", Type.Literal,
            "(", Type.Paren,
            "-", Type.Neg,
            "*", Type.Mult,
            "+", Type.Plus,
            "/", Type.Div,
            "%", Type.Mod,
            "#", Type.Cast
    ));


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
}
