import java.util.LinkedList;
import java.util.regex.Pattern;

/**
 * Created by user on 19.03.15.
 */
public class Lexer implements Analyzer<String, LinkedList<Token>> {

    private Analyzer prevHandler;

    public Lexer() {
    }

    public Analyzer getPrevHandler() {
        return prevHandler;
    }

    public void setPrevHandler(Analyzer prevHandler) {
        this.prevHandler = prevHandler;
    }

    public Lexer(Analyzer nextHandler) {
        this.prevHandler = nextHandler;
    }

    @Override
    public boolean canAnalyze(String matter) {
        boolean result = matter == null ? false : !matter.isEmpty();
        return result;
    }

    @Override
    public LinkedList<Token> analyze(String matter) {
        LinkedList<Token> tokens = new LinkedList<>();
        char[] arr = new char[matter.toCharArray().length];

        Pattern patternText = Pattern.compile(TokenType.TEXT.getRegex());
        Pattern patternStats = Pattern.compile(TokenType.STATEMENTS.getRegex());

        for (int i = 0; i < arr.length; i++) {
            if (patternText.matcher(String.valueOf(arr[i])).matches()) {
                tokens.add(new Token(String.valueOf(arr[i]), TokenType.TEXT, i));
            } else if (patternStats.matcher(String.valueOf(arr[i])).matches()) {
                tokens.add(new Token(String.valueOf(arr[i]), TokenType.STATEMENTS, i));
            }
        }
        tokens.forEach((token) -> System.out.println("Lexer.class: TOKEN: " + token.getToken() + " TYPE: " + token.getNonTerminaltype()));
        return tokens;
    }
}
