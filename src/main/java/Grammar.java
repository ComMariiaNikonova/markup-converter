import java.io.IOException;
import java.util.Properties;

/**
 * Created by user on 22.03.15.
 */
public class Grammar {

    private static final String PROPERTY_FILE = "htmlMarkupGrammar.properties";

    public static Properties getGrammarTable() {
        return grammarTable;
    }

    private static Properties grammarTable = new Properties();

    static {
        try {
            grammarTable.load(IOHandler.class.getClassLoader().getResourceAsStream(PROPERTY_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
