package main;

import java.util.ArrayList;

public class StructBuilder {
    private ArrayList<String> textInfo = new ArrayList<>();
    ArrayList<Article> articles = new ArrayList<>();
    private IPart last = new Section();
    private IPart lastParagraph = new Paragraph();
    private IPart lastPoint = new Point();
    private IPart lastArticleWithLetter = new ArticleWithLetter();

    public StructBuilder() {
        textInfo.add("to będzie tytuł");
        textInfo.add("to będzie artykuł");
        textInfo.add("to będzie artykuł z literką");
        textInfo.add("to będzie dział");
        textInfo.add("to będzie rozdział");
        textInfo.add("to będzie ustęp");
        textInfo.add("to będzie punkt");
        textInfo.add("to będzie litera");
        textInfo.add("to pominiemy, bo kancelaria");
        textInfo.add("to pominiemy, bo data");
        textInfo.add("to pominiemy, bo pojedyncza litera/cyfra");
        textInfo.add("to będzie podtytuł");

        last.saveLine("Twój plik:");
    }

    public void writeInfo (ArrayList<Boolean> matches, String in){

        String in1 = in.replaceAll("\\D", " ");
        String[]  numbers = in1.trim().split(" ");
        boolean test = false;

        for (int i=0; i < matches.size(); i++){
            if (matches.get(i)){
                System.out.println(textInfo.get(i) +" numer: "+ numbers[0]);
                test = true;

                if(textInfo.get(i).equals("to pominiemy, bo pojedyncza litera/cyfra")){
                    break;
                }

                if(textInfo.get(i).equals("to będzie tytuł")){
                    System.out.println("wszedłem w tytuł");
                    Title newTitle = new Title();

                    newTitle.setUp(this.last);
                    this.last.setDown(newTitle);

                    this.last = newTitle;
                    newTitle.saveLine(in);
                    System.out.println(newTitle.toString());
                    break;
                }

                if(textInfo.get(i).equals("to będzie dział")){
                    System.out.println("wszedłem w dział");
                    Section newSection = new Section();

                    ObjectHierarchy hierarchy = new ObjectHierarchy();
                    if(!hierarchy.isHigher(this.last, newSection)){
                        newSection.setUp(this.last);
                        this.last.setDown(newSection);
                    }

                    this.last = newSection;
                    newSection.saveLine(in);
                    System.out.println(newSection.toString());
                    break;
                }

                if (textInfo.get(i).equals("to będzie rozdział")){
                    System.out.println("wszedłem w rozdział");
                    Chapter newChapter = new Chapter();

                    ObjectHierarchy hierarchy = new ObjectHierarchy();
                    if (!hierarchy.isHigher(this.last, newChapter)){
                        newChapter.setUp(this.last);
                        this.last.setDown(newChapter);
                    }

                    this.last = newChapter;
                    newChapter.saveLine(in);
                    System.out.println(newChapter.toString());
                }

                if (textInfo.get(i).equals("to będzie podtytuł")){
                    System.out.println("wszedłem w podtytuł");
                    Subtitle newSubtitle = new Subtitle();

                    ObjectHierarchy hierarchy = new ObjectHierarchy();
                    if (!hierarchy.isHigher(this.last, newSubtitle)){
                        newSubtitle.setUp(this.last);
                        this.last.setDown(newSubtitle);
                    }

                    this.last = newSubtitle;
                    newSubtitle.saveLine(in);
                    System.out.println(newSubtitle.toString());
                }


                if (textInfo.get(i).equals("to będzie artykuł")){
                    System.out.println("wszedłem w artykuł");
                    Article newArticle = new Article();

                    ObjectHierarchy hierarchy = new ObjectHierarchy();
                    if (!hierarchy.isHigher(this.last, newArticle)){
                        if(this.last.getClass().isAssignableFrom(newArticle.getClass())){
                            System.out.println("poprzednia klasa też byla artykulem");
                        }else {
                            newArticle.setUp(this.last);
                            this.last.setDown(newArticle);
                            System.out.println("poprzednia klasa NIE byla artykulem");
                        }
                    }

                    this.last = newArticle;
                    articles.add(newArticle);
                    newArticle.saveLine(in);
                    System.out.println(newArticle.toString());
                }

                if(textInfo.get(i).equals("to będzie artykuł z literką")) {
                    System.out.println("wszedłem w artykuł z literką");
                    ArticleWithLetter newArticleWithLetter = new ArticleWithLetter();

                    ObjectHierarchy hierarchy = new ObjectHierarchy();
                    if (!hierarchy.isHigher(this.last, newArticleWithLetter)) {
                        if (this.last.getClass().isAssignableFrom(newArticleWithLetter.getClass()) || this.last.getClass().isAssignableFrom(Article.class)) {
                            System.out.println("poprzednia klasa też byla artykulem z literką albo samym artykułem");
                            newArticleWithLetter.setLeft(this.last);
                            this.last.setRight(newArticleWithLetter);
                        } else {
                            System.out.println("poprzednia klasa NIE byla artykulem z literką ani artykułem więc łączę góra dół");
                            newArticleWithLetter.setUp(this.last);
                            this.last.setDown(newArticleWithLetter);

                            System.out.println("poprzednia klasa NIE byla artykulem z literką więc również z wcześniejszym artykułem bądź artykułem z literką");
                            if(articles.get(articles.size()-1).getRight() == null){
                                System.out.println("nie było pierwszego artykułu z literką pod ostatnim arytułem");
                                articles.get(articles.size()-1).setRight(newArticleWithLetter);
                                newArticleWithLetter.setLeft(articles.get(articles.size()-1));
                            } else {
                                System.out.println("BYŁ już jakiś artykuł z literką pod ostatnim arytułem");
                                newArticleWithLetter.setLeft(this.lastArticleWithLetter);
                                this.lastArticleWithLetter.setRight(newArticleWithLetter);
                            }

                        }
                    } else {
                        System.out.println("poprzednia klasa była niższa więc łączę z poprzednim artykułem z literką");

                        if(articles.get(articles.size()-1).getRight() == null){
                            System.out.println("nie było pierwszego artykułu z literką pod ostatnim arytułem");
                            articles.get(articles.size()-1).setRight(newArticleWithLetter);
                            newArticleWithLetter.setLeft(articles.get(articles.size()-1));
                        } else {
                            System.out.println("BYŁ już jakiś artykuł z literką pod ostatnim arytułem");
                            newArticleWithLetter.setLeft(this.lastArticleWithLetter);
                            this.lastArticleWithLetter.setRight(newArticleWithLetter);
                        }
                    }

                    this.last = newArticleWithLetter;
                    this.lastArticleWithLetter = newArticleWithLetter;
                    newArticleWithLetter.saveLine(in);
                    System.out.println(newArticleWithLetter.toString());
                }


                if(textInfo.get(i).equals("to będzie ustęp")){
                    System.out.println("wszedłem w ustęp");
                    Paragraph newParagraph = new Paragraph();

                    ObjectHierarchy hierarchy = new ObjectHierarchy();
                    if (!hierarchy.isHigher(this.last, newParagraph)){
                        if(this.last.getClass().isAssignableFrom(newParagraph.getClass())){
                            System.out.println("poprzednia klasa też byla ustępem");
                            newParagraph.setLeft(this.last);
                            this.last.setRight(newParagraph);
                        }else {
                            newParagraph.setUp(this.last);
                            this.last.setDown(newParagraph);
                            System.out.println("poprzednia klasa NIE byla ustępem");
                        }
                    } else {
                        System.out.println("poprzednia klasa była niższa więc łączę z poprzednim ustępem");
                        newParagraph.setLeft(this.lastParagraph);
                        this.lastParagraph.setRight(newParagraph);
                    }

                    this.last = newParagraph;
                    this.lastParagraph = newParagraph;
                    newParagraph.saveLine(in);
                    System.out.println(newParagraph.toString());
                }

                if(textInfo.get(i).equals("to będzie punkt")){
                    System.out.println(("wszedłem w punkt"));
                    Point newPoint = new Point();

                    ObjectHierarchy hierarchy = new ObjectHierarchy();
                    if(!hierarchy.isHigher(this.last, newPoint)){
                        if(this.last.getClass().isAssignableFrom(newPoint.getClass())){
                            System.out.println("poprzednia klasa też była punktem");
                            newPoint.setLeft(this.last);
                            this.last.setRight(newPoint);
                        } else {
                            newPoint.setUp(this.last);
                            this.last.setDown(newPoint);
                            System.out.println("poprzednia klasa NIE była punktem");
                        }
                    } else {
                        System.out.println("poprzednia klasa była niższa więc łączę z poprzednim punktem");
                        newPoint.setLeft(this.lastPoint);
                        this.lastPoint.setRight(newPoint);
                    }

                    this.lastPoint = newPoint;
                    this.last = newPoint;
                    newPoint.saveLine(in);
                    System.out.println(newPoint.toString());
                }

                if(textInfo.get(i).equals("to będzie litera")){
                    System.out.println(("wszedłem w literę"));
                    Letter newLetter = new Letter();

                    ObjectHierarchy hierarchy = new ObjectHierarchy();
                    if(!hierarchy.isHigher(this.last, newLetter)){
                        if(this.last.getClass().isAssignableFrom(newLetter.getClass())){
                            System.out.println("poprzednia klasa też była literą");
                            newLetter.setLeft(this.last);
                            this.last.setRight(newLetter);
                        } else {
                            newLetter.setUp(this.last);
                            this.last.setDown(newLetter);
                            System.out.println("poprzednia klasa NIE była punktem");
                        }
                    }

                    this.last = newLetter;
                    newLetter.saveLine(in);
                    System.out.println(newLetter.toString());
                    break;
                }




            }
        }
        if (!test){
            System.out.println("ten fragment będzie zapisany jako body");
            this.last.addLine(in);
        }
    }

}
