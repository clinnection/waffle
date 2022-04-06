package com.clinnection.wf.lang.expr;

import com.clinnection.wf.lang.DataType;

public class DecimalUnaryExpr extends UnaryExpr {
    public DecimalUnaryExpr(String op, Expr rhs) {
        super(DataType.Decimal, op, rhs);
    }
}
