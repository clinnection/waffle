package com.clinnection.wf.lang;

import com.clinnection.wf.parser.*;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;

import static org.antlr.v4.runtime.CharStreams.fromFileName;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Hello, world!\n");

        CharStream cs = fromFileName("program.txt");
        WfLexer lexer = new WfLexer(cs);

        CommonTokenStream tokens = new CommonTokenStream(lexer);
        WfParser parser = new WfParser(tokens);

        ParseTree tree = parser.program(); // parse the content and get the tree
        WfParserListener listener = new WfBuilder();

        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(listener, tree);
    }
}
