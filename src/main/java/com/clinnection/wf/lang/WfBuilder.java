package com.clinnection.wf.lang;

import com.clinnection.wf.lang.expr.*;
import com.clinnection.wf.parser.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class WfBuilder extends WfParserBaseListener {

    static final Map<String, DataType> dataTypeMap = new HashMap(Map.of(
            "boolean", DataType.Boolean,
            "integer", DataType.Integer,
            "decimal", DataType.Decimal,
            "string", DataType.String
    ));

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


}
