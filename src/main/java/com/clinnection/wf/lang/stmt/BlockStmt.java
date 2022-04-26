package com.clinnection.wf.lang.stmt;

import com.clinnection.wf.lang.Block;
import org.json.JSONObject;

public abstract class BlockStmt extends Stmt {

    private Block block;
    public BlockStmt(Type type) {
        super(type);
    }
    public void setBlock(Block block) {
        this.block = block;
    }

    public JSONObject toParseTree() {
        JSONObject jsonObject = super.toParseTree();
        jsonObject.putOnce("block", block.toParseTree());
        return jsonObject;
    }

}