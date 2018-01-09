package main;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextParser {
    private ArrayList<String> patternStrings = new ArrayList<>();
    private ArrayList<Pattern> patterns = new ArrayList<>();
    private ArrayList<Matcher> matchers = new ArrayList<>();
    private ArrayList<Boolean> matches = new ArrayList<>();

    public TextParser() {
        String constitutionPatternString = ".*\\u00a9.*";
        patternStrings.add(constitutionPatternString);

        String datePatternString = "[0-9]{4}\\-[0-9]{2}\\-[0-9]{2}.*";
        patternStrings.add(datePatternString);

        String singlePatternString = "[0-9 | A-z]{1}";
        patternStrings.add(singlePatternString);

        String fatalArticlesPatternString = "Art. 115–129. \\(pominięte\\)";
        patternStrings.add(fatalArticlesPatternString);

        String titlePatternString = "KONSTYTUCJA|RZECZYPOSPOLITEJ POLSKIEJ|Dz.U. 2007 Nr 50 poz. 331|USTAWA";
        patternStrings.add(titlePatternString);

        String sectionPatternString = "DZIAŁ.*";
        patternStrings.add(sectionPatternString);

        String chapterPatternString = "Rozdział.*";
        patternStrings.add(chapterPatternString);

        String subtitlePatternString = "[A-ZŻŹĆĄŚĘŁÓŃ | \\, | \\t]++";
        patternStrings.add(subtitlePatternString);

        String articlePatternString = "Art\\. [0-9]++\\..*";
        patternStrings.add(articlePatternString);

        String articleWithLetterPatternString =  "Art. [0-9]++[a-z]++. .*";
        patternStrings.add(articleWithLetterPatternString);

        String paragraphPatternString = "[0-9]++\\..*|Art\\. [0-9]++\\. [0-9]++\\..*|[0-9]++[a-z]\\..*|Art\\. [0-9]++[a-z]++\\. [0-9]++\\..*" ;  // Art. 99a. 1.
        patternStrings.add(paragraphPatternString);

        String pointPatternString = "[0-9]++\\) .*|[0-9]++[a-z]++\\) .*";
        patternStrings.add(pointPatternString);

        String letterPatternString = "[a-z]\\) .*";
        patternStrings.add(letterPatternString);

    }

    public void createPattern (){
        for (String patternString : patternStrings){
            patterns.add(Pattern.compile(patternString));
        }
    }

    public void checkInLine (String textLine){

        for (Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(textLine);
            this.matchers.add(matcher);
        }
    }

    public void isInLine (){

        for (Matcher matcher : matchers) {
            Boolean isMatches = matcher.matches();
            this.matches.add(isMatches);
        }
    }

    public ArrayList<Boolean> getMatches() {
        return matches;
    }
}
