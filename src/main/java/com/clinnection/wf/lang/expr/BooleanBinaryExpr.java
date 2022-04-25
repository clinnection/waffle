package com.clinnection.wf.lang.expr;

import com.clinnection.wf.lang.DataType;

public class BooleanBinaryExpr extends BinaryExpr {

    public BooleanBinaryExpr(String op, Expr lhs, Expr rhs) {
        super(DataType.Boolean, op, lhs, rhs);
    }
}
