package main;

import java.util.ArrayList;

public class TextPrinter {
    private ObjectHierarchy hierarchy;
    private PartFinder finder;

    public TextPrinter() {
        this.hierarchy = new ObjectHierarchy();
        this.finder = new PartFinder();
    }

    public void printTableOfContent(StructBuilder struct) {
        for (Article article : struct.articles) {
            IPart part = article;
            this.printContentHelper(part);
        }
    }

    public void printTableOfContentOfChapterOrSection(StructBuilder struct, String chapterName, IPart object) {
        Integer art1 = finder.findChapterOrSection(struct, " " + chapterName, object);
        Integer art2 = finder.findNextChapterOrSection(struct, art1, object);

        Integer i;
        for (i = art1; i < art2; i++) {
            IPart part = struct.articles.get(i);
            this.printContentHelper(part);
        }
    }

    public void printContentHelper(IPart part) {
        do {
            while (part.getUp() != null) {
                part = part.getUp();
            }
            while (part.getDown() != null && !hierarchy.isEqual(part, new Article()) && !hierarchy.isEqual(part, new ArticleWithLetter())) {
                System.out.println(part.toString());
                part = part.getDown();
            }
            part = part.getRight();
        } while (part != null);
    }

    public void printAll(StructBuilder struct) {

        for (Article article : struct.articles) {
            IPart part = article;
            this.printAllHelper(part);
        }
    }

    public void printAllHelper (IPart part){
        do {
            while (part.getUp() != null) {
                part = part.getUp();
            }
            while (part.getDown() != null && !hierarchy.isEqual(part, new Article()) && !hierarchy.isEqual(part, new ArticleWithLetter())) {
                System.out.println(part.toString());
                part = part.getDown();
            }
            printDown(part);
            part = part.getRight();
        } while (part != null);
    }

    public void printArticle(StructBuilder struct, Integer num) {
        if (num > struct.articles.size() - 1) {
            throw new TestException("za duży indeks");
        }
        writePart(struct.articles.get(num - 1));
    }

    public void printArticles(StructBuilder struct, Integer num1, Integer num2) {
        for (int i = num1; i < num2 + 1; i++) {
            printArticle(struct, i);
        }
    }

    public void printArticleWithLetter(StructBuilder struct, Integer num, String partName){
        IPart article = struct.articles.get(num-1);
        printDown(finder.findArticleWithLetter(article, partName));

    }

    public void printChapterOrSection(StructBuilder struct, String chapterName, IPart object) {
        Integer art1 = finder.findChapterOrSection(struct, " " + chapterName, object);
        Integer art2 = finder.findNextChapterOrSection(struct, art1, object);

        Integer i;
        for (i = art1; i < art2; i++) {
            IPart part = struct.articles.get(i);
            this.printAllHelper(part);
        }
    }


    public void printDown(IPart part) {
        System.out.println(part.toString());
        if (part.getDown() != null) {
            writePart(part.getDown());
        }
    }

    public void writePart(IPart part) {

        System.out.println(part.toString());
        if (part.getDown() != null) {
            writePart(part.getDown());
        }
        if (part.getRight() != null) {
            writePart(part.getRight());
        }
    }

    public void printPart(ArrayList<IPart> parts, ArrayList<String> names, IPart partIn) {
        Integer i;
        for (i = 0; i < parts.size(); i++) {
            partIn = finder.findNextPart(parts.get(i), names.get(i), partIn.getDown());
        }
        printDown(partIn);
    }

}
