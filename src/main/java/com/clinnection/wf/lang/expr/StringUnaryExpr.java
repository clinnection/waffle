package com.clinnection.wf.lang.expr;

import com.clinnection.wf.lang.DataType;

public class StringUnaryExpr extends UnaryExpr {
    public StringUnaryExpr(String op, Expr rhs) {
        super(DataType.String, op, rhs);
    }
}
