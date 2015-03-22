import org.junit.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by user on 19.03.15.
 */

public class TestSmoke {

/*    private static String ioConsole(String msg) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = "";
        while (str.isEmpty() || !Files.exists(Paths.get(str))) {
            if (str.equals("no")) {
                System.out.println("NOT Stored to file");
                break;
            }
            System.out.println(msg);
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return str;
    }*/


  /*  @Test(expected = NullPointerException.class)
    public void testIOHandler() {
        IOHandler.handleIO();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ByteArrayInputStream in = new ByteArrayInputStream("/Users/user/1_EduProjects/markup-converter/src/test/resources/inputSource".getBytes());
        System.setIn(in);*/

// do your thing

// optionally, reset System.in to its original
     /*   System.setIn(System.in);*/
 /*       ByteArrayInputStream in = new ByteArrayInputStream("/Users/user/1_EduProjects/markup-converter/src/test/resources/inputSource".getBytes());
        System.setIn(in);*/

    //  }



   /* @Test(expected = NullPointerException.class)
    public void testToHexStringWrong() {
        StringUtils.toHexString(null);
    }*/

}
