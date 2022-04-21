package com.clinnection.wf.lang.expr;

import com.clinnection.wf.lang.DataType;
import java.time.LocalDate;

public class DateLiteralExpr extends Expr {
    private LocalDate value;

    public DateLiteralExpr(String value) {
        super(DataType.Date, "Literal");
        System.out.println("DateLiteralExpr: " + value.substring(1, value.length() - 1));

        this.value = LocalDate.parse(value.substring(1, value.length() - 1));
    }
}