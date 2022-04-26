package com.clinnection.wf.lang.expr;

import com.clinnection.wf.lang.DataType;
import org.json.JSONObject;

public abstract class BinaryExpr extends Expr {

    Expr lhs;
    Expr rhs;

    public BinaryExpr(DataType dataType, String op, Expr lhs, Expr rhs) {
        super(dataType, op);
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public BinaryExpr(DataType dataType, Operation op, Expr lhs, Expr rhs) {
        super(dataType, op);
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public Expr getLhs() {
        return lhs;
    }

    public Expr getRhs() {
        return rhs;
    }

    @Override
    public JSONObject toParseTree() {
        JSONObject jsonObject = super.toParseTree();
        jsonObject.putOnce("lhs", lhs.toParseTree());
        jsonObject.putOnce("rhs", rhs.toParseTree());
        return jsonObject;
    }
}
