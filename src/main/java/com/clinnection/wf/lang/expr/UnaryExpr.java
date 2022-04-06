package com.clinnection.wf.lang.expr;

import com.clinnection.wf.lang.DataType;

public abstract class UnaryExpr extends Expr {

    Expr rhs;

    public UnaryExpr(DataType dataType, String op, Expr rhs) {
        super(dataType, op);
        this.rhs = rhs;
    }
}
