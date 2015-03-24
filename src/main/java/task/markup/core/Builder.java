package task.markup.core;

import java.util.List;

/**
 * Created by user on 23.03.15.
 */
public class Builder {

    public static String build(List<Token> tokensPrepared, List<Token> mixedData) {
        mixedData.forEach((token) -> {
            if (token.getNonTerminaltype().equals(TokenType.TEXT)) {
                tokensPrepared.add(token);
            }
        });

        tokensPrepared.sort((o1, o2) -> {
            System.out.println("BUILDER: TOKEN INDEXES: " + o1.getColumn() + " : " + o2.getColumn());
            return Integer.compare(o1.getColumn(), o2.getColumn());
        });
        //todo: add "html" "body" "/html" "/body" to common grammar (to avoid hardcode);
        StringBuilder builder = new StringBuilder();
        builder.append("<html>\n" +
                "<body>");
        tokensPrepared.forEach((token) ->
                builder.append(token.getToken()));
        builder.append("</body>\n" +
                "</html>");
        return builder.toString();
    }
}
