package main;

import org.apache.commons.cli.*;

import java.io.FileNotFoundException;

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
             CommandLineAnalizer cmdLineAnalizer = new CommandLineAnalizer(cmd);
             cmdLineAnalizer.optionReader();

        } catch (ParseException ex){
            System.out.println("Unexpected ParseException: " + ex.getMessage());
            formatter.printHelp( " ", options );

        } catch (TestException ex){
            System.out.println("TestException: " + ex.getMessage());
        } catch (IndexOutOfBoundsException ex){
            System.out.println("IndexOutOfBoundsException: "+ ex.getMessage());
         }
    }
}
