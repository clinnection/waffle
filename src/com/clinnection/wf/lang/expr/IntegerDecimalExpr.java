package com.clinnection.wf.lang.expr;

import com.clinnection.wf.lang.DataType;

public class IntegerDecimalExpr extends UnaryExpr {
    public IntegerDecimalExpr(String op, Expr rhs) {
        super(DataType.Decimal, op, rhs);
    }
}
