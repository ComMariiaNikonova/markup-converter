package task.markup.core;

/**
 * Created by user on 22.03.15.
 */

public enum TokenType {
    /*TypeTree:*/
    /*task.markup.core.Lexer*/
    TEXT("[^(#{1,3}|\\*{1,3}|\\n{1}|\\[{1}|\\]{1}|\\n{1})]*", HierarchyStruct.NON_TERM), //done
    STATEMENTS("[\\*]{1,2}|[#]{1,3}|\\[|\\]|\\n{1}", HierarchyStruct.NON_TERM),

    /*task.markup.core.Parser and AST*/
    TREE_H("([^#]*[#]{1,3}[^#]*)", LexemFamily.OPENNED, HierarchyStruct.TREE_NON_TERM),//both side
    TREE_P("\\n{1}", LexemFamily.CLOSED, HierarchyStruct.TREE_NON_TERM), //both side - should be checked pragmatically
    TREE_LINK_OPEN("\\[{1}", LexemFamily.OPENNED, HierarchyStruct.TREE_NON_TERM),
    TREE_LINK_CLOSE("\\]{1}", LexemFamily.OPENNED, HierarchyStruct.TREE_NON_TERM),
    TREE_EM_STRONG("([^\\*]*[\\*]{1,2}[^\\*]*)", LexemFamily.CLOSED, HierarchyStruct.TREE_NON_TERM),//both side

    /*Terminals*/
    /*Open tags*/
    H1(LexemFamily.CLOSED, HierarchyStruct.TERM),
    H2(LexemFamily.CLOSED, HierarchyStruct.TERM),
    H3(LexemFamily.CLOSED, HierarchyStruct.TERM),
    P_(LexemFamily.CLOSED, HierarchyStruct.TERM),
    LINK_OPEN(LexemFamily.OPENNED, HierarchyStruct.TERM),
    LINK_CLOSE(LexemFamily.OPENNED, HierarchyStruct.TERM),
    EM(LexemFamily.CLOSED, HierarchyStruct.TERM),
    STRONG(LexemFamily.CLOSED, HierarchyStruct.TERM),


    /*Close tags*/
    H1_CLOSE(HierarchyStruct.TERM),
    H2_CLOSE(HierarchyStruct.TERM),
    H3_CLOSE(HierarchyStruct.TERM),
    EM_CLOSE(HierarchyStruct.TERM),
    STRONG_CLOSE(HierarchyStruct.TERM),
    P_CLOSE(HierarchyStruct.TERM),

    UNCLOSED(HierarchyStruct.COMMON),
    UNDEFINED(HierarchyStruct.COMMON);

    private LexemFamily family;
    private String regex;
    private HierarchyStruct hierarchyStruct;

    public enum LexemFamily {
        CLOSED,
        OPENNED;
    }

    public enum HierarchyStruct {
        NON_TERM,
        TREE_NON_TERM,
        TERM,
        COMMON;
    }

    TokenType() {
    }

    TokenType(String regex) {
        this.regex = regex;
    }

    TokenType(HierarchyStruct hierarchyStruct) {
        this.hierarchyStruct = hierarchyStruct;
    }

    TokenType(String regex, HierarchyStruct hierarchyStruct) {
        this.regex = regex;
        this.hierarchyStruct = hierarchyStruct;
    }

    TokenType(LexemFamily family, HierarchyStruct hierarchyStruct) {
        this.family = family;
        this.hierarchyStruct = hierarchyStruct;
    }

    TokenType(LexemFamily family, String regex) {
        this.family = family;
        this.regex = regex;
    }

    TokenType(LexemFamily family) {
        this.family = family;
    }

    TokenType(String regex, LexemFamily family, HierarchyStruct hierarchyStruct) {
        this.regex = regex;
        this.family = family;
        this.hierarchyStruct = hierarchyStruct;
    }

    public LexemFamily getFamily() {
        return family;
    }

    public String getRegex() {
        return regex;
    }

    public HierarchyStruct getHierarchyStruct() {
        return hierarchyStruct;
    }
}