import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ReadArabicText
{
    public static void main(String[] args) throws FileNotFoundException
    {
        ArabicModelMaker arabicModelMaker = new ArabicModelMaker();
    }
}

class ArabicModelMaker
{
    public String ArText;
    public String PeText;
    public String KuText;

    int ArTextCoupleCount;
    int PeTextCoupleCount;
    int KuTextCoupleCount;

    int[][] ArMatrix;
    int[][] PeMatrix;
    int[][] KuMatrix;

    ArabicModelMaker() throws FileNotFoundException
    {
        ArText = "";
        PeText = "";
        KuText = "";

        //Arabic char range (1536 - 1791)
        ArMatrix = new int[1791 - 1536 + 1][1791 - 1536 + 1];
        PeMatrix = new int[1791 - 1536 + 1][1791 - 1536 + 1];
        KuMatrix = new int[1791 - 1536 + 1][1791 - 1536 + 1];

        ArTextCoupleCount = 0;
        PeTextCoupleCount = 0;
        KuTextCoupleCount = 0;

        ArText = ReadTextFromFile("Ar.txt");
        PeText = ReadTextFromFile("Pe.txt");
        KuText = ReadTextFromFile("Ku.txt");

        ArText = CleanArabicText(ArText);
        PeText = CleanArabicText(PeText);
        KuText = CleanArabicText(KuText);
        //UnknownText = CleanText(UnknownText);

        CalculateCoupleCounts();

        Process(ArMatrix, ArText);
        Process(PeMatrix, PeText);
        Process(KuMatrix, KuText);

        WriteMatrixToFile(ArMatrix, "ArMatrix.txt", ArTextCoupleCount);
        WriteMatrixToFile(PeMatrix, "PeMatrix.txt", PeTextCoupleCount);
        WriteMatrixToFile(KuMatrix, "KuMatrix.txt", KuTextCoupleCount);
    }

    void CalculateCoupleCounts()
    {
        String[] enWords = ArText.split(" ");
        String[] itWords = PeText.split(" ");
        String[] geWords = KuText.split(" ");

        for (int j = 0; j < enWords.length; j++)
            ArTextCoupleCount += (enWords[j].length() - 1);

        for (int j = 0; j < itWords.length; j++)
            PeTextCoupleCount += (itWords[j].length() - 1);

        for (int j = 0; j < geWords.length; j++)
            KuTextCoupleCount += (geWords[j].length() - 1);
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

                int index1 = firstChar - 1536;
                int index2 = secondChar - 1536;

                if (!(index1 < 0 || index2 < 0 || index1 > (array.length - 1) || index2 > (array.length - 1)))
                    array[index1][index2]++;
            }
        }
    }

    String CleanArabicText(String text)
    {
        for (int i = 0; i < 'Z' - 'A' + 1; i++)
        {
            text = text.replaceAll(Character.toString('A' + i), Character.toString('A' + i + 32));
        }

        text = text.replaceAll("[-+.€™\"۰۱^٠٭١٢٣٤٥٦٧٨٩٪٫٬0123456789:!@#%^&*()_+=}{:'?<>/*,$#\nnqwertyui~`opasd\nfghjkl;۵۴'zxc۸۳vb۶۹n۷۲m,./\t]", "");

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
