package test;

import java.lang.reflect.Array;
import java.util.regex.Pattern;

/**
 * Developed in the IntelliJ IDEA Environment.
 * User: Wolf
 * Date: Mar 22, 2010
 * Time: 5:48:43 PM
 */
public class TestString {
    public int pointVal(String problemStatement)
    {

        Pattern p = Pattern.compile("[\\W]");
        String[] words = p.split(problemStatement);

        Pattern d = Pattern.compile("^[:alpha:]");
        p = Pattern.compile("[:alpha:]{2,}");
        int index = 0;
        for(String word : words)
            if(!d.matcher(word).find()) words[index++] = word;
        String[] out = new String[index];
        while(0 < index--) out[index] = words[index];

        for(String word : out) System.out.println(word);

        System.out.println(out[0]);

        return out.length;
    }

    public static void main(String[] arguments)
    {
        TestString counter = new TestString();

        System.out.printf("Word Count: %d%n", counter.pointVal("This is a problem H5 statement."));

    }
}
