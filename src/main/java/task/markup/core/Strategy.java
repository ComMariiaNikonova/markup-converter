package task.markup.core;

import java.util.LinkedList;

/**
 * Created by user on 20.03.15.
 */

public class Strategy {

    public String getData() {
        return data;
    }

    private String data = "";

    public Strategy(String source) {
        this.data = source;
    }

    public String analyzeByStrategy() {

        Lexer lexer = new Lexer();
        Parser parser = new Parser(lexer);
        SimpleASTConstructor astConstructor = new SimpleASTConstructor(parser);
        Converter converter = new Converter();
        LinkedList<Token> tokens = lexer.handle(data);

        return data = Builder.build(converter.convert(astConstructor.handle(parser.handle(tokens))), tokens);
    }
}
