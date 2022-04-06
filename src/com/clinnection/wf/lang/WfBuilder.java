package com.clinnection.wf.lang;

import com.clinnection.wf.lang.expr.*;

import java.util.Stack;

public class WfBuilder extends wfBaseListener {

    private Stack<Expr>  exprs;

    public WfBuilder() {
        exprs   = new Stack<Expr>();
    }

    @Override
    public void exitLiteralIntegerExpr(wfParser.LiteralIntegerExprContext ctx) {
        super.exitLiteralIntegerExpr(ctx);
        System.out.println("exitLiteralIntegerExpr: " + ctx.getText().toString());

        IntegerLiteralExpr integerLiteralExpr = new IntegerLiteralExpr(ctx.getText().toString());
        exprs.push(integerLiteralExpr);
    }

    @Override
    public void exitUnaryIntegerExpr(wfParser.UnaryIntegerExprContext ctx) {
        super.exitUnaryIntegerExpr(ctx);
        System.out.println("exitUnaryIntegerExpr: " + ctx.getText().toString());

        String op = ctx.op.getText().toString();
        System.out.println("op: " + op);

        Expr rhs = exprs.pop();

        IntegerUnaryExpr integerUnaryExpr = new IntegerUnaryExpr(op, rhs);
        exprs.push(integerUnaryExpr);
    }

    @Override
    public void exitBinaryIntegerExpr(wfParser.BinaryIntegerExprContext ctx) {
        super.exitBinaryIntegerExpr(ctx);
        System.out.println("exitBinaryIntegerExpr: " + ctx.getText().toString());

        String op = ctx.op.getText().toString();
        System.out.println("op: " + op);

        Expr rhs = exprs.pop();
        Expr lhs = exprs.pop();

        IntegerBinaryExpr integerBinaryExpr = new IntegerBinaryExpr(op, lhs, rhs);
        exprs.push(integerBinaryExpr);
    }


    @Override
    public void exitLiteralDecimalExpr(wfParser.LiteralDecimalExprContext ctx) {
        super.exitLiteralDecimalExpr(ctx);
        System.out.println("exitLiteralDecimalExpr: " + ctx.getText().toString());

        DecimalLiteralExpr integerLiteralExpr = new DecimalLiteralExpr(ctx.getText().toString());
        exprs.push(integerLiteralExpr);
    }

    @Override
    public void exitUnaryDecimalExpr(wfParser.UnaryDecimalExprContext ctx) {
        super.exitUnaryDecimalExpr(ctx);
        System.out.println("exitUnaryDecimalExpr: " + ctx.getText().toString());

        String op = ctx.op.getText().toString();
        System.out.println("op: " + op);

        Expr rhs = exprs.pop();

        DecimalUnaryExpr decimalUnaryExpr = new DecimalUnaryExpr(op, rhs);
        exprs.push(decimalUnaryExpr);
    }

    @Override
    public void exitBinaryDecimalExpr(wfParser.BinaryDecimalExprContext ctx) {
        super.exitBinaryDecimalExpr(ctx);
        System.out.println("exitBinaryDecimalExpr: " + ctx.getText().toString());

        String op = ctx.op.getText().toString();
        System.out.println("op: " + op);

        Expr rhs = exprs.pop();
        Expr lhs = exprs.pop();

        DecimalBinaryExpr decimalBinaryExpr = new DecimalBinaryExpr(op, lhs, rhs);
        exprs.push(decimalBinaryExpr);
    }

    @Override
    public void exitIntegerDecimalExpr(wfParser.IntegerDecimalExprContext ctx) {
        super.exitIntegerDecimalExpr(ctx);
        System.out.println("exitIntegerDecimalExpr: " + ctx.getText().toString());

        Expr rhs = exprs.pop();

        IntegerDecimalExpr integerDecimalExpr = new IntegerDecimalExpr("#", rhs);
        exprs.push(integerDecimalExpr);
    }
}
