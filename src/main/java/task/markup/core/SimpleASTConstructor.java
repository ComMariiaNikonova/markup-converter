package task.markup.core;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * Created by user on 19.03.15.
 */
public class SimpleASTConstructor implements Analyzer<Map<TokenType, LinkedList<Token>>, CopyOnWriteArrayList<Lexema>> {

    private Analyzer prevHandler;

    public SimpleASTConstructor() {
    }

    public Analyzer getPrevHandler() {
        return prevHandler;
    }

    public void setPrevHandler(Analyzer prevHandler) {
        this.prevHandler = prevHandler;
    }

    public SimpleASTConstructor(Analyzer nextHandler) {
        this.prevHandler = nextHandler;
    }

    @Override
    public boolean canAnalyze(Map<TokenType, LinkedList<Token>> matter) {
        boolean result = matter == null ? false : !matter.isEmpty();
        return result;
    }

    @Override
    public CopyOnWriteArrayList<Lexema> analyze(Map<TokenType, LinkedList<Token>> tokenByFamily) {
        CopyOnWriteArrayList<Lexema> list = new CopyOnWriteArrayList<>();
        tokenByFamily.entrySet().parallelStream().forEach((entry) ->
                analizeLexemFamily(entry, list));
        list.sort((Lexema o1, Lexema o2) ->
                Integer.compare(o1.getOpenPositionIndex(), o2.getOpenPositionIndex()));
        return list;
    }


    private void analizeLexemFamily(Map.Entry<TokenType, LinkedList<Token>> entry, List<Lexema> list) {

        LinkedList<Token> value = entry.getValue();
        switch (entry.getKey().getFamily()) {
            case CLOSED: {
                if ((value.size() % 2) == 0) { /*odd sequence amount*/
                    for (int i = 0; i < value.size(); i++) {
                /*tree char sequence*/
                        if (!value.get(i).isPassed() && !value.get(i + 1).isPassed() && !value.get(i + 2).isPassed()) {
                            if ((value.get(i).getColumn() == (value.get(i + 1).getColumn()) + 1)
                                    && (value.get(i + 1).getColumn()) + 1 == (value.get(i + 2).getColumn()) + 2) {

                                for (int j = i; j < value.size(); j++) {
                                    if (!value.get(j).isPassed() && !value.get(j + 1).isPassed() && !value.get(j + 2).isPassed()) {
                                        if (value.get(j).getColumn() == (value.get(j + 1).getColumn()) + 1 && (value.get(j + 1).getColumn()) + 1 == (value.get(j + 2).getColumn()) + 2) {

                                            value.get(i).setPassed(true);
                                            value.get(i + 1).setPassed(true);
                                            value.get(i + 2).setPassed(true);

                                            value.get(j).setPassed(true);
                                            value.get(j + 1).setPassed(true);
                                            value.get(j + 2).setPassed(true);

                                            //todo Match to terminal type
                                            list.add(new Lexema(setTokenTypeToTerminal(entry.getKey(), 3),
                                                    i, j, 3, true, setTypeToCloseTag(entry.getKey(), 3)));
                                        }
                                    }
                                }
                            }
                        }/*two char sequence*/ else if (!value.get(i).isPassed() && !value.get(i + 1).isPassed()) {
                            if ((value.get(i).getColumn() == (value.get(i + 1).getColumn()) + 1)
                                    && (value.get(i + 1).getColumn()) + 1 != (value.get(i + 2).getColumn()) + 2) {

                                for (int j = i; j < value.size(); j++) {
                                    if (!value.get(j).isPassed() && !value.get(j + 1).isPassed()) {
                                        if (value.get(j).getColumn() == (value.get(j + 1).getColumn()) + 1
                                                && (value.get(j + 1).getColumn()) + 1 != (value.get(j + 2).getColumn()) + 2) {

                                            value.get(i).setPassed(true);
                                            value.get(i + 1).setPassed(true);

                                            value.get(j).setPassed(true);
                                            value.get(j + 1).setPassed(true);

                                            //todo Match to terminal type
                                            list.add(new Lexema(setTokenTypeToTerminal(entry.getKey(), 2),
                                                    i, j, 2, true, setTypeToCloseTag(entry.getKey(), 2)));
                                        }
                                    }
                                }
                            }
                        } /*one char sequence*/ else if (!value.get(i).isPassed()) {
                            if ((value.get(i).getColumn() != (value.get(i + 1).getColumn()) + 1)) {
                                for (int j = i; j < value.size(); j++) {
                                    if (!value.get(j).isPassed()) {
                                        if (value.get(j).getColumn() != (value.get(j + 1).getColumn()) + 1) {

                                            value.get(i).setPassed(true);
                                            value.get(i + 1).setPassed(true);

                                            value.get(j).setPassed(true);
                                            value.get(j + 1).setPassed(true);

                                            //todo Match to terminal type
                                            list.add(new Lexema(setTokenTypeToTerminal(entry.getKey(), 1),
                                                    i, j, 1, true, setTypeToCloseTag(entry.getKey(), 1)));
                                        }
                                    }
                                }
                            }
                        }
                    }
                } /*odd sequence amount*/ else {
                    throw new RuntimeException("Statements not closed");
                }
                break;
            }
            case OPENNED: {
                for (int j = 0; j < value.size(); j++) {
                    if (!value.get(j).isPassed()) {
                        list.add(new Lexema(setTokenTypeToTerminal(entry.getKey(), 1),
                                j, -1, 1, false, TokenType.UNCLOSED));//todo
                    }
                }
            }
        }
    }


