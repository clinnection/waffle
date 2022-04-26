package com.clinnection.wf.lang.expr;

import com.clinnection.wf.lang.DataType;
import org.json.JSONObject;

import static java.lang.Double.parseDouble;

public class DecimalLiteralExpr extends Expr {
    private double value;

    public DecimalLiteralExpr(String value) {
        super(DataType.Decimal, "Literal");
        this.value = parseDouble(value);
    }

    @Override
    public JSONObject toParseTree() {
        JSONObject jsonObject = super.toParseTree();
        jsonObject.putOnce("value", value );
        return jsonObject;
    }
}
