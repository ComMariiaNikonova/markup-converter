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

        return ""; /*data=astConstructor.handle(parser.handle(lexer.handle(data)));*/
    }
}
