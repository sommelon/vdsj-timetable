package sk.vdsj.timetable.main;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import sk.vdsj.timetable.antlr4.grammar.*;

import java.io.IOException;

public class MainParserVisitor {
    public static void main(String[] args) throws IOException {
        GrammarLexer lexer = new GrammarLexer(CharStreams.fromFileName("src/resources/examples/external_grammar_example_1.txt"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        GrammarParser parser = new GrammarParser(tokens);
        ParseTree tree = parser.timetable();

        GrammarParserVisitor visitor = new GrammarParserVisitor();
        visitor.visit(tree);

        System.out.println(visitor.getTimetable());


    }
}
