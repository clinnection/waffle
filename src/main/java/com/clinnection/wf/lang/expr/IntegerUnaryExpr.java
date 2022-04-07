package com.clinnection.wf.lang.expr;

import com.clinnection.wf.lang.DataType;

public class IntegerUnaryExpr extends UnaryExpr {
    public IntegerUnaryExpr(String op, Expr rhs) {
        super(DataType.Integer, op, rhs);
    }
}
