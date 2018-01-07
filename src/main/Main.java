package main;

import org.apache.commons.cli.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
            String inputFilePath = cmd.getOptionValue("input");

            FileReader fileReader = new FileReader(inputFilePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            for (String arg : args )
                System.out.println("Argument : " + arg);

/*            String[] articleNumbers = cmd.getOptionValues("article");
            System.out.println("Input File Path : " + inputFilePath);
            for (String num : articleNumbers )
            System.out.println("Article Number : " + num);
            System.out.println("\n");
*/


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

         //   textInfos.printTableOfContents();

         TextPrinter textPrinter = new TextPrinter();
         textPrinter.printAll(textInfos);
         textPrinter.printTableOfContent(textInfos);
         textPrinter.printArticle(textInfos, 186);
         textPrinter.printArticles(textInfos, 15, 29);

         Integer num =  textPrinter.findChapter(textInfos, "Rozdział IX");
         System.out.println("Rozdział IX zaczyna się z artykułem " + num);

         Integer num1 = textPrinter.findNextChapter(textInfos, num);
         System.out.println("Następny rozdział X zaczyna się z artykułem " + num1);

         textPrinter.printChapter(textInfos, "Rozdział IX");


         //   IPart partToPrint = textPrinter.findParagraph (textInfos, "2.", textInfos.articles.get(161).getDown());
         //   System.out.println("wróciłem do print paragraph z tym -> " +partToPrint.toString());
         //   textPrinter.writePart(partToPrint);

         textPrinter.printParagraph(textInfos, "2.", 162);
         textPrinter.printParagraph(textInfos, "1.", 214);
         textPrinter.printPoint(textInfos, "2)","2.", 162);


            bufferedReader.close();

        } catch (ParseException ex){

            System.out.println("Unexpected ParseException: " + ex.getMessage());
            formatter.printHelp( " ", options );

        } catch (IOException ex){
            System.out.println("Unexpected IOException: " + ex.getMessage());
        }
    }
}