package main;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class OptionsBuilder {
    private Option section;
    private Option input;
    private Option mode;
    private Option chapter;
    private Option tableOfChapter;
    private Option tableOfSection;
    private Option article;
    private Option articles;
    private Option articleWithLetter;
    private Option paragraph;
    private Option point;
    private Option letter;
    private Option help;


    public OptionsBuilder() {

        this.section = new Option("s", "section", true, "wyświetla dział - np. -s VII");
        section.setArgs(1);

        this.input = new Option("i", "input", true, "ścieżka dostępu do pliku");
        input.setRequired(true);
        input.setArgName("FILE PATH");

        this.mode = new Option("m", "mode", true, "wybierz tryb działania: c - wyświetla spis treści, b - wyświetla spis");
        mode.setRequired(true);

        this.chapter = new Option("c", "chapter", true, "wyświetla rozdział - np. -c III");
        chapter.setArgs(1);

        this.tableOfChapter = new Option ("tc", "table of chapters", true, "wyświetl spis treści danego rozdziału, wymagany tryb wyświetlania spisu treści - np. -tc II");
        tableOfChapter.setArgs(1);

        this.tableOfSection = new Option ("ts", "table of sections", true, "wyświetl spis treści danego działu, wymagany tryb wyświetlania spisu treści - np. -ts V");
        tableOfSection.setArgs(1);

        this.article = new Option("a", "article", true, "wyświetla artykuł - np. -a 242");
        article.setArgs(1);

        this.articles = new Option("as", "articles", true, "wyświetla ciąg artykułów - np. -as 34 43");
        articles.setArgs(2);

        this.articleWithLetter = new Option("al", "articleWithLetter", true, "pokaż artykuł z literą - podaj cyfrę, a po odstępie literę - np. -al 105 a");
        articleWithLetter.setArgs(2);

        this.paragraph = new Option("p", "paragraph", true, "wyświetla ustęp - np. -p 3.");
        paragraph.setArgs(1);

        this.point = new Option("d", "point", true, "wyświetla punkt - np. -d 3)");
        point.setArgs(1);

        this.letter = new Option("l", "letter", true, "wyświetla literę - np. -l b)");
        letter.setArgs(1);

        this.help = new Option("h", "help", false, "wyświetla tą istrukcję");

    }

    public Options addOptions (Options options){

        options.addOption(section);
        options.addOption(input);
        options.addOption(mode);
        options.addOption(chapter);
        options.addOption(tableOfChapter);
        options.addOption(tableOfSection);
        options.addOption(article);
        options.addOption(articles);
        options.addOption(articleWithLetter);
        options.addOption(paragraph);
        options.addOption(point);
        options.addOption(letter);
        options.addOption(help);

        return options;
    }

}
