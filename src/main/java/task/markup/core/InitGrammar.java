package task.markup.core;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by user on 22.03.15.
 */
public class InitGrammar {

    private static final String PROPERTY_FILE = "htmlMarkupGrammar.properties";
    private static Properties grammarTable = new Properties();

    static {
        try {
            grammarTable.load(InitGrammar.class.getClassLoader().getResourceAsStream(PROPERTY_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Properties getGrammarTable() {
        return grammarTable;
    }


}
