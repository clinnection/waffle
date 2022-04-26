package com.clinnection.wf.lang.expr;

import com.clinnection.wf.lang.DataType;

public class BooleanCompareExpr extends UnaryExpr {

    public BooleanCompareExpr(Expr expr) {
        super(expr.getDataType(), expr.getOp(), expr);
    }
}
