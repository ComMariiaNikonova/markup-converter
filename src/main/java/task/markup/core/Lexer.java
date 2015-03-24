package task.markup.core;

import java.util.LinkedList;
import java.util.regex.Pattern;

/**
 * Created by user on 19.03.15.
 */
public class Lexer implements Analyzer<String, LinkedList<Token>> {

    public Lexer() {
    }

    public Lexer(Analyzer nextHandler) {
        this.prevHandler = nextHandler;
    }

    private Analyzer prevHandler;


    public Analyzer getPrevHandler() {
        return prevHandler;
    }

    public void setPrevHandler(Analyzer prevHandler) {
        this.prevHandler = prevHandler;
    }


    @Override
    public boolean canAnalyze(String matter) {
        boolean result = matter == null ? false : !matter.isEmpty();
        return result;
    }

    @Override
    public LinkedList<Token> analyze(String matter) {

        System.out.println("\n\nLexer: analyze(): matter: " + "\n" + matter + "\n");

        LinkedList<Token> tokens = new LinkedList<>();
        Pattern patternText = Pattern.compile(TokenType.TEXT.getRegex());
        Pattern patternStats = Pattern.compile(TokenType.STATEMENTS.getRegex());

        for (int i = 0; i < matter.length(); i++) {
            String str = (Character.toString(matter.charAt(i)));
            if (patternText.matcher(str).matches()) {
                tokens.add(new Token(str, TokenType.TEXT, i));
            } else if (patternStats.matcher(str).matches()) {
                tokens.add(new Token(str, TokenType.STATEMENTS, i));
            }
        }
        tokens.forEach((token) -> System.out.println("task.markup.core.Lexer.class: TOKEN: " + token.getToken() + " TYPE: " + token.getNonTerminaltype()));
        return tokens;
    }
}
