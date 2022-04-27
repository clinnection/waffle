package com.clinnection.wf.lang;

import com.clinnection.wf.lang.stmt.Stmt;
import com.clinnection.wf.lang.var.Var;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Block {

    private HashMap<String, Var> vars = new HashMap<String, Var>();
    private List<Stmt> stmts = new ArrayList<Stmt>();

    public void addStmt(Stmt stmt) {
        stmts.add(stmt);
    }

    public void addVar(Var var) { vars.put(var.getName(), var); }
    public HashMap<String, Var> getVars() { return this.vars; }
    public Var getVar(String name) { return this.vars.get(name); }

    public Stmt currentStmt() {
        return stmts.get(stmts.size() -1);
    }

    public JSONObject toParseTree() {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonStmts = new JSONArray();

        // TODO: vars

        for (Stmt stmt : stmts) {
            jsonStmts.put(stmt.toParseTree());
        }

        jsonObject.putOnce("type", "block");
        jsonObject.putOnce("stmts", jsonStmts);
        return jsonObject;
    }

}