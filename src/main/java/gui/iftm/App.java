package gui.iftm;

import gui.iftm.helper.Helper;

import java.io.IOException;

public class App 
{
    public static void main( String[] args ) throws IOException {
        String filePath = "src/main/java/gui/iftm/teste.pas";
        Helper.parseTabIntoSpace(filePath);
        GrammarCompiler grammarCompiler = new GrammarCompiler(filePath);
        grammarCompiler.compile();
    }
}