    private TokenType setTokenTypeToTerminal(TokenType familyToken, int amount) {
        TokenType type = TokenType.UNDEFINED;
        if (familyToken.equals(TokenType.H_FAMILY) && amount == 1) {
            type = TokenType.H1;
        } else if (familyToken.equals(TokenType.H_FAMILY) && amount == 2) {
            type = TokenType.H2;
        } else if (familyToken.equals(TokenType.H_FAMILY) && amount == 3) {
            type = TokenType.H3;
        } else if (familyToken.equals(TokenType.P_FAMILY) && amount == 1) {
            type = TokenType.P_;
        } else if (familyToken.equals(TokenType.EM_STRONG_FAMILY) && amount == 1) {
            type = TokenType.EM;
        } else if (familyToken.equals(TokenType.EM_STRONG_FAMILY) && amount == 1) {
            type = TokenType.STRONG;
        } else if (familyToken.equals(TokenType.LINK_CLOSE_FAMILY) && amount == 1) {
            type = TokenType.LINK_CLOSE;
        } else if (familyToken.equals(TokenType.LINK_OPEN_FAMILY) && amount == 1) {
            type = TokenType.LINK_OPEN;
        }
        return type;
    }


    private TokenType setTypeToCloseTag(TokenType familyToken, int amount) {
        TokenType type = TokenType.UNDEFINED;
        if (familyToken.equals(TokenType.H_FAMILY) && amount == 1) {
            type = TokenType.H1_CLOSE;
        } else if (familyToken.equals(TokenType.H_FAMILY) && amount == 2) {
            type = TokenType.H2_CLOSE;
        } else if (familyToken.equals(TokenType.H_FAMILY) && amount == 3) {
            type = TokenType.H3_CLOSE;
        } else if (familyToken.equals(TokenType.P_FAMILY) && amount == 1) {
            type = TokenType.P_CLOSE;
        } else if (familyToken.equals(TokenType.EM_STRONG_FAMILY) && amount == 1) {
            type = TokenType.EM_CLOSE;
        } else if (familyToken.equals(TokenType.EM_STRONG_FAMILY) && amount == 1) {
            type = TokenType.STRONG_CLOSE;
        } else if (familyToken.equals(TokenType.LINK_CLOSE_FAMILY) && amount == 1) {
            type = TokenType.UNCLOSED;
        } else if (familyToken.equals(TokenType.LINK_OPEN_FAMILY) && amount == 1) {
            type = TokenType.UNCLOSED;
        }
        return type;
    }
}