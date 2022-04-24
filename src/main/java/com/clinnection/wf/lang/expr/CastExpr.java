package com.clinnection.wf.lang.expr;

import com.clinnection.wf.lang.DataType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CastExpr extends UnaryExpr {

    private static final Map<DataType, List<DataType>> dataTypeMap = new HashMap(Map.of(
            DataType.Boolean, List.of(),
            DataType.Integer, List.of(),
            DataType.Decimal, List.of(),
            DataType.Date, List.of(DataType.String),
            DataType.String, List.of()
    ));

    public CastExpr(DataType dataType, Expr rhs) {
        super(dataType, "#", rhs);
        System.out.println("casting: " + rhs.getDataType() + " to: " + dataType);

        if (!dataTypeMap.containsKey(dataType)) {
            throw new RuntimeException(dataType + ": invalid data type");
        }

        if (!dataTypeMap.get(dataType).contains(rhs.getDataType())) {
            throw new RuntimeException("Cannot convert: " + rhs.getDataType() + " to: " + dataType);
        }
    }
}
