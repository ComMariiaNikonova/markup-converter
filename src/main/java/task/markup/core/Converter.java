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

            if (lexema.isNeedCloseTag()) {
                list.add(new Token(replaceByGrammar(lexema.getCloseTagType()), lexema.getCloseTagType(), lexema.getClosePositionIndex()));
            }

            for (int j = 1; j < (lexema.getSequenceLength()); j++) {
                list.add(new Token("", TokenType.UNDEFINED, lexema.getOpenPositionIndex() + j));
                if (lexema.isNeedCloseTag()) {
                    list.add(new Token("", TokenType.UNDEFINED, lexema.getClosePositionIndex() + j));
                }
            }
        });
        return list;
    }

    private String replaceByGrammar(TokenType type) {
        return Grammar.getGrammarTable().getProperty(type.name());
    }
}
