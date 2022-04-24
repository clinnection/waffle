package com.clinnection.wf.lang.expr;

import com.clinnection.wf.lang.DataType;

public class StringBinaryExpr extends BinaryExpr {

    public StringBinaryExpr(String op, Expr lhs, Expr rhs) {
        super(DataType.String, op, lhs, rhs);
    }
}
