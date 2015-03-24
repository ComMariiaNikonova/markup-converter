package task.markup.core;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by user on 19.03.15.
 */
public class Converter {


    public List<Token> convert(List<Lexema> lexemaList) {

        List<Token> list = new LinkedList<>();

        lexemaList.forEach((lexema) -> {

            list.add(new Token(replaceByGrammar(lexema.getType()), lexema.getType(), lexema.getOpenPositionIndex()));

            for (int j = 2; j < (lexema.getSequenceLength()); j++) {
                list.add(new Token("", TokenType.UNDEFINED, lexema.getOpenPositionIndex() + j));
            }
        });
        list.sort((o1, o2) -> Integer.compare(o1.getColumn(), o2.getColumn()));
        return list;
    }

    private String replaceByGrammar(TokenType type) {
        return InitGrammar.getGrammarTable().getProperty(type.name());
    }
}
