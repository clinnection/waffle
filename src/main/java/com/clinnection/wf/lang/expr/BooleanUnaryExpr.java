package com.clinnection.wf.lang.expr;

import com.clinnection.wf.lang.DataType;

public class BooleanUnaryExpr extends UnaryExpr {
    public BooleanUnaryExpr(String op, Expr rhs) {
        super(DataType.Boolean, op, rhs);
    }
}
