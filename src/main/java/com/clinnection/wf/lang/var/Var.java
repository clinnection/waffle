package com.clinnection.wf.lang.var;

import com.clinnection.wf.lang.DataType;
import com.clinnection.wf.lang.expr.Expr;

import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.Map;

public abstract class Var {

    private String name;
    private DataType dataType = DataType.Invalid;
    private Expr expr;

    private static final Map<String, DataType> dataTypeMap = new HashMap(Map.of(
            "boolean", DataType.Boolean,
            "integer", DataType.Integer,
            "decimal", DataType.Decimal,
            "date", DataType.Date,
            "string", DataType.String
    ));

    public static DataType getType(String typeText) {
        if (!dataTypeMap.containsKey(typeText)) {
            throw new RuntimeException(typeText + ": invalid type");
        }
        return dataTypeMap.get(typeText);
    }

    public static Var make(String name, String type) {

        DataType dataType = getType(type);
        Var v = null;

        switch (dataType) {
            case Date:
                v = new DateVar(name);
                break;
            case Boolean:
                v = new BooleanVar(name);
                break;
            case String:
                v = new StringVar(name);
                break;
            case Integer:
                v = new IntegerVar(name);
                break;
            case Decimal:
                v = new DecimalVar(name);
                break;
            default:
                throw new RuntimeException(type + ": invalid type");
        }
        return v;
    }

    public Expr getExpr() {
        return expr;
    }

    public void setExpr(Expr expr) {
        this.expr = expr;
    }

    public Var(String name, DataType dataType) {
        this.name = name;
        this.dataType = dataType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }
}
