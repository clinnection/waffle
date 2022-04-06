package com.clinnection.wf.lang.expr;

import com.clinnection.wf.lang.DataType;

public class DecimalBinaryExpr extends BinaryExpr {

    public DecimalBinaryExpr(String op, Expr lhs, Expr rhs) {
        super(DataType.Decimal, op, lhs, rhs);
    }
}
