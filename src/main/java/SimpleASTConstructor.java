import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;


/**
 * Created by user on 19.03.15.
 */
public class SimpleASTConstructor implements Analyzer<Map<TokenType, LinkedList<Token>>, LinkedList<Token>> {

    private Analyzer prevHandler;

    public SimpleASTConstructor() {
    }

    public Analyzer getPrevHandler() {
        return prevHandler;
    }

    public void setPrevHandler(Analyzer prevHandler) {
        this.prevHandler = prevHandler;
    }

    public SimpleASTConstructor(Analyzer nextHandler) {
        this.prevHandler = nextHandler;
    }

    @Override
    public boolean canAnalyze(Map<TokenType, LinkedList<Token>> matter) {
        boolean result = matter == null ? false : !matter.isEmpty();
        return result;
    }

    @Override
    public LinkedList<Token> analyze(Map<TokenType, LinkedList<Token>> tokenByFamily) {
        tokenByFamily.entrySet().parallelStream().forEach((entry) ->
                analyzeFamilySubtrees(entry));
        return null;
    }

    private void analyzeFamilySubtrees(Map.Entry<TokenType, LinkedList<Token>> entry) {
        TokenType tokenType = entry.getKey();

        switch (entry.getKey()) {
            case H_FAMILY:
                analizeHFamily(entry.getValue());
                break;
            case P_FAMILY:
                analizePFamily(entry.getValue());
                break;
            case LINK_OPEN_FAMILY:
                analizeLOFamily(entry.getValue());
                break;
            case LINK_CLOSE_FAMILY:
                analizeLCFamily(entry.getValue());
                break;
            case EM_STRONG_FAMILY:
                analizeESFamily(entry.getValue());
                break;
        }
    }

    private void analizeHFamily(LinkedList<Token> value) {
        if ((value.size() % 2) == 0) { //valid string
            for (int i = 0; i < value.size(); i++) {
                if (value.get(i).getColumn() == (value.get(i + 1).getColumn()) + 1 && (value.get(i + 1).getColumn()) + 1 == (value.get(i + 2).getColumn()) + 2) {
                /*tree char sequence*/
                } else if (value.get(i).getColumn() == (value.get(i + 1).getColumn()) + 1) {
                /*two char sequence*/
                } else {/*two char sequence*/}
            }
        } else {


        }

    }

    private void analizeESFamily(LinkedList<Token> value) {
    }

    private void analizeLCFamily(LinkedList<Token> value) {
    }

    private void analizeLOFamily(LinkedList<Token> value) {
    }

    private void analizePFamily(LinkedList<Token> value) {
    }
}
