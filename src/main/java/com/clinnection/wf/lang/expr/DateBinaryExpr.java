package com.clinnection.wf.lang.expr;

import com.clinnection.wf.lang.DataType;

public class DateBinaryExpr extends BinaryExpr {

    public DateBinaryExpr(String op, Expr lhs, Expr rhs) {
        super(DataType.Date, op, lhs, rhs);
    }
}
