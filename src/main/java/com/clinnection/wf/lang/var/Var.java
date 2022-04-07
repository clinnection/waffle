package com.clinnection.wf.lang.var;

import com.clinnection.wf.lang.DataType;

public abstract class Var {
    DataType dataType = DataType.Invalid;

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public Var(DataType dataType) {
        this.dataType = dataType;
    }
}
