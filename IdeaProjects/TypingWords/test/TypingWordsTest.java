import org.junit.ComparisonFailure;
import org.junit.Test;
import java.io.*;

import static org.junit.Assert.*;

// Created by Darius on 06.06.2016.

public class TypingWordsTest {

    @Test
    public void startUp(){ Main.main(null); }

    @Test
    public void chooseMenu() {
        String c = Main.decide(), msg = "\n\tfalse entry!\n";
        assert c.equals("1") || c.equals("2") || c.equals("3");

        switch(c) {
            case "1": msg = "\n\tstarting...\n";      break;
            case "2": msg = "\n\tloading list...\n";  break;
            case "3": msg = "\n\tclosing...\n";       break;
            default:                                  break;
        }

        System.out.println(msg);
    }

    @Test
    public void getWord() throws FileNotFoundException {
        String row = new String();
        String rndWord = new TypingWords().declareWord();

        try {
            BufferedReader br = new BufferedReader(new FileReader("words.txt"));
            row = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] expected = row.split(";");

        for (int i = 0; i <= expected.length - 1; i++) {
            try {
                assertEquals(expected[i], rndWord);
            } catch (ComparisonFailure e) { }
        }

        System.out.println("random word:\t" + rndWord);
    }

    @Test
    public void collision()
    {
        Boolean col = new TypingWords().collidedWord();
        assertEquals(true, col);
    }
}
