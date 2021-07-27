import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class MainTest
{

    @Test
    void main1() throws FileNotFoundException
    {
        NaiveBayes naiveBayes = new NaiveBayes();
        naiveBayes.UnknownText = "policia resi conto";
        String label = naiveBayes.ClassifyUnknown();
        assertEquals("Italian",label);
    }

    @Test
    void main2() throws FileNotFoundException
    {
        NaiveBayes naiveBayes = new NaiveBayes();
        naiveBayes.UnknownText = "he is a good boy";
        String label = naiveBayes.ClassifyUnknown();
        assertEquals("English",label);
    }

    @Test
    void main3() throws FileNotFoundException
    {
        NaiveBayes naiveBayes = new NaiveBayes();
        naiveBayes.UnknownText = "I Padri della Chiesa si consacrano prima di tutto alla difesa della fede cristiana";
        String label = naiveBayes.ClassifyUnknown();
        assertEquals("Italian",label);
    }

    @Test
    void main4() throws FileNotFoundException
    {
        NaiveBayes naiveBayes = new NaiveBayes();
        naiveBayes.UnknownText = "I am going to playing football at school";
        String label = naiveBayes.ClassifyUnknown();
        assertEquals("English",label);
    }

    @Test
    void main5() throws FileNotFoundException
    {
        NaiveBayes naiveBayes = new NaiveBayes();
        naiveBayes.UnknownText = "La terminologia usata è quasi sempre di origine";
        String label = naiveBayes.ClassifyUnknown();
        assertEquals("Italian",label);
    }

    @Test
    void main6() throws FileNotFoundException
    {
        NaiveBayes naiveBayes = new NaiveBayes();
        naiveBayes.UnknownText = "Questo per rendere possibile la lettura di profondità o per facilitare l'utilizzo di strumenti piani come battute per il migliore allineamento dell'inizio scala con un capo della lunghezza";
        String label = naiveBayes.ClassifyUnknown();
        assertEquals("Italian",label);
    }

    @Test
    void main7() throws FileNotFoundException
    {
        NaiveBayes naiveBayes = new NaiveBayes();
        naiveBayes.UnknownText = "Driving a car is a very good and cool job";
        String label = naiveBayes.ClassifyUnknown();
        assertEquals("English",label);
    }

    @Test
    void main8() throws FileNotFoundException
    {
        NaiveBayes naiveBayes = new NaiveBayes();
        naiveBayes.UnknownText = "Good boy sleeps before the sun set";
        String label = naiveBayes.ClassifyUnknown();
        assertEquals("English",label);
    }

    @Test
    void main9() throws FileNotFoundException
    {
        NaiveBayes naiveBayes = new NaiveBayes();
        naiveBayes.UnknownText = "Dettagli L'autore sono io e la fonte è la mia macchina fotografica digitale.";
        String label = naiveBayes.ClassifyUnknown();
        assertEquals("Italian",label);
    }

    //out of the train files sentences
    @Test
    void main10() throws FileNotFoundException
    {
        NaiveBayes naiveBayes = new NaiveBayes();
        naiveBayes.UnknownText = "Ciao è una nuova frase e voglio mostrarti che il mio programma funziona molto bene per vederti qui amico. dov'eri oggi è domani";
        String label = naiveBayes.ClassifyUnknown();
        assertEquals("Italian",label);
    }

    @Test
    void main11() throws FileNotFoundException
    {
        NaiveBayes naiveBayes = new NaiveBayes();
        naiveBayes.UnknownText = "Studierò i miei libri perché domani sera ho un esame e sono così nervoso";
        String label = naiveBayes.ClassifyUnknown();
        assertEquals("Italian",label);
    }

    @Test
    void main12() throws FileNotFoundException
    {
        NaiveBayes naiveBayes = new NaiveBayes();
        naiveBayes.UnknownText = "I am going to study my books because i have an exam tomorrow night and i am so nervous";
        String label = naiveBayes.ClassifyUnknown();
        assertEquals("English",label);
    }

    @Test
    void main13() throws FileNotFoundException
    {
        NaiveBayes naiveBayes = new NaiveBayes();
        naiveBayes.UnknownText = "Noi hai vedesti. Avvenne uno ora per mia altrove giovine mistica parlero turbare. Verrocchio la consolarmi ad";
        String label = naiveBayes.ClassifyUnknown();
        assertEquals("Italian",label);
    }

    @Test
    void main14() throws FileNotFoundException
    {
        NaiveBayes naiveBayes = new NaiveBayes();
        naiveBayes.UnknownText = "method to write some text to the file we created in the example above. Note that when you are done writing to the file, you should close it with the";
        String label = naiveBayes.ClassifyUnknown();
        assertEquals("English",label);
    }

    @Test
    void main15() throws FileNotFoundException
    {
        NaiveBayes naiveBayes = new NaiveBayes();
        naiveBayes.UnknownText = "Schools is optimized for learning and training. Examples might be simplified to improve reading and learning. Tutorials, references, and examples are constantly reviewed to avoid errors, but we cannot warrant full correctness of all content.";
        String label = naiveBayes.ClassifyUnknown();
        assertEquals("English",label);
    }

    @Test
    void main16() throws FileNotFoundException
    {
        NaiveBayes naiveBayes = new NaiveBayes();
        naiveBayes.UnknownText = "Mia chi suo agita senza ora piano credi terra. Primavera";
        String label = naiveBayes.ClassifyUnknown();
        assertEquals("Italian",label);
    }

    @Test
    void main17() throws FileNotFoundException
    {
        NaiveBayes naiveBayes = new NaiveBayes();
        naiveBayes.UnknownText = "Riposi ridere cosimo accade fianco si stoffe te un. Ai moribondo terribile guanciale ch ha accomiata il. Cui guardavamo indicibili conosciuto";
        String label = naiveBayes.ClassifyUnknown();
        assertEquals("Italian",label);
    }

    @Test
    void main18() throws FileNotFoundException
    {
        NaiveBayes naiveBayes = new NaiveBayes();
        naiveBayes.UnknownText = "Search in more than 30 million sentences of German newspaper material";
        String label = naiveBayes.ClassifyUnknown();
        assertEquals("English",label);
    }

    @Test
    void main19() throws FileNotFoundException
    {
        NaiveBayes naiveBayes = new NaiveBayes();
        naiveBayes.UnknownText = " gabbiani ah rinunzia minaccia ricevuto ai un. Fretta brilla Pei bruciavano. Tremit dal. ";
        String label = naiveBayes.ClassifyUnknown();
        assertEquals("Italian",label);
    }

    @Test
    void main20() throws FileNotFoundException
    {
        NaiveBayes naiveBayes = new NaiveBayes();
        naiveBayes.UnknownText = "andom gibberish text to use in web pages, site templates and in typography demos.\n" +
                "Get rid of Lorem Ipsum forever. A tool for web designers who want to save time";
        String label = naiveBayes.ClassifyUnknown();
        assertEquals("English",label);
    }

    @Test
    void main21() throws FileNotFoundException
    {
        NaiveBayes naiveBayes = new NaiveBayes();
        naiveBayes.UnknownText = "This handy tool helps you create dummy text for all your layout needs.";
        String label = naiveBayes.ClassifyUnknown();
        assertEquals("English",label);
    }

    @Test
    void main22() throws FileNotFoundException
    {
        NaiveBayes naiveBayes = new NaiveBayes();
        naiveBayes.UnknownText = "We are gradually adding new functionality and we welcome your suggestions and feedback.\n" +
                "\n" +
                "Please feel free to send us any additional dummy texts";
        String label = naiveBayes.ClassifyUnknown();
        assertEquals("English",label);
    }

    @Test
    void main23() throws FileNotFoundException
    {
        NaiveBayes naiveBayes = new NaiveBayes();
        naiveBayes.UnknownText = "Mia chi suo agita senza ora piano credi terra. ";
        String label = naiveBayes.ClassifyUnknown();
        assertEquals("Italian",label);
    }

    //Ge added
    @Test
    void main24() throws FileNotFoundException
    {
        NaiveBayes naiveBayes = new NaiveBayes();
        naiveBayes.UnknownText = "liefen in deutschen Großbuchbindereien";
        String label = naiveBayes.ClassifyUnknown();
        assertEquals("German",label);
    }

    @Test
    void main25() throws FileNotFoundException
    {
        NaiveBayes naiveBayes = new NaiveBayes();
        naiveBayes.UnknownText = "Leiter der Balkan-Aktivitäten der OGPU.\n" +
                "50\tAb 1934 lebte er mit seiner Verlobten zusammen, beide arbeiteten gemeinsam illegal für den KPD-Unterbezirk Berlin-Lichtenberg.\n" +
                "51\tAb 1934 war er Instructor in Pathologie mit dem Abschluss der Residency in Pathologie bei S. Burt Wolbach 1935.";
        String label = naiveBayes.ClassifyUnknown();
        assertEquals("German",label);
    }

    @Test
    void main26() throws FileNotFoundException
    {
        NaiveBayes naiveBayes = new NaiveBayes();
        naiveBayes.UnknownText = "acque con grande densità di vegetazione, predilige le aree in penombra, con acque di 24-26 °C.\n" +
                "41\tAbita con la prozia Constance Burke citation dopo la morte dei suoi genitori, Marshall Glaser e Sophie Burke.\n" +
                "42\tAbitata ancora negli anni Ottanta, la fermata costituisce collegamento per la frazione e per Pocapaglia con le due città di Alba e di Bra.\n" +
                "43\tA bordo della USS Seahawk si trovano sotto indagine, il fratello del navigatore morto nello schianto del tenente Rabb, e il compagno di missione del padre, che dirige la squadra di volo.\n" +
                "44\tA Bruxelles, a distanza di undici anni dall'";
        String label = naiveBayes.ClassifyUnknown();
        assertEquals("Italian",label);
    }

    @Test
    void main27() throws FileNotFoundException
    {
        NaiveBayes naiveBayes = new NaiveBayes();
        naiveBayes.UnknownText = "سلام امروز خوب هستم حال شما خوب است";
        String label = naiveBayes.ClassifyUnknown();
        assertEquals("Persian",label);
    }

    @Test
    void main28() throws FileNotFoundException
    {
        NaiveBayes naiveBayes = new NaiveBayes();
        naiveBayes.UnknownText = "وَإِذْ أَخَذْنَا مِنَ النَّبِيِّينَ مِيثَاقَهُمْ وَمِنْكَ وَمِنْ نُوحٍ وَإِبْرَاهِيمَ وَمُوسَى وَعِيسَى ابْنِ مَرْيَمَ وَأَخَذْنَا مِنْهُمْ مِيثَاقًا غَلِيظًا";
        String label = naiveBayes.ClassifyUnknown();
        assertEquals("Arabic",label);
    }
}