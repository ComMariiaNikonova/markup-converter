package task.markup.core;


/**
 * Created by user on 22.03.15.
 */
public class Token {

    private String token;
    private Enum<TokenType> nonTerminaltype;
    private Enum<TokenType> familyType;
    private Enum<TokenType> terminalType;
    private int column;
    private boolean passed;


    public Token(String token, Enum<TokenType> type, int column) {
        this.token = token;
        this.nonTerminaltype = type;
        this.column = column;
    }

    public String getToken() {
        return token;
    }

    public Enum<TokenType> getNonTerminaltype() {
        return nonTerminaltype;
    }

    public int getColumn() {
        return column;
    }

    public Enum<TokenType> setFamilyTypegetFamilyType() {
        return familyType;
    }

    public void setFamilyType(Enum<TokenType> familyType) {
        this.familyType = familyType;
    }

    public Enum<TokenType> getTerminalType() {
        return terminalType;
    }

    public void setTerminalType(Enum<TokenType> terminalType) {
        this.terminalType = terminalType;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public Enum<TokenType> getFamilyType() {
        return familyType;
    }

    public void setNonTerminaltype(Enum<TokenType> nonTerminaltype) {
        this.nonTerminaltype = nonTerminaltype;
    }

}
