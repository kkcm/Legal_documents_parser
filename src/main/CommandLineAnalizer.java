package main;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;

import java.util.ArrayList;

public class CommandLineAnalizer  {
        private HelpFormatter formatter;
        private CommandLine cmd;
        private TextPrinter textPrinter;
        private StructBuilder structFile;
        private PartFinder finder;

    public CommandLineAnalizer(CommandLine cmd){
        this.formatter = new HelpFormatter();
        this.textPrinter = new TextPrinter();
        this.structFile = new StructBuilder();
        this.finder = new PartFinder();
        this.cmd = cmd;
    }

    public void optionReader() {
        if (cmd.hasOption("input")){
            String inputFilePath = cmd.getOptionValue("input");
            structFile.readFile(inputFilePath);
        }

        if(cmd.hasOption("mode")){
            if (cmd.getOptionValue("mode").equals("c")){
                if(cmd.hasOption("tc")){
                    this.textPrinter.printTableOfContentOfChapterOrSection(structFile, cmd.getOptionValue("tc"), new Chapter());
                } else if (cmd.hasOption("ts")){
                    this.textPrinter.printTableOfContentOfChapterOrSection(structFile, cmd.getOptionValue("ts"), new Section());
                } else {
                    this.textPrinter.printTableOfContent(structFile);
                }

            } else if (cmd.getOptionValue("mode").equals("b")){
                if(cmd.hasOption("section")){
                    this.textPrinter.printChapterOrSection(structFile, cmd.getOptionValue("section"), new Section());
                } else if (cmd.hasOption("chapter")){
                    this.textPrinter.printChapterOrSection(structFile, cmd.getOptionValue("chapter"), new Chapter());
                } else if (cmd.hasOption("articles")){
                    String[] articles = cmd.getOptionValues("articles");

                    if (Integer.parseInt(articles[0]) > Integer.parseInt(articles[1])){
                        throw new TestException("Pierwszy argument powinien być mniejszy od drugiego.");
                    }

                    this.textPrinter.printArticles(structFile, Integer.parseInt(articles[0]), Integer.parseInt(articles[1]) );
                } else if (cmd.hasOption("article")){
                    IPart part = this.structFile.articles.get(Integer.parseInt(cmd.getOptionValue("article"))-1);

                    ArrayList<IPart> parts1 = new ArrayList<>();
                    ArrayList<String> names1 = new ArrayList<>();
                    this.deepSearch(parts1, names1);
                    if (parts1.size() > 0) {
                        this.textPrinter.printPart(parts1, names1, part);
                    } else{
                        this.textPrinter.printDown(part);
                    }

                } else if (cmd.hasOption("articleWithLetter")){
                    String[] articles = cmd.getOptionValues("articleWithLetter");

                    IPart article = this.structFile.articles.get(Integer.parseInt(articles[0])-1);
                    IPart partToWrite = this.finder.findArticleWithLetter(article, articles[1]);

                    ArrayList<IPart> parts2 = new ArrayList<>();
                    ArrayList<String> names2 = new ArrayList<>();
                    this.deepSearch(parts2, names2);

                    if (parts2.size() > 0) {
                        this.textPrinter.printPart(parts2, names2, partToWrite);
                    } else{
                        this.textPrinter.printDown(partToWrite);
                    }
                } else {
                    System.out.println("Nie podałeś specyfikacji więc wyświetlę cały tekst.");
                    this.textPrinter.printAll(structFile);
                }

            } else {
                throw new TestException("Podałeś złe argumenty dla opcji mode.");
            }
        }

    }

    public void deepSearch (ArrayList<IPart> parts, ArrayList<String> names){
        if (cmd.hasOption("paragraph")){
            parts.add(new Paragraph());
            names.add(cmd.getOptionValue("paragraph"));
        }

        if (cmd.hasOption("point")){
            parts.add(new Point());
            names.add(cmd.getOptionValue("point"));
        }

        if(cmd.hasOption("letter")){
            parts.add(new Letter());
            names.add(cmd.getOptionValue("letter"));
        }
    }
}
