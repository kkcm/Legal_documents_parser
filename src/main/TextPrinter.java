package main;

import java.util.ArrayList;

public class TextPrinter {
    private ObjectHierarchy hierarchy;

    public TextPrinter(){
        this.hierarchy = new ObjectHierarchy();
    }

    public void printTableOfContent(StructBuilder struct){
        for (Article article : struct.articles) {
            IPart part = article;

            do {
                while (part.getUp() != null) {
                    part = part.getUp();
                    System.out.println("wchodzę w górę");
                }
                while (part.getDown() != null && !part.getClass().isAssignableFrom(article.getClass()) && !part.getClass().isAssignableFrom(ArticleWithLetter.class) ) {
                    System.out.println(part.toString());
                    part = part.getDown();
                    System.out.println("schodzę w dół");
                }
                part = part.getRight();
            } while (part != null);

        }
    }

    public void printAll(StructBuilder struct){

       for (Article article : struct.articles){
            IPart part = article;

            do {
                while (part.getUp() != null) {
                    part = part.getUp();
                    System.out.println("wchodzę w górę");
                }
                while (part.getDown() != null && !part.getClass().isAssignableFrom(Article.class) && !part.getClass().isAssignableFrom(ArticleWithLetter.class)){
                    System.out.println(part.toString());
                    part = part.getDown();
                    System.out.println("schodzę w dół");
                }


                printDown(part);
                part = part.getRight();
                System.out.println("Spróbuję iść w prawo");
            } while (part != null);


        }
    }

    public void printAll1(StructBuilder struct){

        for (Article article : struct.articles){
            IPart part = article;

                while (part.getUp() != null) {
                    part = part.getUp();
                    System.out.println("wchodzę w górę");
                }
                while (part.getDown() != null && !part.getClass().isAssignableFrom(article.getClass())) {
                    System.out.println(part.toString());
                    part = part.getDown();
                    System.out.println("schodzę w dół");
                }
            writePart(part);
        }
    }

    public void printArticle (StructBuilder struct, Integer num){
        if(num > struct.articles.size()-1){
            throw new TestException("za duży indeks");
        }
        writePart(struct.articles.get(num-1));
    }

    public void printArticles (StructBuilder struct, Integer num1, Integer num2){
        for (int i = num1-1;  i<num2; i++){
            writePart(struct.articles.get(i));
        }
    }

    public void printChapter (StructBuilder struct, String chapterName){
        Integer art1 = findChapter(struct, chapterName);
        Integer art2 = findNextChapter(struct, art1);

        Integer i;
        for (i = art1-1; i<art2-1; i++){
            IPart part = struct.articles.get(i);

            while (part.getUp() != null){
                part = part.getUp();
                System.out.println("wchodzę w górę");
            }
            while (part.getDown() != null && !part.getClass().isAssignableFrom(struct.articles.get(i).getClass()) ) {
                System.out.println(part.toString());
                part = part.getDown();
                System.out.println("schodzę w dół");
            }

            writePart(part);
        }
    }

    public void printSection (StructBuilder struct, String chapterName){
        Integer art1 = findSection(struct, chapterName);
        Integer art2 = findNextSection(struct, art1);

        Integer i;
        for (i = art1-1; i<art2-1; i++){
            IPart part = struct.articles.get(i);

            while (part.getUp() != null){
                part = part.getUp();
                System.out.println("wchodzę w górę");
            }
            while (part.getDown() != null && !part.getClass().isAssignableFrom(struct.articles.get(i).getClass()) ) {
                System.out.println(part.toString());
                part = part.getDown();
                System.out.println("schodzę w dół");
            }

            writePart(part);
        }
    }

    public void printParagraph (StructBuilder struct, String paragraphName, Integer articleNumber){
        IPart partToPrint = findParagraph (struct, paragraphName, struct.articles.get(articleNumber-1).getDown());
        System.out.println("wróciłem do print paragraph z tym -> " +partToPrint.toString());
        printDown(partToPrint);
    }

    public void printPoint (StructBuilder struct, String pointName, String paragraphName, Integer articleNumber){
        IPart pointFrom = findParagraph(struct, paragraphName, struct.articles.get(articleNumber-1).getDown());
        System.out.println("wróciłem do print paragraph z tym -> " +pointFrom.toString());
        IPart partToPrint = findPoint(struct, pointName, pointFrom.getDown());
        System.out.println("wróciłem do print point z tym -> " +partToPrint.toString());
        printDown(partToPrint);

    }

    public void printPart (Integer depth, ArrayList<IPart> parts, ArrayList<String> names, IPart partIn){
       Integer i;
        for ( i=0; i<depth; i++){
            System.out.println("wchodzę do pętli z i " + i);
            partIn = findNextPart(parts.get(i), names.get(i), partIn.getDown());
            System.out.println("wróciłem do printPart z "+partIn.toString() + i);

        }
        printDown(partIn);
    }

    public void writePart(IPart part){
        System.out.println(part.toString());
        if (part.getDown() != null){
            writePart(part.getDown());
        }
        if(part.getRight() != null){
            writePart(part.getRight());
        }
    }

