package task.markup;

import task.markup.core.Grammar;
import task.markup.core.Strategy;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Supplier;

/**
 * Created by user on 19.03.15.
 */
public class IOHandler {


    private static final String SOURCE_FILE_PATH_MSG = "\n\nEnter path to source file: ";
    private static final String OUTPUT_FILE_PATH_MSG = "\n\nEnter path to output file (if you don`t want store it, type 'no'): ";

    public static String readSource() {
        IOHandler handler = new IOHandler();
        String str = handler.iSource(() -> ioConsole(SOURCE_FILE_PATH_MSG));
        return str;
    }


    public static void writeSource(String preparedData) {
        IOHandler handler = new IOHandler();
        handler.oPreparedData(() -> ioConsole(OUTPUT_FILE_PATH_MSG), preparedData);
        System.out.println("Data was written");
    }

    private String iSource(Supplier<String> inFileFunc) {
        String sourceData = "";
        try (RandomAccessFile sourceFile = new RandomAccessFile(/*"/Users/user/1_EduProjects/markup-converter/src/test/resources/inputSource"*/inFileFunc.get(), "r")) {//todo
            FileChannel inChannel = sourceFile.getChannel();

            ByteBuffer buf = ByteBuffer.allocate(256);
            int bytesRead = inChannel.read(buf);
            while (bytesRead != -1) {
                buf.flip();
                System.out.println("\nSOURCE: ");


                byte[] arr = new byte[buf.limit()];
                buf.get(arr);
                sourceData = new String(arr, "UTF-8");
                System.out.print(sourceData);

                buf.clear();
                bytesRead = inChannel.read(buf);
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sourceData;
    }

    private void oPreparedData(Supplier<String> outFileFunc, String preparedData) {
        BufferedWriter writer = null;
        try {
            writer = Files.newBufferedWriter(Paths.get("/Users/user/1_EduProjects/markup-converter/src/test/resources/outHtml"/*outFileFunc.get()*/));
            writer.write(preparedData); //TODO
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("\nOUT: ");
        System.out.println(preparedData);
    }

    private static String ioConsole(String msg) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = "";
        while (str.isEmpty() || !Files.exists(Paths.get(str))) {
            if (str.equals("no")) {
                System.out.println("\nNOT Stored to file");
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
    }

    public static void main(String[] args) {

        Grammar.getGrammarTable().entrySet().forEach((str) -> System.out.println(str));
        Strategy strategy = new Strategy(IOHandler.readSource());
        strategy.analyzeByStrategy();
        IOHandler.writeSource(strategy.getData());
    }
}
