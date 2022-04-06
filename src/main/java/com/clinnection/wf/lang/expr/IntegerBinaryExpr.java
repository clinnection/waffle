package com.clinnection.wf.lang.expr;

import com.clinnection.wf.lang.DataType;

public class IntegerBinaryExpr extends BinaryExpr {

    public IntegerBinaryExpr(String op, Expr lhs, Expr rhs) {
        super(DataType.Integer, op, lhs, rhs);
    }
}
