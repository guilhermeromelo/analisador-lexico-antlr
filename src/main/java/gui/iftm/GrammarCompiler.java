package gui.iftm;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class GrammarCompiler {

    private String fileContent;

    public GrammarCompiler(String filePath) throws IOException {
        this.fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
    }

    public void compile() {
        CharStream charStream = CharStreams.fromString(this.fileContent);
        PascalGrammarLexer pascalGrammarLexer = new PascalGrammarLexer(charStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(pascalGrammarLexer);
        commonTokenStream.fill();
        List<Token> tokenList = commonTokenStream.getTokens();
        for (Token token : tokenList) {
            int tokenType = token.getType();
            String tokenName = pascalGrammarLexer.getVocabulary().getSymbolicName(tokenType);
            String tokenText = token.getText();
            int line = token.getLine();
            int column = token.getCharPositionInLine();
            System.out.println("Token { Name: " + tokenName + " | Text: " + tokenText + " | Line: " + line + " | Column: " + column + " }");
        }
        PascalGrammarParser pascalGrammarParser = new PascalGrammarParser(commonTokenStream);
        ParseTree tree = pascalGrammarParser.program();
    }
}