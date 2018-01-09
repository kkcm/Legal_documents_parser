package main;

import java.util.ArrayList;

public class ObjectHierarchy {
    private ArrayList<Object> objects = new ArrayList<>();

    public ObjectHierarchy(){
        objects.add(new Title());
        objects.add(new Section());
        objects.add(new Chapter());
        objects.add(new Subtitle());
        objects.add(new Article());
        objects.add(new ArticleWithLetter());
        objects.add(new Paragraph());
        objects.add(new Point());
        objects.add(new Letter());

    }

    public boolean isHigher(Object o1, Object o2){
        Integer i;
        for ( i=0 ; i < objects.size(); i++){
            if (objects.get(i).getClass().isAssignableFrom(o1.getClass())){
             //   System.out.println("ok1");
                Integer a;
                for (a = i; a<objects.size(); a++){
                    if (objects.get(a).getClass().isAssignableFrom(o2.getClass())){
                    //    System.out.println("klasa 2 NIE jest wyżej w hierarchi");
                        return false;
                    }
                }
            }
        }
    //    System.out.println("klasa 2 jest wyżej w hierarchi");
        return true;
    }

    public boolean isEqual(Object o1, Object o2){
        if(o1.getClass().isAssignableFrom(o2.getClass())){
            return true;
        } else{
            return false;
        }
    }
}
