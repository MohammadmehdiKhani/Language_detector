import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) throws FileNotFoundException
    {
        NaiveBayes naiveBayes = new NaiveBayes();

        File myObj = new File("input.csv");
        Scanner myReader = new Scanner(myObj);
        File outputFile = new File("output.csv");
        PrintWriter writer = new PrintWriter(outputFile);
        myReader.nextLine();

        writer.write("Id,Category" + "\r\n");

        while (myReader.hasNextLine())
        {
            String line = myReader.useDelimiter("\r\n").next();
            naiveBayes.UnknownText = line;
            String label = naiveBayes.ClassifyUnknown();

            StringBuilder sb = new StringBuilder();
            sb.append(line);
            sb.append(",");
            sb.append(label);
            sb.append("\r\n");

            writer.write(sb.toString());
            writer.flush();
        }
    }
}

class NaiveBayes
{
    public String UnknownText = "";

    int EnTextCoupleCount = 0;
    int ItTextCoupleCount = 0;
    int GeTextCoupleCount = 0;
    int ArTextCoupleCount = 0;
    int PeTextCoupleCount = 0;
    int KuTextCoupleCount = 0;

    //Latin char range (97 - 255)
    int[][] EnMatrix = new int[255 - 'a' + 1][255 - 'a' + 1];
    int[][] ItMatrix = new int[255 - 'a' + 1][255 - 'a' + 1];
    int[][] GeMatrix = new int[255 - 'a' + 1][255 - 'a' + 1];

    //Arabic char range (1536 - 1791)
    int[][] ArMatrix = new int[1791 - 1536 + 1][1791 - 1536 + 1];
    int[][] PeMatrix = new int[1791 - 1536 + 1][1791 - 1536 + 1];
    int[][] KuMatrix = new int[1791 - 1536 + 1][1791 - 1536 + 1];

    NaiveBayes() throws FileNotFoundException
    {
        EnTextCoupleCount = ReadMatrixFromFile(EnMatrix, "EnMatrix.txt");
        ItTextCoupleCount = ReadMatrixFromFile(ItMatrix, "ItMatrix.txt");
        GeTextCoupleCount = ReadMatrixFromFile(GeMatrix, "GeMatrix.txt");

        ArTextCoupleCount = ReadMatrixFromFile(ArMatrix, "ArMatrix.txt");
        PeTextCoupleCount = ReadMatrixFromFile(PeMatrix, "PeMatrix.txt");
        KuTextCoupleCount = ReadMatrixFromFile(KuMatrix, "KuMatrix.txt");
    }

    int ReadMatrixFromFile(int[][] matrix, String address) throws FileNotFoundException
    {
        int coupleCount = 0;
        File file = new File(address);
        Scanner reader = new Scanner(file);

        String firstLine = reader.nextLine();
        coupleCount = Integer.parseInt(firstLine);

        for (int i = 0; i < matrix.length; i++)
        {
            String line = reader.nextLine();
            String[] words = line.split(" ");

            for (int j = 0; j < matrix.length; j++)
            {
                matrix[i][j] = Integer.parseInt(words[j]);
            }
        }
        reader.close();
        return coupleCount;
    }

    String ClassifyUnknown()
    {
        long sumOfChars = 0;
        UnknownText = CleanGenerally(UnknownText);
        int counter = UnknownText.length();

        for (int i = 0; i < counter; i++)
        {
            char ch = UnknownText.charAt(i);
            sumOfChars += ch;
        }

        long averageOfChars = sumOfChars / counter;
        String label = "";

        if (averageOfChars > (1536 - 255) / 2)    //Border of latin and arabic chars
            label = ArabicLabeling();

        else
            label = LatinLabeling();

        return label;
    }

