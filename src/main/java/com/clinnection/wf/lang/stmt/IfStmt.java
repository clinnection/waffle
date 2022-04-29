package com.clinnection.wf.lang.stmt;

import java.util.ArrayList;
import java.util.List;

public class IfStmt extends ExprBlockStmt {

    public IfStmt() {
        super(Type.If);
    }

    private List<ElseIfStmt> elseIfStmts = new ArrayList<ElseIfStmt>();
    private ElseStmt elseStmt;

    public ElseStmt getElseStmt() {
        return elseStmt;
    }
    public void setElseStmt(ElseStmt elseStmt) {
        this.elseStmt = elseStmt;
    }

    public List<ElseIfStmt> getElseIfStmts() {
        return elseIfStmts;
    }
    public void addElseIfStmts(ElseIfStmt elseIfStmt) {
        this.elseIfStmts.add(elseIfStmt);
    }

}
