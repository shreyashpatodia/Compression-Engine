/**
 * @Author: Shreyash Patodia
 */
package code;

import code.Main;
import code.helpers.BitOutputStream;
import code.helpers.BitSequence;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.testng.AssertJUnit.assertEquals;

/**
 * Tests written to test the output stream class.
 */
public class BitOutputStreamTest {

    @Test
    public void checkBitOutputStreamOne() throws IOException {
        File testFile = new File("test_files/test1.txt");
        BitOutputStream outputStream = new BitOutputStream(new FileOutputStream(testFile));
        BitSequence x = new BitSequence(8);
        x.setSequence(65);
        outputStream.write(x);
        FileReader fileReader = new FileReader(testFile);
        assertEquals(65, fileReader.read() );
    }

    @Test
    public void checkBitOutputStreamTwo() throws IOException {
        File testFile = new File("test_files/test2.txt");
        BitOutputStream outputStream = new BitOutputStream(new FileOutputStream(testFile));
        BitSequence x = new BitSequence(4);
        x.setSequence(4);
        outputStream.write(x);
        BitSequence y = new BitSequence(4);
        y.setSequence(0);
        outputStream.write(y);
        FileReader fileReader = new FileReader(testFile);
        assertEquals(64, fileReader.read());
    }

    @Test
    public void checkBitOutputStreamThree() throws IOException {
        File testFile = new File("test_files/test3.txt");
        BitOutputStream outputStream = new BitOutputStream(new FileOutputStream(testFile));
        BitSequence x = new BitSequence(4);
        x.setSequence(1);
        outputStream.write(x);
        x.setSequence(4);
        outputStream.write(x);
        FileReader fileReader = new FileReader(testFile);
        assertEquals(20, fileReader.read());
    }

    @Test
    public void checkBitOutputStreamFour() throws IOException {
        File testFile = new File("test_files/test4.txt");
        BitOutputStream outputStream = new BitOutputStream(new FileOutputStream(testFile));
        BitSequence x = new BitSequence(3);
        x.setSequence(3);
        outputStream.write(x);
        x.setSequence(3);
        outputStream.write(x);
        x.setSequence(3);
        outputStream.write(x);
        x.setSequence(3);
        outputStream.write(x);
        x.setSequence(3);
        outputStream.write(x);
        x.setSequence(3);
        outputStream.write(x);
        x.setSequence(3);
        outputStream.write(x);
        BitSequence y = new BitSequence(4);
        y.setSequence(0);
        outputStream.write(y);
        Path path = Paths.get("test_files/test4.txt");
        byte[] dataStream = Files.readAllBytes(path);
        byte[] checkStream = {109, -74, -40};

        assertEquals(Arrays.equals(dataStream, checkStream), true);

    }

    @Test
    public void checkBitOutputStreamFive() throws IOException {
        File testFile = new File("test_files/test5.txt");
        BitOutputStream outputStream = new BitOutputStream(new FileOutputStream(testFile));
        BitSequence x = new BitSequence(5);
        x.setSequence(3);
        outputStream.write(x);
        x.setSequence(3);
        outputStream.write(x);
        x.setSequence(3);
        outputStream.write(x);
        x.setSequence(3);
        outputStream.write(x);
        x.setSequence(3);
        outputStream.write(x);
        //System.out.println("The count is:" + outputStream.getCount());
        BitSequence y = new BitSequence(Main.BYTE_LENGTH - outputStream.getCount() + 1);
        y.setSequence(0);
        outputStream.write(y);
        Path path = Paths.get("test_files/test5.txt");
        byte[] dataStream = Files.readAllBytes(path);
        byte[] checkStream = {24, -58, 49, -128};
        assertEquals(Arrays.equals(dataStream, checkStream), true);
    }

}
