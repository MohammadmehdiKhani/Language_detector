import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ReadLatinText
{
    public static void main(String[] args) throws FileNotFoundException
    {
        LatinModelMaker latinModelMaker = new LatinModelMaker();
    }
}

class LatinModelMaker
{
    public String EnText;
    public String ItText;
    public String GeText;

    int EnTextCoupleCount;
    int ItTextCoupleCount;
    int GeTextCoupleCount;

    int[][] EnArray;
    int[][] ItArray;
    int[][] GeArray;

    LatinModelMaker() throws FileNotFoundException
    {
        EnText = "";
        ItText = "";
        GeText = "";

        EnArray = new int[255 - 'a' + 1][255 - 'a' + 1];           //225 is the last special char in latin languages
        ItArray = new int[255 - 'a' + 1][255 - 'a' + 1];
        GeArray = new int[255 - 'a' + 1][255 - 'a' + 1];

        EnTextCoupleCount = 0;
        ItTextCoupleCount = 0;
        GeTextCoupleCount = 0;

        EnText = ReadTextFromFile("En.txt");
        ItText = ReadTextFromFile("It.txt");
        GeText = ReadTextFromFile("Ge.txt");

        EnText = CleanLatinText(EnText);
        ItText = CleanLatinText(ItText);
        GeText = CleanLatinText(GeText);

        CalculateCoupleCounts();

        Process(EnArray, EnText);
        Process(ItArray, ItText);
        Process(GeArray, GeText);

        WriteMatrixToFile(EnArray, "EnMatrix.txt", EnTextCoupleCount);
        WriteMatrixToFile(ItArray, "ItMatrix.txt", ItTextCoupleCount);
        WriteMatrixToFile(GeArray, "GeMatrix.txt", GeTextCoupleCount);
    }

    void CalculateCoupleCounts()
    {
        String[] enWords = EnText.split(" ");
        String[] itWords = ItText.split(" ");
        String[] geWords = GeText.split(" ");

        for (int j = 0; j < enWords.length; j++)
            EnTextCoupleCount += (enWords[j].length() - 1);

        for (int j = 0; j < itWords.length; j++)
            ItTextCoupleCount += (itWords[j].length() - 1);

        for (int j = 0; j < geWords.length; j++)
            GeTextCoupleCount += (geWords[j].length() - 1);
    }

    void Process(int[][] array, String text)
    {
        String[] words = text.split(" ");

        for (int j = 0; j < words.length; j++)
        {
            for (int i = 0; i < words[j].length() - 1; i++)
            {
                char firstChar = words[j].charAt(i);
                char secondChar = words[j].charAt(i + 1);

                int index1 = firstChar - 'a';
                int index2 = secondChar - 'a';

                if (!(index1 < 0 || index2 < 0 || index1 > (array.length - 1) || index2 > (array.length - 1)))
                    array[index1][index2]++;
            }
        }
    }

    String CleanLatinText(String text)
    {
        for (int i = 0; i < 'Z' - 'A' + 1; i++)
        {
            text = text.replaceAll(Character.toString('A' + i), Character.toString('A' + i + 32));
        }
        text = text.replaceAll("[-+.€٬٭۰™\"^0123456789:٪٫!@\n#%^&۹*()۵_۳+۶=}۷{:۸~`۴'۱۲?<>/*,$#\t]", "");

        return text;
    }

    String ReadTextFromFile(String Address) throws FileNotFoundException
    {
        String text = "";

        File file = new File(Address);
        Scanner reader = new Scanner(file);
        while (reader.hasNextLine())
        {
            String line = reader.nextLine();
            text += line;
            text += " ";
        }
        reader.close();
        return text;
    }

    void WriteMatrixToFile(int[][] matrix, String address, int coupleCount)
    {
        try
        {
            FileWriter myWriter = new FileWriter(address);
            myWriter.write(coupleCount + "\n");
            myWriter.flush();

            for (int i = 0; i < matrix.length; i++)
            {
                for (int j = 0; j < matrix.length; j++)
                {
                    myWriter.write(matrix[i][j] + " ");
                }
                myWriter.write("\n");
            }
            myWriter.flush();

        } catch (IOException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
