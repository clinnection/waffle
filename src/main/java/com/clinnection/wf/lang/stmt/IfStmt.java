package com.clinnection.wf.lang.stmt;

import org.json.JSONArray;
import org.json.JSONObject;

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

    @Override
    public JSONObject toParseTree() {
        JSONObject jsonObject = super.toParseTree();
        JSONArray jsonElseIf = new JSONArray();

        if (elseStmt != null) {
            jsonObject.putOnce("else", elseStmt.toParseTree());
        } else {
            jsonObject.putOnce("else", new JSONObject());
        }

        for (ElseIfStmt elseIfStmt : elseIfStmts) {
            jsonElseIf.put(elseIfStmt.toParseTree());
        }

        jsonObject.putOnce("elseIfStmts", jsonElseIf);

        return jsonObject;
    }
}