    public void printDown(IPart part){
        System.out.println(part.toString());
        if (part.getDown() != null){
            writePart(part.getDown());
        }
    }

    public Integer findSection (StructBuilder struct, String sectionName){
        Integer i;
        for (i=0; i<struct.articles.size(); i++){
            IPart part = struct.articles.get(i);
            System.out.println("jestem w artykule " + (i+1));
            while (part.getUp() != null){
                part = part.getUp();
                if (part.getClass().isAssignableFrom(Section.class)){
                    String str = part.getBody();
                    System.out.println("znalazłem obiekt o klasie rozdział");
                    if (str.contains(sectionName)){
                        return i+1;
                    }else{
                        break;
                    }
                }
            }
        }
        return null;
    }

    public Integer findNextSection (StructBuilder struct, int num){
        Integer i;
        for (i=num; i<struct.articles.size(); i++){
            IPart part = struct.articles.get(i);
            System.out.println("jestem w artykule " + (i+1));
            while (part.getUp() != null){
                part = part.getUp();
                System.out.println("będę sprawdzał klasę");
                if (part.getClass().isAssignableFrom(Section.class)){
                    System.out.println("znalazłem obiekt o klasie rozdział");
                    return i+1;
                }
                System.out.println("wchodzę w górę");
            }
        }
        return null;
    }

    public Integer findChapter (StructBuilder struct, String chapterName){
        Integer i;
        for (i=0; i<struct.articles.size(); i++){
            IPart part = struct.articles.get(i);
            System.out.println("jestem w artykule " + (i+1));
            while (part.getUp() != null){
                part = part.getUp();
                if (part.getClass().isAssignableFrom(Chapter.class)){
                    String str = part.getBody();
                    System.out.println("znalazłem obiekt o klasie rozdział");
                    if (str.contains(chapterName)){
                        return i+1;
                    }else{
                        break;
                    }
                }
            }
        }
        return null;
    }

    public Integer findNextChapter (StructBuilder struct, int num){
        Integer i;
        for (i=num; i<struct.articles.size(); i++){
            IPart part = struct.articles.get(i);
            System.out.println("jestem w artykule " + (i+1));
            while (part.getUp() != null){
                part = part.getUp();
                System.out.println("będę sprawdzał klasę");
                if (part.getClass().isAssignableFrom(Chapter.class)){
                    System.out.println("znalazłem obiekt o klasie rozdział");
                        return i+1;
                }
                System.out.println("wchodzę w górę");
            }
        }
        return null;
    }

    public IPart findParagraph (StructBuilder struct, String paragraphName, IPart part){
        IPart partToReturn = part;

        System.out.println("to jest tekst zawarty w wejściowym part " +part.toString());

        if(part.getClass().isAssignableFrom(Paragraph.class)){
            String str = part.getBody();
            System.out.println("znalazłem obiekt o klasie paragraph " + str);
            if(str.contains(paragraphName)){
                System.out.print("teraz zwrócę obiekt paragraph" + part.toString());
                partToReturn = part;
            } else if (part.getRight() != null){
                partToReturn = findParagraph(struct, paragraphName, part.getRight());
            } else {
                return null;
            }
        } else if (part.getDown() != null){
            partToReturn = findParagraph(struct, paragraphName, part.getDown());
        }   else{
            return null;
        }
        return partToReturn;
    }

    public IPart findPoint (StructBuilder struct, String pointName, IPart part){
        IPart partToReturn = part;

        System.out.println("to jest tekst zawarty w wejściowym part " +part.toString());

        if(part.getClass().isAssignableFrom(Point.class)){
            String str = part.getBody();
            System.out.println("znalazłem obiekt o klasie point " + str);
            if(str.contains(pointName)){
                System.out.print("teraz zwrócę obiekt point" + part.toString());
                partToReturn = part;
            } else if (part.getRight() != null){
                System.out.println("pójdę w prawo do findPoint");
                partToReturn = findPoint(struct, pointName, part.getRight());
            } else {
                System.out.println("zwrócę null w zagnieżdżonym ifie");
                return null;
            }
        } else if (part.getDown() != null){
            System.out.println("pójdę w dół do findPoint");
            partToReturn = findPoint(struct, pointName, part.getDown());
        }   else{
            return null;
        }
        return partToReturn;
    }

    public IPart findNextPart(IPart simplePart, String partName, IPart partIn){
        System.out.println("to jest tekst zawarty w wejściowym part " +partIn.toString());
        IPart partToReturn = partIn;
        IPart partS = partIn;

        if(partS.getClass().isAssignableFrom(simplePart.getClass())){
            String str = partS.getBody();
            System.out.println("znalazłem obiekt o body " + str);

            if(str.contains(partName)){
                System.out.print("teraz zwrócę obiekt o body" + partS.toString());
                partToReturn = partS;
            } else if (partS.getRight() != null){
                System.out.println("pójdę w prawo do findeNxtPart");
                partToReturn = findNextPart(simplePart, partName, partS.getRight());
            } else {
                System.out.println("zwrócę null w zagnieżdżonym ifie");
                return null;
            }
        } else {
            System.out.println("tutaj nie znajdziesz szukanego elementu");
            return null;
        }
        return partToReturn;
    }
}
