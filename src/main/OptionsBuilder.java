package main;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class OptionsBuilder {
    private Option section;
    private Option input;
    private Option mode;
    private Option chapter;
    private Option article;
    private Option articles;
    private Option paragraph;
    private Option point;
    private Option letter;
    private Option help;


    public OptionsBuilder() {

        this.section = new Option("s", "section", true, "display section");
        section.setArgs(1);

        this.input = new Option("i", "input", true, "input file to read data from");
        input.setRequired(true);
        input.setArgName("FILE PATH");

        this.mode = new Option("m", "mode", true, "choose mode: c - display table of contents, b - display body");
        mode.setRequired(true);

        this.chapter = new Option("c", "chapter", true, "display chapter");
        chapter.setArgs(1);

        this.article = new Option("a", "article", true, "display article");
        article.setArgs(1);

        this.articles = new Option("as", "articles", true, "display articles");
        articles.setArgs(2);

        this.paragraph = new Option("p", "paragraph", true, "display paragraph");
        paragraph.setArgs(1);

        this.point = new Option("d", "point", true, "display point");
        point.setArgs(1);

        this.letter = new Option("l", "letter", true, "display point");
        letter.setArgs(1);

        this.help = new Option("h", "help", false, "print this message");


    }

    public Options addOptions (Options options){

        options.addOption(section);
        options.addOption(input);
        options.addOption(mode);
        options.addOption(chapter);
        options.addOption(article);
        options.addOption(articles);
        options.addOption(paragraph);
        options.addOption(point);
        options.addOption(letter);
        options.addOption(help);

        return options;
    }

}