    String LatinLabeling()
    {
        double enChance = 1;
        double itChance = 1;
        double geChance = 1;

        UnknownText = CleanLatinText(UnknownText);
        String[] words = UnknownText.split(" ");

        for (int i = 0; i < words.length; i++)
        {
            for (int j = 0; j < words[i].length() - 1; j++)
            {
                char firstChar = words[i].charAt(j);
                char secondChar = words[i].charAt(j + 1);

                int index1 = firstChar - 'a';
                int index2 = secondChar - 'a';

                if (index1 < 0 || index2 < 0 || index1 >= EnMatrix.length || index2 >= EnMatrix[0].length)
                    continue;

                double WordIsEnProbability = ((double) EnMatrix[index1][index2] + 1) / (double) EnTextCoupleCount;
                double WordIsItProbability = ((double) ItMatrix[index1][index2] + 1) / (double) ItTextCoupleCount;
                double WordIsGeProbability = ((double) GeMatrix[index1][index2] + 1) / (double) GeTextCoupleCount;

                enChance *= WordIsEnProbability * 100;
                itChance *= WordIsItProbability * 100;
                geChance *= WordIsGeProbability * 100;
            }
        }

        if (enChance >= itChance && enChance >= geChance)
            return "English";

        if (itChance >= enChance && itChance >= geChance)
            return "Italian";

        if (geChance >= itChance && geChance >= enChance)
            return "German";

        return "English";
    }

    String ArabicLabeling()
    {
        double arChance = 1;
        double peChance = 1;
        double kuChance = 1;

        UnknownText = CleanArabicText(UnknownText);
        String[] words = UnknownText.split(" ");

        for (int i = 0; i < words.length; i++)
        {
            for (int j = 0; j < words[i].length() - 1; j++)
            {
                char firstChar = words[i].charAt(j);
                char secondChar = words[i].charAt(j + 1);

                int index1 = firstChar - 1536;
                int index2 = secondChar - 1536;

                if (index1 < 0 || index2 < 0 || index1 >= ArMatrix.length || index2 >= ArMatrix[0].length)
                    continue;

                double WordIsArProbability = ((double) ArMatrix[index1][index2] + 1) / (double) ArTextCoupleCount;
                double WordIsPeProbability = ((double) PeMatrix[index1][index2] + 1) / (double) PeTextCoupleCount;
                double WordIsKuProbability = ((double) KuMatrix[index1][index2] + 1) / (double) KuTextCoupleCount;

                arChance *= WordIsArProbability * 100;
                peChance *= WordIsPeProbability * 100;
                kuChance *= WordIsKuProbability * 100;
            }
        }

        if (arChance >= peChance && arChance >= kuChance)
            return "Arabic";

        if (peChance >= arChance && peChance >= kuChance)
            return "Persian";

        if (kuChance >= peChance && kuChance >= arChance)
            return "Kurdish";

        return "Persian";
    }

    String CleanLatinText(String text)
    {
        for (int i = 0; i < 'Z' - 'A' + 1; i++)
        {
            text = text.replaceAll(Character.toString('A' + i), Character.toString('A' + i + 32));
        }
        text = text.replaceAll("[-+.€٬٭۰™\"^0123456789:٪٫!@\n#%^&۹*()۵_۳+۶=}۷{:~`۸۴'۱۲?<>/*,$#\t]", "");
        return text;
    }

    String CleanArabicText(String text)
    {
        text = text.replaceAll("[-+.€™\"۰۱^٠٭١٢٣٤٥٦٧٨٩٪٫٬0123456789:!@#%^&*()_+=}{:'?<>/*,$#\nnqwertyuiopasd\nfghjkl;۵۴'zxc۸۳vb۶~`۹n۷۲m,./\t]", "");
        return text;
    }

    String CleanGenerally(String text)
    {
        text = text.replaceAll("[-+.€٬٭۰™\"^0123456789:٪٫!@\n#%^&۹*()۵_۳+۶=}۷{:~`۸۴'۱۲?<>/*,$#\t]", "");
        return text;
    }
}
