package task.markup.core;

import java.util.List;

/**
 * Created by user on 23.03.15.
 */
public class Builder {

    public static String build(List<Token> tokens) {
        tokens.sort((o1, o2) ->
                Integer.compare(o1.getColumn(), o2.getColumn()));

        StringBuilder builder = new StringBuilder();
        tokens.forEach((token) ->
                builder.append(token.getToken()));

        return builder.toString();
    }
}
