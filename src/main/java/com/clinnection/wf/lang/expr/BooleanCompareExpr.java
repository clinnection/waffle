package com.clinnection.wf.lang.expr;

import com.clinnection.wf.lang.DataType;

public class BooleanCompareExpr extends BinaryExpr {

    public BooleanCompareExpr(CompareExpr expr) {
        super(DataType.Boolean, expr.getOp(), expr.getLhs(), expr.getRhs());
    }
}
