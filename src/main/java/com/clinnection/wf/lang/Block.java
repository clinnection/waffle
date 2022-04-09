package com.clinnection.wf.lang;

import com.clinnection.wf.lang.var.Var;

import java.util.HashMap;

public class Block {

    private HashMap<String, Var> vars = new HashMap<String, Var>();
// TODO: stmts
//    private List<Stmt> stmts = new ArrayList<Stmt>();
//
//    public void addStmt(Stmt stmt) {
//        stmts.add(stmt);
//    }

    public void addVar(Var var) { vars.put(var.getName(), var); }
    public HashMap<String, Var> getVars() { return this.vars; }
    public Var getVar(String name) { return this.vars.get(name); }

}