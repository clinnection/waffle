package com.clinnection.wf.lang.expr;

import com.clinnection.wf.lang.DataType;
import org.json.JSONObject;

public abstract class UnaryExpr extends Expr {

    Expr rhs;

    public UnaryExpr(DataType dataType, String op, Expr rhs) {
        super(dataType, op);
        this.rhs = rhs;
    }

    public UnaryExpr(DataType dataType, Operation op, Expr rhs) {
        super(dataType, op);
        this.rhs = rhs;
    }

    @Override
    public JSONObject toParseTree() {
        JSONObject jsonObject = super.toParseTree();
        jsonObject.putOnce("rhs", rhs.toParseTree());
        return jsonObject;
    }
}
