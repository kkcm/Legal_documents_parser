package main;

public class TextPrinter {
    public TextPrinter(){

    }

    public void printAll(StructBuilder struct){

        for (Article article : struct.articles){
            IPart part = article;

            while (part.getUp() != null){
                part = part.getUp();
                System.out.println("wchodzę w górę");
            }
            while (part.getDown() != null && !part.getClass().isAssignableFrom(article.getClass()) ) {
                System.out.println(part.toString());
                part = part.getDown();
                System.out.println("schodzę w dół");
            }

            writePart(part);

        }
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

}
