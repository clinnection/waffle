package com.clinnection.wf.lang.expr;

import com.clinnection.wf.lang.DataType;
import org.json.JSONObject;

public class StringLiteralExpr extends Expr {
    private String value;

    public StringLiteralExpr(String value) {
        super(DataType.String, "Literal");
        this.value = value;
    }

    @Override
    public JSONObject toParseTree() {
        JSONObject jsonObject = super.toParseTree();
        jsonObject.putOnce("value", value );
        return jsonObject;
    }
}
