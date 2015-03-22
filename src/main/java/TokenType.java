/**
 * Created by user on 22.03.15.
 */
public enum TokenType {
    /*TypeTree:*/
    /*Lexer*/
    TEXT("[^(#{1-3}|\\*{1-3}|\\n{1}|\\[{1}|\\]{1})]*"), //done
    STATEMENTS("*{1-2}|#{1-3}|\\[|\\]|\\n"),

    /*Parser and AST*/
    H_FAMILY("([^#]*[#]{1-3}[^#]*)"),//both side
    P_FAMILY("\\n{1}"), //both side - should be checked pragmatically
    LINK_OPEN_FAMILY("\\[{1}"),
    LINK_CLOSE_FAMILY("\\]{1}"),
    EM_STRONG_FAMILY("([^\\*]*[\\*]{1-2}[^\\*]*)"),//both side

    /*Common checker*/
    H1("([^#]*[#]{1}[^#]*){2}"),//both side -четное число входждений
    H2("([^#]*[#]{2}[^#]*){2}"),//both side
    H3("([^#]*[#]{3}[^#]*){2}"),//both side
    P_("\\n{1}"), //both side - should be checked pragmatically
    LINK_OPEN("\\[{1}"),
    LINK_CLOSE("\\]{1}"),
    EM("([^\\*]*[\\*]{1}[^\\*]*){2}"), //both side
    STRONG("([^\\*]*[\\*]{2}[^\\*]*){2}");//both side

    private String regex;

    TokenType(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }
}


/*TypeTree:*/
    /*Lexer*/
/*    TEXT("[^(#{1-3}|\\*{1-3}|\\n{1}|\\[{1}|\\]{1})]*"), //done
    STATEMENTS("*{1-2}|#{1-3}|\\[|\\]|\\n"),

    *//*Parser and AST*//*
    H_FAMILY("([^#]*[#]{1-3}[^#]*)"),//both side
    P_FAMILY("\\n{1}"), //both side - should be checked pragmatically
    LINK_OPEN_FAMILY("\\[{1}"),
    LINK_CLOSE_FAMILY("\\]{1}"),
    EM_STRONG_FAMILY("([^\\*]*[\\*]{1-2}[^\\*]*)"),//both side

    H1("([^#]*[#]{1}[^#]*){2}"),//both side -четное число входждений
    H2("([^#]*[#]{2}[^#]*){2}"),//both side
    H3("([^#]*[#]{3}[^#]*){2}"),//both side
    P_("\\n{1}"), //both side - should be checked pragmatically
    LINK_OPEN("\\[{1}"),
    LINK_CLOSE("\\]{1}"),
    EM("([^\\*]*[\\*]{1}[^\\*]*){2}"), //both side
    STRONG("([^\\*]*[\\*]{2}[^\\*]*){2}");//both side*/

// STATEMENTS("([^(#{1-3}|\\*{1-3}|\\n{1}|\\[{1}|\\]{1})]*[(#{1-3}|\\*{1-3}|\\n{1}|\\[{1}|\\]{1})][^(#{1-3}|\\*{1-3}|\\n{1}|\\[{1}|\\]{1})]*)"),


