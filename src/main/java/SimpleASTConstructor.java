import java.util.LinkedList;

/**
 * Created by user on 19.03.15.
 */
public class SimpleASTConstructor implements Analyzer<LinkedList<String>, String> {

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
    public boolean canAnalyze(LinkedList<String> matter) {
        boolean result = matter == null ? false : !matter.isEmpty();
        return result;
    }

    @Override
    public String analyze(LinkedList<String> matter) {
        return matter.getFirst();
    }
}
