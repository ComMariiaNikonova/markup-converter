import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

/**
 * Created by user on 19.03.15.
 */
public class Parser implements Analyzer<LinkedList<Token>, Map<TokenType, LinkedList<Token>>> {


    private Analyzer prevHandler;

    public Parser() {
    }

    public Analyzer getPrevHandler() {
        return prevHandler;
    }

    public void setPrevHandler(Analyzer prevHandler) {
        this.prevHandler = prevHandler;
    }

    public Parser(Analyzer nextHandler) {
        this.prevHandler = nextHandler;
    }

    @Override
    public boolean canAnalyze(LinkedList<Token> matter) {
        boolean result = matter == null ? false : !matter.isEmpty();
        return result;
    }

    @Override
    public Map<TokenType, LinkedList<Token>> analyze(LinkedList<Token> matter) {
        Map<TokenType, LinkedList<Token>> tokenFamily = new ConcurrentHashMap<>();

        for (TokenType tokenType : TokenType.values()) {
            tokenFamily.put(tokenType, new LinkedList<Token>());
        }

        matter.parallelStream().forEach((token) -> {
            if (token.getNonTerminaltype().equals(TokenType.STATEMENTS)) {
                resolveFamily(token, tokenFamily);
                System.out.println("Parser.class: TOKEN: " + token.getToken() + " TYPE: " + token.getNonTerminaltype());
            }
        });
        return tokenFamily;
    }

    /*Without handling nested expression and removing left recursion*/
    private void resolveFamily(Token token, Map<TokenType, LinkedList<Token>> tokenFamily) {

        Pattern patternH = Pattern.compile(TokenType.H_FAMILY.getRegex());
        Pattern patternP = Pattern.compile(TokenType.P_FAMILY.getRegex());
        Pattern patternLO = Pattern.compile(TokenType.LINK_OPEN_FAMILY.getRegex());
        Pattern patternLC = Pattern.compile(TokenType.LINK_CLOSE_FAMILY.getRegex());
        Pattern patternES = Pattern.compile(TokenType.EM_STRONG_FAMILY.getRegex());

        if (patternH.matcher(token.getToken()).matches()) {
            token.setFamilyType(TokenType.H_FAMILY);
            tokenFamily.get(TokenType.H_FAMILY).add(token);

        } else if (patternP.matcher(token.getToken()).matches()) {
            token.setFamilyType(TokenType.P_FAMILY);
            tokenFamily.get(TokenType.P_FAMILY).add(token);

        } else if (patternLO.matcher(token.getToken()).matches()) {
            token.setFamilyType(TokenType.LINK_OPEN_FAMILY);
            tokenFamily.get(TokenType.LINK_OPEN_FAMILY).add(token);

        } else if (patternLC.matcher(token.getToken()).matches()) {
            token.setFamilyType(TokenType.LINK_CLOSE_FAMILY);
            tokenFamily.get(TokenType.LINK_CLOSE_FAMILY).add(token);

        } else if (patternES.matcher(token.getToken()).matches()) {
            token.setFamilyType(TokenType.EM_STRONG_FAMILY);
            tokenFamily.get(TokenType.EM_STRONG_FAMILY).add(token);
        }
    }
}