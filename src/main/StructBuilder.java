package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class StructBuilder {
    private ArrayList<String> textInfo = new ArrayList<>();
    ArrayList<Article> articles = new ArrayList<>();
    private IPart last = new Section();
    private IPart lastParagraph = new Paragraph();
    private IPart lastPoint = new Point();
    private IPart lastArticleWithLetter = new ArticleWithLetter();

    public StructBuilder() {
        textInfo.add("kancelaria");
        textInfo.add("data");
        textInfo.add("litera/cyfra");
        textInfo.add("fatal");
        textInfo.add("tytuł");
        textInfo.add("dział");
        textInfo.add("rozdział");
        textInfo.add("podtytuł");
        textInfo.add("artykuł");
        textInfo.add("artykuł z literką");
        textInfo.add("ustęp");
        textInfo.add("punkt");
        textInfo.add("litera");

        last.saveLine("Twój plik:");
    }

    public StructBuilder readFile(String inputFilePath){

        try{
            FileReader fileReader = new FileReader(inputFilePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String textLine = bufferedReader.readLine();
            do {
                TextParser textParser = new TextParser();
                textParser.createPattern();
                textParser.checkInLine(textLine);
                textParser.isInLine();
                this.parseText(textParser.getMatches(), textLine);
                //    System.out.println(textLine);
                textLine = bufferedReader.readLine();
            } while(textLine != null);
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            System.err.println("File does not exist");
            System.exit(0);
        } catch (IOException e) {
            System.err.println("File exists, but there was IOException");
        }

        return this;
    }


    public void parseText (ArrayList<Boolean> matches, String in){

        boolean test = false;
        boolean test2 = false;
        Integer counter;
        TextEditor textEditor = new TextEditor();
        String[] parts ={};
        counter = 0;
        parts = textEditor.cutLine(in, " ");

        for (int i=0; i < matches.size(); i++){

            if (matches.get(i)){
                test = true;

                if(textInfo.get(i).equals("litera/cyfra") || textInfo.get(i).equals("data") || textInfo.get(i).equals("kancelaria") ){
                    test2 = true;
                    break;
                }

                if(textInfo.get(i).equals("fatal")){
                    this.littleCheat();
                    test2 = true;
                    break;
                }

                if(textInfo.get(i).equals("tytuł")){
                    test2 = true;
                    this.createNewTitle(in);
                    break;
                }

                if (textInfo.get(i).equals("podtytuł")){
                    test2 = true;
                    this.createNewSubtitle(in);
                    break;
                }


                if(textInfo.get(i).equals("dział")){
                    String description = textEditor.concatTableElement(parts, " ", counter++, counter++);
                    this.createNewSection(description);
                    break;
                }

                if (textInfo.get(i).equals("rozdział")){
                    String description = textEditor.concatTableElement(parts, " ", counter++, counter++);
                    this.createNewChapter(description);
                }

                if (textInfo.get(i).equals("artykuł")){
                    String description = textEditor.concatTableElement(parts, " ", counter++, counter++);
                    this.createNewArticle(description);
                }

                if(textInfo.get(i).equals("artykuł z literką")) {
                    String description = textEditor.concatTableElement(parts, " ", counter++, counter++);
                    this.createNewArticleWithLetter(description);
                }


                if(textInfo.get(i).equals("ustęp")){
                    String description = parts[counter++];
                    this.createNewParagraph(description);
                }

                if(textInfo.get(i).equals("punkt")){
                    String description = parts[counter++];
                    this.createNewPoint(description);
                }

                if(textInfo.get(i).equals("litera")){
                    String description = parts[counter++];
                    this.createNewLetter(description);
                }

            }
        }

        if (!test){
            this.last.addLine(in);
        } else if (!test2) {
            String str = textEditor.concatTableElement(parts, " ", counter, parts.length-1);
            if(this.last.getBody() == null){
                this.last.saveLine(str);
            }else {
                this.last.addLine(str);
            }
        }
    }


    public void createNewTitle(String description){
        Title newTitle = new Title();
        newTitle.setUp(this.last);
        this.last.setDown(newTitle);
        this.last = newTitle;
        newTitle.saveDescription(description);
    }

    public void createNewSubtitle(String description){
        Subtitle newSubtitle = new Subtitle();

        ObjectHierarchy hierarchy = new ObjectHierarchy();
        if (!hierarchy.isHigher(this.last, newSubtitle)){
            newSubtitle.setUp(this.last);
            this.last.setDown(newSubtitle);
        }
        this.last = newSubtitle;
        newSubtitle.saveDescription(description);
    }

    public void createNewSection(String description){
        Section newSection = new Section();

        ObjectHierarchy hierarchy = new ObjectHierarchy();
        if(!hierarchy.isHigher(this.last, newSection)){
            newSection.setUp(this.last);
            this.last.setDown(newSection);
        }
        newSection.saveDescription(description);
        this.last = newSection;
    }

    public void createNewChapter (String description){
        Chapter newChapter = new Chapter();

        ObjectHierarchy hierarchy = new ObjectHierarchy();
        if (!hierarchy.isHigher(this.last, newChapter)){
            newChapter.setUp(this.last);
            this.last.setDown(newChapter);
        }
        newChapter.saveDescription(description);
        this.last = newChapter;
    }

    public void createNewArticle (String description){
        Article newArticle = new Article();

        ObjectHierarchy hierarchy = new ObjectHierarchy();
        if (!hierarchy.isHigher(this.last, newArticle)){
            if(this.last.getClass().isAssignableFrom(newArticle.getClass())){
            }else {
                newArticle.setUp(this.last);
                this.last.setDown(newArticle);
            }
        }
        newArticle.saveDescription(description);
        this.last = newArticle;
        articles.add(newArticle);
    }

    public void createNewArticleWithLetter (String description){
        ArticleWithLetter newArticleWithLetter = new ArticleWithLetter();

        ObjectHierarchy hierarchy = new ObjectHierarchy();
        if (!hierarchy.isHigher(this.last, newArticleWithLetter)) {
            if (hierarchy.isEqual(this.last, newArticleWithLetter) || hierarchy.isEqual(this.last, new Article())) {
//                System.out.println("poprzednia klasa też byla artykulem z literką albo samym artykułem");
                newArticleWithLetter.setLeft(this.last);
                this.last.setRight(newArticleWithLetter);
            } else {
//                System.out.println("poprzednia klasa NIE byla artykulem z literką ani artykułem więc łączę góra dół");
                newArticleWithLetter.setUp(this.last);
                this.last.setDown(newArticleWithLetter);

//                System.out.println("poprzednia klasa NIE byla artykulem z literką więc również z wcześniejszym artykułem bądź artykułem z literką");
                if(articles.get(articles.size()-1).getRight() == null){
//                    System.out.println("nie było pierwszego artykułu z literką pod ostatnim arytułem");
                    articles.get(articles.size()-1).setRight(newArticleWithLetter);
                    newArticleWithLetter.setLeft(articles.get(articles.size()-1));
                } else {
//                    System.out.println("BYŁ już jakiś artykuł z literką pod ostatnim arytułem");
                    newArticleWithLetter.setLeft(this.lastArticleWithLetter);
                    this.lastArticleWithLetter.setRight(newArticleWithLetter);
                }
            }
        } else {
//            System.out.println("poprzednia klasa była niższa więc łączę z poprzednim artykułem z literką");
            if(articles.get(articles.size()-1).getRight() == null){
//                System.out.println("nie było pierwszego artykułu z literką pod ostatnim arytułem");
                articles.get(articles.size()-1).setRight(newArticleWithLetter);
                newArticleWithLetter.setLeft(articles.get(articles.size()-1));
            } else {
//                System.out.println("BYŁ już jakiś artykuł z literką pod ostatnim arytułem");
                newArticleWithLetter.setLeft(this.lastArticleWithLetter);
                this.lastArticleWithLetter.setRight(newArticleWithLetter);
            }
        }

        newArticleWithLetter.saveDescription(description);
        this.last = newArticleWithLetter;
        this.lastArticleWithLetter = newArticleWithLetter;
    }

    public void createNewParagraph (String description){
        Paragraph newParagraph = new Paragraph();

        ObjectHierarchy hierarchy = new ObjectHierarchy();
        if (!hierarchy.isHigher(this.last, newParagraph)){
            if(this.last.getClass().isAssignableFrom(newParagraph.getClass())){
                newParagraph.setLeft(this.last);
                this.last.setRight(newParagraph);
            }else {
                newParagraph.setUp(this.last);
                this.last.setDown(newParagraph);
            }
        } else {
            newParagraph.setLeft(this.lastParagraph);
            this.lastParagraph.setRight(newParagraph);
        }

        newParagraph.saveDescription(description);
        this.last = newParagraph;
        this.lastParagraph = newParagraph;
    }

    public void createNewPoint (String description){
        Point newPoint = new Point();

        ObjectHierarchy hierarchy = new ObjectHierarchy();
        if(!hierarchy.isHigher(this.last, newPoint)){
            if(this.last.getClass().isAssignableFrom(newPoint.getClass())){
                newPoint.setLeft(this.last);
                this.last.setRight(newPoint);
            } else {
                newPoint.setUp(this.last);
                this.last.setDown(newPoint);
            }
        } else {
            newPoint.setLeft(this.lastPoint);
            this.lastPoint.setRight(newPoint);
        }

        newPoint.saveDescription(description);
        this.lastPoint = newPoint;
        this.last = newPoint;
    }

    public void createNewLetter (String description){
        Letter newLetter = new Letter();

        ObjectHierarchy hierarchy = new ObjectHierarchy();
        if(!hierarchy.isHigher(this.last, newLetter)){
            if(this.last.getClass().isAssignableFrom(newLetter.getClass())){
                newLetter.setLeft(this.last);
                this.last.setRight(newLetter);
            } else {
                newLetter.setUp(this.last);
                this.last.setDown(newLetter);
            }
        }
        newLetter.saveDescription(description);
        this.last = newLetter;
    }

    public void littleCheat(){
        Integer a;

        Article newArticle1 = new Article();
        newArticle1.saveDescription("Art. 115.");
        newArticle1.saveLine("(pominięte)");
        this.articles.add(newArticle1);
        newArticle1.setUp(this.last);
        this.last.setDown(newArticle1);

        for(a=116; a<130; a++){
            Article newArticle = new Article();
            newArticle.saveDescription("Art. "+a+".");
            newArticle.saveLine("(pominięte)");
            this.articles.add(newArticle);
            this.last = newArticle;
        }

    }

}
