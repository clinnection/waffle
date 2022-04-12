package com.clinnection.wf.lang.stmt;

import com.clinnection.wf.lang.expr.Expr;
import com.clinnection.wf.lang.var.Var;

public class AssignmentStmt extends Stmt {
    private Var var;
    private Expr expr;

    public AssignmentStmt(Var var, Expr expr) {
        super(Type.VarAssignment);

        if (var.getDataType() != expr.getDataType()) {
            throw new RuntimeException(var.getName() + ": cannot assign "+ expr.getDataType() + " to " + var.getDataType());
        }
        this.var = var;
        this.expr = expr;
    }

    public Var getVar() {
        return var;
    }

    public void setVar(Var var) {
        this.var = var;
    }

    public Expr getExpr() {
        return expr;
    }

    public void setExpr(Expr expr) {
        this.expr = expr;
    }
}