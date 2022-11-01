
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        File          inFile      = new File("InputData.txt");
        StringBuilder finalString = new StringBuilder();

        try (Scanner scanner = new Scanner(inFile))
        {
            // Run while there is another int value in the file
            while (scanner.hasNextInt())
            {
                finalString.append(convert32BitIntegerTo4Chars(scanner.nextInt()));
            }

            // Print final string
            System.out.println(finalString);
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    private static String convert32BitIntegerTo4Chars(int num)
    {
        String result = "";
        // Example integer in binary: 0010 0000 0111 1100 0000 1010 0010 1011
        // Split 32-bit integer into 8 bit sections

        // Get the first 8 bits and cast to a char
        // [0010 0000] 0111 1100 0000 1010 0010 1011
        result += (char) (num & 0xff);

        // Move 8 bits to the right and cast the following 8 bits to a char
        // 0010 0000 [0111 1100] 0000 1010 0010 1011
        result += (char) ((num >> 8) & 0xff);

        // Move 16 bits to the right and cast the following 8 bits to a char
        // 0010 0000 0111 1100 [0000 1010] 0010 1011
        result += (char) ((num >> 16) & 0xff);

        // Move 24 bits to the right and cast the following 8 bits to a char
        // 0010 0000 0111 1100 0000 1010 [0010 1011]
        result += (char) ((num >> 24) & 0xff);

        return result;
    }
}