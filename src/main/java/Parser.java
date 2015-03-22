import java.util.LinkedList;

/**
 * Created by user on 19.03.15.
 */
public class Parser implements Analyzer<LinkedList<Token>, LinkedList<Token>> {


    private Analyzer prevHandler;

    public Parser() {
    }

    public Analyzer getPrevHandler() {
        return prevHandler;
    }

    public void setPrevHandler(Analyzer prevHandler) {
        this.prevHandler = prevHandler;
    }

    public Parser(Analyzer nextHandler) {
        this.prevHandler = nextHandler;
    }

    @Override
    public boolean canAnalyze(LinkedList<Token> matter) {
        boolean result = matter == null ? false : !matter.isEmpty();
        return result;
    }

    @Override
    public LinkedList<Token> analyze(LinkedList<Token> matter) {
        matter.forEach((token) -> {
            if (token.getType().equals(TokenType.STATEMENTS)) {
                resolveFamily(token);
            }
        });
        return matter;
    }

    private void resolveFamily(Token token) {


    }
}

/*    String s = "test 1-2-22";
    String[] vars = s.split("[ -]");
    String name = vars[0];
    String part_id = vars[1];
    String brand_id = vars[2];
    String count  = vars[3];*/