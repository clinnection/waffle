package com.clinnection.wf.lang.expr;

import com.clinnection.wf.lang.DataType;
import org.json.JSONObject;

import static java.lang.Integer.parseInt;

public class IntegerLiteralExpr extends Expr {
    private int value;

    public IntegerLiteralExpr(String value) {
        super(DataType.Integer, "Literal");
        this.value = parseInt(value);
    }

    @Override
    public JSONObject toParseTree() {
        JSONObject jsonObject = super.toParseTree();
        jsonObject.putOnce("value", value );
        return jsonObject;
    }
}
