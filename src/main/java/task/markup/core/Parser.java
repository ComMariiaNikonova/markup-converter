package task.markup.core;

import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

/**
 * Created by user on 19.03.15.
 */
public class Parser implements Analyzer<LinkedList<Token>, Map<TokenType, LinkedList<Token>>> {

    public Parser() {
    }

    public Parser(Analyzer nextHandler) {
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
    public boolean canAnalyze(LinkedList<Token> matter) {
        boolean result = matter == null ? false : !matter.isEmpty();
        return result;
    }

    @Override
    public Map<TokenType, LinkedList<Token>> analyze(LinkedList<Token> matter) {
        Map<TokenType, LinkedList<Token>> tokenByFamily = new ConcurrentHashMap<>();

        for (TokenType tokenType : TokenType.values()) {
            if (tokenType.getHierarchyStruct().equals(TokenType.HierarchyStruct.TREE_NON_TERM)) {
                tokenByFamily.put(tokenType, new LinkedList<>());
                System.out.println("task.markup.core.Parser.class: TOKEN TYPES: " + tokenType);
            }
        }

        matter.parallelStream().forEach((token) -> {
            if (token.getNonTerminaltype().equals(TokenType.STATEMENTS)) {
                resolveFamily(token, tokenByFamily);
                System.out.println("task.markup.core.Parser.class: TOKEN: " + token.getToken() + " TYPE: " + token.getFamilyType());
            }
        });

        return tokenByFamily;
    }


    /*Without handling nested expression and removing recursion*/
    private void resolveFamily(Token token, Map<TokenType, LinkedList<Token>> tokenByFamily) {

        Pattern patternH = Pattern.compile(TokenType.TREE_H.getRegex());
        Pattern patternP = Pattern.compile(TokenType.TREE_P.getRegex());
        Pattern patternLO = Pattern.compile(TokenType.TREE_LINK_OPEN.getRegex());
        Pattern patternLC = Pattern.compile(TokenType.TREE_LINK_CLOSE.getRegex());
        Pattern patternES = Pattern.compile(TokenType.TREE_EM_STRONG.getRegex());

        if (patternH.matcher(token.getToken()).matches()) {
            token.setFamilyType(TokenType.TREE_H);
            tokenByFamily.get(TokenType.TREE_H).add(token);

        } else if (patternP.matcher(token.getToken()).matches()) {
            token.setFamilyType(TokenType.TREE_P);
            tokenByFamily.get(TokenType.TREE_P).add(token);

        } else if (patternLO.matcher(token.getToken()).matches()) {
            token.setFamilyType(TokenType.TREE_LINK_OPEN);
            tokenByFamily.get(TokenType.TREE_LINK_OPEN).add(token);

        } else if (patternLC.matcher(token.getToken()).matches()) {
            token.setFamilyType(TokenType.TREE_LINK_CLOSE);
            tokenByFamily.get(TokenType.TREE_LINK_CLOSE).add(token);

        } else if (patternES.matcher(token.getToken()).matches()) {
            token.setFamilyType(TokenType.TREE_EM_STRONG);
            tokenByFamily.get(TokenType.TREE_EM_STRONG).add(token);
        }
    }
}