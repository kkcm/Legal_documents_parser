package main;

import java.util.ArrayList;

public class StructBuilder {
    ArrayList<String> textInfo = new ArrayList<>();

    public StructBuilder() {
        textInfo.add("to będzie artykuł");
        textInfo.add("to będzie dział");
        textInfo.add("to będzie rozdział");
        textInfo.add("to będzie ustęp");
        textInfo.add("to będzie punkt");
        textInfo.add("to będzie litera");
        textInfo.add("to pominiemy, bo kancelaria");
        textInfo.add("to pominiemy, bo data");
        textInfo.add("to pominiemy, bo pojedyncza litera/cyfra");
        textInfo.add("to będzie podtytuł");
    }

    public void writeInfo (ArrayList<Boolean> matches, String in){

        in = in.replaceAll("\\D", " ");
        String[]  numbers = in.trim().split(" ");
        boolean test = false;

        for (int i=0; i < matches.size(); i++){
            if (matches.get(i)){
                System.out.println(textInfo.get(i) +" numer: "+ numbers[0]);
                test = true;
            }
        }
        if (!test){
            System.out.println("ten fragment będzie zapisany jako body");
        }
    }
}
