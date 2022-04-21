package com.clinnection.wf.lang.expr;

import com.clinnection.wf.lang.DataType;

public class StringLiteralExpr extends Expr {
    private String value;

    public StringLiteralExpr(String value) {
        super(DataType.String, "Literal");
        this.value = value;
    }
}
