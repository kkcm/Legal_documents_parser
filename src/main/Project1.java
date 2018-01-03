package main;

import org.apache.commons.cli.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Project1 {
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

            bufferedReader.close();


        } catch (ParseException ex){

            System.out.println("Unexpected ParseException: " + ex.getMessage());
            formatter.printHelp( " ", options );

        } catch (IOException ex){
            System.out.println("Unexpected IOException: " + ex.getMessage());
        }
    }
}