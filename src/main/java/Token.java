/**
 * Created by user on 22.03.15.
 */
public class Token {

    private String token;
    private Enum<TokenType> type;
    private int column;

    public Token(String token, Enum<TokenType> type, int column) {
        this.token = token;
        this.type = type;
        this.column = column;
    }

    public String getToken() {
        return token;
    }

    public Enum<TokenType> getType() {
        return type;
    }

    public int getColumn() {
        return column;
    }
}
