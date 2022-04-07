package com.clinnection.wf.lang.expr;

import com.clinnection.wf.lang.DataType;

import static java.lang.Integer.parseInt;

public class IntegerLiteralExpr extends Expr {
    private int value;

    public IntegerLiteralExpr(String value) {
        super(DataType.Integer, "Literal");
        this.value = parseInt(value);
    }
}
