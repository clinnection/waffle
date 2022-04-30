package com.clinnection.wf.lang;

import com.clinnection.wf.lang.expr.*;
import com.clinnection.wf.lang.stmt.*;
import com.clinnection.wf.lang.var.Var;
import com.clinnection.wf.parser.*;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Stack;

public class WfBuilder extends WfParserBaseListener {

    private Stack<Expr> exprs;
    private Stack<Block> blocks;
    private Stack<Stmt> stmts;

    public WfBuilder() {
        exprs = new Stack<Expr>();
        blocks = new Stack<Block>();
        stmts = new Stack<Stmt>();
    }

    private Var getVar(String name) {
        Var v = null;
        for (int i = blocks.size() - 1; i >= 0; i--) {
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

//    @Override
//    public void exitEveryRule(ParserRuleContext ctx) {
//        System.out.println("- exitEveryRule: " + ctx.getText());
//    }

    /*
     * Program
     */
    @Override
    public void enterProgram(WfParser.ProgramContext ctx) {
        System.out.println("enterProgram: " + ctx.getText());
        blocks.push(new Block());
    }

    @Override
    public void exitProgram(WfParser.ProgramContext ctx) {
        System.out.println("exitProgram: " + ctx.getText());

        Block programBlock = blocks.pop();
        System.out.println(programBlock.toParseTree().toString());
    }

    /*
     * Declare
     */
    @Override
    public void exitDecl(WfParser.DeclContext ctx) {
        System.out.println("exitDecl: " + ctx.getText());

        blocks.peek().addVar(Var.make(ctx.declVar.getText(), ctx.declType.getText()));
    }

    /*
     * Assignment
     */
    @Override
    public void exitAssignStmt(WfParser.AssignStmtContext ctx) {
        System.out.println("exitAssignStmt: " + ctx.getText());

        blocks.peek().addStmt(new AssignmentStmt(getVar(ctx.id.getText()), exprs.pop()));
    }

    /*
     * Integer
     */
    @Override
    public void exitLiteralIntegerExpr(WfParser.LiteralIntegerExprContext ctx) {
        System.out.println("exitLiteralIntegerExpr: " + ctx.getText());

        IntegerLiteralExpr integerLiteralExpr = new IntegerLiteralExpr(ctx.getText());
        exprs.push(integerLiteralExpr);
    }

    @Override
    public void exitVarIntegerExpr(WfParser.VarIntegerExprContext ctx) {
        System.out.println("exitVarIntegerExpr: " + ctx.getText());

        exprs.push(new VarExpr(getVar(ctx.getText())));
    }

    @Override
    public void exitUnaryIntegerExpr(WfParser.UnaryIntegerExprContext ctx) {
        System.out.println("exitUnaryIntegerExpr: " + ctx.getText());

        String op = ctx.op.getText();
        System.out.println("op: " + op);

        Expr rhs = exprs.pop();

        IntegerUnaryExpr integerUnaryExpr = new IntegerUnaryExpr(op, rhs);
        exprs.push(integerUnaryExpr);
    }

    @Override
    public void exitBinaryIntegerExpr(WfParser.BinaryIntegerExprContext ctx) {
        System.out.println("exitBinaryIntegerExpr: " + ctx.getText());

        String op = ctx.op.getText();
        System.out.println("op: " + op);

        Expr rhs = exprs.pop();
        Expr lhs = exprs.pop();

        IntegerBinaryExpr integerBinaryExpr = new IntegerBinaryExpr(op, lhs, rhs);
        exprs.push(integerBinaryExpr);
    }


    /*
     *  Decimal
     */
    @Override
    public void exitLiteralDecimalExpr(WfParser.LiteralDecimalExprContext ctx) {
        System.out.println("exitLiteralDecimalExpr: " + ctx.getText());

        exprs.push(new DecimalLiteralExpr(ctx.getText()));
    }

    @Override
    public void exitVarDecimalExpr(WfParser.VarDecimalExprContext ctx) {
        System.out.println("exitVarIntegerExpr: " + ctx.getText());

        exprs.push(new VarExpr(getVar(ctx.getText())));
    }

    @Override
    public void exitUnaryDecimalExpr(WfParser.UnaryDecimalExprContext ctx) {
        System.out.println("exitUnaryDecimalExpr: " + ctx.getText());

        String op = ctx.op.getText();
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

    /*
     * String
     */
    @Override
    public void exitUnaryStringExpr(WfParser.UnaryStringExprContext ctx) {
        System.out.println("exitUnaryStringExpr: " + ctx.getText());

        String op = ctx.op.getText();
        System.out.println("op: " + op);

        Expr rhs = exprs.pop();

        StringUnaryExpr stringUnaryExpr = new StringUnaryExpr(op, rhs);
        exprs.push(stringUnaryExpr);
    }

    @Override
    public void exitLiteralStringExpr(WfParser.LiteralStringExprContext ctx) {
        String text = ctx.getText();
        text = text.substring(1, text.length() - 1);
        System.out.println("exitLiteralStringExpr: " + text);

        exprs.push(new StringLiteralExpr(text));
    }

    @Override
    public void exitVarStringExpr(WfParser.VarStringExprContext ctx) {
        System.out.println("exitVarStringExpr: " + ctx.getText());

        exprs.push(new VarExpr(getVar(ctx.getText())));
    }

    @Override
    public void exitBinaryStringExpr(WfParser.BinaryStringExprContext ctx) {
        System.out.println("exitBinaryStringExpr: " + ctx.getText());

        String op = ctx.op.getText();
        System.out.println("op: " + op);

        Expr rhs = exprs.pop();
        Expr lhs = exprs.pop();

        StringBinaryExpr stringBinaryExpr = new StringBinaryExpr(op, lhs, rhs);
        exprs.push(stringBinaryExpr);
    }


    /*
     * Date
     */
    @Override
    public void exitUnaryDateExpr(WfParser.UnaryDateExprContext ctx) {
        System.out.println("exitUnaryDateExpr: " + ctx.getText());

        String op = ctx.op.getText();
        System.out.println("op: " + op);

        Expr rhs = exprs.pop();

        DateUnaryExpr dateUnaryExpr = new DateUnaryExpr(op, rhs);
        exprs.push(dateUnaryExpr);
    }

    @Override
    public void exitVarDateExpr(WfParser.VarDateExprContext ctx) {
        System.out.println("exitVarDateExpr: " + ctx.getText());

        exprs.push(new VarExpr(getVar(ctx.getText())));
    }


    @Override
    public void exitBinaryDateExpr(WfParser.BinaryDateExprContext ctx) {
        System.out.println("exitBinaryDateExpr: " + ctx.getText());

        String op = ctx.op.getText();
        System.out.println("op: " + op);

        Expr rhs = exprs.pop();
        Expr lhs = exprs.pop();

        DateBinaryExpr dateBinaryExpr = new DateBinaryExpr(op, lhs, rhs);
        exprs.push(dateBinaryExpr);
    }

    /*
     * Boolean
     */
    @Override
    public void exitUnaryBooleanExpr(WfParser.UnaryBooleanExprContext ctx) {
        System.out.println("exitBooleanUnaryExpr: " + ctx.getText());

        String op = ctx.op.getText();
        System.out.println("op: " + op);

        Expr rhs = exprs.pop();

        BooleanUnaryExpr booleanUnaryExpr = new BooleanUnaryExpr(op, rhs);
        exprs.push(booleanUnaryExpr);
    }

    @Override
    public void exitBinaryBooleanExpr(WfParser.BinaryBooleanExprContext ctx) {
        System.out.println("exitBooleanBinaryExpr: " + ctx.getText());

        String op = ctx.op.getText();
        System.out.println("op: " + op);

        Expr rhs = exprs.pop();
        Expr lhs = exprs.pop();

        BooleanBinaryExpr booleanBinaryExpr = new BooleanBinaryExpr(op, lhs, rhs);
        exprs.push(booleanBinaryExpr);
    }

    @Override
    public void exitLiteralBooleanExpr(WfParser.LiteralBooleanExprContext ctx) {
        System.out.println("exitBooleanLiteralExpr: " + ctx.getText());

        exprs.push(new BooleanLiteralExpr(ctx.getText()));
    }

    @Override
    public void exitVarBooleanExpr(WfParser.VarBooleanExprContext ctx) {
        System.out.println("exitVarBooleanExpr: " + ctx.getText());

        exprs.push(new VarExpr(getVar(ctx.getText())));
    }

    @Override
    public void exitCompareBooleanExpr(WfParser.CompareBooleanExprContext ctx) {
        System.out.println("exitCompareBooleanExpr: " + ctx.getText());

        CompareExpr expr = (CompareExpr) exprs.pop();
        BooleanCompareExpr booleanCompareExpr = new BooleanCompareExpr(expr);
        exprs.push(booleanCompareExpr);
    }

    @Override
    public void exitCompareExpr(WfParser.CompareExprContext ctx) {
        System.out.println("exitCompareExpr: " + ctx.getText());

        String op = ctx.op.getText();
        System.out.println("op: " + op);

        Expr rhs = exprs.pop();
        Expr lhs = exprs.pop();

        CompareExpr compareExpr = new CompareExpr(op, lhs, rhs);
        exprs.push(compareExpr);
    }

    /*
     * While
     */
    @Override
    public void exitWhileDo(WfParser.WhileDoContext ctx) {
        System.out.println("exitWhileDo: " + ctx.getText());

        WhileStmt whileStmt = new WhileStmt();
        whileStmt.setExpr(exprs.pop());

        stmts.push(whileStmt);
        blocks.push(new Block());
    }

    @Override
    public void exitWhileStmt(WfParser.WhileStmtContext ctx) {
        System.out.println("exitWhileStmt: " + ctx.getText());

        WhileStmt whileStmt = (WhileStmt) stmts.pop();

        whileStmt.setBlock(blocks.pop());
        blocks.peek().addStmt(whileStmt);
    }

    //
    //  *** IF ***
    //

    /*
     * If
     */
    @Override
    public void enterIfStmt(WfParser.IfStmtContext ctx) {
        System.out.println("1 enterIfStmt: " + ctx.getText());

        stmts.push(new IfStmt());
        blocks.push(new Block());
    }

    @Override
    public void exitIfStmt(WfParser.IfStmtContext ctx) {
        System.out.println("2 exitIfStmt: " + ctx.getText());

        IfStmt ifStmt = (IfStmt) stmts.pop();
        ifStmt.setBlock(blocks.pop());

        blocks.peek().addStmt(ifStmt);
    }

    /*
     * Else If
     */
    @Override
    public void enterElseIfStmt(WfParser.ElseIfStmtContext ctx) {
        System.out.println("3 enterElseIfStmt: " + ctx.getText());

        stmts.push(new ElseIfStmt());
        blocks.push(new Block());
    }

    @Override
    public void exitElseIfStmt(WfParser.ElseIfStmtContext ctx) {
        System.out.println("4 exitElseIfStmt: " + ctx.getText());

        ElseIfStmt elseIfStmt = (ElseIfStmt) stmts.pop();
        elseIfStmt.setBlock(blocks.pop());

        IfStmt ifStmt = (IfStmt) stmts.peek();
        ifStmt.addElseIfStmts(elseIfStmt);
    }

    /*
     * Exit If Then
     */
    @Override
    public void exitIfThen(WfParser.IfThenContext ctx) {
        System.out.println("5 exitIfThen: " + ctx.getText());

        ExprBlockStmt exprBlockStmt = (ExprBlockStmt) stmts.peek();
        exprBlockStmt.setExpr(exprs.pop());
    }

    /*
     * Else
     */
    @Override
    public void enterElseStmt(WfParser.ElseStmtContext ctx) {
        System.out.println("6 enterElseStmt: " + ctx.getText());

        stmts.push(new ElseStmt());
        blocks.push(new Block());
    }

    @Override
    public void exitElseStmt(WfParser.ElseStmtContext ctx) {
        System.out.println("7 enterElseStmt: " + ctx.getText());

        ElseStmt elseStmt = (ElseStmt) stmts.pop();
        elseStmt.setBlock(blocks.pop());

        IfStmt ifStmt = (IfStmt) stmts.peek();
        ifStmt.setElseStmt(elseStmt);
    }
}