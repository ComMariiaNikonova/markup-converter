package task.markup.core;

/**
 * Created by user on 22.03.15.
 */

public enum TokenType {
    /*TypeTree:*/
    /*task.markup.core.Lexer*/
    TEXT("[^(#{1,3}|\\*{1,3}|\\n{1}|\\[{1}|\\]{1})]*"), //done
    STATEMENTS("[\\*]{1,2}|[#]{1,3}|\\[|\\]|\\n"),

    /*task.markup.core.Parser and AST*/
    H_FAMILY("([^#]*[#]{1,3}[^#]*)"),//both side
    P_FAMILY("\\n{1}"), //both side - should be checked pragmatically
    LINK_OPEN_FAMILY("\\[{1}"),
    LINK_CLOSE_FAMILY("\\]{1}"),
    EM_STRONG_FAMILY("([^\\*]*[\\*]{1,2}[^\\*]*)"),//both side

    /*Terminals*/
    /*Open tags*/
    H1(LexemFamily.CLOSED),
    H2(LexemFamily.CLOSED),
    H3(LexemFamily.CLOSED),
    P_(LexemFamily.CLOSED),
    LINK_OPEN(LexemFamily.OPENNED),
    LINK_CLOSE(LexemFamily.OPENNED),
    EM(LexemFamily.CLOSED),
    STRONG(LexemFamily.CLOSED),


    /*Close tags*/
    H1_CLOSE(),
    H2_CLOSE(),
    H3_CLOSE(),
    EM_CLOSE(),
    STRONG_CLOSE(),
    P_CLOSE(),

    UNCLOSED(),
    UNDEFINED();

    TokenType(String regex) {
        this.regex = regex;
    }

    TokenType() {
    }

    TokenType(LexemFamily family, String regex) {
        this.family = family;
        this.regex = regex;
    }

    TokenType(LexemFamily family) {
        this.family = family;
    }

    private String regex;

    public LexemFamily getFamily() {
        return family;
    }

    public String getRegex() {
        return regex;
    }

    private LexemFamily family;

    public enum LexemFamily {
        CLOSED,
        OPENNED;
    }
}