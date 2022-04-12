package com.clinnection.wf.lang.stmt;

public abstract class Stmt {

    enum Type {
        Invalid,
        If,
        ElseIf,
        Else,
        While,
        VarAssignment,
        Program
    };

    private Stmt.Type type = Stmt.Type.Invalid;

    public Stmt(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

//    public JSONObject toJsonObject() {
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.putOnce("type", type);
//        return jsonObject;
//    }
}