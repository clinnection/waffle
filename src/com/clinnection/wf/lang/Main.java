package com.clinnection.wf.lang;

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
        wfLexer lexer = new wfLexer(cs);

        CommonTokenStream tokens = new CommonTokenStream(lexer);
        wfParser parser = new wfParser(tokens);

        ParseTree tree = parser.program(); // parse the content and get the tree
        wfListener listener = new WfBuilder();

        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(listener,tree);
    }
}
