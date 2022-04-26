package com.clinnection.wf.lang.expr;

import com.clinnection.wf.lang.DataType;
import com.clinnection.wf.lang.var.Var;
import org.json.JSONObject;

public class VarExpr extends Expr {
    private Var var;

    public VarExpr(Var var) {
        super(var.getDataType(), "Variable");
        this.var = var;
    }

    public Var getVar() {
        return var;
    }

    public void setVar(Var var) {
        this.var = var;
    }

    @Override
    public JSONObject toParseTree() {
        JSONObject jsonObject = super.toParseTree();
        jsonObject.putOnce("name", var.getName() );
        return jsonObject;
    }
}
