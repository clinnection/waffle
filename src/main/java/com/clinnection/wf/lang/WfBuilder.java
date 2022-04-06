package com.clinnection.wf.lang;

import com.clinnection.wf.lang.expr.*;
import com.clinnection.wf.parser.*;

import java.util.Stack;

public class WfBuilder extends WfParserBaseListener {

    private Stack<Expr>  exprs;

    public WfBuilder() {
        exprs   = new Stack<Expr>();
    }

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


    @Override
    public void exitLiteralDecimalExpr(WfParser.LiteralDecimalExprContext ctx) {
        System.out.println("exitLiteralDecimalExpr: " + ctx.getText());

        DecimalLiteralExpr integerLiteralExpr = new DecimalLiteralExpr(ctx.getText());
        exprs.push(integerLiteralExpr);
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

    //    @Override
    //    public void exitIntegerDecimalExpr(wfParser.IntegerDecimalExprContext ctx) {
    //        super.exitIntegerDecimalExpr(ctx);
    //        System.out.println("exitIntegerDecimalExpr: " + ctx.getText().toString());
    //
    //        Expr rhs = exprs.pop();
    //
    //        IntegerDecimalExpr integerDecimalExpr = new IntegerDecimalExpr("#", rhs);
    //        exprs.push(integerDecimalExpr);
    //    }
}
