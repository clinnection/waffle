package com.clinnection.wf.lang.expr;

import com.clinnection.wf.lang.DataType;

public class DateUnaryExpr extends UnaryExpr {
    public DateUnaryExpr(String op, Expr rhs) {
        super(DataType.Date, op, rhs);
    }
}
