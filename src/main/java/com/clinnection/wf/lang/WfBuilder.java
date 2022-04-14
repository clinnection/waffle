package com.clinnection.wf.lang;

import com.clinnection.wf.lang.expr.*;
import com.clinnection.wf.lang.stmt.AssignmentStmt;
import com.clinnection.wf.lang.stmt.Stmt;
import com.clinnection.wf.lang.var.Var;
import com.clinnection.wf.parser.*;

import java.util.Stack;

public class WfBuilder extends WfParserBaseListener {

    private Stack<Expr>   exprs;
    private Stack<Block>  blocks;
    private Stack<Stmt>   stmts;

    public WfBuilder() {
        exprs   = new Stack<Expr>();
        blocks  = new Stack<Block>();
        stmts  = new Stack<Stmt>();
    }

    private Var getVar(String name) {
        Var v = null;
        for (int i = blocks.size() - 1; i >= 0; i-- ) {
            v = blocks.get(i).getVar(name);
            if (v != null) {
                break;
            }
        }
        if (v == null) {
            throw new RuntimeException(name + ": not found");
        }
        return v;
    }

    /*
     * Program
     */
    @Override
    public void enterProgram(WfParser.ProgramContext ctx) {
        System.out.println("enterProgram: " + ctx.getText().toString());
        blocks.push(new Block());
    }

    @Override
    public void exitProgram(WfParser.ProgramContext ctx) {
        System.out.println("exitProgram: " + ctx.getText().toString());

        Block programBlock = blocks.pop();
    }

    /*
     * Declare
     */
    @Override
    public void exitDecl(WfParser.DeclContext ctx) {
        System.out.println("exitDecl: " + ctx.getText().toString());

        System.out.println("decl_var: " + ctx.decl_var.getText());
        System.out.println("decl_type: " + ctx.decl_type.getText());
        blocks.peek().addVar(Var.make(ctx.decl_var.getText(), ctx.decl_type.getText()));
    }

    /*
     * Integer
     */
    @Override
    public void exitLiteralIntegerExpr(WfParser.LiteralIntegerExprContext ctx) {
        System.out.println("exitLiteralIntegerExpr: " + ctx.getText().toString());

        IntegerLiteralExpr integerLiteralExpr = new IntegerLiteralExpr(ctx.getText().toString());
        exprs.push(integerLiteralExpr);
    }

    @Override
    public void exitUnaryIntegerExpr(WfParser.UnaryIntegerExprContext ctx) {
        System.out.println("exitUnaryIntegerExpr: " + ctx.getText().toString());

        String op = ctx.op.getText().toString();
        System.out.println("op: " + op);

        Expr rhs = exprs.pop();

        IntegerUnaryExpr integerUnaryExpr = new IntegerUnaryExpr(op, rhs);
        exprs.push(integerUnaryExpr);
    }

    @Override
    public void exitBinaryIntegerExpr(WfParser.BinaryIntegerExprContext ctx) {
        System.out.println("exitBinaryIntegerExpr: " + ctx.getText().toString());

        String op = ctx.op.getText().toString();
        System.out.println("op: " + op);

        Expr rhs = exprs.pop();
        Expr lhs = exprs.pop();

        IntegerBinaryExpr integerBinaryExpr = new IntegerBinaryExpr(op, lhs, rhs);
        exprs.push(integerBinaryExpr);
    }


    /*
        Decimal
     */
    @Override
    public void exitLiteralDecimalExpr(WfParser.LiteralDecimalExprContext ctx) {
        System.out.println("exitLiteralDecimalExpr: " + ctx.getText());
//        System.out.println("getType: " + ctx.arg.getType());

        DecimalLiteralExpr decimalLiteralExpr;

        switch (ctx.arg.getType()) {
            case WfLexer.INTEGER:
            case WfLexer.DECIMAL:
//                System.out.println("WfLexer.DECIMAL");
//                System.out.println("WfLexer.INTEGER");
                decimalLiteralExpr = new DecimalLiteralExpr(ctx.getText());
                break;
            default:
                throw new RuntimeException("Invalid type in double expression");
        }
        exprs.push(decimalLiteralExpr);
    }

    @Override
    public void exitUnaryDecimalExpr(WfParser.UnaryDecimalExprContext ctx) {
        System.out.println("exitUnaryDecimalExpr: " + ctx.getText());

        String op = ctx.op.getText().toString();
        System.out.println("op: " + op);

        Expr rhs = exprs.pop();

        DecimalUnaryExpr decimalUnaryExpr = new DecimalUnaryExpr(op, rhs);
        exprs.push(decimalUnaryExpr);
    }

    @Override
    public void exitBinaryDecimalExpr(WfParser.BinaryDecimalExprContext ctx) {
        System.out.println("exitBinaryDecimalExpr: " + ctx.getText());

        String op = ctx.op.getText();
        System.out.println("op: " + op);

        Expr rhs = exprs.pop();
        Expr lhs = exprs.pop();

        DecimalBinaryExpr decimalBinaryExpr = new DecimalBinaryExpr(op, lhs, rhs);
        exprs.push(decimalBinaryExpr);
    }

    @Override
    public void exitVarDecimalExpr(WfParser.VarDecimalExprContext ctx) {
        System.out.println("exitVarDecimalExpr: " + ctx.getText());

        Var var = getVar(ctx.id.getText());
        exprs.push(new VarExpr(var.getDataType()));
    }

    /* Assignment */
    @Override
    public void exitIntegerAssignStmt(WfParser.IntegerAssignStmtContext ctx) {
        System.out.println("exitIntegerAssignStmt: " + ctx.getText());
        System.out.println("id: " + ctx.id.getText());

        assign(getVar(ctx.id.getText()));
    }

    @Override
    public void exitDecimalAssignStmt(WfParser.DecimalAssignStmtContext ctx) {
        System.out.println("exitVarDecimalExpr: " + ctx.getText());
        System.out.println("id: " + ctx.id.getText());

        assign(getVar(ctx.id.getText()));
    }

    private void assign(Var var) {
        Expr expr = exprs.pop();
        DataType exprDataType = expr.getDataType();
        DataType varDataType = var.getDataType();

        if (varDataType != exprDataType) {
            expr = new CastExpr(varDataType, expr);
        }

        stmts.push(new AssignmentStmt(var, expr));
    }
}
