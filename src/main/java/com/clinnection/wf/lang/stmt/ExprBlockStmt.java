package com.clinnection.wf.lang.stmt;

import com.clinnection.wf.lang.expr.Expr;
import org.json.JSONObject;

public abstract class ExprBlockStmt extends BlockStmt {
    private Expr expr;

    public ExprBlockStmt(Type type) {
        super(type);
    }

    public Expr getExpr() {
        return expr;
    }

    public void setExpr(Expr expr) {
        this.expr = expr;
    }

    @Override
    public JSONObject toParseTree() {
        JSONObject jsonObject = super.toParseTree();
        jsonObject.putOnce("expr", expr.toParseTree());
        return jsonObject;
    }
}
