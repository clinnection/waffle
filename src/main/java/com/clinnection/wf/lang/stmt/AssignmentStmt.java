package com.clinnection.wf.lang.stmt;

import com.clinnection.wf.lang.DataType;
import com.clinnection.wf.lang.expr.CastExpr;
import com.clinnection.wf.lang.expr.Expr;
import com.clinnection.wf.lang.var.Var;

public class AssignmentStmt extends Stmt {
    private Var var;
    private Expr expr;

    public AssignmentStmt(Var var, Expr expr) {
        super(Type.VarAssignment);

        DataType varDataType = var.getDataType();
        DataType exprDataType = expr.getDataType();

        System.out.println("varDataType: " + varDataType);
        System.out.println("exprDataType: " + exprDataType);

        if (varDataType != exprDataType) {
            expr = new CastExpr(varDataType, expr);
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