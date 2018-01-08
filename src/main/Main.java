package main;

import org.apache.commons.cli.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        Options options = new Options();
        OptionsBuilder opt = new OptionsBuilder();
        opt.addOptions(options);

        HelpFormatter formatter = new HelpFormatter();
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;


        try {

            cmd = parser.parse(options, args);
            //         int xyz = cmd.getArgs().length;
            //         System.out.println(xyz);

            

            String inputFilePath = cmd.getOptionValue("input");

            FileReader fileReader = new FileReader(inputFilePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            for (String arg : args )
                System.out.println("Argument : " + arg);


            StructBuilder textInfos = new StructBuilder();

            String textLine = bufferedReader.readLine();
            do {
                TextParser textParser = new TextParser();
                textParser.createPattern();
                textParser.checkInLine(textLine);
                textParser.isInLine();
                textInfos.writeInfo(textParser.getMatches(), textLine);

                System.out.println(textLine);
                textLine = bufferedReader.readLine();
            } while(textLine != null);


         TextPrinter textPrinter = new TextPrinter();
/*         textPrinter.printAll(textInfos);
         textPrinter.printTableOfContent(textInfos);
         textPrinter.printArticle(textInfos, 186);
         textPrinter.printArticles(textInfos, 15, 29);

         Integer num =  textPrinter.findChapter(textInfos, "Rozdział IX");
         System.out.println("Rozdział IX zaczyna się z artykułem " + num);

         Integer num1 = textPrinter.findNextChapter(textInfos, num);
         System.out.println("Następny rozdział X zaczyna się z artykułem " + num1);

         textPrinter.printChapter(textInfos, "Rozdział IX");
         textPrinter.printParagraph(textInfos, "2.", 162);
         textPrinter.printParagraph(textInfos, "1.", 214);
         textPrinter.printPoint(textInfos, "2)","2.", 162);

         String[] articleNumbers = cmd.getOptionValues("articles");
         for (String num3 : articleNumbers )
                System.out.println("Article Number : " + num3);
         System.out.println("\n");
         textPrinter.printArticles(textInfos, Integer.parseInt(articleNumbers[0]), Integer.parseInt(articleNumbers[1]));
*/
         String mode = cmd.getOptionValue("mode");
        if (mode.equals("c")){
            System.out.println("wybrałeś opcję wyświetlania spisu treści");
            textPrinter.printTableOfContent(textInfos);
        } else if (mode.equals("b")){
            System.out.println("wybrałeś opcję wyświetlania body");

            String chapter = cmd.getOptionValue("chapter");
            String article = cmd.getOptionValue("article");
            String[] articles = cmd.getOptionValues("articles");
            String paragraph = cmd.getOptionValue("paragraph");
            String point = cmd.getOptionValue("point");

            if(chapter != null){
                System.out.println("chcesz wyświetlić rozdział " + chapter);
                textPrinter.printChapter(textInfos, "Rozdział "+chapter);
            } else if (articles != null){
                System.out.println("chcesz wyświetlić zakres artykułów");
                textPrinter.printArticles(textInfos, Integer.parseInt(articles[0]), Integer.parseInt(articles[1]));
            } else if (article != null){
                System.out.println("chcesz wyświtlić artykuł i może coś więcej");

                if(paragraph != null && point != null){
                    System.out.println("chcesz wyświtlić artykuł , paragraf, punkt");
                    textPrinter.printPoint(textInfos, point, paragraph, Integer.parseInt(article));
                } else if ( paragraph != null){
                    System.out.println("chcesz wyświtlić artykuł , paragraf");
                    textPrinter.printParagraph(textInfos, paragraph, Integer.parseInt(article));
                }else if ( point != null){
                    System.out.println("chcesz wyświtlić artykuł , punkt");

                    ArrayList<IPart> parts = new ArrayList<>();
                    parts.add(new Point());

                    ArrayList<String> names = new ArrayList<>();
                    names.add(point);

                    System.out.println("zrobiłem listy");

                    textPrinter.printPart(parts.size(), parts, names, textInfos.articles.get(Integer.parseInt(article)-1));

                } else {
                    System.out.println("chcesz wyświtlić artykuł");
                    textPrinter.printArticle(textInfos, Integer.parseInt(article));
                }
            }
            else {
                System.out.println("nie podałeś specyfikacji więc wyświetlę całą konstytucję");
                textPrinter.printAll(textInfos);
            }

    /*        textPrinter.printDown(textPrinter.findNextPart(new Point(), "1)", textInfos.articles.get(241).getDown()));

            ArrayList<IPart> parts = new ArrayList<>();
            parts.add(new Paragraph());
            parts.add(new Point());

            ArrayList<String> names = new ArrayList<>();
            names.add("1.");
            names.add("3");

            textPrinter.printPart(2, parts, names, textInfos.articles.get(88));
*/

        } else {
            System.out.println("podałeś zły argument dla opcji mode");
        }


         bufferedReader.close();

        } catch (ParseException ex){

            System.out.println("Unexpected ParseException: " + ex.getMessage());
            formatter.printHelp( " ", options );

        } catch (IOException ex){
            System.out.println("Unexpected IOException: " + ex.getMessage());
        }
    }
}