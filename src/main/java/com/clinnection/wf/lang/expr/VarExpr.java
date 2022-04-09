package com.clinnection.wf.lang.expr;

import com.clinnection.wf.lang.DataType;
import com.clinnection.wf.lang.var.Var;

public class VarExpr extends Expr {
    private Var var;

    public VarExpr(DataType dataType) {
        super(dataType, "#");
    }

    public Var getVar() {
        return var;
    }

    public void setVar(Var var) {
        this.var = var;
    }
}
