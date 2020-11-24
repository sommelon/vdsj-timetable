package sk.vdsj.timetable.main;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import sk.vdsj.timetable.antlr4.grammar.GrammarLexer;
import sk.vdsj.timetable.antlr4.grammar.GrammarParser;
import sk.vdsj.timetable.antlr4.grammar.GrammarParserVisitor;
import sk.vdsj.timetable.model.Timetable;
import sk.vdsj.timetable.semantics.TimetablePrinter;
import sk.vdsj.timetable.semantics.TimetableVelocityWebGenerator;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class MainParserVisitor {
    public static void main(String[] args) throws IOException {
        GrammarLexer lexer = new GrammarLexer(CharStreams.fromFileName("src/resources/examples/external_grammar_example_1.txt"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        GrammarParser parser = new GrammarParser(tokens);
        ParseTree tree = parser.timetable();

        GrammarParserVisitor visitor = new GrammarParserVisitor();
        Timetable timetable = ((Timetable) visitor.visit(tree));
        timetable.validate();

        TimetablePrinter printer = new TimetablePrinter();
        printer.print(timetable);

        try (Writer writer = new FileWriter("timetable.html")) {
            TimetableVelocityWebGenerator htmlPrinter = new TimetableVelocityWebGenerator();
            htmlPrinter.generate(timetable, writer);
        }
    }
}
