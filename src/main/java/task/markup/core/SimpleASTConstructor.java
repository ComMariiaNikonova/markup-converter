package task.markup.core;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * Created by user on 19.03.15.
 */
 /*Without handling nested expression and removing recursion*/
public class SimpleASTConstructor implements Analyzer<Map<TokenType, LinkedList<Token>>, CopyOnWriteArrayList<Lexema>> {

    public SimpleASTConstructor() {
    }

    public SimpleASTConstructor(Analyzer nextHandler) {
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
    public boolean canAnalyze(Map<TokenType, LinkedList<Token>> matter) {
        boolean result = matter == null ? false : !matter.isEmpty();
        return result;
    }

    @Override
    public CopyOnWriteArrayList<Lexema> analyze(Map<TokenType, LinkedList<Token>> tokenByFamily) {
        System.out.println("task.markup.core.SimpleASTConstructor.class");
        CopyOnWriteArrayList<Lexema> list = new CopyOnWriteArrayList<>();
        tokenByFamily.entrySet().parallelStream().forEach((entry) ->
        {
            System.out.println("task.markup.core.SimpleASTConstructor.class: ENTRY: " + entry.getKey() + " : " + entry.getValue().size());
            if (!entry.getValue().isEmpty()) {
                analizeLexemFamily(entry, list);
            } else {
                tokenByFamily.remove(entry.getKey());
            }
        });
        list.sort((Lexema o1, Lexema o2) -> {
            System.out.println("task.markup.core.SimpleASTConstructor.class: LEXEMA INDEXES: " + o1.getOpenPositionIndex() + " : " + o2.getOpenPositionIndex());
            return Integer.compare(o1.getOpenPositionIndex(), o2.getOpenPositionIndex());
        });
        return list;
    }


    private void analizeLexemFamily(Map.Entry<TokenType, LinkedList<Token>> entry, List<Lexema> list) {
        LinkedList<Token> value = entry.getValue();

        value.sort((o1, o2) -> Integer.compare(o1.getColumn(), o2.getColumn()));

        switch (entry.getKey().getFamily()) {
            case CLOSED: {
                if ((value.size() % 2) == 0) { /*even sequence*/
                    int threeCharSec = 0, twoCharSec = 0, oneCharSec = 0;

                    for (int j = 0; j < value.size(); j++) {
                        if (j <= value.size() - 3
                                && !value.get(j).isPassed()
                                && !value.get(j + 1).isPassed()
                                && !value.get(j + 2).isPassed()
                                && value.get(j).getColumn() == value.get(j + 1).getColumn() + 1
                                && value.get(j + 1).getColumn() + 1 == value.get(j + 2).getColumn() + 2) {

                            value.get(j).setPassed(true);
                            value.get(j + 1).setPassed(true);
                            value.get(j + 2).setPassed(true);

                            /*open tag*/
                            if (threeCharSec % 2 == 0) {
                                list.add(new Lexema(setOpenTokenTypeToTerminal(entry.getKey(), 3), value.get(j).getColumn(), 3, true));
                            }
                            /*close tag*/
                            else {
                                list.add(new Lexema(setCloseTokenTypeToTerminal(entry.getKey(), 3), value.get(j).getColumn(), 3, false));
                            }
                            threeCharSec++;
                        } else if (j <= value.size() - 2
                                && !value.get(j).isPassed()
                                && !value.get(j + 1).isPassed()
                                && value.get(j).getColumn() == value.get(j + 1).getColumn() + 1) {

                            value.get(j).setPassed(true);
                            value.get(j + 1).setPassed(true);

                            /*open tag*/
                            if (twoCharSec % 2 == 0) {
                                list.add(new Lexema(setOpenTokenTypeToTerminal(entry.getKey(), 2), value.get(j).getColumn(), 2, true));
                            }
                            /*close tag*/
                            else {
                                list.add(new Lexema(setCloseTokenTypeToTerminal(entry.getKey(), 2), value.get(j).getColumn(), 2, false));
                            }
                            twoCharSec++;
                        } else if (j <= value.size() - 1
                                && !value.get(j).isPassed()) {

                            value.get(j).setPassed(true);

                            /*open tag*/
                            if (oneCharSec % 2 == 0) {
                                list.add(new Lexema(setOpenTokenTypeToTerminal(entry.getKey(), 1), value.get(j).getColumn(), 1, true));
                            }
                            /*close tag*/
                            else {
                                list.add(new Lexema(setCloseTokenTypeToTerminal(entry.getKey(), 1), value.get(j).getColumn(), 1, false));
                            }
                            oneCharSec++;
                        }
                    }
                } /*odd sequence*/ else {
                    //todo
                    System.out.println("task.markup.core.SimpleASTConstructor.class: ENTRY: " + entry.getKey() + " : " + entry.getValue().size());
                }
                break;
            }
            case OPENNED: {
                for (int j = 0; j < value.size(); j++) {
                    if (j <= value.size() - 3
                            && !value.get(j).isPassed()
                            && !value.get(j + 1).isPassed()
                            && !value.get(j + 2).isPassed()
                            && value.get(j).getColumn() == value.get(j + 1).getColumn() + 1
                            && value.get(j + 1).getColumn() + 1 == value.get(j + 2).getColumn() + 2) {
                        value.get(j).setPassed(true);
                        value.get(j + 1).setPassed(true);
                        value.get(j + 2).setPassed(true);
                        list.add(new Lexema(setOpenTokenTypeToTerminal(entry.getKey(), 3), value.get(j).getColumn(), 3, false));
                    } else if (j <= value.size() - 2
                            && !value.get(j).isPassed()
                            && !value.get(j + 1).isPassed()
                            && value.get(j).getColumn() == value.get(j + 1).getColumn() + 1) {
                        value.get(j).setPassed(true);
                        value.get(j + 1).setPassed(true);
                        list.add(new Lexema(setOpenTokenTypeToTerminal(entry.getKey(), 2), value.get(j).getColumn(), 2, false));
                    } else if (j == value.size() - 1
                            && !value.get(j).isPassed()) {
                        value.get(j).setPassed(true);
                        list.add(new Lexema(setOpenTokenTypeToTerminal(entry.getKey(), 1), value.get(j).getColumn(), 1, false));
                    }
                }
            }
        }
    }


    private TokenType setOpenTokenTypeToTerminal(TokenType familyToken, int amount) {
        TokenType type = TokenType.UNDEFINED;
        if (familyToken.equals(TokenType.TREE_H) && amount == 1) {
            type = TokenType.H1;
        } else if (familyToken.equals(TokenType.TREE_H) && amount == 2) {
            type = TokenType.H2;
        } else if (familyToken.equals(TokenType.TREE_H) && amount == 3) {
            type = TokenType.H3;
        } else if (familyToken.equals(TokenType.TREE_P) && amount == 1) {
            type = TokenType.P_;
        } else if (familyToken.equals(TokenType.TREE_EM_STRONG) && amount == 1) {
            type = TokenType.EM;
        } else if (familyToken.equals(TokenType.TREE_EM_STRONG) && amount == 1) {
            type = TokenType.STRONG;
        } else if (familyToken.equals(TokenType.TREE_LINK_CLOSE) && amount == 1) {
            type = TokenType.LINK_CLOSE;
        } else if (familyToken.equals(TokenType.TREE_LINK_OPEN) && amount == 1) {
            type = TokenType.LINK_OPEN;
        }
        return type;
    }


    private TokenType setCloseTokenTypeToTerminal(TokenType familyToken, int amount) {
        TokenType type = TokenType.UNDEFINED;
        if (familyToken.equals(TokenType.TREE_H) && amount == 1) {
            type = TokenType.H1_CLOSE;
        } else if (familyToken.equals(TokenType.TREE_H) && amount == 2) {
            type = TokenType.H2_CLOSE;
        } else if (familyToken.equals(TokenType.TREE_H) && amount == 3) {
            type = TokenType.H3_CLOSE;
        } else if (familyToken.equals(TokenType.TREE_P) && amount == 1) {
            type = TokenType.P_CLOSE;
        } else if (familyToken.equals(TokenType.TREE_EM_STRONG) && amount == 1) {
            type = TokenType.EM_CLOSE;
        } else if (familyToken.equals(TokenType.TREE_EM_STRONG) && amount == 1) {
            type = TokenType.STRONG_CLOSE;
        } else if (familyToken.equals(TokenType.TREE_LINK_CLOSE) && amount == 1) {
            type = TokenType.UNCLOSED;
        } else if (familyToken.equals(TokenType.TREE_LINK_OPEN) && amount == 1) {
            type = TokenType.UNCLOSED;
        }
        return type;
    }
}