package sk.vdsj.timetable.main;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import sk.vdsj.timetable.antlr4.grammar.GrammarLexer;
import sk.vdsj.timetable.antlr4.grammar.GrammarParser;
import sk.vdsj.timetable.antlr4.grammar.GrammarParserListener;
import sk.vdsj.timetable.model.Timetable;
import sk.vdsj.timetable.semantics.TimetablePrinter;

import java.io.IOException;

public class MainParserListener {
    public static void main(String[] args) throws IOException {
        GrammarLexer lexer = new GrammarLexer(CharStreams.fromFileName("src/resources/examples/external_grammar_example_1.txt"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        GrammarParser parser = new GrammarParser(tokens);
        ParseTree tree = parser.timetable();
        ParseTreeWalker treeWalker = new ParseTreeWalker();
        GrammarParserListener listener = new GrammarParserListener();
        treeWalker.walk(listener, tree);

        Timetable timetable = listener.getTimetable();
        timetable.validate();

        TimetablePrinter printer = new TimetablePrinter();
        printer.print(timetable);
    }


}
