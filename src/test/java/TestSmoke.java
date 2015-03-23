import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import task.markup.IOHandler;
import task.markup.core.Grammar;
import task.markup.core.Strategy;

/**
 * Created by user on 19.03.15.
 */

public class TestSmoke {

    @Rule
    public final TextFromStandardInputStream systemInMock = new TextFromStandardInputStream("");

    @Rule
    public final TextFromStandardInputStream systemOutMock = new TextFromStandardInputStream("");

    @Test
    public void Test() {

        systemInMock.provideText("/Users/user/1_EduProjects/markup-converter/src/test/resources/inputSource");
        Grammar.getGrammarTable().entrySet().forEach((str) -> System.out.println(str));
        Strategy strategy = new Strategy(IOHandler.readSource());
        strategy.analyzeByStrategy();
        systemOutMock.provideText("/Users/gkarabut/src/test_masha/markup-converter/src/test/resources/outHtml");
      //  IOHandler.writeSource(strategy.getData());
    }
}

