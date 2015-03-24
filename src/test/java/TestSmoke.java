import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import task.markup.IOHandler;
import task.markup.core.InitGrammar;
import task.markup.core.Strategy;

/**
 * Created by user on 19.03.15.
 */

public class TestSmoke {

    @Rule
    public final TextFromStandardInputStream systemInMock = new TextFromStandardInputStream("");

    @Test
    public void Test() {

        systemInMock.provideText("src/test/resources/inputSource\n");
        InitGrammar.getGrammarTable().entrySet().forEach((str) -> System.out.println(str));
        Strategy strategy = new Strategy(IOHandler.readSource());
        strategy.analyzeByStrategy();
    }
}

