package com.clinnection.wf.lang.expr;

import com.clinnection.wf.lang.DataType;

public class CompareExpr extends BinaryExpr {
    public CompareExpr(String op, Expr lhs, Expr rhs) {
        super(lhs.getDataType(), op, lhs, rhs);
    }
}
