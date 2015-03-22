/**
 * Created by user on 22.03.15.
 */
public class Token {
    public void setNonTerminaltype(Enum<TokenType> nonTerminaltype) {
        this.nonTerminaltype = nonTerminaltype;
    }

    private String token;
    private Enum<TokenType> nonTerminaltype;
    private Enum<TokenType> familyType;
    private Enum<TokenType> terminalType;
    private int column;

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
}
