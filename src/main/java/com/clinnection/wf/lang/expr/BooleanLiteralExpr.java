package com.clinnection.wf.lang.expr;

import com.clinnection.wf.lang.DataType;

import java.time.LocalDate;

public class BooleanLiteralExpr extends Expr {
    private boolean value = false;

    public BooleanLiteralExpr(String value) {
        super(DataType.Boolean, "Literal");
        System.out.println("BooleanLiteralExpr: " + value);

        this.value = value.equals("true");
    }
}
